<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/adminheader.jsp"%>
<%@ page import="main.com.web.admin.reserve.dto.Sales" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<% 
    List<Sales> sales = (List<Sales>)request.getAttribute("sales");
%>

<section class="salesflex">
    <aside class="sales-aside">
        <nav>
            <ul>
                <li>매출현황</li>
                <li><a href="<%=request.getContextPath()%>/sales/salesupdate.do?salesbymonth=월별매출">월별매출</a></li>
                <li><a href="<%=request.getContextPath()%>/sales/salesupdate.do?salesbylocation=지역별매출">지역별매출</a></li>
            </ul>
        </nav>
    </aside>
    
    <main class="sales-main">
        <div id="salesTitle">
            Sales
        </div>
        
        <div class="separator"></div>
        
        <div class="sales-content">
            <div class="sales-table-container">
                <table class="salesData">
                    <thead>
                        <tr>
                            <th>월</th>
                            <th>매출액</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if(sales.size() > 0) {
                            for(Sales s : sales) { %>    
                            <tr>
                                <td><%= s.getMonth() %></td>
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

let salesData = [
    {"month":"2024-01","revenue":100000},
    {"month":"2024-02","revenue":200000},
    {"month":"2024-03","revenue":200000},
    {"month":"2024-04","revenue":400000},
    {"month":"2024-05","revenue":5380000},
    {"month":"2024-06","revenue":9280000},
    {"month":"2024-07","revenue":6600000},
    {"month":"2024-08","revenue":4200000},
    {"month":"2024-09","revenue":200000},
    {"month":"2024-10","revenue":400000},
    {"month":"2024-11","revenue":100000},
    {"month":"2024-12","revenue":400000}
];

// 데이터 가공 및 그래프 생성 함수
function drawChart() {
    const labels = [];
    const data = [];

    for (let i = 0; i < salesData.length; i++) {
        labels.push(salesData[i].month);
        data.push(salesData[i].revenue);
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
                        text: '월'
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: '매출액'
                    },
                    min: 100000,
                    max: 14000000
                }
            },
            responsive: true,
            maintainAspectRatio: false
        }
    });
}

// 초기 그래프 생성
drawChart();

// 데이터 업데이트 함수
function updateData(newSalesData) {
    // 데이터 갱신
    salesData = newSalesData;

    // 그래프 다시 그리기
    salesChart.destroy(); // 기존 차트 삭제
    drawChart(); // 새로운 데이터로 그래프 생성
}

// 예약이 추가될 때마다 호출되는 함수 예시
function addReservation(newRevenue) {
    salesData.push({"revenue": newRevenue}); // 새로운 매출 데이터 추가
    updateData(salesData); // 데이터 업데이트 및 그래프 재생성
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