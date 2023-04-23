
const tempform = document.getElementById("eventForm");
tempform.addEventListener("submit", function addEvent(event){

    event.preventDefault();
    $.ajax({
		url:"AddEventServlet",
		type: "POST",
		data: {
			User_id: sessionStorage.getItem("user_id"),
	        eventName: document.eventForm.eventName.value,
	        organization: document.eventForm.organization.value,
	        description: document.eventForm.description.value,
	        eventDate: document.eventForm.eventDate.value,
	        startTime: document.eventForm.startTime.value,
	        endTime: document.eventForm.endTime.value,
	        imageURL: document.eventForm.imageURL.value
		},
		success: function(result) {
			window.location.href = "calendar.html";
		},
		error: function(result) {
			alert(result.responseJSON);
		}
		
	});
});