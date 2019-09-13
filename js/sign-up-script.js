
const signUpBox = document.querySelector('.sign-up-box');

window.onclick = function(event) {
  if (event.target.className === 'sign-up-box') {
    signUpBox.style.display = "none";
  }
}

function signUp(event) {
   event.preventDefault();
   const email = document.querySelector('.sign-up-email');
   const password = document.querySelector('.sign-up-password');
   const username = document.querySelector('.username');
   console.log(email.value, password.value, username.value)
   fetch('http://thesi.generalassemb.ly:8080/signup', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({
               email: email.value,
               password: password.value,
               username: username.value
           })
   })
   .then((res) => {
       return res.json();
   })
   .then((res) => {
     console.log(res)
       localStorage.setItem('user', res.token);
   })
   .catch((err) => {
       console.log(err);
   })
}