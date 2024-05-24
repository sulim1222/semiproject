package main.com.web.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.pay.model.dto.Payment;
import main.com.web.pay.model.service.PaymentService;
import main.com.web.reservation.dto.Reserve;

@WebServlet("/pay/paycompletePage")
public class PayCompleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentService paymentService = new PaymentService();

    public PayCompleteController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reserveNo = request.getParameter("reserveNo");
        if (reserveNo != null) {
            Reserve myReserve = paymentService.selectMyReserve(reserveNo);
            Payment payment = paymentService.selectPayment(reserveNo);

            if (myReserve != null && payment != null) {
                request.setAttribute("myReserve", myReserve);
                request.setAttribute("payment", payment);
                request.getRequestDispatcher("/WEB-INF/views/pay/payComplete.jsp").forward(request, response);
            } else {
                System.out.println("myReserve 또는 payment가 Null");
                response.sendRedirect(request.getContextPath());
            }
        } else {
            System.out.println("reserveNo가 Null입니다.");
            response.sendRedirect(request.getContextPath());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath());
    }
}
