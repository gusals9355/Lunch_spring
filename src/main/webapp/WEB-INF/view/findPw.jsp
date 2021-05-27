<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="container" style="flex-direction: column; align-items: center;">
	<header class="content-title">
		<h1>비밀번호 찾기</h1>
	</header>
	<form action="findPw" method="post" onsubmit="return verify('비밀번호 변경');">
		<div class="input_row">
			<input class="int" type="text" name="id" id="id" value="${param.id }" placeholder="아이디" required><br>
		</div>
		<div class="input_row">
			<input class="int" type="text" name="name" id="name" value="${param.name }" placeholder="이름" required autofocus><br>
		</div>
		<div class="input_row">
			<input class="int" type="email" name="email" id="email" value="${param.email }" placeholder="이메일" required><br>
		</div>
			<p>${msg }
		<div class="submits">
			<button type="button" class="cancel btn btn-outline-secondary" onclick="againCheck('ojm','취소')">취소</button>
			<input class="sub btn btn-outline-success" type="submit" value="찾기">
		</div>
	</form>
</div>