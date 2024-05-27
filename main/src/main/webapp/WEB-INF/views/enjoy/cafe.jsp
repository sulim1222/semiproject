<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.com.web.enjoy.dto.Cafe" %>    
<%@ page import="main.com.web.member.dto.Member" %>
<%@ page import="main.com.web.enjoy.dto.Rating" %> 
<%@ page import="main.com.web.review.dto.Review" %> 

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/cafe.css">

<%
    List<Cafe> cafes = (List<Cafe>) request.getAttribute("cafes");
	List<Review> reviwes = (List<Review>) request.getAttribute("reviews");
	List<Rating> ratings = (List<Rating>) request.getAttribute("rating");
    Member loggedInUser = (Member) session.getAttribute("member"); // 사용자 정보 가져오기
%>

<section class="main-section">
    <div class="main-content">
        <div class="sub-content">
            <ul>
                <li><a href="<%=request.getContextPath()%>/enjoy/tour">Tour</a></li>
                <li><a href="<%=request.getContextPath()%>/enjoy/cafe">cafe</a></li>
                <li><a href="<%=request.getContextPath()%>/enjoy/cafe">Cafe</a></li>
            </ul>
        </div>
        <!-- 카페 리스트 -->
          <% if (cafes != null && !cafes.isEmpty()) {
                for (Cafe cafe : cafes) { %>
          
                <div class="cafe" data-id="<%= cafe.getCafeNo() %>" data-name="<%= cafe.getCafeName() %>" data-details="<%= cafe.getCafeAddress() %>">
                    <img style= "width:200px; height:200px;" src="<%= cafe.getCafeImg() %>" alt="<%= cafe.getCafeName() %>">
                    <h2><%= cafe.getCafeName() %></h2>
                    <p>주소: <%= cafe.getCafeAddress() %></p>
                    <p>전화번호: <%= cafe.getCafePhone() %></p>
                    <p>영업시간: <%= cafe.getCafeTime() %></p>
                </div>
            <% }} else { %>
                <p>카페 목록이 없습니다.</p>
            <% } %> 
        </div> 
        
     <%--    
       <!-- 카페 리스트 -->
        <% if (cafes != null && !cafes.isEmpty()) { %>
            <% for (Cafe cafe : cafes) { %>
                <div class="cafe" data-id="<%= cafe.getCafeNo() %>" data-name="<%= cafe.getCafeName() %>" data-details="<%= cafe.getCafeAddress() %>">
                    <img style="width:200px; height:200px;" src="<%= request.getContextPath() + cafe.getCafeImg() %>" alt="<%= cafe.getCafeName() %>">
                    <h2><%= cafe.getCafeName() %></h2>
                    <p>주소: <%= cafe.getCafeAddress() %></p>
                    <p>전화번호: <%= cafe.getCafePhone() %></p>
                    <p>영업시간: <%= cafe.getCafeTime() %></p>
                    <div class="rating">
                        <% 
                        Rating rating = ratings.stream().filter(r -> r.getRatingNo() == cafe.getReviewNo()).findFirst().orElse(null);
                        if (rating != null) {
                            for (int i = 1; i <= 5; i++) { %>
                                <span class="star"><%= i <= rating.getRatingScore() ? "&#9733;" : "&#9734;" %></span>
                            <% } 
                        } %>
                    </div>
                    <% 
                    Review review = reviews.stream().filter(r -> r.getCafeId() == cafe.getCafeNo()).findFirst().orElse(null);
                    if (review != null) { %>
                        <p>리뷰: <%= review.getReviewContent() %></p>
                    <% } %>
                </div>
            <% } %>
        <% } else { %>
            <p>카페 목록이 없습니다.</p>
        <% } %>
    </div> --%>
        
</section>

<!-- 필드에 사용자 정보 추가 -->
<input type="hidden" id="loggedInUserId" value="<%= loggedInUser != null ? loggedInUser.getMemberNo() : "" %>">

<!-- 팝업 -->
<div id="popup" class="popup" style="display:none;">
    <div class="popup-content">
        <span class="close">&times;</span>
        <img id="popup-image" src="" alt="Popup Image">
        <h2 id="popup-title"></h2>
        <p id="popup-details"></p>
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

        <div id="reviews"></div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/cafe.js"></script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

