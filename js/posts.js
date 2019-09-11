function getRandomSubset(arr, size) {
    let shuffled = arr.slice(0), i = arr.length, min = i - size, temp, index;
    while (i-- > min) {
        index = Math.floor((i + 1) * Math.random());
        temp = shuffled[index];
        shuffled[index] = shuffled[i];
        shuffled[i] = temp;
    }
    return shuffled.slice(min);
}


function listSomePosts(event) {
  fetch("http://thesi.generalassemb.ly:8080/post/list", {
    method: "GET",
    headers: {
      "Content-Type": "application/json"
    }
  })
    .then(res => {
      return res.json();
    })
    .then(res => {
      let subRes = getRandomSubset(res, 15);
      const list = document.querySelector(".posts");
      for (let i = 0; i < subRes.length; ++i) {
        const item = document.createElement("li");
        item.className = "post"
        const title = document.createElement("h2");
        const description = document.createElement("p");
        item.appendChild(title);
        item.appendChild(description);
        title.innerText = subRes[i].title;
        description.innerText = subRes[i].description;
        list.appendChild(item);
      }
    })
    .catch(err => {
      console.log(err);
    });
}
listSomePosts();

function createPost(event) {
  event.preventDefault();
  const title = document.querySelector(".title");
  const description = document.querySelector(".description");
  fetch("http://thesi.generalassemb.ly:8080/post", {
    method: "POST",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      title: title.value,
      description: description.value
    })
  })
    .then(res => {
      console.log(res);
      updateDom(res);
    })
    .catch(err => {
      console.log(err);
    });
}

function updateDom() {
  document.querySelector(".postForm").style.display = "block";
  fetch("http://thesi.generalassemb.ly:8080/user/post", {
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user")
    }
  })
    .then(res => {
      return res.json();
    })
    .then(res => {
      const list = document.querySelector(".posts");
      const item = document.createElement("li");
      item.className = "post"
      const title = document.createElement("h2");
      const description = document.createElement("p");
      item.appendChild(title);
      item.appendChild(description);
      title.innerText = res[res.length-1].title;
      description.innerText = res[res.length-1].description;
      list.insertBefore(item, list.firstChild);
    })
    .catch(err => {
      console.log(err);
    });
}