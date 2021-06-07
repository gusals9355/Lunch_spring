<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<section class="row">
		<header class="content-title" style="padding: 20px;">
			<h1>관리자 등록</h1>
			<p>코드를 입력해주세요.</p>
		</header>
		<form action="regi_manager.do" class="row formt" method="post" onsubmit="return verify();">
			<div class="input_row">
				<input class="int" type="text" name="code" id="code" maxlength="20" placeholder="Enter Code">
			</div>
			<div class="error">
				<p id="errorp">${msg }</p>
			</div>
			<div class="input_row">
				<input class="btn btn-success" type="submit" value="확인">
			</div>
		</form>
	</section>
</div>
<script src="/js/register_manager.js"></script>