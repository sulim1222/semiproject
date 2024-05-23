//package main.com.web.enjoy.controller;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import main.com.web.enjoy.dto.Cafe;
//
///**
// * Servlet implementation class CafeServlet
// */
//@WebServlet("/enjoy/cafe")
//public class CafeServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public CafeServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Cafe cf=Cafe.builder()
//				.cafeNo(Integer.parseInt(request.getParameter("cafeNo")))
//				.cafeName(request.getParameter("cafeName"))
//				.cafeAddress(request.getParameter("cafeAddress"))
//				.cafePhone(request.getParameter("cafePhone"))
//				.cafeTime(request.getParameter("cafeTime"))
//				.cafeImg(request.getParameter("cafeImg"))
//				.cafeVistor(request.getParameter("cafeVistor"))
//				.location(request.getParameter("location"))
//				.category(request.getParameter("category"))
//				.cafeContent(request.getParameter("cafeContent"))
//				.build();
//		
//		
//					
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
