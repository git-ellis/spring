package com.example.aspect;

import com.example.service.aspect.AtmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:aspect/application.xml"})
public class AtmServiceTest {

    @Autowired
    private CreditCard card;
    @Autowired
    private AtmService service;

    @Test
    public void  testWithdrawing() throws Exception {
        service.withdrawMoney(card, "ppwd", 500);
    }
}
