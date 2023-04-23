src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js";

const logForm = document.getElementById("loginForm");
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    let baseURL = window.location.origin + "/USCheduler/";
    const url = new URL("LoginServlet", baseURL);
    const logFormData = new FormData(logForm);
    const logData = new URLSearchParams(logFormData);

    fetch(url, {
        method: "POST",
        headers: {
            Accept: "application/json"
        },
        body:logData
    })
    .then((response) =>response.json())
    .then(json =>{
        //the json represents the entire user item.
        sessionStorage.setItem("user", json);
        sessionStorage.setItem("user_id", json.userID);
        console.log(json);
        window.location.href ="calendar.html";
    })
    .catch(function(error) {
        console.log(error.message);
    });
 });