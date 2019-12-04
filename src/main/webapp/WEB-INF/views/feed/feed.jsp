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
	<c:import url="/WEB-INF/views/include/cssScript.jsp"></c:import>
	<header><c:import url="/WEB-INF/views/include/header.jsp"></c:import></header>
	<div id="waitme-container" class="waitme-container">
	<section id="feed">
		<div class="feedContWrap">
				<input type="hidden" class="loginUserNo" value="2" />
				<input type="hidden" class="aa" value="${aa}" />
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
				<!-- comment modal -->
				<div class="optionModalWrap commentboardmodal">
			        <div>
			            <div class="commentDelete">글 삭제</div>
			            <div class="commentModify">글 수정</div>
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
				<div>내 주변 친구들</div>
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