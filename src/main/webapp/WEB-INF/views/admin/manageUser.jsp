<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/adminInclude.jsp"%>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
	<section id="container">
		<%@ include file="/WEB-INF/views/include/adminHeaderAndSideBar.jsp" %>
		<!-- **********************************************************************************************************************************************************
        MAIN CONTENT 메인 컨텐츠
        *********************************************************************************************************************************************************** -->
		<!-- 메인 컨텐츠 시작 -->
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<!-- 모달창 시작 -->
				<div id="detail-user" class="w3-modal">
					<div class="w3-modal-content w3-card-4 w3-animate-zoom">
						<header class="w3-container w3-teal">
							<span
								onclick="document.getElementById('detail-user').style.display='none'"
								class="w3-button w3-teal w3-xlarge w3-display-topright">&times;</span>
							<h2>회원관리</h2>
						</header>

						<div id="Detail" class="w3-container menu w3-padding-large">
							<span id="unlockAndBlock"></span>
						</div>
						
						<div id="Block" class="w3-container menu w3-padding-large">
							<h3 class="w3-large">수정할 이용정지 기한을 설정해주세요.</h3>
							<form action="updateBlcok.do">
								<input type="date" class="w3-margin-bottom" name="date-selector"><br>
								<button type="button" class="w3-btn w3-blue-grey w3-small"
									id="update-block">기간수정</button>
								<button type="button" class="w3-btn w3-blue-grey w3-small"
									id="cancel-block">정지해제</button>
								<button type="button" class="w3-btn w3-black w3-small"
									id="delete-user">회원삭제</button>
							</form>
						</div>

						<div class="w3-container w3-light-grey w3-padding">
							<button class="w3-button w3-right w3-white w3-border"
								onclick="document.getElementById('detail-user').style.display='none'">닫기</button>
						</div>
					</div>
				</div>
				<!-- Tapped 모달 끝 -->
			
			<style>
			table {text-align: left}
			</style>
				<table class="table table-bordered table-hover mt" id="tbo">
					<thead>
						<tr class="row">
							<th class="col-md-1">회원번호</th>
							<th class="col-md-2">회원아이디</th>
							<th class="col-md-3">정지상태</th>
							<th class="col-md-3">정지기간</th>
							<th class="col-md-3">정보수정 강제탈퇴</th>
						</tr>
					</thead>
				</table>
			
			</section>
		</section>
		<!--main content end-->
		
		<!-- 메인컨텐츠끝 푸터 시작 -->
		<%@ include file="/WEB-INF/views/include/adminFooter.jsp" %>
		<!--footer end-->
	</section>
	<!-- js placed at the end of the document so the pages load faster -->
	<script src="lib/jquery/jquery.min.js"></script>
	<script src="lib/bootstrap/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="lib/jquery.dcjqaccordion.2.7.js"></script>
	<script src="lib/jquery.scrollTo.min.js"></script>
	<script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
	<script src="lib/jquery.sparkline.js"></script>
	<!--common script for all pages-->
	<script src="lib/common-scripts.js"></script>
	<script type="text/javascript" src="lib/gritter/js/jquery.gritter.js"></script>
	<script type="text/javascript" src="lib/gritter-conf.js"></script>
	<!--script for this page-->
	<script src="lib/sparkline-chart.js"></script>
	<script src="lib/zabuto_calendar.js"></script>
	<script type="text/javascript">
  </script>
  	
  	<script src="${pageContext.request.contextPath}/js/manageUser.js"></script>

</body>
</html>