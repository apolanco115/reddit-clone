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
      for (let i = 0; i < 15; ++i) {
        updatePostDom(subRes[i].title, subRes[i].description, subRes[i].id);
        viewComments(subRes[i].id);
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
      return res.json();
    })
    .then(res => {
      updatePostDom(title.value, description.value, res.id);
    })
    .catch(err => {
      console.log(err);
    });
}

function updatePostDom(postTitle, postDesc, postId) {
  const list = document.querySelector(".posts");
  const item = document.createElement("li");
  item.className = "post"
  item.id = postId;
  const title = document.createElement("h2");
  const description = document.createElement("p");
  item.appendChild(title);
  item.appendChild(description);
  title.innerText = postTitle;
  description.innerText = postDesc;
  list.insertBefore(item, list.firstChild);
  createCommentForm(postId);
  createDelPostButton(postId);
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
      return res.json();
    })
    .then(res => {
      updateCommentDom(commentText.value, postId, res.id);
    })
    .catch(err => {
      console.log(err);
    });
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

function removePostDom(postId){
  document.getElementById(`postId${postId}`).remove()
}



function createDelComButton(commentId){
  const delButton = document.createElement("button");
  delButton.setAttribute("type", "button");
  delButton.innerText = 'delete comment';
  delButton.onclick= () => delComment(event, commentId);
  document.getElementById(`commentId${commentId}`).appendChild(delButton);

}


function createDelPostButton(postId){
  const delButton = document.createElement("button");
  delButton.setAttribute("type", "button");
  delButton.innerText = 'delete post';
  delButton.onclick= () => delPost(event, postId);
  document.getElementById(`${postId}`).appendChild(delButton);

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
      }else if(res.status === 400){
        alert('you can only delete your own comments, buddy.');
      }else{
        alert('please delete your commennt after a page refresh');
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