const date = new Date();
const pickedDate = new Date();
var notToday = false;
var lastDay = new Date();
var dayResults;


const updateEvents = () => {
	var date = pickedDate.getFullYear() + "-";
	if(pickedDate.getMonth()+1 <= 9)
	  date += "0" + (pickedDate.getMonth()+1)  + "-";
	else
	  date += (pickedDate.getMonth()+1) + "-";
	  
	if(pickedDate.getDate() <= 9) 
		date += "0" + pickedDate.getDate();
	else
	  date += pickedDate.getDate();
	
	$.ajax({
		url: "EventListServlet",
		type: "POST",
		data: {
			Date: date
		},
		success: function(result) {
			dayResults = result;
			var events = "";
			for(var i = 0; i < result.length; i++) {
				var start = result[i].time;
				start = start.split(" ")[1].split(":")[0];
				start = Number(start);
				
				if(start > 12) {
					start = start%12 + "pm"
				}
				else if (start == 12) {
					start += "pm"
				}
				else {
					start += "am"
				}
				
				var end = result[i].time_end;
				end = end.split(" ")[1].split(":")[0];
				end = Number(end);
				if(end > 12) {
					end = end%12 + "pm"
				}
				else if (end == 12) {
					end += "pm"
				}
				else {
					end += "am"
				}
				
				
				events += "<div id=event" + i + " onclick='displayEvent(" + i + ")'>";
				events += "<p id=eventName" + i + " class=\"eventName\">"+ result[i].name;
				events += "<p id=eventDetails" + i + " class=\"eventDetails\">" + result[i].organizer + " | " + start + " - " + end + "</p>";
				events += "<hr class=\"bottomLine\"></div>"
			}
			$("#eventList").empty().append(events);
		}
	});
}

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
 		  updateEvents();
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
updateEvents();	

function displayEvent(idx) {
	sessionStorage.setItem("eventJSON", JSON.stringify(dayResults[idx]));
	window.location.href = "eventDisplay.html";
}
