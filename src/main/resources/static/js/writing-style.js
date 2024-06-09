//ANIMATION
const container = document.getElementById("container");
container.classList.add('move');
/*
const anim = document.getElementById("animation");
anim.addEventListener("click", () => {
    container.classList.add('out');
});
*/
//

// toggle create <-> check
const create_div = document.getElementById("create-div");
const check_div = document.getElementById("check-div");
check_div.style.display = "none";

const toggle_btn = document.getElementById("temp-btn");
var iscreate = true;
toggle_btn.addEventListener("click", () => {
    if (iscreate) {
        check_div.style.display = "block";
        create_div.style.display = "none";
        //toggle_btn.innerText = "기존 계정 로그인";
    } else {
        create_div.style.display = "block";
        check_div.style.display = "none";
        //toggle_btn.innerText = "새로운 계정 만들기";
    }
    iscreate = !iscreate;
});

