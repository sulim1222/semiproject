<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.com.web.admin.reserve.dto.Member" %> 
<!DOCTYPE html>
<html lang="ko">
<html>


<head>
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/> --%>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
<title>관리자페이지</title>
</head>


<body>
	<header>
		<div id="logo" >
            <img src="<%=request.getContextPath()%>/imges/admin/logo.png" alt="logo" width="150px" height="80px">
        </div>
        <div id="menu">
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/reserve/reserveupdate.do">Reservation</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/rooms/roomsupdate.do">Rooms</a>
                </li>
                <li>
                    <a href="#" onclick="alert('아직 개발 중입니다.'); return false;">Enjoy</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/admin/FAQList">Q&A</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/sales/salesupdate.do">Sales</a>
                </li>
            </ul>
        </div>
	</header>

	
<style>
	header {
	    background-color: #f7f7f7; /* 부드러운 회색 배경 */
	    height: 120px;
	    position: relative;
	    padding: 10px 0;
	    border: 1px solid #e0e0e0; /* 연한 회색 테두리 */
	    align-items: center;
	    width: 100%;
	    justify-content: space-between;
	    display: flex;
	}
	
	
	#menu {
	    margin-right: 40px; /* 오른쪽 여백 추가 */
	}
	
	#menu ul {
	    list-style-type: none;
	    padding: 0;
	    margin: 0;
	    display: flex;
	    gap: 50px;
	    margin-top: 50px; 
	}
	
	#menu ul li a {
	    text-decoration: none;
	    color: black;
	    font-size: 20px;
	    font-weight: bold; /* 글꼴을 두껍게 만듭니다. */
	    display: block;
	    line-height: 1.5;
	}
	
	#menu ul li a:hover {
	    transition: 0.3s; /* 변화가 더 빨리 일어나도록 합니다. */
	    transform: scale(1.1); /* 약간 작게 변경합니다. */
	}
	
	#logo {
	    margin-left: 4.5%;
	}
	
</style>

