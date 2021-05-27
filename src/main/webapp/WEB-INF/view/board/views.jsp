<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/boot/bootstrap.css"> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<div class="container">
	<div class="row layout">
		<div class="col left_layout"> <!-- 왼쪽 레이아웃 -->
			<div class="imageBlock"> <!-- 사진블럭 -->
				<img src="/upload/${boards.picture}" onerror="this.src='../../../upload/noImage.gif';" width="550" height="300">
				<c:if test="${boards.isFav eq 0 }">
				<a href="/board/heart?no=${param.no }&fav=1"><i class="bi bi-heart" style="color: red"></i></a>
				</c:if>
				<c:if test="${boards.isFav eq 1 }">
				<a href="/board/heart?no=${param.no }&fav=0"><i class="bi bi-heart-fill" style="color: red"></i></a>
				</c:if>
			</div>
			<div class="row"> <!-- 하단블럭 -->
				<p class="msg">${msg }</p>
				<div class="col category-div dropdown"> <!-- 카테고리 -->
					<button class="btn btn-secondary disabled" type="button" id="food">${boards.category }</button>
				</div>
				<div class="col star-div dropdown"><!-- 평점 -->
					<button class="btn btn-secondary disabled" type="button" id="stars">
						<c:forEach begin="1" end="${boards.star }">
							<i class="bi bi-star-fill"></i>
						</c:forEach>
					</button>
				</div>
			</div>
		</div>
		
		<div class="col right_layout" style="position: relative;"> <!-- 오른쪽 레이아웃 (주 폼태그) -->
			<div class="title">
				<h1>${boards.title }</h1>
			</div>
			<div class="content" style="white-space: pre-wrap;">${boards.content }</div>
			<hr>
			<div style="margin-bottom: 30px;">
				<c:forEach var="item" items="${reples }">
					<div class="repleCmt">
						<div class="userInfo">
							<span class="nickname">${item.nickname}</span>
							<span class="date">(${item.reg_dt})</span>
						<c:if test="${userInfo.id == item.id }">
							<button style="width: 55px; height: 30px;" type="button" class="cancel btn btn-outline-secondary" onclick="againCheck('board/delReple?no=${param.no}&repleNo=${item.no}','삭제')">삭제</button>
							<button style="width: 55px; height: 30px;" type="button" class="modify btn btn-outline-info" onclick="goPage('board/modReple?no=${param.no}&repleNo=${item.no}')">수정</button>
						</c:if>
						</div>
						<div class="comment">
							<span class="cmt">${item.reple}</span>
						</div>
						<!-- 댓글 삭제 등록-->
					</div>
				</c:forEach>
				
				<form action="/board/views?no=${param.no }" method="post">
					<div class="col">
						<input type="text" name="reple" maxlength="500" placeholder="댓글입력" size="50">
						<input type="submit" class="btn btn-success" value="등록" style="width: 50px; height: 30px;"></input>
					</div>
				</form>
			</div>
			
			
			<!-- 글 삭제 등록 -->
			<c:if test="${userInfo.id == boards.id }">
				<div class="row" style="position: absolute; bottom: 20px; right: 80px; margin-top: 50px"> <!-- 하단 블럭 -->
					<div class="col"> <!-- 등록 -->
						<button type="button" class="cancel btn btn-secondary" onclick="againCheck('board/delBoard?no=${param.no}','삭제')">삭제</button>
					</div>
					<div class="col">
						<button type="button" class="submit btn btn-info" onclick="goPage('board/modBoard?no=${param.no}')">수정</button>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>