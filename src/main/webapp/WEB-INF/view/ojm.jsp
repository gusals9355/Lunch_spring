<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=05a3bed3cf436895037eb617468dc965&libraries=services"></script>
<div class="container">
	<div class="row"> 
		<div class="col"> <!-- 왼쪽 레이아웃 -->
			좋아요list
		</div>
		<div class="col"> <!-- 중간 레이아웃 -->
			<div class="row">
				<div class="col">
					<div class="search_store"> <!-- 검색블럭 -->
						<form onsubmit="searchPlaces(); return false;">
							<label>매장찾기 <input type="text" value="코리아it아카데미" id="keyword" size="40"></label> 
							<button type="submit" style="display: none;">검색하기</button> 
						</form>
					</div>
				</div>
				<div style="display: flex; align-self: center;" class="col category-div dropdown">
					<button style="width: 120px; height: 40px;" class="btn btn-outline-secondary dropdown-toggle" type="button" id="food" data-bs-toggle="dropdown" aria-expanded="false">
						<span id="asd">전체</span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="food">
						<c:forEach var="type" items="${typelist}">
							<li><button class="dropdown-item" id="${type }" type="button" onclick="setType('${type}')">${type}</button></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="map_wrap"> <!-- 지도블럭 -->
				<div id="map" style="width:500px;height:400px;position:relative;overflow:hidden;"></div>
				<div id="menu_wrap" class="bg_white"> 
					<div class="option"></div>
					<ul id="placesList"></ul>
					<div id="pagination"></div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<button type="button" class="home btn btn-outline-warning" onclick="panTo()">Home</button>
				</div>
				<div class="col">
					<button type="button" class="write btn btn-outline-warning" onclick="location.href='/board/write'">글 등록</button>
				</div>
			</div>
		</div>
		<div class="col"> <!-- 오른쪽 레이아웃 -->
			식사추천 알고리즘
		</div>
	</div>
</div>
<c:forEach var="item" items="${list }">
	<input type="hidden" name="no" value="${item.no}">
	<input type="hidden" name="store" value="${item.store}">
	<input type="hidden" name="mapX" value="${item.mapX}">
	<input type="hidden" name="mapY" value="${item.mapY}">
</c:forEach>
<script>

</script>
<script src="/js/ojm.js"></script>