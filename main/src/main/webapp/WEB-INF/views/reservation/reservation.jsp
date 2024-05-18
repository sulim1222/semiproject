<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reservation.css">
 <script src = "<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
    <section class="main-section">
        <div class="main-content">
    <section>
    <div id='calendar'></div>
      <div id="checkdate">
        <div id="checkindate">
          <p id="ck1" name="checkindate">체크인 날짜 : </p>
        </div>
        <div id="checkoutdate">
          <p id="ck2" name="checkoutdate">체크아웃 날짜 : </p>
        </div>
        <div>
          <button id="btn1">확인</button>
          <button id="btn" >초기화</button>
        </div>
      </div>
      <div id="reservation">
        <div id="roomType">
        
        </div>
      </div>
    </section>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js'></script>
<script src='https://cdn.jsdelivr.net/npm/@fullcalendar/google-calendar@6.1.11/index.global.min.js'></script>
 <script src="<%=request.getContextPath()%>/js/reservation.js"></script>
        </div>
    </section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>