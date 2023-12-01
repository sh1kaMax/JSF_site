window.onload = function () {
    let clock = document.getElementById("clock")
    clock.innerHTML = new Date().toLocaleString()
    window.setInterval(function () {
        clock.innerHTML = new Date().toLocaleString()
    }, 12000)
}