package main.com.web.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.mypage.service.MyPageService;

/**
 * Servlet implementation class CancelReservation
 */
@WebServlet("/mypage/cancelreservation")
public class CancelReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reserveNo = request.getParameter("reserveNo");
        
        
        if (reserveNo == null || reserveNo.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid reserve number.");
            return;
        }

        try {
            boolean isCancelled = new MyPageService().cancelReservation(reserveNo);
            if (isCancelled) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("예약이 정상적으로 취소되었습니다.");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "예약 취소에 실패하였습니다.");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while cancelling the reservation: " + e.getMessage());
        }
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
