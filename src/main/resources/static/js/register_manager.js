function verify(){
    let code = document.getElementById('code').value;
    let param = {
        code
    };
    ajax(param);
}

function ajax(param){
    const init = {
        method: 'POST',
        headers:{
            'accept' : 'application/json',
            'content-type' : 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(param)
    };

    fetch("/regi_manager.do",init)
        .then(res => res.json())
        .then(myJson => {
            console.log(myJson);
            switch (myJson.result){
                case "0":
                    document.getElementById('errorp').innerText='코드가 일치하지 않습니다.';
                    break;
                case "1":
                    location.href='/ojm';
                    alert('관리자 권한이 설정되었습니다.');
                    break;
            }
        });
}