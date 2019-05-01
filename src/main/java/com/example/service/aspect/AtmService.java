package com.example.service.aspect;

import com.example.aspect.CreditCard;

public class AtmService {

    public int withdrawMoney(CreditCard card, String password, int money) throws Exception {
        if (!card.getPassword().equals(password))
            throw new Exception("Password is invalid");

        if (money > card.getMoney())
            throw new Exception("Money of this credit card is not enough");

        card.setMoney(card.getMoney() - money);

        return money;
    }


}
