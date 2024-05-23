<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.com.web.admin.reserve.dto.Member" %> 
<!DOCTYPE html>
<html lang="ko">
<html>
<head>
    <!-- <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Method Hotel</title>
    <link rel="stylesheet" href="jejuroom.css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet"> -->

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<title>관리자페이지</title>
</head>

<style>
	header{
		background-color:lightgray;
		height:130px;
		position:relative;
		padding:10px 0 0 0;
	}
	
	div,section,header,footer{padding:10px;}
	
	header {
        display: flex;
        justify-content: space-between; 
        align-items: center; 
        padding: 10px 0;
        border: 1px solid black;
        width: 100%;
	        }
	
	#menu {
	    margin-right: 80px;
	}
	
	#logo{
	    margin-left:40px;
	}
	
	#menu ul {
	    list-style-type: none; 
	    padding: 0; 
	    margin: 0; 
	    display: flex; 
	    gap: 50px;
	}
	
	#menu ul li a {
	    text-decoration: none;
	    color: black;
	    font-size: 20px;
	    font-weight: bolder;
	    display: block; 
	    line-height: 1.5; 
	}
	
	#menu ul li a:hover { 
	    transition: 1s;
	    transform: scale(1.2);
	}
	
	
	div#reserveNo{display:inline-block;}
	div#memberName{display:none;}
	div#roomType{display:none;}

</style>

<body>
	<header>
		<div id="logo" >
            <img src="./img/logo.png" alt="logo" width="150px" height="80px">
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
                    <a href="<%=request.getContextPath()%>/enjoy/enjoyupdate.do">Enjoy</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/qna/qnaupdate.do">Q&A</a>
                </li>
            </ul>
        </div>
	</header>
	
	
	
	
	

