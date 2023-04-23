function Register() {
	var username = document.registrationForm.username.value;
	var password = document.registrationForm.password.value;
	var dispName = document.registrationForm.displayName.value;
	var mail = document.registrationForm.email.value;
	 
	 $.ajax({
		url: "RegisterServlet",
		type: "POST",
		data: {
			username: username,
			password: password,
			displayName: dispName,
			email: mail
		},
		success: function(result) {
			sessionStorage.setItem("username", result.username);
			sessionStorage.setItem("displayName", result.displayName);
        	sessionStorage.setItem("user_id", result.userID);
        	window.location.href ="calendar.html";
        },
		error: function(result) {
			alert(result.responseJSON);
		},
		complete: function(data) {
			document.registrationForm.username.value = "";
			password = document.registrationForm.password.value = "";
			dispName = document.registrationForm.displayName.value = "";
			mail = document.registrationForm.email.value = "";
		}
		
	 });
}