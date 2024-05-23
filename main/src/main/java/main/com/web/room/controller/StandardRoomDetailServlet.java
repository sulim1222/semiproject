package main.com.web.room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.room.dto.Room;
import main.com.web.room.service.RoomService;

/**
 * Servlet implementation class StandardRoomDetailServlet
 */
@WebServlet("/room/standardroom.do")
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
		//(사용자가 선택한 방) db에서 starndardroom에 대한 정보를 가져옴
		String roomType=request.getParameter("room");
		
		//HttpServletRequest, HttpSession, ServletContext
		//setAttribute("key",Object value); -> getAttribute("key");
		request.setAttribute("room", 
				new String[]{"1-ST.png","2-ST.png","3-ST.png","4-ST.png","5-ST.png"});
		
		RoomService rs=new RoomService();
		Room room=rs.getARoomService();
		System.out.println(room);
		
		HttpSession session=request.getSession();
		session.setAttribute("rooms",room);
		request.getRequestDispatcher("/WEB-INF/views/rooms/roomdetail.jsp").forward(request,response);
		
		
//		RoomService roomsr = new RoomService();
//	     	List<Room> rooms=roomsr.GetAllRooms();
//
//	        request.setAttribute("rooms", rooms);
//	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rooms/roomdetail.jsp");
//	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
