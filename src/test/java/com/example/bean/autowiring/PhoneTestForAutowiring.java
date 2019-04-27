package com.example.bean.autowiring;


import com.example.annotation.qualifier.IPhoneXr;
import com.example.annotation.qualifier.IPhoneXs;
import com.example.annotation.qualifier.Pixel3;
import com.example.domain.Phone;
import com.example.javaconfig.bean.AutoWiringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AutoWiringConfig.class) // locations = {"classpath*:bean/autowiring/application.xml"}
public class PhoneTestForAutowiring {
    @Autowired
    private Phone phone1;

    @Autowired
    @IPhoneXr
    private Phone phone2;

    @Autowired
    @Qualifier("iphoneXs")
    private Phone phone3;

    /**
     * 經觀察得知，自訂義限定符一樣會把classname自首轉小寫進行註冊，
     * 所以還是會有@Pixel @Qualifier("pixel")造成錯誤的問題
     * 自訂義限定符主要還是為了解決java不允許放兩個同樣的anotation
     * (除非該anotation定義有標示@Repeatable，但@Qualifier並沒有標示)
     */

    @Autowired
    @IPhoneXs
    private Phone phone4;

    @Autowired
    @Qualifier("pixel3")
    private Phone phone5;

    @Autowired
    @Pixel3
    private Phone phone6;

    @Autowired
    @Qualifier("Pixel3a")
    private Phone phone7;

    @Autowired
    @Qualifier("Note10")
    private Phone phone8;


    @Test
    public void showPhone1() {
        System.out.println(phone1.showDetails());
    }

    @Test
    public void showPhone2() {
        System.out.println(phone2.showDetails());
    }

    @Test
    public void showPhone3() {
        System.out.println(phone3.showDetails());
    }

    @Test
    public void showPhone4() {
        System.out.println(phone4.showDetails());
    }

    @Test
    public void showPhone5() {
        System.out.println(phone5.showDetails());
    }

    @Test
    public void showPhone6() {
        System.out.println(phone6.showDetails());
    }

    @Test
    public void showPhone7() {
        System.out.println(phone7.showDetails());
    }

    @Test
    public void showPhone8() {
        System.out.println(phone8.showDetails());
    }

}
