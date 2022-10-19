<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<td class="tit"><c:out value="${result.popupTitleNm}"/></td>
		<td>
			<c:choose>
				<c:when test="${result.sysTyCode eq 'TYPE1'}">일반팝업</c:when>
				<c:when test="${result.sysTyCode eq 'TYPE2'}">레이어팝업</c:when>
				<c:otherwise>-</c:otherwise>
			</c:choose>
		</td>
		<td>
			<c:out value="${result.ntceBgnde}"/>~<br/>
			<c:out value="${result.ntceEndde}"/>
		</td>
	            <td>
	            		<c:url var="previewUrl" value="/popup/select.do">
				<c:param name="popupId" value="${result.popupId}"/>
			</c:url>
	            		<a href="${previewUrl}" class="btn spot btn-preview" data-title="${result.popupTitleNm}" data-hlc="${result.popupHlc}" data-wlc="${result.popupWlc}" data-hsize="${result.popupHsize}" data-wsize="${result.popupWsize}">미리보기</a>
	            </td>
	            <td>
	            		<c:url var="updateUrl" value="/admin/popup/regist.do${_BASE_PARAM}">
				<c:param name="popupId" value="${result.popupId}"/>
				<c:param name="pageIndex" value="${searchVO.pageIndex}" />
			</c:url>
			<a href="${updateUrl}" class="btn spot">수정</a>
			<br/><br/>
			<c:url var="deleteUrl" value="/admin/popup/delete.do${_BASE_PARAM}">
				<c:param name="popupId" value="${result.popupId}"/>
				<c:param name="pageIndex" value="${searchVO.pageIndex}" />
			</c:url>
			<a href="${deleteUrl}" class="btn spot btn-del">삭제</a>
		</td>
	</tr>
</c:forEach>

<c:forEach var="result" items="${resultList}">
	if
	window.open(href, title, "top=" + hlc + ", left=" + wlc + ", width=" + wsize + ", height=" + hsize);
</c:forEach>
	
<script>
$(document).ready(function(){
	
});
//미리보기
$(".btn-preview").click(function(){
	var href = $(this).attr("href"),
		title = $(this).data("title"),
		hlc = $(this).data("hlc"),
		wlc = $(this).data("wlc"),
		hsize = $(this).data("hsize"),
		wsize = $(this).data("wsize");
	
	//팝업오픈
	window.open(href, title, "top=" + hlc + ", left=" + wlc + ", width=" + wsize + ", height=" + hsize);
	return false;
});

</script>

</body>
</html>