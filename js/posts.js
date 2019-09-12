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
      subRes = res;
      const list = document.querySelector(".posts");
      for (let i = 0; i < 15; ++i) {
        const item = document.createElement("li");
        item.className = "post"
        item.id = subRes[i].id
        const title = document.createElement("h2");
        const description = document.createElement("p");
        item.appendChild(title);
        item.appendChild(description);
        title.innerText = subRes[i].title;
        description.innerText = subRes[i].description;
        list.appendChild(item);
        viewComments(subRes[i].id)
        createCommentForm(subRes[i].id);
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
      updatePostDom(res);
    })
    .catch(err => {
      console.log(err);
    });
}

function updatePostDom() {
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

function updateCommentDom(commentText, postId, commentId) {
  const post = document.getElementById(`${postId}`);
  const list = post.querySelector("ul");
  const item = document.createElement("li");
  item.id = `commentId${commentId}`
  const text = document.createElement("p");
  item.appendChild(text);
  text.innerText = commentText;
  list.appendChild(item);
  createDelComButton(commentId);
}

function viewComments(postId) {
  fetch(`http://thesi.generalassemb.ly:8080/post/${postId}/comment`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json"
    }
  })
    .then(res => {
      return res.json();
    })
    .then(res => {
      const post = document.getElementById(`${postId}`);
      const list = document.createElement("ul");
      list.className = ".comments"
      post.appendChild(list);
      for (let i = 0; i < res.length; ++i) {
        updateCommentDom(res[i].text, postId, res[i].id);
      }
    })
    .catch(err => {
      console.log(err);
    });
}

function addComment(event, postId) {
  event.preventDefault();
  const commentText = document.getElementById(`textField${postId}`);
  fetch(`http://thesi.generalassemb.ly:8080/comment/${postId}`, {
    method: "POST",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      text: commentText.value,
    })
  })
    .then(res => {
      console.log(res);
      updateCommentDom(commentText.value, postId);
    })
    .catch(err => {
      console.log(err);
    });
}


function whatsMyId(event){
  event.preventDefault();
  alert(event.target.parentNode.id);
}

function createCommentForm(postId) {
    const form = document.createElement("form");
    const textField = document.createElement("input");
    textField.id = `textField${postId}` 
    const submitButton = document.createElement("input");  

    form.onsubmit = () => addComment(event, postId);
    textField.setAttribute("type", "text");
    textField.setAttribute("placeholder", "comment");
    form.appendChild(textField);  

    submitButton.type="submit";
    form.appendChild(submitButton);

    document.getElementById(`${postId}`).appendChild(form);
}


function removeComDom(commentId){
  document.getElementById(`commentId${commentId}`).remove()
}




function createDelComButton(commentId){
  const delButton = document.createElement("button");
  delButton.setAttribute("type", "button");
  delButton.innerText = 'delete comment';
  delButton.onclick= () => delComment(event, commentId);
  document.getElementById(`commentId${commentId}`).appendChild(delButton);

}



function delComment(event, commentId) {
  event.preventDefault();
  fetch(`http://thesi.generalassemb.ly:8080/comment/${commentId}`, {
    method: "DELETE",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    }
  })
    .then(res => {
      console.log(res);
      if(res.status === 200){
        removeComDom(commentId);
      }else{
        alert('you can only delete your own comments, buddy.')
      }
    })
    .catch(err => {
      console.log(err);
    });
}

function delPost(event, postId){
  event.preventDefault();
  fetch(`http://thesi.generalassemb.ly:8080/post/${postId}`, {
    method: "DELETE",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    }
  })
    .then(res => {
      console.log(res);
      if(res.status === 200){
        removeComDom(commentId);
      }else{
        alert('you can only delete your own posts, buddy.')
      }
    })
    .catch(err => {
      console.log(err);
    });

}



