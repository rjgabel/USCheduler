
const tempform = document.getElementById("eventForm");
tempform.addEventListener("submit", function addEvent(event){

    event.preventDefault();
    let baseURL = window.location.origin + "/USCheduler/";
    var url = new URL("AddEventServlet", baseURL);
    let sender = {
        User_id: sessionStorage.getItem("user_id"),
        eventName: document.eventForm.eventName.value,
        organization: document.eventForm.organization.value,
        description: document.eventForm.description.value,
        eventDate: document.eventForm.eventDate.value,
        startTime: document.eventForm.startTime.value,
        endTime: document.eventForm.endTime.value,
        imageURL: document.eventForm.imageURL.value
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