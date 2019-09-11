function postLogin(event) {
   event.preventDefault();
   const email = document.querySelector('.email');
   const password = document.querySelector('.pw');
   const username = document.querySelector('.username');
   console.log(email.value, password.value)
   fetch('http://thesi.generalassemb.ly:8080/login', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({
               email: email.value,
               password: password.value,
           })
   })
   .then((res) => {
     //console.log(res)
       return res.json();
   })
   .then((res) => {
     console.log(res)
       localStorage.setItem('user', res.token);
       // createPost();
   })
   .catch((err) => {
       console.log(err);
   })
}