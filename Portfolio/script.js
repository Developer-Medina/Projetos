const menuButton = document.getElementById('menu-toggle');

menuButton.addEventListener('click', () => {
    document.body.classList.toggle('menu-open');
})