/**
 *  I have not tested this yet
 */


const logForm = document.getElemenbyById("loginForm");
logForm.addEventListener("submit", loginUser);
 async function loginUser(event) {
    event.preventDefault();
    const form = event.currentTarget;
    const url = form.action;

    let logInfo = {
        username: document.loginForm.username,
        password: document.loginForm.password
    }

    const logJson = JSON.stringify(logInfo);

    await fetch(url{
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json"
        },
        body:logJson
    })
    .then((response) =>response.json())
    .then((res) =>{
        const user = JSON.parse(response);
        if(user.user_id === -1){
            console.log("user not found");
        }
        else if(user.user_id === -2){
            console.log("password does not match username");
        }
        else{
            console.log("Successful login");
            sessionStorage.setItem("user_id", user.user_id);
            sessionStorage.setItem("display_name", user.display_name);
            sessionStorage.setItem("email", user.email);
        }
    })

 }

 function registerBtn(){

 }