<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
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
<link rel="stylesheet" href="./../css/mainfeed.css">
<!-- Jquery -->
<script type="text/javascript" src="../js/jquery.js"></script>
<script src="//code.jquery.com/jquery-2.2.2.min.js"></script>
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

</head>
<body>
		<%@ include file="/WEB-INF/views/include/header.jsp"%>
				<div class="waitme-container">
			<section id="feeds">
				<a href="javascript:;" id="mainfeed-scroll" style="display: none;"><span></span></a>
				<div class="feedsWrap msrItems" id="feedsWrap">
					<!-- 반복을 통한 list출력 -->
				</div>
				<div id="detailFeedModal"></div>
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
		</script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/MainFeed.js"></script>
	
</body>
</html>