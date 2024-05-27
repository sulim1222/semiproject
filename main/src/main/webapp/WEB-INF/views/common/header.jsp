<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="main.com.web.member.dto.*" %>
<%
	Member m = (Member)session.getAttribute("member");
	Kakao member = (Kakao)session.getAttribute("kakaoMember"); // kakao 부분
%>
<!DOCTYPE html>
<html lang="ko">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Method Jeju</title>
    
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/index.css"> <!-- index.jsp 파일위치 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&family=Noto+Serif+KR&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Libre+Baskerville:ital,wght@0,400;0,700;1,400&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <header class="background" id="background">
       	<h1></h1>
        <div class="top-section">
            <ul>
            <%if(m==null&&member==null){ %>
                <li><a href="<%=request.getContextPath()%>/member/loginPage">LOGIN</a></li>
                <li><a href="<%=request.getContextPath()%>/member/signupPage">JOIN</a></li>

                <%}else{ %>
                <%if(!(m==null)){ %>
				
                <li><p><%=m.getMemberName()%>님 반갑습니다<p></li>
                <li><a href="<%=request.getContextPath()%>/mypage/myReservationPage">MYPAGE</a></li>
                <li><a href="<%=request.getContextPath()%>/member/logout">LOGOUT</a></li>
                <%}else{ %>
                <li><p><%=member.getNickname()%>님 반갑습니다<p></li> <!-- kakao에 대한부분  -->
                <li><a href="<%=request.getContextPath()%>/mypage/myReservationPage">MYPAGE</a></li>
                <li><a href="<%=request.getContextPath()%>/kakao/logout">LOGOUT</a></li>
                <%} }%>
            </ul>
        </div>
        <div class="menu-section">
            <div class="logo"><img src="<%=request.getContextPath()%>/imges/hotellogo.png" alt="호텔로고"></div>

            <div class="menu">
                <ul>
                    <li class="submenu-parent">
                        <a href="jejumain.html">HOME</a>
                    </li>
                    <li class="submenu-parent">
                        <a href="<%=request.getContextPath()%>/room/standardroom.do">ROOMS</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="<%=request.getContextPath() %>/room/standardroom.do?room=Standard&location=제주">Standard</a></li>
                                <li><a href="<%=request.getContextPath()%>/room/standardroom.do?room=Deluxe&location=제주">Deluxe</a></li>
                                <li> <a href="<%=request.getContextPath()%>/room/standardroom.do?room=Suite&location=제주">Suite</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="submenu-parent">
                        <a href="<%=request.getContextPath()%>/revation">RESERVATION</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="<%=request.getContextPath()%>/revation">reservation</a></li>
                                <li><a href="#">caution</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="submenu-parent">
                        <a href="<%=request.getContextPath()%>/enjoy/cafe"">ENJOY</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="<%=request.getContextPath()%>/enjoy/Tour">Tour</a></li>
                                <li><a href="<%=request.getContextPath()%>/enjoy/Restaurant">Restaurant</a></li>
                                <li><a href="<%=request.getContextPath()%>/enjoy/cafe">Cafe</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="submenu-parent">
                        <a href="<%=request.getContextPath()%>/qna/FAQList">Q&A</a>
                        <div class="submenu">
                            <ul>
                                <li><a href="<%=request.getContextPath()%>/qna/FAQList">FAQ</a></li>
                                <li><a href="<%=request.getContextPath()%>/qna/submitInquiry">Q&A</a></li>
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
        <script src="<%=request.getContextPath()%>/js/header.js"></script> <!-- javascript 내용 -->
    </header>