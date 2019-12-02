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
			<div class="feedWrap">
				<c:forEach var="cn" items="${cmtno}">
				<input type="hidden" value="${cn}" class="commentNo"/>
				</c:forEach>
				<!-- 리스트 반복 -->
				<c:forEach var="bl" items="${boardlist}">
				<input type="hidden" class="loginUserNo" value="1" />
				<div class="feedList">
					<div class="feedInfo">
						<div class="feedUserImg">
							<img src="./../images/test_user.jpg" alt="">
						</div>
						<div>
							<span>${bl.userNickName}</span>
							<button type="button" class="myBoard${bl.postNo}">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>
					<c:if test="${aa eq 1}">
					<div id="feedImgWrap">
						<img id="feedImg" src="./../images/test_img1.jpg" alt="">
						<div class="hoverWrap">
							<div>
								<button class="like">
									<i class="far fa-heart"></i><span>4</span>
								</button>
							</div>
						</div>
					</div>
					</c:if>
					<!--  이미지없고 텍스트만 -->
					<div class="feedText">
						<div>
							게시판 내용 : ${bl.postContent}
							게시판 번호 : ${bl.postNo}
						</div>
					</div>
					<!-- 댓글 -->
					<input type="hidden" name="postNo" class="postNo" id="postNo" value="${bl.postNo}" />
					<div class="feedPlay">
						<div id="commentList">
							<div id="boardCommentList${bl.postNo}"></div>
						</div>
<%-- 						<form id="insertComment${bl.postNo}" method="post" action="boardCommentInsert.do"> --%>
<!-- 							<div class="insertComment insertComment2"> -->
<%-- 								<input id="commentWriter" class="commentWriter${bl.postNo}" type="text" /> --%>
<!-- 								<button id="commentInsertBtn">등록</button> -->
<!-- 							</div> -->
<!-- 						</form> -->
							<div class="insertComment">
								<input id="commentWriter" class="commentWriter${bl.postNo}" type="text" />
								<button type="button" class="commentInsertBtn" data-postNumber="${bl.postNo}">등록</button>
							</div>
					</div>
					<!--  modal -->
					<!-- boardModal -->
					<div id="modalBoard${bl.postNo}" class="board">
						<!-- boardModal content -->
						<div class="board-modal">
							<div>
								<button id="report" class="report" type="button">
									부적절한 컨텐츠 신고								</button>
							</div>
							<div>
								<button id="share">퍼가기</button>
							</div>
							<div id="boardCancel "class="boardClose${bl.postNo}">취 소 - 게시글 번호 : ${bl.postNo}</div>
						</div>
					</div>
				    
				</div>
				</c:forEach>
			</div>
				<!-- commentModal -->
				<div class="optionModalWrap commentboardmodal">
			        <div>
			            <div class="delete">글 삭제</div>
			            <div class="modify">글 수정</div>
			            <div class="updatecancel">취소</div>
			        </div>
			    </div>
			
			<div class="feedSide">
				<div>최신 글 등록</div>
				<div>
					<div class="feedSideUserList">
						<!-- 리스트 반복 -->
						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a> <span>3분 전</span>
							</div>
						</div>

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a>
							</div>
						</div>

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a>
							</div>
						</div>

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a>
							</div>
						</div>

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a>
							</div>
						</div>

					</div>
				</div>
			</div>
			<!-- 리스트 반복 -->
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

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a>
							</div>
						</div>

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a>
							</div>
						</div>

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a>
							</div>
						</div>

						<div class="feedInfo">
							<div class="feedUserImg">
								<img src="./../images/test_user.jpg" alt="">
							</div>
							<div class="feedUserName">
								<a href="#">userName</a>
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