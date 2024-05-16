<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="../css/mypage.css">
     <section class="sectionflex">
        <aside class="aside">
            <nav>
                <ul>
                    <li>MyPage</li>
                    <li><a href="<%=request.getContextPath() %>/mypage/myReservationPage">예약/결제 내역</a></li>
                    <li><a href="<%=request.getContextPath() %>/mypage/myReviewPage">별점/리뷰 관리</a></li>
                    <li><a href="<%=request.getContextPath() %>/mypage/myInfoPage">개인정보 수정</a></li>
                </ul>
            </nav>
        </aside>
        <main class="main"><span id="title">예약/결제 내역</span>
            <div class="separator"></div>
            <div class="container1">
                <select style="width: 100px; height: 28px;">
                    <option value="reserveNo">예약번호</option>
                    <option value="memberName">호텔명</option>
                </select>
                <input type="text" placeholder="Search..." class="searchInput">
                <button type="button" class="search">검색</button>
            </div>
            <div class="container2">
                <table class="reservationInfo">
                    <thead>
                        <tr>
                            <th>예약번호</th>
                            <th>호텔명</th>
                            <th>객실타입</th>
                            <th>입실날짜</th>
                            <th>퇴실날짜</th>
                            <th>결제금액</th>
                            <th>예약/결제 취소</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>J24060201</td>
                            <td>Method Hotel Jeju</td>
                            <td>스탠다드</td>
                            <td>2024/06/02</td>
                            <td>2024/06/03</td>
                            <td>100,000원</td>
                            <td>
                                <button type="button" onclick="cancel();">취소</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </main>
    </section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>