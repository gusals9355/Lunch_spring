<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="container">
	<div class="mybox">
		<h3>비밀번호 변경</h3>
		<p>주기적으로 비밀번호를 변경하여 계정을 보호하세요.</p>
		<button type="button" class="btn btn-danger" onclick="goPage('user/edit/pw.go')">비밀번호 변경</button>
	</div>
	<div class="mybox">
		<h3>정말 탈퇴하시겠습니까?</h3>
		<p>탈퇴 시 기존의 정보들이 절대 복구 되지 않습니다.</p>
		<button type="button" class="btn btn-danger" onclick="againCheck('user/remove_user.do','탈퇴')">탈퇴하기</button>
	</div>
</div>