package main.com.web.qna.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.sql.DATE;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class Inquiry {
	private int onToOneInquiryId;
	private String inquiryType;
	private String title;
	private String content;
	private DATE inquiryDate;
	private String MemberNo;
}
