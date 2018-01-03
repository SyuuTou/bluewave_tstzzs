package com.lhjl.tzzs.proxy.controller.test;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhjl.tzzs.proxy.dto.CommonDto;
import com.lhjl.tzzs.proxy.model.Users;
import com.lhjl.tzzs.proxy.service.UserInfoService;

@RestController
public class ControllerTest {
	private static Logger logger=LoggerFactory.getLogger(ControllerTest.class);
	@Resource
	private UserInfoService userInfoService;
	
	//*****测试用******
    @GetMapping("/get/singleUser")
    public CommonDto<Users> getUUID(Integer userid) {
    	CommonDto<Users> result = userInfoService.getUserByUserId(userid);
		return result;
    }
    @GetMapping("/get/usersSplit")
    public List<Users> listSplit(Integer pageNum,Integer pageSize){
    	List<Users> listSplit = userInfoService.listSplit(pageNum,pageSize);
    	return listSplit;
    }
}
