const title = document.getElementById("title");
const content = document.getElementById("content");
title.value = sessionStorage.getItem("title");
content.value = sessionStorage.getItem("content");

const container = document.getElementById("container");
container.classList.add("move");

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
const save_btn = document.getElementById("save-btn");
save_btn.addEventListener("click", saveDiary);

function saveDiary() {
    save_btn.disabled = true;

    if (title && content) {
        const access_token = sessionStorage.getItem("accessToken");
        const member_id = sessionStorage.getItem("memberId");
        const diary_id = sessionStorage.getItem("diaryId");

        // 여기서 저장 로직을 구현합니다.
        const data = {
            memberId: member_id,
            title: title.value,
            content: content.value
        };

        fetch(`http://localhost:8080/diary/${diary_id}`, {
            method: 'PATCH',
            headers: {
                'accept': '*/*',
                'Authorization': 'Bearer ' + access_token,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                window.location.href = "calendar";
            })
            .catch(error => {
                console.error('Error:', error);
            })
            .finally( () => {
                save_btn.disabled = false;
            });
    } else {
        alert('제목과 내용을 모두 입력해주세요.');
    }
}

const btn_1 = document.getElementById("btn-1");
const btn_2 = document.getElementById("btn-2");

var current = 1;
btn_1.classList.add("active");

btn_1.addEventListener("click", ()=> {
    btn_1.classList.add("active");
    btn_2.className = "toggle-btn";
    if (current != 1) {
        current = 1;
        title.value = sessionStorage.getItem("title");
        content.value = sessionStorage.getItem("content");
    }
})

btn_2.addEventListener("click", ()=> {
    btn_2.classList.add("active");
    btn_1.className = "toggle-btn";
    if (current != 2) {
        current = 2;
        title.value = sessionStorage.getItem("title2");
        content.value = sessionStorage.getItem("content2");
    }
})