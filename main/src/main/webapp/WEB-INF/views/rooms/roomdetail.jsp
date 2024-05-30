<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,main.com.web.room.dao.RoomDao" %>     

<%
	Room room=(Room)request.getAttribute("room");
//Room 테이블이자 객체를 쓰기위해 선언 근데 여기 키값은 왜 또 room인걸까,,?그냥 임의로 지정한값?아닌데 키는 중요한값이자나
		
%>
<%@ page import="main.com.web.room.dto.*" %>
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
        
           <%
          	
              for(RoomImages image : room.getRoomImages()){
           %>
            <div class="slide fade">
                <img src="<%=request.getContextPath()%>/images/rooms/<%=image.getRoomAttachName() %>" alt="각 룸이미지" width="100%" height="600px">
            </div>
            <%} %>
            <div class="sharing"><h6>URL</h6>
                <button type="button" class="sharing-container" onclick="copyToClipboard();">
                    <i class="fa-regular fa-copy" style="color: #ffffff;"></i>
                </button>
            </div>
            
            <!-- Previous and next buttons -->
            <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
            <a class="next" onclick="plusSlides(1)">&#10095;</a>
        </div>
        <div class="standard-detail">
            <div class="icon-container">
                <i class="fa-solid fa-bed fa-4x" style="color: #c4c4c4;"></i>
                <i class="fa-solid fa-expand fa-4x" style="color: #c4c4c4;"></i>
                <i class="fa-solid fa-person fa-4x" style="color: #c4c4c4;"></i>
                <i class="fa-regular fa-clock fa-4x" style="color: #c4c4c4;"></i>
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
                <p>1~4명</p>
                <p>3PM/12AM</p>
            </div>
        </div>
		
		
		
 <div class="content-container">
 	
     <div class="section_container">
           <div class="left_section">
        <div class="standard-info">
            <div class="standard-title">객실 안내<br></div>
            <div><% 
			    String roomInfo = room.getRoomInfo();
			    String[] roomParts = roomInfo.split(",");
			    for(String b : roomParts) {
			        out.println(b + "<br>"); // 각 부분을 HTML의 <br> 태그로 구분해서 출력
			    }
			    %>
            </div>
        </div>

        <div class="standard-facilty">
            <div class="standard-facility-title">객실 시설</div>
            <ul class="facility-list">
                <%for(String a : room.getRoomAmenity().split(",")) {%>
                	<li><%=a %></li>
                <%} %>
            </ul>
        </div>
        

        <div class="service">
            <div class="service-title">고객 서비스</div>
               <ul class="service-list">
             	   <%for(String service : room.getHotelService().split(",")){ %>
                   	 <li><%=service %></li>
                   <%} %>
              </ul>
        	</div>
        </div>
       

       <div class="right_section">
           <div class="notice_container">
               <h2 class="notice_line">NOTICE</h2><br>
               <div>

                   <ul>
                       <li>객실 및 영업장을 포함한 호텔 내 모든 구역은<br>금연으로 지정되어 있습니다.</li><br><br>
                       <li>흡연을 하실 경우 30만원의 정비요금이 별도 부과됩니다.</li><br>

                       <li><b style="color: #b99664;">SAVE EARTH, SAVE JEJU!</b><br>메소드에서는 일회용품 줄이기 캠페인을 진행합니다.
                       </li><br>

                       <li>대나무 칫솔을 미니바에서 구입하실 수 있습니다.</li>
                       <br><br><br><br>

                       <h4>취소 / 변경 및 노쇼 안내</h4><br>
                       <li>체크인 1일 전 18시까지는 위약금 없이
                           취소 및 변경 가능합니다.</li><br>

                       <li>18시 이후 취소 또는 변경 및 노쇼(No-show)발생 시<br>
                           최초 1일 숙박 요금의 100%가 위약금으로
                           부과됩니다.</li><br>

                       <li>기후변화 또는 천재지변 등의 불가항력적 상황으로<br>
                           호텔의 정상적 영업이 불가한 경우,
                           <br>호텔의 고지 (MMS, 홈페이지 게재 등)에 의하여 예약이
                           취소되거나<br>호텔 업장 이용이 제한될 수 있습니다.
                       </li><br><br><br>

                   </ul><br>

                   <h4 style="border-top: 1px solid rgb(0, 0, 0, 0.1);"><br>예약 문의</h4><br>
                   +082.002.0202<br>

                   method_jeju@method.com<br><br>


                   <div class="reservation_btn">
                       <input type="button" id="room_reservation_btn" value="RESERVATION">

                   </div>



                     </div>
                 </div>
             </div>
         </div>

         <div class="etc_container">
             <div class="etc_text">
                 <div class="line">
                     <div> ENJOY METHOD!</div>
                     객실을 떠나도, 즐거움이 가득해요🤍
                 </div>
             </div>
             <div class="etc_img_container">
                 <div class="etc_img"><img src="<%=request.getContextPath()%>/images/rooms/picnic.png" alt="더미1">
                 </div>
                 <div class="etc_img"><img src="<%=request.getContextPath()%>/images/rooms/lounge.jpeg" alt="더미1">
                 </div>
                 <div class="etc_img"><img src="<%=request.getContextPath()%>/images/rooms/cake.png" alt="더미1">
                 </div>
                 <div class="etc_img"><img src="<%=request.getContextPath()%>/images/rooms/grill.png" alt="더미1">
                 </div>
                 <div class="etc_img"><img src="<%=request.getContextPath()%>/images/rooms/pool.jpg" alt="더미1">
                 </div>
             </div>
             <div class="etc_info_container">
                 <div class="etc_info">
                     <div><b>우리, 피크닉 할까요?</b></div>
                     <div class="img_content">귀여운 토끼 친구들이 뛰어노는<br>메서드 잔디정원 위<br>매트를 펼쳐보아요.</div>
                 </div>
                 <div class="etc_info">
                     <div><b>컴포터블 스페이스</b></div>
                     <div class="img_content">아늑한 분위기의 라운지에서<br>훌륭한 조식과 티,<br>칵테일을 즐겨보세요.</div>
                 </div>
                 <div class="etc_info">
                     <div><b>메서드 라운지</b></div>
                     <div class="img_content">이상한 나라의 메서드♥<br>새콤달콤한 케이크와 함께라면<br>즐거운 일이 가득할거에요.</div>
                 </div>
                 <div class="etc_info">
                     <div><b>Grill & Bar</b></div>
                     <div class="img_content">여럿이 함께 즐길 수 있는<br>스페셜한 요리와<br>잊지못할 추억을 만들어보세요!</div>
                 </div>
                 <div class="etc_info">
                     <div><b>국내 최고의 인피니티풀</b></div>
                     <div class="img_content">푸른 바다를 향해<br>끊임없이 이어지는 듯한<br>광활한 오션을 만끽하세요.</div>
                 </div>
             </div>
             <div class="upbtn-container">
                 <button type="button" class="up-btn" onclick="goUp();"><i class="fa-solid fa-arrow-up"></i></button>
             </div>
         </div>



     </div>

<script>
	const a = document.getElementsByClassName("background")[0];
	console.dir(a);
	const $div = document.createElement("div");
	$div.innerText="<%=room.getRoomType()%>"; //내용
	console.log($div);
	a.appendChild($div);
	$div.style.marginTop = "63px";
	$div.style.display = "grid";
	$div.style.placeItems = "center"; // 수평 및 수직 중앙 정렬
	$div.style.fontSize = "45px";
	$div.style.fontWeight = "bolder";

</script>
	
   </section>
   


<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<script src="<%= request.getContextPath()%>/js/room.js"></script>
