//untested, rough drafted code
//including ajax for jQuery
src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js";

//Implementation of the back button.
 function OnBack() {
	 e.preventDefault();
	 window.history.back();
 }
 
 //function to display current event, organizer, date/time, event description, and image
 //but how to get specific event? from event_id? how to get this info?
 function loadEventInformation(){
	 //try with fetch
	 var baseURL = window.location.origin + "/USCheduler/";
	 var url = new URL("EventListServlet", baseURL);
	 
	 fetch(url)
		 .then(data => {
			 console.log(data.text())
			 return data.json();
		 })
		 .then(data => {
			 document.getElementById('title').innerHTML = data['name'];
			 document.getElementById('club').innerHTML = data['organizer'];
			 document.getElementById('time').innerHTML = data['date'] + ' | ' + data['time'] + '-' + data['time-end'];
			 document.getElementById('description').innerHTML = data['description'];
			 document.getElementById('imgFrame').innerHTML = data['time']; //change this later
		 }).catch(function(error){
			 console.log('error in loading event info', error)
		 });
 }