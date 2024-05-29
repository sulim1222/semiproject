package main.com.web.admin.room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.room.dto.Room;
import main.com.web.room.service.RoomService;

/**
 * Servlet implementation class RoomUpdateServlet
 */
@WebServlet("/rooms/roomsupdate.do")
public class RoomUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String location=request.getParameter("location");
		if(location==null) location="제주";
		
		
		List<Room> rooms=new RoomService().getAllRoomsServiceByLocation(location);
		
		
		request.setAttribute("rooms", rooms);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/adminRooms.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
