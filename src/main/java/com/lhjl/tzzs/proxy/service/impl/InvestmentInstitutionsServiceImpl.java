package com.lhjl.tzzs.proxy.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.ImageHandlerDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionComplexOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionSearchOutputDto;
import com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto2;
import com.lhjl.tzzs.proxy.dto.ProjectRatingDto;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionAdminMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsAddressMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsAddressPartMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsLabelMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsSegmentationMapper;
import com.lhjl.tzzs.proxy.mapper.InvestmentInstitutionsStageMapper;
import com.lhjl.tzzs.proxy.mapper.MetaProjectStageMapper;
import com.lhjl.tzzs.proxy.mapper.MetaSegmentationMapper;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutions;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddress;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsAddressPart;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsLabel;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsSegmentation;
import com.lhjl.tzzs.proxy.model.InvestmentInstitutionsStage;
import com.lhjl.tzzs.proxy.model.MetaProjectStage;
import com.lhjl.tzzs.proxy.model.MetaSegmentation;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService;
import com.lhjl.tzzs.proxy.service.bluewave.UserLoginService;
import com.lhjl.tzzs.proxy.utils.MD5Util;
import com.lhjl.tzzs.proxy.utils.TypeConvertUtil;

import me.chanjar.weixin.common.exception.WxErrorException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

@Service
public class InvestmentInstitutionsServiceImpl extends GenericService implements InvestmentInstitutionsService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentInstitutionsServiceImpl.class);

    @Autowired
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Autowired
    private InvestmentInstitutionsSegmentationMapper investmentInstitutionsSegmentationMapper;

    @Autowired
    private InvestmentInstitutionsStageMapper investmentInstitutionsStageMapper;

    @Autowired
    private InvestmentInstitutionsAddressMapper investmentInstitutionsAddressMapper;
    
    @Autowired
    private WxMaService qrcodeService;
    
    @Autowired
    private MetaProjectStageMapper metaProjectStageMapper;
    
    @Autowired
    private MetaSegmentationMapper metaSegmentationMapper;
    
    @Autowired
    private InvestmentInstitutionsAddressPartMapper investmentInstitutionsAddressPartMapper;
    
    @Autowired
    private InvestmentInstitutionAdminMapper investmentInstitutionAdminMapper;
    
    @Autowired
    private UserLoginService userLoginService;
    
    @Autowired
    private InvestmentInstitutionsLabelMapper investmentInstitutionsLabelMapper;
    
    
    
    
    @Override
    public CommonDto<InvestmentInstitutionComplexOutputDto> getInvestmentInstitutionsComlexInfo(Map<String,Integer> body){
        CommonDto<InvestmentInstitutionComplexOutputDto> result =  new CommonDto<>();

        if (body.get("investorInstitutionId") == null){
            result.setStatus(50001);
            result.setMessage("机构id不能为空");
            result.setData(null);

            log.info("根据id获取机构信息的接口场景");
            log.info("机构id不能为空");

            return result;
        }

        InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectByPrimaryKey(body.get("investorInstitutionId"));
        if (investmentInstitutions == null){
            result.setMessage("当前机构id没有找到对应的机构");
            result.setStatus(50001);
            result.setData(null);

            log.info("根据id获取机构信息的接口场景");
            log.info("当前机构id没有找到对应的机构");

            return result;
        }else {
            InvestmentInstitutionComplexOutputDto investmentInstitutionComplexOutputDto = new InvestmentInstitutionComplexOutputDto();
            if (StringUtils.isEmpty(investmentInstitutions.getKenelCase())){

                investmentInstitutionComplexOutputDto.setInvestmentInstitutionDesc(investmentInstitutions.getComment());
            }else {
                investmentInstitutionComplexOutputDto.setInvestmentInstitutionDesc(investmentInstitutions.getKenelCase());
            }
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionLogo(investmentInstitutions.getLogo());
            investmentInstitutionComplexOutputDto.setInvestmentInstitutionName(investmentInstitutions.getShortName());

            result.setData(investmentInstitutionComplexOutputDto);
            result.setMessage("success");
            result.setStatus(200);

        }



        return result;
    }

    /**
     * 根据输入词搜索机构信息接口
     * @param inputsWords 输入的词
     * @return
     */
    @Override
    public CommonDto<List<InvestmentInstitutionSearchOutputDto>> getInstitutionIntelligentSearch(String inputsWords,Integer pageSize){
        CommonDto<List<InvestmentInstitutionSearchOutputDto>> result = new CommonDto<>();
        List<InvestmentInstitutionSearchOutputDto> list = new ArrayList<>();

        if (inputsWords == null || "undefined".equals(inputsWords)){
           inputsWords = "";
        }
        if (pageSize == null || pageSize <= 0){
            pageSize = 5;
        }


        List<InvestmentInstitutions> investmentInstitutionsList = investmentInstitutionsMapper.findeInvestmentByShortName(inputsWords,0,pageSize);
        if (investmentInstitutionsList.size() > 0){
            for(InvestmentInstitutions ii:investmentInstitutionsList){
                InvestmentInstitutionSearchOutputDto investmentInstitutionSearchOutputDto = new InvestmentInstitutionSearchOutputDto();
                investmentInstitutionSearchOutputDto.setInstitutionFullName(ii.getShortName());
                investmentInstitutionSearchOutputDto.setInstitutionShortName(ii.getShortName());
                investmentInstitutionSearchOutputDto.setInstitutionId(ii.getId());

                list.add(investmentInstitutionSearchOutputDto);
            }

            result.setData(list);
            result.setMessage("success");
            result.setStatus(200);
        }else {
            result.setStatus(200);
            result.setMessage("success");
            result.setData(list);
        }

        return result;
    }

    /**
     * 获取机构详情的接口
     * @param institutionId
     * @return
     */
    @Override
    public CommonDto<Map<String,Object>> findInstitutionDetails(Integer institutionId){
        CommonDto<Map<String,Object>> result  = new CommonDto<>();

        if (institutionId == null){
            result.setMessage("机构id不能为空");
            result.setStatus(502);
            result.setData(null);
            return result;
        }

        Map<String,Object> map = new HashMap<>();

        //先获取机构简介
        InvestmentInstitutions investmentInstitutions = investmentInstitutionsMapper.selectByPrimaryKey(institutionId);
        if (investmentInstitutions == null){
            result.setMessage("输入的机构id不存在，请检查");
            result.setData(null);
            result.setStatus(502);

            return result;
        }

        //获取机构的关注领域
        List<Map<String,Object>> segmentList = new ArrayList<>();
        segmentList = investmentInstitutionsSegmentationMapper.selectSegmentationCount(institutionId);
        for (Map<String,Object> m:segmentList){
            String name = "";
            if (m.get("segmentation_name") != null){
                name = (String)m.get("segmentation_name");
            }
           m.put("name",name);

            if (m.get("segmentation_logo") == null){
                m.put("segmentation_logo","http://img.idatavc.com/static/seg/jiaoyu.png");
            }
        }

        // 最近关注领域
        CommonDto<List<MetaSegmentation>> filedResult=getRencentlyFiled(institutionId);
        List<MetaSegmentation> recentlyFild = new ArrayList<>();
        if (filedResult.getStatus() == 200){
            recentlyFild = filedResult.getData();
        }

        //获取机构的关注阶段
        List<Map<String,Object>> stageList = new ArrayList<>();
        stageList = investmentInstitutionsStageMapper.findInstitutionStageCount(institutionId);

        //获取机构的城市、地址、邮箱、电话
        Example iiaExample = new Example(InvestmentInstitutionsAddress.class);
        iiaExample.and().andEqualTo("investmentInstitutionId",institutionId);

        List<Map<String,Object>> listiia = new ArrayList<>();
        List<InvestmentInstitutionsAddress> investmentInstitutionsAddressList = investmentInstitutionsAddressMapper.selectByExample(iiaExample);
        if (investmentInstitutionsAddressList.size() > 0){
            for (InvestmentInstitutionsAddress iia:investmentInstitutionsAddressList){
                Map<String,Object> mapForY = new HashMap<>();
                if (iia.getTown() == null){
                    mapForY.put("town","");
                }else {
                    mapForY.put("town",iia.getTown());
                }

                if (iia.getDetailAddress() == null){
                    mapForY.put("detailAddress","");
                }else {
                    mapForY.put("detailAddress",iia.getDetailAddress());
                }

                if (iia.getEmail() == null){
                    mapForY.put("email","");
                }else {
                    mapForY.put("email",iia.getEmail());
                }

                if (iia.getPhoneCountryCode() == null && iia.getPhoneDistrictCode() == null && iia.getPhoneNumber() ==null){
                    mapForY.put("phoneNumber","");
                }else {
                    String phonenumber = "";
                    if (iia.getPhoneCountryCode() != null){
                        phonenumber = phonenumber + iia.getPhoneCountryCode() + "-";
                    }
                    if (iia.getPhoneDistrictCode() != null){
                        phonenumber = phonenumber + iia.getPhoneDistrictCode() + "-";
                    }
                    if (iia.getPhoneNumber() != null){
                        phonenumber = phonenumber + iia.getPhoneNumber();
                    }

                    mapForY.put("phoneNumber",phonenumber);
                }

                listiia.add(mapForY);
            }
        }

        //组装返回数据
        if (investmentInstitutions.getKenelCase() != null){

            map.put("institutionDesc",investmentInstitutions.getKenelCase());
        }else {
            String kenelCase= "";
            if (investmentInstitutions.getComment() != null ){
                kenelCase = investmentInstitutions.getComment();
            }
            map.put("institutionDesc", kenelCase);
        }
        map.put("institutionSegmentation",segmentList);
        map.put("institutionStage",stageList);
        map.put("address",listiia);
        map.put("institutionLogo",investmentInstitutions.getLogo());
        map.put("institutionName",investmentInstitutions.getShortName());
        map.put("recentlyFiled",recentlyFild);

        result.setStatus(200);
        result.setData(map);
        result.setMessage("success");

        return result;
    }

    @Override
    public CommonDto<Map<String,Object>> findFliterInfo(Integer institutionId){
        CommonDto<Map<String,Object>> result = new CommonDto<>();

        Map<String,Object> map = new HashMap<>();
        //获取机构的关注领域
        List<Map<String,Object>> segmentList = new ArrayList<>();
        segmentList = investmentInstitutionsSegmentationMapper.findSegment(institutionId);
        for (Map<String,Object> m:segmentList){
            if (m.get("name") == null ){
                m.put("name","");
            }

            if (m.get("segmentation_logo") == null){
                m.put("segmentation_logo","http://img.idatavc.com/static/seg/jiaoyu.png");
            }
        }

        List<Map<String,Object>> yearList = new ArrayList<>();
        yearList = investmentInstitutionsSegmentationMapper.findYear(institutionId);
        for (int i = 0;i < yearList.size();i++){

            yearList.get(i).put("ydate",yearList.get(i).get("financing_time_year"));

        }

        map.put("segmentation",segmentList);
        map.put("years",yearList);

        result.setData(map);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }

    @Transactional
    @Override
    public InputStream imageHandler(ImageHandlerDto reqDto) {

        InvestmentInstitutions query = new InvestmentInstitutions();
        query.setShortName(reqDto.getName());
        List<InvestmentInstitutions> investmentInstitutions = investmentInstitutionsMapper.select(query);
        if (null == investmentInstitutions || investmentInstitutions.size() == 0){
            return null;
        }
        InvestmentInstitutions ii = investmentInstitutions.get(0);
        if (null == ii.getKeyWords() || ii.getKeyWords().equals("")){
            try {
                String keyWords = MD5Util.md5Encode(PinyinHelper.convertToPinyinString(reqDto.getName(), "", PinyinFormat.WITHOUT_TONE).toUpperCase(),"").substring(12,32);
                ii.setKeyWords(keyWords);
                investmentInstitutionsMapper.updateByPrimaryKey(ii);
            } catch (PinyinException e) {
                log.error(e.getLocalizedMessage(),e.fillInStackTrace());
            }
        }


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {

            File img = qrcodeService.getQrcodeService().createWxCode(reqDto.getPath()+"&jg="+ii.getKeyWords(),reqDto.getW());
//
//            File img =  qrcodeService.getQrcodeService().createWxCode().createWxCodeLimit(ii.getKeyWords()+"_"+reqDto.getActivityId(),reqDto.getPath(),reqDto.getW(),true,null);
            BufferedImage qcode = ImageIO.read(img);
            Thumbnails.of(new URL(reqDto.getTemplateUrl()).openStream()).size(750,7452).watermark(new Position() {
                @Override
                public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
                    return new Point(reqDto.getX(),reqDto.getY());
                }
            }, qcode, 1.0f).toOutputStream(os);
//            Thumbnails.of(new File("/Users/zhhu/Downloads/1.jpg")).size(750,1334).watermark(Positions.CENTER, qcode,1.0f).toFile("/Users/zhhu/Downloads/b.jpg");
//            ImageIO.write(scaledImage, "jpg", os);
//            ImageIO.write()
//            ImageUtils.pressImage(new URL(reqDto.getTemplateUrl()),img,os,750,7452,1f);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(),e.fillInStackTrace());
        } catch (WxErrorException e) {
            log.error(e.getLocalizedMessage(),e.fillInStackTrace());
        } catch (Exception e){
            log.error(e.getLocalizedMessage(),e.fillInStackTrace());
        }

        return new ByteArrayInputStream(os.toByteArray());
    }

	@Override
	public CommonDto<List<MetaProjectStage>> listInvestementStages() {
		CommonDto<List<MetaProjectStage>> result= new CommonDto<>();
		
		List<MetaProjectStage> stages = metaProjectStageMapper.findAll();
		result.setData(stages);
		result.setMessage("success");
		result.setStatus(200);
		
		return result;
	}

	@Override
	public CommonDto<List<MetaSegmentation>> listInvestementFields() {
		CommonDto<List<MetaSegmentation>> result=new CommonDto<>();
		
		List<MetaSegmentation> segs = metaSegmentationMapper.findAll();
		 result.setData(segs);
		 result.setMessage("success");
		 result.setStatus(200);
		 
		return result;
	}

	@Override
	public CommonDto<List<InvestmentInstitutionsAddressPart>> listAllAddressPartsById(Integer investmentInstitutionId) {
		CommonDto<List<InvestmentInstitutionsAddressPart>> result=new CommonDto<>();
		
		List<InvestmentInstitutionsAddressPart> addrParts = investmentInstitutionsAddressPartMapper.findAllById(investmentInstitutionId);
		result.setData(addrParts);
		result.setMessage("success");
		result.setStatus(200);
		
		return result;
	}
	/**
	 * 根据token以及appid获取机构id
	 * @param token
	 * @param appid
	 * @return
	 */
	private Integer getiiId(String token,Integer appid) {
		Integer userId = userLoginService.getUserIdByToken(token, appid);
		Integer iiId= investmentInstitutionAdminMapper.findInvestmentInstitionIdByUserId(userId);
		return iiId;
	}
	
	/* (non-Javadoc)
	 * @see com.lhjl.tzzs.proxy.service.InvestmentInstitutionsService#updataInvesInfo(java.lang.Integer, com.lhjl.tzzs.proxy.dto.InvestmentInstitutionsDto2)
	 */
	@Transactional
	@Override
	public CommonDto<Boolean> saveOrUpdate(Integer appid,InvestmentInstitutionsDto2 body) {
		
		CommonDto<Boolean> result =new CommonDto<Boolean>();
		//进行机构信息的提取
		InvestmentInstitutions ii=new InvestmentInstitutions();
		
		ii.setId(body.getId());  //取得机构id 根据此值判断进行增加或者更新
		ii.setLogo(body.getLogo());
		ii.setShortName(body.getShortName());
		ii.setFullName(body.getFullName());
		ii.setKenelCase(body.getKenelCase());
		ii.setComment(body.getComment());
		ii.setHomeUrl(body.getHomeUrl());
		ii.setTotalFundScale(body.getTotalFundScale());
		ii.setRmbFundScale(body.getRmbFundScale());
		ii.setDollarFundScale(body.getDollarFundScale());
		ii.setRmbInvestAmountMin(body.getRmbInvestAmountMin());
		ii.setRmbInvestAmountMax(body.getRmbInvestAmountMax());
		ii.setDollarInvestAmountMax(body.getDollarInvestAmountMax());
		ii.setDollarInvestAmountMin(body.getDollarInvestAmountMin());
		ii.setInvestmentIdea(body.getInvestmentIdea());
		ii.setProductRequirement(body.getProductRequirement());
		ii.setRecruitmentRequirement(body.getRecruitmentRequirement());
		ii.setCreateTime(new Date());
		
		//自定义领域信息的提取
		String[] newSegmentations = body.getNewSegmentations();
		//实现领域机构关系信息的提取
		Integer[] segmentations = body.getSegmentations();
		//实现阶段机构信息的提取
		Integer[] stages = body.getStages();
		
		
		//实现机构总部信息的提取
		InvestmentInstitutionsAddress head =new InvestmentInstitutionsAddress();
		head.setTown(body.getCity());
		head.setDetailAddress(body.getDetailAddress());
		head.setEmail(body.getEmail());
		head.setBpEmail(body.getBpEmail());
		
		//实现机构分部信息的提取
		List<InvestmentInstitutionsAddressPart> parts = body.getInvestmentInstitutionsAddressParts();

		//下面进行机构信息的增加
		if(ii.getId() == null) {
			System.err.println("<<<<<<");
			//自增长Id
			int updateLines = investmentInstitutionsMapper.insertSelective(ii);
			// investmentInstitutionsMapper.getLastInsertId();
			Integer autoGrowId = ii.getId();
			System.err.println("**********autoGrowId*********" + autoGrowId);
			
			//实现机构自定义领域数据的增加
			int diySegInserts;
			if(newSegmentations != null) {
				List<InvestmentInstitutionsLabel> iiLables =new ArrayList<>();
				for(String temp:newSegmentations) {
					InvestmentInstitutionsLabel iiLable=new InvestmentInstitutionsLabel();
					iiLable.setInvestmentInstitutionsId(autoGrowId);
					iiLable.setLabelName(temp);
					iiLables.add(iiLable);
				}
				diySegInserts = investmentInstitutionsLabelMapper.insertList(iiLables);
			}else {
				diySegInserts = investmentInstitutionsLabelMapper.insertList(null);
			}
			
			//实现领域机构关系表的增加
			List<InvestmentInstitutionsSegmentation> iiSegList=null;
			if(segmentations != null) {
				iiSegList=new ArrayList<>();
				for(Integer temp:segmentations) {
					InvestmentInstitutionsSegmentation iiSeg=new InvestmentInstitutionsSegmentation();
					iiSeg.setMetaSegmentationId(temp);
					iiSeg.setInvestmentInstitutionsId(autoGrowId);
					iiSegList.add(iiSeg);
				}
			}
			
			Boolean segsFlag=investmentInstitutionsSegmentationMapper.addBatch(iiSegList);
			
			//实现阶段机构关系表的增加
			
			List<InvestmentInstitutionsStage> iiStageList=null;
			if(stages != null) {
				iiStageList=new ArrayList<>();
				for(Integer temp:stages) {
					InvestmentInstitutionsStage iiStage=new InvestmentInstitutionsStage();
					iiStage.setMetaProjectStageId(temp);
					iiStage.setInvestmentInstitutionId(autoGrowId);
					iiStageList.add(iiStage);
				}
			}
			iiStageList.forEach((e)->{
				investmentInstitutionsStageMapper.insert(e);
			});
			
			//实现机构总部的增加
			Integer addrUpdate = investmentInstitutionsAddressMapper.insertSelective(head);
			
			//实现机构分部的增加
			List<InvestmentInstitutionsAddressPart> handleAfterParts = null;
			if(parts != null) {
				handleAfterParts = new ArrayList<>();
				for(InvestmentInstitutionsAddressPart temp:parts) {
					temp.setInvestmentInstitutionId(autoGrowId);
					handleAfterParts.add(temp);
				}
			}
			int partsInserts = investmentInstitutionsAddressPartMapper.insertList(handleAfterParts);
			
				result.setData(true);
				result.setMessage("数据增加成功");
				result.setStatus(200);
				
	}else {  //下面进行更新操作 
		int line = investmentInstitutionsMapper.updateByPrimaryKeySelective(ii);
		
		//自定义领域信息的更新
		if(newSegmentations != null) {
			List<InvestmentInstitutionsLabel> iiLables =new ArrayList<>();
			for(String temp:newSegmentations) {
				InvestmentInstitutionsLabel iiLable=new InvestmentInstitutionsLabel();
				iiLable.setInvestmentInstitutionsId(ii.getId());
				iiLable.setLabelName(temp);
				iiLables.add(iiLable);
			}
			
			InvestmentInstitutionsLabel delEntity=new InvestmentInstitutionsLabel();
			delEntity.setInvestmentInstitutionsId(ii.getId());
			investmentInstitutionsLabelMapper.delete(delEntity);
				
			//进行标签数据的重新增加
			int line2 = investmentInstitutionsLabelMapper.insertList(iiLables);
			
		}else {
		}
		
		//实现领域机构关系表的更新
		List<InvestmentInstitutionsSegmentation> iiSegList=null;
		if(segmentations != null) {
			iiSegList=new ArrayList<>();
			for(Integer temp:segmentations) {
				InvestmentInstitutionsSegmentation iiSeg=new InvestmentInstitutionsSegmentation();
				iiSeg.setMetaSegmentationId(temp);
				iiSeg.setInvestmentInstitutionsId(ii.getId());
				iiSegList.add(iiSeg);
			}
		}
		
		//实现关系的删除
		InvestmentInstitutionsSegmentation segDelEntity=new InvestmentInstitutionsSegmentation();
		segDelEntity.setInvestmentInstitutionsId(ii.getId());
		investmentInstitutionsSegmentationMapper.delete(segDelEntity);
		//进行关系的新增
		Boolean segsFlag=investmentInstitutionsSegmentationMapper.addBatch(iiSegList);
		
		//实现阶段机构关系表的更新
		List<InvestmentInstitutionsStage> iiStageList=null;
		if(stages != null) {
			iiStageList=new ArrayList<>();
			for(Integer temp:stages) {
				InvestmentInstitutionsStage iiStage=new InvestmentInstitutionsStage();
				iiStage.setMetaProjectStageId(temp);
				iiStage.setInvestmentInstitutionId(ii.getId());
				iiStageList.add(iiStage);
			}
		}
		
		//实现关系的删除
		InvestmentInstitutionsStage staDelEntity=new InvestmentInstitutionsStage();
		staDelEntity.setInvestmentInstitutionId(ii.getId());
		investmentInstitutionsStageMapper.delete(staDelEntity);
		//进行关系的新增
		Boolean stasFlag=investmentInstitutionsStageMapper.addBatch(iiStageList);
		
		//实现机构总部的更新
		InvestmentInstitutionsAddress addrUpdateEntity=new InvestmentInstitutionsAddress();
		addrUpdateEntity.setInvestmentInstitutionId(ii.getId());
		
		addrUpdateEntity =investmentInstitutionsAddressMapper.selectOne(addrUpdateEntity);
		head.setId(addrUpdateEntity.getId());
		head.setInvestmentInstitutionId(ii.getId());
		
		InvestmentInstitutionsAddress preUpdateAddr=head;
		int line3 = investmentInstitutionsAddressMapper.updateByPrimaryKey(preUpdateAddr);
		
//		机构分部信息更新
		List<InvestmentInstitutionsAddressPart> handleAfterParts = null;
		if(parts != null) {
			handleAfterParts = new ArrayList<>();
			for(InvestmentInstitutionsAddressPart temp:parts) {
				temp.setInvestmentInstitutionId(ii.getId());
				handleAfterParts.add(temp);
			}
		}
//		进行分部信息的删除
		InvestmentInstitutionsAddressPart delEntity=new InvestmentInstitutionsAddressPart();
		delEntity.setInvestmentInstitutionId(ii.getId());
		investmentInstitutionsAddressPartMapper.delete(delEntity);
		//进行分部信息的重新增加
		int linefenbu = investmentInstitutionsAddressPartMapper.insertList(handleAfterParts);
		
		result.setData(true);
		result.setMessage("数据更新成功");
		result.setStatus(200);
	}
		return result;
}
	
	@Override
	public CommonDto<InvestmentInstitutionsDto2> echoinstiinfo(Integer id, Integer appid) {
		
		CommonDto<InvestmentInstitutionsDto2> result = new CommonDto<InvestmentInstitutionsDto2>();
		InvestmentInstitutionsDto2 formBody =null;
			formBody=new InvestmentInstitutionsDto2();
			//获取机构的相关信息
			InvestmentInstitutions ii = investmentInstitutionsMapper.selectByPrimaryKey(id);
			
			//获取机构总部的地址信息
			InvestmentInstitutionsAddress headquarters=new InvestmentInstitutionsAddress();
			headquarters.setInvestmentInstitutionId(id);
			headquarters = investmentInstitutionsAddressMapper.selectOne(headquarters);
			//获取机构分部的地址信息
			InvestmentInstitutionsAddressPart part=new InvestmentInstitutionsAddressPart();
			part.setInvestmentInstitutionId(id);
			List<InvestmentInstitutionsAddressPart> parts = investmentInstitutionsAddressPartMapper.select(part);
			//获取机构的领域信息
			InvestmentInstitutionsStage stage = new  InvestmentInstitutionsStage();
			stage.setInvestmentInstitutionId(id);
			List<InvestmentInstitutionsStage> stages = investmentInstitutionsStageMapper.select(stage);
			//获取机构相关的自定义领域信息
			InvestmentInstitutionsLabel iiLable = new InvestmentInstitutionsLabel();
			iiLable.setInvestmentInstitutionsId(id);
			List<InvestmentInstitutionsLabel> iiLabels = investmentInstitutionsLabelMapper.select(iiLable);
			//获取机构的阶段信息
			InvestmentInstitutionsSegmentation investmentInstitutionsSegmentation = new InvestmentInstitutionsSegmentation();
			investmentInstitutionsSegmentation.setInvestmentInstitutionsId(id);
			List<InvestmentInstitutionsSegmentation> segmentations = investmentInstitutionsSegmentationMapper.select(investmentInstitutionsSegmentation);
			
			if(ii != null) {
				formBody.setLogo(ii.getLogo());
				formBody.setShortName(ii.getShortName());
				formBody.setFullName(ii.getFullName());
				formBody.setKenelCase(ii.getKenelCase());
				formBody.setComment(ii.getComment());
				formBody.setHomeUrl(ii.getHomeUrl());
				formBody.setTotalFundScale(ii.getTotalFundScale());
				formBody.setRmbFundScale(ii.getRmbFundScale());
				formBody.setDollarFundScale(ii.getDollarFundScale());
				formBody.setRmbInvestAmountMin(ii.getRmbInvestAmountMin());
				formBody.setRmbInvestAmountMax(ii.getRmbInvestAmountMax());
				formBody.setDollarInvestAmountMax(ii.getDollarInvestAmountMax());
				formBody.setDollarInvestAmountMin(ii.getDollarInvestAmountMin());
			}
			//设置总部的相关信息
			if(headquarters != null) {
				formBody.setCity(headquarters.getTown());
				formBody.setDetailAddress(headquarters.getDetailAddress());
				formBody.setEmail(headquarters.getEmail());
				formBody.setBpEmail(headquarters.getBpEmail());
				formBody.setLongitude(headquarters.getLongitude());
				formBody.setLatitude(headquarters.getLatitude());
			}
			//设置机构自定义领域的相关信息
			String[] arrLabels=null;
			if(iiLabels!=null) {
				List<String> labels =new ArrayList<>();
				iiLabels.forEach((e)->{
					labels.add(e.getLabelName());
				});
				
				arrLabels=new String[iiLabels.size()];
				arrLabels = labels.toArray(arrLabels);
				
				formBody.setNewSegmentations(arrLabels);
			}else {
				formBody.setNewSegmentations(arrLabels);
			}
			
			//设置相关的阶段信息(将list转换为一个Integer)
			List<Integer> staList=new ArrayList<>();
			if(stages != null && stages.size() != 0) {
				Iterator<InvestmentInstitutionsStage> ite = stages.iterator();
				while(ite.hasNext()) {
					staList.add(ite.next().getMetaProjectStageId());
				}
			}
			
			Integer[] stageArr = new Integer[staList.size()];
			stageArr = staList.toArray(stageArr);
			formBody.setStages(stageArr);
			
			//设置相关的领域信息
			List<Integer> segmList=new ArrayList<>();
			if(segmentations != null && segmentations.size() != 0) {
				Iterator<InvestmentInstitutionsSegmentation> ite = segmentations.iterator();
				while(ite.hasNext()) {
					segmList.add(ite.next().getMetaSegmentationId());
				}
			}
			
			Integer[] segmArray = new Integer[segmList.size()];
			segmArray = segmList.toArray(segmArray);
			formBody.setSegmentations(segmArray);
			
			//设置相关的地址分部信息
			formBody.setInvestmentInstitutionsAddressParts(parts);
			
			result.setData(formBody);
			result.setStatus(200);
			result.setMessage("success");
			
			return result;
	}


	private CommonDto<List<MetaSegmentation>> getRencentlyFiled(Integer institutionId){
	    CommonDto<List<MetaSegmentation>> result = new CommonDto<>();
	    List<MetaSegmentation> list = new ArrayList<>();

        List<Map<String,Object>> metaSegmentations = metaSegmentationMapper.findInstitutionTop(institutionId);
        List<Map<String,Object>> metaSegmentationList = metaSegmentationMapper.findUserFocusSegmentation(institutionId);

        List<Map<String, Object>> segmentations = new ArrayList<>();

        //初始化top9 和 机构投资人关注领域数量
        Integer num = 9;
        for (Map<String, Object> map : metaSegmentations){
            map.put("count",fetchFocusSegmentationCount(map.get("name").toString(), metaSegmentationList, num));

            num--;
            segmentations.add(map);
        }


        //合并关注领域不在机构关注top9里面的领域
        Map<String, Object> temp = null;
        for (Map<String, Object> map : metaSegmentationList){

            temp = findSegmentationName(metaSegmentations, map.get("name").toString());
            if (null == temp){
                map.put("count",map.get("cot"));
                segmentations.add(map);
            }

        }

        //数据排序
        Collections.sort(segmentations, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return Integer.valueOf(o2.get("count").toString()) - Integer.valueOf(o1.get("count").toString()) ;
            }
        });

        //整理数据返回
        if(segmentations.size()>0){
            for (Map<String, Object> m:segmentations){
                MetaSegmentation metaSegmentation = new MetaSegmentation();
                metaSegmentation.setName((String)m.get("name"));
                metaSegmentation.setId((Integer)m.get("id"));
                metaSegmentation.setSegmentationLogo((String)m.get("segmentation_logo"));

                list.add(metaSegmentation);
            }
        }


        result.setMessage("success");
        result.setStatus(200);
        result.setData(list);

	    return result;
    }

    private Map<String, Object> findSegmentationName(List<Map<String, Object>> metaSegmentations, String name) {
        Map<String , Object> temp = null;
	    for (Map<String, Object> map : metaSegmentations){
	        if (name.equals(map.get("name").toString())){
	            temp = map;
	            break;
            }
        }

	    return temp;
    }

    private Integer fetchFocusSegmentationCount(String name, List<Map<String, Object>> metaSegmentationList, Integer num) {
	    Integer count = 0;
	    for (Map<String, Object> map : metaSegmentationList){
	        if (map.get("name").toString().equals(name)){
                count = Integer.valueOf(map.get("cot").toString()) ;
                break;
            }
        }
        return count + num;
    }

	@Override
	public CommonDto<String> institutionRating(ProjectRatingDto body) {
		CommonDto<String> result =new CommonDto<String>();
		//TODO 机构评级逻辑
		this.LOGGER.info("机构评级待完善，，后台数据结构不足，，，");
		result.setData("机构评级待完善，，后台数据结构不足，，，");
		result.setMessage("success");
		result.setStatus(200);
		return result;
	}

}
