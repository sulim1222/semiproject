package main.com.web.admin.reserve.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.reserve.dto.Member;
import main.com.web.admin.reserve.service.AdminReserveService;

/**
 * Servlet implementation class UpdateEndReserve
 */
@WebServlet(name="updatereserve",urlPatterns="/reserve/updateendreserve.do")
public class UpdateEndReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEndReserve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reserveNo=request.getParameter("reserveNo");
		String memberId=request.getParameter("memberId");
		int payPrice=0;
		try {
			payPrice=Integer.parseInt(request.getParameter("payPrice"));
		}catch (NumberFormatException e) {
			payPrice=1;
		
		}
		Date reserveDate=Date.valueOf(request.getParameter("reserveDate"));
		String memberName=request.getParameter("memberName");
		String memberPhone=request.getParameter("memberPhone");
		int roomPeopleNo  =0;
		try {
			roomPeopleNo=Integer.parseInt(request.getParameter("roomPeopleNo"));	
		}catch (NumberFormatException e) {
			roomPeopleNo=1;
		}
		String roomType=request.getParameter("roomType");
		String bedType=request.getParameter("bedType");
		String location=request.getParameter("location");
		Date checkInDate=Date.valueOf(request.getParameter("checkInDate"));
		Date checkOutDate=Date.valueOf(request.getParameter("checkOutDate"));
		Date updateReserveDate=Date.valueOf(request.getParameter("updateReserveDate"));
		String memberAddress=request.getParameter("memberAddress");
		if (memberAddress == null) {
            memberAddress = "";
        }
		String requestMemo=request.getParameter("requestMemo");
		
		
		Member m= Member.builder()
				.reserveNo(reserveNo)
				.location(location)
				.memberId(memberId)
				.memberName(memberName)
				.roomType(roomType)
				.bedType(bedType)
				.checkInDate(checkInDate)
				.checkOutDate(checkOutDate)
				.memberPhone(memberPhone)
				.payPrice(payPrice)
				.roomPeopleNo(roomPeopleNo)
				.memberAddress(memberAddress)
				.updateReserveDate(updateReserveDate)
				.reserveDate(reserveDate)
				.requestMemo(requestMemo)
				.build();
	
		int result=new AdminReserveService().updateReserve(m);
		
		String msg="", again="";
		if(result>0) {
			msg="수정이 완료 되었습니다.";
			again="/reserve/updatereserve.do?reserveNo=" +reserveNo;
		}else if(result==0){
			msg="수정된 내용이 없습니다.";
			again="/reserve/updatereserve.do?reserveNo=" +reserveNo;
		}else {
			msg="수정되지 않았습니다. 다시 실행해주세요";
			again="/reserve/reserveupdate.do";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("again", again);
		
		request.getRequestDispatcher("/WEB-INF/views/common/adminmsg.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
