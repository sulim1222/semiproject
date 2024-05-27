<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.com.web.reservation.dto.*, java.util.List" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
    List<Reserve> reservations = (List<Reserve>) request.getAttribute("reservations");
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<link rel="stylesheet" href="../css/mypage.css">
<section class="sectionflex">
    <aside class="aside">
        <nav>
            <ul>
                <li>MyPage</li>
                <li><a href="<%= request.getContextPath() %>/mypage/myReservationPage">예약/결제 내역</a></li>
                <li><a href="<%= request.getContextPath() %>/mypage/myReviewPage">별점/리뷰 관리</a></li>
                <li><a href="<%= request.getContextPath() %>/mypage/myInfoPage">개인정보 수정</a></li>
            </ul>
        </nav>
    </aside>
    <main class="main">
        <span id="title">예약/결제 내역</span>
        <div class="separator"></div>
        <div class="container1">
            <select style="width: 100px; height: 28px;">
                <option value="reserveNo">예약번호</option>
                <option value="memberName">호텔명</option>
            </select>
            <input type="text" placeholder="Search..." class="searchInput">
            <button type="button" class="search">검색</button>
        </div>
        <div class="container2">
            <table class="reservationInfo">
                <thead>
                    <tr>
                        <th>예약번호</th>
                        <th>호텔명</th>
                        <th>객실타입</th>
                        <th>입실날짜</th>
                        <th>퇴실날짜</th>
                        <th>결제금액</th>
                        <th>예약/결제 취소</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (reservations.isEmpty()) { %>
                    <tr>
                        <td colspan="7">조회된 예약정보가 없습니다.</td>
                    </tr>
                    <% } else {
                        for (Reserve r : reservations) { %>
                    <tr>
                        <td><%= r.getReserveNo() %></td>
                        <td><%= r.getLocation() %></td>
                        <td><%= r.getRoomType() %></td>
                        <td><%= r.getCheckInDate() %></td>
                        <td><%= r.getCheckOutDate() %></td>
                        <td><%= r.getPayPrice() %></td>
                        <td>
                            <button type="button"
                                onclick="openModal('<%= r.getReserveNo() %>');">취소</button>
                        </td>
                    </tr>
                    <% }
                    } %>
                </tbody>
            </table>
            <!-- 맨 위로 버튼 -->
<!--             <div class="btn_gotop" style="display:none">
				<a href="javascript:void(0);">TOP</a>
			</div> -->
			
            <!-- 모달창 -->
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    취소 사유를 입력해주세요 <br> 
                    <input type="text" id="cancelReason">
                    <p id="modalMessage">정말 취소하시겠습니까?</p>
                    <div class="modal-buttons">
                        <button type="button" onclick="cancelReservation()">예</button>
                        <button type="button" onclick="closeModal()">아니요</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <!-- 맨위로 버튼 -->
<!-- 	<script>

		$(document).ready(function(){
		//따라다니는 TOP버튼
		$(window).on("scroll touchmove mousewheel DOMMouseScroll", function(e) {
		if($(window).scrollTop() >= 20) $(".btn_gotop").fadeIn();
		else $(".btn_gotop").fadeOut();
		});
		$(".btn_gotop").click(function(){
			$("html,body").animate({ scrollTop: 0 }, "fast");
			});
		});

	</script> -->

	<script>
        var modal = document.getElementById("myModal");
        var span = document.getElementsByClassName("close")[0];
        var myReserveNo = null;

        function openModal(reserveNo) {
            myReserveNo = reserveNo;
            document.getElementById("modalMessage").innerText = "예약번호 " + reserveNo + "를 정말 취소하시겠습니까?";
            modal.style.display = "block";
        }

        function closeModal() {
            modal.style.display = "none";
        }

        span.onclick = function() {
            closeModal();
        }

        window.onclick = function(e) {
            if (e.target == modal) {
                closeModal();
            }
        }

        function cancelReservation() {
            var cancelReason = document.getElementById("cancelReason").value;
            if (!myReserveNo) {
                alert("예약번호를 찾을 수 없습니다.");
                return;
            }

            $.ajax({
                url: "<%=request.getContextPath()%>/mypage/cancelreservation",
                type: "POST",
                data: {
                    reserveNo: myReserveNo,
                    reason: cancelReason
                },
                success: function(response) {
                    alert(response);
                    closeModal(); // 취소 완료 후 모달 창 닫기
                    location.reload(); // 페이지 새로고침
                },
                error: function(xhr, status, error) {
                    alert("예약 취소 중 오류가 발생했습니다: " + error);
                }
            });
        }
    </script>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
