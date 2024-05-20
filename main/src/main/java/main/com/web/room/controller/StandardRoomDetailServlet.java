package main.com.web.room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StandardRoomDetailServlet
 */
@WebServlet("/room/detailroom.do")
public class StandardRoomDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StandardRoomDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//db에서 starndardroom에 대한 정보를 가져오기
		String roomName=request.getParameter("room");
		
		//HttpServletRequest, HttpSession, ServletContext
		//setAttribute("key",Object value); -> getAttribute("key");
		request.setAttribute("room", 
				new String[]{"1-ST.png","2-ST.png","3-ST.png","4-ST.png","5-ST.png"});
		
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/rooms/roomdetail.jsp");
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
