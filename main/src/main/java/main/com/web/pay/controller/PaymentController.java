package main.com.web.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.pay.model.service.PaymentService;
import main.com.web.room.dto.Room;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/pay/paymentPage")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private PaymentService paymentService = new PaymentService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("결제페이지 이동");
		String checkInDate = request.getParameter("checkindate"); // 체크인 날짜
		String checkOutDate = request.getParameter("checkoutdate"); // 체크아웃 날짜
		System.out.println("체크인날짜:"+checkInDate);
		System.out.println("체크아웃날짜:"+checkOutDate);
		String roomNo1 = request.getParameter("roomNo"); // roomNo 룸넘버
		String roomPeopleNo1  = request.getParameter("peopelNo");
		String car = request.getParameter("car");
		String bedType = request.getParameter("bedType");
		String roomRequest = request.getParameter("roomRequest");
		int roomNo = Integer.parseInt(roomNo1);
		int roomPeopleNo =Integer.parseInt(roomPeopleNo1);

		Room r = paymentService.selectRoom(roomNo);
		request.setAttribute("room", r);
		
		request.getRequestDispatcher("/WEB-INF/views/pay/payment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
