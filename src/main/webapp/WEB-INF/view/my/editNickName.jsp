<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="container">
	<div class="content">
		<header class="content-title">
			<h2>닉네임 변경</h2>
		</header>
		<form action="/user/edit/nickname.do" method="post" onsubmit="alert('변경되었습니다.')">
			<div class="input_row">
				<label for="nickname">닉네임<input class="int" id="nickname" type="text" name="nickname" value="${userInfo.nickname }" required autofocus maxlength="10"></label>
			</div>
			<div class="input_row" style="margin-top: 20px;">
				<button type="button" class="cancel btn btn-outline-secondary" onclick="againCheck('ojm','취소')">취소</button>
				<input type="submit" class="btn btn-outline-info" value="변경" style="width: 114px; height: 36px;">
			</div>
		</form>
	</div>
</div>