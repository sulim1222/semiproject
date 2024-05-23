<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.com.web.reservation.dto.*, java.util.List" %>
    
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	Reserve r = (Reserve)request.getAttribute("myReserve");
%>

<!-- jQuery -->
<script type="text/javascript"
    src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<link rel="stylesheet" href="../css/payComplete.css">
<section>
	<h1>예약 완료 페이지</h1>
	<h2>OOO님 감사합니다. <br> 고객님의 예약이 완료되었습니다.</h2>

   <div class="reservation-container">
        <hr>
        <h2>예약번호</h2>
        <p><%=r.getReserveNo() %></p>
        <hr>
        <h2>예약자 정보</h2>
        <p>예약자 성함: <%=r.getMemberName() %></p>
        <p>연락처: <%=r.getMemberPhone() %></p>
        <hr>
        <h2>투숙인원</h2>
        <p><%=r.getRoomPeopleNo() %></p>
        <hr>
        <h2>객실정보</h2>
        <p>객실 타입: <%=r.getRoomType() %> </p>
        <p>체크인: <%=r.getCheckInDate() %></p>
        <p>체크아웃: <%=r.getCheckOutDate() %></p>
        <hr>
        <h2>결제정보</h2>
        <p>결제번호: [결제번호]</p>
        <p>결제수단: [결제수단]</p>
        <p>총 결제금액: [총 결제금액]</p>
        <hr>
        <button class="main-button" onclick="location.href='index.html'">메인화면으로</button>
    </div>












</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>