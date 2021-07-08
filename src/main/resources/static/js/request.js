$("#LoginBtn").on('click', () => { //.on 이벤트 트리거

    var selectVal = $("#login option:selected").val();

    if (selectVal === "user") {
        var data = {};
        $("form[name=loginform]").serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        console.log(data)

        $.ajax({
            type: "POST", //fix
            dataType: "json", //fix
            contentType: "application/json; charset=utf-8;", //fix
            url: "/api/uidlogin",
            data: JSON.stringify(data), //fix ; 객체->스트링
        }).done(function (data) {
            //로직 필요에 따라 변경
            if (data.status === 102) {

                alert("안녕하세요!")
                document.location.href = '/index.html';
            } else if (data.status === 103) {
                alert("유저 로그인 실패! 아이디와 비밀번호를 다시 입력해주세요!")
            }
        })
    }
})