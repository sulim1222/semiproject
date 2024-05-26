package main.com.web.admin.reserve.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.reserve.dto.Member;
import main.com.web.admin.reserve.service.AdminReserveService;

/**
 * Servlet implementation class UpdateReserve
 */
@WebServlet("/reserve/updatereserve.do")
public class UpdateReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReserve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reserveNo =request.getParameter("reserveNo");
	   
		Member m=new AdminReserveService().selectByReserveNo(reserveNo);
		request.setAttribute("member",m);
		request.getRequestDispatcher("/WEB-INF/views/member/updateReserve.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
