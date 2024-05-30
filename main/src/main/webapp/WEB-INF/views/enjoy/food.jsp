<!-- cafe.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.com.web.enjoy.dto.Cafe" %>
<%@ page import="main.com.web.member.dto.Member" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cafe.css">

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
    List<Cafe> cafes = (List<Cafe>) request.getAttribute("cafes");
    Member loggedInUser = (Member) session.getAttribute("member"); // 사용자 정보 가져오기
%>

<section class="main-section">
    <div class="main-content">
        <div class="sub-content">
            <ul>
                <li><a href="#" onclick="alert('아직 개발 중입니다.'); return false;">Tour</a></li>
                <li><a href="#" onclick="alert('아직 개발 중입니다.'); return false;">Food</a></li>
                <li><a href="<%=request.getContextPath()%>/enjoy/cafe">Cafe</a></li>
            </ul>
        </div>
        <!-- 카페 리스트 -->
        <div class="cafe-list">
            <% if (cafes != null && !cafes.isEmpty()) {
                for (int i = 0; i < cafes.size(); i++) {
                    Cafe cafe = cafes.get(i);
                    if (i % 2 == 0 && i != 0) { %>
                        </div><div class="cafe-list">
                    <% }
                    %>
                    <div class="cafe" data-id="<%= cafe.getCafeNo() %>" data-name="<%= cafe.getCafeName() %>" 
                         data-details="<%= cafe.getCafeAddress() %>" data-phone="<%= cafe.getCafePhone() %>" 
                         data-time="<%= cafe.getCafeTime() %>">
                        <img class="cafe-img" src="<%= cafe.getCafeImg() %>" alt="<%= cafe.getCafeName() %>">
                        <h2><%= cafe.getCafeName() %></h2>
                        <div class="average-rating">
                            <% int fullStars = (int) cafe.getAverageRating(); %>
                            <% for (int j = 0; j < fullStars; j++) { %>
                                <span class="star">&#9733;</span>
                            <% } %>
                            <% for (int j = fullStars; j < 5; j++) { %>
                                <span class="star">&#9734;</span>
                            <% } %>
                            <!-- 평균별 -->
                            ( <%= String.format("%.1f", cafe.getAverageRating()) %>)
                        </div>
                    </div>
                <% }
            } else { %>
                <p>카페 목록이 없습니다.</p>
            <% } %> 
        </div>
        <div id="pageBar" class="page-bar">
            <%=request.getAttribute("pageBar") %>
        </div>
    </div>
</section>

<!-- 필드에 사용자 정보 추가 -->
<input type="hidden" id="loggedInUserId" value="<%= loggedInUser != null ? loggedInUser.getMemberNo() : "" %>">

<!-- 팝업 -->
<div id="popup" class="popup" style="display:none;">
    <div class="popup-content"> 
        <span class="close">&times;</span>
        <div class="popup-left">
            <img id="popup-image" class="popup-image" src="" alt="Popup Image">
        </div>
        <div class="popup-right">
            <h2 id="popup-title"></h2>
            <p id="popup-details"></p>
            <p id="popup-phone"></p>
            <p id="popup-time"></p>
            <div id="reviews"></div>
            <form id="reviewForm" action="<%=request.getContextPath()%>/enjoy/submitReview" method="post">
                <input type="hidden" name="categoryId" id="categoryId">
                <input type="hidden" name="category" value="CAFE">
                <input type="hidden" name="memberId" value="<%= loggedInUser != null ? loggedInUser.getMemberNo() : "" %>">
                <div class="popup-rating">
                    <span class="star" data-value="1">&#9734;</span>
                    <span class="star" data-value="2">&#9734;</span>
                    <span class="star" data-value="3">&#9734;</span>
                    <span class="star" data-value="4">&#9734;</span>
                    <span class="star" data-value="5">&#9734;</span>
                </div>
                <textarea id="review" name="text" placeholder="별점과 리뷰를 작성해주세요"></textarea>
                <input type="hidden" id="rating" name="rating" value="0">
                <button type="submit">저장</button>
            </form>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/cafe.js"></script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
