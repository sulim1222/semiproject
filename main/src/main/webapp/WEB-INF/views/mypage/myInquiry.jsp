<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="main.com.web.qna.dto.Inquiry, java.util.List" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
	List<Inquiry> inquiries = (List<Inquiry>)request.getAttribute("inquiries");
	int no = 1;
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myinquiry.css">   



<section class="sectionflex">
	<aside class="aside">
		<nav>
			<ul>
				<li>MyPage<hr></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myReservationPage">예약/결제 내역</a></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myReviewPage">별점/리뷰 관리</a></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myInquiryListPage">문의 내역</a></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myInfoPage">개인정보 수정</a></li>
			</ul>
		</nav>
	</aside>
	<main class="main">
		<span id="title">문의 내역</span> <br>
		<div class="separator"></div>
        <div class="container1">
		<table>
			<thead>
				<tr>
					<th>No</th>
					<th>카테고리</th>
					<th>제목</th>
					<th>문의 날짜</th>
				</tr>
			</thead>
			<tbody>
				<%if(inquiries.isEmpty()){ %>
				<tr>
					<td colspan="4">조회된 문의 내역이 없습니다.</td>
				</tr>
				<%} else { 
					for(Inquiry i : inquiries) { %>
				<tr>
					<td><%=no %></td>
					<td><%=i.getInquiryType() %></td>
					<td><%=i.getTitle() %></td>
					<td><%=i.getInquiryDate() %></td>
				</tr>
					<%no++;
					}
				}%>
			</tbody>
		</table>
		</div>

	</main>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>