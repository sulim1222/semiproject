<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.com.web.admin.reserve.dto.Sales" %>
<%@ page import="java.util.List" %> 
<% 
	List<Sales> sales = (List<Sales>)request.getAttribute("sales");
%>
<section class="salesglex">
	<aside class="sales-aside">
		<nav>
			<ul>
				<li><a href="<%=request.getContextPath()%>/sales/salesupdate.do?salesbymonth=월별매출">월별매출</a></li>
				<li><a href="<%=request.getContextPath()%>/sales/salesupdate.do?salesbylocation=지역별매출">지역별매출</a></li>
			</ul>
		</nav>
	</aside>
	
	<main class="sales-main">
		<div id="salesTitle">
			Sales
		</div>
		<div>
			<table class="salesData">
				<thead>
					<tr>
						<th>월</th>
						<th>매출액</th>
					</tr>
				</thead>
				<tbody>
					<% if(sales.size()>0){
						for(Sales s : sales){%>	
					 <tr>
                        <td><%= s.getMonth() %></td>
                        <td><%= s.getRevenue() %></td>
                    </tr>
					
					<% }%>
					<%} %>
				</tbody>
			</table>
		</div>
	
	
	
	
	
	
	
	
	</main>
</section>	

<style>

	.sales-aside {
	    margin-top: 10px;
	    width: 15%;
	    background-color: #f7f7f7; /* 부드러운 회색 배경 */
	    padding: 20px;
	    height: 800px;
	    border: 1px solid #e0e0e0; /* 연한 회색 테두리 */
	    border-radius: 10px; /* 둥근 테두리 */
	    box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1); /* 약한 그림자 */
	    overflow-y: auto; /* 필요 시 스크롤 생성 */
	}
	
	.sales-main {
        width: 80%; 
        margin-left: 3%; 
        margin-top: 10px;
        display: flex; 
        justify-content: space-between; 
        padding: 10px; 
        border: 1px solid black;
        background-color: #fff;
        display: block;
        border-radius: 8px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }
</style>























<%@ include file="/WEB-INF/views/common/adminfooter.jsp"%>