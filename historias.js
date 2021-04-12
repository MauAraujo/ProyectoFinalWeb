function createStorie() {
  const stories = document.getElementById("stories");
  const col = document.createElement("div");
  col.classList.add("col-2", "me-4", "my-2");

  const card = document.createElement("div");
  card.classList.add("card", "hovereable");

  const body = document.createElement("div");
  body.classList.add("card-body");

  const title = document.createElement("h4");
  title.classList.add("card-title");
  title.innerHTML = "Historia 1";

  const date = document.createElement("span");
  date.classList.add("text-start");
  date.innerHTML = new Date().toLocaleDateString();

  const storieId = document.createElement("p");
  storieId.classList.add("storie-id", "text-center", "my-5");
  storieId.innerText = getRandomInt(1, 100).toString();

  const days = document.createElement("p");
  days.classList.add("fs-3", "text-center");
  days.innerText = getRandomInt(10, 15).toString() + " días";

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
