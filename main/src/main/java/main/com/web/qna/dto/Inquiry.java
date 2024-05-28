package main.com.web.qna.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inquiry {
    private int onToOneInquiryId;
    private String inquiryType;
    private String title;
    private String content;
    private Date inquiryDate; 
    private Time inquiryTime; 
    private int MemberNo;
}
