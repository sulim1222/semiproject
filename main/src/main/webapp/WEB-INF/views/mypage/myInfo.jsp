<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myinfo.css">    
<% Member loginMember = (Member)session.getAttribute("member"); %>

<section class="sectionflex">
    <aside class="aside">
        <nav>
            <ul>
                <li>
                    <h3>MyPage</h3><hr>
                </li>
                <li><a href="<%=request.getContextPath() %>/mypage/myReservationPage">예약/결제 내역</a></li>
                <li><a href="<%=request.getContextPath()%>/mypage/myReviewPage">내가 작성한 리뷰</a></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myInquiryListPage">문의 내역</a></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myInfoPage">개인정보 수정</a></li>
            </ul>
        </nav>
    </aside>
    <main class="main">
        <span id="title">개인정보 수정</span>
        <div class="separator"></div>

        <div class="container">
			<form class="form-signin" action="<%=request.getContextPath() %>/mypage/myInfo" method="post" name="myForm" onsubmit="return validateForm()">
                <div class="input-group">
                    <label for="memberId">아이디</label>
                    <span><%=loginMember.getMemberId() %></span>
                </div>

                <div class="input-group">
                    <label for="memberName">이름</label>
                    <input type="text" name="name" value="<%=loginMember.getMemberName() %>">
                </div>

				<div class="input-group">
					<label for="password">비밀번호</label> <input type="password"
						name="password">
				</div>

				<div class="input-group">
					<label for="passwordCheck">비밀번호 확인</label> <input type="password"
						name="passwordCheck" onkeyup="validatePassword()"> <span
						id="passwordMatch"></span>
				</div>

				<div class="input-group">
                    <label for="phone">휴대전화</label>
                  <input type="number" name="phone" id="phone" maxlength="3" value="010" min="0">
                    <span>-</span>
                    <input type="number" name="phone1" id="phone1" maxlength="4">
                    <span>-</span>
                    <input type="number" name="phone2" id="phone2" maxlength="4">
                </div>

                <div class="button-container">
                    <button type="submit" class="btn">수정하기</button>
                </div>
            </form>
        </div>
    </main>
</section>
<script>
    function validateForm() {
        var name = document.forms["myForm"]["name"].value;
        var password = document.forms["myForm"]["password"].value;
        var passwordCheck = document.forms["myForm"]["passwordCheck"].value;
        var phone = document.forms["myForm"]["phone"].value;
        var phone1 = document.forms["myForm"]["phone1"].value;
        var phone2 = document.forms["myForm"]["phone2"].value;

        if (name == "" || password == "" || passwordCheck == "" || phone1 == "" || phone2 == "") {
            alert("모든 입력 필드를 작성해주세요.");
            if (name == "") {
                document.forms["myForm"]["name"].focus();
            } else if (password == "") {
                document.forms["myForm"]["password"].focus();
            } else if (passwordCheck == "") {
                document.forms["myForm"]["passwordCheck"].focus();
            } else if (phone == "" || phone1 == "" || phone2 == "") {
                document.forms["myForm"]["phone"].focus();
            }
            return false;
        }
    }
</script>

<script>
    function validatePassword() {
        var password = document.forms["myForm"]["password"].value;
        var passwordCheck = document.forms["myForm"]["passwordCheck"].value;
        var message = document.getElementById("passwordMatch");

        if (password === passwordCheck) {
            message.style.color = 'blue';
            message.innerHTML = '비밀번호가 일치합니다.';
        } else {
            message.style.color = 'red';
            message.innerHTML = '비밀번호가 일치하지 않습니다.';
        }
    }

    document.forms["myForm"]["passwordCheck"].addEventListener('keyup', validatePassword);
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>
