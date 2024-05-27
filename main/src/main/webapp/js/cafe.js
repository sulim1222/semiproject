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
                popup.setAttribute('data-cafe-id', cafeId);

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
            reviewsContainer.innerHTML = '';	//초기화 
            //카페 리뷰 가져오기, 없으면 빈배열  
            const reviews = cafeReviews[cafeName] || [];
            // 각 리뷰 반복 및 리뷰 항목 생성 & 컨테이너 추가
            reviews.forEach(review => {
                const reviewEntry = createReviewEntry(review.name, review.rating, review.text);
                reviewsContainer.appendChild(reviewEntry);
            });
        }
    }
	
	//리뷰 항목 설정 
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

    // 리뷰 제출 이벤트 처리
    window.submitReview = function () {
        const reviewText = document.getElementById('review').value;
        const cafeName = popupTitle.textContent;
        const cafeId = popup.getAttribute('data-cafe-id');
        if (reviewText === "" || selectedRating === 0) {
            alert("별점과 리뷰를 모두 입력해주세요.");
            return;
        }

	     // 이름을 암호화하는 함수
		function encryptName(name) {
	    // 성을 제외한 이름의 첫 3글자를 추출하여 암호화
	    const firstName = name.substring(1, 4);
	    const encryptedName = firstName.replace(/[a-zA-Z가-힣]/g, '*'); // '*'로 대체
	    return encryptedName;
	}
	
		// 이름 암호화
		const review = { 
	    cafeId: cafeId,
	    name: `${encryptName(cafeName)}ㅇ`, // 이름 암호화하여 사용
	    rating: selectedRating, 
	    text: reviewText 
	};

		//제출한 리뷰 처리(user->서버->결과에 따라 화면 메세지 표현)
        fetch('<%=request.getContextPath()%>/enjoy/submitReview', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(review)
        })
        .then(response => response.json())	//서버 응답 
        .then(data => {
            if (data.success) {
                if (!cafeReviews[cafeName]) {	//카페 리뷰 배열 없을 경우
                    cafeReviews[cafeName] = [];
                }
                cafeReviews[cafeName].push(review);	

                displayReviews(cafeName);	
                document.getElementById('review').value = ''; // 리뷰 입력 필드 초기화
                alert('리뷰가 성공적으로 제출되었습니다.');
            } else {
                alert('리뷰 제출에 실패했습니다. 다시 시도해주세요.');
            }
        });
    };

    // 팝업 닫기 이벤트
    const closeButton = document.querySelector('.close');
    if (closeButton) {
        closeButton.addEventListener('click', function () {
            popup.style.display = 'none';
        });
    }

    if (popupStars) {
        popupStars.forEach(star => {
            star.addEventListener('click', function () {
                selectedRating = this.getAttribute('data-value');
                popupStars.forEach((star, index) => {
                    star.textContent = index < selectedRating ? '★' : '☆';
                });
            });
        });
    }
});
