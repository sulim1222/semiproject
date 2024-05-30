package main.com.web.mypage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.member.dto.Member;
import main.com.web.mypage.service.MyPageService;

/**
 * Servlet implementation class MyInfoServlet
 */
@WebServlet("/mypage/myInfo")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyInfoServlet() {
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

		// 세션에서 가져온 memberId
		String memberId = loginMember.getMemberId();

		// 수정할 회원 정보
		String newName = request.getParameter("name");
		String newPassword = request.getParameter("password");
		String phone = request.getParameter("phone");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		if(phone == null) {
			phone = "010";
		}
		String newPhone = phone + "-" + phone1 + "-" + phone2;

		int result = new MyPageService().updateMember(memberId, newName, newPassword, newPhone);
		String msg, loc;
		if (result > 0) {
			// 회원 정보 수정 성공
			msg = "회원 정보를 수정하였습니다.";
			loc = "/index.jsp"; 
		} else {
			// 회원 정보 수정 실패
			msg = "회원 정보 수정에 실패하였습니다. 다시 시도해주세요!";
			loc = "/mypage/myInfo";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
		.forward(request, response);
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
