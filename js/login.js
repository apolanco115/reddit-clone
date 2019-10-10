function postData(event) {
   event.preventDefault();
   const username = document.querySelector('.email');
   const password = document.querySelector('.pw');
   console.log(username.value, password.value)
   fetch('http://localhost:8181/login', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({
               username: username.value,
               password: password.value,
           })
   })
   .then((res) => {
      return res.json();
   })
   .then((res) => {
     // console.log(res)
       localStorage.setItem('user', res.token);
       console.log(res.token)
       if(res.token !== null) {
        window.location.href = "content-page.html";
       }else{
        localStorage.clear();
        alert('please enter valid user information');
       }

   })
   .catch((err) => {
       console.log(err);
   })
}
