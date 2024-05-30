<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>
<%@ page import="main.com.web.admin.reserve.dto.Sales" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<% 
    List<Sales> sales = (List<Sales>) request.getAttribute("sales");
    String salesJson = new Gson().toJson(sales);
    String type = (String) request.getAttribute("type");
    
%>

<section class="salesflex">
    <aside class="sales-aside">
        <nav>
            <ul>
                <li>매출현황</li>
             	<li><a href="<%= request.getContextPath() %>/sales/salesbymonth.do" id="showMonthlySales">월별매출</a></li>
                <li><a href="<%= request.getContextPath() %>/sales/salesbylocation.do" id="showLocationSales">지역별매출</a></li>
            </ul>
        </nav>
    </aside>
    
    <main class="sales-main">
        <div id="salesTitle">
            Sales
        </div>
        
        <div class="separator"></div>
        
        <div class="sales-content">
            <div class="sales-table-container" id="monthlySalesTable">
                <table class="salesData">
                    <thead>
                        <tr>
                            <th>월</th>
                            <th>매출액</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (sales != null && !sales.isEmpty()) {
                            for (Sales s : sales) { %>    
                            <tr>
                                <td><%= s.getMonth() %></td>
                                <td><%= s.getRevenue() %></td>
                            </tr>
                        <% } %>
                        <% } %>
                    </tbody>
                </table>
            </div>
            
            <div class="sales-table-container" id="locationSalesTable" style="display:none;">
                <table class="salesData">
                    <thead>
                        <tr>
                            <th>지역</th>
                            <th>매출액</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (sales != null && !sales.isEmpty()) {
                            for (Sales s : sales) { %>    
                            <tr>
                                <td><%= s.getLocation() %></td>
                                <td><%= s.getRevenue() %></td>
                                                               
                            </tr>
                        <% } %>
                        <% } %>                        
                    </tbody>
                </table>
                
            </div>
            
            <div class="sales-graph-container">
                <canvas id="salesChart"></canvas>
            </div>
        </div>
    
    </main>
</section>

<script>


	fetchSalesData('<%= salesJson %>', '<%= type %>');
	
	function fetchSalesData(salesJson, type) {
	    var salesData = JSON.parse(salesJson);
	    var dataType = type;
	
	    drawChart(salesData, dataType);
	}


    function drawChart(salesData, dataType) {
        const labels = [];
        const data = [];
        for (let i = 0; i < salesData.length; i++) {
            if (dataType === 'month') {
                labels.push(salesData[i].month);
                data.push(salesData[i].revenue);
                document.querySelector("#monthlySalesTable").style.display="block";
                document.querySelector("#locationSalesTable").style.display="none";
            } else if (dataType === 'location') {
                labels.push(salesData[i].location);
                data.push(salesData[i].revenue);
                document.querySelector("#locationSalesTable").style.display="block";
                document.querySelector("#monthlySalesTable").style.display="none";
            }
        }

        const ctx = document.getElementById('salesChart').getContext('2d');
        const salesChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: '매출액',
                    data: data,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1,
                    fill: true
                }]
            },
            options: {
                scales: {
                    x: {
                        title: {
                            display: true,
                            text: dataType === 'month' ? '월' : '지역'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: '매출액'
                        },
                        min: 0,
                        max: 24000000
                    }
                },
                responsive: true,
                maintainAspectRatio: false
            }
        });
        
       
    }
   
    
</script>

<style>

	#salesChart{
		height:750px;
	}
    .salesflex {
        display: flex;
    }
    
    #salesTitle {
        font-size: 24px;
        font-weight: bold;
        color: #333;
        margin-bottom: 20px;
    }
    
    .separator {
        height: 1px;
        background-color: #ccc;
        margin-bottom: 20px;
    }
    
    .sales-aside {
        margin-top: 10px;
        width: 15%;
        background-color: #f7f7f7;
        padding: 20px;
        height: 800px;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        overflow-y: auto;
    }
    
    .sales-aside ul {
        list-style-type: none;
        padding: 0;
        text-align: center;
        margin-top: 30px;
    }
    
    .sales-aside ul li:nth-child(1) {
        margin-bottom: 40px;
        font-size: 30px;
        transition: all 0.3s ease-in-out;
        font-weight: bold;
    }
    
    .sales-aside ul li {
        margin-bottom: 20px;
        font-size: 22px;
        transition: all 0.3s ease-in-out;
    }
    
    .sales-aside ul li a {
        text-decoration: none;
        color: #333;
        display: block;
        padding: 10px 0;
        border-radius: 5px;
    }
    
    .sales-aside ul li a:hover {
        background-color: #e0e0e0;
        color: #222;
    }
    
    .sales-main {
        width: 80%; 
        margin-left: 3%; 
        margin-top: 10px;
        display: flex; 
        flex-direction: column;
        padding: 20px;
        border: 1px solid black;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }

    .sales-content {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
    }
    
    .sales-table-container {
        width: 21%;
    }
    
    .sales-graph-container {
        width: 75%;
        height: 600px;
        padding-top: 70px;
    }
    
    .salesData {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    
    .salesData th, .salesData td {
        border: 1px solid #ddd;
        padding: 12px;
        text-align: center;
    }
    
    .salesData th, .salesData td{
        background-color: #f2f2f2;
        font-weight: bold;
        height: 28px;
        vertical-align: middle;
    }

    .salesData tr:nth-child(odd) {
        background-color: #f9f9f9;
    }

    .salesData tr:nth-child(even) {
        background-color: #ffffff;
    }

    .salesData tr:hover {
        background-color: #f1f1f1;
    }

    .salesData td {
        vertical-align: middle;
        color: #555;
    }
</style>






















<%@ include file="/WEB-INF/views/common/adminfooter.jsp"%>