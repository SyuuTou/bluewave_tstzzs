package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ProjectBusinessPlanImageMapper;
import com.lhjl.tzzs.proxy.mapper.ProjectBusinessPlanMapper;
import com.lhjl.tzzs.proxy.model.ProjectBusinessPlan;
import com.lhjl.tzzs.proxy.model.ProjectBusinessPlanImage;
import com.lhjl.tzzs.proxy.service.GenericService;
import com.lhjl.tzzs.proxy.service.ProjectBusinessPlanService;
import com.lhjl.tzzs.proxy.utils.AliOssUtils;
import com.lhjl.tzzs.proxy.utils.PdfToImageToUpload;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service("projectBusinessPlanService")
public class ProjectBusinessPlanServiceImpl extends GenericService implements ProjectBusinessPlanService {

    @Autowired
    private ProjectBusinessPlanMapper projectBusinessPlanMapper;

    @Autowired
    private ProjectBusinessPlanImageMapper projectBusinessPlanImageMapper;

    @Transactional
    @Override
    public CommonDto<List<ProjectBusinessPlanImage>> resolveProjectBusinessPlan(MultipartFile file, Integer projectId, String token) {
        CommonDto<List<ProjectBusinessPlanImage>> result = new CommonDto<>();
        try {
            String imagePath = AliOssUtils.upload(file);

            if (projectBusinessPlanMapper.existsWithPrimaryKey(projectId)){
                ProjectBusinessPlan now = new ProjectBusinessPlan();
                now.setProjectId(projectId);
                now.setUrl("https://img.idatavc.com/"+imagePath);
                now.setUpdateTime(DateTime.now().toDate());
                projectBusinessPlanMapper.updateByPrimaryKeySelective(now);
            }else {
                ProjectBusinessPlan now = new ProjectBusinessPlan();
                now.setProjectId(projectId);
                now.setCreateTime(DateTime.now().toDate());
                now.setUrl("https://img.idatavc.com/"+imagePath);
                now.setUpdateTime(DateTime.now().toDate());
                now.setCreator(token);
                projectBusinessPlanMapper.insert(now);
            }

            File convFile = new File( file.getOriginalFilename());
            file.transferTo(convFile);
            List<String> imgList = PdfToImageToUpload.convert(convFile);

            if(null == imgList || imgList.size() == 0){
                result.setStatus(200);
                result.setMessage("success");
                result.setData(null);
                return result;
            }
            ProjectBusinessPlanImage projectBusinessPlanImage = null;
            List<ProjectBusinessPlanImage> imageList = new ArrayList<>();
            for (int i = 0 ; i< imgList.size(); i++ ){
                    projectBusinessPlanImage = new ProjectBusinessPlanImage();
                    projectBusinessPlanImage.setNo(i);
                    projectBusinessPlanImage.setCreateTime(DateTime.now().toDate());
                    projectBusinessPlanImage.setProjectId(projectId);
                    projectBusinessPlanImage.setCreator(token);
                    projectBusinessPlanImage.setUpdateTime(DateTime.now().toDate());
                    projectBusinessPlanImage.setUrl(imgList.get(i));
                    projectBusinessPlanImageMapper.insert(projectBusinessPlanImage);
                imageList.add(projectBusinessPlanImage);
            }
            result.setData(imageList);
            result.setMessage("success");
            result.setStatus(200);
            return result;
        } catch (IOException e) {
            this.LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }

        return null;
    }

    @Transactional
    @Override
    public CommonDto<String> updateProjectBusinessPlanImage(List<ProjectBusinessPlanImage> reqDto, Integer projectId) {

        if (projectBusinessPlanImageMapper.deleteByPrimaryKey(projectId)>0){
            for (ProjectBusinessPlanImage projectBusinessPlanImage : reqDto){
                projectBusinessPlanImageMapper.insert(projectBusinessPlanImage);
            }
            return new CommonDto<>("ok","success",200);
        }

        return new CommonDto<>("failed","failed",500);
    }
}
