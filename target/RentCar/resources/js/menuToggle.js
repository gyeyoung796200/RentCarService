let toggleBtn = document.querySelector(".navbar__toggleBtn");
let menu = document.querySelector(".navbar__menu");
let user = document.querySelector(".navbar__user");
function toggle() {
    menu.classList.toggle("active");
    user.classList.toggle("active");
}
toggleBtn.addEventListener("click", toggle);
