package main.com.web.admin.reserve.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.admin.reserve.dto.Member;
import main.com.web.admin.reserve.service.AdminReserveService;

/**
 * Servlet implementation class reserveListLocation
 */
@WebServlet("/reserve/reserveList.do")
public class reserveListLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public reserveListLocation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 String location = request.getParameter("location");
	        if (location == null || location.isEmpty()) {
	            location = "Seoul";
	        }
	        request.setAttribute("location", location);

		int cPage = 0;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		int numPerpage = 0;
		try {
			numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
		} catch (NumberFormatException e) {
			numPerpage = 10;
		}

		StringBuffer pageBar = new StringBuffer();
		int totalData = new AdminReserveService().selectMemberCountByLocation(location);
		int totalPage = (int) Math.ceil((double) totalData / numPerpage);
		int pageBarSize = 5;
		if (totalPage < cPage) {
			request.setAttribute("prevPage", request.getRequestURI());
		}
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;

		if (pageNo == 1) {
			pageBar.append("<span>[이전]</span>");
		} else {
			pageBar.append("<a href='" + request.getRequestURI() 
			 				+ "?location=" + location
							+ "&cPage=" + (pageNo - 1) 
							+ "&numPerpage="
							+ numPerpage + "'>[이전]</a>");
		}

		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (pageNo == cPage) {
				pageBar.append("<span>" + pageNo + "</span>");
			} else {
				pageBar.append("<a href='" 
								+ request.getRequestURI() 
								+ "?location=" + location
								+ "&cPage=" + (pageNo)
								+ "&numPerpage="+ numPerpage 
								+ "'>" + pageNo + "</a>");
			}
			pageNo++;
		}

		if (pageNo > totalPage) {
			pageBar.append("<span>[다음]</span>");
		} else {
			pageBar.append("<a href='" 
							+ request.getRequestURI() 
							+ "?location=" + location
							+ "&cPage=" + (pageNo) 
							+ "&numPerpage=" + numPerpage 
							+ "'>[다음]</a>");
		}

		request.setAttribute("pageBar", pageBar);

		List<Member> members = new AdminReserveService().selectMemberByLocation(location, cPage, numPerpage);
		request.setAttribute("members", members);
		request.getRequestDispatcher("/WEB-INF/views/member/reserveList.jsp").forward(request, response);
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


