<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<section class="row">
			<header class="content-title">
				<h1>아이디 찾기</h1>
				<p>이메일 확인으로 아이디를 찾을 수 있습니다.</p>
			</header>
			<form action="findId.go" method="post" class="row formt">
			<div class="input_row">
				<input class="int" type="email" name="email" id="email" value="${param.email }" required autofocus placeholder="이메일을 입력해주세요"><br>
			</div>
			<div class="error">
				<p>${msg }
			</div>
			<%-- 아이디 개수만큼 아이디를 출력!--%>
			<c:forEach var="_id" items="${idList }">
				<div class="input_row">
					<label for="_id">아이디</label>
					<p id="_id">${_id}</p>
				</div>
			</c:forEach>
			<div class="submits">
				<button type="button" class="cancel btn btn-outline-secondary" onclick="againCheck('ojm','취소')">취소</button>
				<input class="sub btn btn-outline-success" type="submit" value="찾기">
			</div>
		</form>
	</section>
</div>