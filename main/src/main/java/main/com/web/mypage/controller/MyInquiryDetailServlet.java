package main.com.web.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import main.com.web.member.dto.Member;
import main.com.web.mypage.service.MyPageService;
import main.com.web.qna.dto.Inquiry;

/**
 * Servlet implementation class MyInquiryDetailServlet
 */
@WebServlet("/mypage/myInquiryDetail")
public class MyInquiryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyInquiryDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("member");
		if (loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/member/loginPage"); // 로그인 페이지로 리디렉션
			return;
		}
		int inquiryNo = Integer.parseInt(request.getParameter("inquiry"));
		Inquiry i = new MyPageService().selectInquiryByNo(inquiryNo);
		request.setAttribute("myInquiry", i);
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/myInquiryDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
