<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.com.web.member.dto.*, main.com.web.room.dto.*"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
    Member m1 = (Member)session.getAttribute("member");
    Room r = (Room)request.getAttribute("room");
    int roomNo = Integer.parseInt(request.getParameter("roomNo"));
    int roomPeopleNo  = Integer.parseInt(request.getParameter("peopelNo"));
    String car = request.getParameter("car");
    String bedType = request.getParameter("bedType");
    String roomRequest = request.getParameter("roomRequest");
    if (roomRequest == null) {
        roomRequest = "";
    }
    String checkInDate = request.getParameter("checkindate"); // 체크인 날짜
    String checkOutDate = request.getParameter("checkoutdate"); // 체크아웃 날짜
    int totalPrice = Integer.parseInt(request.getParameter("price"));
%>

<!-- jQuery -->
<script type="text/javascript"
    src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/pay.css">    
<section>
    <div class="container">
        <h1>예약/결제하기</h1>
        <form action="#" method="POST">
       
            <div class="info-container">
                <label for="hotel">지역</label> 
                <input type="text" id="hotel" name="hotel" readonly value="<%=r.getLocation()%>"> 
                <label for="checkin">체크인</label> 
                <input type="text" id="checkin" name="checkin" readonly value="<%=checkInDate %>"> 
                <label for="checkout">체크아웃</label> 
                <input type="text" id="checkout" name="checkout" readonly value="<%=checkOutDate %>"> 
                <label for="room">객실 타입</label> 
                <input type="text" id="room" name="room" readonly value="<%=r.getRoomType() %>"> 
                <label for="amount">인원 수</label> 
                <input type="text" id="amount" name="amount" readonly value="<%=roomPeopleNo %>"> 
                <label for="bed">침대</label> 
                <input type="text" id="bed" name="bed" readonly value="<%=bedType%>"> 
                <label for="car">자차여부</label> 
                <input type="text" id="car" name="car" readonly value="<%=car %>"> 
                <label for="request">요청사항</label> 
                <input type="text" id="roomRequest" name="roomRequest" readonly value="<%=roomRequest %>"> 
                <label for="price">결제금액</label> 
                <input type="text" id="price" name="price" readonly value="<%=totalPrice %>">
                
                <!-- 데이터 전송용 -->
                <input type="hidden" id="roomNo" name="roomNo"value="<%=r.getRoomNo()%>"> 
                <input type="hidden"id="peopleNo" name="peopleNo" value="<%=roomPeopleNo%>"> 
                <input type="hidden" id="car" name="car" value="<%=car%>"> 
                <input type="hidden" id="bedType" name="bedType" value="<%=bedType%>">
                <input type="hidden" id="roomRequest" name="roomRequest" value="<%=roomRequest%>"> 
                <input type="hidden" id="checkInDate" name="checkInDate" value="<%=checkInDate%>">
                <input type="hidden" id="checkOutDate" name="checkOutDate" value="<%=checkOutDate%>">
            </div>
          
            <div class="agreement-container">
                <p>▶ 개인정보 수집 동의서 ◀</p>
                <p>Method Hotel은 귀하의 소중한 개인정보를 수집, 활용하고자 [개인정보보호법]에 따라 본인의 동의를 얻고 있습니다.</p>
                <p>이에 아래의 내용과 같이 귀하의 개인정보를 수집, 활용하는데 동의하여 주실 것을 요청드립니다.</p>
                <p>- 수집항목: 이름, 이메일, 주소, 전화번호</p>
                <p>- 수집목적: 서비스 제공, 고객 문의 응대</p>
                <p>- 보유기간: 서비스 이용 종료 후 1년까지</p>
                <p>- 처리방침: 귀하는 개인정보에 대한 권리를 갖고 있으며, 제공한 개인정보의 열람, 정정, 삭제, 처리정지 요청이 가능합니다.</p>
                <p>- 동의철회: 귀하는 언제든지 동의를 철회할 수 있으며, 동의를 철회하는 경우 제공한 개인정보는 파기됩니다.</p>
                <p>- 동의거부: 귀하는 개인정보 수집에 대한 동의를 거부할 수 있으나, 이 경우 서비스 이용이 제한될 수 있습니다.</p>
                <p>동의하지 않을 경우 해당 서비스를 이용하실 수 없습니다.</p>
            </div>
                <div class="checkbox-container">
                    <label for="agree">
                        <input type="checkbox" id="agree" name="agree"> 개인정보 수집에 동의합니다.
                    </label>
                </div>
            <div class="payment-btn-container">
                <img src="<%=request.getContextPath()%>/images/kakaoPay.png" alt="Pay Button" id="kakaoPayImg" width="150" height="100">
                <!-- <button type="button" id="inicisPay">이니시스 결제하기</button> -->
            </div>
        </form>
    </div>

    <script>
        $(document).ready(function() {
            IMP.init('imp66468378');

            $('#kakaoPayImg').click(function(event) {
                event.preventDefault(); 
                if ($('#agree').prop('checked')) {
                    requestPayKakao();
                } else {
                    alert('개인정보 수집에 동의해야 결제가 가능합니다.');
                }
            });

            $('#inicisPay').click(function(event) {
                event.preventDefault();
                if ($('#agree').prop('checked')) {
                    requestPayInicis();
                } else {
                    alert('개인정보 수집에 동의해야 결제가 가능합니다.');
                }
            });

            function requestPayKakao() {
                IMP.request_pay({
                    pg : 'kakaopay',
                    pay_method : 'card',
                    merchant_uid : 'p_' + new Date().getTime(),
                    name : 'METHOD Hotel',
                    amount : '<%=totalPrice %>',
                    buyer_email : '<%=m1.getMemberId()%>',
                    buyer_name : '<%=m1.getMemberName()%>',
                    buyer_tel : '<%=m1.getMemberPhone()%>'
                }, function(rsp) {
                    if (rsp.success) {
                        alert('결제가 완료되었습니다.');

                        $.ajax({
                            url: '<%=request.getContextPath()%>/pay/savepayment',
                            method: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify({
                                imp_uid: rsp.imp_uid,
                                merchant_uid: rsp.merchant_uid,
                                payPrice: '<%=totalPrice%>',
                                paymentMethod: 'kakaopay',
                                status: 'paid',
                                location: '<%=r.getLocation()%>',
                                roomPeopleNo: <%= roomPeopleNo %>, 
                                roomNo: <%= r.getRoomNo() %>,
                                checkInDate: '<%=checkInDate %>', 
                                checkOutDate: '<%=checkOutDate %>',
                                bedType: '<%=bedType %>',
                                car: '<%=car %>'
                            }),
                            success: function(response) {
                                const reserveNo = response.reserveNo;
                                window.location.href = '<%=request.getContextPath()%>/pay/paycompletePage?reserveNo=' + reserveNo;
                            },
                            error: function() {
                                alert('결제 정보 저장에 실패하였습니다.');
                            }
                        });

                    } else {
                        alert('결제를 취소하였습니다.');
                    }
                });
            }

            <%--  function requestPayInicis() {
                IMP.request_pay({
                    pg : 'html5_inicis.INIpayTest',
                    pay_method : 'card',
                    merchant_uid : 'p_' + new Date().getTime(),
                    name : 'METHOD Hotel',
                    amount : '<%=totalPrice %>',
                    buyer_email : '<%=m1.getMemberId()%>',
                    buyer_name : '<%=m1.getMemberName()%>',
                    buyer_tel : '<%=m1.getMemberPhone()%>'
                }, function(rsp) {
                    if (rsp.success) {
                        alert('결제가 완료되었습니다.');

                        $.ajax({
                            url: '<%=request.getContextPath()%>/pay/savepayment',
                            method: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify({
                                imp_uid: rsp.imp_uid,
                                merchant_uid: rsp.merchant_uid,
                                payPrice: '<%=totalPrice%>',
                                paymentMethod: 'html5_inicis',
                                status: 'paid',
                                location: '<%=r.getLocation()%>',
                                roomPeopleNo: <%= roomPeopleNo %>, 
                                roomNo: <%= r.getRoomNo() %>,
                                checkInDate: '<%=checkInDate %>', 
                                checkOutDate: '<%=checkOutDate%>',
                                bedType: '<%=bedType %>',
                                car: '<%=car %>'
                            }),
                            success: function(response) {
                                const reserveNo = response.reserveNo;
                                window.location.href = '<%=request.getContextPath()%>/pay/paycompletePage?reserveNo=' + reserveNo;
                            },
                            error: function() {
                                alert('결제 정보 저장에 실패하였습니다.');
                            }
                        });

                    } else {
                        alert('개발중...');
                    }
                });
            } --%>
        });
    </script>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>