
//takes in an array and returns an shuffled version of specified size.
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

//queries api for list of all posts
function listSomePosts(event) {
  fetch('http://localhost:8181/post/list', {
    method: "GET",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    }
  })
    .then(res => {
      return res.json();
    })
    .then(res => {
      for (let i = 0; i < res.length; ++i) {
        updatePostDom(res[i].postTitle, res[i].postBody, res[i].id);
        for(let j = 0; j < res[i].comments.length; ++j){
          updateCommentDom(res[i].comments[j].comment, res[i].id, res[i].comments[j].id);
        }
        //viewComments(res[i].id);
      }
    })
    .catch(err => {
      console.log(err);
    });
}
window.onload = listSomePosts(event);
window.onload = loadUserAvatar(event);

function loadUserAvatar(event){
  const av = document.querySelector(".av-img");
  const randString = Math.random().toString(36).slice(2);
  av.setAttribute("src", `http://api.adorable.io/avatars/285/[${randString}].png`)
}

function listRecentUsers(user){
  const list = document.querySelector(".recent-users");
  const av = document.createElement("img");
  const userText = document.createElement("p");
  const randString = Math.random().toString(36).slice(2);
  av.setAttribute("src", `http://api.adorable.io/avatars/285/[${randString}].png`)
  item = document.createElement("li");
  item.appendChild(av);
  userText.innerText = user;
  item.appendChild(userText);
  list.appendChild(item)
}


//queries the api for creating post
function createPost(event) {
  event.preventDefault();
  const title = document.querySelector(".title");
  const body = document.querySelector(".description");
  fetch("http://localhost:8181/post/", {
    method: "POST",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      postTitle: title.value,
      postBody: body.value
    })
  })
    .then(res => {
      console.log(res);
      return res.json();
    })
    .then(res => {
      updatePostDom(title.value, body.value, res.id);
      title.value='';
      body.value='';
      createPostWindow.style.display = "none";
    })
    .catch(err => {
      console.log(err);
    });
}
//updates html to display a post
function updatePostDom(postTitle, postDesc, postId) {
  const list = document.querySelector(".posts");
  const item = document.createElement("li");
  const comList = document.createElement("ul");
  comList.className = ".comments";
  item.className = "post";
  item.id = postId;
  list.insertBefore(item, list.firstChild);
  createDelPostButton(postId);
  const title = document.createElement("h2");
  const body = document.createElement("p");
  item.appendChild(title);
  item.appendChild(body);
  title.innerText = postTitle;
  body.innerText = postDesc;
  item.appendChild(comList);
  createCommentForm(postId);
}

//updates dom to display comments
function updateCommentDom(commentText, postId, commentId) {
  const post = document.getElementById(`${postId}`);
  const list = post.querySelector("ul");
  const item = document.createElement("li");
  item.id = `commentId${commentId}`;
  item.className = ".comment";
  const text = document.createElement("p");
  item.appendChild(text);
  text.innerText = commentText;
  list.appendChild(item);
  createDelComButton(commentId);
}

//queries api to add comment
function addComment(event, postId) {
  event.preventDefault();
  const commentText = document.getElementById(`textField${postId}`);
  fetch('http://localhost:8181/comment', {
    method: "POST",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      comment: commentText.value,
      post: {
        id: postId
      }
    })
  })
    .then(res => {
      console.log(res);
      return res.json();
    })
    .then(res => {
      updateCommentDom(commentText.value, postId, res.id);
      commentText.value='';
    })
    .catch(err => {
      console.log(err);
    });
}

//queries api for comments on postId
function viewComments(postId) {
  fetch('http://localhost:8181/comment/list', {
    method: "GET",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    }
  })
    .then(res => {
      return res.json();
    })
    .then(res => {
      for (let i = 0; i < res.length; ++i) {
        updateCommentDom(res[i].comment, postId, res[i].id);
      }
    })
    .catch(err => {
      console.log(err);
    });
}



//creates a form to enter and submit comments on posts
function createCommentForm(postId) {
    const form = document.createElement("form");
    const list = document.createElement("ul")
    const item0 = document.createElement("li")
    const item1 = document.createElement("li")
    const textField = document.createElement("textarea");
    textField.id = `textField${postId}`
    textField.className = "textbox";
    textField.className += " comment-box";
    textField.required = true;
    const submitButton = document.createElement("input");
    form.onsubmit = () => addComment(event, postId);
    textField.setAttribute("rows", "5");
    textField.setAttribute("type", "30");
    textField.setAttribute("placeholder", "comment");
    item0.appendChild(textField);


    submitButton.type="submit";
    submitButton.className = "button-style";
    submitButton.className += " create-com";
    submitButton.value = `\u002B comment`;
    item1.appendChild(submitButton);
    list.appendChild(item0);
    list.appendChild(item1);
    form.appendChild(list);

    document.getElementById(`${postId}`).appendChild(form);
}

function createCommentButton(postId){
  const delButton = document.createElement("button");
  delButton.setAttribute("type", "button");
  delButton.className = 'button-style';
  delButton.className += ' create-com';
  delButton.innerHTML = `\u002B comment`;
  delButton.onclick= () => delPost(event, postId);
  document.getElementById(`${postId}`).appendChild(delButton);

}

//removes comment from dom
function removeComDom(commentId){
  document.getElementById(`commentId${commentId}`).remove()
}

//removes post from dom
function removePostDom(postId){
  document.getElementById(`${postId}`).remove()
}


//creates delete button comments
function createDelComButton(commentId){
  const delButton = document.createElement("button");
  delButton.setAttribute("type", "button");
  delButton.className = 'button-style';
  delButton.className += ' del-com';
  delButton.innerHTML = '&times;';
  delButton.onclick= () => delComment(event, commentId);
  document.getElementById(`commentId${commentId}`).appendChild(delButton);

}

//creates delete post button
function createDelPostButton(postId){
  const delButton = document.createElement("button");
  delButton.setAttribute("type", "button");
  delButton.className = 'button-style';
  delButton.className += ' del-post';
  delButton.innerHTML = '&times;';
  delButton.onclick= () => delPost(event, postId);
  document.getElementById(`${postId}`).appendChild(delButton);

}


//queries the api to delete comment
function delComment(event, commentId) {
  event.preventDefault();
  fetch(`http://localhost:8181/comment/${commentId}`, {
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
      }else if(res.status === 406){
        alert('you can only delete your own comments, buddy.');
      }
    })
    .catch(err => {
      console.log(err);
    });
}

//quiereis the api to delete post
function delPost(event, postId){
  event.preventDefault();
  fetch(`http://localhost:8181/post/${postId}`, {
    method: "DELETE",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    }
  })
    .then(res => {
      console.log(res);
      if(res.status === 200){
        removePostDom(postId);
      }else{
        alert('you can only delete your own posts, buddy.')
      }
    })
    .catch(err => {
      console.log(err);
    });

}

function updateProf(event) {
  event.preventDefault();
  const email = document.querySelector(".email");
  const mobile = document.querySelector(".mobile");
  console.log(email.value, mobile.value, localStorage.getItem("user"));
  fetch("http://localhost:8181/profile", {
    method: "PUT",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      email: email.value,
      mobile: mobile.value
    })
  })
    .then(res => {
      console.log(res);
      updateProfWindow.style.display = "none";
      return res.json();
    })
    .catch(err => {
      console.log(err);
    });
}


function viewProf(event) {
  event.preventDefault();
  document.querySelector('.view-profile-box').style.display='block';
  const email = document.querySelector(".vemail");
  const mobile = document.querySelector(".vmobile");
  fetch("http://localhost:8181/profile", {
    method: "Get",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("user"),
      "Content-Type": "application/json"
    },
  })
  .then(res => {
    return res.json();
  })
  .then(res =>{
    email.innerText = "email: " + res.email;
    mobile.innerText = "mobile: " + res.mobile;
  })
  .catch(err => {
    console.log(err);
  });
}



const updateProfWindow = document.querySelector('.update-profile-box');

const viewProfWindow = document.querySelector('.view-profile-box');

const createPostWindow = document.querySelector('.create-post-box');

window.onclick = function(event) {
  if (event.target.className === 'update-profile-box' ) {
    updateProfWindow.style.display = "none";
  }
  if (event.target.className === 'view-profile-box' ) {
    viewProfWindow.style.display = "none";
  }
   if (event.target.className === 'create-post-box') {
    createPostWindow.style.display = "none";
  }
}



// window.onclick = function(event) {
//   if (event.target.className === 'create-post-box') {
//     createPostWindow.style.display = "none";
//   }
// }
