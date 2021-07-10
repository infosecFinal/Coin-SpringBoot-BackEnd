
function approveUser() { //.on 이벤트 트리거

    var selectVal = $("#login option:selected").val();
    if (selectVal === "user") {
        console.log(selectVal);
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
                document.location.href = '/index';
            } else if (data.status === 103) {
                alert("유저 로그인 실패! 아이디와 비밀번호를 다시 입력해주세요!")
            }
        })
    }
}

function userDuplicateBtn() { //.on 이벤트 트리거
    console.log(data)

    if (check($("#user_id"))) {
        var data = {};
        $("form[name=regiform]").serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        console.log(data)

        $.ajax({
            type: "POST", //fix
            dataType: "json", //fix
            contentType: "application/json; charset=utf-8;", //fix
            url: "/api/udup",
            data: JSON.stringify(data), //fix ; 객체->스트링
        }).done(function (data) {

            //로직 필요에 따라 변경
            if (data.status === 200) {
                alert("사용 가능한 아이디입니다.")
            } else if (data.status === 201) {
                alert("이미 존재하는 아이디입니다. 아이디를 변경해주세요.")
            }
        })
    }
}

function registerUser() { //.on 이벤트 트리거
    console.log(data)

    if (check($("#user_id"))) {
        var data = {};
        $("form[name=regiform]").serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        console.log(data)

        $.ajax({
            type: "POST", //fix
            dataType: "json", //fix
            contentType: "application/json; charset=utf-8;", //fix
            url: "/api/uregi",
            data: JSON.stringify(data), //fix ; 객체->스트링
        }).done(function (data) {

            //로직 필요에 따라 변경
            if (data.status === 100) {
                alert("유저 회원가입 성공! 환영합니다!")
                document.location.href = '/index';
            } else if (data.status === 101) {
                alert("유저 회원가입 실패! 아이디 중복확인을 진행해주세요!")
            }
        })
    }
}

function userDelete() { //.on 이벤트 트리거
    let value = confirm("정말 탈퇴하시겠습니까?");

    if(value) {
        var data = {};
        $("form[name=deleteform]").serializeArray().map(function (x) {
            data[x.name] = x.value;
        });
        console.log(data)

        $.ajax({
            type: "POST", //fix
            dataType: "json", //fix
            contentType: "application/json; charset=utf-8;", //fix
            url: "/api/udelete",
            data: JSON.stringify(data), //fix ; 객체->스트링
        }).done(function (data) {
            //로직 필요에 따라 변경
            if (data.status === 400) {
                alert("유저 회원탈퇴가 완료되었습니다. 이용해주셔서 감사합니다.")
                document.location.href = '/index';
            } else if (data.status === 401) {
                alert("비밀번호 입력 오류입니다. 비밀번호를 다시 입력해주세요.")
            }
        })
    }
}

// 특수문자 코딩
function check(str) {

    if (str.val() === '' || str.val() == null) {
        alert('값을 입력해주세요');
        return false;
    }
//
//     var blank_pattern = /^\s+|\s+$/g;
//     if (str.val().replace(blank_pattern, '') === "") {
//         alert('공백만 입력되었습니다.');
//         return false;
//     }
//
//     //공백 금지
//     //var blank_pattern = /^\s+|\s+$/g;(/\s/g
//     var blank_pattern = /[\s]/g;
//     if (blank_pattern.test(str.val()) === true) {
//         alert('공백은 사용할 수 없습니다.');
//         return false;
//     }
//
//     var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
//
//     if (special_pattern.test(str.val()) === true) {
//         alert('특수문자는 사용할 수 없습니다.');
//         return false;
//     }
//
     return true;
//
 }