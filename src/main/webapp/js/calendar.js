//including ajax for jQuery
src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js";

//function to go to add event page
const addeventclick = document.querySelector(".addEventBtn");
addeventclick.addEventListener("click", AddEvent);
function AddEvent(e) {
	 e.preventDefault();
	 window.location.href = 'addEvent.html';
 }
 
//event listener and function to go to login register page
const loginregisterclick = document.querySelector(".loginRegBtn");
loginregisterclick.addEventListener("click", LoginRegister);
function LoginRegister(e) {
	 e.preventDefault();
	 window.location.href = 'login.html';
 }

//function to display buttons based on login status
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
 
//function to display event information on a current day
function DisplayEventsInformation(){
	 var baseURL = window.location.origin + "/USCheduler/";
	 var url = new URL("EventListServlet", baseURL);
	 fetch(url)
		 .then(data => {
			 console.log(data.text())
			 return data.json();
		 })
		 .then(data => {
			 let eventNum = 1;
			 Object.keys(data).forEach(function(key){
				 //create new div, with incrementing id = event1, event2, event3, etc.
				 var newDiv = document.createElement("div");
				 let eventid = eventNum.toString();
				 newDiv.setAttribute("id", eventid);
				 eventNum += 1;
				 
				 //when an event is clicked, go to eventDisplay page 
				 newDiv.onclick = function(e, eventid) {
					 e.preventDefault();
					 window.location.href = 'eventDisplay.html';
					 loadEventInformation(eventid);
				 }
				 
				 var eventName = document.createElement("p");
				 eventName.innerText = key['name'];
				 eventName.setAttribute("id", "eventName");
				 eventName.setAttribute("class", "eventName");
				 newDiv.appendChild(eventName);
				 
				 var eventDetails = document.createElement("p");
				 eventDetails.innerText = key['organizer'] + key['time'] + '-' + key['time-end'];
				 eventDetails.setAttribute("id", "eventDetails");
				 eventDetails.setAttribute("class", "eventDetails");
				 newDiv.appendChild(eventDetails);
				 
				 var hr = document.createElement("hr");
				 hr.setAttribute("class", "bottomLine");
				 newDiv.appendChild(hr);
				 
			 	 //insert div into html//gotta give the class scroll the id scroll
			 	 document.getElementById('scroll').appendChild(newDiv);
			 }); 
		 }).catch(function(error){
			 console.log('error in loading event info', error)
		 });
}