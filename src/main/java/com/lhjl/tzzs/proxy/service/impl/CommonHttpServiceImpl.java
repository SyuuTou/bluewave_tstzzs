package com.lhjl.tzzs.proxy.service.impl;

import com.lhjl.tzzs.proxy.service.CommonHttpService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commonHttpService")
public class CommonHttpServiceImpl implements CommonHttpService {
    @Override
    public List<String> requestLogin(String name, String password) {

        return null;
    }
}
