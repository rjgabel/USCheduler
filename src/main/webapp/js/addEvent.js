/**
 * 
 */


const tempform = document.getElementById("eventForm");
tempform.addEventListener("submit", addEvent);


async function addEvent(event){

    event.preventDefault();
    const form = event.currentTarget;
    const url = form.action;

    let sender = {
        user_id: sessionStorage.getItem("user_id"),
        name: document.eventForm.eventName.value,
        organization: document.eventForm.organization,
        description: document.eventForm.description,
        date: document.eventForm.eventDate,
        time: document.eventForm.startTime,
        time_end: document.eventForm.endTime,
        eventImage: document.eventForm.eventImage
    };

    const tempJSON = JSON.stringify(sender);

    await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        body: tempJSON
    })
    .then((response) => response.json())
    //receives the response as json
    .then((res) => {
        const obj = JSON.parse(response);
        if(obj.error_code === 0){
            //correctly posted
            console.log('event Added Successfully')
        }
        else if(obj.error_code === 1){
            console.log('Invalid date: date in past')
        }
        else if(obj.error_code === 2){
            console.log('Invalid Time: time is past')
        }
        else if(obj.error_code === 3){
            console.log('Invalid End: end before start')
        }
    })
    .catch(function (error) {
            console.log('request failed', error)
    });
}