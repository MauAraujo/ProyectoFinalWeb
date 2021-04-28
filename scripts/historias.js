let historias = [];

function createStorie() {
  const form = document.getElementById("storiesForm");
  const data = {
    title: form.elements["title"].value,
    id: form.elements["storieId"].value,
    description: form.elements["description"].value,
    date: form.elements["date"].value,
    score: form.elements["score"].value,
    observations: form.elements["observations"].value,
    time: form.elements["time"].value,
    timeSelect:
      document.querySelector('input[name="timeSelect"]:checked').value ||
      "days",
  };

  paintStory(data);

  // TODO: Guardar en la base de datos
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
  storieId.innerText = data.score || "Sin valor";

  const days = document.createElement("p");
  days.classList.add("fs-3", "text-center");
  days.innerText = `${data.time} ${
    data.timeSelect === "days" ? "Días" : "Semanas"
  }`;

  const pHtml = document.createElement("p");
  pHtml.classList.add("mt-5");

  const deleteButtonHtml = document.createElement("button");
  deleteButtonHtml.type = "button";
  deleteButtonHtml.classList.add("btn", "btn-outline-danger", "delete-button");
  deleteButtonHtml.innerHTML = "Borrar";
  deleteButtonHtml.onclick = `deleteStory(${data.id})`;

  pHtml.appendChild(deleteButtonHtml);

  body.appendChild(title);
  body.appendChild(date);
  body.appendChild(storieId);
  body.appendChild(days);
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
}

function getStories() {
  historias = [];
  // TODO: Servicio GET de Historias
  // historias =

  // Esto va a repintar cada que se incie la app y
  // cada que se borre una historia
  const stories = document.getElementById("stories");
  stories.innerHTML = "";
  historias.forEach(paintStory);
}

getStories();
