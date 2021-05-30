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
	let name = form.name.value;
	let email = form.email.value;
	let gender = form.gender.value;
	let id = form.id.value;
	let pw = form.pw.value;
	let pw2 = form.pw2.value;

	if (pw != pw2) {
		alert('비밀번호를 확인해주세요');

		return false;
	}
	if(pw.length <8 || pw2.length <8){
		document.getElementById('pwerror').innerHTML='비밀번호는 8자 이상 입력해주세요.';
		return false;
	}
	if(id.length < 5){
		document.getElementById('nmerror').innerText='아이디는 5자 이상 입력해주세요.';
		return false;
	}

	let param = {
		name,
		email,
		gender,
		id,
		pw
	}

	ajax(param);

	function ajax(param){
		const init = {
			method: 'POST',
			headers:{
				'ContentType':'application/json'
			},
			body: JSON.stringify(param)
		}
		fetch('/user/join.go',init)
			.then(response => response.json())
			.then(myJson => console.log('sucess:',JSON.stringify(myJson)))
			.catch(error => console.log('error:',error));
				switch (myJson){
					case 1:
						console.log(myJson);
						alert('회원가입이 되었습니다.')
						return false;
					case 0:
						console.log(myJson);
						err.innerText('이미 존재하는 아이디입니다.');
						return false;
					default:
						console.log(myJson);
						return false;
				}
	}
}
