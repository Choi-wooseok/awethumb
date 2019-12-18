<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="kor">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<%@ include file="/WEB-INF/views/include/cssScript.jsp"%>
<!-- favicon -->
<link rel="shortcut icon" href="../images/favicon/favicon.ico">
<!-- Basic Css -->
<link rel="stylesheet" href="./../css/reset.css">
<link rel="stylesheet" type="text/css"
	media="screen and (min-width:1281px)" href="./../css/pc.css">
<!-- <link rel="stylesheet" type="text/css" media="screen and (min-width:768px) and (max-width:1280px)" href="../css/tablet.css"> -->
<!-- <link rel="stylesheet" type="text/css" media="screen and (max-width:767px)" href="../css/mobile.css"> -->
<link rel="stylesheet" href="./../css/common.css">
<!-- Jquery -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<!-- <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script> -->
<!-- web font -->
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<!-- font icon -->

<!-- grid -->
<link rel="stylesheet" href="<c:url value='/js/brick/masonry.css' />">
<link rel="stylesheet" href="<c:url value='/css/hashtag.css' />" />
<link rel="stylesheet" href="./../css/mainfeed.css">
</head>
<style>
	.theSelector {
		overflow: hidden; overflow-wrap: break-word; height: 48px;
	}
</style>
<body>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
		<%@ include file="/WEB-INF/views/include/header.jsp"%>
				<div class="waitme-container">
			<section id="feeds">
				<a href="javascript:;" id="mainfeed-scroll" style="display: none;"><span></span></a>
				
				<!-- 메인피드 생성 -->
				<div class="feedsWrap msrItems" id="feedsWrap">
					<!-- 반복을 통한 list출력 -->
				</div>
				
				<!-- detail창 생성 -->
				<div id="detailFeedModal"></div>
				
				<!-- detail 모달창 -->
				<div id="cmtModalDetail" class="cmtModalDetail">
		            <div class="comment-modal">
		        		<div><button id="report" class="report" type="button">신고</button></div>
		        		<div><button class ="cmtUpdateBtn" type="button">수정</button></div>
		        		<div><button class ="cmtDeleteBtn">삭제</button></div>
		        		<div class="detailModalClose">취 소 </div>
		    		</div>
				</div>
				<div id="unLoginCmtModal" class="unLoginCmtModal">
		            <div class="comment-modal">
		        		<div><button id="report" class="report" type="button">신고</button></div>
		        		<div class="detailModalClose">취 소 </div>
		    		</div>
				</div>
				
				<div class="updateComment">
					<input type="text" id="contentUpdate"/>
					<div>
						<button id="upBtn" class="updateSubmit">수정</button>
						<button id="upBtn" class="updateCancel">취소</button>
					</div>
				</div>
				<div class="deleteComment">
					<div>
						<button id="delBtn" class="deleteSubmit">삭제</button>
						<button id="delBtn" class="deleteCancel">취소</button>
					</div>
				</div>
				<div id="modalBoard" class="board">
		        	<div class="board-modal">
			            <div> <button id="report" class="report" type="button">신 고</button></div>
			            <div> <button id="share">퍼가기</button></div>
		            	<div class="boardClose">취 소 </div>
			        </div>
			    </div>
				<div id="LoginModalBoard" class="LoginModalBoard">
		        	<div class="board-modal">
			            <div> <button id="updateBtn" class="updateBtn" type="button">수정 / 삭제</button></div>
		            	<div class="LoginBoardClose">취 소 </div>
			        </div>
			    </div>
			    
			</section>	
				</div>	
		<script src="<c:url value='/js/brick/masonry.js' />"></script>
		<script>
			let pageContextURI = '${pageContext.request.contextPath}';
			
			   //update columns size on window resize
				$(window).on('resize', function(e) {
					time = setTimeout(function() {
						$('.msrItems').msrItems('refresh');
					}, 200);
					clearTimeout(time);
				})
			let hashtag = '${hashtag}'
			console.log("hash", hashtag);
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/mainfeedjs.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hashtag/jquery.hashtags.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/hashtag/autosize.min.js"></script>
</body>
</html>