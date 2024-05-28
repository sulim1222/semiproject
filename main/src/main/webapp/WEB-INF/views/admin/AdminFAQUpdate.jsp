<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>
<%@ page import="main.com.web.qna.dto.FAQ" %>
<%
    FAQ faq = (FAQ) request.getAttribute("faq");
%>
<section class="sectionflex">
    <aside class="aside">
        <!-- 추가적인 네비게이션 내용 -->
    </aside>

    <main class="main">
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
    </main>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
