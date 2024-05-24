package main.com.web.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.member.dto.Member;
import main.com.web.mypage.service.MyPageService;
import main.com.web.review.dto.Review;

/**
 * Servlet implementation class MyReviewController
 */
@WebServlet("/mypage/myReviewPage")
public class MyReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("마이페이지(별점/리뷰 관리) 이동");
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("member");
		
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/loginPage"); // 로그인 페이지로 리디렉션
	        return;
		}
		
		int loginMemberNo = loginMember.getMemberNo();
		
		List<Review> reviews = new MyPageService().selectMyReview(loginMemberNo);
		request.setAttribute("reviews", reviews);	
		request.getRequestDispatcher("/WEB-INF/views/mypage/myReview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
