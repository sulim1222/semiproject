package main.com.web.admin.reserve.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.reserve.dto.Member;
import main.com.web.admin.reserve.service.AdminReserveService;

/**
 * Servlet implementation class SerarchMemberKeyword
 */
@WebServlet("/admin/searchMember")
public class SerarchMemberKeyword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SerarchMemberKeyword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage=1;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			
		}
		int numPerpage=10;
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerpage"));
		}catch(NumberFormatException e) {}
				
		
		String type = request.getParameter("searchType");
		String keyword = request.getParameter("searchKeyword");
		String location = request.getParameter("location"); 
		
		
		
		StringBuffer pageBar=new StringBuffer();
		
		int totalData=new AdminReserveService().searchMemberCount(type,keyword);
		int totalPage=(int)Math.ceil(((double)totalData/numPerpage));
		int pageBarSize=5;
		if(totalPage<cPage) {
			request.setAttribute("prevPage", request.getRequestURI());
		}
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo==1) {
			pageBar.append("<span>[이전]</span>");
		}else {
			pageBar.append("<a href='"
							+request.getRequestURI()
							+"?location="+location
							+"&cPage="+(pageNo-1)
							+"&numPerpage="+numPerpage
							+"'>[이전]</a>");
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar.append("<span>"+pageNo+"</span>");
			}else {
				pageBar.append("<a href='"
								+request.getRequestURI()
								+"?location="+location
								+"?cPage="+(pageNo)
								+"&numPerpage="+numPerpage
								+"'>"+pageNo+"</a>");
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar.append("<span>[다음]</span>");
		}else {
			pageBar.append("<a href='"
							+request.getRequestURI()
							+"?location="+location
							+"?cPage="+(pageNo)
							+"&numPerpage="+numPerpage
							+"'>[다음]</a>");
		}
		
		
		
		
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("location", location);
		
		request.getRequestDispatcher("/WEB-INF/views/member/reserveList.jsp").forward(request,response);
	
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
