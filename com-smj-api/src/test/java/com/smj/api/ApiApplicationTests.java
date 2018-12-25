package com.smj.api;


import com.smj.comm.models.DictionVO;
import com.smj.comm.tools.CommTool;
import com.smj.one.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(value=SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class ApiApplicationTests {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void contextLoads() {


        List<DictionVO> set=new ArrayList<DictionVO>();
        List<DictionVO> set0=new ArrayList<DictionVO>();
        List<DictionVO> set1=new ArrayList<DictionVO>();
        List<DictionVO> set2=new ArrayList<DictionVO>();
        List<DictionVO> set3=new ArrayList<DictionVO>();

        set1.add(DictionVO.builder().key("1").value("1000").build());
        set1.add(DictionVO.builder().key("2").value("1000").build());
        set1.add(DictionVO.builder().key("3").value("1000").build());

        set2.add(DictionVO.builder().key("1").value("1000").build());
        set2.add(DictionVO.builder().key("2").value("1000").build());
        set2.add(DictionVO.builder().key("12").value("1000").build());

        set3.add(DictionVO.builder().key("1").value("1000").build());
        set3.add(DictionVO.builder().key("15").value("1000").build());
        set3.add(DictionVO.builder().key("16").value("1000").build());
        // 合集
        set.addAll(set1);
        set.addAll(set2);
        set.addAll(set3);
        System.out.println("合集是 "+set);

        //交集
        set1.retainAll(set2);
        set1.retainAll(set3);
        System.out.println("交集是 "+set1);

        //差集
        set0.removeAll(set1);
        set0.removeAll(set2);
        set0.removeAll(set3);
        System.out.println("差集是 "+set0);

        List<String> list=new ArrayList<String>();
        list.add("first");
        list.add("second");
        list.add("third");

       String str= StringUtils.join(list.toArray(), ",");
        System.out.println(str);

        Date dt = CommTool.addDay(new Date(), 7);
        System.out.println("------------------------------------------------------");
        System.out.println(CommTool.dateToString(dt, CommTool.FORMAT_MONTH_DAY));
        System.out.println("------------------------------------------------------");

        List<String> lists = userInfoService.QueryInfoList();
    }
}

