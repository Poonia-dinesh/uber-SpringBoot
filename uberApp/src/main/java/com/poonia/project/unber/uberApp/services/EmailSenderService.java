package com.poonia.project.unber.uberApp.services;

public interface EmailSenderService {
    public void sendEmail(String toEmail, String subject, String body);
}
