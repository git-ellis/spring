package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@Aspect
public aspect TestAspect {

    /**
     * 使用方法參數的型別來限制pointcut
     */

    /**
     * 匹配所有方法上的有特定annotation
     * @param mylog
     */
    @Before("execution(* com.example..*.*(..)) && @annotation(mylog)")
    public void test1(Mylog mylog) {
        mylog.value();
    }

    @Before("execution(* ..Sample+.sampleGenericMethod(*)) && args(param)")
    public void test2(String param) {
        // 限制Sample底下的子類的sampleGenericMethod方法，該方法的參數類型必須為字串
    }

    /**
     * 不能用在泛型集合，此advice會出錯(X)
     */
    @Before("execution(* ..Sample+.showGeneticList(*)) && args(params)")
    public void test3(List<String> params) {
        // 錯誤示範
    }

    /**
     * 使用argNames來應對method paramters
     */
    @Before(value = "execution(* com.example.service..*.*(..)) && target(bean) && @annotation(mylog)",
            argNames = "bean, mylog") // 對應下面方法參數
    public void test4(Object bean, Mylog mylog) {
    }

    /**
     * Spring AOP會嘗試推斷要綁定的變量與parameter之間的配對
     * 例如，如果只有一個變量，而advice method也只有一個參數，那它們就是一對）
     * 如果變量的綁定是模糊的，會拋出一個AmbiguousBindingException
     */
    @Before(value = "execution(* com.example.service..*.*(..)) && target(bean) && @annotation(mylog)",
            argNames = "bean, mylog") // 如果第一個參數是JoinPoint，argNames不用再定義jp
    public void test5(JoinPoint jp, Object bean, Mylog mylog) {

    }


//    ---------- 限制參數 ----------

    /**
     * within指定class全名，指定類型中的所有方法將被攔截
     */
    @Pointcut("within(com.example.service.aspect.AtmService)")
    public void within_1() {
    }

    @Pointcut("within(com.example.service.aspect..*)")
    public void within_2() {
    }

    /**
     * this(type)如果生成的代理對象可以轉換成type，匹配生成的代理對象的所有方法(public)
     * (目標對象有實作或繼承type)
     */
    @Before("this(com.example.aspect.Sample)")
    public void this_1(Sample sample) {
    }

    /**
     * target(type)如果被代理的目標對象可以轉換成type，則匹配所有的被代理的目標對象的所有方法(public)
     * (目標對象有實作或繼承type)
     */
    @Before("target(com.example.aspect.Sample)")
    public void target(Sample sample) {
    }

    /**
     * 匹配不帶任何參數的方法
     */
    @Pointcut("args()")
    public void args_1() {
    }

    /**
     * 匹配只有一個參數且型別為String的方法
     */
    @Pointcut("args(String)")
    public void args_2() {
    }

    /**
     * 匹配任意個參數但第一個參數型別為String的方法
     */
    @Pointcut("args(String, ..)")
    public void args_3() {
    }

    /**
     * 匹配任意個參數但最後一個參數型別為String的方法
     */
    @Pointcut("args(.., String)")
    public void args_4() {
    }

    /**
     * @args(type) 匹配有參數的方法且參數上有typq型別的annotation
     */
    @Pointcut("@args(com.example.aspect.Mylog)")
    public void at_args() {
        // add(@Mylog Myparam param)
    }

    /**
     * @target(type) 被代理的目標類別(以及父類?)上有typq型別的annotation的方法
     */
    @Pointcut("@target(com.example.aspect.Mylog)")
    public void at_target() {
    }

    /**
     * @within(type) 和@target類似，匹配被代理的目標類別以及父類上有該typq型別的annotation，
     * (如果方法被定義在父類，則子類調用父類的方法會匹配，但是子類自定義的方法則不會匹配)
     * 如果父類的方法被子類override則不會匹配
     */
    @Pointcut("@within(com.example.aspect.Mylog)")
    public void at_within() {
    }

    /**
     * @annotation(type) 匹配所有方法上面有typq型別的annotation的方法
     */
    @Before("@annotation(com.example.aspect.Mylog)")
    public void at_annotation(Mylog myLog) {
    }

    /**
     * @bean(idOrName) 匹配spring bean容器中id或name等於指定idOrName的所有方法
     */
    @Pointcut("@bean(atmService)")
    public void at_bean_1() {
    }

    /**
     * @bean(idOrName*) 匹配spring bean容器中id或name為idOrName開頭的bean的所有方法
     */
    @Pointcut("@bean(atmService*)")
    public void at_bean_2() {
    }


    /**
     * 匹配spring bean容器中id或name為idOrName開頭的bean的所有方法，
     * 或方法上面有@Mylog的方法
     */
    @Before("@bean(atmService*) || @annotation(com.example.aspect.Mylog)")
    public void combination_1() {
    }

    /**
     * 匹配spring bean容器中id或name為idOrName開頭的bean的所有空參數方法
     */
    @Before("@bean(atmService*) && args()")
    public void combination_2() {
    }


    /**
     * 任何一個advice都可以自行附加一個JoinPoint參數(around advice必須是ProceedingJoinPoint)
     * JoinPoint接口提供了大量有用的方法，例如
     * getArgs()（返回方法参数）
     * getThis()（返回代理对象）
     * getTarget()（返回目标对象）
     * getSignature()（返回advised method的描述）
     * toString()（打印advised method）
     */
//
    @Before("log(card)")
    public void beforeWithdraw(CreditCard card) {
        System.out.println("---------- beforeWithdraw ----------");

    }

}
