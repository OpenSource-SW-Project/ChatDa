const start_chat_form = document.getElementById("start-chat-form");
const name_input = document.getElementById("name-input");

start_chat_form.addEventListener("submit", start_chat);

//ANIMATION
const container = document.getElementById("container");
container.classList.add('move');
const anim = document.getElementById("animation");
anim.addEventListener("click",()=>{
    
});
//

const userRequest = new XMLHttpRequest();
userRequest.open('POST', url + `users/?userName=${name}`);
userRequest.send();
userRequest.onload = () => {
    if( userRequest.status === 200 ) {
        console.log(userRequest.response);
        var response = JSON.parse(userRequest.response);
        console.log(response.result);

        //init userID var
        const userId = response.result.userId;
        //send talk create request
        const talkRequest = new XMLHttpRequest();
        talkRequest.open('POST', url + `talk/?userId=${userId}`);
        talkRequest.send();
        talkRequest.onload = () => {
            if( talkRequest.status === 200 ) {
                var response = JSON.parse(talkRequest.response);
                const talkId = response.result.talkId;
                localStorage.setItem("userId", userId);
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
            
            //init userID var
            const userId = response.result.userId;
            //send talk create request
            const talkRequest = new XMLHttpRequest();
            talkRequest.open('POST', url + `talk/?userId=${userId}`);
            talkRequest.send();
            talkRequest.onload = () => {
                if( talkRequest.status === 200 ) {
                    var response = JSON.parse(talkRequest.response);
                    const talkId = response.result.talkId;
                    localStorage.setItem("userId", userId);
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