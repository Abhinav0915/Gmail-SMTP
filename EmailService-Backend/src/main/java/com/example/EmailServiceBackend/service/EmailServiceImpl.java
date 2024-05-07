package com.example.EmailServiceBackend.service;

import com.example.EmailServiceBackend.dto.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);


//
//            List<String> toRecipients = details.getToRecipients();
//            if( toRecipients != null && !toRecipients.isEmpty()){
//                simpleMailMessage.setTo(toRecipients.toArray(new String[0]));
//            }
//
//            List<String> toCC = details.getToCC();
//            if(toCC != null){
//                simpleMailMessage.setCc(toCC.toArray(new String[0]));
//            }
//
//            List<String> toBCC = details.getToBCC();
//            if(toBCC != null){
//                simpleMailMessage.setBcc(toBCC.toArray(new String[0]));
//            }
            simpleMailMessage.setTo(details.getRecipient());
//            simpleMailMessage.setTo(details.getCc());
            simpleMailMessage.setText(details.getMsgBody());
            simpleMailMessage.setSubject(details.getSubject());

            javaMailSender.send(simpleMailMessage);
            return "Mail Sent Successfully";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Error in Sending Email";
        }
    }

//    @Override
//    public String sendAttachmentMail(EmailDetails details) {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try{
//            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(details.getSubject());
//
//            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
//            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()),file);
//
//            javaMailSender.send(mimeMessage);
//            return "Mail Sent Successfully";
//
//        }catch (MessagingException e) {
//            System.out.println("Error: "+ e.getMessage());
//            return "Error while Sending Mail";
//        }
//    }

    @Override
    public String sendAttachmentMail(EmailDetails details, MultipartFile attachment) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            if (attachment != null) {
                mimeMessageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), getFileResource(attachment));
            }
            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully";

        } catch (MessagingException | IOException e) {
            System.out.println("Error: " + e.getMessage());
            return "Error while Sending Mail";
        }

    }

    private Resource getFileResource(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return new ByteArrayResource(bytes);
    }
}
