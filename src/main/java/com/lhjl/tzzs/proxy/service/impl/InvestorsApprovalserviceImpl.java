package com.lhjl.tzzs.proxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.lhjl.tzzs.proxy.dto.*;
import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.UserInfoService;
import com.lhjl.tzzs.proxy.service.UserIntegralsService;
import com.lhjl.tzzs.proxy.service.UserLevelService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.InvestorsApprovalService;
import tk.mybatis.mapper.entity.Example;

@Service
public class InvestorsApprovalserviceImpl extends GenericService implements InvestorsApprovalService {

    @Value("${pageNum}")
    private Integer defaultPageNum;

    @Value("${pageSize}")
    private Integer defaultPageSize;

    @Resource
    private InvestorsApprovalMapper investorsApprovalMapper;

    @Resource
    private UserTokenMapper userTokenMapper;
    @Resource
    private UsersMapper usersMapper;
    @Resource

    private InvestorsMapper investorsMapper;
    @Resource
    private UserLevelService userLevelService;
    @Resource
    private UsersWeixinMapper usersWeixinMapper;
    @Resource
    private InvestorInvestmentCaseMapper investorInvestmentCaseMapper;

    @Autowired
    private WxMaService wxService;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private SubjectTypeRelationalMapper subjectTypeRelationalMapper;

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserIntegralsService userIntegralsService;

    @Autowired
    private UserLevelRelationMapper userLevelRelationMapper;

    @Autowired
    private FoundersMapper foundersMapper;

    @Autowired
    private FounderAduitRecordMapper founderAduitRecordMapper;

    /**
     * 新后台用户列表审核接口
     *
     * @param body
     * @return
     */
    @Override
    public CommonDto<String> adminSpecialApproval(InvestorSpecialApprovalDto body, Integer appid) {
        CommonDto<String> result = new CommonDto<>();
        if (body.getUserId() == null) {
            result.setMessage("用户id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.getInvestorType() == null && body.getUserLevel() == null) {
            result.setMessage("审核状态不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (StringUtils.isAnyBlank(body.getComanyDuties(), body.getCompanyName(), body.getUserName())) {
            result.setMessage("用户姓名，公司职务，公司名称不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (body.getInvestorType() == 2) {
            //  审核创始人逻辑
            CommonDto<String> resultFounderAduit = founderAduitRecord(body, appid);
            if (resultFounderAduit.getStatus() != 200) {
                result = resultFounderAduit;

                return result;
            }

        } else {
            // 审核投资人
            CommonDto<String> approvalResult = adimSpecialApproval(body);
            if (approvalResult.getStatus() != 200) {
                result = approvalResult;

                return result;
            }

            // 发模板消息
            CommonDto<String> resultResult = userInfoService.getUserFormid(body.getUserId());
            if (resultResult.getStatus() == 200) {
                Integer status = 0;
                if (null != body.getInvestorType() && body.getInvestorType() == 0) {
                    status = 3;
                } else if (null != body.getInvestorType() && body.getInvestorType() == 1) {
                    status = 4;
                }
                if (status > 0) {
                    CommonDto<String> sendResult = sendTemplate(body.getUserId(), status, resultResult.getData());
                    if (sendResult.getStatus() == 200) {
                        userInfoService.setUserFormid(resultResult.getData());
                    }
                }
            }
        }


        // 如果管理员选择升级会员等级的时候，升级会员
        if (body.getUserLevel() != null) {
            CommonDto<String> upLevelResult = updateSpecailLevel(body.getUserLevel(), body.getUserId());
            if (upLevelResult.getStatus() != 200) {
                result = upLevelResult;

                return result;
            }
        }

        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 认证投资人信息记录
     */
    @Transactional
    @Override
    public CommonDto<String> saveTouZi(TouZiDto params) {
        CommonDto<String> result = new CommonDto<String>();
        try {
            //先取出参数进行验证
            String token = params.getToken();
            String compellation = params.getCompellation();
            String dataName = params.getDateName();
            String company = params.getOrganization();
            String companyDuties = params.getFillOffice();
            String formId = params.getFormId();

            if ("".equals(token) || token == null || "undefined".equals(token)) {
                result.setData(null);
                result.setMessage("用户token不能为空");
                result.setStatus(50001);

                this.LOGGER.info("投资人认证场景");
                this.LOGGER.info("用户token不能为空");

                return result;
            }

            if ("".equals(compellation) || compellation == null || "undefined".equals(compellation)) {
                result.setData(null);
                result.setMessage("请填写姓名");
                result.setStatus(50001);

                this.LOGGER.info("投资人认证场景");
                this.LOGGER.info("请填写姓名");

                return result;
            }


            if ("".equals(company) || company == null || "undefined".equals(company)) {
                result.setData(null);
                result.setMessage("请填写所在公司");
                result.setStatus(50001);

                this.LOGGER.info("投资人认证场景");
                this.LOGGER.info("请填写所在公司");

                return result;
            }

            if ("".equals(companyDuties) || companyDuties == null || "undefined".equals(companyDuties)) {
                result.setData(null);
                result.setMessage("请填写所在公司职务");
                result.setStatus(50001);

                this.LOGGER.info("投资人认证场景");
                this.LOGGER.info("请填写所在公司职务");

                return result;
            }

            if ("".equals(formId) || formId == null || "undefined".equals(formId)) {
                result.setData(null);
                result.setMessage("formId不存在");
                result.setStatus(50001);

                this.LOGGER.info("投资人认证场景");
                this.LOGGER.info("formId不存在");

                return result;
            }


            InvestorsApproval investorsApproval = new InvestorsApproval();
            
            UserToken userToken = new UserToken();
            userToken.setToken(params.getToken());
            List<UserToken> userTokens = userTokenMapper.select(userToken);
            //设置用户id
            if(userTokens!=null && userTokens.size()>0) {
            	investorsApproval.setUserid(userTokens.get(0).getUserId());
            }
            
            //设置认证类型
            investorsApproval.setInvestorsType(Integer.valueOf(dataName));
            //设置申请人姓名
            investorsApproval.setApprovalUsername(params.getCompellation());
            //设置认证说明
            investorsApproval.setDescription(params.getEvaContent());
            //所在公司
            investorsApproval.setCompany(params.getOrganization());
            //担任职务
            investorsApproval.setCompanyDuties(params.getFillOffice());
            //工作名片
            investorsApproval.setWorkCard(params.getTempFilePaths());
            
            //设置为待审核状态
            investorsApproval.setApprovalResult(0);
            //设置审核时间为，默认为创建的时间
            investorsApproval.setReviewTime(new Date());
            
            investorsApproval.setCreateTime(new Date());
            //投资人投资案例
            investorsApproval.setInvestorsApprovalcolCase(params.getInvestorsApprovalcolCase());
            //formId
            investorsApproval.setFormId(formId);
            investorsApprovalMapper.insertSelective(investorsApproval);
            
            //更新用户表中的信息
            Users users = new Users();
            //params.getToken()指的是认证id
            users.setUuid(params.getToken());
            Users u = usersMapper.selectOne(users);
            
            u.setCompanyName(params.getOrganization());
            u.setCompanyDuties(params.getFillOffice());
            u.setActualName(params.getCompellation());
            u.setWorkCard(params.getTempFilePaths());
            
            usersMapper.updateByPrimaryKey(u);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 认证信息回显
     */
    @Override
    public CommonDto<Map<String, Object>> findInvestorsApproval(String token) {
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();
        try {
            
            UserToken userToken = new UserToken();
            userToken.setToken(token);
            userToken = userTokenMapper.selectOne(userToken);
            Integer userId = userToken.getUserId();
            
            Users users1 = new Users();
            users1.setUuid(token);
            users1 = usersMapper.selectOne(users1);

            Map<String, Object> map = investorsApprovalMapper.findInvestorsApproval(userId);
            /*String anli = "";
            if (map != null) {
            	if(map.get("investors_approvalcol_case") != null) {
            		anli = String.valueOf(map.get("investors_approvalcol_case"));
            	}
            } 
*/
            String[] anliArray = {};
            if (map != null) {
            	
            	if(map.get("investors_approvalcol_case") != null) {
            		anliArray = String.valueOf(map.get("investors_approvalcol_case")).split(",");
            	}
            	
                map.put("id", token);
                map.put("renzhengtouzirenshenhebiao7additional", "");//其他说明
                map.put("renzhengtouzirenshenhebiao7applicantn", String.valueOf(map.get("approval_username")));
                map.put("renzhengtouzirenshenhebiao7assumeoffi", String.valueOf(map.get("company_duties")));
                map.put("renzhengtouzirenshenhebiao7certificat", String.valueOf(map.get("company")));
                map.put("renzhengtouzirenshenhebiao7wherecompa", String.valueOf(map.get("description")));
                map.put("investorsApprovalcolCase", anliArray);
                
                map.put("renzhengtouzirenshenhebiao7frontofbus", users1.getWorkCard());

                Map<String, Object> renzhenleixin = new HashMap<>();
                if (null != map.get("investors_type")) {
                    if (Integer.valueOf(String.valueOf(map.get("investors_type"))) == 0) {
                        renzhenleixin.put("0", true);
                        renzhenleixin.put("1", false);
                        renzhenleixin.put("2", false);
                    }
                    if (Integer.valueOf(String.valueOf(map.get("investors_type"))) == 1) {
                        renzhenleixin.put("0", false);
                        renzhenleixin.put("1", true);
                        renzhenleixin.put("2", false);
                    }
                    if (Integer.valueOf(String.valueOf(map.get("investors_type"))) == 2) {
                        renzhenleixin.put("0", false);
                        renzhenleixin.put("1", false);
                        renzhenleixin.put("2", true);
                    }
                } else {
                    renzhenleixin.put("0", false);
                    renzhenleixin.put("1", false);
                    renzhenleixin.put("2", false);
                }
                map.put("renzhenleixin", renzhenleixin);
            } else {
                map = new HashMap<>();
                
                Users users = new Users();
                users.setUuid(token);
                users = usersMapper.selectOne(users);
                String username = users.getActualName();

                map.put("id", token);
                map.put("renzhengtouzirenshenhebiao7applicantn", username);
                map.put("renzhengtouzirenshenhebiao7additional", null);//其他说明
                map.put("renzhengtouzirenshenhebiao7assumeoffi", users.getCompanyDuties());
                map.put("renzhengtouzirenshenhebiao7certificat", users.getCompanyName());
                map.put("renzhengtouzirenshenhebiao7frontofbus", users.getWorkCard());
                map.put("renzhengtouzirenshenhebiao7wherecompa", null);
                map.put("investorsApprovalcolCase", anliArray);
                
                Map<String, Object> renzhenleixin = new HashMap<>();
                renzhenleixin.put("0", false);
                renzhenleixin.put("1", false);
                renzhenleixin.put("2", false);
                map.put("renzhenleixin", renzhenleixin);
            }
            result.setData(map);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 进入审核状态的
     */

    @Override
    public CommonDto<Map<String, Object>> findInvestorsExamine(String token) {
        CommonDto<Map<String, Object>> result = new CommonDto<Map<String, Object>>();

        if (token == null || "".equals(token) || "undefined".equals(token)) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("success", false);
            obj.put("tips", "用户token不能为空");

            result.setStatus(50001);
            result.setMessage("用户token不能为空");
            result.setData(obj);

            return result;

        }

        Map<String, Object> map = new HashMap<>();
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken = userTokenMapper.selectOne(userToken);
        Integer userId = userToken.getUserId();
        //判断用户token是否有效
        if (userId == null) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("success", false);
            obj.put("tips", "用户token无效请检查");

            result.setData(obj);
            result.setMessage("用户token无效请检查");
            result.setStatus(50001);

            return result;
        }

        map = investorsApprovalMapper.findInvestorsApproval(userId);
        if (map != null) {
            if (Integer.valueOf(String.valueOf(map.get("approval_result"))) == 0) {
                map.put("gongsizhiwei", String.valueOf(map.get("company_duties")));
                map.put("gongsimingcheng", String.valueOf(map.get("company")));
                map.put("touzirenleixing", null);
                map.put("shenhewancheng", false);
                map.put("shenhezhong", true);
                map.put("weitongguo", false);
                map.put("yiquxiao", false);
                map.put("success", true);
            }
            if (Integer.valueOf(String.valueOf(map.get("approval_result"))) == 1) {
                map.put("gongsizhiwei", String.valueOf(map.get("company_duties")));
                map.put("gongsimingcheng", String.valueOf(map.get("company")));
                map.put("touzirenleixing", null);
                map.put("shenhewancheng", false);
                map.put("shenhezhong", false);
                map.put("weitongguo", true);
                map.put("yiquxiao", false);
                map.put("success", true);
            }
            if (Integer.valueOf(String.valueOf(map.get("approval_result"))) == 3) {
                map.put("gongsizhiwei", String.valueOf(map.get("company_duties")));
                map.put("gongsimingcheng", String.valueOf(map.get("company")));
                map.put("touzirenleixing", "个人投资人");
                map.put("shenhewancheng", true);
                map.put("shenhezhong", false);
                map.put("weitongguo", false);
                map.put("yiquxiao", false);
                map.put("success", true);
            }
            if (Integer.valueOf(String.valueOf(map.get("approval_result"))) == 4) {
                map.put("gongsizhiwei", String.valueOf(map.get("company_duties")));
                map.put("gongsimingcheng", String.valueOf(map.get("company")));
                map.put("touzirenleixing", "机构投资人");
                map.put("shenhewancheng", true);
                map.put("shenhezhong", false);
                map.put("weitongguo", false);
                map.put("yiquxiao", false);
                map.put("success", true);
            }
            //投资人认证的时候取消了VIP投资人的认证
            if (Integer.valueOf(String.valueOf(map.get("approval_result"))) == 5) {
                map.put("gongsizhiwei", String.valueOf(map.get("company_duties")));
                map.put("gongsimingcheng", String.valueOf(map.get("company")));
                map.put("touzirenleixing", "VIP投资人");
                map.put("shenhewancheng", true);
                map.put("shenhezhong", false);
                map.put("weitongguo", false);
                map.put("yiquxiao", false);
                map.put("success", true);
            }
        } else {
            map = new HashMap<>();
            map.put("success", true);
            map.put("shenhewancheng", false);
            map.put("shenhezhong", false);
            map.put("weitongguo", false);
            map.put("yiquxiao", false);
        }
        result.setData(map);
        return result;
    }

    /**
     * 获取投资审核信息
     * @param body 查询条件
     * @return
     */
    @Override
    public CommonDto<List<InvestorsApprovalNew>> findApprovals(InvestorsApprovalDto body) {
        CommonDto<List<InvestorsApprovalNew>> result = new CommonDto<>();
        List<InvestorsApproval> data = new ArrayList<>();
        List<InvestorsApprovalNew> dataNew = new ArrayList<>();
        int beginNum = (body.getPageNum() - 1) * body.getPageSize();
        data = investorsApprovalMapper.findApproval(body.getCheckName(), body.getTime(), beginNum, body.getPageSize());
        for (InvestorsApproval approval : data) {
            InvestorsApprovalNew investorsApprovalNew = new InvestorsApprovalNew();
            investorsApprovalNew.setId(approval.getId());
            investorsApprovalNew.setApprovalUsername(approval.getApprovalUsername());
            investorsApprovalNew.setApprovalResult(approval.getApprovalResult());
            investorsApprovalNew.setCompany(approval.getCompany());
            investorsApprovalNew.setCompanyDuties(approval.getCompanyDuties());
            investorsApprovalNew.setDescription(approval.getDescription());
            investorsApprovalNew.setInvestorsType(approval.getInvestorsType());
            investorsApprovalNew.setLeadership(approval.getLeadership());
            investorsApprovalNew.setSupplementaryExplanation(approval.getSupplementaryExplanation());
            investorsApprovalNew.setUserid(approval.getUserid());
            investorsApprovalNew.setWorkCard(approval.getWorkCard());
            investorsApprovalNew.setFormId(approval.getFormId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = simpleDateFormat.format(approval.getCreateTime());
            String reviewTime = simpleDateFormat.format(approval.getReviewTime());
            investorsApprovalNew.setCreateTime(createTime);
            investorsApprovalNew.setReviewTime(reviewTime);
            investorsApprovalNew.setInstitutionId(approval.getInstitutionId());
            dataNew.add(investorsApprovalNew);
        }
        result.setStatus(200);
        result.setMessage("获取投资审核信息成功");
        result.setData(dataNew);
        return result;
    }

    /**
     * 获取投资审核信息列表（新）
     *
     * @param body
     * @return
     */
    @Override
    public CommonDto<PagingOutputDto<InvestorsApprovalOutputDto>> adminFindApprovals(InvestorsApprovalInputDto body) {
        CommonDto<PagingOutputDto<InvestorsApprovalOutputDto>> result = new CommonDto<>();
        
        PagingOutputDto<InvestorsApprovalOutputDto> pod=new PagingOutputDto<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");

        /*if (body.getPageNum() == null) {
            body.setPageNum(defaultPageNum);
        }*/
        
        if (body.getCurrentPage() == null) {
            body.setCurrentPage(defaultPageNum);
        }
            
        if (body.getPageSize() == null) {
            body.setPageSize(defaultPageSize);
        }
        if (body.getApprovalTimeOrder() == null) {
            body.setApprovalTimeOrder(1);
            body.setApprovalTimeOrderDesc(1);
        }

        body.setStart((long)(body.getCurrentPage() - 1) * body.getPageSize());
        
        List<InvestorsApprovalOutputDto> list = investorsApprovalMapper.findApprovalList(body);
        if(list !=null && list.size()>0) {
        	list.forEach((e) ->{
        		if(e.getCreateTime()!=null) {
        			e.setApprovalTime( sdf.format(e.getCreateTime()) );
        		}
                
            	if( e.getApprovalResult() !=null) {
            		switch(e.getApprovalResult()) {
            		case 0: 
                        e.setAduitStatus("待审核");
                        break;
                    case 1:
                    	e.setAduitStatus("未通过认证");
                        break;
                    case 2:
                    	e.setAduitStatus("投资人认证");
                        break;
                    case 3:
                    	e.setAduitStatus("个人投资人");
                        break;
                    case 4:
                    	e.setAduitStatus("机构投资人");
                        break;
                    case 5:
                    	e.setAduitStatus("VIP投资人");
                    default:
                    	e.setAduitStatus("待审核"); 
            		}
            	}
            });
        }

        Long total = investorsApprovalMapper.findApprovalListCount(body);

        pod.setList(list);
        pod.setTotal(total);
        pod.setCurrentPage(body.getCurrentPage());
        pod.setPageSize(body.getPageSize());

        result.setMessage("success");
        result.setData(pod);
        result.setStatus(200);
        
        return result;
    }

    /**
     * 后台审核操作接口
     *
     * @param body  请求对象
     * @param appId
     * @return
     */
    @Override
    public CommonDto<String> approval(InvestorsApprovalActionDto body, Integer appId) {
        CommonDto<String> result = new CommonDto<>();
        Integer approvalId = body.getId();
        String approveResult = body.getApproveResult();
        String explanation = body.getExplanation();
        String approvalStatus = body.getApprovalStatus();
        Date now = new Date();

        if (approvalId == null) {
            result.setMessage("申请记录id不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        //更新投资审核记录表
        InvestorsApproval approval = new InvestorsApproval();
        approval.setId(approvalId);
        approval = investorsApprovalMapper.selectOne(approval);
        approval.setApprovalResult(Integer.parseInt(approveResult));
        if (explanation != null && !"".equals(explanation) ) {
            approval.setSupplementaryExplanation(explanation);
        }
        investorsApprovalMapper.updateByPrimaryKeySelective(approval);

        //获得机构id
        Integer jgid = getInvestmentInstitutionId(approvalId);
        if (jgid == -1) {
            result.setData(null);
            result.setStatus(502);
            result.setMessage("没有找到申请记录");

            return result;
        }
        
        //更新投资人信息
        int userId = approval.getUserid();
        
        Investors investors = new Investors();
        investors.setUserId(userId);
        investors = investorsMapper.selectOne(investors);

        if (investors != null) {
            investors.setApprovalStatus(Integer.parseInt(approvalStatus));
            investors.setInvestmentInstitutionsId(jgid);

            switch (Integer.parseInt(approveResult)) {
                case 3:
                    investors.setInvestorsType(0);
                    investors.setApprovalStatus(1);
                    investors.setApprovalTime(now);
                    break;
                case 4:
                	//认证为机构投资人
                    investors.setInvestorsType(1);
                    investors.setApprovalStatus(1);
                    investors.setApprovalTime(now);
                    break;
                case 5:
                	//认证为VIP投资人
                    investors.setInvestorsType(2);
                    investors.setApprovalStatus(1);
                    investors.setApprovalTime(now);
                    break;
                default:
                    investors.setInvestorsType(null);
                    investors.setApprovalStatus(0);
                    investors.setApprovalTime(now);
            }

            //升级为VIP投资人
            if (investors.getInvestorsType() != null) {
                if (investors.getInvestorsType() == 2 || investors.getInvestorsType() == 1) {
                    UserToken userToken = new UserToken();
                    userToken.setUserId(userId);
                    userToken = userTokenMapper.selectOne(userToken);
                    userLevelService.upLevel(appId, userToken.getToken(), 4, null, appId);
                }
            }

            investorsMapper.updateByPrimaryKey(investors);

            //获取到申请人的投资人id
            int investorsId = investors.getId();

            //先删除已经存在的投资案例
            InvestorInvestmentCase investorInvestmentCaseForBefore = new InvestorInvestmentCase();
            investorInvestmentCaseForBefore.setInvestorId(investorsId);

            investorInvestmentCaseMapper.delete(investorInvestmentCaseForBefore);


        } else {
            Investors newInvestors = new Investors();
            newInvestors.setUserId(userId);
            newInvestors.setName(approval.getApprovalUsername());
            switch (Integer.parseInt(approveResult)) {
                case 3:
                    newInvestors.setInvestorsType(0);
                    newInvestors.setApprovalStatus(1);
                    newInvestors.setApprovalTime(now);
                    break;
                case 4:
                    newInvestors.setInvestorsType(1);
                    newInvestors.setApprovalStatus(1);
                    newInvestors.setApprovalTime(now);
                    break;
                case 5:
                    newInvestors.setInvestorsType(2);
                    newInvestors.setApprovalStatus(1);
                    newInvestors.setApprovalTime(now);
                    break;
                default:
                    newInvestors.setInvestorsType(null);
                    newInvestors.setApprovalStatus(0);
                    newInvestors.setApprovalTime(now);
            }
            //升级为VIP投资人
            if (newInvestors.getInvestorsType() != null) {
                if (newInvestors.getInvestorsType() == 2 || newInvestors.getInvestorsType() == 1) {
                    UserToken userToken = new UserToken();
                    userToken.setUserId(userId);
                    userToken = userTokenMapper.selectOne(userToken);
                    userLevelService.upLevel(appId, userToken.getToken(), 4, null, appId);
                }
            }
            newInvestors.setApprovalStatus(Integer.parseInt(approvalStatus));
            newInvestors.setCreateTime(new Date());
            newInvestors.setPosition(approval.getCompanyDuties());
            newInvestors.setYn(1);
            newInvestors.setApprovalTime(new Date());
            newInvestors.setInvestmentInstitutionsId(jgid);

            investorsMapper.insertSelective(newInvestors);

            Integer investorId = newInvestors.getId();
            String anli = approval.getInvestorsApprovalcolCase();
            if (anli != null) {
                String[] anliArray = anli.split(anli);


                //新建投资人的投资案例
                for (int i = 0; i < anliArray.length; i++) {
                    InvestorInvestmentCase investorInvestmentCase = new InvestorInvestmentCase();
                    investorInvestmentCase.setInvestorId(investorId);
                    investorInvestmentCase.setInvestmentCase(anliArray[i]);

                    investorInvestmentCaseMapper.insert(investorInvestmentCase);
                }
            }

        }


        result.setStatus(200);
        result.setMessage("审核操作成功");
        return result;
    }

    /**
     * 后台审核操作接口(新)
     * @param body 请求对象
     * @return
     */
    @Transactional
    @Override
    public CommonDto<String> adminApproval(InvestorSpecialApprovalDto body) {
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();
        if (body.getId() == null) {
            result.setStatus(502);
            result.setData(null);
            result.setMessage("申请记录id不能为空");

            return result;
        }

        if (body.getInvestorType() == null) {
            result.setStatus(502);
            result.setData(null);
            result.setMessage("审核类型不能空");

            return result;
        }
        if (StringUtils.isAnyBlank(body.getUserName(), body.getCompanyName(), body.getComanyDuties())) {
            result.setMessage("认证说明，真实姓名，公司职位，公司名称不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        // 更新投资人申请表
        InvestorsApproval investorsApproval = new InvestorsApproval();
        investorsApproval.setId(body.getId());
        investorsApproval.setSupplementaryExplanation(body.getSupplementaryExplanation());
        investorsApproval.setLeadership(body.getLeader());
        
        Integer investorAuditType = 0;
        if (body.getInvestorType() == 1) {
        	//认证为个人投资人
            investorAuditType = 4;
        } else if (body.getInvestorType() == 0) {
        	//认证为机构投资人
            investorAuditType = 3;
        }
        investorsApproval.setApprovalResult(investorAuditType);
        //设置审核时间
        investorsApproval.setReviewTime(now);
        investorsApprovalMapper.updateByPrimaryKeySelective(investorsApproval);

        // 创建投资人表
        //获得机构id
        Integer jgid = getInvestmentInstitutionId(body.getId());
        if (jgid == -1) {
            result.setData(null);
            result.setStatus(502);
            result.setMessage("没有找到申请记录");

            return result;
        }

        Integer userId = body.getUserId();
        if (userId == null) {
            result.setMessage("用户id不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        Investors investorsForInsert = new Investors();
        investorsForInsert.setUserId(userId);

        investorsForInsert.setInvestorsType(body.getInvestorType());
        investorsForInsert.setName(body.getUserName());
        investorsForInsert.setPosition(body.getComanyDuties());
        investorsForInsert.setInvestmentInstitutionsId(jgid);
        investorsForInsert.setLeaderYn(body.getLeader());
        
        investorsForInsert.setApprovalStatus(1);
        investorsForInsert.setApprovalTime(now);
        investorsForInsert.setYn(1);
        
        Investors investors = new Investors();
        investors.setUserId(userId);
        investors = investorsMapper.selectOne(investors);
        
        if (investors != null) {
            investorsForInsert.setId(investors.getId());
            investorsForInsert.setUpdateTime(now);
            investorsMapper.updateByPrimaryKeySelective(investorsForInsert);
        } else {
            investorsForInsert.setCreateTime(now);
            investorsMapper.insertSelective(investorsForInsert);
        }

        // 发模板消息

        String formId = investorsApprovalMapper.selectByPrimaryKey(body.getId()).getFormId();
        Integer status = 0;
        if (body.getInvestorType() == 0) {
            status = 3;
        } else if (body.getInvestorType() == 1) {
            status = 4;
        }
        if (status > 0 && formId != null) {
            sendTemplate(userId, status, formId);
        }

        //更新用户信息
        Users users = new Users();
        users.setId(userId);
        users.setActualName(body.getUserName());
        users.setCompanyName(body.getCompanyName());
        users.setCompanyDuties(body.getComanyDuties());

        usersMapper.updateByPrimaryKeySelective(users);

        // 升级会员等级
        Example ulrExample = new Example(UserLevelRelation.class);
        // VIP投资人
        ulrExample.and().andEqualTo("userId", userId).andEqualTo("levelId", 4);
        List<UserLevelRelation> userLevelRelationListHistory = userLevelRelationMapper.selectByExample(ulrExample);
        
        if (userLevelRelationListHistory.size() == 0){
        	//审核为个人投资人时
            if (body.getInvestorType() == 1) {
            	//升级为VIP投资人
                CommonDto<Boolean> resulta = specialUpUserlevel(userId, 4);
                
                if (resulta.getStatus() != 200) {
                    result.setMessage(resulta.getMessage());
                    result.setStatus(502);
                    result.setData(null);

                    return result;
                }
            }
        }

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }


    /**
     * 根据提交审核信息获取机构id,私有方法
     *
     * @param approvalId
     * @return
     */
    private Integer getInvestmentInstitutionId(Integer approvalId) {
        Integer result = -1;
        Date now = new Date();
        InvestorsApproval investorsApproval = investorsApprovalMapper.selectByPrimaryKey(approvalId);

        //判断提交记录是否存在
        if (investorsApproval == null) {
            return result;
        } else {
            //拿到主体表的信息
            Example sExample = new Example(Subject.class);
            sExample.and().andEqualTo("shortName", investorsApproval.getCompany());
            
            List<Subject> subjectList = subjectMapper.selectByExample(sExample);
            if (subjectList.size() < 1) {
                //没有主体表信息的时候创建
                //先创建机构信息
                InvestmentInstitutions e = new InvestmentInstitutions();
                e.setType(0);
                e.setCreateTime(new Date());
                e.setShortName(investorsApproval.getCompany());

                investmentInstitutionsMapper.insertSelective(e);

                Integer jgid = e.getId();

                //创建主体表
                Subject subjectForInsert = new Subject();
                subjectForInsert.setSourceid(jgid);
                //简称和全称暂时设置为一致，因为只是存在一个简称的字符串
                subjectForInsert.setFullName(investorsApproval.getCompany());
                subjectForInsert.setShortName(investorsApproval.getCompany());

                subjectMapper.insertSelective(subjectForInsert);

                Integer ztbid = subjectForInsert.getId();
                //创建主体关系表
                SubjectTypeRelational subjectTypeRelational = new SubjectTypeRelational();
                subjectTypeRelational.setSubjectId(ztbid);
                //设置主体的类型为机构
                subjectTypeRelational.setSubjectTypeId(2);

                subjectTypeRelationalMapper.insertSelective(subjectTypeRelational);

                return jgid;

            } else {
            	//TODO 假设简称都是唯一的？？
                Subject subject = subjectList.get(0);
                //如果主体表的信息存在，判断主体类型
                Example subjectExample = new Example(SubjectTypeRelational.class);
                subjectExample.and().andEqualTo("subjectId", subject.getId()).andEqualTo("subjectTypeId", 2);
                
                List<SubjectTypeRelational> subjectTypeRelationalList = subjectTypeRelationalMapper.selectByExample(subjectExample);
                //如果主体的类型是机构，则拿到机构id返回
                if (subjectTypeRelationalList.size() > 0) {
                    return subject.getSourceid();
                } else {
                    //如果主体类型不是机构，或者没有那么创建机构信息表，并创建一个关系表；
                    //创建主体类型关系表
                    SubjectTypeRelational subjectTypeRelational = new SubjectTypeRelational();
                    subjectTypeRelational.setSubjectTypeId(2);
                    subjectTypeRelational.setSubjectId(subject.getId());

                    subjectTypeRelationalMapper.insertSelective(subjectTypeRelational);

                    //创建机构表信息
                    InvestmentInstitutions e = new InvestmentInstitutions();
                    
                    e.setShortName(subject.getShortName());
                    e.setCreateTime(now);
                    
                    e.setType(0);
                    e.setApprovalStatus(1);
                    e.setApprovalTime(now);
                    e.setYn(1);
                    
                    e.setKenelCase(subject.getSummary());
                    e.setLogo(subject.getPicture());

                    investmentInstitutionsMapper.insertSelective(e);

                    Integer jgid = e.getId();

                    return jgid;

                }
            }

        }
        //return result;
    }

    /**
     * 获取工作名片
     *
     * @param approvalId 投资审核记录ID
     * @return
     */
    @Override
    public CommonDto<String> getWorkcard(String approvalId) {
        CommonDto<String> result = new CommonDto<>();
        
        if (approvalId == null || "".equals(approvalId)) {
            result.setStatus(301);
            result.setMessage("无效参数");
            return result;
        }
        InvestorsApproval investorsApproval=investorsApprovalMapper.selectByPrimaryKey(Integer.parseInt(approvalId));
        
        if (investorsApproval != null) {
        	result.setStatus(200);
            result.setMessage("查询工作名片成功");
            result.setData(investorsApproval.getWorkCard());
            return result;
        } else {
            result.setStatus(302);
            result.setMessage("未找到该审核记录");
            return result;
        }
    }


    @Override
    public CommonDto<String> sendTemplate(Integer userId, Integer status, String formId) {
        CommonDto<String> result = new CommonDto<>();
        UsersWeixin userswx = new UsersWeixin();
        userswx.setUserId(userId);

        String kaitou = "";
        String name = "";
        String xiaoxi = "";
        String type = null;
        if (status == 0) {
            result.setData(null);
            result.setMessage("传入类型错误");
            result.setStatus(50001);

            return result;
        }

        if (status == 1 || status == 2) {
            kaitou = "抱歉！";
        } else {
            kaitou = "恭喜成为";
        }

        switch (status) {
            case 1:

                name = "您的投资人认证未通过审核";
                break;
            case 2:
                name = "您已被取消投资人资格";
                break;
            case 3:
                name = "个人投资人";
                type = "";
                break;
            case 4:
                name = "机构投资人";
                type = "";
                break;
            case 5:
                name = "VIP投资人";
                type = "";
                break;
        }

        xiaoxi = kaitou + name;

        String openId = "";
        List<UsersWeixin> usersWeixins = usersWeixinMapper.select(userswx);
        if (usersWeixins.size() > 0) {
            openId = usersWeixins.get(0).getOpenid();
        } else {
            result.setData(null);
            result.setMessage("没有找到用户的openId信息");
            result.setStatus(50001);

            return result;
        }


        Users users = usersMapper.selectByPrimaryKey(userId);
        String desc = users.getActualName() + " " + users.getCompanyName() + " " + users.getCompanyDuties();


        try {
            List<WxMaTemplateMessage.Data> datas = new ArrayList<>();
            try {
                if (status == 1 || status == 2) {//认证失败
                    datas.add(new WxMaTemplateMessage.Data("keyword1", name));
//					datas.add(new WxMaTemplateMessage.Data("keyword2",xiaoxi,"#EA4343"));
                    this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.builder().templateId("RcjdkVcWR9K3Jmfz2HVbMKKLoVHhXEJkpz42Lgr6t6E").formId(formId).toUser(openId).data(datas).build());
                }

                if (status == 3 || status == 4 || status == 5) { //认证成功
                    datas.add(new WxMaTemplateMessage.Data("keyword1", name));
                    datas.add(new WxMaTemplateMessage.Data("keyword2", xiaoxi, "#EA4343"));
                    datas.add(new WxMaTemplateMessage.Data("keyword3", desc));
                    this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.builder().templateId("IQL59_p78hezrN9Oz6UAStwSyFk8ZbLgVPaPqEi1KyA").formId(formId).toUser(openId).data(datas).page("pages/boot/boot").build());
                }
            } catch (WxErrorException e) {
                e.printStackTrace();
                result.setStatus(50001);
                result.setMessage("该formid已经被使用，或者无效");
                result.setData(null);

                return result;
            }
            //wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.newBuilder().toUser(openId).data().build());
//			wxService.getMsgService().sendKefuMsg(message);
            result.setStatus(200);
            result.setMessage("发送成功");
            result.setData(null);

        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(), e);
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);

        }
        return result;
    }

    /**
     * 用户列表页审核用户成为投资人的接口
     *
     * @param userId       用户id
     * @param status       审核状态
     * @param userName     用户名
     * @param companyName  公司名称
     * @param comanyDuties 公司职位
     * @param appId
     * @return
     */
    @Override
    public CommonDto<String> specialApproval(Integer userId, Integer status, String userName, String companyName, String comanyDuties, Integer appId) {
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (userId == null) {
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户id不能为空");

            return result;
        }

        if (status == null) {
            result.setData(null);
            result.setStatus(502);
            result.setMessage("审核状态不能为空");

            return result;
        }

        if (userName == null) {
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户真实姓名不能为空");

            return result;
        }

        if (companyName == null) {
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户所在公司不能为空");

            return result;
        }

        if (comanyDuties == null) {
            result.setData(null);
            result.setStatus(502);
            result.setMessage("用户公司职位不能为空");

            return result;
        }
        //判断好状态
        Integer approvalStatus = -1;

        switch (status) {
            case 1:
                approvalStatus = 0;
                break;
            case 2:
                approvalStatus = 0;
                break;
            default:
                approvalStatus = 1;
        }

        Integer approvalType = -1;
        switch (status) {
            case 3:
                approvalType = 0;
                break;
            case 4:
                approvalType = 1;
                break;
            case 5:
                approvalType = 2;
                break;
            default:
                approvalType = null;
        }
        //先查用户投资人信息是否存在
        Investors investors = new Investors();
        investors.setUserId(userId);

        Investors investorsForSearch = investorsMapper.selectOne(investors);
        if (investorsForSearch != null) {
            //获取机构id
            Integer jgid = getInstitutionIdByCompanyName(companyName);
            if (jgid == -1) {
                result.setMessage("获取机构id时出错");
                result.setStatus(502);
                result.setData(null);

                return result;
            }

            //存在的时候获取投资人id更新用户身份
            Integer tzrid = investorsForSearch.getId();
            investors.setYn(1);
            investors.setPosition(comanyDuties);
            investors.setName(userName);
            investors.setApprovalTime(now);
            investors.setApprovalStatus(approvalStatus);
            investors.setInvestorsType(approvalType);
            investors.setInvestmentInstitutionsId(jgid);

            investorsMapper.updateByPrimaryKeySelective(investors);

            //升级为VIP投资人
            if (investors.getInvestorsType() != null) {
                if (investors.getInvestorsType() == 2 || investors.getInvestorsType() == 1) {
                    UserToken userToken = new UserToken();
                    userToken.setUserId(userId);
                    userToken = userTokenMapper.selectOne(userToken);
                    userLevelService.upLevel(appId, userToken.getToken(), 4, "VIP_Investor", appId);
                }
            }

            //更新用户表的信息
            Users users = new Users();
            users.setActualName(userName);
            users.setCompanyName(companyName);
            users.setCompanyDuties(comanyDuties);
            users.setId(userId);

            usersMapper.updateByPrimaryKeySelective(users);
        } else {

            //获取用户机构id
            Integer jgid = getInstitutionIdByCompanyName(companyName);

            //不存在的时候新建投资人信息
            Investors investorsForInsert = new Investors();

            investorsForInsert.setUserId(userId);
            investorsForInsert.setApprovalStatus(approvalStatus);
            investorsForInsert.setCreateTime(now);
            investorsForInsert.setInvestorsType(approvalType);
            investorsForInsert.setApprovalTime(now);
            investorsForInsert.setName(userName);
            investorsForInsert.setPosition(comanyDuties);
            investorsForInsert.setYn(1);
            investorsForInsert.setInvestmentInstitutionsId(jgid);

            investorsMapper.insert(investorsForInsert);

            //升级为VIP投资人
            if (investorsForInsert.getInvestorsType() == 2 || investorsForInsert.getInvestorsType() == 1) {
                UserToken userToken = new UserToken();
                userToken.setUserId(userId);
                userToken = userTokenMapper.selectOne(userToken);
                userLevelService.upLevel(appId, userToken.getToken(), 4, "VIP_Investor", appId);
            }

            //更新用户表的信息
            Users users = new Users();
            users.setActualName(userName);
            users.setCompanyName(companyName);
            users.setCompanyDuties(comanyDuties);
            users.setId(userId);

            usersMapper.updateByPrimaryKeySelective(users);
        }

        //设置投资人认证为已审核
        setInvestmentApprovalStatus(userId, status, "");

        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");
        return result;
    }

    @Override
    public CommonDto<String> sendCommonTemplate(Integer userId, String title, String msg) {
        CommonDto<String> result = new CommonDto<>();
        UsersWeixin userswx = new UsersWeixin();
        userswx.setUserId(userId);


        String openId = "";
        List<UsersWeixin> usersWeixins = usersWeixinMapper.select(userswx);
        if (usersWeixins.size() > 0) {
            openId = usersWeixins.get(0).getOpenid();
        } else {
            result.setData(null);
            result.setMessage("没有找到用户的openId信息");
            result.setStatus(50001);

            return result;
        }


        Users users = usersMapper.selectByPrimaryKey(userId);
        String desc = users.getActualName() + " " + users.getCompanyName() + " " + users.getCompanyDuties();


        try {
            List<WxMaTemplateMessage.Data> datas = new ArrayList<>();
            try {
                // 发模板消息
                CommonDto<String> resultResult = userInfoService.getUserFormid(userId);
                if (resultResult.getStatus() == 200) {
                    Integer status = 0;

                    datas.add(new WxMaTemplateMessage.Data("keyword1", title));
                    datas.add(new WxMaTemplateMessage.Data("keyword2", msg, "#EA4343"));
                    datas.add(new WxMaTemplateMessage.Data("keyword3", desc));
                    this.wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.builder().templateId("IQL59_p78hezrN9Oz6UAStwSyFk8ZbLgVPaPqEi1KyA").formId(resultResult.getData()).toUser(openId).data(datas).page("pages/boot/boot").build());
                    userInfoService.setUserFormid(resultResult.getData());
                }
            } catch (WxErrorException e) {
                e.printStackTrace();
                result.setStatus(50001);
                result.setMessage("该formid已经被使用，或者无效");
                result.setData(null);

                return result;
            }
            //wxService.getMsgService().sendTemplateMsg(WxMaTemplateMessage.newBuilder().toUser(openId).data().build());
//			wxService.getMsgService().sendKefuMsg(message);
            result.setStatus(200);
            result.setMessage("发送成功");
            result.setData(null);

        } catch (Exception e) {
            this.LOGGER.error(e.getMessage(), e);
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);

        }
        return result;
    }

    /**
     * 设置最新一条投资记录为已审核的私有方法
     *
     * @param userId 用户id
     * @param status 审核状态
     * @return
     */
    private CommonDto<String> setInvestmentApprovalStatus(Integer userId, Integer status, String description) {
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        //先找到最近的一条投资人认证记录
        Example iaExample = new Example(InvestorsApproval.class);
        iaExample.and().andEqualTo("userid", userId);
        iaExample.setOrderByClause("create_time desc");

        List<InvestorsApproval> investorsApprovalList = investorsApprovalMapper.selectByExample(iaExample);
        if (investorsApprovalList.size() > 0) {
            InvestorsApproval investorsApprovalForUpdate = new InvestorsApproval();
            investorsApprovalForUpdate.setId(investorsApprovalList.get(0).getId());
            if (description != null && description != "") {
                investorsApprovalForUpdate.setDescription(description);
            }
            investorsApprovalForUpdate.setApprovalResult(status);
            investorsApprovalForUpdate.setReviewTime(now);

            investorsApprovalMapper.updateByPrimaryKeySelective(investorsApprovalForUpdate);
        }

        result.setData(null);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    /**
     * 根据公司名称获取机构id的私有方法
     *
     * @param companyName 公司名称
     * @return
     */
    private Integer getInstitutionIdByCompanyName(String companyName) {
        Integer result = -1;

        Date now = new Date();
        //拿到主体表的信息
        Example sExample = new Example(Subject.class);
        sExample.and().andEqualTo("shortName", companyName);
        List<Subject> subjectList = subjectMapper.selectByExample(sExample);
        
        if (subjectList.size() < 1) {
            //没有主体表信息的时候创建
            //先创建机构信息
            InvestmentInstitutions e = new InvestmentInstitutions();
            e.setType(0);
            e.setCreateTime(now);
            e.setShortName(companyName);
            investmentInstitutionsMapper.insertSelective(e);
            Integer jgid = e.getId();

            //创建主体表
            Subject subjectForInsert = new Subject();
            subjectForInsert.setSourceid(jgid);
            subjectForInsert.setFullName(companyName);
            subjectForInsert.setShortName(companyName);
            subjectMapper.insertSelective(subjectForInsert);

            //创建主体关系表
            Integer ztbid = subjectForInsert.getId();
            
            SubjectTypeRelational subjectTypeRelational = new SubjectTypeRelational();
            subjectTypeRelational.setSubjectId(ztbid);
            subjectTypeRelational.setSubjectTypeId(2);

            subjectTypeRelationalMapper.insertSelective(subjectTypeRelational);

            return jgid;

        } else {
            Subject subject = subjectList.get(0);
            //如果主体表的信息存在，判断主体类型
            Example subjectExample = new Example(SubjectTypeRelational.class);
            subjectExample.and().andEqualTo("subjectId", subject.getId()).andEqualTo("subjectTypeId", 2);
            
            List<SubjectTypeRelational> subjectTypeRelationalList = subjectTypeRelationalMapper.selectByExample(subjectExample);
            if (subjectTypeRelationalList.size() > 0) {
                //如果主体的类型是机构，则拿到机构id返回
                return subject.getSourceid();
            } else {
                //如果主体类型不是机构，或者没有那么创建机构信息表，并创建一个关系表；
                //创建主体类型关系表
                SubjectTypeRelational subjectTypeRelational = new SubjectTypeRelational();
                subjectTypeRelational.setSubjectTypeId(2);
                subjectTypeRelational.setSubjectId(subject.getId());
                subjectTypeRelationalMapper.insertSelective(subjectTypeRelational);

                //创建机构表信息
                InvestmentInstitutions e = new InvestmentInstitutions();
                
                e.setShortName(subject.getShortName());
                e.setKenelCase(subject.getSummary());
                e.setLogo(subject.getPicture());
                
                e.setCreateTime(now);
                e.setType(0);
                e.setApprovalStatus(1);
                e.setApprovalTime(now);
                e.setYn(1);

                investmentInstitutionsMapper.insertSelective(e);

                Integer jgid = e.getId();

                return jgid;

            }
        }
    }

    /**
     * 特殊审核用户信息的接口
     *
     * @param body
     * @return
     */
    @Transactional
    public CommonDto<String> adimSpecialApproval(InvestorSpecialApprovalDto body) {
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (body.getUserId() == null) {
            result.setStatus(502);
            result.setMessage("用户id不能为空");
            result.setData(null);

            return result;
        }

        if (StringUtils.isEmpty(body.getCompanyName())) {
            result.setMessage("用户公司名称不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }
        //获取机构id
        Integer jgid = getInstitutionIdByCompanyName(body.getCompanyName());
        if (jgid == -1) {
            result.setMessage("获取机构id时出错");
            result.setStatus(502);
            result.setData(null);

            return result;
        }


        Integer userId = body.getUserId();
        if (body.getInvestorType() != null) {
            if (body.getInvestorType() < 2) {
                //查投资人信息是否存在
                Investors investors = new Investors();
                investors.setUserId(userId);

                Investors investorsForSearch = investorsMapper.selectOne(investors);

                //更新or创建投资人信息
                Investors investorsForInsert = new Investors();

                investorsForInsert.setUserId(userId);
                investorsForInsert.setApprovalStatus(1);

                investorsForInsert.setInvestorsType(body.getInvestorType());
                investorsForInsert.setApprovalTime(now);
                investorsForInsert.setName(body.getUserName());
                investorsForInsert.setPosition(body.getComanyDuties());
                investorsForInsert.setYn(1);
                investorsForInsert.setInvestmentInstitutionsId(jgid);
                if (investorsForSearch != null) {
                    investorsForInsert.setId(investorsForSearch.getId());

                    investorsMapper.updateByPrimaryKeySelective(investorsForInsert);
                } else {
                    investorsForInsert.setCreateTime(now);

                    investorsMapper.insertSelective(investorsForInsert);
                }
            }
        }

        //更新用户表的信息
        Users users = new Users();
        users.setActualName(body.getUserName());
        users.setCompanyName(body.getCompanyName());
        users.setCompanyDuties(body.getComanyDuties());
        users.setId(userId);

        usersMapper.updateByPrimaryKeySelective(users);

        //设置投资人认证为已审核
        if (body.getInvestorType() != null) {
            Integer status = 0;
            if (body.getInvestorType() == 0) {
                status = 3;
            } else if (body.getInvestorType() == 1) {
                status = 4;
            }
            if (body.getSupplementaryExplanation() == null) {
                body.setSupplementaryExplanation("");
            }

            setInvestmentApprovalStatus(userId, status, body.getSupplementaryExplanation());
        }
        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }

    /**
     * 特殊用户等级提升接口
     *
     * @param userLevelId
     * @param userId
     * @return
     */
    @Transactional
    public CommonDto<String> updateSpecailLevel(Integer userLevelId, Integer userId) {
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (userLevelId == null) {
            result.setMessage("用户等级id不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }
        if (userId == null) {
            result.setStatus(502);
            result.setData(null);
            result.setMessage("用户id不能为空");

            return result;
        }

        //找到原来的数据并将其设置成无效的
        Example ulrExample = new Example(UserLevelRelation.class);
        ulrExample.and().andEqualTo("userId", userId).andEqualTo("yn", 1);

        List<UserLevelRelation> userLevelRelationList = userLevelRelationMapper.selectByExample(ulrExample);
        if (userLevelRelationList.size() > 0) {
            for (UserLevelRelation ul : userLevelRelationList) {
                ul.setYn(0);
                userLevelRelationMapper.updateByPrimaryKeySelective(ul);
            }
        }
        //计算失效时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(DateTime.now().toDate());
        calendar.add(Calendar.DAY_OF_YEAR, 365);
        Date end = calendar.getTime();

        //新建一条用户等级记录
        UserLevelRelation userLevelRelation = new UserLevelRelation();
        userLevelRelation.setUserId(userId);
        userLevelRelation.setLevelId(userLevelId);
        userLevelRelation.setBeginTime(now);
        userLevelRelation.setCreateTime(now);
        userLevelRelation.setEndTime(end);
        userLevelRelation.setStatus(1);
        userLevelRelation.setYn(1);

        userLevelRelationMapper.insertSelective(userLevelRelation);

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

    /**
     * 特殊的给用户升级接口
     * @param userId
     * @param levelStage
     * @return
     */
    @Transactional
    public CommonDto<Boolean> specialUpUserlevel(Integer userId, Integer levelStage) {
        CommonDto<Boolean> result = new CommonDto<>();
        
        if (userId == null) {
            result.setMessage("用户id不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (levelStage == null) {
            result.setMessage("升级等级不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }
        
        Date now = new Date();
        //计算失效时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(DateTime.now().toDate());
        calendar.add(Calendar.MONTH, 3);
        Date end = calendar.getTime();

        //获取当前用户的会员等级
        Example ulExample = new Example(UserLevelRelation.class);
        ulExample.and().andEqualTo("userId", userId).andEqualTo("yn", 1);
        ulExample.setOrderByClause("end_time desc");

        List<UserLevelRelation> userLevelRelationList = userLevelRelationMapper.selectByExample(ulExample);
        if (userLevelRelationList.size() > 0) {
            // 修改原等级为无效
            Integer userLevelRecently = userLevelRelationList.get(0).getLevelId();

            if (userLevelRecently > levelStage) {
                result.setMessage("当前已经有更高等级的会员了");
                result.setStatus(200);
                result.setData(null);
                return result;
            } else {
                for (UserLevelRelation ulr : userLevelRelationList) {
                    ulr.setYn(0);
                    userLevelRelationMapper.updateByPrimaryKeySelective(ulr);
                }

                UserLevelRelation userLevelRelation = new UserLevelRelation();
                userLevelRelation.setUserId(userId);
                userLevelRelation.setCreateTime(now);
                userLevelRelation.setBeginTime(now);
                userLevelRelation.setEndTime(end);
                userLevelRelation.setLevelId(levelStage);
                
                userLevelRelation.setYn(1);
                userLevelRelation.setStatus(1);

                userLevelRelationMapper.insertSelective(userLevelRelation);
            }
        } else {

            UserLevelRelation userLevelRelation = new UserLevelRelation();
            userLevelRelation.setUserId(userId);
            userLevelRelation.setCreateTime(now);
            userLevelRelation.setBeginTime(now);
            userLevelRelation.setEndTime(end);
            userLevelRelation.setLevelId(levelStage);
            
            userLevelRelation.setYn(1);
            userLevelRelation.setStatus(1);

            userLevelRelationMapper.insertSelective(userLevelRelation);

        }

        result.setMessage("success");
        result.setData(null);
        result.setStatus(200);

        return result;
    }

    /**
     * 审核创始人的逻辑
     *
     * @param body
     * @return
     */
    @Transactional
    public CommonDto<String> founderAduitRecord(InvestorSpecialApprovalDto body, Integer appid) {
        CommonDto<String> result = new CommonDto<>();
        Date now = new Date();

        if (null == body.getUserId()) {
            result.setMessage("用户id不能为空");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        if (body.getInvestorType() == null && body.getUserLevel() == null) {
            result.setMessage("审核状态不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        if (StringUtils.isAnyBlank(body.getComanyDuties(), body.getCompanyName(), body.getUserName())) {
            result.setMessage("用户姓名，公司职务，公司名称不能为空");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        Founders founders = new Founders();
        founders.setUserId(body.getUserId());
        founders.setName(body.getUserName());
        founders.setPosition(body.getComanyDuties());
        founders.setApprovalStatus(2);
        founders.setApprovalTime(now);
        founders.setYn(1);

        Example founderExample = new Example(Founders.class);
        founderExample.and().andEqualTo("userId", body.getUserId());

        Integer founderId = 1;

        List<Founders> foundersList = foundersMapper.selectByExample(founderExample);
        if (foundersList.size() > 0) {
            founders.setId(foundersList.get(0).getId());
            foundersMapper.updateByPrimaryKeySelective(founders);

            founderId = foundersList.get(0).getId();
        } else {
            foundersMapper.insertSelective(founders);

            founderId = founders.getId();
        }


        //创建审核记录表信息
        FounderAduitRecord founderAduitRecord = new FounderAduitRecord();
        founderAduitRecord.setAduitStatus(2);
        founderAduitRecord.setAduitTime(now);
        founderAduitRecord.setAppid(appid);
        founderAduitRecord.setDiscription(body.getSupplementaryExplanation());
        founderAduitRecord.setFounderId(founderId);
        founderAduitRecord.setAppid(appid);

        founderAduitRecordMapper.insertSelective(founderAduitRecord);

        result.setStatus(200);
        result.setData(null);
        result.setMessage("success");

        return result;
    }

}
