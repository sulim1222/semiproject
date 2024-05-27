package main.com.web.qna.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class FAQ {
	private int faqAllNo;
    private String faqCategory;
    private String faqTitle;
    private String faqContent;
//    private String faqWriter;
    private Date faqDate;
    private String location;
	
    
    
	

   
}
