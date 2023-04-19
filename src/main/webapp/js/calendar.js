//including ajax for jQuery
src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js";

//function to go to add event page
 function AddEvent() {
	 e.preventDefault();
	 window.location.href = 'addEvent.html';
 }
 
 //function to go to login register page
 function LoginRegister() {
	 e.preventDefault();
	 window.location.href = 'login.html';
 }
 
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
			 Object.keys(data).forEach(function(key){
				 //create new div
				 var newDiv = document.createElement("div");
				 newDiv.setAttribute("id", "event");
				 //when an event is clicked, go to eventDisplay page 
				 //(is there any way to share which event it is?)
				 newDiv.onclick = function() {
					 e.preventDefault();
					 window.location.href = 'eventDisplay.html';
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