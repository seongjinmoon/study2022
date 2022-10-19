<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><c:out value='${result.popupTitleNm}'/></title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="/asset/cookie.js"></script>

<script>
//체크버튼 클릭시
$(document).ready(function(){
	$("#chkPopup").change(function(){
		if($(this).is(":checked")){
			$.cookie("${result.popupId}","done", {expires:1,path : '/'});
		}else{
			$.removeCookie("${result.popupId}", {expires:1,path : '/'});
		}
	});
});
function fnPopupCheck() {

	var chk = document.getElementById("chkPopup");
	if(chk && chk.checked) {
		$.cookie("${result.popupId}","done", {expires:1,path : '/'});
	}
	window.close();
}

//alert($.cookie("${result.popupId}"));
</script>
<style> 
	html{height:100%;padding:0;margin:0;}
	body{width:100%;height:100%;overflow:auto;padding:0;margin:0;}
	#popmainboxdn { position:fixed;bottom:0; clear:both;  width:100%; height:30px; padding:0;background-color:#747474; text-align:right; line-height:30px;}
	#popmainboxdn label { margin-right:20px;  padding-top:4px; font:normal 12px/14px Dotum,"돋움"; color:#d8d8d8; }
	#popmainboxdn input { border:0; margin-right:5px; vertical-align:middle; }
</style>
</head>
<body>
	<div id="popmainboxcon">
		<div id="popmainboxcon2">
			<div id="popmainboxcontxt">
				<c:out value="${result.popupCn}" escapeXml="false" />
			</div>
		</div>
	</div>
	
	<div id="popmainboxdn">
		<label for="chkPopup"><input type="checkbox" name="chkPopup" id="chkPopup"/>24시간동안 이 창 열지 않기</label>
		<a href="#" class="btn_close_pop" onclick="fnPopupCheck()" title="팝업창닫기"><img src="/asset/LYTTMP_0000000000000/images/common/popbox_close.gif" alt="닫기" /></a>
	</div>
</body>
</html>