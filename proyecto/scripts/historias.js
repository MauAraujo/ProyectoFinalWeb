let historias = [];
let projectID = "";

function getProjectID() {
  const urlParams = new URLSearchParams(window.location.search);
  projectID = urlParams.get("id");
  console.log(projectID);
}

function createStorie() {
  const form = document.getElementById("storiesForm");
  const data = {
    title: form.elements["title"].value,
    projectid: projectID,
    description: form.elements["description"].value,
    date: form.elements["date"].value,
    value: form.elements["score"].value,
    observations: form.elements["observations"].value,
    days: form.elements["time"].value,
    timeSelect: "days",
  };

  // paintStory(data);

  cleanStoryForm();

  // TODO: Guardar en la base de datos
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "http://localhost:8080/proyecto/Historias?type=story", true);
  xhr.onload = function () {
    story = JSON.parse(this.responseText);
    console.log(story);
    getStories();
  };

  xhr.send(JSON.stringify(data));
  // saveStory(data)
}

function paintStory(data) {
  const stories = document.getElementById("stories");
  const col = document.createElement("div");
  col.classList.add("col-2", "me-4", "my-2");

  const card = document.createElement("div");
  card.classList.add("card", "hovereable");

  const body = document.createElement("div");
  body.classList.add("card-body");

  const title = document.createElement("h4");
  title.classList.add("card-title");
  title.innerHTML = data.title || "";

  const date = document.createElement("span");
  date.classList.add("text-start");
  console.log(data.date);
  date.innerHTML = data.date || "Sin fecha";

  const storieId = document.createElement("p");
  storieId.classList.add("storie-id", "text-center", "my-5");
  storieId.innerText = data.value || "Sin valor";

  const days = document.createElement("p");
  days.classList.add("fs-3", "text-center");
  days.innerText = `${data.days} ${
    data.timeSelect === "days" ? "Días" : "Semanas"
  }`;

  const descriptionHtml = document.createElement("p");
  descriptionHtml.classList.add("text-center", "my-5");
  descriptionHtml.innerText = data.description || "";

  const observationsHtml = document.createElement("p");
  observationsHtml.classList.add("text-center", "my-5");
  observationsHtml.innerText = data.observations || "";

  const pHtml = document.createElement("p");
  pHtml.classList.add("mt-5");

  const deleteButtonHtml = document.createElement("button");
  deleteButtonHtml.type = "button";
  deleteButtonHtml.classList.add("btn", "btn-outline-danger", "delete-button");
  deleteButtonHtml.innerHTML = "Borrar";
  deleteButtonHtml.setAttribute("onclick", `deleteStory(${data.id})`);
  // deleteButtonHtml.onclick = `deleteStory(${data.id})`;

  pHtml.appendChild(deleteButtonHtml);

  body.appendChild(title);
  body.appendChild(date);
  body.appendChild(storieId);
  body.appendChild(days);
  body.appendChild(descriptionHtml);
  body.appendChild(observationsHtml);

  body.appendChild(pHtml);

  card.appendChild(body);

  col.appendChild(card);
  stories.appendChild(col);
}

function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min)) + min;
}

function saveStlory(data) {
  // TODO: Servicio POST de Historia
}

function deleteStory(id) {
  // TODO: Servicio DELETE de Historia

  var xhr = new XMLHttpRequest();
  xhr.open(
    "DELETE",
    `http://localhost:8080/proyecto/Historias?id=${id}&type=story`,
    true
  );
  xhr.onload = function () {
    historias = JSON.parse(this.responseText);
    console.log(historias);
    getStories();
  };
  xhr.send();
}

function getStories() {
  historias = [];
  // TODO: Servicio GET de Historias
  // historias =

  var xhr = new XMLHttpRequest();
  xhr.open("GET", `http://localhost:8080/proyecto/Historias?id=${projectID}&type=story`, true);
  xhr.onload = function () {
    historias = JSON.parse(this.responseText);
    console.log(historias);
    const stories = document.getElementById("stories");
    stories.innerHTML = "";
    historias.forEach(paintStory);
  };

  xhr.send();
  // Esto va a repintar cada que se incie la app y
  // cada que se borre una historia
}

function cleanStoryForm() {
  const form = document.getElementById("storiesForm");
  form.elements["title"].value = "";
  form.elements["description"].value = "";
  form.elements["date"].value = "";
  form.elements["score"].value = "";
  form.elements["observations"].value = "";
  form.elements["time"].value = "";
}

getProjectID();
getStories();
