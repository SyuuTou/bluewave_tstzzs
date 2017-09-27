package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.mapper.*;
import com.lhjl.tzzs.proxy.model.*;
import com.lhjl.tzzs.proxy.service.InvestmentDataService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InvestmentDataImpl implements InvestmentDataService{
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentDataImpl.class);

    @Resource
    private InvestmentInstitutionsMapper investmentInstitutionsMapper;

    @Resource
    private FoundersMapper foundersMapper;

    @Resource
    private ProjectsMapper projectsMapper;

    @Resource
    private ProjectFinancingLogMapper projectFinancingLogMapper;

    @Resource
    private FoundersEducationMapper foundersEducationMapper;

    @Resource
    private FoundersWorkMapper foundersWorkMapper;

    @Override
    public CommonDto<String> addInvestmentData(String investment_institution_name , String project_name, String project_full_name, String summary, String field,  String city, String rounds, String amount,  String currency,  String stock_right,  Date date,  String founder_name, String founder_work, String founder_education){
        CommonDto<String> result = new CommonDto<String>();


        //参数验证
        if (StringUtil.isEmpty(investment_institution_name)){
            result.setStatus(50001);
            result.setMessage("请填写投资机构名称");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(project_name)){
            result.setStatus(50001);
            result.setMessage("请填写投资项目简称");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(project_full_name)){
            result.setStatus(50001);
            result.setMessage("请填写投资项目的工商注册全称");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(summary)){
            result.setStatus(50001);
            result.setMessage("请填写一句话介绍");
            result.setData(null);

            return result;
        }else if (summary.length()>50){
            result.setStatus(50003);
            result.setMessage("一句话介绍长度不能超过50个字符");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(field)){
            result.setStatus(50001);
            result.setMessage("请填写所属细分领域");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(city)){
            result.setStatus(50001);
            result.setMessage("请填写地域");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(rounds)){
            result.setStatus(50001);
            result.setMessage("请填写投资轮次");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(amount)){
            result.setStatus(50001);
            result.setMessage("请填写投资金额");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(currency)){
            result.setStatus(50001);
            result.setMessage("请填写投资币种");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(stock_right)){
            result.setStatus(50001);
            result.setMessage("请填写股份占比");
            result.setData(null);

            return result;
        }
        if (date == null){
            result.setStatus(50001);
            result.setMessage("请填写投资时间");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(founder_name)){
            result.setStatus(50001);
            result.setMessage("请填写创始人姓名");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(founder_work)){
            result.setStatus(50001);
            result.setMessage("请填写创始人工作背景");
            result.setData(null);

            return result;
        }
        if (StringUtil.isEmpty(founder_education)){
            result.setStatus(50001);
            result.setMessage("请填写创始人毕业院校");
            result.setData(null);

            return result;
        }

        int currency1 =Integer.parseInt(currency);

        //获取现在时间
        Date now = new Date();

        //先创建投资机构
        InvestmentInstitutions investmentInstitutions = new InvestmentInstitutions();
        investmentInstitutions.setCreateTime(now);
        investmentInstitutions.setShortName(investment_institution_name);

        //创造查询用的实例
        InvestmentInstitutions investmentInstitutionssearch = new InvestmentInstitutions();
        investmentInstitutionssearch.setShortName(investment_institution_name);

        List<InvestmentInstitutions> investmentInstitutions1  = investmentInstitutionsMapper.select(investmentInstitutionssearch);

        //判断机构名称，项目工商注册全称，轮次是否同时存在，同时存在给出提示；否则新建机构数据，新建项目信息，新建融资信息，新建创始人信息
        int jgid=-1,xmid=-1,yhid=-1;

        if(investmentInstitutions1.size() > 0){
            //获取机构id
            InvestmentInstitutions firstInvestmentInstitutions = investmentInstitutions1.get(0);
            jgid = firstInvestmentInstitutions.getId();

            //构造查询项目表实例
            Projects projectsSearch = new Projects();
            projectsSearch.setInvestmentInstitutionsId(jgid);
            projectsSearch.setFullName(project_full_name);

            //查询项目是否存在,若存在继续判断轮次是否存在，若不存在创建项目，创建融资信息，创始人信息
            List<Projects> projectsList = projectsMapper.select(projectsSearch);

            if (projectsList.size() > 0){
                Projects firstProjects = projectsList.get(0);

                xmid = firstProjects.getId();

                //构造查询投资历史实例
                ProjectFinancingLog projectFinancingLogSearch = new ProjectFinancingLog();
                projectFinancingLogSearch.setProjectId(xmid);
                projectFinancingLogSearch.setStage(rounds);

                //查询融资历史是否存在；若存在抛出异常提示；若不存在创建融资历史
                List<ProjectFinancingLog> projectFinancingLogList = projectFinancingLogMapper.select(projectFinancingLogSearch);
                if (projectFinancingLogList.size() > 0){
                    result.setMessage("您当前填的机构的项目，已存在。请勿重复提交！");
                    result.setStatus(50002);
                    result.setData(null);
                    return result;
                }else {
                    //拿到项目id，创建融资记录信息
                    BigDecimal bigDecimalAmount = new BigDecimal(amount);
                    BigDecimal bigDecimalStockRight = new BigDecimal(stock_right);


                    ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();

                    projectFinancingLog.setStage(rounds);
                    projectFinancingLog.setAmount(bigDecimalAmount);
                    projectFinancingLog.setCreateTime(now);
                    projectFinancingLog.setCurrency(currency1);
                    projectFinancingLog.setStockRight(bigDecimalStockRight);
                    projectFinancingLog.setFinancingTime(date);
                    projectFinancingLog.setStatus(0);
                    projectFinancingLog.setProjectId(xmid);

                    //插入数据
                    projectFinancingLogMapper.insert(projectFinancingLog);

                    result.setData(null);
                    result.setStatus(200);
                    result.setMessage("success");
                }
            }else{
                //构造创建项目的实例
                int aa = (int)(Math.random()*1000000);

                Projects projects = new Projects();
                projects.setShortName(project_name);
                projects.setFullName(project_full_name);
                projects.setKernelDesc(summary);
                projects.setSegmentation(field);
                projects.setCity(city);
                projects.setSerialNumber(aa);
                projects.setCreateTime(now);
                projects.setInvestmentInstitutionsId(jgid);

                projectsMapper.insert(projects);
                xmid = projects.getId();

                //拿到项目id，创建融资记录信息
                BigDecimal bigDecimalAmount = new BigDecimal(amount);
                BigDecimal bigDecimalStockRight = new BigDecimal(stock_right);


                ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();

                projectFinancingLog.setStage(rounds);
                projectFinancingLog.setAmount(bigDecimalAmount);
                projectFinancingLog.setCreateTime(now);
                projectFinancingLog.setCurrency(currency1);
                projectFinancingLog.setStockRight(bigDecimalStockRight);
                projectFinancingLog.setFinancingTime(date);
                projectFinancingLog.setStatus(0);
                projectFinancingLog.setProjectId(xmid);

                    //插入数据
                projectFinancingLogMapper.insert(projectFinancingLog);


                //拿到项目id,新建创始人信息
                Founders founders =new Founders();
                founders.setName(founder_name);
                founders.setCreateTime(date);
                founders.setYn(0);
                founders.setProjectId(xmid);

                    //插入创始人信息
                foundersMapper.insert(founders);
                yhid = founders.getId();



                    //获取到用户id创建教育经历
                FoundersEducation foundersEducation = new FoundersEducation();
                foundersEducation.setEducationExperience(founder_education);
                foundersEducation.setFounderId(yhid);

                List<FoundersEducation> foundersEducationsList = foundersEducationMapper.select(foundersEducation);
                if (foundersEducationsList.size() == 0){
                    foundersEducationMapper.insert(foundersEducation);
                }


                    //获取到用户id创建工作经历
                FoundersWork foundersWork = new FoundersWork();
                foundersWork.setFounderId(yhid);
                foundersWork.setWorkExperience(founder_work);

                List<FoundersWork> foundersWorkList = foundersWorkMapper.select(foundersWork);
                if (foundersWorkList.size() == 0){
                    foundersWorkMapper.insert(foundersWork);
                }

                result.setMessage("success");
                result.setStatus(200);
                result.setData(null);

            }

        }else {
            //没有该机构，则新建机构，新建项目，新建融资历史
            investmentInstitutionsMapper.insert(investmentInstitutions);
            jgid = investmentInstitutions.getId();
            //构造创建项目的实例
            int bh = 0;
            List<Projects> projectsList = projectsMapper.maxSerialNumber();
            if (projectsList.size() > 0){
                Projects projectsa = projectsList.get(0);
                bh = projectsa.getSerialNumber();
            }else {
                bh= 1000000;
            }


            int aa = bh+1;

            Projects projects = new Projects();
            projects.setShortName(project_name);
            projects.setFullName(project_full_name);
            projects.setKernelDesc(summary);
            projects.setSegmentation(field);
            projects.setCity(city);
            projects.setSerialNumber(aa);
            projects.setCreateTime(now);
            projects.setInvestmentInstitutionsId(jgid);


            //创建项目信息，并获取到刚创建的项目id
           projectsMapper.insert(projects);
           xmid =projects.getId();


            //拿到项目id,新建融资记录信息
              //将数据转换过来
            BigDecimal bigDecimalAmount = new BigDecimal(amount);
            BigDecimal bigDecimalStockRight = new BigDecimal(stock_right);


            ProjectFinancingLog projectFinancingLog = new ProjectFinancingLog();

            projectFinancingLog.setStage(rounds);
            projectFinancingLog.setAmount(bigDecimalAmount);
            projectFinancingLog.setCreateTime(now);
            projectFinancingLog.setCurrency(currency1);
            projectFinancingLog.setStockRight(bigDecimalStockRight);
            projectFinancingLog.setFinancingTime(date);
            projectFinancingLog.setStatus(0);
            projectFinancingLog.setProjectId(xmid);

              //插入数据
            projectFinancingLogMapper.insert(projectFinancingLog);


            //拿到项目id,新建创始人信息
            Founders founders =new Founders();
            founders.setName(founder_name);
            founders.setCreateTime(date);
            founders.setYn(0);
            founders.setProjectId(xmid);

            //插入创始人信息
            foundersMapper.insert(founders);
            yhid = founders.getId();


            //获取到用户id创建教育经历
            FoundersEducation foundersEducation = new FoundersEducation();
            foundersEducation.setEducationExperience(founder_education);
            foundersEducation.setFounderId(yhid);

            List<FoundersEducation> foundersEducationsList = foundersEducationMapper.select(foundersEducation);
            if (foundersEducationsList.size() == 0){
                foundersEducationMapper.insert(foundersEducation);
            }


            //获取到用户id创建工作经历
           FoundersWork foundersWork = new FoundersWork();
            foundersWork.setFounderId(yhid);
            foundersWork.setWorkExperience(founder_work);

            List<FoundersWork> foundersWorkList = foundersWorkMapper.select(foundersWork);
            if (foundersWorkList.size() == 0){
                foundersWorkMapper.insert(foundersWork);
            }


            result.setData(null);
            result.setStatus(200);
            result.setMessage("success");

        }



        return result;
    }

}
