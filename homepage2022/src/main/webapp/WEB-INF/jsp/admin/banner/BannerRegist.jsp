<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko" >
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>한국폴리텍 예약관리</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- BBS Style -->
<link href="/asset/BBSTMP_0000000000001/style.css" rel="stylesheet" />
<!-- 공통 Style -->
<link href="/asset/LYTTMP_0000000000000/style.css" rel="stylesheet" />

<!-- jQuery UI -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

</head>
<body>

<c:choose>
	<c:when test="${not empty searchVO.bannerId}">
		<c:set var="actionUrl" value="/admin/banner/update.do"/>
	</c:when>
	<c:otherwise>
		<c:set var="actionUrl" value="/admin/banner/insert.do"/>
	</c:otherwise>
</c:choose>

<%-- 기본 URL --%>
<c:url var="_BASE_PARAM" value="">
  	<c:param name="searchCondition" value="${searchVO.searchCondition}" />
  	<c:if test="${not empty searchVO.searchKeyword}"><c:param name="searchKeyword" value="${searchVO.searchKeyword}" /></c:if>
</c:url>

    
<!-- content 시작 -->
<div id="content">
	<div class="container">
		<div id="contents">
			<form action="${actionUrl}" method="post" id="frm" name="frm" onsubmit="return regist()" enctype="multipart/form-data">
				<input type="hidden" name="bannerId" value="${result.bannerId}"/>
				
				<table class="chart2">
			        <caption>배너정보 작성</caption>
			        <colgroup>
			            <col style="width:200px" />
			            <col />
			        </colgroup>
			        <tbody>
			            <tr>
			                <th scope="row">배너명</th>
			                <td>
			                    <input type="text" id="bannerNm" name="bannerNm" title="제목입력" class="q3" value="<c:out value="${result.bannerNm}"/>"/>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row">배너이미지</th>
			                <td>
			                	<input type="file" name="file"/>
								<c:if test="${not empty searchVO.bannerId}">
									<input type="hidden" name="bannerImage" value="${result.bannerImage}"/>
									<input type="hidden" name="bannerImageFile" value="${result.bannerImageFile}"/>
								</c:if>
			                </td>
			            </tr>
			            <tr>
			            	<th scope="row">게시기간</th>
			                <td>
			                    <input type="text" id="ntceBgnde" class="datepicker" name="ntceBgnde" title="게시시작일" value="<c:out value="${result.ntceBgnde}"/>" readonly="readonly"/>
			                    ~ <input type="text" id="ntceEndde" class="datepicker" name="ntceEndde" title="게시종료일" value="<c:out value="${result.ntceEndde}"/>" readonly="readonly"/>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row">링크URL</th>
			                <td>
			                    <input type="text" id="liknUrl" name="liknUrl" title="링크URL" class="q3" value="<c:out value="${result.liknUrl}"/>"/>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row">새창보기여부</th>
			                <td>
			                    <label>예 : <input type="checkbox" id="popupTrgetY" name="popupTrgetAt" value="Y" checked="checked"/></label>
			                    <label>아니오 : <input type="checkbox" id="popupTrgetN" name="popupTrgetAt" value="N" <c:if test="${result.popupTrgetAt eq 'N'}">checked="checked"</c:if>/></label>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row">내용</th>
			                <td>
			                    <textarea id="bannerDc" name="bannerDc" rows="15" title="내용입력"><c:out value="${result.bannerDc}"/></textarea>
			                </td>
			            </tr>
			        </tbody>
			    </table>
				<div class="btn-cont ar">
				    <c:choose>
						<c:when test="${not empty searchVO.bannerId}">
							<c:url var="uptUrl" value="/admin/banner/update.do${_BASE_PARAM}">
								<c:param name="bannerId" value="${result.bannerId}"/>
							</c:url>
							<a href="${uptUrl}" id="btn-reg" class="btn">수정</a>
							
							<c:url var="delUrl" value="/admin/banner/delete.do${_BASE_PARAM}">
								<c:param name="bannerId" value="${result.bannerId}"/>
							</c:url>
				    		<a href="${delUrl}" id="btn-del" class="btn"><i class="ico-del"></i> 삭제</a>
						</c:when>
						<c:otherwise>
							<a href="#none" id="btn-reg" class="btn spot">등록</a>
						</c:otherwise>
					</c:choose>
					<c:url var="listUrl" value="/admin/banner/selectList.do${_BASE_PARAM}"/>
				    <a href="${listUrl}" class="btn">취소</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- //content 끝 -->
 
<script>
$(document).ready(function(){
	//datepicker
	$(".datepicker").datepicker({
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달', // 이전 버튼 title
        nextText: '다음 달', // 다음 버튼 title
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // 월 표시
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], // 요일 표시
        showMonthAfterYear: true, // 연도 다음에 월 표시 여부
        yearSuffix: '년' // 연도 숫자다음에 나올 글자
	});
	
	//등록
	$("#btn-reg").click(function(){
		$("#frm").submit();
		return false;
	});
	
	//삭제
	$("#btn-del").click(function(){
		if(!confirm("삭제하시겠습니까?")){
			return false;
		}
	});
});

function regist(){
	if(!$("#bannerNm").val()){
		alert("배너명을 입력해주세요.");
		return false;
	}
}
</script>
</body>
</html>

