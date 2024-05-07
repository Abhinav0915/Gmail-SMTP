package com.example.EmailServiceBackend.service;

import com.example.EmailServiceBackend.dto.EmailDetails;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);

    String sendAttachmentMail(EmailDetails details, MultipartFile attachment);
//    String sendAttachmentMail(EmailDetails details);

}
