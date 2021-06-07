<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/boot/bootstrap.css"> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="/css/write.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>
<div class="containerW">
	<div class="row layout">
	
		<div class="col left_layout"> <!-- 왼쪽 레이아웃 -->
			<div class="imageBlock"> <!-- 사진블럭 -->
				<c:forEach var="item" items="#{picture}">
					<img src="/upload/${item}" onerror="this.src='/img/noImage.gif';" width="380" height="300">
				</c:forEach>
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
			<div class="row boardBar">
				<div class="col-md-3">
					<c:out value="${boards.nickname}"/>
				</div>
				<div class="col-md-6">
					<fmt:parseDate value="${boards.reg_dt}" var="reg_dt" pattern="yyyy-MM-dd HH:mm:ss"/>
					<fmt:formatDate value="${reg_dt}" pattern="yyyy-MM-dd HH:mm"/>
				</div>
				<div class="col-md-3">
					조회: <c:out value="${boards.readcount}"/>
				</div>
			</div>
			<div class="title">
				<h1>${boards.title }</h1>
			</div>
			<div class="content" style="white-space: pre-wrap;">${boards.content }</div>
			<hr>
				<c:forEach var="item" items="${reples }">
					<div style="margin-bottom: 30px;">
						<c:choose>
							<c:when test="${item.no == repleNo}">
								<form action="modReple.do?boardno=${boards.no }&no=${param.no}" method="post" class="repletab">
									<textarea rows="2" cols="50" name="reple" maxlength="450" required wrap="hard">${item.reple}</textarea>
									<button type="button" class="cancel btn btn-secondary" style="width: 55px; height: 30px;margin-bottom: 30px" onclick="againCheck('board/views.do?no=${boards.no}','취소')">취소</button>
									<input type="submit" class="modify btn btn-info" value="수정" style="width: 55px; height: 30px;margin-bottom: 30px">
								</form>
							</c:when>
							<c:otherwise>
							<div class="userInfo">
								<span class="nickname">${item.nickname}</span>
								<span class="date">(${item.reg_dt})</span>
								<div class="comment">
									<span class="content">${item.reple}</span>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				<hr>
				</c:forEach>
			</div>
		</div>
	</div>
</div>