package main.com.web.pay.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.member.dto.Member;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/pay/paymentPage")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		HttpSession session =  request.getSession();
		Member member = (Member)session.getAttribute("member");
		if(member!=null) {
		System.out.println("결제페이지 이동");
		String checkInDate = request.getParameter("checkindate"); // 체크인 날짜
		String checkOutDate = request.getParameter("checkoutdate"); // 체크아웃 날짜
		System.out.println("체크인날짜:"+checkInDate);
		System.out.println("체크아웃날짜:"+checkOutDate);
		String roomNo1 = request.getParameter("roomNo"); // roomNo 룸넘버
		String roomPepleNo1  = request.getParameter("peopelNo");
		String car = request.getParameter("car");
		String bedType = request.getParameter("bedType");
		String Mrequest = request.getParameter("Mrequest");
		int roomNo = Integer.parseInt(roomNo1);
		int roomPepleNo =Integer.parseInt(roomPepleNo1);
		System.out.println(Mrequest);
		System.out.println("인원"+roomPepleNo);
		System.out.println("룸넘버"+roomNo);
		System.out.println("주차여부"+car);
		System.out.println("침대 타입"+bedType);
		request.getRequestDispatcher("/WEB-INF/views/pay/payment.jsp").forward(request, response);
		}
		else {
		 response.sendRedirect("/main/member/loginPage");	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
