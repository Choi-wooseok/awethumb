<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
	<!-- 로그인 user 정보 -->
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="u"/>
	</sec:authorize>
	<c:import url="/WEB-INF/views/include/cssScript.jsp"></c:import>
	<header><c:import url="/WEB-INF/views/include/header.jsp"></c:import></header>
	<div id="waitme-container" class="waitme-container">
	<section id="feed">
		<div class="feedContWrap">
				<input type="hidden" class="loginUserNo" value="${u.userNo}" />
				<input type="hidden" class="loginUserNickName" value="${u.userNickname}" />
				<input type="hidden" class="imageState" value="${imageState}" />
				<c:forEach var="p" items="${postNoList}">
				<input type="hidden" name="postNo" class="postNo" id="postNo" value="${p.postNo}"/>
				</c:forEach>
<%-- 				<c:forEach var="cn" items="${cmtno}"> --%>
<%-- 				<input type="hidden" value="${cn}" class="commentNo"/> --%>
<%-- 				</c:forEach> --%>
			<a href="javascript:;" id="mainfeed-scroll" style="display: none;"><span></span></a>
			<div class="feedWrap" id="feedWrap">
				<!-- 리스트 반복 -->
			</div>
			<!--  modal -->
<%-- 			<div id="modalBoard${bl.postNo}" class="board"> --%>
<!-- 				boardModal content -->
<!-- 				<div class="board-modal"> -->
<!-- 					<div> -->
<!-- 						<button id="report" class="report" type="button"> -->
<!-- 						부적절한 컨텐츠 신고</button> -->
<!-- 					</div> -->
<!-- 				<div> -->
<!-- 					<button id="share">퍼가기</button> -->
<!-- 				</div> -->
<%-- 				<div id="boardCancel "class="boardClose${bl.postNo}">취 소 - 게시글 번호 : ${bl.postNo}</div> --%>
<!-- 			</div> -->
			
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
			<div class="feedSide">
				<div>최신 글 등록</div>
				<div>
					<div class="feedSideUserList">
						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a> <span>3분 전</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 주변친구 리스트 반복 -->
			<div class="feedSide2">
				<div>팔로워 추천</div>
				<div>
					<div class="feedSideUserList">

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a> <span>함께 아는 친구 3명</span>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>
	</div>
	<script src="<c:url value="/js/feed.js" />"></script>


</body>
</html>