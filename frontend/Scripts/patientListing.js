async function hospitalPatientListing() {
  let hospital_id = sessionStorage.getItem("patientHospitalId");
  let token = sessionStorage.getItem("token");
  let response = await fetch(
    `http://localhost:8080/api/patients/dicom/list/?hospitalId=${hospital_id}`,
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
      var row = `
        <td>${count}</td>
            <td>${data.name} </td>
            <td>${data.patient_id} </td>
            <td>${data.sex} </td>
            <td>${data.age} </td>
            <td>${data.modality} </td>
            <td>${data.dob}</td>
            <td title="view Dicom" onclick="viewDicom(${data.id})"><i class="fa fa-image"></i></td>
            <td><input type="checkbox" class="row-checkbox" data-id="${data.id}"></td>`;
      finalRow += `<tr id="hospital-${data.id}">${row}</tr>`;
      count += 1;
    });
    tbody.innerHTML = finalRow;
  }
  $(document).ready(function () {
    $("#list").DataTable({
      dom: "Qlfrtip",
      aoColumns: [
        { bSortable: true },
        { bSortable: true },
        { bSortable: true },
        { bSortable: true },
        { bSortable: true },
        { bSortable: true },
        { bSortable: true },
        { bSortable: false },
      ],
      searching: false,
    });
    $("#datatable_next").visibility = "hidden";
  });
  sessionStorage.removeItem("patientHospitalId");
}
hospitalPatientListing();

function viewDicom(id) {
  var modal = document.getElementById("myModal");
  var span = document.getElementsByClassName("close")[0];
  modal.style.display = "block";
  span.onclick = function () {
    modal.style.display = "none";
  };
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
  cornerstoneWADOImageLoader.external.cornerstone = cornerstone;

  var config = {
    maxWebWorkers: navigator.hardwareConcurrency || 4,
    startWebWorkersOnDemand: true,
    webWorkerPath:
      "http://localhost:8080/vendor/cornerstoneWADOImageLoaderWebWorker.js",

    taskConfiguration: {
      decodeTask: {
        loadCodecsOnStartup: false,
        initializeCodecsOnStartup: false,
        codecsPath:
          "http://localhost:8080/vendor/cornerstoneWADOImageLoaderCodecs.js",
      },
    },
  };

  cornerstoneWADOImageLoader.webWorkerManager.initialize(config);
  const element = document.getElementById("dicom-image");
  cornerstone.enable(element);
  console.log("Enabled");
  cornerstone
    .loadImage(`dicomweb://localhost:8080/api/patients/dicom/${id}/`)
    .then(function (image) {
      cornerstone.displayImage(element, image);
    });
}

function cancelPatientDicom() {
  var modal = document.getElementById("myModal");
  modal.style.display = "none";
}
