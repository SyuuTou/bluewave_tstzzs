package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.CompanyIntelligentOutputDto;
import com.lhjl.tzzs.proxy.mapper.SubjectMapper;
import com.lhjl.tzzs.proxy.mapper.SubjectTypeRelationalMapper;
import com.lhjl.tzzs.proxy.model.Subject;
import com.lhjl.tzzs.proxy.model.SubjectTypeRelational;
import com.lhjl.tzzs.proxy.service.SubjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private SubjectTypeRelationalMapper subjectTypeRelationalMapper;

    /**
     * 公司检索接口
     * @param inputsWords 输入内容
     * @param pageSize  一页返回数量
     * @param inputsType 搜全程还是搜简称
     * @return
     */
    @Override
    public CommonDto<List<CompanyIntelligentOutputDto>> getCompanyIntelligent(String inputsWords, Integer pageSize,Integer inputsType,Integer projectType){
        CommonDto<List<CompanyIntelligentOutputDto>> result =new CommonDto<>();

        if (inputsWords == null){
            inputsWords = "";
        }

        if (pageSize == null || pageSize <= 0){
            pageSize = 5;
        }

        if (inputsType == null){
            inputsType = 0;
        }

        Integer startPage = 0;


        List<CompanyIntelligentOutputDto> list = new ArrayList<>();

        if (inputsType == 0){
            List<Subject> subjectList = subjectMapper.getIntelligentSearchInfo(inputsWords,startPage,pageSize,projectType);
            if (subjectList.size() > 0){
                for (Subject s:subjectList){
                    CompanyIntelligentOutputDto companyIntelligentOutputDto = new CompanyIntelligentOutputDto();
                    companyIntelligentOutputDto.setCompanyFullName(s.getFullName());
                    companyIntelligentOutputDto.setCompanyName(s.getShortName());
                    companyIntelligentOutputDto.setCompanyId(s.getId());
                    companyIntelligentOutputDto.setSourceId(s.getSourceid());

                    SubjectTypeRelational subjectTypeRelationalForSearch = new SubjectTypeRelational();
                    subjectTypeRelationalForSearch.setSubjectId(s.getId());
                    SubjectTypeRelational subjectTypeRelational = subjectTypeRelationalMapper.selectOne(subjectTypeRelationalForSearch);
                    if (null != subjectTypeRelational){
                        companyIntelligentOutputDto.setSourceType(subjectTypeRelational.getSubjectTypeId());
                    }else {
                        companyIntelligentOutputDto.setSourceType(1);
                    }

                    list.add(companyIntelligentOutputDto);
                }
            }

        }else if (inputsType == 1){
            List<Subject> subjectList = subjectMapper.getIntelligentSearchInfoFullName(inputsWords,startPage,pageSize,projectType);
            if (subjectList.size()>0){
                for (Subject s:subjectList){
                    CompanyIntelligentOutputDto companyIntelligentOutputDto = new CompanyIntelligentOutputDto();
                    companyIntelligentOutputDto.setCompanyFullName(s.getFullName());
                    companyIntelligentOutputDto.setCompanyName(s.getShortName());
                    companyIntelligentOutputDto.setCompanyId(s.getId());
                    companyIntelligentOutputDto.setSourceId(s.getSourceid());

                    SubjectTypeRelational subjectTypeRelationalForSearch = new SubjectTypeRelational();
                    subjectTypeRelationalForSearch.setSubjectId(s.getId());
                    SubjectTypeRelational subjectTypeRelational = subjectTypeRelationalMapper.selectOne(subjectTypeRelationalForSearch);
                    if (null != subjectTypeRelational){
                        companyIntelligentOutputDto.setSourceType(subjectTypeRelational.getSubjectTypeId());
                    }else {
                        companyIntelligentOutputDto.setSourceType(1);
                    }

                    list.add(companyIntelligentOutputDto);
                }
            }
        }else {
            result.setMessage("输入类型未知");
            result.setStatus(502);
            result.setData(null);

            return result;
        }

        result.setData(list);
        result.setStatus(200);
        result.setMessage("success");

        return result;
    }
}
