

const logForm = document.getElementById("loginForm");
/*document.getElementById('loginForm').addEventListener('submit', function(event) {
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
<<<<<<< HEAD
    if(sessionStorage.getItem('user' != null)){
		window.location.href ="calendar.html";
	}
	else{
		//logForm.reset();
		console.log("reset");
	}
 });*/
 
 function LogIn() {
	 var username = document.loginForm.username.value;
	 var password = document.loginForm.password.value;
	 
	 $.ajax({
		url: "LoginServlet",
		type: "POST",
		data: {
			username: username,
			password: password
		},
		success: function(result) {
			sessionStorage.setItem("username", result.username);
			sessionStorage.setItem("displayName", result.displayName);
        	sessionStorage.setItem("user_id", result.userID);
        	window.location.href ="calendar.html";
        },
		error: function(result) {
			alert(result.innerText);
		},
		complete: function(data) {
			document.loginForm.username.value = "";
			document.loginForm.password.value = "";
		}
		
	 });
 }
 
 function Register() {
	 window.location.href ="registration.html";
 }
 
=======
 });
>>>>>>> 43ffcd4073080addb924264a73dd29ac67d99def
