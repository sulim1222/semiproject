var slideIndex = 0;
var slideInterval;

function plusSlides(n) {
    clearTimeout(slideInterval); // 이전에 설정된 타이머 취소
    showSlides(slideIndex += n);
}

function showSlides(n) {
    var slides = document.getElementsByClassName("slide");
    if (n < 0) {
        slideIndex = slides.length - 1;
    }
    if (n >= slides.length) {
        slideIndex = 0;
    }
    for (var i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slides[slideIndex].style.display = "block";
    slideInterval = setTimeout(function() { plusSlides(1) }, 3000); // 다음 슬라이드로 이동
}

showSlides(slideIndex); // 초기 슬라이드 표시


 function copyToClipboard() {
        // 복사할 URL
        const url = window.location.href;

        // 임시 입력 요소 생성
        const tempInput = document.createElement('input');
        tempInput.style.position = 'absolute';
        tempInput.style.left = '-9999px';
        tempInput.value = url;
        document.body.appendChild(tempInput);

        // 텍스트 선택
        tempInput.select();
        const urlTextex = tempInput.value;
       const uri = urlTextex.substring(7,urlTextex.length);
       // uri가 local 복사한 주소창
        console.log(uri);
        console.log(tempInput.value);

        // 텍스트 복사
        document.execCommand('copy');

        // 임시 입력 요소 제거
        document.body.removeChild(tempInput);

        // URL 복사 알림
        alert('URL 주소가 복사되었습니다.');
    }
    // 복사 버튼에 이벤트 리스너 추가
    const copyButton = document.querySelector(".sharing-container");
            copyButton.addEventListener("click", copyToClipboard);
        	
        ;




function goUp(){
    window.scrollTo({
        top: 0,
        left: 0,
        behavior: 'smooth'
    });
}

 // 버튼 요소를 가져옵니다.
    var reservationBtn = document.getElementById("room_reservation_btn");

    // 버튼을 클릭했을 때 이벤트 처리기를 추가합니다.
    reservationBtn.addEventListener("click", function() {
        // 페이지를 이동할 URL을 지정합니다.
        var destinationURL = "http://localhost:9090/main/revation";

        // 현재 창에서 지정된 URL로 이동합니다.
        window.location.href = destinationURL;
    });


