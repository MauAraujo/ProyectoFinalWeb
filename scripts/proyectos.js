let newCollabs = [];
let editCollabs = [];
// TODO: Recibirlo del servicio
let collabsList = [
  { name: "Pepe", uid: 0 },
  { name: "Diana", uid: 1 },
];

let projects = [];

let selected = {};

function createProject() {
  const form = document.getElementById("newForm");
  const formData = {
    name: form.elements["name"].value,
    description: form.elements["description"].value,
    collabs: newCollabs,
  };

  newCollabs = [];

  saveProject(formData);

  // paintProject({ ...formData, id: projectID });
  getProjects();
  cleanProject();
}

function saveProject(formData) {
  // TODO: Guardar proyecto en bd
  formData.date = Date();
  let project;
  var xhr = new XMLHttpRequest();
  xhr.open("POST", `http://localhost:8080/proyecto/Proyectos`, true);
  xhr.onload = function () {
    project = JSON.parse(this.responseText);
    console.log(project);
  };

  xhr.send(JSON.stringify(formData));
}

function paintProject(formData) {
  const row = document.getElementById("projects-row");
  const col = document.createElement("div");
  col.classList.add("col-3", "me-4", "my-2");
  col.id = `proyecto-${formData.id}`;

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
  del.setAttribute("onclick", `deleteProject(${formData.id})`);

  const edit = document.createElement("button");
  edit.classList.add("btn", "btn-outline-warning");
  edit.type = "button";
  edit.innerHTML = "Editar";
  edit.dataset.bsToggle = "modal";
  edit.dataset.bsTarget = "#editModal";
  edit.setAttribute("onclick", `selectProject(${formData.id})`);

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

function editProject() {
  const form = document.getElementById("editForm");

  const data = {
    // TODO: No se que nombre es el de colaboradores dentro del servicio
    collabs: editCollabs,
    name: form.elements["name"].value,
    description: form.elements["description"].value,
    date: Date(),
  };

  // TODO: Servicio PUT Proyecto
  // selected es el que debe tener el proyecto que se va a editar

  var xhr = new XMLHttpRequest();
  xhr.open("POST", `http://localhost:8080/proyecto/Proyectos`, true);
  xhr.onload = function () {
    selected = JSON.parse(this.responseText);
    console.log(selected);
  };

  xhr.send(JSON.stringify(data));

  // Esto es para que vuelva a pintar todos los proyectos nuevamente
  getProjects();
}

function cleanProject() {
  const form = document.getElementById("newForm");
  form.elements["name"].value = "";
  form.elements["description"].value = "";
  document.getElementById("collabs").innerHTML = "";
  newCollabs = [];

  const edit = document.getElementById("editForm");
  edit.elements["name"].value = "";
  edit.elements["description"].value = "";
  document.getElementById("editCollabs").innerHTML = "";
  editCollabs = [];
}

function addCollab() {
  const id = document.getElementById("newCollabSelect").value;

  const collab = collabsList[id];
  console.log(collab);

  if (newCollabs.find((element) => element.uid == id)) {
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

function addCollabEdit() {
  const id = document.getElementById("editCollabSelect").value;

  const collab = collabsList[id];

  console.log(editCollabs);
  console.log(id);
  console.log(
    editCollabs.find((element) => {
      console.log(element.uid, id);
      return element.uid == id;
    })
  );

  if (editCollabs.find((element) => element.uid == id)) {
    return;
  }

  editCollabs.push(collab);

  const collabs = document.getElementById("editCollabs");

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

function paintEditCollabs(collab) {
  editCollabs.push(collab);

  const collabs = document.getElementById("editCollabs");

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
  var xhr = new XMLHttpRequest();
  xhr.open(
    "GET",
    `http://localhost:8080/proyecto/Proyectos?collabs=true`,
    true
  );
  xhr.onload = function () {
    collabsList = JSON.parse(this.responseText);
    console.log(collabsList);
    setCollabs();
  };

  xhr.send();
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

  const editSelectHtml = document.getElementById("editCollabSelect");
  editSelectHtml.innerHTML = "";
  const defaultOptionEdit = document.createElement("option");
  defaultOptionEdit.hidden = true;
  defaultOptionEdit.disabled = true;
  defaultOptionEdit.selected = true;
  defaultOptionEdit.value = "";
  defaultOptionEdit.innerHTML = "Elige un Colaborador";

  editSelectHtml.appendChild(defaultOptionEdit);

  collabsList.forEach((collab, index) => {
    const optionHtml = document.createElement("option");
    optionHtml.value = index;
    optionHtml.innerHTML = collab.name;
    editSelectHtml.appendChild(optionHtml);
  });
}

function getProjects() {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/proyecto/Proyectos", true);
  xhr.onload = function () {
    projects = JSON.parse(this.responseText);
    const row = document.getElementById("projects-row");
    row.innerHTML = "";
    projects.forEach(paintProject);
  };

  xhr.send();
}

function deleteProject(id) {
  var xhr = new XMLHttpRequest();
  xhr.open("DELETE", `http://localhost:8080/proyecto/Proyectos?id=${id}`, true);
  xhr.onload = function () {
    response = JSON.parse(this.responseText);
    console.log(response);
  };

  xhr.send();
  getProjects();
}

function selectProject(id) {
  cleanProject();
  setCollabs();

  // TODO: Servicio GET Projects
  var xhr = new XMLHttpRequest();
  xhr.open("GET", `http://localhost:8080/proyecto/Proyectos?id=${id}`, true);
  xhr.onload = function () {
    selected = JSON.parse(this.responseText);
    console.log(id);
    console.log(selected);
    const edit = document.getElementById("editForm");
    edit.elements["name"].value = selected.name;
    edit.elements["description"].value = selected.description;

    selected.collabs.forEach(paintEditCollabs);
  };

  xhr.send();
  // selected = proyectos[id]
  //  selected = {
  //   id: 0,
  //   name: "Apple",
  //   description: "lorem ipsum",
  //   date: "10/10/21",
  //   collabs: [{ name: "Pepe", uid: 0 }],
  // };
}

getProjects();
getCollabs();
