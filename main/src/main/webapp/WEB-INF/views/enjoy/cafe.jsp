<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="../css/cafe.css"> 
 <section class="main-section">
        <div class="main-content">
            <div class="sub-content">
                <ul>
                    <li><a href="#">Tour</a></li>
                    <li><a href="#">Restaurant</a></li>
                    <li><a href="#">Cafe</a></li>
                </ul>
            </div>
            <!-- 카페 리스트 -->
            <form action="<%=request.getContextPath()%>enjoy/cafe" method="post">
  
            <div class="restaurant" data-name="강요한" data-details="세부 정보 1">
                <img src="/images/mara.jpeg" alt="Restaurant 1">
                <h2>강요한</h2>
                <div class="rating">
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9734;</span>
                    <span class="star">&#9734;</span>
                </div>
                <p>리뷰: 맛있어요! 좋아요.</p>
                <p>리뷰: 완전 추천해요!</p>
            </div>
            <div class="restaurant" data-name="김동훈" data-details="세부 정보 2">
                <img src="/images/mara.jpeg" alt="Restaurant 2">
                <h2>김동훈</h2>
                <div class="rating">
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9734;</span>
                </div>
                <p>리뷰: 최고예요!</p>
                <p>리뷰: 완전 추천해요!</p>
            </div>
            <div class="restaurant" data-name="임성욱" data-details="세부 정보 3">
                <img src="/images/mara.jpeg" alt="Restaurant 3">
                <h2>임성욱</h2>
                <div class="rating">
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9734;</span>
                    <span class="star">&#9734;</span>
                </div>
                <p>리뷰: 맛있어요! 괜찮아요.</p>
                <p>리뷰: 완전 추천해요!</p>
            </div>
            <div class="restaurant" data-name="김해진" data-details="세부 정보 4">
                <img src="/images/mara.jpeg" alt="Restaurant 4">
                <h2>김해진</h2>
                <div class="rating">
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                </div>
                <p>리뷰: 정말 맛있어요!</p>
                <p>리뷰: 완전 추천해요!</p>
            </div>
            <div class="restaurant" data-name="유선정" data-details="세부 정보 5">
                <img src="/images/mara.jpeg" alt="Restaurant 5">
                <h2>유선정</h2>
                <div class="rating">
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9734;</span>
                    <span class="star">&#9734;</span>
                    <span class="star">&#9734;</span>
                </div>
                <p>리뷰: 괜찮아요.</p>
                <p>리뷰: 완전 추천해요!</p>
            </div>
            <div class="restaurant" data-name="유병승" data-details="세부 정보 6">
                <img src="/images/mara.jpeg" alt="Restaurant 6">
                <h2>유병승</h2>
                <div class="rating">
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                    <span class="star">&#9733;</span>
                </div>
                <p>리뷰: 완전 추천해요!</p>
                <p>리뷰: 완전 추천해요!</p>
            </div>
       </form>
            
            
            <div class="paging">
                <span>1</span>
                <span>2</span>
                <span>3</span>
                <span>4</span>
                <span>5</span>
            </div>
        </div>
    </section>



<script src="<%=request.getContextPath()%>/js/cafe.js"></script> <!-- javascript 내용 -->
<%@ include file="../common/footer.jsp"%>