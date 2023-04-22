const date = new Date();
const pickedDate = new Date();
var notToday = false;
var lastDay = new Date();

window.addEventListener("load", (event) =>{
  const addbutt = document.getElementById("addButton");
  const logbutt = document.getElementById("logButton");
	if(sessionStorage.getItem('user') != null){
    addbutt.style.display = "block";
    logbutt.style.display = "none";
	}
	else{
    addbutt.style.display = "none";
    logbutt.style.display = "block";
	}
});

const updatePickedDate = () => {
  for (let i = 1; i <= lastDay; i++) {
    if (!(i === pickedDate.getDate() && pickedDate.getMonth() === date.getMonth())) 
    {
	  var mDay = '[name="day' + i + '"]';
	  var selector = document.querySelector(mDay);
	  if(!(selector === null)) {
        selector.addEventListener("click", () => {
          var d = 'day' + i;
	      var res = document.getElementsByName(d);

	      pickedDate.setDate(res[0].innerHTML);
          pickedDate.setMonth(date.getMonth());
          notToday = true;
          renderCalendar();

        });
      }
    }
  }
};

const renderCalendar = () => {
  date.setDate(1);
  //pickedDate.setDate(1);

  const monthDays = document.querySelector(".days");

  lastDay = new Date(
    date.getFullYear(),
    date.getMonth() + 1,
    0
  ).getDate();

  const prevLastDay = new Date(
    date.getFullYear(),
    date.getMonth(),
    0
  ).getDate();

  const firstDayIndex = date.getDay();

  const lastDayIndex = new Date(
    date.getFullYear(),
    date.getMonth() + 1,
    0
  ).getDay();

  const nextDays = 7 - lastDayIndex - 1;

  const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];
	
  //Update Month name in mini calendar
  document.querySelector(".date h1").innerHTML = months[date.getMonth()];

  //Update date on daily part
  document.querySelector(".activeDate").innerHTML = pickedDate.toDateString();

  let days = "";

  for (let x = firstDayIndex; x > 0; x--) {
    days += `<div class="prev-date">${prevLastDay - x + 1}</div>`;
  }

  for (let i = 1; i <= lastDay; i++) {
    if ((
	!notToday &&i === new Date().getDate() &&
      date.getMonth() === new Date().getMonth()) ||
	(notToday && i === pickedDate.getDate() && pickedDate.getMonth() === date.getMonth())
    ) {
      days += `<div class="today">${i}</div>`;
    } else if(date.getMonth() === new Date().getMonth() && date.getFullYear() === new Date().getFullYear() && i < new Date().getDate()) {
      days += `<div class="prev-date">${i}</div>`;
    } else {
      days += `<div class="day" name="day${i}">${i}</div>`;
    }
  }

  

  for (let j = 1; j <= nextDays; j++) {
    days += `<div class="next-date">${j}</div>`;
    monthDays.innerHTML = days;
  }
  
 updatePickedDate();
};

document.querySelector(".prev").addEventListener("click", () => {
  //Just dont look into next year or it breaks :`)
  if(date.getMonth()-1 >= new Date().getMonth() || date.getFullYear() > new Date().getFullYear()) {
    date.setMonth(date.getMonth() - 1);
    renderCalendar();
  }
});

document.querySelector(".next").addEventListener("click", () => {
  date.setMonth(date.getMonth() + 1);
  renderCalendar();
});

renderCalendar();

