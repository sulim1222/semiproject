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


const button = document.querySelector("#room_reservation_btn");
button.addEventListener("click", function() {
    window.location.href = "reservation.html";
});

