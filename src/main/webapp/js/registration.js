const regForm = document.getElementById("registrationForm");
regForm.addEventListener('submit', function(event){
    event.preventDefault();
    let baseURL = window.location.origin + "/USCheduler/"
	var url = new URL("RegisterServlet", baseURL);
    const regData = new FormData(regForm);
    const sendReg = new URLSearchParams(regData);
    fetch(url, {
        method: "POST",
        headers: {
            Accept: "application/json",
        },
        body: sendReg,
    })
    .then((response) => response.json())
    //receives the response as json
    .then((res) => {
        const obj = JSON.parse(response);
        console.log(obj)
        //store the 
    })
    .catch(function (error) {
            console.log('request failed', error)
    });
    regForm.reset();
});
