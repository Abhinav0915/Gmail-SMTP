package com.example.EmailServiceBackend.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDetails {
    private String recipient;
    private String cc;
    private String msgBody;
    private String subject;
    private MultipartFile attachment;
    private List<String> toRecipients;
    private List<String> toCC;
    private List<String> toBCC;
}
