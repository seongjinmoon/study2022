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
<title>나만의 포트폴리오</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

	<%@ include file="/WEB-INF/jsp/main/inc/Header.jsp"%>
	
	<!-- site-body -->
	<div class="site-body">
		<div class="wrap">
			<div class="slides">
				<div class="slide" style="display:none;">
					<strong><span class="month">4</span>월호</strong>
					폴리텍대전캠퍼스 스마트소프트웨어과
					<p>함께하는 소식과<br/>다양한 활동을 소개합니다</p>
				</div>
				<div class="slide" style="display:none;">
					<strong><span class="month">5</span>월호</strong>
					폴리텍대전캠퍼스 스마트소프트웨어과
					<p>함께하는 소식과<br/>다양한 활동을 소개합니다</p>
				</div>
				<div class="slide active">
					<strong><span class="month">6</span>월호</strong>
					폴리텍대전캠퍼스 스마트소프트웨어과
					<p>함께하는 소식과<br/>다양한 활동을 소개합니다</p>
				</div>
			</div>
		</div>
		<button class="btn-prev">이전</button>
		<button class="btn-next">다음</button>
	</div>
	<!-- //site-body -->
	
	<%@ include file="/WEB-INF/jsp/main/inc/Footer.jsp"%>
	<%--배너 --%>
	<c:import url="/banner/bannerService.do" charEncoding="utf-8"/>
	
	<%--팝업 --%>
	<c:import url="/popup/popupService.do" charEncoding="utf-8"/>

<script>
$(document).ready(function(){
	
});
</script>
</body>
</html>

