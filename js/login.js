function postData(event) {
   event.preventDefault();
   const email = document.querySelector('.email');
   const password = document.querySelector('.pw');
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
      return res.json();
   })
   .then((res) => {
     console.log(res)
       localStorage.setItem('user', res.token);
       if(res.httpStatus !== 'BAD_REQUEST') {
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