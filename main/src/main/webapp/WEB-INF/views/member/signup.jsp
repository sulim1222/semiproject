<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="../css/signup.css">
<!-- 각 파일에 위치하는값 설정 -->
<section id="signupContainer">
	<div id="signupFont">
		<p>J O I N</p>

	</div>
	<form action="<%=request.getContextPath()%>/member/signup"
		method="post" class="signup-form">

		<div class="groupJoin">
			<label for="userId">이메일</label> <input type="text" name="userId"
				id="userId" placeholder="이메일을 입력하세요"> <span>@</span> <select
				name="emailDomain" id="emailDomain">
				<option value="">선택</option>
				<option value="@gmail.com">gmail.com</option>
				<option value="@naver.com">naver.com</option>
				<option value="@daum.net">daum.net</option>
				<option value="@nate.com">nate.com</option>
			</select>
		</div>
		<div id="btnclass">
			<button type="button" id="btn1" onclick="duplicate()">중복확인</button>
			<button type="button" id="btn2" onclick="sendMail()">이메일 전송</button>
			<div id="sibal">
				<input type="number" id="checkMail">
				<button type ="button" onclick="checkMailbtn()">확인</button>
			</div>
		</div>
		<div class="groupJoin">
			<label for="memberPwd">비밀번호</label> <input type="password"
				name="memberPwd" id="memberPwd" 
				placeholder="8자이상 16자미만 특수기호(!@#$%^&*)포함">
		</div>
		<div class="groupJoin">
			<label for="memberPwdConfirm">비밀번호 확인</label> <input type="password"
				name="memberPwdConfirm" id="memberPwdConfirm"
				placeholder="비밀번호를 다시 입력하세요">
		</div>
		<div class="groupJoin">
			<label for="memberName" >이름</label> <input type="text"
				name="memberName" id="memberName" placeholder="이름을 입력하세요">
		</div>
		<div class="groupJoin">
			<label for="userId">주민번호</label> <input type="number"
				name="memberCheckNo" id="memberCheckNo" placeholder="주민번호 입력하세요">
			<span>-</span> <input type="number" name=memberCheckNoEnd id="memberCheckNoEnd">
		</div>
		<div class="groupJoin">
			<label for="phone">휴대폰</label> 
			<input type="number" name="phone" id="phone" placeholder="010"> 
			<span>-</span> 
			<input type="number" name="phone1" id="phone1"> 
			<span>-</span> 
			<input type="number" name="phone2" id="phone2">
		</div>
		<div class="groupJoin button-group">
			<button type="submit" id=allck>가입하기</button>
		</div>
	</form>
	<script src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/signup.js"></script> <!-- javascript 내용 -->
</section>
<%@ include file="../common/footer.jsp"%>