<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

#report {
	color: red;
	border: none;
	background: transparent;
}

#share {
	border: none;
	background: transparent;
}

#myBoard {
	border: none;
	background: transparent;
	float: right;
}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/include/cssScript.jsp"></c:import>
	<header></header>
	<section id="feed">
		<div class="feedContWrap">
			<div class="feedWrap">
				<!-- 리스트 반복 -->
				<div class="feedList">
					<div class="feedInfo">
						<div class="feedUserImg">
							<img src="./../images/test_user.jpg" alt="">
						</div>
						<div>
							<span>userName</span>
							<button type="button" class="myBoard">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>

					이미지 없을 경우 해당 div 삭제
					<div id="feedImgWrap">
						<img id="feedImg" src="./../images/test_img1.jpg" alt="">
						<div class="hoverWrap">
							<div>
								<button class="like">
									<i class="far fa-heart"></i> <span>4</span>
								</button>
							</div>
						</div>
					</div>


					<div class="feedPlay">
						<div>

							댓글 반복
							<div class="commentList">
								<div class="commentUserImg">
									<img src="./../images/test_user.jpg" alt="">
								</div>
								<div class="commentWrap">좋아요 누르고 갑니다.</div>
							</div>

							<div class="commentList">
								<div class="commentUserImg">
									<img src="./../images/test_user.jpg" alt="">
								</div>
								<div class="commentWrap">묻고 더블로 가 !</div>
							</div>

						</div>
						<div class="insertComment">
							<input type="text" />
							<button>등록</button>
						</div>
					</div>
				</div>
				
				<c:forEach var="bl" items="${boardlist}">
				<div class="feedList">
					<div class="feedInfo">
						<div class="feedUserImg">
							<img src="./../images/test_user.jpg" alt="">
						</div>
						<div>
							<span>${bl.userNickName}</span>
							<button type="button" class="myBoard">
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
						</div>
					</div>
					<div class="feedPlay">
						<div>
							<!-- 댓글 반복 -->
							<c:forEach var="c" items="${clist}">
								<div class="commentList">
									<c:if test="${c.postNo eq bl.postNo}">
										<div class="commentList">
											<div class="commentUserImg">
												<img src="./../images/test_user.jpg" alt="">
											</div>
											<div class="commentWrap">${c.cmtContent}
											작성일자 : ${c.cmtRegDt}</div>
										</div>
									</c:if>
								</div>
							</c:forEach>
						</div>
						<div class="insertComment">
							<input type="text" />
							<button>등록</button>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
				<!-- boardModal -->
				<div id="modalBoard" class="board">
					<!-- boardModal content -->
					<div class="board-modal">
						<h4>
							<button id="report" type="button">
								신 고<i class="fas fa-angry"></i>
							</button>
						</h4>
						<h4>
							<a href="bb.html"><button id="share">퍼가기</button></a>
						</h4>
						<h4 class="boardClose">취 소</h4>
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

	<script src="<c:url value="/js/feed.js" />"></script>


</body>
</html>