<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="http://localhost:8080/css/boot/bootstrap.css"> -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<link rel="stylesheet" href="css/write.css">
<title>${board.title }</title>
</head>
<body>
<jsp:include page="../nav/${str }" flush="false"/>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>

<div class="container">
	<div class="row layout">
		<div class="col left_layout"> <!-- 왼쪽 레이아웃 -->
			<div class="search_store"> <!-- 검색블럭 -->
				<form onsubmit="searchPlaces(); return false;">
					<label>매장찾기 : <input type="text" value="${board.store }" id="keyword" size="40"></label> 
					<button type="submit" style="display: none;">검색하기</button> 
				</form>
			</div>
			
			<div class="map_wrap"> <!-- 지도블럭 -->
				<div id="map" style="height:400px;position:relative;overflow:hidden;"></div>
				<div id="menu_wrap" class="bg_white"> 
					<div class="option"></div>
					<ul id="placesList"></ul>
					<div id="pagination"></div>
				</div>
			</div>
			<div class="row"> <!-- 하단블럭 -->
				<p class="msg">${msg }</p>
				<div class="col category-div dropdown"> <!-- 카테고리 -->
					<button class="btn btn-secondary dropdown-toggle" type="button" id="food" data-bs-toggle="dropdown" aria-expanded="false">
						<span id="asd">${board.category }</span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="food">
						<c:forEach var="type" items="${typelist}">
							<li><button class="dropdown-item" type="button" onclick="setType('${type}')">${type}</button></li>
						</c:forEach>
					</ul>
				</div>
				<div class="col star-div dropdown"><!-- 평점 -->
					<button class="btn btn-secondary dropdown-toggle" type="button" id="stars" data-bs-toggle="dropdown" aria-expanded="false">
						<span id="zxc">
							<c:forEach begin="1" end="${board.star }">
								<i class="bi bi-star-fill" ></i>
							</c:forEach>
						</span>
						<c:forEach var="i" begin="1" end="5"><i id="star${i}"></i></c:forEach>
					</button>
					<ul class="dropdown-menu" aria-labelledby="stars">
						<c:forEach var="i" begin="1" end="5">
							<li><button class="dropdown-item" type="button" onclick="setStar(${i})">
								<%-- i개만큼 꽉찬 별 찍기 --%>
								<c:forEach var="j" begin="1" end="${i}"> 
									<i class="bi bi-star-fill"></i>
								</c:forEach>
								<%-- 5-i개만큼 빈별 찍기 --%>
								<c:forEach var="j" begin="${i+1}" end="5">
									<i class="bi bi-star"></i>
								</c:forEach>
							</button></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="col right_layout"> <!-- 오른쪽 레이아웃 (주 폼태그) -->
			<form action="/board/modBoard" method="post" enctype="multipart/form-data"> <!-- get방식 전송불가 -->
				<input type="hidden" name="no" value="${param.no }">
				<input type="hidden" name="store" id="placeName" value="${board.store}">
				<input type="hidden" name="lat" id="lat" value="${board.mapX }">
				<input type="hidden" name="lng" id="lng" value="${board.mapY }">
				<input type="hidden" name="star" id="star" value="${board.star }">
				<input type="hidden" name="category" id="foodType" value="${board.category }">
				<!--<c:if test="${userInfo eq null }">
					<div class="row"> 
						<div class="col">
							<input type="text" name="id" value="${anonymous }" placeholder="아이디" maxlength="20" required>
						</div>
						<div class="col">
							<input type="password" name="pw" placeholder="비밀번호" maxlength="20" required>
						</div>
					</div>
				</c:if>!-->
				<div>
					<input type="text" name="title" maxlength="30" placeholder="제목" size="60" value="${board.title }" autofocus required>
				</div>
				<div class="danger-log"> <!-- danger log -->
					<p><strong>※ 무분별한 게시글은 관리자에 의해 삭제될 수 있습니다.</strong></p>
				</div>
				<div>
					<textarea rows="10" cols="70" name="content" maxlength="1000" required>${board.content }</textarea>
				</div>
				<div class="row"> <!-- 하단 블럭 -->
					<div class="col select_file"> <!-- 파일업로드 -->
						<label class="input-file-button" for="input-file">파일선택</label>
						<input type="file" class="input" id="input-file" name="file" accept="image/*" style="display: none;">
						<div class="preview"> <!-- 썸네일 -->
							<p></p>
						</div>
					</div>
					<div class="col"> <!-- 등록 -->
						<button type="button" class="cancel btn btn-secondary" onclick="againCheck('ojm','취소')">취소</button>
					</div>
					<div class="col">
						<input type="submit" class="submit btn btn-success" value="수정">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="/js/write.js"></script>
<script src="/js/kakao.js"></script>
</body>
</html>