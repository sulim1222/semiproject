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
import main.com.web.reservationdetail.dto.ReservationDetail;

@WebServlet("/pay/paycompletePage")
public class PayCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentService paymentService = new PaymentService();

	public PayCompleteController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reserveNo = request.getParameter("reserveNo");
		System.out.println(reserveNo);
		if (reserveNo != null && !reserveNo.isEmpty()) {
			Reserve myReserve = paymentService.selectMyReserve(reserveNo);
			Payment payment = paymentService.selectPayment(reserveNo);
			ReservationDetail myReserveDetail = paymentService.selectMyReserveDetail(reserveNo);
			
			
			request.setAttribute("myReserve", myReserve);
			request.setAttribute("payment", payment);
			request.setAttribute("myReserveDetail", myReserveDetail);
			System.out.println(myReserve);
			System.out.println(payment);
			System.out.println(myReserveDetail);
			request.getRequestDispatcher("/WEB-INF/views/pay/payComplete.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/errorPage");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}
}
