package main.com.web.enjoy.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TourApiController {
	 private static final String API_KEY = "7db8089711ec43feb81b691bfa621e32";
	 private static final String API_URL = "http://api.visitjeju.net/vsjApi/contents/searchlist?apiKey=" + API_KEY + "&locale=kr&category=c1&page=1";

	    public static void main(String[] args) {
	        try {
	            // API 호출
	            URL url = new URL(API_URL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            int responseCode = conn.getResponseCode();

	            if (responseCode == 200) { // 응답이 성공적일 때
	                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                String inputLine;
	                StringBuffer response = new StringBuffer();

	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                in.close();

	                // JSON 파싱
	                JsonElement jsonElement = JsonParser.parseString(response.toString());
	                JsonObject jsonObject = jsonElement.getAsJsonObject();
	                JsonArray items = jsonObject.getAsJsonArray("items");

	                // 필요한 데이터 추출 //위도 경도 변수명 변경하기 
	                for (JsonElement itemElement : items) {
	                    JsonObject item = itemElement.getAsJsonObject();
	                    String label = item.getAsJsonObject("contentscd").get("label").getAsString();
	                    String tourName = item.get("title").getAsString();
	                    String tourAddress = item.get("address").getAsString();
	                    String introduction = item.get("introduction").getAsString();
	                    double latitude = item.get("latitude").getAsDouble();
	                    double longitude = item.get("longitude").getAsDouble();
	                    String phoneno = item.get("phoneno").getAsString();
	                    String photoid = item.getAsJsonObject("repPhoto").getAsJsonObject("photoid").get("photoid").getAsString();
	                    String imgpath = item.getAsJsonObject("repPhoto").get("imgpath").getAsString();

	                    // 출력
	                    System.out.println("콘텐츠코드 라벨/관광지: " + label);
	                    System.out.println("콘텐츠명/성산일출봉: " + tourName);
	                    System.out.println("주소: " + tourAddress);
	                    System.out.println("간단소개: " + introduction);
	                    System.out.println("위도: " + latitude);
	                    System.out.println("경도: " + longitude);
	                    System.out.println("전화번호: " + phoneno);
	                    System.out.println("사진 ID: " + photoid);
	                    System.out.println("일반 이미지 경로: " + imgpath);
	                    System.out.println("-------------------------");
	                }
	            } else {
	                System.out.println("GET 요청 실패: " + responseCode);
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
	    }
}
