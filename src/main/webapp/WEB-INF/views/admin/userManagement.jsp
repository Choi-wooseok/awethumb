<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/adminInclude.jsp"%>
<meta charset="UTF-8">
<title>불량회원 신고현황</title>
<style>
.menu {
	display: none;
}
</style>
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
									id="deny-block">승인거절</button>
								<button type="button" class="w3-btn w3-black w3-small"
									id="delete-report">삭제하기</button>
							</form>
						</div>

						<div class="w3-container w3-light-grey w3-padding">
							<button class="w3-button w3-right w3-white w3-border"
								onclick="document.getElementById('detail-user').style.display='none'">닫기</button>
						</div>
					</div>
				</div>
				<!-- Tapped 모달 끝 -->
			
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
	<script type="application/javascript">
		
    $(document).ready(function() {
      $("#date-popover").popover({
        html: true,
        trigger: "manual"
      });
      $("#date-popover").hide();
      $("#date-popover").click(function(e) {
        $(this).hide();
      });

      $("#my-calendar").zabuto_calendar({
        action: function() {
          return myDateFunction(this.id, false);
        },
        action_nav: function() {
          return myNavFunction(this.id);
        },
        ajax: {
          url: "show_data.php?action=1",
          modal: true
        },
        legend: [{
            type: "text",
            label: "Special event",
            badge: "00"
          },
          {
            type: "block",
            label: "Regular event",
          }
        ]
      });
    });

    function myNavFunction(id) {
      $("#date-popover").hide();
      var nav = $("#" + id).data("navigation");
      var to = $("#" + id).data("to");
      console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }
	
	</script>

</body>
</html>