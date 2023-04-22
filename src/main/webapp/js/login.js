const logForm = document.getElemenbyById("loginForm");
logForm.addEventListener("submit", function(event) {
    event.preventDefault();
    const form = event.currentTarget;
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
        sessionStorage.setItem("user_id", json.user_id);
        sessionStorage.setItem("balance", json.balance);
        window.location.href = "calendar.html";
    })
    .catch(function(error) {
        console.log(error.message);
    })

 })