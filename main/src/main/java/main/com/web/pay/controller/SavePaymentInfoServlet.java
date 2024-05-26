package main.com.web.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import main.com.web.member.dto.Member;
import main.com.web.pay.model.service.PaymentService;
import main.com.web.reservation.dto.Reserve;
import main.com.web.room.dto.Room;

@WebServlet("/pay/savepayment")
public class SavePaymentInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentService paymentService = new PaymentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 및 응답 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 요청 본문을 읽어와서 JSON 객체로 변환
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());

        // 세션에서 회원 정보 가져오기
        HttpSession session = request.getSession();
        Member m = (Member) session.getAttribute("member");

        // JSON에서 필요한 데이터 추출
        String impUid = json.getString("imp_uid");
        String merchantUid = json.getString("merchant_uid");
        int payPrice = json.getInt("payPrice");
        String paymentMethod = json.getString("paymentMethod");
        String status = json.getString("status");
        String location = json.getString("location");
        String bedType = json.getString("bedType");
        int roomPeopleNo = json.getInt("roomPeopleNo");
        int roomNo = (json.getInt("roomNo"));
        String roomRequest = json.optString("roomRequest", "");
        String car = json.optString("car", "");
        
        String checkInDate = json.getString("checkInDate");
        String checkOutDate = json.getString("checkOutDate");
        
//        System.out.println("impUid: " + impUid);
//        System.out.println("merchantUid: " + merchantUid);
//        System.out.println("payPrice: " + payPrice);
//        System.out.println("paymentMethod: " + paymentMethod);
//        System.out.println("status: " + status);
//        System.out.println("location: " + location);
//        System.out.println("checkInDate: " + checkInDate); 
//        System.out.println("checkOutDate: " + checkOutDate);
//        System.out.println("roomPeopleNo: " + roomPeopleNo);
//        System.out.println("roomNo: " + roomNo);

        // Room 객체 조회
        Room r = paymentService.selectRoom(roomNo);

        // 예약 정보 삽입 및 예약 번호 가져오기
        String reserveNo = paymentService.insertReservationInfo(m, r, checkInDate, checkOutDate, roomPeopleNo, roomRequest, bedType);

        // 결제 정보 저장
        boolean result = paymentService.savePaymentInfo(impUid, merchantUid, payPrice, paymentMethod, status, location, reserveNo);
        
        // 방금 저장한 예약 가져오기
        Reserve myReserve = paymentService.selectMyReserve(reserveNo);
        
        // 예약 상세 저장 
        boolean result2 = paymentService.insertReservationDetail(reserveNo, r, roomRequest, bedType, car, roomPeopleNo);
        
        
        // 결과에 따라 응답 처리 
        if (result) {
            response.sendRedirect(request.getContextPath() + "/pay/paycompletePage?reserveNo=" + reserveNo);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "결제 정보 저장에 실패하였습니다.");
        }
    }
}
