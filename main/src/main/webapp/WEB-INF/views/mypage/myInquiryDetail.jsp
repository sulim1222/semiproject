<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="main.com.web.qna.dto.Inquiry"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
Inquiry i = (Inquiry) request.getAttribute("myInquiry");
Member loginMember = (Member) session.getAttribute("member");
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/myinquiry.css">



<section class="sectionflex">
	<aside class="aside">
		<nav>
			<ul>
				<li>
					<h3>MyPage</h3>
					<hr>
				</li>
				<li><a
					href="<%=request.getContextPath()%>/mypage/myReservationPage">예약/결제
						내역</a></li>
				<li><a href="<%=request.getContextPath()%>/mypage/myReviewPage">내가 작성한 리뷰</a></li>
				<li><a
					href="<%=request.getContextPath()%>/mypage/myInquiryListPage">문의
						내역</a></li>
				<li><a href="<%=request.getContextPath()%>/mypage/myInfoPage">개인정보
						수정</a></li>
			</ul>
		</nav>
	</aside>
	<main class="main">
		<span id="title">문의 내역</span> <br>
		<div class="separator"></div>
		<div class="container1">
			<table class="inquiry-table">
				<tbody>
					<tr>
						<th>분류</th>
						<td><%=i.getInquiryType()%></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><%=i.getTitle()%></td>
					</tr>
					<tr>
						<th>문의사항</th>
						<td><%=i.getContent()%></td>
					</tr>
					<tr>
						<th>답변</th>
						<td>
							<%
							if (i.getAnswer() != null && i.getIsAnswered().equals("Y")) {
							%> <%=i.getAnswer()%>
							<%
							} else {
							%> <b>답변 준비중입니다. 신속히 답변해드리겠습니다.</b> <%}%>
						</td>
					</tr>
				</tbody>
			</table>
			<%-- <button class="custom-button" onclick="location.href='<%=request.getContextPath()%>/mypage/deleteInquiry?inquiryNo=<%=i.getOnToOneInquiryId() %>'">삭제</button> --%>
		</div>
		<button class="custom-button"
			onclick="location.href='<%=request.getContextPath()%>/mypage/myInquiryListPage'">목록</button>
	</main>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>