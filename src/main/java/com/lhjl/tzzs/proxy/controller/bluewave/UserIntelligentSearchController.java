package com.lhjl.tzzs.proxy.controller.bluewave;

import com.lhjl.tzzs.proxy.controller.GenericController;
import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.dto.CompanyIntelligentOutputDto;
import com.lhjl.tzzs.proxy.model.FoundersEducation;
import com.lhjl.tzzs.proxy.model.FoundersWork;
import com.lhjl.tzzs.proxy.service.FounderEducationService;
import com.lhjl.tzzs.proxy.service.FounderWorkService;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserIntelligentSearchController extends GenericController{
    @Resource
    private FounderWorkService founderWorkService;

    @Resource
    private FounderEducationService founderEducationService;

    @Resource
    private SubjectService subjectService;

    /**
     * 教育经历智能检索
     * @param appid
     * @param inputsWords
     * @param pageSize
     * @return
     */
    @GetMapping("/v{appid}/eductation/intelligent/search")
    public CommonDto<List<FoundersEducation>> educationIntelligentSearchV2(@PathVariable Integer appid, String inputsWords, Integer pageSize){
        CommonDto<List<FoundersEducation>> result  = new CommonDto<>();
        try {
            result = founderEducationService.getFounderEducationIntelligent(inputsWords, pageSize);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());

            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }
        return result;
    }

    /**
     * 工作经历智能检索
     * @param appid
     * @param inputsWords
     * @param pageSize
     * @return
     */
    @GetMapping("/v{appid}/work/intelligent/search")
    public CommonDto<List<FoundersWork>> findWorkIntelligentSearchV2(@PathVariable Integer appid,String inputsWords, Integer pageSize){
        CommonDto<List<FoundersWork>> result  = new CommonDto<>();

        try {
            result = founderWorkService.findFounderWork(inputsWords, pageSize);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
            result.setData(null);
            result.setMessage("服务器端发生错误");
            result.setStatus(502);
        }

        return result;
    }

    @GetMapping("/v{appid}/company/intelligent/search")
    public CommonDto<List<CompanyIntelligentOutputDto>> getCompanyIntelligentSearchV2(@PathVariable Integer appid,String inputsWords, Integer pageSize,Integer inputsType,Integer projectType){
        CommonDto<List<CompanyIntelligentOutputDto>> result =new CommonDto<>();

        try {
            result = subjectService.getCompanyIntelligent(inputsWords, pageSize, inputsType,projectType);
        }catch (Exception e){
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());

            result.setStatus(502);
            result.setMessage("服务器端发生错误");
            result.setData(null);
        }

        return result;
    }
}
