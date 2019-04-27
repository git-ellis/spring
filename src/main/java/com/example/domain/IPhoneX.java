package com.example.domain;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
/**
 * @Primary 配置為自動裝配的首選(如果有多個選擇)
 */
public class IPhoneX extends Phone {

    public IPhoneX() {
        brand = "Apple";
        model = "X";
        placeOfManufacture = "China";
    }

    @Override
    public String showDetails() {
        return this.toString();
    }
}
