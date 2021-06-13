let form = document.querySelector('#form');

$('#login_check').on('click',function (){
    const param = {
        id: form.id.value,
        pw: form.pw.value
    };
    $.ajax({
        url: "login.go",
        type: "post",
        data: JSON.stringify(param),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (param){
            console.log(param.pw);
        },
        error: function (){
            alert('asd');
        }
    });
});
