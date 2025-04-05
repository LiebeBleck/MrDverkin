const burgerBtn = document.querySelector('.burger');

const burgerPopup = document.querySelector('.burger_popup');

const burgerBtnClose = document.querySelector('.burger_popup_close');

burgerBtn.addEventListener('click', () => {
    burgerPopup.classList.toggle('show');
    burgerPopup.style.display = 'flex';
})

burgerBtnClose.addEventListener('click', () => {
    burgerPopup.classList.toggle('close');
    burgerPopup.style.display = 'none';
})

