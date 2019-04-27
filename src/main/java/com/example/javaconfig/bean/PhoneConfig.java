package com.example.javaconfig.bean;

import com.example.annotation.qualifier.IPhoneXr;
import com.example.annotation.qualifier.IPhoneXs;
import com.example.annotation.qualifier.Pixel3;
import com.example.domain.IPhone;
import com.example.domain.Note;
import com.example.domain.Phone;
import com.example.domain.Pixel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhoneConfig {

    @Bean
    @IPhoneXr
    public Phone iphoneXr() {
        return new IPhone("Apple", "Xr", "China");
    }

    @Bean
    @IPhoneXs
    public Phone iphoneXs() {
        return new IPhone("Apple", "Xs", "China");
    }

    @Bean
    @Qualifier("Note10")
    public Phone s10() {
        return new Note("Samsung", "S10", "Korea");
    }

    @Bean
    @Qualifier("Pixel3a")
    public Phone pixel3a() {
        return new Pixel("Google", "Pixel3a", "Taiwan");
    }


    @Bean
    @Pixel3
    public Phone pixel3() {
        return new Pixel("Google", "Pixel3", "Taiwan");
    }

    @Bean
//    @Qualifier("pixel3")
    public Phone pixel3_1() {
        return new Pixel("Google", "Pixel3_1", "Taiwan");
    }
}
