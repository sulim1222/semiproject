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

	        if (result > 0) {
	            response.getWriter().write("Success");
	        } else {
	            response.getWriter().write("Failed");
	        }
	    
        
    }
		
		
		
		


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
