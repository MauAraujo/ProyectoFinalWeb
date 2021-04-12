function createProject() {
  const row = document.getElementById("projects-row");
  const col = document.createElement("div");
  col.classList.add("col-3", "me-4", "my-2");

  const card = document.createElement("div");
  card.classList.add("card");

  const body1 = document.createElement("div");
  body1.classList.add("card-body");

  const title = document.createElement("h4");
  title.classList.add("card-title");
  title.innerHTML = "Proyecto 1";

  const description = document.createElement("p");
  description.classList.add("card-text");
  description.innerHTML =
    "Lorem ipsum dolor sit amet consectetur adipisicing elit. Repudiandae, sit et sequi nemo doloribus cum ipsam alias,tenetur quidem, rerum debitis eius nulla? Deleniti expedita aperiam architecto obcaecati alias maiores.";

  const collabs = document.createElement("ul");
  collabs.classList.add(
    "list-group",
    "list-group-horizontal",
    "list-group-flush",
    "scrollable"
  );

  for (let index = 0; index < 4; index++) {
    const li = document.createElement("li");
    li.classList.add("list-group-item");

    const avatar = document.createElement("img");
    avatar.src = "#";
    avatar.alt = `Avatar #${index + 1}`;
    li.appendChild(avatar);
    collabs.appendChild(li);
  }

  const body2 = document.createElement("div");
  body2.classList.add("card-body", "d-flex", "justify-content-between");

  const del = document.createElement("button");
  del.classList.add("btn", "btn-outline-danger");
  del.type = "button";
  del.innerHTML = "Eliminar";
  const open = document.createElement("button");
  open.classList.add("btn", "btn-outline-primary");
  open.type = "button";
  open.innerHTML = "Abrir";

  body1.appendChild(title);
  body1.appendChild(description);

  body2.appendChild(del);
  body2.appendChild(open);

  card.appendChild(body1);
  card.appendChild(collabs);
  card.appendChild(body2);

  col.appendChild(card);

  row.appendChild(col);
}
