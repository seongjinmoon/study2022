<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>수업용 게시판</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
nav{
	margin:100px;
}
.member-type{
	display:inline-block;
	width:150px;
	height:150px;
	text-align:center;
	line-height:150px;
	border:1px solid #000;
}
</style>
</head>
<body>

<%@ include file="/WEB-INF/jsp/main/inc/Header.jsp"%>

<div class="wrap">
	<nav>
		<a href="/join/memberRegist.do?loginType=normal" class="member-type">일반 회원가입</a>
		<a class="btn-kakao" href="#" data-type="join">
			<img src="http://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="150" alt="카카오 로그인 버튼"/>
		</a>
		
		<a class="btn-naver" href="${naverAuthUrl}" data-type="join">
			<img src="/asset/front/images/common/btn-naver.png" width="150" alt="네이버 로그인 버튼"/>
		</a>
	</nav>
</div>

<form id="joinFrm" name="joinFrm" method="post" action="/join/insertMember.do">
	<input type="hidden" name="loginType" value=""/>
	<input type="hidden" name="emplyrId"/>
	<input type="hidden" name="userNm"/>
</form>

<%@ include file="/WEB-INF/jsp/main/inc/Footer.jsp"%>

</body>
</html>