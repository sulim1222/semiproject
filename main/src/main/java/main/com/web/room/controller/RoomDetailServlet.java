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
public class RoomDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(사용자가 선택한 방) db에서 standardroom에 대한 정보를 가져옴
		String roomType=request.getParameter("room");
		String location=request.getParameter("location");
		//HttpServletRequest, HttpSession, ServletContext
		//setAttribute("key",Object value); -> getAttribute("key");
//		request.setAttribute("room", 
//				new String[]{"1-ST.png","2-ST.png","3-ST.png","4-ST.png","5-ST.png"});
		
		
//		List<Room> rooms=rs.GetAllRoomsService();
		Room room=new RoomService().getRoomDetailService(roomType,location);
		//매개변수가 있는 메소드는 반환값이 있고 그값은 타입이같은 변수에 넣어서 쓸수있다
		//변수에 넣지 않는다면 직접 그값이 되어서 딱한번만 사용할수있다(1회용)
//		Room room=rs.getARoomService();
		System.out.println(room);
		
		//사용자가 입력한값을 서버에 가져올땐 겟파라미터("키값");
		//jsp에서 서블릿의 값을가져올땐 getattribute
		
		//jsp에서쓸값을 넣어줄때(화면출력) setatt
		request.setAttribute("room", room);
		//여기서 키값과 발루값을 설정한다 이후에 jsp에서 룸이라는 키 값으로 룸 객체를 받을 수 있
		
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
