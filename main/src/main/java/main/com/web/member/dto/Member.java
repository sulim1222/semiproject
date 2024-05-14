package main.com.web.member.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private int memberNo; //sequence 값 설정
	private String memberId;
	private String memberPwd;  
	private String memberName; 
	private String memberPhone; 
	private String memberCheckNo; //*입력값 안받아옴
	private Date memberEnrollDate; //default sysdate
}
