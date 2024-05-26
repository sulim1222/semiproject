<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>

<section class="sectionflex">
    <aside class="aside">
        <!-- 추가적인 네비게이션 내용 -->
    </aside>

    <main class="main">
        <h2>FAQ 신규 등록</h2>
        <form action="<%=request.getContextPath()%>/admin/faqInsert" method="post">
            <label for="faqCategory">카테고리:</label>
            <select id="faqCategory" name="faqCategory">
                <option value="PAY">PAY</option>
                <option value="ETC">ETC</option>
            </select><br>
            
            <label for="faqTitle">제목:</label>
            <input type="text" id="faqTitle" name="faqTitle" required><br>
            
            <label for="faqContent">내용:</label>
            <textarea id="faqContent" name="faqContent" required></textarea><br>
            
            <label for="location">위치:</label>
            <input type="text" id="location" name="location" value="제주" required><br>
            
            <button type="submit">등록</button>
        </form>
    </main>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
