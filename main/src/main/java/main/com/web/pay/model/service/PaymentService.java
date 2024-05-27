package main.com.web.pay.model.service;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.commit;
import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;

import main.com.web.member.dto.Member;
import main.com.web.pay.model.dao.PaymentDao;
import main.com.web.pay.model.dto.Payment;
import main.com.web.reservation.dao.ReservationDao;
import main.com.web.reservation.dto.Reserve;
import main.com.web.reservationdetail.dto.ReservationDetail;
import main.com.web.room.dto.Room;

public class PaymentService {

	 private static final String IAMPORT_API_KEY = "4618247488373851"; //내 API Key
	 private static final String IAMPORT_API_SECRET = "ou0o1JEwZY0nbMY8EzeUkr4mYpuR2qQhDYAaobi1gPEj70uP6jKTr5GqfF0NQyEt30Q2arW1heit3qE0"; //내 API Secret
	 private static PaymentDao paymentDao;
	 private static ReservationDao reserveDao;
	 
	 public PaymentService() {
		 paymentDao = new PaymentDao();
	 }
	 


	    public Reserve selectMyReserve(String reserveNo) {
	    	Connection conn = getConnection();
	    	Reserve result =  new ReservationDao().selectMyReserve(conn, reserveNo);
	    	close(conn);
			return result;
		}
	   
	    
	    public boolean savePaymentInfo(String impUid, String merchantUid, int payPrice, String paymentMethod, String status, String location, String reserveNo) {
	        Connection conn = getConnection();
	        boolean result = false;
	        
	        try {
	            int insertResult = paymentDao.savePaymentInfo(conn, impUid, merchantUid, payPrice, paymentMethod, status, location, reserveNo);
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
		public boolean insertReservationDetail(String reserveNo, Room r, String roomRequest, String bedType, String car, int roomPeopleNo) {
			Connection conn = getConnection();
			boolean result = false;
			try {
				int insertResult = paymentDao.insertReservationDetail(conn, reserveNo, r, roomRequest, bedType, car, roomPeopleNo);
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

		public Payment selectPayment(String reserveNo) {
			Connection conn = getConnection();
			Payment payment = paymentDao.selectPayment(conn, reserveNo); 
			close(conn);
			return payment;
		}


		public Room selectRoom(int roomNo) {
			Connection conn = getConnection();
			Room room = paymentDao.selectRoom(conn, roomNo);
			close(conn);
			return room;
		}
		
		public ReservationDetail selectMyReserveDetail(String reserveNo) {
			Connection conn = getConnection();
			ReservationDetail r = paymentDao.selectReserveDetail(conn, reserveNo);
			close(conn);
			return r;
		}


		 public String insertReservationInfo(Member m, Room r, String checkInDate, String checkOutDate, int roomPeopleNo, String roomRequest, String bedType) {
		        Connection conn = getConnection();
		        String reserveNo = null;
		        int result = paymentDao.insertReservationInfo(conn, m, r, checkInDate, checkOutDate, roomPeopleNo, roomRequest, bedType);
		        if (result > 0) {
		            commit(conn);
		            reserveNo = paymentDao.getLatestReserveNo(conn);  // 방금 저장된 예약번호 가져오기
		        } else {
		            rollback(conn);
		        }
		        close(conn);
		        return reserveNo;
		    }






	    //결제 위변조 검증
		
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
	}