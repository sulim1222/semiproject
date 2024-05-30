<!-- tour.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.com.web.enjoy.dto.Tour" %>
<%@ page import="main.com.web.member.dto.Member" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/tour.css">

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
    List<Tour> tours = (List<Tour>) request.getAttribute("tours");
    Member loggedInUser = (Member) session.getAttribute("member"); // 사용자 정보 가져오기
%>

<section class="main-section">
    <div class="main-content">
        <div class="sub-content">
            <ul>
                <li><a href="<%=request.getContextPath()%>/enjoy/tour">Tour</a></li>
                <li><a href="<%=request.getContextPath()%>/enjoy/food">Food</a></li>
                <li><a href="<%=request.getContextPath()%>/enjoy/cafe">Cafe</a></li>
            </ul>
        </div>
        <!-- 관광지 리스트 -->
        <div class="tour-list">
            <% if (tours != null && !tours.isEmpty()) {
                for (int i = 0; i < tours.size(); i++) {
                    Tour tour = tours.get(i);
                    if (i % 2 == 0 && i != 0) { %>
                        </div><div class="tour-list">
                    <% }
                    %>
                    <div class="tour" data-id="<%= tour.getTourNo() %>" data-name="<%= tour.getTourName() %>" 
                         data-details="<%= tour.getTourAddress() %>" data-phone="<%= tour.getTourPhone() %>" 
                         data-time="<%= tour.getTourTime() %>">
                        <img class="tour-img" src="<%= tour.getTourImg() %>" alt="<%= tour.getTourName() %>">
                        <h2><%= tour.getTourName() %></h2>
                        <div class="average-rating">
                            <% int fullStars = (int) tour.getAverageRating(); %>
                            <% for (int j = 0; j < fullStars; j++) { %>
                                <span class="star">&#9733;</span>
                            <% } %>
                            <% for (int j = fullStars; j < 5; j++) { %>
                                <span class="star">&#9734;</span>
                            <% } %>
                            <!-- 평균별 -->
                            ( <%= String.format("%.1f", tour.getAverageRating()) %>)
                        </div>
                    </div>
                <% }
            } else { %>
                <p>관광지 목록이 없습니다.</p>
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
                <input type="hidden" name="category" value="TOUR">
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

<script src="<%=request.getContextPath()%>/js/tour.js"></script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
