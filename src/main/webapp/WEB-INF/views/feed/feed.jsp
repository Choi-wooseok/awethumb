<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/cssScript.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<!-- 로그인 user 정보 -->
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="u"/>
	</sec:authorize>
	
	<div id="waitme-container" class="waitme-container">
	<section id="feed">
		<div class="feedContWrap">
				<input type="hidden" class="loginUserNo" value="${u.userNo}" />
				<input type="hidden" class="loginUserNickName" value="${u.userNickname}" />
				<input type="hidden" class="loginUserId" value="${u.userId}" />
				<input type="hidden" class="imageState" value="${imageState}" />
				<input type="hidden" class="userFollowMeCount" value="${userFollowMeCount}" />
				<input type="hidden" class="boardCount" value="${boardCount}" />
<%-- 				<input type="hidden" name="postNo" class="postNo" id="postNo" value="${postNoList}"/> --%>
				<c:forEach var="p" items="${postNoList}">
					<input type="hidden" name="postNo" class="postNo" id="postNo" value="${p.postNo}"/>
				</c:forEach>
			<a href="javascript:;" id="mainfeed-scroll" style="display: none;"><span></span></a>
			<div class="feedWrap" id="feedWrap">
				<!-- 리스트 반복 -->
			</div>
				<!-- boardModal -->
				<div id="modalBoard" class="optionModalWrap board">
					<!-- boardModal content -->
					<div>
						<div class="report">글 신고</div>
						<div class="boardShare">퍼가기</div>
						<div class="boardCancel">취 소</div>
					</div>
				</div>
				<!-- comment modal -->
				<div class="optionModalWrap commentboardmodal">
			        <div>
			            <div class="report">댓글 신고</div>
			            <div class="commentDelete">댓글 삭제</div>
			            <div class="commentModify">댓글 수정</div>
			            <div class="modalCancel">취소</div>
			        </div>
			    </div>
			
			<!-- 최신글 리스트 반복 -->
			<div class="feedSide aaa" id="side">
					<div id="followa">나를 팔로우 하는 사람</div>
					<div id="feedSideUserList" class="feedSideUserList">
					</div>
			</div>
			<!-- 주변친구 리스트 반복 -->
			<div class="feedSide2">
				<div>팔로워 추천</div>
				<div class="categoryListSide"></div>
			</div>
		</div>
	</section>
	</div>
	<script src="<c:url value="/js/feed.js" />"></script>
	<script src="<c:url value="/js/like.js" />"></script>


</body>
</html>