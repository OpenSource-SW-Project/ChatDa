const chat_form = document.getElementById("chat-form");
const chat_input = document.getElementById("chat-input");
const chat_btn = document.getElementById("chat-btn");
const chat_log = document.getElementById("chat-log");

chat_form.addEventListener("submit", send_chat)

var user_name = sessionStorage.getItem("name");
console.log("got user name : " + user_name);

function send_chat(event){
    const user_message = chat_input.value;
    if(user_message != ""){
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
        const http = new XMLHttpRequest();
        const url = `http://43.202.126.252:8080/api/chat?userPrompt=${user_message}`;
        http.open('GET', url);
        http.send();
        http.onload = () => {
            if( http.status === 200 ) {
                console.log(http.response);
                const response = JSON.parse(http.response);
                receive_chat(null, response.result.message);
            } else {
                console.error("Error", http.status, http.statusText);
            }
        };
    }
}

function receive_chat(event, response){
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
}
//
