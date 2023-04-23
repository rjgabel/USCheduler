//untested code
//including ajax for jQuery
src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js";

 //function to display current event, organizer, date/time, event description, and image
 function loadEventInformation(eventid){
	 //try with fetch
	 var baseURL = window.location.origin + "/USCheduler/";
	 var url = new URL("EventListServlet", baseURL);
	 
	 fetch(url)
		 .then(data => {
			 console.log(data.text())
			 return data.json();
		 })
		 .then(data => {
			 let i = 1; 
			 //loops over each key to see if it matches the event's id
			 Object.keys(data).forEach(function(key){
				 if (eventid == i.toString){
					 document.getElementById('title').innerHTML = key['name'];
			 		 document.getElementById('club').innerHTML = key['organizer'];
			 		 document.getElementById('time').innerHTML = key['date'] + ' | ' + key['time'] + '-' + key['time_end'];
			 		 document.getElementById('description').innerHTML = key['description'];
			 		 document.getElementById('imgFrame').src = key['img_url'];
				 }
				 else {
					 i++;
				 }
			 }); 
		 }).catch(function(error){
			 console.log('error in loading event info', error)
		 });
 }