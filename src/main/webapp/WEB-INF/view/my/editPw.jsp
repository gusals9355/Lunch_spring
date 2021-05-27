<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="container" style="flex-direction: column; align-items: center;">
	<header class="content-title">
		<h2>비밀번호 변경</h2><br>
	</header>
	<form action="/user/edit/pw?id=${param.id }" method="post" onsubmit="return verify('비밀번호 변경');">
		<div class="input_row">
			<input class="int" type="password" name="pw" id="pw" value="${param.pw}" required autofocus maxlength="20" placeholder="비밀번호"> 
		</div>
		<div class="input_row">
			<input class="int" type="password" name="pw2" id="pw2" value="${param.pw }" required maxlength="20" placeholder="비밀번호확인"> 
		</div>
		<div class="error">
			<p>${msg }
		</div>
		<div class="submits">
			<button type="button" class="cancel btn btn-outline-secondary" onclick="againCheck('ojm','취소')">취소</button>
			<input type="submit" class="sub btn btn-outline-info" value="변경">
		</div>
	</form>
</div>
<script src="/js/join.js"></script>