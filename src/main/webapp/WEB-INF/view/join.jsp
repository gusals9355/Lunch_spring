<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<section class="row">
		<header class="content-title">
			<h1>회원가입</h1>
			<p>	&nbsp; 환영합니다!</p>
		</header>
		<form action="join.go" method="post" id="form" class="row formt">
			<div class="input_row">
				<input class="int" type="text" name="name" id="name" placeholder="이름" maxlength="10" required="required" value="${param.name }">
			</div>
			<div class="input_row">
				<input class="int" type="email" name="email" placeholder="이메일" maxlength="50" required="required" value="${param.email }">
				<p>이메일은 아이디 또는 비밀번호를 찾을 때 사용합니다.
			</div>
			<div class="input_row2 btn-group btn-group-toggle" data-toggle="buttons">
				<label class="btn btn-secondary">
					<input type="radio" name="gender" value="M" checked> 남
				</label>
				<label class="btn btn-secondary">
					<input type="radio" name="gender" value="F"> 여
				</label>
			</div>
			<div class="input_row">
				<input class="int" type="text" name="id" id="id" placeholder="아이디" maxlength="20" required="required" value="${param.id }">
			</div>
			<div class="error">
				<p class="error" id="nmerror">${msg}</p>
			</div>
			<div class="input_row">
				<input class="int" type="password" name="pw" id="pw" placeholder="비밀번호" maxlength="20" required="required">
			</div>
			<div class="error">
				<p class="error" id="pwerror"></p>
			</div>
			<div class="input_row">
				<input class="int" type="password" name="pw2" id="pw2" placeholder="비밀번호확인" maxlength="20" required="required">
			</div>
			<div class="input_row">
				<input class="btn btn-success" type="button" value="회원가입" onclick="asdasd();">
			</div>
		</form>
	</section>
</div>
<script src="/js/join.js"></script>