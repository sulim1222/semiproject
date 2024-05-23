<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<% %>   
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>
<%
    String searchType=request.getParameter("searchType");
    String searchKeyword=request.getParameter("searchKeyword");
    List<Member> members=(List<Member>)request.getAttribute("members");
    /* String pageBar=(String)request.getAttribute("pageBar"); */
    String location=(String)request.getAttribute("location");
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
        <span id="title">
        <% if ("서울".equals(location)) { %>
        	Seoul Reservation List
    	<% }else if("부산".equals(location)) { %>
    		Busan Reservation List
    	<% }else if("제주".equals(location)) { %>
    		Jeju Reservation List
    	<% } else{ %>
    		Reservation
    	<% } %>
    	</span>
        <div class="separator"></div>

        <div class="container1">
            <select id="searchType" style="width: 100px; height: 25px;">
                <option value="reserveNo" <%=searchType != null && searchType.equals("reserveNo") ? "selected" : ""%>>예약번호</option>
                <option value="memberName" <%=searchType != null && searchType.equals("memberName") ? "selected" : ""%>>이름</option>
                <option value="roomType" <%=searchType != null && searchType.equals("roomType") ? "selected" : ""%>>객실타입</option>
            </select>

            <div id="search-reserveNo">
                <form action="<%=request.getContextPath()%>/admin/searchMember">
                    <input type="hidden" name="searchType" value="reserveNo">
                    <input type="text" name="searchKeyword" size="25" placeholder="예약번호를 입력하세요">
                    <button type="submit">검색</button>
                </form>
            </div>
            <div id="search-memberName">
                <form action="<%=request.getContextPath()%>/admin/searchMember">
                    <input type="hidden" name="searchType" value="memberName">
                    <input type="text" name="searchKeyword" size="25" placeholder="이름을 입력하세요">
                    
                    <button type="submit">검색</button>
                </form>
            </div>
            <div id="search-roomType">
                <form action="<%=request.getContextPath()%>/admin/searchMember">
                    <input type="hidden" name="searchType" value="roomType"> 
                    <input type="text" name="searchKeyword" size="25" placeholder="객실 타입을 입력하세요">
                    <button type="submit">검색</button>
                </form>
            </div>

            <button type="button" value="신규등록" id="inputnewreserve" onclick="location.assign('<%=request.getContextPath()%>/reserve/inputnewreserve.do')">신규등록</button>
        </div>
        
        <div class="container2">
            <table class="reservationInfo">
                <thead>
                    <tr>
                        <th>Select</th>
                        <th>ReservationNo</th>
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
						    <td><input type="checkbox" class="checkone" onclick="checkOnlyOne(this)"></td>
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
						        <button type="button" onclick="update();">수정</button>
						        <button type="button" onclick="cancel();">삭제</button>
						    </td>
						</tr>
						<% } %>
						<% }else{ %>
	                    <tr>
	                        <td colspan="12">검색 대상을 골라주세요.</td>
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
        const checkboxes = document.querySelectorAll('.checkone');
        checkboxes.forEach((cb) => {
            if (cb !== checkbox) {
                cb.checked = false;
            }
        });
    };
</script>

<style>

	#pageBar{
		border: 1px solid black;
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
        background-color: lightgray; 
        padding: 10px; 
        justify-content: center;
        height: 700px;
        border: 1px solid black;
    }

    .aside ul {
        list-style-type: none; 
        padding: 0; 
        text-align: center;
        margin-top: 50px;
    }

    .aside ul li:nth-child(1) {
        border-bottom: 2px solid black; 
        padding-bottom: 30px; 
    }

    .aside ul li {
        margin-bottom: 30px; 
        font-weight: bold; 
        font-size: 20px;
        letter-spacing: 0.1em;
    }

    .aside ul li a {
        letter-spacing: 0.1em;
        text-decoration: none; 
        color: black;
        display: block; 
        line-height: 1.5; 
    }

    .aside ul li a:hover {
        transition: 1s;
        transform: scale(1.2);
    }

    .main {
        width: 80%; 
        margin-left: 3%; 
        margin-top: 10px;
        display: flex; 
        justify-content: space-between; 
        padding: 10px; 
        border: 1px solid black;
        background-color: lightgray;
        display: block;
    }

    #title {
        font-size: 30px;
        letter-spacing: 0.05em;
        font-weight: bolder;
    }

    .separator {
        border-bottom: 2px solid black;  
    }

    .container1 {
        display: flex; 
        align-items: center;
        padding-top: 10px;
    }

    .searchInput {
        width: 200px; 
        padding: 5px; 
    }

    .container2 {
        margin-bottom: 30px;
    }

    .reservationInfo {
        width: 100%; 
        border-collapse: collapse; 
    }

    .reservationInfo th,
    .reservationInfo td {
        border: 1px solid black; 
        padding: 10px; 
        text-align: center; 
    }

    #inputnewreserve{
        margin-left: Auto;
    }

    div[id^='search-'] {
        display: none;
    }

    .container1 input{
        height : 20px;
    }
</style>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>