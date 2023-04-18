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
		 
	 /* failed attempts, will delete late but just want the code on record
	 //try with ajax
	 $.ajax({
		url: "EventListServlet",
		data: {
			title: document.getElementById('title'),
			field: "title", //name
			club: document.getElementById('club'),
			field: "club", //organizer
			time: document.getElementById('time'),
			field: "time", //date | time - time end
			decription: document.getElementById('description'),
			field: "description", //description
			imgFrame: document.getElementById('imgFrame'),
			field: "imgFrame" //?
		},
		success: function(result){
			$("#title").html(result);
			$("#club").html(result);
			$("#time").html(result);
			$("#description").html(result);
			$("#imgFrame").html(result);
		}
	 });
	 
	 //try with xhttp
	 let baseURL = window.location.origin;
	 const xhttp = new XMLHttpRequest();
	 xhttp.onload = function(){
		 document.getElementById("").innerHTML = this.responseText;
	 }
	 xhttp.open("GET", "url", true);
	 xhttp.send();
	 */
 }