package com.microservice.configuration;

import com.microservice.service.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class NotificationServiceConfiguration {

    @Bean
    public Consumer<Message<String>> notificationEventSupplier() {
        return message -> new MailService().sendMail(message.getPayload());
    }

}
