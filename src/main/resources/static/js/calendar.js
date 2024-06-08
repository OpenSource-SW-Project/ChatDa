// get all diary
const member_id = sessionStorage.getItem("memberId");
const access_token = sessionStorage.getItem("accessToken");
var diary_data;

fetch(url + `diary/diaryList/${member_id}`, {
    method: 'GET',
    headers: {
        'accept': '*/*',
        'Authorization': 'Bearer ' + access_token
    }
})
    .then(response => response.json())
    .then(data => {
        console.log(data);
        diary_data = data.result.diaries;
        showCalendar(currentMonth, currentYear);
    })
    .catch(error => {
        console.error('Error:', error);
    });

let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();
let selectYear = document.getElementById("year");
let selectMonth = document.getElementById("month");

let months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

let monthAndYear = document.getElementById("monthAndYear");

function next() {
    currentYear = (currentMonth === 11) ? currentYear + 1 : currentYear;
    currentMonth = (currentMonth + 1) % 12;
    showCalendar(currentMonth, currentYear);
}

function previous() {
    currentYear = (currentMonth === 0) ? currentYear - 1 : currentYear;
    currentMonth = (currentMonth === 0) ? 11 : currentMonth - 1;
    showCalendar(currentMonth, currentYear);
}

function jump() {
    currentYear = parseInt(selectYear.value);
    currentMonth = parseInt(selectMonth.value);
    showCalendar(currentMonth, currentYear);
}

function showCalendar(month, year) {
    let firstDay = (new Date(year, month)).getDay();
    let daysInMonth = 32 - new Date(year, month, 32).getDate();

    let tbl = document.getElementById("calendar-body"); // body of the calendar

    // clearing all previous cells
    tbl.innerHTML = "";

    // filing data about month and in the page via DOM.
    monthAndYear.innerHTML = months[month] + " " + year;
    selectYear.value = year;
    selectMonth.value = month;

    // creating all cells
    let date = 1;

    //data for this month
    const data = diary_data.filter(diary => (diary.createdAt[0] == year) && (diary.createdAt[1] == month + 1));
    console.log(data);

    for (let i = 0; i < 6; i++) {
        // creates a table row
        let row = document.createElement("tr");

        //creating individual cells, filing them up with data.
        for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDay) {
                let cell = document.createElement("td");
                let cellText = document.createTextNode("");
                cell.appendChild(cellText);
                row.appendChild(cell);
            }
            else if (date > daysInMonth) {
                break;
            }
            else {
                const cell = document.createElement("td");
                const cellText = document.createElement("div");
                cellText.innerText = date;
                cellText.classList.add("cell-date");
                if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                    cell.classList.add("bg-primary");
                } // color today's date

                cell.appendChild(cellText);
                const diary = data.filter(element => (element.createdAt[2] == date))[0];
                if(diary) {
                    addSymbol(cell, diary.title, diary.createdAt, diary.content);
                }
                row.appendChild(cell);

                date++;
            }
        }
        tbl.appendChild(row); // appending each row into calendar body.
    }
}

function formatNumber(num) {
    // 숫자가 2자리 이상이면 그대로 문자열로 변환
    if (num >= 10) {
        return num.toString();
    }
    // 숫자가 1자리이면 앞에 0을 붙여 2자리 문자열로 변환
    else {
        return num.toString().padStart(2, '0');
    }
}

const diary = document.getElementById("diary");
const title_p = document.getElementById("title");
const date_p = document.getElementById("date");
const content_p = document.getElementById("content");

function addSymbol(cell, title, date, content) {
    const diarySymbol = document.createElement("div");
    diarySymbol.classList.add("symbol");
    diarySymbol.textContent = "📖";
    diarySymbol.addEventListener("click", function () {
        title_p.innerText = title;
        date_p.innerText = `${date[0]}년 ${date[1]}월 ${date[2]}일`;
        content_p.innerText = content;        

        diary.style.display = "block";
        window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
    });

    cell.appendChild(diarySymbol);
}