const disTime = document.querySelector(".current__time");

function time() {
    const date = new Date();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();

    disTime.innerHTML = `${hours < 10 ? `0${hours}` : `${hours}`}:${
        minutes < 10 ? `0${minutes}` : `${minutes}`
    }:${seconds < 10 ? `0${seconds}` : `${seconds}`}`;
}

function init() {
    time();
    setInterval(time, 1000);
}

init();
