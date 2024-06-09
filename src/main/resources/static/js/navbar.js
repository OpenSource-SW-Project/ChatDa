const body = document.body;

const navbar = document.createElement("div");
navbar.id = "navbar";

const home = document.createElement("span");
home.innerText = "HOME";
home.classList.add("navbar-item");
home.addEventListener("click", ()=>{
    window.location.href = "/";
});

const bar = document.createElement("span");
bar.innerText = "|";

const logout = document.createElement("span");
logout.classList.add("navbar-item");
logout.innerText = "LOG OUT";
logout.addEventListener("click", ()=>{
    sessionStorage.clear();
    window.location.href = "/";
});

navbar.appendChild(home);
navbar.appendChild(bar);
navbar.appendChild(logout);
body.appendChild(navbar);