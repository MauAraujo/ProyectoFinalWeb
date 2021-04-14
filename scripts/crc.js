function createCRC() {
  const stories = document.getElementById("crcs");
  const col = document.createElement("div");
  col.classList.add("col-2", "me-4", "my-2");

  const card = document.createElement("div");
  card.classList.add("card", "hovereable");

  const body = document.createElement("div");
  body.classList.add("card-body");

  const title = document.createElement("h4");
  title.classList.add("card-title");
  title.innerHTML = "Clase diferente";

  const subtitle = document.createElement("span");
  subtitle.classList.add("text-start");
  subtitle.innerHTML = "Superclase diferente";

  body.appendChild(title);
  body.appendChild(subtitle);

  card.appendChild(body);

  col.appendChild(card);
  stories.appendChild(col);
}
