const start_chat_form = document.getElementById("start-chat-form");
const name_input = document.getElementById("name-input");

start_chat_form.addEventListener("submit", start_chat);

function start_chat(event) {
    const name = name_input.value;
    sessionStorage.setItem("userName", name);

    //create user & create new talk
    const userRequest = new XMLHttpRequest();
    const url = `http://43.202.126.252:8080/`;
    userRequest.open('POST', url + 'users');

    var data = JSON.stringify({
        name: name
    });

    userRequest.send(data);
    userRequest.onload = () => {
        if( userRequest.status === 200 ) {
            console.log(userRequest.response);
            const response = JSON.parse(userRequest.response);
            console.log(response);
            
            //init userID var
            const userId = '';
            //send talk create request
            const talkRequest = new XMLHttpRequest();
            talkRequest.open('POST', url + '/talk' + userId);
            data = JSON.stringify({
                userId: userId
            });
            talkRequest.send(data);
            talkRequest.onload = () => {
                if( talkRequest.status === 200 ) {
                    sessionStorage.setItem("userId", userId);
                    sessionStorage.setItem("talkId", talkId);
                    window.location.href = "chat";
                } else {
                    console.error("Error", talkRequest.status, talkRequest.statusText);
                    window.location.href = "chat";  
                }
            }

        } else {
            console.error("Error", userRequest.status, userRequest.statusText);
            window.location.href = "chat";
        }
    };  
    
}