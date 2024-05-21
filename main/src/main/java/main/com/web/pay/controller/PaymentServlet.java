package main.com.web.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import main.com.web.pay.model.service.PaymentService;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/pay/payment")
public class PaymentServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//
//
//	
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // JSON 데이터 파싱
//        Gson gson = new Gson();
//        JsonObject jsonResponse = new JsonObject();
//
//        try {
//            JsonObject requestBody = gson.fromJson(request.getReader(), JsonObject.class);
//            String impUid = requestBody.get("imp_uid").getAsString();
//            String merchantUid = requestBody.get("merchant_uid").getAsString();
//
//            // 결제 정보 로그 출력 (개발 단계에서 유용)
//            System.out.println("imp_uid: " + impUid);
//            System.out.println("merchant_uid: " + merchantUid);
//            
//            boolean isPaymentVerified = new PaymentService().verifyAndSavePayment(impUid, merchantUid);
//            
//            if(isPaymentVerified) {
//	            // 결제 검증 및 저장 성공 시
//	            jsonResponse.addProperty("success", true);
//	            jsonResponse.addProperty("message", "결제가 성공적으로 처리되었습니다.");
//            }else {
//            	jsonResponse.addProperty("success", false);
//	            jsonResponse.addProperty("message", "결제 검증에 실패하였습니다.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsonResponse.addProperty("success", false);
//            jsonResponse.addProperty("message", "결제 처리 중 오류가 발생하였습니다: " + e.getMessage());
//        }
//
//        // 응답 설정
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        response.getWriter().print(jsonResponse.toString());
//    }
}
