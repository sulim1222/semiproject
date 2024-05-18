package main.com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.member.dto.Kakao;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("체크인");
		String code = request.getParameter("code");
		String email = request.getParameter("email");
		System.out.println(code);
		System.out.println(email);
		HttpSession session = request.getSession();
		String access_Token = new KakaoService().getAccessToken(code);
		System.out.println("access_Token :"+access_Token);
		//session.setAttribute("session",access_Token);// 세션값 담음
		//String nickname = request.getParameter("nickname");
		//System.out.println(nickname);
		//request.getRequestDispatcher("/index.jsp").forward(request, response);
		Kakao member = new KakaoService().memberInfo(access_Token);
		
		if (member.getNickname() != null) {
			/*
			 * session.setAttribute("nickname", member.getNickname());
			 * session.setAttribute("access_Token", access_Token);
			 * session.setAttribute("kakaoId", member.getKakaoId());
			 */
		     session.setAttribute("kakaoMember", member);
		   }
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
