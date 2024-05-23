package main.com.web.pay.model.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import static main.com.web.common.JDBCTemplate.*;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import main.com.web.pay.model.dao.PaymentDao;
import main.com.web.reservation.dao.ReservationDao;
import main.com.web.reservation.dto.Reserve;

public class PaymentService {

	 private static final String IAMPORT_API_KEY = "4618247488373851"; //내 API Key
	    private static final String IAMPORT_API_SECRET = "ou0o1JEwZY0nbMY8EzeUkr4mYpuR2qQhDYAaobi1gPEj70uP6jKTr5GqfF0NQyEt30Q2arW1heit3qE0"; //내 API Secret
	    private PaymentDao paymentDao;
	    private static ReservationDao reserveDao = new ReservationDao(); 

	    public PaymentService() {
	        this.paymentDao = new PaymentDao();
	    }

	    //결제 완료 페이지에 방금 결제한 예약정보
	    public static Reserve selectMyReserve(int reserveNo) {
	    	Connection conn = getConnection();
	    	Reserve result = reserveDao.selectMyReserve(conn, reserveNo);
	    	close(conn);
			return result;
		}
	   
	    
	    
//	    public boolean verifyAndSavePayment(String impUid, String merchantUid) throws Exception {
//	    	Connection conn = getConnection();
//	        if (verifyPayment(impUid)) {
//	            int result = dao.savePaymentInfo(conn, impUid, merchantUid);
//	            //DB저장 확인
//	            if(result > 0) {
//	            	commit(conn);
//	            	System.out.println("결제 정보 DB 저장 완료");
//	            }else {
//	            	rollback(conn);
//	            	System.out.println("결제 정보 DB 저장 실패");
//	            }
//	            commit(conn);
//	            return true;
//	        }
//	        rollback(conn);
//	        close(conn);
//	        return false;
//	    }
//
//	    private boolean verifyPayment(String impUid) throws Exception {
//	        String token = getToken();
//
//	        URL url = new URL("https://api.iamport.kr/payments/" + impUid);
//	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	        connection.setRequestMethod("GET");
//	        //HTTP 요청 헤더에 Authorization 속성을 추가하고, 앞서 가져온 토큰을 설정
//	        // 이를 통해 서버에 인증된 요청을 보냄
//	        connection.setRequestProperty("Authorization", token);
//
//	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//	        String inputLine;
//	        StringBuffer content = new StringBuffer();
//	        while ((inputLine = br.readLine()) != null) {
//	            content.append(inputLine);
//	        }
//	        br.close();
//	        connection.disconnect();
//
//	        JsonObject jsonResponse = new Gson().fromJson(content.toString(), JsonObject.class);
//	        JsonObject response = jsonResponse.get("response").getAsJsonObject();
//	        return response != null && response.get("status").getAsString().equals("paid");
//	    }
//
//	    private String getToken() throws Exception {
//	        URL url = new URL("https://api.iamport.kr/users/getToken");
//	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//	        connection.setRequestMethod("POST");
//	        connection.setRequestProperty("Content-Type", "application/json");
//
//	        JsonObject jsonBody = new JsonObject();
//	        jsonBody.addProperty("imp_key", IAMPORT_API_KEY);
//	        jsonBody.addProperty("imp_secret", IAMPORT_API_SECRET);
//
//	        connection.setDoOutput(true);
//	        OutputStream os = connection.getOutputStream();
//	        os.write(jsonBody.toString().getBytes());
//	        os.flush();
//	        os.close();
//
//	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//	        String inputLine;
//	        StringBuffer content = new StringBuffer();
//	        while ((inputLine = in.readLine()) != null) {
//	            content.append(inputLine);
//	        }
//	        in.close();
//	        connection.disconnect();
//
//	        JsonObject jsonResponse = new Gson().fromJson(content.toString(), JsonObject.class);
//	        return jsonResponse.get("response").getAsJsonObject().get("access_token").getAsString();
//	    }
	    
	    public boolean savePaymentInfo(String impUid, String merchantUid, String memberId, int payPrice, String paymentMethod, String status, int hotelNo, int reserveNo) {
	        Connection conn = getConnection();
	        boolean result = false;
	        
	        try {
	            int insertResult = paymentDao.savePaymentInfo(conn, impUid, merchantUid, memberId, payPrice, paymentMethod, status, hotelNo, reserveNo);
	            if (insertResult > 0) {
	                commit(conn);
	                result = true;
	            } else {
	                rollback(conn);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            rollback(conn);
	        } finally {
	            close(conn);
	        }
	        
	        return result;
	    }
	}