<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<section>
		<header class="content-title">
			<h2>비밀번호 변경</h2><br>
			<p>안전한 비밀번호로 재설정해주세요.</p>
		</header>
		<form action="/user/edit/pw.go?id=${param.id }" method="post" class="row formt" onsubmit="return pw_check();">
			<div class="input_row">
				<input class="int" type="password" name="pw" id="pw" value="${param.pw}" required autofocus maxlength="20" placeholder="비밀번호">
			</div>
			<div class="input_row">
				<input class="int" type="password" name="pw2" id="pw2" value="${param.pw }" required maxlength="20" placeholder="비밀번호확인">
			</div>
			<div class="error">
				<p id="pwerror">${msg }
			</div>
			<div class="submits">
				<button type="button" class="cancel btn btn-outline-secondary" onclick="againCheck('ojm','취소')">취소</button>
				<input type="submit" class="sub btn btn-outline-info" value="변경">
			</div>
		</form>
	</section>
</div>
<script src="/js/edit_pw.js"></script>