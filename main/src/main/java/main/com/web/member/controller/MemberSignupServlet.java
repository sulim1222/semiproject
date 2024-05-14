package main.com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.member.dto.Member;
import main.com.web.member.service.MemberService;

/**
 * Servlet implementation class MemberSignupServlet
 */
@WebServlet("/member/signup")
public class MemberSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userId =request.getParameter("userId");
		String emailDomain =request.getParameter("emailDomain");
		String memberPwd =request.getParameter("memberPwd");
		String memberName =request.getParameter("memberName");
		String memberCheckNo =request.getParameter("memberCheckNo");
		String memberCheckNoEnd =request.getParameter("memberCheckNoEnd");
		String phone =request.getParameter("phone");
		String phone1 =request.getParameter("phone1");
		String phone2 =request.getParameter("phone2");
		String memberId = userId+emailDomain;
		String memberPhone = phone+"-"+phone1+"-"+phone2;
		memberCheckNo = memberCheckNo+""+memberCheckNoEnd;
		Member member = Member.builder().memberId(memberId).memberPwd(memberPwd).memberName(memberName).memberPhone(memberPhone).memberCheckNo(memberCheckNo).build();
		System.out.print(member.toString());
		int result = new MemberService().memberEnroll(member);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/member/loginPage");
		}
	}

}
