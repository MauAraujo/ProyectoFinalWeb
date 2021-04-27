let newCollabs = [];
// TODO: Recibirlo del servicio
let collabsList = [
  { name: "Pepe", id: 0 },
  { name: "Diana", id: 1 },
];

let proyectos = [];

function createProject() {
  const form = document.getElementById("newForm");
  const formData = {
    name: form.elements["name"].value,
    description: form.elements["description"].value,
    collabs: newCollabs,
  };

  newCollabs = [];

  const projectID = saveProject(formData);

  paintProject({ ...formData, id: projectID });
  cleanNewProject();
}

function saveProject(formData) {
  // TODO: Guardar proyecto en bd

  let id;

  return id;
}

function paintProject(formData) {
  const row = document.getElementById("projects-row");
  const col = document.createElement("div");
  col.classList.add("col-3", "me-4", "my-2");

  const card = document.createElement("div");
  card.classList.add("card");

  const body1 = document.createElement("div");
  body1.classList.add("card-body");

  const title = document.createElement("h4");
  title.classList.add("card-title");
  title.innerHTML = formData.name;

  const description = document.createElement("p");
  description.classList.add("card-text");
  description.innerHTML = formData.description;

  const collabs = document.createElement("ul");
  collabs.classList.add(
    "list-group",
    "list-group-horizontal",
    "list-group-flush",
    "scrollable"
  );

  for (let index = 0; index < formData.collabs.length; index++) {
    const li = document.createElement("li");
    li.classList.add("list-group-item");

    const avatar = document.createElement("p");
    avatar.classList.add(
      "d-flex",
      "flex-column",
      "align-items-center",
      "justify-content-center",
      "my-1"
    );

    const icon = document.createElement("i");
    icon.classList.add("bi", "bi-person", "d-block", "fs-2", "text-primary");

    const name = document.createElement("span");
    name.classList.add("text-center", "m-0");
    name.innerHTML = formData.collabs[index].name || "";

    avatar.appendChild(icon);
    avatar.appendChild(name);

    li.appendChild(avatar);
    collabs.appendChild(li);
  }

  const body2 = document.createElement("div");
  body2.classList.add("card-body", "d-flex", "justify-content-between");

  const del = document.createElement("button");
  del.classList.add("btn", "btn-outline-danger");
  del.type = "button";
  del.innerHTML = "Eliminar";
  del.onclick = `deleteProject(${formData.id})`;

  const edit = document.createElement("button");
  edit.classList.add("btn", "btn-outline-warning");
  edit.type = "button";
  edit.innerHTML = "Editar";
  edit.dataset.bsToggle = "modal";
  edit.dataset.bsTarget = "#exampleModal";

  const open = document.createElement("a");
  open.classList.add("btn", "btn-outline-primary");
  open.type = "button";
  open.innerHTML = "Abrir";
  open.href = `pages/proyecto.html?id=${formData.id}`;

  body1.appendChild(title);
  body1.appendChild(description);

  body2.appendChild(del);
  body2.appendChild(edit);
  body2.appendChild(open);

  card.appendChild(body1);
  card.appendChild(collabs);
  card.appendChild(body2);

  col.appendChild(card);

  row.appendChild(col);
}

function cleanNewProject() {
  const form = document.getElementById("newForm");
  form.elements["name"].value = "";
  form.elements["description"].value = "";
  document.getElementById("collabs").innerHTML = "";
}

function addCollab() {
  const id = document.getElementById("newCollabSelect").value;

  const collab = collabsList[id];

  if (newCollabs.find((element) => element.uid === id)) {
    return;
  }

  newCollabs.push(collab);

  const collabs = document.getElementById("collabs");

  const li = document.createElement("li");
  li.classList.add("list-group-item", "border-less");

  const avatar = document.createElement("p");
  avatar.classList.add(
    "d-flex",
    "flex-column",
    "align-items-center",
    "justify-content-center"
  );

  const icon = document.createElement("i");
  icon.classList.add("bi", "bi-person", "d-block", "fs-2", "text-primary");

  const name = document.createElement("span");
  name.classList.add("text-center", "m-0");
  name.innerHTML = collab.name;

  avatar.appendChild(icon);
  avatar.appendChild(name);

  li.appendChild(avatar);
  collabs.appendChild(li);
}

function getCollabs() {
  // TODO: Servicio para obtener los colaboradores
  setCollabs();
}

function setCollabs() {
  const selectHtml = document.getElementById("newCollabSelect");
  selectHtml.innerHTML = "";
  const defaultOption = document.createElement("option");
  defaultOption.hidden = true;
  defaultOption.disabled = true;
  defaultOption.selected = true;
  defaultOption.value = "";
  defaultOption.innerHTML = "Elige un Colaborador";

  selectHtml.appendChild(defaultOption);

  collabsList.forEach((collab, index) => {
    const optionHtml = document.createElement("option");
    optionHtml.value = index;
    optionHtml.innerHTML = collab.name;
    selectHtml.appendChild(optionHtml);
  });
}

function getProjects() {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/proyecto/Proyectos", true);
  xhr.onload = function () {
    projects = JSON.parse(this.responseText);
    console.log(projects);
  };

  xhr.send();
}

function deleteProject(id) {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (xhr.readyState == 4) {
      var res = xhr.responseText;
      const data = JSON.parse(res);
      console.log(data);
    }
  };
  xhr.open("DELETE", `http://localhost:8080/proyecto/Proyectos`, true);
  xhr.send(null);
}

getProjects();
getCollabs();
