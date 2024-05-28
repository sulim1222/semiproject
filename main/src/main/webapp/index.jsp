<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
     <div id="mainText">
                  
                </div>
    <section class="main-section">
                
        <div class="main-content ">
            <!-- 여기에 메인 컨텐츠 추가해주세용 -->
          <a href="<%=request.getContextPath()%>/room/standardroom.do?room=Standard&location=제주">Standard</a>
          <a href="<%=request.getContextPath()%>/room/standardroom.do?room=Deluxe&location=제주">Deluxe</a>
          <a href="<%=request.getContextPath()%>/room/standardroom.do?room=Suite&location=제주">Suite</a>
        </div>
        
    </section>
    <script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
    <script>
   	 $(".main-content").hide(); 
    </script>
    <script src = "<%=request.getContextPath()%>/js/extendIndex.js"></script>
    <style>
    .background{
     background-image:url('./imges/2.jpg');
     height: 1200px;
     }

     .texth2{
   	  margin-top : 200px;
      text-align: center; 

     .texth2{	
     text-align: center;
     }
    </style>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>