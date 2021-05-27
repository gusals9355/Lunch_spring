//write.jsp 파일 썸네일
var input = document.querySelector('.input');
var preview = document.querySelector('.preview');

input.addEventListener('change', updateImageDisplay);
  
function updateImageDisplay() {
	while(preview.firstChild) { 
	  preview.removeChild(preview.firstChild); //프리뷰의 모든 내용을 지움
	}

	const curFiles = input.files;
	if(curFiles.length === 0) { //선택된 파일이 없을때
	  const para = document.createElement('p');
	  para.textContent = '선택된 파일이 없습니다';
	  preview.appendChild(para);
	} else {
	  const list = document.createElement('ol');
	  preview.appendChild(list);

	  for(const file of curFiles) {
	    const listItem = document.createElement('li');
	    const para = document.createElement('p');
	    if(validFileType(file)) {
	      const image = document.createElement('img');
	      image.src = URL.createObjectURL(file);

	      listItem.appendChild(image);
	      listItem.appendChild(para);
	    } else {
	      para.textContent = '파일 타입이 올바르지 않습니다. 다시 확인해주세요.';
	      listItem.appendChild(para);
	    }

	    list.appendChild(listItem);
	  }
	}
}
  
const fileTypes = [
	'image/apng',
	'image/bmp',
	'image/gif',
	'image/jpeg',
	'image/pjpeg',
	'image/png',
	'image/svg+xml',
	'image/tiff',
	'image/webp',
	'image/x-icon'
];

function validFileType(file) {
	return fileTypes.includes(file.type);
}
function setStar(i) {
    document.getElementById('star').value = i;
	document.getElementById('zxc').innerHTML = "";
	switch(i){
		case 1:
		for(var j=1; j<=i; j++)document.getElementById('star'+j).className='bi bi-star-fill';
		for(var j; j<=5; j++)document.getElementById('star'+j).className='bi bi-star';
		break;
		case 2:
		for(var j=1; j<=i; j++)document.getElementById('star'+j).className='bi bi-star-fill';
		for(var j; j<=5; j++)document.getElementById('star'+j).className='bi bi-star';
		break;
		case 3:
		for(var j=1; j<=i; j++)document.getElementById('star'+j).className='bi bi-star-fill';
		for(var j; j<=5; j++)document.getElementById('star'+j).className='bi bi-star';
		break;
		case 4:
		for(var j=1; j<=i; j++)document.getElementById('star'+j).className='bi bi-star-fill';
		for(var j; j<=5; j++)document.getElementById('star'+j).className='bi bi-star';
		break;
		case 5:
		for(var j=1; j<=i; j++)document.getElementById('star'+j).className='bi bi-star-fill';
		for(var j; j<=5; j++)document.getElementById('star'+j).className='bi bi-star';
		break;
	}
}

function drawStar(n){
	var iArr = document.getElementsByClassName('star');
	var iArrCnt = 0;
	for(var i=0;i<n;i++,iArrCnt++){
		iArr[iArrCnt].className = "bi bi-star-fill";
	}
	for(var i=0;i<iArr.length-n;i++,iArrCnt++){
		iArr[iArrCnt].className = "bi bi-star";
	}
}
