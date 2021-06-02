// function verify(str){
// 	var pw = document.getElementById("pw");
// 	var pw2 = document.getElementById("pw2");
// 	if(pw.value == pw2.value){
// 		alert(`${str}이 되었습니다.`);
// 		return true;
// 	}else{
// 		alert('비밀번호를 확인해주세요');
// 		return false;
// 	}
// }
let form = document.querySelector('#form');

function asdasd() {
	let name = document.getElementById('name').value;
	let email = form.email.value;
	let gender = form.gender.value;
	let id = form.id.value;
	let pw = form.pw.value;
	let pw2 = form.pw2.value;

	if(id.length < 5){
		document.getElementById('nmerror').innerText='아이디는 5자 이상 입력해주세요.';
		return;
	}else{
		document.getElementById('nmerror').innerText='';
	}

	if(pw.length <8 || pw2.length <8){
		document.getElementById('pwerror').innerHTML='비밀번호는 8자 이상 입력해주세요.';
		return;
	}else{
		document.getElementById('pwerror').innerHTML='';
	}
	if (pw != pw2) {
		document.getElementById('pwerror').innerHTML='비밀번호를 확인해주세요';
		return;
	}else{
		document.getElementById('pwerror').innerHTML='';
	}

	let param = {
		name,
		email,
		gender,
		id,
		pw
	}
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
	}

	fetch('/user/join.go',init)
		.then(res => res.json())
		.then(myJson => {
			console.log(myJson);
			switch (myJson){
				case 0:
					document.getElementById('nmerror').innerText='이미 존재하는 아이디입니다.';
					break;
				case 1:
					location.href='/ojm';
					alert('회원가입이 완료되었습니다.');
					break;
			}
		});
}