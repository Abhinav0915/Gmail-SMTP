package com.example.EmailServiceBackend.controller;
import com.example.EmailServiceBackend.dto.EmailDetails;
import com.example.EmailServiceBackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//7311115544
@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        return emailService.sendSimpleMail(details);
    }

    @PostMapping(path = "/sendAttachmentMail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> sendAttachmentMail(@ModelAttribute EmailDetails details) {
        try {
            String result = emailService.sendAttachmentMail(details, details.getAttachment());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }
}
