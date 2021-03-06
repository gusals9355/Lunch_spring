<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<section class="row">
		<header class="content-title">
			<h1>로그인을 해주세요</h1>
			<p>아직 아이디가 없다면 회원가입을 해주세요</p>
		</header>
		<form action="login.go" id="loginForm" method="post" class="row formt">
		<div class="input_row">
			<input class="int" type="text" name="id" value="123123123" placeholder="아이디" required maxlength="20" value="${param.id }">
		</div>
		<div class="input_row">
			<input class="int" type="password" name="pw" value="123123123" placeholder="비밀번호" required maxlength="20">
		</div>
		<div class="error">
			<p>${msg }</p>
		</div>
		<div class="input_row">
			<input class="btn btn-success" type="submit" value="로그인">
		</div>
		</form>
		<div class="info sub1">
			<a href="join.go" class="join">회원가입 </a> <span class="txt-bar">|</span>
			<a href="findId.go">아이디찾기 </a> <span class="txt-bar">|</span>
			<a href="findPw.go">비밀번호찾기 </a>
		</div>
	</section>
</div>
<script src="/js/chat.js"></script>