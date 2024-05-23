<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//Room r=request.getAttribute("room");
	String[] images=(String[])request.getAttribute("room");
	
%>
    <%@ page import="java.util.List,main.com.web.room.dao.RoomDao" %>     

<%
	Room room=(Room)session.getAttribute("rooms");
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
           <%for(String image:images){ %>
            <div class="slide fade">
                <img src=""<%=request.getContextPath() %>/images/rooms/<%=image %>" alt="첫번째사진" width="100%" height="600px">
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
                <%-- <p><%=room.getRoomPeopleNo() %>명</p> --%> <!-- 여기서 에러나요! -->
                <p>3PM/12AM</p>
            </div>
        </div>

        <div class="standard-info">
            <div class="standard-title">실용적인 스탠다드 룸<br></div>
            <div><%=room.getRoomInfo() %>모던함 & 안락함이 공존하는 메서드의 공간을 선사합니다.</div>
            <div>천장을 넓힌 최신식 확장 인테리어 구조로</div>
            <div>쾌적함과 여유로움을 만끽하실 수 있습니다.</div>
            <div>따사로운 햇살이 고객님의 멋진 하루를 응원합니다.</div>
        </div>

        <div class="standard-facilty">
            <div class="standard-facility-title">시설안내</div>
            <ul class="facility-list">
                <li><%=room.getRoomAmenity() %>미니바</li>
                <li>무선인터넷</li>
                <li>220v전원</li>
                <li>목욕가운/실내화</li>
                <li>천연허브티/커피</li>
                <li>조말론 배스세트</li>
                <li>헤어드라이어</li>
                <li>smart tv/무선인터넷</li>
                <li>디지털 금고</li>
                <li>로라스타 스팀다리미</li>
            </ul>
        </div>
        

        <div class="service">
            <div class="service-title">고객 서비스
                <div class="service-inform">
                    <div>고품격 세탁서비스 제공</div>
                    <div>생일케이크 및 와인 (사전예약)</div>
                    <div>주차서비스</div>
                    <div>초고속 무료 WIFI</div>
                    <div>유아용품/노트북 렌탈</div>
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
