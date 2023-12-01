const rDefault = 80
const zeroGraphic = 105
const acceptableX = [-2.0, -1.5, -1.0, -0.5, 0, 0.5, 1.0, 1.5, 2.0]

function checkY() {
    let y = document.getElementById("y").value;

    if(y === "-") {
        alert("Y не может быть \"-\"");
        return false;
    }
}

function checkR(e) {
    return PF('r').value === null
}

function showTable() {
    document.getElementById("info").setAttribute("style", "visibility: hidden");
    document.getElementById("table").setAttribute("style", "visibility: visible");
    document.getElementById("btn").setAttribute("onclick", "showInfo()");
}

function showInfo() {
    document.getElementById("info").setAttribute("style", "visibility: visible");
    document.getElementById("table").setAttribute("style", "visibility: hidden");
    document.getElementById("btn").setAttribute("onclick", "showTable()");
}

async function makeForm(e) {
    if (!checkR()) {
        let image = document.getElementById("canvas");
        let imageBorders = image.getBoundingClientRect();
        let r = PF('r').value;

        let xCoordinate = e.clientX - imageBorders.left;
        let yCoordinate = e.clientY - imageBorders.top;
        let x = Math.round(((xCoordinate - zeroGraphic) / rDefault * r) * 2) / 2;
        let y = ((zeroGraphic - yCoordinate) / rDefault * r).toFixed(1);
        if (acceptableX.includes(x) && y >= -5 && y <= 5) {
            changeX(x)
            await new Promise(resolve => setTimeout(resolve, 50));
            changeY(y)
            document.getElementById("info:submit").click()
        } else alert("Неподходящее значение для x или y")
    } else alert("Выберите радиус");
}

function changeX(x) {
    switch (x) {
        case -2.0:
            document.getElementById("info:x1").click()
            break
        case -1.5:
            document.getElementById("info:x2").click()
            break
        case -1.0:
            document.getElementById("info:x3").click()
            break
        case -0.5:
            document.getElementById("info:x4").click()
            break
        case 0:
            document.getElementById("info:x5").click()
            break
        case 0.5:
            document.getElementById("info:x6").click()
            break
        case 1.0:
            document.getElementById("info:x7").click()
            break
        case 1.5:
            document.getElementById("info:x8").click()
            break
        case 2.0:
            document.getElementById("info:x9").click()
            break
    }
}

function clearCanvas() {
    let image = document.getElementById("canvas");
    let context = image.getContext('2d');
    context.clearRect(0, 0, image.width, image.height);
}

function drawPointOnload() {
    let x = document.getElementById("info:lastPointX").value
    let y = document.getElementById("info:lastPointY").value
    let r = document.getElementById("info:lastPointR").value
    let hit = document.getElementById("info:lastPointHit").value
    hit = hit.toLowerCase() === "true"
    draw(x, y, r, hit)
}

function drawPoint() {
    clearCanvas()
    let x = document.getElementById("info:output-x").innerText
    console.log(x)
    let y = document.getElementById("info:y").value
    let r = PF('r').value
    let hit = checkHit(x, y, r)
    draw(x, y, r, hit)
}

function draw(x, y, r, hit) {
    let image = document.getElementById("canvas");
    let context = image.getContext('2d');
    let xCoordinate = zeroGraphic + x / r * rDefault;
    let yCoordinate = zeroGraphic - y / r * rDefault;
    if (hit) {
        context.fillStyle = 'rgb(173, 255, 47)'
    } else {
        context.fillStyle = 'rgb(250, 47, 47)';
    }
    context.strokeStyle = 'black';
    context.beginPath();
    context.arc(xCoordinate, yCoordinate, 3, 0, 2 * Math.PI);
    context.fill();
    context.stroke();
}

function checkHit(x, y, r) {
    return ((x <= 0 && y >= 0 && (y - 2 * x <= r)) ||
        (x >= 0 && y >= 0 && y <= r && x <= r) ||
        (x >= 0 && y <= 0 && (x * x + y * y <= (r / 2) * (r / 2))))
}