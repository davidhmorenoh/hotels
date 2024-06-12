package com.management.hotels.domain.ports.services.email;

public interface NotificationEmailDomainPortService {

    void sendSimpleMessage(String toEmail, String subject, String body);

}