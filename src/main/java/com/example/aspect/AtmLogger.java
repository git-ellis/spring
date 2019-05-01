package com.example.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component // Spring AOP 只針對bean容器裡面的物件起作用，所以切面類也要註冊到bean容器
//@EnableAspectJAutoProxy
public class AtmLogger {

    private Map<Integer, String> infoMap = new HashMap<>();

    /**
     * 可以定義多個PointCut
     */
//    @Pointcut("execution(* com.example.service..*.*(..))")
//    public void log() {
//        // 所有com.example.service同層與子目錄下的所有class的所有方法
//    }
    @Pointcut("execution(* com.example.service.aspect.AtmService.*(..))")
    public void log() {
        // 所有com.example.service同層與子目錄下的所有class的所有方法
    }


    // 推測一個ASPECT只能有一個Pointcut

    /**
     * 使用方法參數的型別來限制pointcut
     */
//    @Pointcut("execution(* com.example.service.aspect.AtmService.withdrawMoney(com.example.aspect.CreditCard,..))"
//            + " && args(card)")
//    public void log(CreditCard card) {
//
//    }

    /**
     * 使用方法參數的型別來限制pointcut
     */
//    @Pointcut("execution(* com.example.service..*.*(..)) && args(card, ..)")
//    public void log(CreditCard card) {
//        // (1)強制匹配至少一個參數的方法執行，且第一個參數必須是CreditCard instance;
//        // (2)讓實際的CreditCard對象在advice中可用
//    }


//
    @After("log()")
    public void afterWithdraw() {
        System.out.println("---------- afterWithdraw ----------");
    }

    @AfterThrowing(value = "log()", throwing = "e")
    public void withDrawFailed(Exception e) {
        System.out.println("---------- withDrawFailed ----------");
    }

    @AfterReturning(value = "log()", returning = "r")
    public void finishWithdrawing(Object r) {
        System.out.println("---------- finishWithdrawing ----------");
    }


//    @Before("log(card, password, money)")
//    public void beforeWithdraw(CreditCard card, String password, int money) {
//        System.out.println("---------- beforeWithdraw ----------");
//
//    }
//
//    @After("log(card, password, money)")
//    public void afterWithdraw(CreditCard card, String password, int money) {
//        System.out.println("---------- afterWithdraw ----------");
//    }
//
//    @AfterThrowing(value = "log(card, password, money)", throwing = "e")
//    public void withDrawFailed(CreditCard card, String password, int money, Exception e) {
//        System.out.println("---------- withDrawFailed ----------");
//    }
//
//    @AfterReturning(value = "log(card, password, money)", returning = "r")
//    public void finishWithdrawing(CreditCard card, String password, int money, Object r) {
//        System.out.println("---------- finishWithdrawing ----------");
//    }

    /**
     * 最彈性的advise，但是一般需求用其他的advise就好
     * 回傳型別要和Target method一樣
     *
     * @param jp
     * @return
     * @throws Throwable
     */
    /*@Around("log(card, password, money)")
    public int aroundWithdrawing(ProceedingJoinPoint jp, CreditCard card, String password, int money) throws Throwable {
        System.out.println("---------- aroundWithdrawing ----------");

        // before
//        int result = (int) jp.proceed(); // 可以調用數次，或甚至根本不調用
        int result = (int) jp.proceed(new Object[]{card, password, money}); // 帶參數
        // after(finally)

        return result;
    }*/


}
