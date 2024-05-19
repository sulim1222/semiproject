<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<!-- jQuery -->
<script type="text/javascript"
    src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<link rel="stylesheet" href="../css/pay.css">

<section>

<div class="container">
    <h1>예약/결제하기</h1>
    <form action="#" method="POST">
        <div style="overflow: hidden;">
            <div style="float: left; width: 30%;">
                <label for="hotel">호텔 이름</label>
                <input type="text" id="hotel" name="hotel" readonly value="Method Hotel Jeju">
                
                <label for="checkin">체크인</label>
                <input type="text" id="checkin" name="checkin" readonly value="2024/06/02">
                
                <label for="checkout">체크아웃</label>
                <input type="text" id="checkout" name="checkout" readonly value="2024/06/03">
                
                <label for="room">객실 타입</label>
                <input type="text" id="room" name="room" readonly value="스탠다드">
                
                <label for="amount">결제 금액</label>
                <input type="text" id="amount" name="amount" readonly value="100,000원">
            </div>
            <div style="float: right; width: 70%;">
                <div class="agreement-container">
                    <p>개인정보 수집 동의서</p>
                    <p>Method Hotel은 귀하의 소중한 개인정보를 수집, 활용하고자 [개인정보보호법]에 따라 본인의 동의를 얻고 있습니다. </p>
                    <p>이에 아래의 내용과 같이 귀하의 개인정보를 수집, 활용하는데 동의하여 주실 것을 요청드립니다.</p>
                    <p>-------------------------------------------------------------------------------------</p>
                    <p>ㅇ</p><p>ㅇ</p><p>ㅇ</p><p>ㅇ</p><p>ㅇ</p><p>ㅇ</p><p>ㅇ</p>
                    <p>ㅇ</p>
                    <p>ㅇ</p>
                    <p>저는 위 사이트에서 제공하는 서비스를 이용하기 위해 아래와 같이 개인정보 수집에 동의합니다.</p>
                    <p>- 수집항목: 이름, 이메일, 주소, 전화번호</p>
                    <p>- 수집목적: 서비스 제공, 고객 문의 응대</p>
                    <p>- 보유기간: 서비스 이용 종료 후 1년까지</p>
                    <p>동의하지 않을 경우 해당 서비스를 이용하실 수 없습니다.</p>
                </div><br><br><br><br><br><br><br><br><br><br><br>
                
                <div class="checkbox-container">
                    <label for="agree">
                        <input type="checkbox" id="agree" name="agree">
                        개인정보 수집에 동의합니다.
                    </label>
                </div>
            </div>
        </div>
        <button type="submit" class="payment-btn" id="payButton">결제하기</button>
    </form>
</div>


<script>
	$(document).ready(function() {
	    // 결제 버튼 클릭 이벤트 핸들러
	    $('#payButton').click(function() {
	        // 결제 정보를 설정합니다.
	        var IMP = window.IMP; // 생략 가능
	        IMP.init('imp66468378'); // 가맹점 식별코드
	
	        function requestPay() {
	            IMP.request_pay({
	                pg: 'kakaopay',
	                pay_method: 'card',
	                merchant_uid: 'payNo' + new Date().getTime(), // 결제번호
	                name: '주문명: 결제테스트',
	                amount: 1000, // 결제 금액
	                buyer_email: 'test@example.com',
	                buyer_name: '구매자이름',
	                buyer_tel: '010-1234-5678',
	                buyer_addr: '서울특별시 강남구 삼성동',
	                buyer_postcode: '123-456',
	                m_redirect_url: 'https://www.yourdomain.com/payments/complete' // 결제 완료 후 이동할 URL
	            }, function(rsp) {
	                if (rsp.success) {
	                    // 결제 성공 시 로직
	                    console.log(rsp);
	                    alert('결제가 완료되었습니다.');
	
	                    // 서버에 결제 정보 전달
	                    axios({
	                        url: '/pay/payment', // 서버의 결제 정보를 받는 엔드포인트
	                        method: 'post',
	                        headers: { "Content-Type": "application/json" },
	                        data: {
	                            imp_uid: rsp.imp_uid, // 아임포트 결제 고유 ID
	                            merchant_uid: rsp.merchant_uid // 상점에서 생성한 주문번호
	                            
	                        }
	                    })
	                    .then((response) => {
	                        // 서버 결제 API 성공 시 처리할 로직
	                        if (response.data.success) {
	                            alert('결제 정보가 성공적으로 전송되었습니다.');
	                            // 추가로 결제 완료 후 처리할 작업들
	                        } else {
	                            alert(`결제 완료 처리에 실패하였습니다: ${response.data.message}`);
	                        }
	                    })
	                    .catch((error) => {
	                        // 서버와의 통신 에러 처리
	                        alert(`서버와의 통신 중 오류가 발생하였습니다: ${error.message}`);
	                    });
	                } else {
	                    // 결제 실패 시 로직
	                    console.log(rsp);
	                    alert('결제에 실패하였습니다. 에러 내용: ' + rsp.error_msg);
	                }
	            });
	        }
	
	        // 결제 요청 함수 호출
	        requestPay();
	    });
	});
</script>

</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
