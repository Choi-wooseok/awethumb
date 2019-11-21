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
<style>
.board {
	display: none;
	/* Hidden by default */
	position: fixed;
	/* Stay in place */
	z-index: 1;
	/* Sit on top */
	left: 0;
	top: 0;
	width: 100%;
	/* Full width */
	height: 100%;
	/* Full height */
	overflow: auto;
	/* Enable scroll if needed */
	background-color: rgb(0, 0, 0);
	/* Fallback color */
	background-color: rgba(0, 0, 0, 0.4);
	/* Black w/ opacity */
}
.commentboard {
	display: none;
	/* Hidden by default */
	position: fixed;
	/* Stay in place */
	z-index: 1;
	/* Sit on top */
	left: 0;
	top: 0;
	width: 100%;
	/* Full width */
	height: 100%;
	/* Full height */
	overflow: auto;
	/* Enable scroll if needed */
	background-color: rgb(0, 0, 0);
	/* Fallback color */
	background-color: rgba(0, 0, 0, 0.4);
	/* Black w/ opacity */
}

/* Modal Content/Box */
.board-modal {
	background-color: #fefefe;
	margin: 15% auto;
	/* 15% from the top and centered */
	padding: 20px;
	border: 3px solid #888;
	width: 20%;
	height: 150px;
	/* Could be more or less, depending on screen size */
}
.comment-modal {
	background-color: #fefefe;
	margin: 15% auto;
	/* 15% from the top and centered */
	padding: 20px;
	border: 3px solid #888;
	width: 20%;
	height: 150px;
	/* Could be more or less, depending on screen size */
}
/* The Close Button */
.boardClose {
	font-weight: bold;
}

.boardClose:hover, .boardClose:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.board-modal {
	line-height: 50px;
}

.board-modal h4 {
	text-align: center;
	border-bottom: 1px solid #e5e5e5;
}
.comment-modal {
	line-height: 50px;
}

.comment-modal h4 {
	text-align: center;
	border-bottom: 1px solid #e5e5e5;
}

#report {
	color: red;
	border: none;
	background: transparent;
}

#share {
	border: none;
	background: transparent;
}

.myBoard {
	border: none;
	background: transparent;
	float: right;
}
#commentModal {
	border: none;
	background: transparent;
	float: right;
}
.commentModalClose {
	font-weight: bold;
}
.commentModalClose:hover, 
.commentModalClose:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}
#commentModal{  
 	display:none;  
} 
#commentList:hover #commentModal{  
	display:block;  
} 


</style>
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
				<input type="hidden" class="loginUserNo" value="3" />
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
					<c:if test="${aa eq 2}">
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
						<div>
							<div id="boardCommentList${bl.postNo}"></div>
						</div>
						<form id="insertComment${bl.postNo}" method="post" action="boardCommentInsert.do">
							<div class="insertComment insertComment2">
								<input class="commentWriter${bl.postNo}" type="text" />
								<button >등록</button>
							</div>
						</form>
					</div>
					<!--  modal -->
					<!-- boardModal -->
					<div id="modalBoard${bl.postNo}" class="board">
						<!-- boardModal content -->
						<div class="board-modal">
							<h4>
								<button id="report" type="button">
									${pn}신 고<i class="fas fa-angry"></i>
								</button>
							</h4>
							<h4>
								<a href="#"><button id="share">퍼가기</button></a>
							</h4>
							<h4 class="boardClose${bl.postNo}">취 소${bl.postNo}</h4>
						</div>
					</div>
					<!-- commentModal -->
<%-- 					<div id="commentBoardAjax${bl.postNo}"></div> --%>
				</div>
				</c:forEach>
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