package main.com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.member.service.KakaoService;

/**
 * Servlet implementation class KakaoLoginServlet
 */
@WebServlet("/kakaologin")
public class KakaoLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("체크인");
		String code = request.getParameter("code");
		String email = request.getParameter("email");
		System.out.println(email);
//		String id = request.getParameter("id");
//		System.out.println(code);
//		System.out.println(id);
//		String access_Token ="";
//		String refresh_Token ="";
//		String reqURL ="https://kauth.kakao.com/oauth/token";
		HttpSession session = request.getSession();
		String access_Token = new KakaoService().getAccessToken(code);
		System.out.println("access_Token :"+access_Token);
		/* [출처] 카카오 로그인 API - REST API (2)|작성자 dushui */
		request.setAttribute("session", access_Token); // 세션값 담음 

		request.getRequestDispatcher("/").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
