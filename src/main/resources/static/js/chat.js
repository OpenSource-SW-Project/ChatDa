//ANIMATION
const chat_box = document.getElementById("chat-box");
chat_box.classList.add('move');
//

const chat_form = document.getElementById("chat-form");
const chat_input = document.getElementById("chat-input");
const chat_btn = document.getElementById("chat-btn");
const chat_log = document.getElementById("chat-log");

chat_form.addEventListener("submit", send_chat)

var chat_count = 0;

var member_id = sessionStorage.getItem("memberId");
var talk_id = sessionStorage.getItem("talkId");
var access_token = sessionStorage.getItem("accessToken");
console.log("got member id : " + member_id);
console.log("got talk id : " + talk_id);
console.log("got access token : " + access_token);

function send_chat(event) {
    const user_message = chat_input.value;
    console.log(user_message);
    if (user_message != "") {
        console.log("send message");

        const new_chat_wrapper = document.createElement("div");
        new_chat_wrapper.classList.add("chat-wrapper");

        const new_chat_box = document.createElement("div");
        new_chat_box.classList.add("chat-box");

        const new_chat = document.createElement("div");
        new_chat.classList.add("chat-bubble");
        new_chat.innerText = user_message;

        new_chat_wrapper.appendChild(new_chat_box);
        new_chat_wrapper.appendChild(new_chat);
        chat_log.appendChild(new_chat_wrapper);

        chat_log.scrollTop = chat_log.scrollHeight;
        chat_input.value = "";
        chat_btn.disabled = true;

        //send request & get response
        const data = {
            talkId: talk_id,
            userPrompt: user_message
        };

        fetch(url + `api/chat?memberId=${member_id}`, {
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
                receive_chat(null, data.result.message);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        /*
        const chatRequest = new XMLHttpRequest();
        chatRequest.open('POST', url + `api/chat?memberId=${user_id}`);
        chatRequest.setRequestHeader("Content-Type", "application/json");

        var body = JSON.stringify({
            talkId : talk_id,
            userPrompt : user_message
        });
        chatRequest.send(body);
        chatRequest.onload = () => {
            if( chatRequest.status === 200 ) {
                console.log(chatRequest.response);
                const response = JSON.parse(chatRequest.response);
                receive_chat(null, response.result.message);
                
            } else {
                console.error("Error", chatRequest.status, chatRequest.statusText);
            }
        };
        */
    }
}

function receive_chat(event, response) {
    chat_btn.disabled = false;

    const new_response_wrapper = document.createElement("div");
    new_response_wrapper.classList.add("chat-wrapper");

    const new_response_box = document.createElement("div");
    new_response_box.classList.add("chat-box");

    const new_response = document.createElement("div");
    new_response.classList.add("chat-bubble");
    new_response.innerText = response;

    new_response_wrapper.appendChild(new_response);
    new_response_wrapper.appendChild(new_response_box);
    chat_log.appendChild(new_response_wrapper);

    chat_log.scrollTop = chat_log.scrollHeight;

    chat_count = chat_count + 1;
    if (chat_count === 10) {
        chat_btn.disabled = true;
        alert("데모 버전에서 여기까지 대화를 제공합니다. 일기 생성 버튼을 눌러 일기를 생성해보세요!");
    }
}
//

const end_chat_btn = document.getElementById("end-chat-btn");
end_chat_btn.addEventListener("click", endChat);

function endChat(event) {
    end_chat_btn.disabled = true;
    chat_box.classList.add('out');
    //create diary
    const data = {
        talkId: talk_id,
        userPrompt: ""
    };

    fetch(url + `diary?memberId=${member_id}`, {
        method: 'POST',
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
            const result = data.result;
            sessionStorage.setItem("title", result.title);
            sessionStorage.setItem("content", result.content);
            sessionStorage.setItem("diaryId", result.diaryId);
            window.location.href = "temp";
        })
        .catch(error => {
            console.error('Error:', error);
        });
    /*
    const diaryRequest = new XMLHttpRequest();
    diaryRequest.open('POST', url + `diary?memberId=${member_id}`);
    diaryRequest.setRequestHeader("Content-Type", "application/json");
    var body = JSON.stringify({
        talkId: talk_id,
        userPrompt: ""
    });
    diaryRequest.send(body);
    end_chat_btn.disabled = true;
    alert("일기를 작성 중입니다. 잠시만 기다려 주세요(30~40초)");
    diaryRequest.onload = () => {
        if (diaryRequest.status === 200) {

            const response = JSON.parse(diaryRequest.response);
            console.log(response);
            localStorage.setItem("diary", response.result.content);
            window.location.href = "temp";
        } else {
            console.error("Error", diaryRequest.status, diaryRequest.statusText);
        }
    };
    */
}

//typing bubble
var count = 0;
var interval;
const enableBtn = document.getElementById("enable-wait-btn");
const disableBtn = document.getElementById("disable-wait-btn");

function enableWait() {
    console.log("enabled");

    const new_response_wrapper = document.createElement("div");
    new_response_wrapper.id = "wait-wrapper";
    new_response_wrapper.classList.add("chat-wrapper");

    const new_response_box = document.createElement("div");
    new_response_box.classList.add("chat-box");

    const new_response = document.createElement("div");
    new_response.classList.add("chat-bubble");
    new_response.id = "wait-bubble";
    new_response.innerText = ".....";

    new_response_wrapper.appendChild(new_response);
    new_response_wrapper.appendChild(new_response_box);
    chat_log.appendChild(new_response_wrapper);

    count = 0;
    interval = setInterval(nextWait, 100);
}

function disableWait() {
    console.log("disabled");
    document.getElementById("wait-wrapper").remove();
    clearInterval(interval);
}

enableBtn.addEventListener("click", enableWait);
disableBtn.addEventListener("click", disableWait);

function nextWait() {
    const bubble = document.getElementById("wait-bubble");
    var wait_text = "";
    if (count < 5) {
        for (i = 0; i < count; i++) {
            wait_text += ".";
        }
        wait_text += "·";
        for (; i < 4; i++) {
            wait_text += ".";
        }
        bubble.innerText = wait_text;
    } else {
        bubble.innerText = ".....";
    }

    count++;
    if (count > 10) {
        count = 0;
    }
}

//PENGU
const pengu = document.getElementById("penguin");
setTimeout(() => {
    pengu.className = "in";
}, "5000");
