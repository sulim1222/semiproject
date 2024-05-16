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
        <main class="main"><span id="title">별점/리뷰 관리</span>
            <div class="separator"></div>
            <div class="container1">

            
            </div>
            <div class="container2">
                <table class="reservationInfo">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>호텔명</th>
                            <th>객실타입</th>
                            <th>입실날짜</th>
                            <th>퇴실날짜</th>
                            <th>별점</th>
                            <th>작성한 리뷰</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Method Hotel Jeju</td>
                            <td>스탠다드</td>
                            <td>2024/05/02</td>
                            <td>2024/05/03</td>
                            <td><img src="<%=request.getContextPath()%>/images/star5.png" width="80"></td>
                            <td>
                                <span> 좋아요~ ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </main>
    </section>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>