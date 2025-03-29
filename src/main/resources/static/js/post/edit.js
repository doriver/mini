function cancelEdit() {
    var postId = $("#postId").val();
    location.href="/view/posts/" + postId + "/detail";
}


function editPost() {
    var postId = $("#postId").val();
    
    var title = $("#title").val();
    var content = $("#content").val();

    var params = {
        "title": title
        , "content": content
    }

    $.ajax({
        type:"PATCH",
        url:"/api/posts/" + postId,
        contentType: "application/json",
        data: JSON.stringify(params),
        success:function(response) {

            if (response === "fail") {
                alert("글 수정 실패");
            } else if (response === "success") {
                alert("글 수정 성공");
                location.href="/view/posts/" + postId + "/detail";
            }
        },
        error:function(xhr) {
            let response = xhr.responseJSON;
            console.log(response);
            alert("에러발생 \n" + response);
        }
    });
}