package main.com.web.reservation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.com.web.reservation.service.ReservationService;
import main.com.web.room.dto.Room;

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
        String checkInDate = request.getParameter("checkindate");
        String checkOutDate = request.getParameter("checkoutdate");
        String roomType = request.getParameter("roomType");

        List<Room> roomList = new ArrayList<Room>();
        System.out.println("파라미터에서 가져온 값: " + checkInDate);
        System.out.println("파라미터에서 가져온 값: " + checkOutDate);
        int currentPage = 0; // 현재 페이지
        int itemsPerPage = 3; // 페이지당 데이터 출력 수
        System.out.println("1" + itemsPerPage);
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageParam);
                System.out.println("2" + itemsPerPage);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("3" + itemsPerPage);
                currentPage = 1;
            }
        } else {
            currentPage = 1; // 페이지 파라미터가 없는 경우 기본값으로 1을 설정합니다.
        }
        int totalData = 0;
        if (roomType == null) {
            roomType = "Standard";
        }
        if(checkInDate == null || checkOutDate == null) { //날짜선택을안한경우
        	roomList = new ReservationService().selectPagedRooms(roomType, currentPage, itemsPerPage);
            totalData = new ReservationService().selectAllCountRoom(roomType); // totalData
            
        }else { //날짜를 선택한경우
        	roomList  = new ReservationService().containerDate(checkInDate,checkOutDate,roomType,currentPage,itemsPerPage);
        	totalData = new ReservationService().containerAllCount(roomType,checkInDate,checkOutDate);
        }
        
        // 페이징 처리
        System.out.println("Current Page: " + currentPage);
        System.out.println("Items Per Page: " + itemsPerPage);
        //날짜가 있는 roomList
        
        System.out.println(totalData);
        System.out.println("Fetched roomList: " + roomList);

        // JSON 응답에 roomList와 totalData 포함
        Map<String, Object> result = new HashMap<>();
        result.put("roomList", roomList);
        result.put("totalData", totalData);
        System.out.println(totalData);
        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new Gson();
        String json = gson.toJson(result);
        response.getWriter().print(json);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
