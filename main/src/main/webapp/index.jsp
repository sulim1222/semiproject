<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <section class="main-section">
        <div class="main-content">
            <!-- 여기에 메인 컨텐츠 추가해주세용 -->
          <a href="<%=request.getContextPath()%>/room/detailroom.do?room=standard">stardard</a>
          <a href="<%=request.getContextPath()%>/room/detailroom.do?room=delux">delux</a>
          <a href="<%=request.getContextPath()%>/room/detailroom.do?room=suite">suite</a>
        </div>
    </section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>