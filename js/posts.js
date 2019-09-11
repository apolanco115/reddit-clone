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
