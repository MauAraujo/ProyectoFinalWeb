let responsabilites = [];
let colaborations = [];
let superclases = ["Auto", "Humano"];
let tarjetas = [];

function createCRC() {
  const form = document.getElementById("crcForm");
  const data = {
    name: form.elements["name"].value,
    superclass: document.getElementById("superClassSelect").value,
    responsabilites,
    colaborations,
  };
  console.log(data);
  paintCRC(data);
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
  deleteButtonHtml.onclick = `deleteCRC(${data.id})`;

  pHtml.appendChild(deleteButtonHtml);

  body.appendChild(title);
  body.appendChild(subtitle);
  body.appendChild(pHtml);

  card.appendChild(body);

  col.appendChild(card);
  crcs.appendChild(col);
}

function clean() {
  responsabilites = [];
  colaborations = [];
  getCRC();
}

function addResponsability() {
  responsabilites.push("");

  const responsHtml = document.getElementById("responsabilites");
  const inputHtml = document.createElement("input");
  inputHtml.type = "text";
  inputHtml.classList.add("form-control", "my-3");
  inputHtml.setAttribute(
    "onchange",
    `changeResponsability(this,${
      responsabilites.length > 0 ? responsabilites.length - 1 : 0
    })`
  );

  responsHtml.appendChild(inputHtml);
}
function changeResponsability(input, index) {
  responsabilites[index] = input.value;
}
function addCollaboration() {
  colaborations.push("");

  const responsHtml = document.getElementById("colaborations");
  const inputHtml = document.createElement("input");
  inputHtml.type = "text";
  inputHtml.classList.add("form-control", "my-3");
  inputHtml.setAttribute(
    "onchange",
    `changeCollaboration(this,${
      colaborations.length > 0 ? colaborations.length - 1 : 0
    })`
  );

  responsHtml.appendChild(inputHtml);
}
function changeCollaboration(input, index) {
  colaborations[index] = input.value;
}

function saveCRC(data) {
  // TODO: Servicio POST CRC
}

function getCRC() {
  superclases = [];
  tarjetas = [];
  // TODO: Servicio GET de CRC
  // ...
  // tarjetas = ...
  // TODO: Borrar esto y cambiar por el servicio
  superclases = ["Auto", "Humano"];
  paintSuperclass();

  const crcsHtml = document.getElementById("crcs");
  crcsHtml.innerHTML = "";
  tarjetas.forEach(paintCRC);
}

function deleteCRC(id) {
  // TODO: Servicio DELETE CRC
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

getCRC();
