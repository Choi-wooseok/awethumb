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
					<img class="project-thumb" src="/awethumb/images/test_user.jpg">
				</div>
				<div class="invitation-meta">
					<div class="user-thumb-wrap">
						<a href="#">
							<img class="user-thumb" src="/awethumb/images/test_user.jpg">
						</a>
					</div>
					<div class="invitation-content">
						<h2 class="invitation-userNick">jae</h2>
						<span>has invited you to</span>
						<a href="#">프로젝트 이름</a>
					</div>
				</div>
				<div class="invitation-btn-wrap">
					<button>수락</button>
					<button>거절</button>
				</div>
			</div>
		</div>
    </section>
    <script>
    </script>
</body>
</html>