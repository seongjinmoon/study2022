<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:choose>
	<c:when test="${not empty searchVO.crudId}">
		<c:set var="actionUrl" value="/crud2/update.do"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="/crud2/insert.do"/>
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<title>CRUD</title>
</head>
<body>
* 등록폼
<form action="${actionUrl}" method="post" name="tempVO">
	<input type="hidden" name="crudId" value="${result.crudId}"/>
	<label for="crudSj">제목</label> 
	<input type="text" id="crudSj" name="crudSj" value="${result.crudSj}" placeholder="제목을 입력하세요."/>
	<br/>
	<label for="userNm">작성자</label> 
	<input type="text" id="userNm" name="userNm" value="${result.userNm}" placeholder="작성자를 입력하세요."/>
	<br/>
	<label for="crudCn">내용</label> 
	<textarea id="crudCn" name="crudCn" placeholder="내용을 입력하세요."><c:out value="${result.crudCn}"/></textarea>
	<br/>
	<c:choose>
		<c:when test="${not empty searchVO.crudId}">
			<button type="submit">수정</button>
		</c:when>
		<c:otherwise>
			<button type="submit">등록</button>
		</c:otherwise>
	</c:choose>
	<a href="/crud2/selectList.do">목록</a>
</form>
</body>
</html>

