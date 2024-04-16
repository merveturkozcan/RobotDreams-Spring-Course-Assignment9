package com.robotdreams.assignment9.service;

import com.robotdreams.assignment9.customFunctions.SmsFunction;
import com.robotdreams.assignment9.entity.Order;
import com.robotdreams.assignment9.entity.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Async
    public void sendSms(Order order, User user) {

        SmsFunction smsReplaceFunction = (template, firstname, email, orderNumber) ->
                template.replace("NAME", firstname).replace("EMAIL", email).replace("ORDERNUMBER", orderNumber);

        var orderNumber = order.getOrderNumber();
        var name = user.getFirstname();
        var email = user.getEmail();

        // mocking
        var phoneNumber = user.getPhoneNumber();

        String smsBody = "Sevgili NAME siparişini aldık, Siparişinin numarası: ORDERNUMBER . Siparişinin detaylarına EMAIL adresine gönderdiğimiz mailden ulaşabilirsin.";

        smsReplaceFunction.replace(smsBody, name, email, orderNumber);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
