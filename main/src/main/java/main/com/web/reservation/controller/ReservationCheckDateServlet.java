package main.com.web.reservation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import main.com.web.reservation.service.ReservationService;
import main.com.web.room.dto.RoomTest;

/**
 * Servlet implementation class ReservationCheckDateServlet
 */
@WebServlet("/reservation/date")
public class ReservationCheckDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationCheckDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String checkInDate = request.getParameter("checkindate"); //
		String checkOutDate = request.getParameter("checkoutdate");
		String roomType = request.getParameter("roomType");
		if(roomType==null) {
			roomType = "Standard";
		}
		System.out.println(checkInDate);
		System.out.println(checkOutDate);
		List<RoomTest> roomList = new ReservationService().selectRoom(roomType);
		response.setContentType("application/json;charset=UTF-8");
		System.out.println(roomList);
		JSONObject jobj = new JSONObject(); //json 객체 생성
		jobj.put("roomList", roomList);
		response.getWriter().print(jobj); // 전달해줌 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
