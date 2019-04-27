package com.example.bean;

import com.example.service.bean.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 測試使用xml讀取javaconfig配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:bean/configuration/application-importJavaConfig.xml"})
public class ImportingJavaConfigTestFromXml {
    @Autowired
    ApplicationContext context;

    @Test
    public void showUser1() {
        DepartmentService ds = (DepartmentService) context.getBean("departmentService");
        ds.showDetail();
    }
}
