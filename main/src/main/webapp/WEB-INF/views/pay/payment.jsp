<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<!-- jQuery -->
<script type="text/javascript"
    src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<link rel="stylesheet" href="../css/mypage.css">

<section>

    <!-- 결제 버튼 -->
    <button id="payButton">결제하기</button>

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
                    merchant_uid: 'order_no_' + new Date().getTime(), // 주문번호
                    name: '주문명: 결제테스트',
                    amount: 1000, // 결제 금액
                    buyer_email: 'test@example.com',
                    buyer_name: '구매자이름',
                    buyer_tel: '010-1234-5678',
                    buyer_addr: '서울특별시 강남구 삼성동',
                    buyer_postcode: '123-456',
                    m_redirect_url: 'https://www.yourdomain.com/payments/complete' // 결제 완료 후 이동할 URL
                }, function (rsp) {
                    if (rsp.success) {
                        // 결제 성공 시 로직
                        console.log(rsp);
                        alert('결제가 완료되었습니다.');
                        // 서버에 결제 정보 전달
                        // 예: fetch('/payments/complete', { method: 'POST', body: JSON.stringify(rsp) })
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
