let fileCount = 1;

function addFileInput() {
    if (fileCount >= 5) {
        alert('파일은 최대 5개까지 추가할 수 있습니다.');
        return;
    }

    const fileInputsContainer = document.getElementById('fileInputs');
    const newFileInput = document.createElement('div');
    newFileInput.classList.add('file-input');
    newFileInput.innerHTML = `
        <input type="file" name="addFile">
        <button type="button" onclick="addFileInput()">추가</button>
        <button type="button" onclick="removeFileInput(this)">삭제</button>
    `;
    fileInputsContainer.appendChild(newFileInput);
    fileCount++;
}

function removeFileInput(button) {
    const fileInputsContainer = document.getElementById('fileInputs');
    if (fileInputsContainer.children.length > 1) {
        fileInputsContainer.removeChild(button.parentElement);
        fileCount--;
    } else {
        alert('최소 하나의 파일 입력창은 유지되어야 합니다(필수첨부X).');
    }
}

function toggleDropdown() {
    const dropdown = document.querySelector('.dropdown');
    dropdown.classList.toggle('active');
}

function selectCategory(category) {
    document.getElementById('categoryInput').value = category;
    toggleDropdown();
}

function validateForm() {
    const form = document.getElementById('inquiryForm');
    const requiredFields = form.querySelectorAll('input[required], textarea[required]');
    let isValid = true;

    requiredFields.forEach(field => {
        if (!field.value.trim() || (field.type === 'checkbox' && !field.checked)) {
            isValid = false;
        }
    });

    if (!isValid) {
        alert('필수정보를 모두 입력해주세요!');
    } else {
        form.submit();
    }
}


