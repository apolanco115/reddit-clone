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
   fetch('http://localhost:8181/signup/', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({
               userProfile: {
                 email: email.value
               },
               password: password.value,
               username: username.value
           })
   })
   .then((res) => {
       return res.json();
   })
   .then((res) => {
     console.log(res)
     if(res.httpStatus !== 'BAD_REQUEST') {
      localStorage.setItem('user', res.token);
      alert('thank you for signing up!');
      email.value='';
      password.value='';
      username.value='';
      signUpBox.style.display = "none";
     }else{
      localStorage.clear();
      alert('please enter valid user information');
      email.value='';
      password.value='';
      username.value='';
      signUpBox.style.display = "none";
     }
   })
   .catch((err) => {
       console.log(err);
   })
}
