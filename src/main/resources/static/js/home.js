//ANIMATION
const container = document.getElementById("container");
container.classList.add('move');
const anim = document.getElementById("animation");
anim.addEventListener("click", () => {

});
//

//start chat function
/*
function start_chat(event) {
    const name = name_input.value;
    localStorage.setItem("userName", name);

    //create user & create new talk
    const userRequest = new XMLHttpRequest();
    userRequest.open('POST', url + `users/?userName=${name}`);
    userRequest.send();
    userRequest.onload = () => {
        if( userRequest.status === 200 ) {
            console.log(userRequest.response);
            var response = JSON.parse(userRequest.response);
            console.log(response.result);
            
            //init memberId var
            const memberId = response.result.memberId;
            //send talk create request
            const talkRequest = new XMLHttpRequest();
            talkRequest.open('POST', url + `talk/?memberId=${memberId}`);
            talkRequest.send();
            talkRequest.onload = () => {
                if( talkRequest.status === 200 ) {
                    var response = JSON.parse(talkRequest.response);
                    const talkId = response.result.talkId;
                    localStorage.setItem("memberId", memberId);
                    localStorage.setItem("talkId", talkId);
                    //if success move to chat page
                    container.classList.add('out');
                    setTimeout(() => {
                        window.location.href = "chat";
                    }, "1000");
                } else {
                    console.error("Error", talkRequest.status, talkRequest.statusText);
                }
            }
        } else {
            console.error("Error", userRequest.status, userRequest.statusText);
        }
    };
}
*/

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

const show_login = document.getElementById("show-login");
const show_signup = document.getElementById("show-signup");

show_login.addEventListener("click", () => {
    login_div.style.display = "block";
    signup_div.style.display = "none";
});
show_signup.addEventListener("click", () => {
    signup_div.style.display = "block";
    login_div.style.display = "none";
});

//login function
const login_form = document.getElementById("login-form");
login_form.addEventListener("submit", login);
function login(event) {
    event.preventDefault();
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
            if (data.isSuccess){
                console.log(data);
                sessionStorage.setItem("userId", data.result.userId);
                sessionStorage.setItem("accessToken", data.result.accessToken);
                alert("로그인 성공");
                window.location.href = "/";
            } else {
                alert("로그인 실패 : " + data.result);
            }
        })
        .catch(error => {
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