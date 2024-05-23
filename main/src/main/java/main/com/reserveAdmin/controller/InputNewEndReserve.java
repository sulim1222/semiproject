//package com.reserve.controller;
//
//import java.io.IOException;
//import java.sql.Date;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.reserve.dto.Member;
//
///**
// * Servlet implementation class InputNewEndReserve
// */
//@WebServlet(name="newreserve",urlPatterns="/reserve/inputnewendreserve.do")
//public class InputNewEndReserve extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public InputNewEndReserve() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String memberId=request.getParameter("memberId");
//		int reserveNo=Integer.parseInt(request.getParameter("reserveNo"));
//		String memberName=request.getParameter("memberName");
//		String roomType=request.getParameter("roomType");
//		String checkInDate=request.getParameter("checkInDate");
//		Date checkOutDate=request.getParameter("checkOutDate");
//		String memberPhone=request.getParameter("memberPhone");
//		int payPrice=Integer.parseInt(request.getParameter("payPrice"));
//		int roomPeopleNo=Integer.parseInt(request.getParameter("roomPeopleNo"));
//		String memberAddress=request.getParameter("memberAddress");
//		Date reserveDate=request.getParameter("reserveDate");
//		
//		Member m= Member.builder()
//				.memberId(memberId)
//				.reserveNo(reserveNo)
//				.memberName(memberName)
//				.roomType(roomType)
//				.checkInDate(checkInDate)
//				.checkOutDate(checkOutDate)
//				.memberPhone(memberPhone)
//				.payPrice(payPrice)
//				.roomPeopleNo(roomPeopleNo)
//				.memberAddress(memberAddress)
//				.reserveDate(reserveDate)
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
//package main;


