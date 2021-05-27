<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/boot/bootstrap.css"> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>
<div class="container">
	<div class="row layout">
	
		<div class="col left_layout"> <!-- 왼쪽 레이아웃 -->
			<div class="picture_wrap"> <!-- 사진블럭 -->
				<img src="../../../upload/${boards.picture}" onerror="this.src='../../../upload/noImage.gif';" width="550" height="300">
			</div>
			<div class="row"> <!-- 하단블럭 -->
				<p class="msg">${msg }</p>
				<div class="col category-div dropdown"> <!-- 카테고리 -->
					<button class="btn btn-secondary disabled" type="button" id="food">${boards.category }</button>
				</div>
				<div class="col star-div dropdown"><!-- 평점 -->
					<button class="btn btn-secondary disabled" type="button" id="stars">${boards.star }</button>
				</div>
			</div>
		</div>
		
		<div class="col right_layout"> <!-- 오른쪽 레이아웃 (주 폼태그) -->
			<div class="title">
				<h1>${boards.title }</h1>
			</div>
			<div class="content" style="white-space: pre-wrap;">${boards.content }</div>
			<hr>
				<c:forEach var="item" items="${reples }">
					<div style="margin-bottom: 30px;">
						<c:choose>
							<c:when test="${item.no == repleNo}">
								<form action="/board/modReple?no=${param.no }&repleNo=${item.no}" method="post">
									<input type="text" name="reple" value="${item.reple }">
									<button type="button" class="cancel btn btn-secondary" onclick="goPage('board/views?no=${param.no}')">취소</button>
									<input type="submit" class="modify btn btn-info" value="수정">
								</form>
							</c:when>
							<c:otherwise>
								${item.nickname } ${item.reple } ${item.star } ${item.reg_dt }
							</c:otherwise>
						</c:choose>
					</div>
					<hr>
				</c:forEach>
			</div>
		</div>
	</div>
</div>