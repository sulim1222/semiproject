<%@page import="main.com.web.reservationdetail.dto.ReservationDetail"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.com.web.reservation.dto.*, main.com.web.pay.model.dto.*, main.com.web.reservationdetail.dto.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
    Reserve r = (Reserve)request.getAttribute("myReserve");
    ReservationDetail rd = (ReservationDetail)request.getAttribute("myReserveDetail");
    Payment p = (Payment)request.getAttribute("payment");
%>

<link rel="stylesheet" href="../css/payComplete.css">
<section class="reservation-complete">
    <h2><%=r.getMemberName() %>님 감사합니다.<br>고객님의 예약이 완료되었습니다.</h2>

    <div class="reservation-container">
        <h2>예약번호</h2>
        <p class="info"><%=r.getReserveNo() %></p>
        <hr>
        <h2>예약자 정보</h2>
        <p class="info">예약자 성함: <%=r.getMemberName() %></p>
        <p class="info">연락처: <%=r.getMemberPhone() %></p>
        <hr>
        <h2>객실정보</h2>
        <p class="info">객실 타입: <%=r.getRoomType() %></p>
        <p class="info">체크인: <%=r.getCheckInDate() %></p>
        <p class="info">체크아웃: <%=r.getCheckOutDate() %></p>
        <p class="info">투숙 인원: <%=r.getRoomPeopleNo() %></p>
        <hr>
        <h2>결제정보</h2>
        <% if (p != null) { %>
            <p class="info">결제번호: <%=p.getPayNo() %></p>
            <p class="info">결제수단: <%=p.getPayMethod() %></p>
            <p class="info">총 결제금액: <%=p.getPayPrice() %></p>
        <% } %>
        <hr>
        <button class="main-button" onclick="location.href='<%=request.getContextPath() %>'">메인화면으로</button>
    </div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
