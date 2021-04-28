let responsibilities = [];
let collaborations = [];
let superclases = ["Auto", "Humano"];
let tarjetas = [];

function createCRC() {
  const form = document.getElementById("crcForm");
    const data = {
    projectid: projectID,
    name: form.elements["name"].value,
    superclass: document.getElementById("superClassSelect").value,
    responsibilities,
    collaborations,
  };
  console.log(data);
  saveCRC(data);
  clean();
}

function paintCRC(data) {
  const crcs = document.getElementById("crcs");
  const col = document.createElement("div");
  col.classList.add("col-2", "me-4", "my-2");

  const card = document.createElement("div");
  card.classList.add("card", "hovereable");

  const body = document.createElement("div");
  body.classList.add("card-body");

  const title = document.createElement("h4");
  title.classList.add("card-title");
  title.innerHTML = data.name || "";

  const subtitle = document.createElement("span");
  subtitle.classList.add("text-start");
  subtitle.innerHTML = data.superclass || "";

  const pHtml = document.createElement("p");
  pHtml.classList.add("mt-5");

  const deleteButtonHtml = document.createElement("button");
  deleteButtonHtml.type = "button";
  deleteButtonHtml.classList.add("btn", "btn-outline-danger", "delete-button");
  deleteButtonHtml.innerHTML = "Borrar";
  deleteButtonHtml.setAttribute("onclick", `deleteCRC(${data.id})`);
  // deleteButtonHtml.onclick = `deleteCRC(${data.id})`;

  pHtml.appendChild(deleteButtonHtml);

  body.appendChild(title);
  body.appendChild(subtitle);
  body.appendChild(pHtml);

  card.appendChild(body);

  col.appendChild(card);
  crcs.appendChild(col);
}

function clean() {
  responsibilities = [];
  collaborations = [];
  getCRC();
}

function addResponsability() {
  responsibilities.push("");

  const responsHtml = document.getElementById("responsibilities");
  const inputHtml = document.createElement("input");
  inputHtml.type = "text";
  inputHtml.classList.add("form-control", "my-3");
  inputHtml.setAttribute(
    "onchange",
    `changeResponsability(this,${
      responsibilities.length > 0 ? responsibilities.length - 1 : 0
    })`
  );

  responsHtml.appendChild(inputHtml);
}
function changeResponsability(input, index) {
  responsibilities[index] = input.value;
}
function addCollaboration() {
  collaborations.push("");

  const responsHtml = document.getElementById("collaborations");
  const inputHtml = document.createElement("input");
  inputHtml.type = "text";
  inputHtml.classList.add("form-control", "my-3");
  inputHtml.setAttribute(
    "onchange",
    `changeCollaboration(this,${
      collaborations.length > 0 ? collaborations.length - 1 : 0
    })`
  );

  responsHtml.appendChild(inputHtml);
}
function changeCollaboration(input, index) {
  collaborations[index] = input.value;
}

function saveCRC(data) {
    // TODO: Servicio POST CRC
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/proyecto/Historias?type=crc", true);
    xhr.onload = function () {
        tarjetas = JSON.parse(this.responseText);
        console.log(tarjetas);
        getCRC();
    };

    xhr.send(JSON.stringify(data));
}

function getCRC() {
  superclases = [];
    tarjetas = [];

    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/proyecto/Historias?type=crc", true);
    xhr.onload = function () {
        tarjetas = JSON.parse(this.responseText);
        console.log(tarjetas);
        superclases = ["Auto", "Humano"];
        paintSuperclass();
        const crcsHtml = document.getElementById("crcs");
        crcsHtml.innerHTML = "";
        tarjetas.forEach(paintCRC);
    };

    xhr.send();
}

function deleteCRC(id) {
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", `http://localhost:8080/proyecto/Historias?id=${id}&type=crc`, true);
    xhr.onload = function () {
        tarjetas = JSON.parse(this.responseText);
        console.log(tarjetas);
        clean();
    };

    xhr.send();
}

function paintSuperclass() {
  const superclassSelectHtml = document.getElementById("superClassSelect");
  superclassSelectHtml.innerHTML = "";
  const defaultOptionEdit = document.createElement("option");
  defaultOptionEdit.hidden = true;
  defaultOptionEdit.disabled = true;
  defaultOptionEdit.selected = true;
  defaultOptionEdit.value = "";
  defaultOptionEdit.innerHTML = "Elige una Super Clase";

  superclassSelectHtml.appendChild(defaultOptionEdit);

  superclases.forEach((superclass) => {
    const optionHtml = document.createElement("option");
    optionHtml.value = superclass;
    optionHtml.innerHTML = superclass;
    superclassSelectHtml.appendChild(optionHtml);
  });
}

getProjectID();
getCRC();
