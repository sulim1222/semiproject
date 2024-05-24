package main.com.web.pay.controller;

import java.io.IOException;

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
        int payPrice = json.getInt("payPrice");
        String paymentMethod = json.getString("paymentMethod");
        String status = json.getString("status");
        String location = json.getString("location");
        String reserveNo = json.getString("reserveNo");

        boolean result = paymentService.savePaymentInfo(impUid, merchantUid, payPrice, paymentMethod, status, location, reserveNo);
        
        if (result) {
            response.sendRedirect(request.getContextPath() + "/pay/paycompletePage?reserveNo=" + reserveNo);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "결제 정보 저장에 실패하였습니다.");
        }
    }
}
