package main.com.web.room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.room.dto.Room;
import main.com.web.room.service.RoomService;

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

		//(사용자가 선택한 방) db에서 starndardroom에 대한 정보를 가져옴
		String roomType=request.getParameter("room");
		
		request.setAttribute("room", 
				new String[]{"1-ST.png","2-ST.png","3-ST.png","4-ST.png","5-ST.png"});
		
		
		RoomService rs=new RoomService();
		Room room=rs.getARoomService();
		System.out.println(room);
		
		HttpSession session=request.getSession();
		session.setAttribute("rooms",room);
		request.getRequestDispatcher("/WEB-INF/views/rooms/roomdetail.jsp").forward(request,response);
		
		
		
		
		
		
//		//클라이언트가 클릭한 room정보를 가져오기
//		String roomType=request.getParameter("room");
//				
//		//jsp에 보낼 데이터 준비하기 key:value
//		request.setAttribute("room",new String[] {"1-SU.png","2-SU.png","3-SU.png","4-SU.png","5-SU.png"});
//				
//		//jsp에 전달
//		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/rooms/roomdetail.jsp");
//		rd.forward(request, response);
//		
//		//DB에서 데이터 가져오기
//        List<Room> roomList = roomDAO.getAllRooms(); // 예시: Room 객체의 리스트를 가져오는 DAO 메서드 호출
//		
//		//JSP로 데이터 전달
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
