/**
 * 
 */
document.addEventListener('DOMContentLoaded', function () {
    const foods = document.querySelectorAll('.food');
    const popup = document.getElementById('popup');
    const popupImage = document.getElementById('popup-image');
    const popupTitle = document.getElementById('popup-title');
    const popupDetails = document.getElementById('popup-details');
    const popupPhone = document.getElementById('popup-phone');
    const popupTime = document.getElementById('popup-time');
    const popupStars = document.querySelectorAll('.popup-rating .star');
    const reviewsContainer = document.getElementById('reviews');
    let selectedRating = 0;

    // 음식점 클릭 이벤트 처리
    if (foods) {
        foods.forEach(food => {
            food.addEventListener('click', function () {
                const name = this.getAttribute('data-name');
                const imageSrc = this.querySelector('img').src;
                const title = this.querySelector('h2').textContent;
                const details = this.getAttribute('data-details');
                const phone = this.getAttribute('data-phone');
                const time = this.getAttribute('data-time');
                const foodId = this.getAttribute('data-id');

                popupImage.src = imageSrc;
                popupTitle.textContent = title;
                popupDetails.textContent = `주소: ${details}`;
                popupPhone.textContent = `전화번호: ${phone}`;
                popupTime.textContent = `영업시간: ${time}`;
                document.getElementById('categoryId').value = foodId;

                // 리뷰 가져오기
                fetchReviews(foodId);
                selectedRating = 0;
                popupStars.forEach(star => star.textContent = '☆');
                popup.style.display = 'flex';
            });
        });
    }

    // 리뷰 가져오기
    function fetchReviews(foodId) {
        fetch(`/main/enjoy/getReviews?foodId=${foodId}`)
            .then(response => response.json())
            .then(data => displayReviews(data))
            .catch(error => console.error('Error fetching reviews:', error));
    }

    // 리뷰 표시
    function displayReviews(reviews) {
        reviewsContainer.innerHTML = '';
        reviews.forEach(review => {
            const reviewEntry = createReviewEntry(review.reviewContent, review.memberNo);
            reviewsContainer.appendChild(reviewEntry);
        });
    }

    // 리뷰 항목 생성
    function createReviewEntry(content, memberNo) {
        const reviewEntry = document.createElement('div');
        reviewEntry.className = 'review-entry';
        reviewEntry.innerHTML = `
            <span class="review-icon">&#128100;</span>
            <div class="review-content">
                ${memberNo} : ${content}
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

    // 팝업 닫기 이벤트
    const closeButton = document.querySelector('.close');
    if (closeButton) {
        closeButton.addEventListener('click', function () {
            popup.style.display = 'none';
        });
    }
});
