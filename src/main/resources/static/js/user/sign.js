function signUp() {
    var nickname = $("#nickname").val();
    var role = $("#role").val();

    var params = {
        "nickname": nickname
        , "role": role
    }

    $.ajax({
        type:"POST",
        url:"/api/user/sign-up",
        data:params,
        success:function(response) {

            if (response === "fail") {
                alert("회원가입 실패");
            } else if (response === "success") {
                alert("회원가입 성공");
            }
        },
        error:function(xhr) {
            let response = xhr.responseJSON;
            console.log(response);
            alert("에러발생 \n" + response);
        }
    });
}

function signIn() {
    var nickname = $("#inNickname").val();

    var params = {
        "nickname": nickname
    }

    $.ajax({
        type:"POST",
        url:"/api/user/sign-in",
        data:params,
        success:function(response) {
            if (response === "fail") {
                alert("로그인 실패");
            } else if (response === "success") {
                alert("로그인 성공");
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