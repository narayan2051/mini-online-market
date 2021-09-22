package com.miu.minionlinemarkert.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String userEmail, String subject,String bodyMessage){
        MimeMessage mimeMailMessage= buildMimeMessage(userEmail,subject,bodyMessage);
        sendEmailAsynchronously(mimeMailMessage);
    }

    private MimeMessage buildMimeMessage(String userEmail, String subject, String bodyMessage) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            mimeMessage.setText(bodyMessage);
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(userEmail));
            mimeMessage.setSubject(subject);
        } catch (Exception exception) {
           exception.printStackTrace();
        }
        return mimeMessage;
    }

    public void sendEmailAsynchronously(MimeMessage mimeMessage) {
        CompletableFuture.runAsync(() -> {
            try {
                mailSender.send(mimeMessage);
            } catch (Exception e) {
              e.printStackTrace();
            }
        });
    }
}
