function goListPage() {
    location.href="/view/posts/list";
}


function goEditPage() {
    var postId = $("#postId").val();
    location.href="/view/posts/" + postId + "/edit";
}

