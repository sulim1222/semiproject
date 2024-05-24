package main.com.web.pay.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import main.com.web.pay.model.service.PaymentService;

@WebServlet("/pay/savepayment")
public class SavePaymentInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PaymentService paymentService = new PaymentService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }
        JSONObject json = new JSONObject(sb.toString());

        String impUid = json.getString("imp_uid");
        String merchantUid = json.getString("merchant_uid");
        String memberId = json.getString("memberId");
        int payPrice = json.getInt("payPrice");
        String paymentMethod = json.getString("paymentMethod");
        String status = json.getString("status");
        int hotelNo = json.getInt("hotelNo");
        int reserveNo = json.getInt("reserveNo");

        boolean result = paymentService.savePaymentInfo(impUid, merchantUid, memberId, payPrice, paymentMethod, status, hotelNo, reserveNo);
        request.setAttribute("reserveNo", reserveNo);
        
        
        if (result) {
            // 결제 저장 성공 시 예약완료페이지로
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pay/paycompletePage");
            request.setAttribute("paymentInfo", json.toString()); // 또는 필요한 데이터를 개별적으로 설정
            dispatcher.forward(request, response);
        } else {
            // 결제 저장 실패 시 다른 페이지로 리디렉션
            response.sendRedirect(request.getContextPath() + "/errorPage.jsp?message=결제 정보를 저장하는 데 실패했습니다.");
        }
        
    }
}

