package main.com.web.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.pay.model.service.PaymentService;
import main.com.web.reservation.dto.Reserve;

/**
 * Servlet implementation class PayComplete
 */
@WebServlet("/pay/paycompletePage")
public class PayCompleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayCompleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("결제완료 페이지 이동");
		int reserveNo = (int)request.getAttribute("reserveNo");
		Reserve myReserve = new PaymentService().selectMyReserve(reserveNo);
		request.setAttribute("myReserve", myReserve);
		
		request.getRequestDispatcher("/WEB-INF/views/pay/payComplete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
