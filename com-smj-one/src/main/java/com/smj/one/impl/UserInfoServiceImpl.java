package com.smj.one.impl;

import com.smj.one.service.UserInfoService;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public List<String> QueryInfoList() {
        List<String> list = Lists.newArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        return list;
    }
}


