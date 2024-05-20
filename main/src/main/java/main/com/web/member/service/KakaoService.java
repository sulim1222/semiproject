package main.com.web.member.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.sql.Connection;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import main.com.web.member.dao.MemberDao;
import main.com.web.member.dto.Kakao;
import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.close;
public class KakaoService {
	 public String getAccessToken (String authorize_code) {
	     System.out.println("----------------------------토큰발급---------------------------");
		 String access_Token = "";
	     String refresh_Token = "";
	     String id_token ="";
	     
	     
	     //토큰발급 요청을 보낼 주소
	     String reqURL = "https://kauth.kakao.com/oauth/token";
	        
	        try {
                //URL객체 생성
	            URL url = new URL(reqURL); //code를 가지고 보낼 url요청주소
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            // 새로운창으로 연결해줌
	            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로 
                //.setDoOutput(true): URLConnection이 서버에 데이터를 보내는데 사용할 수 있는 지의 여부를 설정하는 것
	            conn.setRequestMethod("POST"); // 보내는방식은 POST
	            conn.setDoOutput(true); // ..
	            
	            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=a567f7c380e11df07ed70d544e8f86d8");
	            sb.append("&redirect_uri=http://localhost:9090/main/kakaologin");
	            sb.append("&code=" + authorize_code);
	            bw.write(sb.toString());
	            bw.flush();
	            
	            //응답확인 200이면 정상
	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);
	 
	            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println("response body : " + result);
	            
	            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            //JsonParser parser = new JsonParser();
	            System.out.println("result"+result);
				JsonParser parser = new JsonParser(); 
	            JsonElement element = parser.parse(result);
	            System.out.println("parser"+parser);
	            System.out.println("element"+element);
	           // --로직오류 66~ 오류-- 
	            		
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	            id_token = element.getAsJsonObject().get("id_token").getAsString();
	            System.out.println("access_Token:"+access_Token);
	            System.out.println("refresh_Token:"+refresh_Token);
	            System.out.println("id_token:"+id_token);
	            
	            System.out.println("access_token : " + access_Token);
	            System.out.println("refresh_token : " + refresh_Token);
	            System.out.println("id_token: "+ id_token);
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	        
	        return access_Token;
	    }


	public Kakao memberInfo(String access_Token) {
		Connection connection = getConnection();
		Kakao member = new Kakao(); //사용자 정보 
		
		String requestURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(requestURL);
			HttpURLConnection conn = ((HttpURLConnection)url.openConnection()); 
			conn.setRequestMethod("POST"); // post로 연결설정
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode:"+responseCode); //연결코드
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line =""; //받아온값
			String result =""; // 가져온값 
			while((line=br.readLine())!=null) {
				result +=line;
			}
			System.out.println("response body :"+ result);
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
		    JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            Long id = element.getAsJsonObject().get("id").getAsLong();
            
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
			/*
			 * String gender = kakao_account.getAsJsonObject().get("gender").getAsString();
			 * String birthday =
			 * kakao_account.getAsJsonObject().get("birthday").getAsString(); String
			 * age_range = kakao_account.getAsJsonObject().get("age_range").getAsString();
			 */
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            System.out.println("id: "+ id);
            System.out.println("nickname: "+nickname);
            
            //setter이용하여 KakaoVO에 담기 
            member.setKakaoId(id);
            member.setNickname(nickname);
            member.setAccount_email(email); 
            boolean duplicate = new MemberDao().kakaoselect(connection,email);
            close(connection);
            if(duplicate) {
            	Connection connto = getConnection();
            	new MemberDao().KakaoMember(connto,member);
            	close(connto);
            }
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}
	



}
