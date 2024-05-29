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
import main.com.web.qna.dto.Inquiry;

/**
 * Servlet implementation class DeleteInquiry
 */
@WebServlet("/mypage/deleteInquiry")
public class DeleteInquiry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteInquiry() {
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
		Inquiry i = (Inquiry) request.getAttribute("myInquiry");
		int inquiryNo = i.getOnToOneInquiryId();
		boolean isDeleted = new MyPageService().deleteInquiry(inquiryNo);

		try {
			if (isDeleted) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write("문의내역이 정상적으로 삭제되었습니다.");
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"문의내역 삭제를 실패하였습니다." + e.getMessage());
		}
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
