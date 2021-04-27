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

  paintStorie(data);

  // TODO: Guardar en la base de datos
  // saveStorie(data)
}

function paintStorie(data) {
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

  body.appendChild(title);
  body.appendChild(date);
  body.appendChild(storieId);
  body.appendChild(days);

  card.appendChild(body);

  col.appendChild(card);
  stories.appendChild(col);
}

function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min)) + min;
}
