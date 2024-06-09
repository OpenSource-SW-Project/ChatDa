//ANIMATION
const container = document.getElementById("container");
container.classList.add('move');
const anim = document.getElementById("animation");
anim.addEventListener("click", () => {
    container.classList.add('out');
});
//

// toggle usercheck <-> main func
const usercheck_container = document.getElementById("usercheck-container");
const button_container = document.getElementById("button-container");

if (sessionStorage.getItem("accessToken") === null) {
    button_container.style.display = "none";
} else {
    usercheck_container.style.display = "none";
}

// toggle login <-> signup
const login_div = document.getElementById("login-div");
const signup_div = document.getElementById("signup-div");
signup_div.style.display = "none";

const toggle_btn = document.getElementById("toggle-btn");
var isLogin = true;
toggle_btn.addEventListener("click", () => {
    if (isLogin) {
        signup_div.style.display = "block";
        login_div.style.display = "none";
        toggle_btn.innerText = "기존 계정 로그인";
    } else {
        login_div.style.display = "block";
        signup_div.style.display = "none";
        toggle_btn.innerText = "새로운 계정 만들기";
    }
    isLogin = !isLogin;
});

//login function
const login_form = document.getElementById("login-form");
const login_btn = document.getElementById("login-btn");
login_form.addEventListener("submit", login);
function login(event) {
    event.preventDefault();
    login_btn.disabled = true;
    const username = document.getElementById("login-username-input").value;
    const password = document.getElementById("login-password-input").value;

    const data = {
        username: username,
        password: password
    };

    fetch(url + 'users/signIn', {
        method: 'POST',
        headers: {
            'accept': '*/*',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            login_btn.disabled = false;
            if (data.isSuccess) {
                console.log(data);
                sessionStorage.setItem("memberId", data.result.memberId);
                sessionStorage.setItem("accessToken", data.result.accessToken);
                alert("로그인 성공");
                window.location.href = "/";
            } else {
                alert("로그인 실패 : " + data.result);
            }
        })
        .catch(error => {
            login_btn.disabled = false;
            alert("요청 실패");
            console.error('Error:', error);
        });
}

//signup function
const signup_form = document.getElementById("signup-form");
signup_form.addEventListener("submit", signup);
function signup(event) {
    event.preventDefault();
    const name = document.getElementById("signup-name-input").value;
    const username = document.getElementById("signup-username-input").value;
    const password = document.getElementById("signup-password-input").value;
    const password_check = document.getElementById("signup-passwordcheck-input").value;

    if (password != password_check) {
        alert("비밀번호 확인이 올바르지 않습니다.");
    } else {
        const data = {
            name: name,
            username: username,
            password: password
        };

        fetch(url + 'users/signUp', {
            method: 'POST',
            headers: {
                'accept': '*/*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                alert("계정 생성을 성공하였습니다.");
                window.location.href = "/";
            })
            .catch(error => {
                alert("계정 생성을 실패하였습니다.");
                console.error('Error:', error);
            });
    }
}

//start chat
const chat_btn = document.getElementById("chat-btn");
chat_btn.addEventListener("click", start_chat);
function start_chat(event) {
    const member_id = sessionStorage.getItem("memberId");
    const access_token = sessionStorage.getItem("accessToken");
    fetch(url + `talk/?memberId=${member_id}`, {
        method: 'POST',
        headers: {
            'accept': '*/*',
            'Authorization': 'Bearer ' + access_token
        },
        body: ''
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            sessionStorage.setItem("talkId", data.result.talkId);
            change_page("/chat");
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

//go to calendar page
const diary_btn = document.getElementById("diary-btn");
diary_btn.addEventListener("click", () => {
    //window.location.href = "/calendar";
    change_page("/calendar");
});

//go to writing style page
const writing_style_btn = document.getElementById("writing-style-btn");
writing_style_btn.addEventListener("click", () => {
    change_page("/writing-style");
});

function change_page(page) {
    container.classList.add('out');
    setTimeout(() => {
        window.location.href = page;
    }, "1000");
}
