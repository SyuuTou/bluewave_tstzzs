package com.lhjl.tzzs.proxy.service.reference;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.ReportModules;
import com.lhjl.tzzs.proxy.model.ReportModulesFiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReportModulesService {
    CommonDto<List<ReportModules>> queryReportModulesList(Integer appId, Integer reportId);

    CommonDto<String> createReportModules(Integer appId, ReportModules reportModules);

    CommonDto<String> deleteReportModulesById(Integer appId, Integer reportModulesId);

    CommonDto<List<ReportModulesFiles>> queryReportModulesFilesList(Integer appId, Integer reporModulesId);

    CommonDto<String> deleteReportModulesFilesById(Integer appId, Integer reportModulesFileId);

    CommonDto<String> saveOrUpdateReportModulesFile(Integer appId, Integer reportModulesId, Integer reportModulesFileId, MultipartFile file);
}
