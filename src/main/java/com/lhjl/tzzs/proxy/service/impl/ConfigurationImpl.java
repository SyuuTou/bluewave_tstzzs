package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.mapper.ConfigurationTableMapper;
import com.lhjl.tzzs.proxy.model.ConfigurationTable;
import com.lhjl.tzzs.proxy.service.ConfigurationService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConfigurationImpl implements ConfigurationService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConfigurationImpl.class);

    @Resource
    ConfigurationTableMapper configurationTableMapper;

    @Override
    public CommonDto<String> getConfiguration(Integer type){
      CommonDto<String>   result = new CommonDto<>();

      if (type == null || "".equals(type) || "undefined".equals(type)){
          result.setStatus(50001);
          result.setMessage("类型不能为空");
          result.setData(null);
      }

        ConfigurationTable configurationTable = new ConfigurationTable();
        configurationTable.setConfigurationType(type);

        List<ConfigurationTable> configurationTableList = configurationTableMapper.select(configurationTable);
        if (configurationTableList.size() > 0){
            ConfigurationTable configurationTableForValue = configurationTableList.get(0);
            String value = configurationTableForValue.getConfigurationValue();

            result.setData(value);
            result.setMessage("success");
            result.setStatus(200);
        }else {
            result.setStatus(50001);
            result.setMessage("没有找到当前类型的配置");
            result.setData(null);
        }

      return result;
    }
}
