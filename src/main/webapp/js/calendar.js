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
 //use ajax to get information from servlet + json strings
 //use for loop to print out event information in divs
async function DisplayEventsInformation(){
	 var baseURL = window.location.origin + "/USCheduler/";
	 var url = new URL("EventListServlet", baseURL);
	 fetch(url)
		 .then(data => {
			 console.log(data.text())
			 return data.json();
		 })
		 .then(data => {
			 Object.keys(data).forEach(function(key){
				 //create a new div and display it!!
				 var newDiv = document.createElement("div");
				 //newDiv.textContent = ;
				 var eventName = document.createElement("p");
				 eventName.innerText = data['name'];
				 newDiv.appendChild(eventName);
				 var eventDetails = document.createElement("p");
				 eventDetails.innerText = data['organizer'] + data['time'] + '-' + data['time-end'];
				 newDiv.appendChild(eventDetails);
			 	 //insert div into html//gotta give the class scroll the id scroll
			 	 document.getElementById('scroll').appendChild(newDiv);
			 }); 
		 }).catch(function(error){
			 console.log('error in loading event info', error)
		 });
}