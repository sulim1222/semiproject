package main.com.web.room.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.room.dto.Room;
import main.com.web.room.service.RoomService;


@WebServlet("/room/standardroom.do")
//서블릿 하나로 쿼리스트링을 통해 여러페이지를 나타낼수있는듯?

public class RoomDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RoomDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(사용자가 선택한 방) db에서 standardroom에 대한 정보를 가져옴 ->??
		String roomType=request.getParameter("room");
		// 여기서 room, location은 = key
		// view(jsp)에서 사용자의 선택으로 가져오는거니까 jsp페이지에서 room location이 뭔지보자
		// model(dto)에서 db Room테이블을 자바로 가져오기위해 틀을 만들어놨음
		// view(jsp)에서 키값을 room으로 가진 Room 객체를 만들었음
		
		String location=request.getParameter("location"); // 여기서 location = key
		
		
		//HttpServletRequest, HttpSession, ServletContext
		//setAttribute("key",Object value); -> getAttribute("key");
//		request.setAttribute("room", 
//				new String[]{"1-ST.png","2-ST.png","3-ST.png","4-ST.png","5-ST.png"});
//		if(roomType.equals("Deluxe")) {
//			int count = 6;
//			List<RoomImages> imgs = new RoomService().getImgs(count); 
//		}
		
//		List<Room> rooms=rs.GetAllRoomsService();
		Room room=new RoomService().getRoomDetailService(roomType,location);
		//매개변수가 있는 메소드는 반환값이 있고 그값은 타입이같은 변수에 넣어서 쓸수있다
		//변수에 넣지 않는다면 직접 그값이 되어서 딱한번만 사용할수있다(1회용)
//		Room room=rs.getARoomService();
		System.out.println(room);
		
		//사용자가 입력한값을 서버에 가져올땐 겟파라미터("키값");
		//jsp에서 서블릿의 값을가져올땐 getattribute
		
		//jsp에서쓸값을 넣어줄때(화면출력) setattribute
		request.setAttribute("room", room);
		//여기서 키값과 발루값을 설정한다 이후에 jsp에서 룸이라는 키 값으로 룸 객체를 받을 수 있다..
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rooms/roomdetail.jsp");
        dispatcher.forward(request, response);
		//아래꺼 이번에 주석했음
//		request.getRequestDispatcher("/WEB-INF/views/rooms/roomdetail.jsp")
//		.forward(request,response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
