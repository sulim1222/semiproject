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


const button = document.querySelector("#room_reservation_btn");
button.addEventListener("click", function() {
    window.location.href = "main/revation";
});


function goUp(){
    window.scrollTo({
        top: 0,
        left: 0,
        behavior: 'smooth'
    });
}



// window.addEventListener('scroll', function() {
//     var scrollPosition = window.pageYOffset || document.documentElement.scrollTop;
//     var title = document.querySelector('.main-title');
    
//     if (scrollPosition > 300) { // 원하는 위치(예: 300px)로 설정
//         title.style.position = 'fixed';
//         title.style.top = '10px'; // 원하는 고정 위치로 설정
//         title.style.left = '50%'; // 가운데 정렬을 위해 50%로 설정
//         title.style.transform = 'translateX(-50%)'; // 가운데 정렬을 위해 사용
//         title.style.zIndex = '999'; // 다른 요소 위에 표시되도록 설정
//     } else {
//         title.style.position = 'static'; // 일정 스크롤 위치 이하에 있을 때는 원래 위치로 돌아감
//     }
// });