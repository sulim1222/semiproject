package main.com.web.room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SuiteRoomDetailServlet
 */
@WebServlet("/room/suiteroom.do")
public class SuiteRoomDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuiteRoomDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트가 클릭한 room정보를 가져오기
		String roomType=request.getParameter("room");
				
		//jsp에 보낼 데이터 준비하기 key:value
		request.setAttribute("room",new String[] {"1-SU.png","2-SU.png","3-SU.png","4-SU.png","5-SU.png"});
				
		//jsp에 전달
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/views/rooms/roomdetail.jsp");
		rd.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
