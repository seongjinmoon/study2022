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

<%-- <%@ include file="/WEB-INF/jsp/main/inc/Footer.jsp"%> --%>


<div class="dim"></div>
<!-- 로그인 -->
<div class="layer-popup layer-login" style="display:none;">
	<header class="layer-header">
		<span class="logo">
			<span class="img-logo">한국폴리텍대학 대전캠퍼스 스마트소프트웨어학과</span>
		</span>
		<button type="button" class="layer-close"><span>팝업 닫기</span></button>
	</header>
	<div class="layer-body">
		<form action="/login/actionLogin.do" id="frmLogin" name="frmLogin" method="post" onsubmit="return vali()">
			<input type="hidden" name="userSe" value="USR"/>
			<input type="hidden" id="loginType" name="loginType" value=""/>
			<fieldset>
				<legend>로그인을 위한 아이디/비밀번호 입력</legend>
				<div class="ipt-row">
					<input type="text" id="loginId" name="id" placeholder="아이디" required="required">
				</div>
				<div class="ipt-row">
					<input type="password" id="loginPw" name="password" placeholder="비밀번호" required="required">
				</div>
				<button type="submit" class="btn-login"><span>로그인</span></button>
				<br/>
				<a class="btn-kakao" href="#" data-type="login">
  					<img src="http://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="388" alt="카카오 로그인 버튼"/>
				</a>
			</fieldset>
		</form>
	</div>
</div>

<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
$(document).ready(function(){
	//로그인
	$(".login").click(function(){
		$(".dim, .layer-login").fadeIn();
		return false;
	});
	
	//레이어닫기
	$(".layer-close").click(function(){
		$(".dim, .layer-login").fadeOut();
		return false;
	});
	
	//카카오 로그인 버튼
	$(".btn-kakao").click(function(){
		const type = $(this).data("type"); 
		kakaoLogin(type);
		return false;
	});
});

function vali(){
	if(!$("#loginId").val()){
		alert("아이디을 입력해주세요.");
		$("#loginId").focus();
		return false;
	}
	
	if(!$("input[name=loginType]").val()){
		if(!$("#loginPw").val()){
			alert("비밀번호를 입력해주세요.");
			$("#loginPw").focus();
			return false;
		}
	}
}

//카카오 키 정보 입력
Kakao.init('65ffb9ac529a1e24aaf1caf6957548df');

//카카오SDK 초기화
Kakao.isInitialized();

//카카오로그인
function kakaoLogin(type) {
	Kakao.Auth.login({
		success: function (response) {
			Kakao.API.request({
				url: '/v2/user/me',
				success: function (response) {
					console.log(response)
					$("input[name=loginType]").val("KAKAO");
					if(type == "join"){
						$("input[name=emplyrId]").val(response.id);
						$("input[name=userNm]").val(response.properties.nickname);
						
						$("#joinFrm").submit();
					}else{
						$("input[name=id]").val(response.id);
						$("#frmLogin").submit();
					}
				},
          		fail: function (error) {
            		console.log(error)
          		},
        	})
      }, fail: function (error) {
			console.log(error)
      },
    })
}

<c:if test="${not empty message}">
	alert("${message}");
</c:if>

<c:if test="${not empty loginMessage}">
	alert("${loginMessage}");
</c:if>

</script>

</body>
</html>