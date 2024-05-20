package main.com.web.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.pay.model.dto.Payment;
import org.json.JSONObject;

/**
 * Servlet implementation class PayCompleteServlet
 */
@WebServlet("/pay/savepayment")
public class SavePaymentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // JSON 데이터를 받기 위한 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 파싱
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());

        String impUid = json.getString("imp_uid");
        String merchantUid = json.getString("merchant_uid");

        // 결제 정보를 저장하는 DAO 호출
        Payment payment = new Payment(impUid, merchantUid); /* 추가 결제 정보 */);
        boolean result = paymentDao.savePayment(payment);

        // 결과에 따른 응답 설정
        JSONObject jsonResponse = new JSONObject();
        if (result) {
            jsonResponse.put("success", true);
        } else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "결제 정보를 저장하는 데 실패했습니다.");
        }
        response.getWriter().write(jsonResponse.toString());
    }
}
