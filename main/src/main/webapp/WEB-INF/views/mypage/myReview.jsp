<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="main.com.web.review.dto.*, java.util.List" %>
	
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="../css/mypage.css">
<%
	List<Review> reviews = (List<Review>)request.getAttribute("reviews");
%>

<section class="sectionflex">
	<aside class="aside">
		<nav>
			<ul>
				<li>MyPage</li>
				<li><a
					href="<%=request.getContextPath() %>/mypage/myReservationPage">예약/결제
						내역</a></li>
				<li><a
					href="<%=request.getContextPath() %>/mypage/myReviewPage">별점/리뷰
						관리</a></li>
				<li><a href="<%=request.getContextPath() %>/mypage/myInfoPage">개인정보
						수정</a></li>
			</ul>
		</nav>
	</aside>
	<main class="main">
		<span id="title">별점/리뷰 관리</span>
		<div class="separator"></div>
		<div class="container1"></div>
		<div class="container2">
			<table class="reservationInfo">
				<thead>
					<tr>
						<th>ㅇ</th>
						<th>카테고리</th>
						<th>리뷰내용</th>
						
					</tr>
				</thead>
				<tbody>
					<% if(reviews.isEmpty()) { %>
					<tr>
						<td colspan="7">조회된 리뷰가 없습니다.
					</tr>
					<% } else {
						for(Review r : reviews) {%>
					<tr>
						<td><%=r.getReviewNo() %></td>
						<td><%=r.getCategory() %></td>
						<td><%=r.getReviewContent() %></td>
					</tr>
						<%}
					}	%>
				</tbody>
			</table>
		</div>
	</main>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>