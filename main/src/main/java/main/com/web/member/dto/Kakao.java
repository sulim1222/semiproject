package main.com.web.member.dto;

import javax.annotation.processing.SupportedAnnotationTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kakao {
	private Long kakaoId;
	private String nickname;
	private String account_email;
	/* [출처] 카카오 로그인 API - REST API(3)|작성자 dushui */


	
}
