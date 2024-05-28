document.addEventListener('DOMContentLoaded', function () {
    const cafes = document.querySelectorAll('.cafe');
    const popup = document.getElementById('popup');
    const popupImage = document.getElementById('popup-image');
    const popupTitle = document.getElementById('popup-title');
    const popupDetails = document.getElementById('popup-details');
    const popupStars = document.querySelectorAll('.popup-rating .star');
    const reviewsContainer = document.getElementById('reviews');
    let selectedRating = 0;
    let cafeReviews = {};

    // 카페 클릭 이벤트 처리
    if (cafes) {
        cafes.forEach(cafe => {
            cafe.addEventListener('click', function () {
                const name = this.getAttribute('data-name');
                const imageSrc = this.querySelector('img').src;
                const title = this.querySelector('h2').textContent;
                const details = this.getAttribute('data-details');
                const cafeId = this.getAttribute('data-id');

                popupImage.src = imageSrc;
                popupTitle.textContent = title;
                popupDetails.textContent = details;
                document.getElementById('categoryId').value = cafeId;

                displayReviews(name);
                selectedRating = 0;
                popupStars.forEach(star => star.textContent = '☆');
                popup.style.display = 'flex';
            });
        });
    }

    // 특정 카페 리뷰 표시 
    function displayReviews(cafeName) {
        if (reviewsContainer) {
            reviewsContainer.innerHTML = '';    // 초기화 
            // 카페 리뷰 가져오기, 없으면 빈 배열  
            const reviews = cafeReviews[cafeName] || [];
            // 각 리뷰 반복 및 리뷰 항목 생성 & 컨테이너 추가
            reviews.forEach(review => {
                const reviewEntry = createReviewEntry(review.name, review.rating, review.text);
                reviewsContainer.appendChild(reviewEntry);
            });
        }
    }

    // 리뷰 항목 설정 
    function createReviewEntry(name, rating, text) {
        const reviewEntry = document.createElement('div');
        reviewEntry.className = 'review-entry';
        reviewEntry.innerHTML = `
            <span class="review-icon">&#128100;</span>
            <div class="review-content">
                ${name} ${'★'.repeat(rating)}${'☆'.repeat(5 - rating)}
                <br>
                ${text}
            </div>
        `;
        return reviewEntry;
    }

    // 별점 클릭 이벤트 처리
    if (popupStars) {
        popupStars.forEach(star => {
            star.addEventListener('click', function () {
                selectedRating = this.getAttribute('data-value');
                document.getElementById('rating').value = selectedRating;
                popupStars.forEach((star, index) => {
                    star.textContent = index < selectedRating ? '★' : '☆';
                });
            });
        });
    }
    
   document.addEventListener('DOMContentLoaded', function () {
    const reviewForm = document.getElementById('reviewForm');
    if (reviewForm) {
        reviewForm.addEventListener('submit', function (event) {
            event.preventDefault();
            const formData = new FormData(reviewForm);
            fetch(reviewForm.action, {
                method: 'POST',
                body: formData
            }).then(response => response.json())
              .then(data => {
                  if (data.success) {
                      alert('리뷰가 성공적으로 제출되었습니다.');
                      // 팝업 닫기 또는 다른 작업 수행
                      document.getElementById('popup').style.display = 'none';
                      // 필요 시 리뷰 목록 갱신
                  } else {
                      alert('리뷰 제출에 실패했습니다.');
                  }
              }).catch(error => {
                  console.error('Error:', error);
                  alert('리뷰 제출 중 오류가 발생했습니다.');
              });
        });
    }
});



    // 팝업 닫기 이벤트
    const closeButton = document.querySelector('.close');
    if (closeButton) {
        closeButton.addEventListener('click', function () {
            popup.style.display = 'none';
        });
    }
});
