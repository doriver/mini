function goListPage() {
    location.href="/view/posts/list";
}

function goEditPage() {
    var postId = $("#postId").val();
    location.href="/view/posts/" + postId + "/edit";
}

function deletePost() {
    var postId = $("#postId").val();
    
    $.ajax({
        type:"DELETE",
        url:"/api/posts/" + postId,
        success:function(response) {

            if (response === "fail") {
                alert("글 삭제 실패");
            } else if (response === "success") {
                alert("글 삭제 성공");
                location.href="/view/posts/list";
            }
        },
        error:function(xhr) {
            let response = xhr.responseJSON;
            console.log(response);
            alert("에러발생 \n" + response);
        }
    });    
}