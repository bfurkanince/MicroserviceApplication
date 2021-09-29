package com.microservice.service;

import org.springframework.stereotype.Service;

@Service
public class MailService {

    public void sendMail(String ean){
            System.out.println("#################### " + ean +" ####################");
            // Entegrasyon yazilacak.
    }

}
