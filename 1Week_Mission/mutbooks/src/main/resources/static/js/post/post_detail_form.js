'use strict'

const Editor = toastui.Editor;
const viewer = new Editor.factory({
    el: document.querySelector('#viewer'),
    initialValue: document.querySelector("#content").value,
    viewer: true,
    plugins: [Editor.plugin.codeSyntaxHighlight],
});
