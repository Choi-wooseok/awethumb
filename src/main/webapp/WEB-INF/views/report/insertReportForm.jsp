<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<title>신고하기</title>
	<%@ include file="/WEB-INF/views/include/cssScript.jsp" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

  <style>
  	body {
			background-image: url( "${pageContext.request.contextPath}/images/main_bg.jpg");
			background-position: center;
     		background-size: cover;
/*   		background-image: url("./../images/main_bg.jpg"); */
  		
  	}
  </style>
</head>
<body class="w3-container w3-auto" style="width:700px;">
	<!-- 이걸 해줌으로써 su.userName이런식으로 현재 로그인한 사용자의 정보를 받아올수 있게 된다. -->
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
	<div class="w3-black w3-opacity-min w3-text-white">
		<form action="<c:url value='/report/insertReport.do'/>" method="post" name="submit-form" class="w3-panel w3-card-4" onsubmit='return submitForm();'>
			<h2>신고하기</h2>
			<div id="userId">신고대상 회원 아이디 : </div>
			<div id="userNickName">신고대상 회원 닉네임 : </div>
			<div>
			<select class="w3-select w3-section w3-text-black" name="blockCode" >
			    <option value="" disabled selected>신고 사유를 선택해주세요.</option>
			    <option value="1">욕설 및 비방</option>
			    <option value="2">음란물</option>
			    <option value="3">스팸</option>
			    <option value="4">사행성</option>
			    <option value="5">기타</option>
			</select>
			</div>
			<input type="text" class="w3-input w3-text-black" placeholder="기타를 선택하신분은 사유를 적어주세요." id="reason" name="reportReason">
			<input type="hidden" name="reportUserNo" value="${su.userNo}">
			<button class="w3-btn w3-blue w3-margin-top w3-margin-bottom" id="report-button" >신고하기</button>
			<br>
		</form>
	</div>
	
<script>
	/* 기본 디스에이블드, 셀렉트변화시 기타일경우에 풀어주고, 다시 다른걸로 변하면 잠궈준다. */

		
		/*
		겟방식으로 이 페이지에 들어왔을때, commentNo가 있으면 댓글이 신고된 것이고, postNo만 있으면 게시글이 신고된 것이다.
		commentNo가 null이 아닐경우와 null일 경우에 따라, ajax로 바로 게시글이나 댓글의 원본을 추적해서 화면에 보여준다.
		*/
		let postNo = "";
		let commentNo = "";
		postNo = <%= request.getParameter("postNo") %>;
		commentNo = <%= request.getParameter("commentNo") %>;
		console.log(commentNo);
</script>
  <script src="${pageContext.request.contextPath}/js/report/insertreport.js"></script>
</body>
</html>