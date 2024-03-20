async function addHospital(event) {
  let name = document.getElementById("input-name");
  let streetAddress = document.getElementById("input-street-address");
  let city = document.getElementById("input-city");
  let state = document.getElementById("input-state");
  let country = document.getElementById("input-country");
  let pincode = document.getElementById("input-pincode");
  let contactName1 = document.getElementById("input-contact1-name");
  let number1 = document.getElementById("input-contact-number1");
  let email1 = document.getElementById("input-email1");
  let contactName2 = document.getElementById("input-contact2-name");
  let number2 = document.getElementById("input-contact-number2");
  let email2 = document.getElementById("input2-email");

  let token = sessionStorage.getItem("token");
  let payload = JSON.stringify({
    name: name.value,
    street_address: streetAddress.value,
    city: city.value,
    state: state.value,
    country: country.value,
    pincode: pincode.value,
    hospitalContacts: [
      {
        contact_name: contactName1.value,
        contact_number: number1.value,
        email: email1.value,
      },
      {
        contact_name: contactName2.value,
        contact_number: number2.value,
        email: email2.value,
      },
    ],
  });
  let response = await fetch("http://localhost:8080/api/hospitals/", {
    method: "POST",
    body: payload,
    headers: {
      "Content-type": "application/json; charset=UTF-8",
      Authorization: "Bearer " + token,
    },
  });
  let jsonR = await response.json();
  if (response.status == 201) {
    location.href = "./hospitalListing.html";
  }
  console.log(jsonR);
}

function cancelAdd(event) {
  location.href = "./hospitalListing.html";
}

function validatePayload(event) {
  let name = document.getElementById("input-name");
  let streetAddress = document.getElementById("input-street-address");
  let city = document.getElementById("input-city");
  let state = document.getElementById("input-state");
  let country = document.getElementById("input-country");
  let pincode = document.getElementById("input-pincode");
  let contactName1 = document.getElementById("input-contact1-name");
  let number1 = document.getElementById("input-contact-number1");
  let email1 = document.getElementById("input-email1");
  let contactName2 = document.getElementById("input-contact2-name");
  let number2 = document.getElementById("input-contact-number2");
  let email2 = document.getElementById("input2-email");

  let isError = false;
  var numericFilter = /[0-9]/;
  var pincodeFilter = /^\d{6}$/;
  var phnfilter = /^\d{10}$/;
  var emailFilter =
    /^([a-z A-Z 0-9 _\.\-])+\@(([a-z A-Z 0-9\-])+\.)+([a-z A-z 0-9]{3,3})+$/;

  if (!name.value) {
    console.log("Name is required");
    let error = document.getElementById("errorname");
    error.style.visibility = "visible";
    isError = true;
  } else if (!/[a-zA-Z]/.test(name.value)) {
    let error = document.getElementById("errorname");
    error.innerHTML = "*Name must contains characters";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorname");
    error.style.visibility = "hidden";
  }
  if (!streetAddress.value || streetAddress.value.length < 0) {
    console.log("Street Address is required");
    let error = document.getElementById("errorstreet");
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorstreet");
    error.style.visibility = "hidden";
  }
  if (!city.value || city.value.length < 0) {
    console.log("City is required", typeof city.value);
    let error = document.getElementById("errorcity");
    error.style.visibility = "visible";
    isError = true;
  } else if (!/[a-zA-Z]/.test(city.value)) {
    let error = document.getElementById("errorcity");
    error.innerHTML = "*City must contains characters.";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorcity");
    error.style.visibility = "hidden";
  }
  if (!state.value || state.value.length < 0) {
    console.log("State is required", state.value);
    let error = document.getElementById("errorstate");
    error.style.visibility = "visible";
    isError = true;
  } else if (!/[a-zA-Z]/.test(state.value)) {
    let error = document.getElementById("errorstate");
    error.innerHTML = "*State must contains characters";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorstate");
    error.style.visibility = "hidden";
  }
  if (!country.value || country.value.length < 0) {
    console.log("Country is required");
    let error = document.getElementById("errorcountry");
    error.style.visibility = "visible";
    isError = true;
  } else if (!/[a-zA-Z]/.test(country.value)) {
    let error = document.getElementById("errorcountry");
    error.innerHTML = "*Country must contains characters";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorcountry");
    error.style.visibility = "hidden";
  }
  if (!pincode.value || pincode.value.length < 0) {
    console.log("Pincode is required");
    let error = document.getElementById("errorpincode");
    error.style.visibility = "visible";
    isError = true;
  } else if (!numericFilter.test(pincode.value)) {
    let error = document.getElementById("errorpincode");
    error.innerHTML = "*Pincode must be numeric.";
    error.style.visibility = "visible";
    isError = true;
  } else if (!pincodeFilter.test(pincode.value)) {
    let error = document.getElementById("errorpincode");
    error.innerHTML = "*Pincode must be of 6 digits.";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorpincode");
    error.style.visibility = "hidden";
  }
  if (!contactName1.value || contactName1.value.length < 0) {
    console.log("Contact Name is required");
    let error = document.getElementById("errorcontact1name");
    error.style.visibility = "visible";
    isError = true;
  } else if (!/[a-zA-Z]/.test(contactName1.value)) {
    let error = document.getElementById("errorcontact1name");
    error.innerHTML = "*Contact name must contains characters";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorcontact1name");
    error.style.visibility = "hidden";
  }
  if (!number1.value || number1.value.length < 0) {
    console.log("Contact Number is required");
    let error = document.getElementById("errorcontact1num");
    error.style.visibility = "visible";
    isError = true;
  } else if (!numericFilter.test(number1.value)) {
    let error = document.getElementById("errorcontact1num");
    error.innerHTML = "*Comtact number must be numeric.";
    error.style.visibility = "visible";
    isError = true;
  } else if (!phnfilter.test(number1.value)) {
    let error = document.getElementById("errorcontact1num");
    error.innerHTML = "*Contact number must of 10 digits";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorcontact1num");
    error.style.visibility = "hidden";
  }
  if (!email1.value || email1.value.length < 0) {
    console.log("Email is required");
    let error = document.getElementById("errorcontact1email");
    error.style.visibility = "visible";
    isError = true;
  } else if (!emailFilter.test(email1.value)) {
    let error = document.getElementById("errorcontact1email");
    error.innerHTML = "*Email is not valid";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorcontact1email");
    error.style.visibility = "hidden";
  }
  if (!contactName2.value || contactName2.value.length < 0) {
    console.log("Contact Name is required");
    let error = document.getElementById("errorcontact2name");
    error.style.visibility = "visible";
    isError = true;
  } else if (!/[a-zA-Z]/.test(contactName2.value)) {
    let error = document.getElementById("errorcontact2name");
    error.innerHTML = "*Contact name must contains characters";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorcontact2name");
    error.style.visibility = "hidden";
  }
  if (!number2.value || number2.value.length < 0) {
    console.log("Contact Number is required");
    let error = document.getElementById("errorcontact2num");
    error.style.visibility = "visible";
    isError = true;
  } else if (!numericFilter.test(number2.value)) {
    let error = document.getElementById("errorcontact2num");
    error.innerHTML = "*Contact number must be numeric.";
    error.style.visibility = "visible";
    isError = true;
  } else if (!phnfilter.test(number2.value)) {
    let error = document.getElementById("errorcontact2num");
    error.innerHTML = "*Contact number must of 10 digits";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorcontact2num");
    error.style.visibility = "hidden";
  }
  if (!email2.value || email2.value.length < 0) {
    console.log("Email is required");
    let error = document.getElementById("errorcontact2email");
    error.style.visibility = "visible";
    isError = true;
  } else if (!emailFilter.test(email2.value)) {
    let error = document.getElementById("errorcontact2email");
    error.innerHTML = "*Email is not valid";
    error.style.visibility = "visible";
    isError = true;
  } else {
    let error = document.getElementById("errorcontact2email");
    error.style.visibility = "hidden";
  }
  if (!isError) {
    addHospital(event);
  }
}
