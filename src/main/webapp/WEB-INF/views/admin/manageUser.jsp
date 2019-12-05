<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/adminInclude.jsp"%>
<meta charset="UTF-8">
<title>회원관리<title>
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
				<!-- Tapped 모달창 시작 -->
				<div id="detail-block" class="w3-modal">
					<div class="w3-modal-content w3-card-4 w3-animate-zoom">
						<header class="w3-container w3-teal">
							<span
								onclick="document.getElementById('detail-block').style.display='none'"
								class="w3-button w3-teal w3-xlarge w3-display-topright">&times;</span>
							<h2>상세보기 & 회원정지</h2>
						</header>

						<div class="w3-bar w3-border-bottom">
							<button class="tablink w3-bar-item w3-button"
								onclick="openMenu(event, 'Detail')">상세글보기</button>
							<button class="tablink w3-bar-item w3-button"
								onclick="openMenu(event, 'Block')">이용정지</button>
						</div>

						<div id="Detail" class="w3-container menu w3-padding-large">

						</div>

						<div id="Block" class="w3-container menu w3-padding-large">
							<h3 class="w3-large">이용정지 기한을 설정해주세요.</h3>
							<form action="blockUser.do">
								<input type="date" class="w3-margin-bottom" name="date-selector"><br>
								<span id="permit-span"></span>
								<button type="button" class="w3-btn w3-blue-grey w3-small"
									id="deny-block">승인거절</button>
								<button type="button" class="w3-btn w3-black w3-small"
									id="delete-report">삭제하기</button>
							</form>
						</div>

						<div class="w3-container w3-light-grey w3-padding">
							<button class="w3-button w3-right w3-white w3-border"
								onclick="document.getElementById('detail-block').style.display='none'">닫기</button>
						</div>
					</div>
				</div>
				<!-- Tapped 모달 끝 -->

				<table class="table table-bordered table-hover mt" id="tbo">
					<thead>
						<tr class="row col-md-9">
							<th class="col-md-3">회원번호</th>
							<th class="col-md-3">회원아이디</th>
							<th class="col-md-3">정지상태</th>
							<th class="col-md-3">정지기간</th>
						</tr>
						<tr class="row col-md-3">
							<th class="col-md-6">정보수정</th>
							<th class="col-md-6">강제탈퇴</th>
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
  
	<script>
  /* 정지,취소,승인등을 처리하기 위해 전역변수 설정 */
  let userNo;
  let page = 1;
  let perPageNum = 10;
  let list;
  let pageMaker;
  let Criteria = { "page" : page, "perPageNum" : perPageNum };
  
 	function userListAjax() {
		$.ajax({
			url: "userListAjaxPaging.do",
        	method : 'GET',
            data : Criteria,
			success: function(result){
				list = result.uList;
        		pageMaker = result.pageMaker;
        		console.log('list :', list, 'pageMaker :', pageMaker);
            	makeUserList(list, pageMaker);
			}
		});
	}
  /* 로딩시 실행되는 스크립트 */
  $(() => {
		
	// 로딩시 목록 호출
	reportListAjax();
	
	// 로딩시 정지셀렉터에 현재 날짜 자동 부여
	$("input[name=date-selector]").val(new Date().toISOString().substring(0, 10));
  	
  })
  
  function toPad(val) {
		return val < 10 ? "0" + val : val;
  }
  
  function makeUserList(list, pageMaker) {
		let $tbr = $("<tbody></tbody>");
		$.each(list, (i, u) => {
			var date = new Date(r.reportDt);
			var time = date.getFullYear() + "-" 
			         + (date.getMonth() + 1) + "-" 
			         + date.getDate() + " "
			         + toPad(date.getHours()) + ":"
			         + toPad(date.getMinutes()) + ":"
			         + toPad(date.getSeconds());
					
			$tbr.append( 
			    `<tr id="row${u.userNo}" class="row col-md-9">
			   		<td class="col-md-3">\${u.userNo}</td>
					<td class="col-md-3">\${u.userId}</td>
					<td class="col-md-3">\${u.blockEnabled}</td>
					<td class="col-md-3">\${u.blockEndDt}</td>
				</tr>
				<tr class="row col-md-3">
					<td class="row col-md-12">
					<button type="button" class="w3-btn btn-detail-dc w3-round-medium" id="detail\${r.reportNo}" style="background-color:#6dd5bc; color: white;
	        	    font-weight: 500;">정지해제/연장/강제탈퇴
	        	    </td>
	          </button>
				</tr>
			    </tr>`);
		});
		//페이지네이션 만드는 작업.
		let $pag = $("<div></div>").addClass("w3-bar").addClass("pagination-dc");
		console.log('리스트', list);
		console.log('페이지메이커', pageMaker);
		if(pageMaker.prev){
			$pag.append(`<a href="javascript:paging(\${pageMaker.startPage}-1);" class="w3-button">&laquo;</a>`);
		}
		for(let i = pageMaker.startPage; i <= pageMaker.endPage; i++){
			$pag.append(`<a class="w3-button" href="javascript:paging(\${i});" data-page="\${i}">\${i}</a>`);
		}
		if(pageMaker.next && pageMaker.endPage > 0){
			$pag.append(`<a class="w3-button" href="javascript:paging(\${pageMaker.endPage}+1);">&raquo;</a>`);
		}
		<!-- 페이지네이션, 페이징, html부분 -->
		$("#tbo > tbody").remove();
		$("#tbo").append($tbr);
		$(".pagination-dc").remove();
		$("#tbo").after($pag);
		console.log('현재페이지', pageMaker.cri.page);
		
		let page = $('#main-content > section > div.w3-bar.pagination-dc > a')
		
		for (let p of page) {
			if($(p).data('page') == pageMaker.cri.page) {
				$(p).addClass("w3-green");
			}
		}	
	}
/*페이징 관련 함수*/

function paging(idx){
    page = idx;    
	Criteria["page"] = page;
    Criteria["perPageNum"] = perPageNum; /* 나중에 셀렉박스.val로 바꾸셈 */
    userListAjax();
    }

/* 각각의 디테일 버튼이 클릭되면 함수 실행 */

  </script>

</body>
</html>