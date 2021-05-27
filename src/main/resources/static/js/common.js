function goPage(page){
	location.href='/'+page;
}

function againCheck(page, str) {
	if (confirm(`정말 ${str}하시겠습니까?`) == true){
	    goPage(page);
		if(str =='탈퇴') alert('탈퇴되었습니다.');
	}else{
	    return false;
	}
}

function setType(type){
	document.getElementById('asd').innerHTML = type;
    document.getElementById('foodType').value = type;
}