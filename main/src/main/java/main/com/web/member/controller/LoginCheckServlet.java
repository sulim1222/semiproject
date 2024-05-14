package main.com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import main.com.web.member.dto.Member;
import main.com.web.member.service.MemberService;

/**
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet("/member/logincheck") //이메일 체크
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String userId = request.getParameter("userId");
	String emailDomain = request.getParameter("emailDomain");
	System.out.println(userId);
	System.out.println(emailDomain);
	System.out.println("통신완료!!!");
	Member m = Member.builder().memberId(userId+emailDomain).build();
	boolean result = new MemberService().duplicateId(m);
	System.out.println(result);
	JSONObject jobj = new JSONObject(); //json객체 생성
	jobj.put("duplicate", result); // key,value 형식
	response.setContentType("application/x-json; charset=utf-8"); // 보낼 방식 
	response.getWriter().print(jobj);	// 보낸방법 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
