'use strict'

const Editor = toastui.Editor;
const viewer = new Editor.factory({
    el: document.querySelector('#viewer'),
    initialValue: document.querySelector("#content").value,
    viewer: true,
    plugins: [Editor.plugin.codeSyntaxHighlight],
});

function deletePost() {
    if (!confirm("게시글을 삭제하시겠습니까?")) {
        return false;
    }

    const id = document.querySelector(".delete-btn").value;
    location.href=`/post/${id}/delete`;
}
