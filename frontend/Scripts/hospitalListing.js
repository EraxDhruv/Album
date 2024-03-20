let id = document.getElementById("data-id");
let name = document.getElementById("data-name");
let code = document.getElementById("data-code");
let address = document.getElementById("data-address");
let city = document.getElementById("data-city");
let state = document.getElementById("data-state");
let country = document.getElementById("data-country");
let pincode = document.getElementById("data-pincode");
let tbody = document.getElementById("tbody");
let table = document.getElementById("list");

let searchName = document.getElementById("search-name").value;
let searchCity = document.getElementById("search-city").value;
let searchCode = document.getElementById("search-code").value;

let datatable;

async function hospitalListing(search_name, search_city, search_code) {
  let token = sessionStorage.getItem("token");
  let pageNo = 0;
  let pageSize = 400;
  let response = await fetch(
    `http://localhost:8080/api/hospitals/?pageNo=${pageNo}&pageSize=${pageSize}&name=${search_name}&city=${search_city}&code=${search_code}`,
    {
      method: "GET",
      headers: {
        "Content-type": "application/json; charset=UTF-8",
        Authorization: "Bearer " + token,
      },
    }
  );
  let jsonR = await response.json();
  let finalRow = "";
  let count = 1;
  if (response.status == 200) {
    jsonR.forEach((data) => {
      let a = `${data["hospital"].street_address} \n${data["hospital"].id} \n3`;
      console.log(a);
      let finalAddress = `${data["hospital"].street_address}, <br>\n\n${data["hospital"].city}, ${data["hospital"].state}<br> \n${data["hospital"].pincode}, ${data["hospital"].country}`;
      let firstContactDetails = `${data["hospitalContacts"][0].contact_name} <br>${data["hospitalContacts"][0].contact_number}<br> ${data["hospitalContacts"][0].email}`;
      let secondContactDetails = `${data["hospitalContacts"][1].contact_name} <br>${data["hospitalContacts"][1].contact_number}<br> ${data["hospitalContacts"][1].email}`;
      console.log(data);
      var row = `
      <td>${count}</td>
          <td>${data["hospital"].name} </td>
          <td>${data["hospital"].code} </td>
          <td>${finalAddress} </td>
          <td>${firstContactDetails} </td>
          <td>${secondContactDetails} </td>
          <td title="Upload Dicom" onclick="dicomModal(${data["hospital"].id})"><i class="fa fa-image" title="Upload Dicom"></i></td>
          <td title="Patients List" onclick="patientPage(${data["hospital"].id})"><i class="fa fa-user" ></i></td>
          <td title="Update Hospital" onclick="updateHospitalRow(${data["hospital"].id})"><i class="fa fa-pencil"></i></td>
          <td title="Delete Hospital" onclick="deleteHospital(${data["hospital"].id},${count}-1)"><i class="fa fa-trash" ></i></td>`;
      finalRow += `<tr id="hospital-${data["hospital"].id}">${row}</tr>`;
      count += 1;
    });
    tbody.innerHTML = finalRow;
  }
  datatable = $("#list").DataTable({
    dom: "Qlfrtip",
    aoColumns: [
      { bSortable: true },
      { bSortable: true },
      { bSortable: true },
      { bSortable: true },
      { bSortable: true },
      { bSortable: true },
      { bSortable: false },
      { bSortable: false },
      { bSortable: false },
      { bSortable: false },
    ],
    searching: false,
  });
}
hospitalListing(searchName, searchCity, searchCode);

function hospitalBySearch(event) {
  datatable.destroy();
  let searchName = document.getElementById("search-name").value;
  let searchCity = document.getElementById("search-city").value;
  let searchCode = document.getElementById("search-code").value;
  hospitalListing(searchName, searchCity, searchCode);
}

function resetSearch() {
  document.getElementById("search-name").value = "";
  document.getElementById("search-city").value = "";
  document.getElementById("search-code").value = "";
  // hospitalListing(searchName,searchCity, searchCode);
  location.href = "../Pages/hospitalListing.html";
}

async function deleteHospital(id, index) {
  console.log(id, index);
  let token = sessionStorage.getItem("token");

  let confirmMsg = confirm("Do you really want to delete?");
  if (confirmMsg) {
    let response = await fetch(
      `http://localhost:8080/api/hospitals/?id=${id}`,
      {
        method: "DELETE",
        headers: {
          "Content-type": "application/json; charset=UTF-8",
          Authorization: "Bearer " + token,
        },
      }
    );
    console.log(response);
    if (response.status == 200) {
      table.deleteRow(index);
      location.href = "../Pages/hospitalListing.html";
    }
  } else {
    location.href = "../Pages/hospitalListing.html";
  }
}

async function updateHospitalRow(id) {
  console.log(id);
  sessionStorage.setItem("hospital_id", id);
  location.href = "../Pages/updateHospital.html";
}

function dicomModal(hospitalId) {
  var modal = document.getElementById("myModal");
  var span = document.getElementsByClassName("close")[0];
  console.log(hospitalId);
  modal.style.display = "block";
  sessionStorage.setItem("dicomHospitalId", hospitalId);
  span.onclick = function () {
    modal.style.display = "none";
  };
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
}

function cancelDicom() {
  var modal = document.getElementById("myModal");
  modal.style.display = "none";
}

async function submitDicom() {
  let hospital_id = sessionStorage.getItem("dicomHospitalId");
  const uploadFileEle = document.getElementById("dicomfile").files[0];
  let formData = new FormData();
  formData.append("image", uploadFileEle);
  let token = sessionStorage.getItem("token");
  try {
    const response = await fetch(
      `http://localhost:8080/api/patients/dicom/upload/${hospital_id}/`,
      {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: formData,
      }
    );
    const jsonResponse = await response.json();
    if (response.status == 200) {
      console.log("Successfully uploaded study!!");
      let confirmMsg = document.getElementById("confirmMessage");
      confirmMsg.style.display = "block";
      setTimeout(() => {
        window.location.href = "./hospitalListing.html";
      }, 4000);
    } else {
      console.log(jsonResponse.message);
    }
  } catch {
    console.log("Oops something went wrong!!");
  }
  sessionStorage.removeItem("dicomHospitalId");
}

function patientPage(hospitalId) {
  sessionStorage.setItem("patientHospitalId", hospitalId);
  location.href = "../Pages/patientListing.html";
}
