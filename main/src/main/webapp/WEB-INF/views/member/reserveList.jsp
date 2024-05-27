<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 

<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>
<%
    String searchType=request.getParameter("searchType");
    String searchKeyword=request.getParameter("searchKeyword");
    List<Member> members=(List<Member>)request.getAttribute("members");
    String location=(String)request.getAttribute("location");
    Member mem=(Member) request.getAttribute("member");
   
%>



<section class="sectionflex">
    <aside class="aside">
        <nav>
            <ul>
                <li>Reservations</li>
                <li><a href="<%=request.getContextPath()%>/reserve/reserveList.do?location=서울">Seoul</a></li>
                <li><a href="<%=request.getContextPath()%>/reserve/reserveList.do?location=부산">Busan</a></li>
                <li><a href="<%=request.getContextPath()%>/reserve/reserveList.do?location=제주">Jeju</a></li>
            </ul> 
        </nav>
    </aside>

    <main class="main">
    	<div id="titleContainer">
	    	<div id="title">
	        <% if ("서울".equals(location)) { %>
	        	Seoul Reservation List
	    	<% }else if("부산".equals(location)) { %>
	    		Busan Reservation List
	    	<% }else if("제주".equals(location)) { %>
	    		Jeju Reservation List
	    	<% } else{ %>
	    		Reservation
	    	<% } %>
	    	</div>
	    	<div id="btn5">
	    		<button class="btn5" onclick="location.assign('<%=request.getContextPath()%>/')">
					<img src="<%=request.getContextPath()%>/imges/admin/homepage.jpg" >
				</button>
	    	</div>
    	</div>
    	
        <div class="separator"></div>

        <div class="container1">
            <select id="searchType">
                <option value="reserveNo" <%=searchType != null && searchType.equals("reserveNo") ? "selected" : ""%>>예약번호</option>
                <option value="memberName" <%=searchType != null && searchType.equals("memberName") ? "selected" : ""%>>이름</option>
                <option value="roomType" <%=searchType != null && searchType.equals("roomType") ? "selected" : ""%>>객실타입</option>
            </select>

            <div id="search-reserveNo">
                <form id="searchForm" action="<%=request.getContextPath()%>/admin/searchMember">
                    <input type="hidden" name="searchType" value="reserveNo">
                    <input type="text" name="searchKeyword"  placeholder="예약번호를 입력하세요">
                    <button type="submit" class="btn2" >검색</button>
                </form>
            </div>
            <div id="search-memberName">
                <form id="searchForm" action="<%=request.getContextPath()%>/admin/searchMember">
                    <input type="hidden" name="searchType" value="memberName">
                    <input type="text" name="searchKeyword"  placeholder="이름을 입력하세요">
                    <button type="submit" class="btn2" >검색</button>
                </form>
            </div>
            <div id="search-roomType">
                <form id="searchForm" action="<%=request.getContextPath()%>/admin/searchMember">
                    <input type="hidden" name="searchType" value="roomType">
                    <input type="text" name="searchKeyword"  placeholder="객실 타입을 입력하세요">
                    <button type="submit" class="btn2" >검색</button>
                </form>
            </div>

            <button type="button" class="btn1" value="신규등록" id="inputnewreserve" onclick="location.assign('<%=request.getContextPath()%>/reserve/insertnewreserve.do')">신규등록</button>
        </div>
        
        <div class="container2">
            <table class="reservationInfo">
                <thead>
                    <tr>
                        <th>Select</th>
                        <th>ReserveNo</th>
                        <th>Location</th>
                        <th>Name</th>
                        <th>RoomType</th>
                        <th>BedType</th>
                        <th>CheckInDate</th>
                        <th>CheckOutDate</th>
                        <th>Price</th>
                        <th>PhoneNumber</th>
                        <th>ReserveDate</th>
                        <th>Update / Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (members.size() > 0) {
				    for (Member m : members) { %>
						<tr>
						    <td><input type="checkbox" id="checkone" onclick="checkOnlyOne(this)"></td>
						    <td><%=m.getReserveNo() %></td>
						    <td><%=m.getLocation() %></td>
						    <td><%=m.getMemberName() %></td>
						    <td><%=m.getRoomType() %></td>
						    <td><%=m.getBedType() %></td>
						    <td><%=m.getCheckInDate() %></td>
						    <td><%=m.getCheckOutDate() %></td>
						    <td><%=m.getPayPrice() %></td>
						    <td><%=m.getMemberPhone() %></td>
						    <td><%=m.getReserveDate() %></td>
						    <td>
						        <input type="button" class="btn" value="수정" onclick="location.assign('<%=request.getContextPath()%>/reserve/updatereserve.do?reserveNo=<%=m.getReserveNo()%>')">
						        <form action="<%=request.getContextPath()%>/reserve/deleteReserve.do" method="post" style="display:inline;">
                                    <input type="hidden" name="reserveNo" value="<%=m.getReserveNo()%>">
                                    <input type="submit" class="btn" value="삭제" onclick="return confirm('정말 삭제하시겠습니까?')">
                                </form>
						    </td>
						</tr>
						<% } %>
						<% }else{ %>
	                    <tr>
	                        <td colspan="12">조회 내용이 없어요.</td>
	                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <div id="pageBar">
        	<%=request.getAttribute("pageBar")%>
        </div>
    </main>
</section>

<script>
	
	
    $(() => {
        $("#searchType").change();
    })
    $("#searchType").change(e => {
        const type = e.target.value;
        $(e.target).parent().find("div[id^='search-']").hide();
        $("#search-" + type).show();
    });
    
    const checkOnlyOne = (checkbox) => {
        const checkboxes = document.querySelectorAll('#checkone');
        checkboxes.forEach((cb) => {
            if (cb !== checkbox) {
                cb.checked = false;
            }
        });
    };
    
    <%-- document.addEventListener("dateDelete", function() {
        const deleteButton = document.querySelectorAll("#deleteReserve");

        deleteButton.forEach(button => {
            button.addEventListener("click", function() {
                const reserveNo = this.getAttribute("reserveNoData");

                if (confirm("정말 삭제하시겠습니까?")) {
                    fetch('<%= request.getContextPath() %>/reserve/deleteReserve.do', {
                        method: "POST",
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            alert("삭제되었습니다.");
                            location.reload();
                        } else {
                            alert("삭제에 실패했습니다.");
                        }
                    })
                }
            });
        });
    }); --%>
    
    
    
</script>

    <%-- const deleteReserve = (reserveNo) => {
        if (confirm("정말 삭제하시겠습니까?")) {
            location.assign(`<%=request.getContextPath()%>/reserve/deletereserve.do?reserveNo=${reserveNo}`);
        }
    }; --%>
<style>
	#btn5 {
    margin-left: auto;
}
	
	#pageBar{
		display: flex;
    	justify-content: center;
    	align-items: center;
	}
	#pageBar>*{
		margin-left:5px;
		margin-right:5px;
		font-weight: bold;
		text-decoration:none;
		color: black;
	}

    .sectionflex {
        display: flex; 
    }

    .aside {
	    margin-top: 10px;
	    width: 15%;
	    background-color: #f7f7f7; /* 부드러운 회색 배경 */
	    padding: 20px;
	    height: 800px;
	    border: 1px solid #e0e0e0; /* 연한 회색 테두리 */
	    border-radius: 10px; /* 둥근 테두리 */
	    box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1); /* 약한 그림자 */
	    overflow-y: auto; /* 필요 시 스크롤 생성 */
	}
	
	.aside ul {
	    list-style-type: none;
	    padding: 0;
	    text-align: center;
	    margin-top: 30px; /* 위쪽 여백 축소 */
	}
	
	.aside ul li:nth-child(1) {
	    margin-bottom: 40px; /* 간격 축소 */
	    font-size: 30px; /* 글꼴 크기 축소 */
	    transition: all 0.3s ease-in-out; /* 부드러운 변화 효과 */
	    font-weight: bold;
	}
	
	.aside ul li{
	    margin-bottom: 20px; /* 간격 축소 */
	    font-size: 22px; /* 글꼴 크기 축소 */
	    transition: all 0.3s ease-in-out; /* 부드러운 변화 효과 */
	}
	
	.aside ul li a {
	    text-decoration: none;
	    color: #333; /* 어두운 글꼴 색상 */
	    display: block;
	    padding: 10px 0;
	    border-radius: 5px; /* 둥근 모서리 */
	}
	
	.aside ul li a:hover {
	    background-color: #e0e0e0; /* 마우스 호버 시 배경색 변경 */
	    color: #222; /* 어두운 글꼴 색상 */
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

    #title {
	    font-size: 24px;
	    font-weight: bold;
	    color: #333;
	    margin-bottom: 20px;
	}
	
	.container1 select,
	.container1 input[type="text"] {
	    width: 100px;
	    height: 25px;
	    margin-right: 10px; /* 필요에 따라 간격 조절 가능 */
	}

	#search-reserveNo,
	#search-memberName,
	#search-roomType {
	    display: none; /* 기본적으로 숨겨둡니다. */
	}
    .separator {
	    height: 1px;
	    background-color: #ccc;
	    margin-bottom: 20px;
	}

    .container1 {
        display: flex; 
        align-items: center;
        padding-top: 20px;
    }
    

	.container1 select,
	.container1 input[type="text"],
	.container1 .btn2 {
	    margin-right: 10px; /* 요소들 사이의 간격을 조정합니다. */
	}

    .searchInput {
        width: 200px; 
        padding: 5px; 
    }

    .container2 {
        margin-bottom: 30px;
        margin-top: 40px;
    }

    .reservationInfo {
	    width: 100%;
	    border-collapse: collapse;
	    border: 1px solid #ddd; /* 테이블 테두리 */
	}
	
	.reservationInfo th, .reservationInfo td {
	    padding: 13px;
	    text-align: center;
	}
	
	.reservationInfo th {
	    background-color: #f2f2f2; /* 헤더 배경색 */
	    color: #333; /* 헤더 글자색 */
	    border-bottom: 1px solid #ddd; /* 헤더 아래쪽 테두리 */
	}
	
	.reservationInfo td {
	    border-bottom: 1px solid #ddd; /* 셀 아래쪽 테두리 */
	}
	
	.reservationInfo tbody tr:nth-child(even) {
	    background-color: #f9f9f9; /* 짝수 행 배경색 */
	}
	
	.reservationInfo tbody tr:hover {
	    background-color: #f2f2f2; /* 마우스 호버 시 배경색 변경 */
	}

	#searchType {
	    width: 120px;
	    height: 30px;
	    margin-right: 20px;
	}

    .container1 input{
        height : 20px;
    }
    
    #searchForm {
	    display: flex;
	    align-items: center;
	}
    
    #searchForm input[type="text"] {
	    width: 250px;
	    height: 30px;
	    margin-right: 10px;
	    padding: 0 10px;
	    border: 1px solid #ccc;
	}
    
    .btn {
    	padding: 3px 5px;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }
    
    .btn1 {
	    margin-left: auto;
	    width: 100px;
	    height: 34px;
	    background-color: #28a745;
	    color: #fff;
	    border: none;
	    border-radius: 4px;
	    cursor: pointer;
	    float: right;
	}
    
    .btn2 {
	    width: 60px;
	    height: 30px;
	    background-color: black;
	    color: #fff;
	    border: none;
	    border-radius: 4px;
	    cursor: pointer;
	}
	
	.btn5 {
	    background-color: transparent; /* 버튼 배경색 제거 */
	    border: none; /* 버튼 테두리 제거 */
	    cursor: pointer; /* 커서 모양 변경 */
	    padding: 0; /* 버튼 안쪽 여백 제거 */
	    
	}

	.btn5 img {
	    display: block; /* 이미지 블록 요소로 표시 */
	    width: 30px; /* 이미지 너비 설정 */
	    height: 30px; /* 이미지 높이 설정 */
	    object-fit: cover; /* 이미지가 컨테이너에 맞게 조절되도록 설정 */
	}
	
	#titleContainer {
    	display: flex;
    	justify-content: space-between;
    	align-items: center;
	}
	
    
</style>

<%@ include file="/WEB-INF/views/common/adminfooter.jsp"%>