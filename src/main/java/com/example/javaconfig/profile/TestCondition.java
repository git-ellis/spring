package com.example.javaconfig.profile;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 配合@conditional使用，如果條件吻合，就生成該bean物件
 */
public class TestCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String isactive = environment.getRequiredProperty("spring.profiles.active");
        System.out.println("isActive = " + isactive);
        return true;
    }
}
