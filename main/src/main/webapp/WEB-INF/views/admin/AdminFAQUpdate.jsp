<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>
<%@ page import="main.com.web.qna.dto.FAQ" %>
<%
    FAQ faq = (FAQ) request.getAttribute("faq");
%>


<section id="update-container">
    <h2>FAQ 수정</h2>
    <form action="<%=request.getContextPath()%>/admin/updateFAQ" method="post">
        <input type="hidden" name="faqAllNo" value="<%= faq.getFaqAllNo() %>">
        
        <label for="faqCategory">카테고리:</label>
        <select id="faqCategory" name="faqCategory">
            <option value="PAY" <%= "PAY".equals(faq.getFaqCategory()) ? "selected" : "" %>>PAY</option>
            <option value="ETC" <%= "ETC".equals(faq.getFaqCategory()) ? "selected" : "" %>>ETC</option>
        </select><br>
        
        <label for="faqTitle">제목:</label>
        <input type="text" id="faqTitle" name="faqTitle" value="<%= faq.getFaqTitle() %>" required><br>
        
        <label for="faqContent">내용:</label>
        <textarea id="faqContent" name="faqContent" required><%= faq.getFaqContent() %></textarea><br>
        
        <label for="location">위치:</label>
        <input type="text" id="location" name="location" value="<%= faq.getLocation() %>" required><br>
        
        <button type="submit">수정</button>
    </form>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>

<style>
    body {
        background-color: lightgray;
        margin: 0;
        padding: 0;
    }
    #update-container {
        width: 60%;
        margin: 2rem auto;
        background-color: white;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
    }
    h2 {
        background-color: lightgray;
        margin: 10px;
        padding: 1rem;
        text-align: center;
    }
    form {
        padding: 1rem;
    }
    label {
        display: block;
        margin-bottom: 0.5rem;
        font-weight: bold;
    }
    input[type="text"],
    select,
    textarea {
        width: calc(100% - 20px);
        padding: 0.5rem;
        margin-bottom: 1rem;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    textarea {
        resize: none
