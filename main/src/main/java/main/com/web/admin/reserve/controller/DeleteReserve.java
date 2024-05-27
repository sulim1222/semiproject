package main.com.web.admin.reserve.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.reserve.service.AdminReserveService;

/**
 * Servlet implementation class DeleteReserve
 */
@WebServlet("/reserve/deleteReserve.do")
public class DeleteReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReserve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 	String reserveNo = request.getParameter("reserveNo");

	        AdminReserveService service = new AdminReserveService();

	        int result = service.deleteReserve(reserveNo);

	        String msg="", again="";
			if(result>0) {
				msg="삭제가 완료되었습니다..";
				again="/reserve/reserveupdate.do";
			}else {
				msg="삭제에 실패했습니다..";
				again="/reserve/reserveupdate.do";
			}
			
			request.setAttribute("msg",msg);
			request.setAttribute("again", again);
			
			request.getRequestDispatcher("/WEB-INF/views/common/adminmsg.jsp").forward(request,response);
	    
        
    }
		
		
		
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
