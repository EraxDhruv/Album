async function userLogin(event) {
  let username = document.getElementById("username-login");
  let password = document.getElementById("pwd-login");
  let error1 = document.getElementById("errorpwd1");
  let errorname = document.getElementById("errorname");
  let emailFilter =
    /^([a-z A-Z 0-9 _\.\-])+\@(([a-z A-Z 0-9\-])+\.)+([a-z A-z 0-9]{3,3})+$/;
  let isError = false;
  if (!username.value) {
    errorname.style.visibility = "visible";
    isError = true;
  } else if (!emailFilter.test(username.value)) {
    errorname.innerHTML = "*Email id is not valid";
    errorname.style.visibility = "visible";
    isError = true;
    isError = true;
  } else {
    errorname.style.visibility = "hidden";
  }
  if (!password.value) {
    console.log("enter", password.value);
    error1.style.visibility = "visible";
    isError = true;
  } else {
    error1.style.visibility = "hidden";
  }

  if (!isError) {
    let response = await fetch("http://localhost:8080/api/users/login/", {
      method: "POST",
      body: JSON.stringify({
        email: username.value,
        password: password.value,
      }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    });
    let jsonR = await response.json();
    if (response.status == 200) {
      sessionStorage.setItem("token", jsonR.token);
      location.href = "./hospitalListing.html";
    } else {
      errorname.innerHTML = "Incorrect username or password";
      errorname.style.visibility = "visible";
    }
    console.log(jsonR);
  }
}

function forgotpwd() {
  location.href = "./Register.html";
}
