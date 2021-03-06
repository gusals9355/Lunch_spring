<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ffecec; margin-bottom: 35px;">
  <div class="container-fluid">
    <a class="navbar-brand" href="/ojm"><img src="/img/오점뭐.png"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav ms-auto navbar-nav-scroll" style="--bs-scroll-height: 100px; margin: 0 0 0 416px;">
          <li><button type="button" class="btn btn-outline-secondary" onclick="goPage('user/edit/nickname.do')"><c:out value="${userInfo.nickname}님"/></button></li>
          <li><button type="button" class="btn btn-outline-secondary"><c:out value="${userInfo.ranked }"/></button></li>
          <li><button type="button" class="btn btn-outline-secondary" onclick="goPage('user/ranking.do')">랭킹</button></li>
          <li><button type="button" class="btn btn-outline-secondary" onclick="goPage('user/pw_check.do')">내정보</button></li>
          <li><button type="button" class="btn btn-outline-secondary"><fmt:formatNumber groupingUsed="true" value="${userInfo.point}"/>xp </button></li>
      </ul>
<%--  		<div class="btn-group" role="group" aria-label="First group">--%>
<%--		    <button type="button" class="btn btn-outline-secondary" onclick="goPage('user/edit/nickname.do')"><c:out value="${userInfo.nickname}님"/></button>--%>
<%--		    <button type="button" class="btn btn-outline-secondary"><c:out value="${userInfo.ranked }"/></button>--%>
<%--		    <button type="button" class="btn btn-outline-secondary" onclick="goPage('user/ranking.do')">랭킹</button>--%>
<%--		    <button type="button" class="btn btn-outline-secondary" onclick="goPage('user/pw_check.do')">내정보</button>--%>
<%--		    <button type="button" class="btn btn-outline-secondary"><fmt:formatNumber groupingUsed="true" value="${userInfo.point}"/>xp </button>--%>
<%--  		</div>--%>
      <form action="/user/logout.do">
        <button class="btn btn-outline-success" type="submit">로그아웃</button>
      </form>
    </div>
  </div>
</nav>