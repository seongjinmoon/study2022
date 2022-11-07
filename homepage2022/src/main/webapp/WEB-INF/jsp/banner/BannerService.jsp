<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="/asset/rolling.js"></script>
<script>
$(document).ready(function(){
	var view = "";
	view = $(".slider").find("li").length;
	$(".slider").jCarouselLite({
		visible: view,
		speed: 2000,
		auto : 800,
		playBtn : "#start",
		stopBtn : "#stop",
		btnNext: ".next",
		btnPrev: ".prev",
		mouseWheel: false,
	});
});
</script>

<style>
.rolling{clear:both;}
.slider ul li img{padding: 0 10px;}
.footer{margin-top:30px; color:#ccc;}
.footer a{color:#ccc;}
a { margin: 20px; }
</style>

<div class="rolling">
	<button id="stop">stop</button>
	<button id="start">start</button>
	<button class="prev">&lt;&lt;</button>
	<button class="next">&gt;&gt;</button>
	<div class="slider">
		<ul>
			<c:forEach var="result" items="${resultList}">
				<li><img src="${bannerWebPath}/${result.bannerImageFile}" alt="${result.bannerNm}" style="max-width:150px;"/></li>
			</c:forEach>
	    </ul>
	</div>
</div>
</body>
</html>