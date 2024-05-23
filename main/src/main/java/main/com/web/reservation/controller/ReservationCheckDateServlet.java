package main.com.web.reservation.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		List<RoomTest> roomList = null;
		if(roomType==null) {
			roomType = "Standard";
		}
		System.out.println("파라미터에서가져온값"+checkInDate);
		System.out.println("파라미터에서가져온값"+checkOutDate);
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat(); Date date =
		 * java.sql.Date.valueOf(checkInDate); Date date1 =
		 * java.sql.Date.valueOf(checkOutDate); System.out.println(date);
		 * System.out.println(date1);
		 */
		if(checkInDate ==null || checkOutDate ==null) {
		roomList = new ReservationService().selectRoom(roomType);
		}else {
		roomList = new ReservationService().selectRoom(roomType,checkInDate,checkOutDate);
		}
		response.setContentType("application/json;charset=UTF-8");
		System.out.println(roomList);
		System.out.println(roomList.size());
		
		Gson gson = new Gson();
		String json = gson.toJson(roomList);
		response.getWriter().print(json);
		
		//페이지 전환 내용 
	
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
