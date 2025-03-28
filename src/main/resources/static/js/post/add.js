function cancelWrite() {

    // 작성하던 데이터들 보관하는거 구현 > 나중에 다시 작성할때 해당 데이터 보여주기

    location.href="/view/posts/list";
    // 페이지 정보 받을수있으면 해당 페이지로 갈듯, 여기선 굳이 필요없을듯, 다른곳에서 쓰일듯
}

function addPost() {
    var title = $("#title").val();
    var content = $("#content").val();

    var params = {
        "title": title
        , "content": content
    }

    $.ajax({
        type:"POST",
        url:"/api/posts",
        data:JSON.stringify(params),
        success:function(response) {

            if (response.result === "fail") {
                alert("글 등록 실패");
            } else if (response.result === "success") {
                alert("글 등록 성공");
                location.href="/view/posts/" + response.postId + "/detail";
            }
        },
        error:function(xhr) {
            let response = xhr.responseJSON;
            console.log(response);
            alert("에러발생 \n" + response);
        }
    });
}