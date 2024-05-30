package main.com.web.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.common.exception.MyPageError;
import main.com.web.member.dto.Member;
import main.com.web.mypage.service.MyPageService;
import main.com.web.reservation.dto.Reserve;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/mypage/myReservationPage")
public class MyReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReservationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("마이페이지(예약/결제 내역) 이동");
		
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("member");
		

		if (loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/member/loginPage"); // 로그인 페이지로 리디렉션
			return;
		}
		
		int cPage = 1;
		try {
		    cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
		}

		int numPerPage = 5;
		// PageBar 만들기
		StringBuffer pageBar = new StringBuffer();
		int totalData = new MyPageService().selectReservationCount();
		int totalPage = (int) Math.ceil((double) totalData / numPerPage);

		if (totalPage < cPage) {
		    request.setAttribute("prevPage", request.getRequestURI());
		    throw new MyPageError("잘못된 페이지 번호입니다.");
		}

		int pageBarSize = 5;
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;

		// pageBar html
		if (pageNo == 1) {
		    pageBar.append("<span>[이전]</span>");
		} else {
		    pageBar.append("<a href='" + request.getRequestURI()
		        + "?cPage=" + (pageNo - 1) + "'>[이전]</a>");
		}

		while (!(pageNo > pageEnd || pageNo > totalPage)) {
		    if (pageNo == cPage) {
		        pageBar.append("<span>" + pageNo + "</span>");
		    } else {
		        pageBar.append("<a href='" + request.getRequestURI()
		            + "?cPage=" + pageNo + "'>" + pageNo + "</a>");
		    }
		    pageNo++;
		}

		if (pageNo > totalPage) {
		    pageBar.append("<span>[다음]</span>");
		} else {
		    pageBar.append("<a href='" + request.getRequestURI()
		        + "?cPage=" + pageNo + "'>[다음]</a>");
		}

		request.setAttribute("pageBar", pageBar.toString());

		// 해당 페이지의 데이터 가져오기
		String loginMemberId = loginMember.getMemberId();
		List<Reserve> reservations = new MyPageService().selectMyReservation(loginMemberId, cPage, numPerPage);
		request.setAttribute("reservations", reservations);

		request.getRequestDispatcher("/WEB-INF/views/mypage/myReservation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
