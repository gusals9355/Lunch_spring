<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/ranking.css">
<div class="container">
	<div class="row">
		<div class="mybox col-1">
			순위
		</div>
		<div class="mybox col-3">
			아이디
		</div>
		<div class="mybox col-2">
			등급
		</div>
		<div class="mybox col-2">
			포인트
		</div>
		<div class="mybox col-4">
			가입날짜
		</div>
	</div>
	<div class="row">
	<div class="col-1" id="count"></div>
	<div class="col-3" id="nickName"></div>
	<div class="col-2" id="rank"></div>
	<div class="col-2" id="point"></div>
	<div class="col-4" id="reg_dt"></div>
	</div>
	<c:forEach var="i" items="${rankingList}" varStatus="status">
		<div class="row">
			<div class="mybox col-1">
				${pageNum + status.count}
			</div>
			<div class="mybox col-3">
				${i.nickname }(${i.id })
			</div>
			<div class="mybox col-2">
				${i.ranked }
			</div>
			<div class="mybox col-2">
				${i.point }
			</div>
			<div class="mybox col-4">
				${i.reg_dt }
			</div>
		</div>
	</c:forEach>
	<div class="row">
		<div class="under col">
			<c:forEach var="i" begin="1" end="${maxPage}">
				<c:choose>
					<c:when test="${i eq 1 && (empty param.page) || i eq param.page}">
						<span class="page">${i}</span>
					</c:when>
					<c:otherwise>
						&nbsp;<a href="?page=${i}&select=${param.select}&search=${param.search}"><span>${i}</span></a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div class="under col"> <!--검색창!-->
			<form action="ranking.do" method="get">
				<select name="select">
					<option value="nickname">닉네임
					<option value="id">아이디
				</select>
				<input type="text" name="search" maxlength="10" value="${param.search }">
				<input type="submit" value="검색">
			</form>
		</div>
	</div>
</div>
<script>
/*
	function search(){
		var id = document.querySelector('#id').value;
		var param = {
			id
		}
		ajax(param);
	}
	
	function ajax(param){
		fetch('/ranking', init)
		.then(function(res){
			return res.json();
		}).then(function(myJson){
			console.log(myJson);
			setData(myJson);
		});
	}
	
	function setData(data){
		document.querySelector('#count').innerText = 0;
		document.querySelector('#nickName').innerHTML = data.nickName;
		document.querySelector('#rank').innerText = data.rank;
		document.querySelector('#point').innerHTML = data.point;
		document.querySelector('#reg_dt').innerText = data.reg_dt;
	}
	*/
</script>