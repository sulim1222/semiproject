document.addEventListener('DOMContentLoaded', function () {
    // 모든 서브메뉴 부모 요소를 가져옴
    const submenuParents = document.querySelectorAll('.submenu-parent');

    // 각 서브메뉴 부모 요소에 이벤트 리스너 추가
    submenuParents.forEach(parent => {
        // 호버 이벤트 처리
        parent.addEventListener('mouseenter', function () {
            // 해당 서브메뉴 표시
            const submenu = this.querySelector('.submenu');
            submenu.classList.add('active');
        });

        // 마우스가 서브메뉴 영역을 벗어날 때 이벤트 처리
        parent.addEventListener('mouseleave', function () {
            // 해당 서브메뉴 숨김
            const submenu = this.querySelector('.submenu');
            submenu.classList.remove('active');
        });

        // 서브메뉴 항목 클릭 이벤트 처리
        const submenuLinks = parent.querySelectorAll('.submenu a');
        submenuLinks.forEach(link => {
            link.addEventListener('click', function (event) {
                // 기본 이벤트(링크 이동) 방지
                event.preventDefault();
                // 링크가 가리키는 페이지로 이동
                window.location.href = this.getAttribute('href');
            });
        });
    });
});