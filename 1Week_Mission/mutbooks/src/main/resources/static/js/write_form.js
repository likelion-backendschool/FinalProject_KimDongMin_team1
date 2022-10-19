'use strict';

const markdownInput = document.querySelector('.markdown');
const htmlInput = document.querySelector('.html');
const submitBtn = document.querySelector('.write-btn');

const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    previewStyle: 'vertical',
    height: '500px',
});

submitBtn.addEventListener('click', () => {
    markdownInput.value = editor.getMarkdown();
    htmlInput.value = editor.getHTML();
})