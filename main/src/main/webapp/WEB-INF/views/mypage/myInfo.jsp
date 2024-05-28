<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/myinfo.css">    
<% Member loginMember = (Member)session.getAttribute("member"); %>

<section class="sectionflex">
    <aside class="aside">
        <nav>
            <ul>
				<li>MyPage<hr></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myReservationPage">예약/결제 내역</a></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myReviewPage">별점/리뷰 관리</a></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myInquiryListPage">문의 내역</a></li>
                <li><a href="<%=request.getContextPath() %>/mypage/myInfoPage">개인정보 수정</a></li>
            </ul>
        </nav>
    </aside>
    <main class="main">
        <span id="title">회원 정보 수정</span>
        <div class="separator"></div>

        <div class="container">
            <form class="form-signin" action="<%=request.getContextPath() %>/mypage/myInfo" method="post">
                <div class="input-group">
                    <label for="memberId">아이디</label>
                    <span><%=loginMember.getMemberId() %></span>
                </div>

                <div class="input-group">
                    <label for="memberName">이름</label>
                    <input type="text" name="name" value="<%=loginMember.getMemberName() %>">
                </div>

                <div class="input-group">
                    <label for="password">비밀번호</label>
                    <input type="password" name="password">
                </div>

                <div class="input-group">
                    <label for="passwordCheck">비밀번호 확인</label>
                    <input type="password" name="passwordCheck">
                </div>

                <div class="input-group">
                    <label for="phone">휴대전화</label>
                    <input type="number" name="phone" id="phone" maxlength="3" placeholder="010">
                    <span>-</span>
                    <input type="number" name="phone1" id="phone1" maxlength="4">
                    <span>-</span>
                    <input type="number" name="phone2" id="phone2" maxlength="4">
                </div>

                <button type="submit" class="search">수정하기</button>
            </form>
        </div>
    </main>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
