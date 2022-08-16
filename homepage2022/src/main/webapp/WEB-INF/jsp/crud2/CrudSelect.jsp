<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<title>CRUD</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>

</style>
</head>
<body>

<table>
	<tbody>
		<tr>
			<th>제목</th>
			<td><c:out value="${result.crudSj}"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><c:out value="${result.userNm}"/></td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><fmt:formatDate value="${result.crudId}" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><c:out value="${result.crudCn}"/></td>
		</tr>
	</tbody>
</table>

<div class="box-btn">
	<c:url var="uptUrl" value="/crud2/crudRegist.do">
		<c:param name="crudId" value="${result.crudId}"/>
	</c:url>
	<a href="${uptUrl}">수정</a>
	
	<c:url var="delUrl" value="/crud2/delete.do">
		<c:param name="crudId" value="${result.crudId}"/>
	</c:url>
	<a href="${delUrl}" class="btn-del">삭제</a>
	
	<a href="/crud2/selectList.do">목록</a>
</div>
<script>
$(document).ready(function(){
	$(".btn-del").click(function(){
		if(!confirm("삭제하시겠습니까?")){
			return false;
		}
	});
});
</script>
</body>
</html>

