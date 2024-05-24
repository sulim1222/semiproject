<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONArray, org.json.JSONObject" %>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="../css/tour.css"> 
<body>
    <section class="main-section">
        <div class="main-content">
            <div class="sub-content">
                <ul>
                    <li><a href="#">Tour</a></li>
                    <li><a href="#">Restaurant</a></li>
                    <li><a href="#">Cafe</a></li>
                </ul>
            </div>
            <!-- 관광지 정보 리스트 -->
       
            <%
                JSONArray items = (JSONArray) request.getAttribute("items");
                if (items != null && items.length() > 0) {
                    for (int i = 0; i < Math.min(items.length(), 6); i++) {  
                        JSONObject item = items.getJSONObject(i);
                        String title = item.getString("title");
                        String address = item.getString("address");
                        String imageUrl = item.getJSONObject("repPhoto").getJSONObject("photoid").getString("imgpath");
            %>
                        <div class="restaurant" data-name="<%= title %>" data-details="<%= address %>">
                            <img src="<%= imageUrl %>" alt="<%= title %>">
                            <h2><%= title %></h2>
                            <p><%= address %></p>
                        </div>
                        
                        <div class="restaurant" data-name="<%= title %>" data-details="<%= address %>">
                            <img src="<%= imageUrl %>" alt="<%= title %>">
                            <h2><%= title %></h2>
                            <p><%= address %></p>
                        </div>
                        
                        <div class="restaurant" data-name="<%= title %>" data-details="<%= address %>">
                            <img src="<%= imageUrl %>" alt="<%= title %>">
                            <h2><%= title %></h2>
                            <p><%= address %></p>
                        </div>
                        
                        <div class="restaurant" data-name="<%= title %>" data-details="<%= address %>">
                            <img src="<%= imageUrl %>" alt="<%= title %>">
                            <h2><%= title %></h2>
                            <p><%= address %></p>
                        </div>
                        
                           <div class="restaurant" data-name="<%= title %>" data-details="<%= address %>">
                            <img src="<%= imageUrl %>" alt="<%= title %>">
                            <h2><%= title %></h2>
                            <p><%= address %></p>
                        </div>
                        
                            <div class="restaurant" data-name="<%= title %>" data-details="<%= address %>">
                            <img src="<%= imageUrl %>" alt="<%= title %>">
                            <h2><%= title %></h2>
                            <p><%= address %></p>
                        </div>
            <%
                    }
                } else {
            %>
                <p>데이터를 불러오는 중 오류가 발생했습니다.</p>
            <%
                }
            %>
        </div>
    </section>

    <script src="<%=request.getContextPath()%>/js/tourapi.js"></script>

    <%@ include file="/WEB-INF/views/common/footer.jsp"%>

    <div id="popup" class="popup" style="display:none;">
        <div class="popup-content">
            <span class="close" onclick="closePopup()">&times;</span>
            <div class="popup-left">
                <img id="popup-image" src="" alt="Restaurant Image">
            </div>
            <div class="popup-right">
                <h2 id="popup-title"></h2>
                <div id="average-rating">
                    <strong>평균 별점</strong>: <span id="average-stars">0</span>
                </div>
                <p id="popup-details"></p>
                <div class="popup-rating">
                    <span class="star" data-value="1">&#9734;</span>
                    <span class="star" data-value="2">&#9734;</span>
                    <span class="star" data-value="3">&#9734;</span>
                    <span class="star" data-value="4">&#9734;</span>
                    <span class="star" data-value="5">&#9734;</span>
                </div>
                <textarea id="review" placeholder="리뷰를 작성하세요"></textarea>
                <button onclick="submitReview()">리뷰 제출</button>
                <div id="reviews" class="review-list">
                    <!-- 작성된 리뷰들이 여기에 표시됩니다. -->
                </div>
            </div>
        </div>
    </div>
</body>
<%@ include file="../common/footer.jsp"%>
