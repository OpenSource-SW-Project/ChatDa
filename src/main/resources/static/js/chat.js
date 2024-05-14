const chat_form = document.getElementById("chat-form");
const chat_input = document.getElementById("chat-input");
const chat_btn = document.getElementById("chat-btn");
const chat_log = document.getElementById("chat-log");

chat_form.addEventListener("submit", send_chat)

var chat_count = 0;

var user_name = localStorage.getItem("userName");
var user_id = localStorage.getItem("userId");
var talk_id = localStorage.getItem("talkId");
console.log("got user name : " + user_name);
console.log("got user id : " + user_id);
console.log("got talk id : " + talk_id);

//const url = "http://43.202.126.252:8080/";
const url = "http://localhost:8080/";

init_chat();

function init_chat() {

    if (!get_today_diary()) {
        create_chatDa_chat("오늘은 이미 일기를 작성했어!! 내일 다시 찾아줘!!");
        chat_btn.disabled = true;
        return;
    }

    create_chatDa_chat("안녕! 나는 챗다야. 오늘 하루 중 가장 기억에 남는 일은 뭐야?");

    if (chatings = get_today_chat()) {
        while (chatings) {
            // chatDa chat, user chat 번갈아가면서 입력
            chat_count++;
        }

    }

    create_chatDa_chat("안녕! 나는 챗다야. 오늘 하루 중 가장 기억에 남는 일은 뭐야?");

}

function send_chat(event){
    const user_message = chat_input.value;
    console.log(user_message);
    if(user_message != ""){
        console.log("send message");

        create_user_chat(user_message);

        chat_log.scrollTop = chat_log.scrollHeight;
        chat_input.value = "";
        chat_btn.disabled = true;

        //send request & get response
        const chatRequest = new XMLHttpRequest();
        chatRequest.open('POST', url + `api/chat?userId=${user_id}`);
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
    }
}

function receive_chat(event, response){
    chat_btn.disabled = false;

    create_chatDa_chat(response);

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

function endChat(event){

    //create diary
    const diaryRequest = new XMLHttpRequest();
    diaryRequest.open('POST', url + `diary?userId=${user_id}`);
    diaryRequest.setRequestHeader("Content-Type", "application/json");
    var body = JSON.stringify({
        talkId : talk_id,
        userPrompt : ""
    });
    diaryRequest.send(body);
    end_chat_btn.disabled = true;
    alert("일기를 작성 중입니다. 잠시만 기다려 주세요(30~40초)");
    diaryRequest.onload = () => {
        if( diaryRequest.status === 200 ) {
            
            const response = JSON.parse(diaryRequest.response);
            console.log(response);
            localStorage.setItem("diary", response.result.content);
            window.location.href = "temp";
        } else {
            console.error("Error", diaryRequest.status, diaryRequest.statusText);
        }
    };
}

function create_user_chat(user_message) {
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
}

function create_chatDa_chat(new_message) {
    const new_response_wrapper = document.createElement("div");
    new_response_wrapper.classList.add("chat-wrapper");

    const new_response_box = document.createElement("div");
    new_response_box.classList.add("chat-box");

    const new_response = document.createElement("div");
    new_response.classList.add("chat-bubble");
    new_response.innerText = new_message;

    new_response_wrapper.appendChild(new_response);
    new_response_wrapper.appendChild(new_response_box);
    chat_log.appendChild(new_response_wrapper);
}

function get_today_diary() {
    var today = new Date();
    var year = today.getFullYear();
    var month = (today.getMonth() + 1).toString().padStart(2, '0');
    var day = today.getDate().toString().padStart(2, '0');

    const formattedDate = year + '-' + month + '-' + day;

    const http = new XMLHttpRequest();
    const query = url + `api/DB/diary?date=${formattedDate}&userId=${user_id}`;
    console.log(query);
    http.open('GET', query);
    http.send();
    http.onload = () => {
        if (http.status === 200) {
            //console.log(http.responseText);

            if (http.response != "") {
                console.log("Today diary already exist!!");
                return true;
            }
            else {
                console.log(`No diary user = ${user_id}, date=${formattedDate}`);
                return false;
            }
        } else {
            console.error("Error", http.status, http.statusText);
        }
    };

    return false;
}
