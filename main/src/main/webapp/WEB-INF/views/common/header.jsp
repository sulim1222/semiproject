<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="main.com.web.member.dto.Member" %>
<%
	Member m = (Member)session.getAttribute("member");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Method Jeju</title>
    <link rel="stylesheet" href="./css/index.css"> <!-- index.jsp 파일위치 -->
    <link rel="stylesheet" href="../css/index.css"> <!-- 각 파일에 위치하는값 설정 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <header class="background" id="background">
        <div class="top-section">
            <ul>
            <%if(m==null){ %>
                <li><a href="<%=request.getContextPath()%>/member/loginPage">LOGIN</a></li>
                <li><a href="<%=request.getContextPath()%>/member/signupPage">JOIN</a></li>
                <%}else{ %>
                <li><p><%=m.getMemberName()%>반갑습니다<p></li>
                <li><a href="<%=request.getContextPath()%>/mypage/myReservationPage">MYPAGE</a></li>
                <%} %>
            </ul>
        </div>
        <div class="menu-section">
            <div class="logo"><img src="images/logoBlack.png" alt="호텔로고"></div>
            <div class="menu">
                <ul>
                    <li class="submenu-parent">
                        <a href="jejumain.html">HOME</a>
                    </li>
                    <li class="submenu-parent">
                        <a href="#">ROOMS</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="jejuroom.html">Standard</a></li>
                                <li><a href="#">Deluxe</a></li>
                                <li><a href="#">Suite</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="submenu-parent">
                        <a href="<%=request.getContextPath()%>/revation">RESERVATION</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="#">plzfill</a></li>
                                <li><a href="#">intheblanks</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="submenu-parent">
                        <a href="#">ENJOY</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="#">Tour</a></li>
                                <li><a href="#">Restaurant</a></li>
                                <li><a href="#">Cafe</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="submenu-parent">
                        <a href="#">Q&A</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="#">FAQ</a></li>
                                <li><a href="#">Q&A</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Main Title Display -->
        <div id="mainTitle" class="main-title hidden">
            <h1></h1>
        </div>
    </header>