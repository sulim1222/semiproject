<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="main.com.web.qna.dto.FAQ" %>
<%@ page import="main.com.web.member.dto.Member" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/faqAll.css">

<%
    List<FAQ> faqs = (List<FAQ>) request.getAttribute("faqs");
    Member loggedInUser = (Member) session.getAttribute("member"); // 사용자 정보 가져오기
%>

<section class="main-section">
    <div class="main-content">
        <div class="sub-content">
            <ul>
                <li><a href="<%=request.getContextPath() %>/qna/FAQAll">ALL</a></li>
                <li><a href="<%=request.getContextPath() %>/qna/FAQPay">PAY</a></li>
                <li><a href="<%=request.getContextPath() %>/qna/FAQEtc">ETC</a></li>
            </ul>
        </div>
        <% if (faqs != null && !faqs.isEmpty()) { %>
        <table class="faq-table">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>카테고리</th>
                    <th>제목</th>
                    <th>작성시간</th>
                </tr>
            </thead>
            <tbody>
                <% for (FAQ faq : faqs) { %>
                <tr class="faq-question">
                    <td><%= faq.getFaqAllNo() %></td>
                    <td><%= faq.getFaqCategory() %></td>
                    <td><%= faq.getFaqTitle() %></td>
                    <td><%= faq.getFaqDate() %></td>
                </tr>
                <tr class="faq-answer">
                    <td colspan="4"><%= faq.getFaqContent() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } else { %>
        <p>faq 목록이 없습니다.</p>
        <% } %>
        
        
        
         <div id="pageBar">
    	<%=request.getAttribute("pageBar") %>
   		 </div>
         
    </section>
    
   

<script src="<%=request.getContextPath()%>/js/faq.js"></script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
