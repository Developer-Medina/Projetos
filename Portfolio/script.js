const menuButton = document.getElementById('menu-toggle');

menuButton.addEventListener('click', () => {
    document.body.classList.toggle('menu-open');
})

//getKey

console.log("Minha API Key:", API_KEY);

fetch(`https://api.exemplo.com/dados?apikey=${API_KEY}`)
  .then(res => res.json())
  .then(data => console.log(data))
  .catch(err => console.error("Erro:", err));


// Mail function


(function () {
    emailjs.init()
})