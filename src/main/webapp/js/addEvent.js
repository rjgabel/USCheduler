/**
 * I have not tested this yet
 */


const tempform = document.getElementById("eventForm");
tempform.addEventListener("submit", function addEvent(event){

    event.preventDefault();
    const form = event.currentTarget;
    let baseUrl = window.location.origin + "/USCheduler/";
    var url = new URL("AddEventServlet", baseURL);
    let sender = {
        user_id: sessionStorage.getItem("user_id"),
        name: document.eventForm.eventName.value,
        organization: document.eventForm.organization.value,
        description: document.eventForm.description.value,
        date: document.eventForm.eventDate.value,
        time: document.eventForm.startTime.value,
        time_end: document.eventForm.endTime.value,
        eventImage: document.eventForm.eventImage.value
    };

    const eventData = new URLSearchParams(sender);
    fetch(url, {
        method: "POST",
        headers: {
            Accept: "application/json",
        },
        body: eventData
    })
    .then(response => response.json())
    //receives the response as json
    //there is nothing to do with the json response if no error occurred.
    .catch(function (error) {
            console.log(error.message());
    });
}
);