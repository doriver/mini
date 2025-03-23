function signOut() {

    $.ajax({
        type:"GET",
        url:"/api/user/sign-out",
        success:function(response) {
            if (response === "fail") {
                alert("로그아웃 실패");
            } else if (response === "success") {
                alert("로그아웃 성공");
            }

            location.reload(true);
        },
        error:function(xhr) {
            let response = xhr.responseJSON;
            console.log(response);
            alert("에러발생 \n" + response);
        }
    });
}