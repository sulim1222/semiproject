<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,main.com.web.room.dao.RoomDao" %>     

<%
	Room room=(Room)request.getAttribute("room");
 // 
%>
<%@ page import="main.com.web.room.dto.Room" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<script src="https://kit.fontawesome.com/2c827c8cca.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%= request.getContextPath()%>/css/room.css">


<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR&display=swap"
    rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&display=swap"
    rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap"
    rel="stylesheet">
<section class="main-section">
        <!-- 여기에 메인 컨텐츠 추가해주세용 -->
        <div class="slideshow-container">
           <%for(String image:room.getRoomUrl().split(",")){ %>
            <div class="slide fade">
                <img src="<%=request.getContextPath() %>/images/rooms/<%=image %>" alt="첫번째사진" width="100%" height="600px">
            </div>
            <%} %>
            <div class="sharing">공유하기
                <button class="sharing-container" style="width: 40px; height: 40px; border: 1px solid red;">
                    <i class="fa-solid fa-share-nodes" style="font-size: 30px; color: #ffffff;"></i>
                </button>
            </div>
            <!-- Previous and next buttons -->
            <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
            <a class="next" onclick="plusSlides(1)">&#10095;</a>
        </div>

        



        <div class="standard-detail">
            <div class="icon-container">
                <i class="fa-solid fa-bed fa-4x" style="color: #b99664;"></i>
                <i class="fa-solid fa-expand fa-4x" style="color: #b99664;"></i>
                <i class="fa-solid fa-person fa-4x" style="color: #b99664;"></i>
                <i class="fa-regular fa-clock fa-4x" style="color: #b99664;"></i>
            </div>
            <div class="icon-title">
                <p>BED TYPE</p>
                <p>ROOM SIZE</p>
                <p>CAPACITY</p>
                <p>CHECK-IN(OUT)</p>
            </div>
            <div class="icon-description">
                <p>SINGLE/DOUBLE</p>
                <p><%=room.getRoomArea() %>m2</p>
<<<<<<< HEAD
                <p>2~4명</p>
=======
                <%-- <p><%=room.getRoomPeopleNo() %>명</p> --%> <!-- 여기서 에러나요! -->
>>>>>>> branch 'dev' of https://github.com/JuniorSinior/synergy.git
                <p>3PM/12AM</p>
            </div>
        </div>

        <div class="standard-info">
            <div class="standard-title">실용적인 스탠다드 룸<br></div>
            <div><%=room.getRoomInfo() %></div>
        </div>

        <div class="standard-facilty">
            <div class="standard-facility-title">시설안내</div>
            <ul class="facility-list">
                <%for(String a : room.getRoomAmenity().split(",")) {%>
                	<li><%=a %></li>
                <%} %>
            </ul>
        </div>
        

        <div class="service">
            <div class="service-title">고객 서비스
                <div class="service-inform">
                	<%for(String service:room.getHotelService().split(",")){ %>
                    <div><%=service %></div>
                   <%} %>
                </div>
            </div>
        </div>

        <div class="reservation_btn">
            <input type="button" id="room_reservation_btn" value="RESERVATION">

        </div>

    <div class=""></div>






	<script src="<%= request.getContextPath()%>/js/room.js"></script>
    </section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
