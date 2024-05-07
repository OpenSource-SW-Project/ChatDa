let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();
let selectYear = document.getElementById("year");
let selectMonth = document.getElementById("month");

let months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

let monthAndYear = document.getElementById("monthAndYear");
showCalendar(currentMonth, currentYear);


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
    for (let i = 0; i < 6; i++) {
        // creates a table row
        let row = document.createElement("tr");

        let user = "UserA";

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
                let cell = document.createElement("td");
                let cellText = document.createTextNode(date);
                if (date === today.getDate() && year === today.getFullYear() && month === today.getMonth()) {
                    cell.classList.add("bg-primary");
                } // color today's date

                cell.appendChild(cellText);

                (function (date, month, year) {
                    // HTTP 요청 보내기
                    const http = new XMLHttpRequest();
                    const url = `http://localhost:8080/api/DB/diary?date=${year}-${formatNumber(month + 1)}-${formatNumber(date)}&user=${user}`;
                    //console.log(url);
                    http.open('GET', url);
                    http.send();
                    http.onload = () => {
                        if (http.status === 200) {
                            //console.log(http.responseText);

                            if (http.response != "") {
                                const response = JSON.parse(http.response);

                                let diaryButton = document.createElement("button");

                                diaryButton.textContent = "📖";
                                diaryButton.addEventListener("click", function(){
                                    let diary = document.getElementById("diary");
                                    let title = document.getElementById("title");
                                    let date_info = document.getElementById("date");
                                    let content = document.getElementById("content");

                                    diary.style.display = "block";
                                    title.innerText = response.title;
                                    date_info.innerText = response.date;
                                    content.innerText = response.content;
                                });

                                cell.appendChild(diaryButton);

                                console.log(response.title);
                            }
                        } else {
                            console.error("Error", http.status, http.statusText);
                        }
                    };
                })(date, month, year);

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