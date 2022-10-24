'use strict';

const markdownInput = document.querySelector('.markdown');
const htmlInput = document.querySelector('.html');
const submitBtn = document.querySelector('.write-btn');

const Editor = toastui.Editor;
const editor = new Editor({
    el: document.querySelector('#editor'),
    plugins: [Editor.plugin.codeSyntaxHighlight],
    previewStyle: 'vertical',
    height: '500px',
});

submitBtn.addEventListener('click', () => {
    markdownInput.value = editor.getMarkdown();
    htmlInput.value = editor.getHTML();
})