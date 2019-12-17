<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

	<c:import url="/WEB-INF/views/include/cssScript.jsp"></c:import>
	
    <!-- webfont -->
    <link href="https://fonts.googleapis.com/css?family=Passion+One&display=swap" rel="stylesheet">
	<!-- viewall -->
    <link href="${pageContext.request.contextPath}/css/invitations/main.css" rel="stylesheet">

</head>
<body>
    <!-- include header -->
    <header>
    	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
    </header>
    <section id="feed" >
		<div class="feedContWrap" >
			<div class="invitationWrap">
				<div class="project-thumb-wrap">
					<img class="project-thumb">
				</div>
				<div class="invitation-meta">
					<div class="user-thumb-wrap">
						<a class="user-link" href="#">
							<img class="user-thumb">
						</a>
					</div>
					<div class="invitation-content">
						<h2 class="invitation-userNick"></h2>
						<span>has invited you to</span>
						<a href="${pageContext.request.contextPath}/detailProject/${sp.projectNo}" class="projectTitle">프로젝트 이름</a>
					</div>
				</div>
				<div class="invitation-btn-wrap">
					<button class="approve">수락</button>
					<button class="disapprove">거절</button>
				</div>
			</div>
		</div>
    </section>
    <script>
    	const sp = {
    			projectNo: ${sp.projectNo},
    			sharedUserNo: ${sp.sharedUserNo},
    			shareCheck: '${sp.shareCheck}',
    			invitationUrl: '${sp.invitationUrl}'
    	};
    	console.log(sp)
    	$.ajax({
    		url: pageContextPath + "/api/project/" + sp.projectNo,
    		dataType: "json"
    	})
    	.done(e => {
    		// 프로젝트 타이틀
    		$(".projectTitle").text(e.projectTitle);
    		getUser(e.userNo);
    		getUserThumb(e.userNo);
    		getProjectThumb(e.projectNo)
    	})
    	
    	// 유저 썸네일 
    	function getUserThumb(userNo){
    		$.ajax({
    			url: pageContextPath + "/api/user/" + userNo + "/thumb",
        	})
        	.done(e => {
        		$(".user-thumb").attr("src", e);
        	})
    	}
    	
    	// 프로젝트 썸네일
    	function getProjectThumb(projectNo){
    		$.ajax({
        		url: pageContextPath + "/api/project/" + sp.projectNo + "/thumb"
        	})
        	.done(e => {
        		$(".project-thumb").attr("src", e);
        	})
    	}
    	
    	// 유저 정보
    	function getUser(userNo){
    		$.ajax({
        		url: pageContextPath + "/api/user/" + userNo,
        		dataType: "json"
        	})
        	.done(e => {
        		$(".invitation-userNick").text(e.userNickname);
        		$(".user-link").attr("href", pageContextPath + "/profile/" + e.userNickname)
        	})
    	}
    	
    	// 수락 버튼 눌렀을 때
    	$(".approve").click(() => {
    		sp.shareCheck = 'Y'
    		$.ajax({
    			url: pageContextPath + "/invitations",
    			type: "PUT",
    			contentType: 'application/json; charset=utf-8',
    			data: JSON.stringify(sp)
    		})
    		.done(() => {
    			location.href = pageContextPath + "/detailProject/" + sp.projectNo;
    		})
    	})
    	// 거절 버튼 클릭시 프로필로 돌아감
    	$(".disapprove").click(() => {
    		$.ajax({
        		url: pageContextPath + "/api/user/" + sp.sharedUserNo,
        		dataType: "json"
        	})
        	.done(e => {
	    		location.href = pageContextPath + "/profile/" + e.userNickname;
        	})
    	})
    </script>
</body>
</html>