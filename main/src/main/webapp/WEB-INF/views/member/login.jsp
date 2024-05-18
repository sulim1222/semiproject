<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
<link rel="stylesheet" href="../css/login.css"> <!-- 각 파일에 위치하는값 설정 -->
<section id="loginForm">
	<!--로그인 내용-->
	<div id="loginTop">
		<p>METHOD HOTEL</p>
	</div>
	<p>LOGIN</p>
	<div id="loginContainer">
		<form action="<%=request.getContextPath()%>/member/login" method="post">
			<div id="mainLogin">
				<img src="<%=request.getContextPath()%>/imges/userImg.png" id="idimg"
					width="30px" height="30px" alt=""> <input type="text"
					name="memberId" id="memberId" placeholder="아이디">
			</div>
			<div id="mainLogin1">
				<img src="<%=request.getContextPath()%>/imges/pwdImg.png" id="Pwdimg" width="30px"
					height="30px" alt=""> <input type="password" name="memberPwd"
					id="memberPwd" placeholder="비밀번호"  size=15>

			</div>
			<div id="check">
				<label for="checkId">아이디 저장</label> <input type="checkbox"
					id="checkId" value="idCheck"> <input type="submit"
					value="로그인"  size=15> <br>
			</div>
		</form>
		<div id="kakaoLogin">
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=a567f7c380e11df07ed70d544e8f86d8&redirect_uri=http://localhost:9090/main/kakaologin&response_type=code"><img height="38px" src="<%=request.getContextPath()%>/imges/kakao_login.png" /></a>
		</div>
		<div id="submenu">
			<a href="<%=request.getContextPath()%>/member/signupPage">회원가입</a> 
			| <a
				href="">비밀번호 찾기</a> | <a href="">아이디찾기</a> <br>
		</div>
	</div>
	<div>
    
    <%-- <a href="javascript:kakaoLogin()"><img src="<%=request.getContextPath()%>/imges/kakao_login.png"/></a> --%>
</div>
<!-- <button type="submit" class="btn btn-primary">로그인</button> -->
        <!-- 오류 -->
<!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri=http://localhost:8079/Dong-Dong/kakao&response_type=code"></a> -->
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<!-- </script> -->
<%@ include file="../common/footer.jsp"%>