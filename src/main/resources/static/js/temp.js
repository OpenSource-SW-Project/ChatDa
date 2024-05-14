const content = document.getElementById("content");
content.value = localStorage.getItem("diary");

// 오늘 날짜를 표시하는 함수
function displayCurrentDate() {
    const today = new Date();
    const dateString = today.toLocaleDateString('ko-KR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        weekday: 'long'
    });
    document.getElementById('currentDate').textContent = dateString;
}

// 페이지 로드 시 오늘 날짜를 표시
window.onload = displayCurrentDate;

// 일기 저장 함수
function saveDiary() {
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;

    if (title && content) {
        // 여기서 저장 로직을 구현합니다.
        alert('일기가 저장되었습니다!');
    } else {
        alert('제목과 내용을 모두 입력해주세요.');
    }
}