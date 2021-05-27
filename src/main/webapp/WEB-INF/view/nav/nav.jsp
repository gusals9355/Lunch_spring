<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ffecec; margin-bottom: 35px;">
  <div class="container-fluid">
    <a class="navbar-brand" href="/ojm"><img src="/img/오점뭐.png"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      </ul>
      <form class="d-flex" action="/user/login">
        <button class="btn btn-outline-success" type="submit">로그인</button>
      </form>
    </div>
  </div>
</nav>