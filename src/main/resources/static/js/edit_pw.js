function pw_check(){
    let pw = document.getElementById('pw').value;
    let pw2= document.getElementById('pw2').value;

    if(pw.length <8 || pw2.length <8){
        document.getElementById('pwerror').innerHTML='비밀번호는 8자 이상 입력해주세요.';
        return false;
    }  else if (pw != pw2) {
        document.getElementById('pwerror').innerHTML='비밀번호를 확인해주세요';
        return false;
    }

    alert('비밀번호가 변경되었습니다.');
}