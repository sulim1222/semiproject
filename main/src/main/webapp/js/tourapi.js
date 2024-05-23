document.addEventListener('DOMContentLoaded', function () {
    // 추가적인 클라이언트 측 기능 구현
    console.log("tourapi.js loaded");
    // 예: 팝업을 여는 기능, 리뷰 제출 기능 등
});


//
document.addEventListener('DOMContentLoaded', function () {
    const submenuParents = document.querySelectorAll('.submenu-parent');
    const restaurants = document.querySelectorAll('.restaurant');
    const popup = document.getElementById('popup');
    const popupImage = document.getElementById('popup-image');
    const popupTitle = document.getElementById('popup-title');
    const popupDetails = document.getElementById('popup-details');
    const popupStars = document.querySelectorAll('.popup-rating .star');
    const reviewsContainer = document.getElementById('reviews');
    let selectedRating = 0;
    let restaurantReviews = {};

    // 서브메뉴 관련 이벤트 처리
    submenuParents.forEach(parent => {
        parent.addEventListener('mouseenter', function () {
            const submenu = this.querySelector('.submenu');
            submenu.classList.add('active');
        });

        parent.addEventListener('mouseleave', function () {
            const submenu = this.querySelector('.submenu');
            submenu.classList.remove('active');
        });

        parent.querySelectorAll('.submenu a').forEach(link => {
            link.addEventListener('click', function (event) {
                event.preventDefault();
                window.location.href = this.getAttribute('href');
            });
        });
    });

    // 식당 팝업 관련 이벤트 처리
    restaurants.forEach(restaurant => {
        restaurant.addEventListener('click', function () {
            const name = this.getAttribute('data-name');
            const imageSrc = this.querySelector('img').src;
            const title = this.querySelector('h2').textContent;
            const details = this.getAttribute('data-details');

            popupImage.src = imageSrc;
            popupTitle.textContent = title;
            popupDetails.textContent = details;

            displayReviews(name);
            selectedRating = 0;
            popupStars.forEach(star => star.textContent = '☆');
            popup.style.display = 'flex';
        });
    });

    function displayReviews(restaurantName) {
        reviewsContainer.innerHTML = '';
        const reviews = restaurantReviews[restaurantName] || [];
        reviews.forEach(review => {
            const reviewEntry = createReviewEntry(review.name, review.rating, review.text);
            reviewsContainer.appendChild(reviewEntry);
        });
    }

    function createReviewEntry(name, rating, text) {
        const reviewEntry = document.createElement('div');
        reviewEntry.className = 'review-entry';
        reviewEntry.innerHTML = `
            <span class="review-icon">&#128100;</span>
            <div class="review-content">
                ${name} ${'★'.repeat(rating)}${'☆'.repeat(5 - rating)}
                <br>
                > ${text}
            </div>
        `;
        return reviewEntry;
    }

    // 리뷰 제출 이벤트 처리
    window.submitReview = function () {
        const reviewText = document.getElementById('review').value;
        const restaurantName = popupTitle.textContent;
        if (reviewText === "" || selectedRating === 0) {
            alert("별점과 리뷰를 모두 입력해주세요.");
            return;
        }

        const review = { name: `임${restaurantName.charAt(0)}ㅇ`, rating: selectedRating, text: reviewText };
        if (!restaurantReviews[restaurantName]) {
            restaurantReviews[restaurantName] = [];
        }
        restaurantReviews[restaurantName].push(review);

        displayReviews(restaurantName);
        document.getElementById('review').value = ''; // 리뷰 입력 필드 초기화
    };

    // 팝업 닫기 이벤트
    document.querySelector('.close').addEventListener('click', function () {
        popup.style.display = 'none';
    });

    popupStars.forEach(star => {
        star.addEventListener('click', function () {
            selectedRating = this.getAttribute('data-value');
            popupStars.forEach((star, index) => {
                star.textContent = index < selectedRating ? '★' : '☆';
            });
        });
    });
});
