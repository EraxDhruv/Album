async function userSignup(event) {
  let username = document.getElementById("username");
  let password = document.getElementById("pwd");
  let confirmpwd = document.getElementById("confirmpwd");
  let error = document.getElementById("errorpwd");
  let error1 = document.getElementById("errorpwd1");
  let errorname = document.getElementById("errorname");
  let emailFilter =
    /^([a-z A-Z 0-9 _\.\-])+\@(([a-z A-Z 0-9\-])+\.)+([a-z A-z 0-9]{3,3})+$/;
  let isError = false;
  if (!username.value) {
    errorname.innerHTML = "*Email id is required";
    errorname.style.visibility = "visible";
    isError = true;
  } else if (!emailFilter.test(username.value)) {
    errorname.innerHTML = "*Email id is not valid";
    errorname.style.visibility = "visible";
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
  if (!confirmpwd.value) {
    error.style.visibility = "visible";
    isError = true;
  } else if (confirmpwd.value !== password.value) {
    error.innerHTML = "*Password doesn't match";
    error.style.visibility = "visible";
    isError = true;
  } else if (!confirmpwd.value && !password.value) {
    error1.style.visibility = "visible";
    error.style.visibility = "visible";
    isError = true;
  } else {
    error.style.visibility = "hidden";
  }
  if (!isError) {
    let response = await fetch("http://localhost:8080/api/users/signup/", {
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
    if (response.status == 201) {
      let success_form = document.getElementById("successformModal");
      success_form.style.display = "block";
    } else {
      errorname.innerHTML = jsonR.message;
      errorname.style.visibility = "visible";
    }
    console.log(jsonR);
  }
}

function successSignupMessage() {
  let success_form = document.getElementById("successformModal");
  success_form.style.display = "block";
  location.href = "./login.html";
}
