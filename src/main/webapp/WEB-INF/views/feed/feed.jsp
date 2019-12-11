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
	<div id="waitme-container" class="waitme-container">
	<section id="feed">
		<div class="feedContWrap">
				<input type="hidden" class="loginUserNo" value="${u.userNo}" />
				<input type="hidden" class="loginUserNickName" value="${u.userNickname}" />
				<input type="hidden" class="loginUserId" value="${u.userId}" />
				<input type="hidden" class="imageState" value="${imageState}" />
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
<!-- 			<div class="feedSide" style="z-index : 1; top:10px ; height:85px; background:red;"> -->
<%-- 					<c:if test="${empty meCategory}"> --%>
<!-- 						<h3>카테고리가 없습니다.</h3> -->
<%-- 					</c:if> --%>
<!-- 					<div style="text-align: center;">내 카테고리 </div> -->
<!-- 					<div> -->
<%-- 						<h3 style="word-break: keep-all;">${meCategory}</h3> --%>
<!-- 					</div> -->
<!-- 			</div> -->
			<div class="feedSide aaa" id="side">
					<div>나를 팔로우 하는 사람</div>
<%-- 					<c:if test="${empty followme}"> --%>
<!-- 						<h3>팔로우 하는 사람이 없습니다.</h3> -->
<%-- 					</c:if> --%>
<%-- 					<c:forEach var="fmu" items="${followme}"> --%>
<!-- 						<div class="feedSideUserList"> -->
<!-- 							<div class="feedInfo"> -->
<!-- 								<div class="feedUserImg"> -->
<!-- 									<img src="./../images/test_user.jpg" alt=""> -->
<!-- 								</div> -->
<!-- 								<div class="feedUserName"> -->
<%-- 									<a href="<c:url value="/profile/${fmu.userNickname}"/>">${fmu.userNickname}</a> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<%-- 					</c:forEach> --%>
					<div id="feedSideUserList" class="feedSideUserList">
					</div>
<!-- 					<div class="addBtn" id="addBtn" style="text-align:center;"> -->
<!-- 						<button type="button" style=" border:none; background-color:transparent"> -->
<!-- 							더보기 -->
<!-- 						</button> -->
<!-- 					</div> -->
			</div>
			<!-- 주변친구 리스트 반복 -->
			<div class="feedSide2">
				<div>팔로워 추천</div>
				<div>
					<c:forEach var="cl" items="${categorylist}">
						<div class="feedSideUserList">
							<div class="feedInfo">
								<div class="feedUserImg">
									<img src="./../images/test_user.jpg" alt="">
								</div>
								<div class="feedUserName">
									<a href="<c:url value="/profile/${cl.userNickName}"/>">${cl.userNickName}</a>
									<span style="color:#6dd5bc">${cl.categoryTitle}</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
	</div>
	<script src="<c:url value="/js/feed.js" />"></script>


</body>
</html>