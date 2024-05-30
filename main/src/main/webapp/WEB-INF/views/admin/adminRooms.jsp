<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,main.com.web.room.dto.*" %>
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>
<%
	String location=(String)request.getAttribute("location");
	List<Room> rooms=(List<Room>)request.getAttribute("rooms");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/room_admin.css">
    <style>
        .aside {
            margin-top: 10px;
            width: 15%;
            background-color: #f7f7f7;
            /* 부드러운 회색 배경 */
            padding: 20px;
            height: 800px;
            border: 1px solid #e0e0e0;
            /* 연한 회색 테두리 */
            border-radius: 10px;
            /* 둥근 테두리 */
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
            /* 약한 그림자 */
            overflow-y: auto;
            /* 필요 시 스크롤 생성 */
        }

        .aside ul {
            list-style-type: none;
            padding: 0;
            text-align: center;
            margin-top: 30px;
            /* 위쪽 여백 축소 */
        }

        .aside ul li:nth-child(1) {
            margin-bottom: 40px;
            /* 간격 축소 */
            font-size: 30px;
            /* 글꼴 크기 축소 */
            transition: all 0.3s ease-in-out;
            /* 부드러운 변화 효과 */
            font-weight: bold;
        }

        .aside ul li {
            margin-bottom: 20px;
            /* 간격 축소 */
            font-size: 22px;
            /* 글꼴 크기 축소 */
            transition: all 0.3s ease-in-out;
            /* 부드러운 변화 효과 */
        }

        .sectionflex {
            display: flex;
        }

        .aside ul li a {
            text-decoration: none;
            color: #333;
            /* 어두운 글꼴 색상 */
            display: block;
            padding: 10px 0;
            border-radius: 5px;
            /* 둥근 모서리 */
        }

        .main {
            width: 80%;
            margin-left: 3%;
            margin-top: 10px;
            display: flex;
            justify-content: space-between;
            padding: 10px;
            border: 1px solid black;
            background-color: #fff;
            display: block;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        .aside ul li a:hover {
            background-color: #e0e0e0;
            /* 마우스 호버 시 배경색 변경 */
            color: #222;
            /* 어두운 글꼴 색상 */
        }

        header {
            background-color: #f7f7f7;
            /* 부드러운 회색 배경 */
            height: 120px;
            position: relative;
            padding: 10px 0;
            border: 1px solid #e0e0e0;
            /* 연한 회색 테두리 */
            align-items: center;
            width: 100%;
            justify-content: space-between;
            display: flex;
        }


        #menu {
            margin-right: 40px;
            /* 오른쪽 여백 추가 */
        }

        #menu ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            display: flex;
            gap: 50px;
            margin-top: 50px;
        }

        #menu ul li a {
            text-decoration: none;
            color: black;
            font-size: 20px;
            font-weight: bold;
            /* 글꼴을 두껍게 만듭니다. */
            display: block;
            line-height: 1.5;
        }

        #menu ul li a:hover {
            transition: 0.3s;
            /* 변화가 더 빨리 일어나도록 합니다. */
            transform: scale(1.1);
            /* 약간 작게 변경합니다. */
        }

        #logo {
            margin-left: 4.5%;
        }
        
        
        
        
    </style>
    <%-- <aside class="aside">
        <nav>
            <ul>
                <li>Rooms</li>
                <li><a href="<%=request.getContextPath()%>/reserve/reserveList.do?location=서울">Seoul</a></li>
                <li><a href="<%=request.getContextPath()%>/reserve/reserveList.do?location=부산">Busan</a></li>
                <li><a href="<%=request.getContextPath()%>/reserve/reserveList.do?location=제주">Jeju</a></li>
            </ul> 
        </nav>
    </aside> --%>

    	<section class="room-section">
        <div class="room-container">
            <aside class="aside">
                <nav>
                    <ul>
                        <li>Rooms</li>
                        <li><a href="">Seoul</a></li>
                        <li><a href="">Busan</a></li>
                        <li><a href="">Jeju</a></li>
                    </ul>
                </nav>
            </aside>
            <div class="roomtable-container">
                <div class="room-admin-container">
                    <div class="room-admin-title">룸 이미지
                    </div>
                    <div class="room-admin-title">시설 안내
                    </div>
                    <div class="room-admin-title">서비스 안내
                    </div>
                </div>

				<%
				 if(!rooms.isEmpty()){
					for(Room r: rooms){
						String typeKor="";
						switch(r.getRoomType()){
							case "Standard":typeKor="스탠다드룸";break;
							case "Deluxe":typeKor="디럭스룸";break;
							case "Suite":typeKor="스위트룸";break;						
					}
				%>
				<form action="<%=request.getContextPath() %>/admin/roomupdateend.do" method="post" enctype="multipart-formdata">
				<input type="hidden" name="roomno" value="<%=r.getRoomNo()%>">
                <div class="room-admin-standard">
                    <div class="standard-admin-container">
                        <h4><b><%=typeKor%> (<%=r.getRoomType() %>)</b></h4>
                        <%if(r.getRoomImages().size()>0){
                        	%>
                        	<div id="imagecontainer" style="display:flex;justify-content:space-between;">
                        	<%for(RoomImages ri:r.getRoomImages()){%>
                        		<img src="<%=request.getContextPath() %>/images/rooms/<%=ri.getRoomAttachName()%>" style="width:18%; height: 120px;">
                      			&nbsp;
                      			<input type="hidden" name="orifile" value="<%=ri.getRoomAttachName()%>">
                      		<%}%>
                        	</div>
                        <%} %>
                            <button id="admin-btn" type="button" onclick="addInputFile(event)">추가</button>
                            <button id="admin-btn" type="button" onclick="delInputFile(event)">삭제</button>
                            <div id="imageUpload-container">
                            </div>
                   
                    </div>
                    <div class="deluxe-admin-container">
                        <div class="left-admin-section">
                        <h4><b>시설/어메니티 변경</b></h4>
                        
                        <input type="checkbox" name=roomAmenity value="SMART TV">SMART TV<br>
                        <input type="checkbox" name="roomAmenity" value="WIFI">WIFI<br>
                        <input type="checkbox" name="roomAmenity" value="커피포트">커피포트<br>
                        <input type="checkbox" name="roomAmenity" value="미니바">미니바<br>
                        <input type="checkbox" name="roomAmenity" value="샤워욕조">샤워욕조<br>
                        <input type="checkbox" name="roomAmenity" value="최첨단 금고">최첨단 금고<br>
                        <input type="checkbox" name="roomAmenity" value="스위스 TEA세트">스위스 TEA세트<br>
                        <input type="checkbox" name="roomAmenity" value="미니건조대">미니건조대<br>
                        <input type="checkbox" name="roomAmenity" value="로라스타 다리미">로라스타 다리미<br>
                        <input type="checkbox" name="roomAmenity" value="아기침대">아기침대<br>
                        
                    </div>
                    </div>
                    <div class="suite-admin-container">
                        <h4><b>서비스 변경</b></h4>
                        <input type="checkbox" name="hotelService" value="과일바구니">과일바구니<br>
                        <input type="checkbox" name="hotelService" value="케이크">케이크<br>
                        <input type="checkbox" name="hotelService" value="최고급 와인">최고급 와인<br>
                        <input type="checkbox" name="hotelService" value="샤워욕조">샤워욕조<br>
                        <input type="checkbox" name="hotelService" value="유아용품">유아용품<br>
                        <input type="checkbox" name="hotelService" value="다이슨세트">다이슨세트<br>
                        <input type="checkbox" name="hotelService" value="노트북(대여)">노트북(대여)<br>
                        <input type="checkbox" name="hotelService" value="세탁서비스">세탁서비스<br>
                        <input type="checkbox" name="hotelService" value="주차서비스">주차서비스<br>
                        <input type="checkbox" name="hotelService" value="사진촬영">사진촬영<br>	
                    </div>
                    <div class="right-admin-section">
                        <button>수정</button>
                    </div>
                </div>
                </form>
                <%}
				} %>
            </div>
        </div>
    </section>
    
    
	<script>
		const addDelFunc=(()=>{
			let count=0;
			return [
				(e)=>{
					if(count<5){
						const inputFile=$("<input>").attr({"type":"file","name":`upFile\${count++}`});
						$(e.target).siblings("div").append(inputFile);
					}else{
						alert("5개 이상 추가할 수 없습니다. :p")
					}
				},
				(e)=>{
					if(count!=1){
						$(e.target).siblings("div").find("input").last().remove();
						count--;
					}else{
						alert("더이상 삭제할 수 없습니다. :p");
					}
				}
			]
		})();
		const addInputFile=addDelFunc[0];
		const delInputFile=addDelFunc[1];
	</script>
<%@ include file="/WEB-INF/views/common/adminfooter.jsp"%>