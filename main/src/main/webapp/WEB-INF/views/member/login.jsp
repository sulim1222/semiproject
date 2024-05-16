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
				<img src="<%=request.getContextPath()%>/images/userImg.png" id="idimg"
					width="30px" height="30px" alt=""> <input type="text"
					name="memberId" id="memberId" placeholder="아이디">
			</div>
			<div id="mainLogin1">
				<img src="<%=request.getContextPath()%>/images/pwdImg.png" id="Pwdimg" width="30px"
					height="30px" alt=""> <input type="password" name="memberPwd"
					id="memberPwd" placeholder="비밀번호">

			</div>
			<div id="check">
				<label for="checkId">아이디 저장</label> <input type="checkbox"
					id="checkId" value="idCheck"> <input type="submit"
					value="로그인"> <br>
			</div>
		</form>
		<div id="submenu">
			<a href="<%=request.getContextPath()%>/member/signupPage">회원가입</a> 
			| <a
				href="">비밀번호 찾기</a> | <a href="">아이디찾기</a> <br>
		</div>
	</div>
</section>

<%@ include file="../common/footer.jsp"%>