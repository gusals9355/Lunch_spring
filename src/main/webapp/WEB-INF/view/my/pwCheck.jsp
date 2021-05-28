<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="container">
	<section class="content">
	<header class="content-title">
		<h3>비밀번호 확인</h3>
		<p>회원님의 정보를 안전하게 보호하기 위해 비밀번호를 한번 더 확인합니다.</p>
	</header>
		<div>
			${userInfo.nickname}님
			회원 | ${userInfo.ranked} (${userInfo.point }xp)
		</div>
		<form action="/user/pw_check.do" method="post">
			<div class="input_row">
				<input class="int" type="text" name="id" value="${userInfo.id }" readonly>
			</div>
			<div class="input_row">
				<input class="int" type="password" name="pw" autofocus required placeholder="비밀번호">
			</div>
			<div class="error">
				<p>${msg }</p>
			</div>
			<div class="input_row">
				<input type="submit" class="btn btn-danger" value="비밀번호 확인">
			</div>
		</form>
	</section>
</div>