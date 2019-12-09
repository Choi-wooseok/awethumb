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
						<tr class="row">
							<th class="col-md-1">신고번호</th>
							<th class="col-md-1">유저아이디</th>
							<th class="col-md-1">정지상태</th>
							<th class="col-md-1">처리여부</th>
							<th class="col-md-2">신고사유</th>
							<th class="col-md-2">신고날짜</th>
							<th class="col-md-3">신고 발생글</th>
							<th class="col-md-1">처리하기</th>
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
  
   /*  $(document).ready(function() {
      var unique_id = $.gritter.add({
        // (string | mandatory) the heading of the notification
        title: 'Welcome to Dashio!',
        // (string | mandatory) the text inside the notification
        text: 'Hover me to enable the Close Button. You can hide the left sidebar clicking on the button next to the logo.',
        // (string | optional) the image to display on the left
        image: 'img/ui-sam.jpg',
        // (bool | optional) if you want it to fade out on its own or just sit there
        sticky: false,
        // (int | optional) the time you want it to be alive for before fading out
        time: 8000,
        // (string | optional) the class name you want to apply to that specific message
        class_name: 'my-sticky-class'
      });

      return false;
    }); */
  </script>
	<script>
  /* 정지,취소,승인등을 처리하기 위해 전역변수 설정 */
  let reportNo;
  let page = 1;
  let perPageNum = 10;
  let list;
  let pageMaker;
  let Criteria = { "page" : page, "perPageNum" : perPageNum };
  
 	function reportListAjax() {
		$.ajax({
// 			url: "reportListAjax.do",
			url: "reportListAjaxPaging.do",
        	method : 'GET',
// 			success: list => makeReportList(list)
            data : Criteria,
			success: function(result){
				list = result.rList;
        		pageMaker = result.pageMaker;
        		console.log('list :', list, 'pageMaker :', pageMaker);
            	makeReportList(list, pageMaker);
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
  
  function makeReportList(list, pageMaker) {
	let $tbr = $("<tbody></tbody>");
	$.each(list, (i, r) => {
		var date = new Date(r.reportDt);
		var time = date.getFullYear() + "-" 
		         + (date.getMonth() + 1) + "-" 
		         + date.getDate() + " "
		         + toPad(date.getHours()) + ":"
		         + toPad(date.getMinutes()) + ":"
		         + toPad(date.getSeconds());
		$tbr.append( 
		    `<tr id="row${r.reportNo}" class="row">
			    <td class="col-md-1">\${r.reportNo}</td>
			    <td class="col-md-1">\${r.userId}</td>
			    <td class="col-md-1">\${r.blockEnabled}</td>
			    <td class="col-md-1">\${r.reportStatus}</td>
			    <td class="col-md-2">\${r.reportReason}</td>
			    <td class="col-md-2">\${time}</td>
			    <td class="col-md-3">\${r.reportTitle}</td>
			    <td class="col-md-1">
          <button type="button" class="w3-btn btn-detail-dc w3-round-medium" id="detail\${r.reportNo}" style="background-color:#6dd5bc; color: white;
        	    font-weight: 500;">내용보기 & 이용정지
          </button></td>
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
		/* 	<div class="w3-bar">
			  <c:if test="${pageMaker.prev}">
			  <a href="javascript:paging(${pageMaker.startPage-1});" class="w3-button">&laquo;</a>
			  </c:if>
			  <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			  		<a class="w3-button" href="javascript:paging(${idx});">${idx}</a>
			  </c:forEach>
			  <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			  	<a class="w3-button" href="javascript:paging(${pageMaker.endPage+1});">&raquo;</a>
			  </c:if>
			</div> */
	$("#tbo > tbody").remove();
	$("#tbo").append($tbr);
	$(".pagination-dc").remove();
	$("#tbo").after($pag);
// 	#main-content > section > div.w3-bar.pagination-dc > a:nth-child(1)

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
    reportListAjax();
    }

/* 각각의 디테일 버튼이 클릭되면 함수 실행 */
$(document).on("click", ".btn-detail-dc", function() {
// 	console.log("ㄲㅈ");
  reply_click($(this).attr("id"));
  
});
function reply_click(id){
	reportNo = id.slice(6);
    view_detail(reportNo);
}

function view_detail(reportNo){
  $("#detail-block").css("display", "block");
  console.log('뷰디테일에서', reportNo);
  originPostAjax(reportNo);
}

function originPostAjax(reportNo){

  console.log('에이젝스 시작전', reportNo);
	$.ajax({
		url: "originPostAjax.do",
		type: "GET",
		data: {reportNo: reportNo},
		dataType: "json",
		success: function(result){
			if(result.blockEnabled == 'Y'){
				$("#permit-span").html(
				`<button type="button" class="w3-btn w3-red w3-small" id="cancel-block">정지해제</button>`);
			} else {
				$("#permit-span").html(
				`<button type="button" class="w3-btn w3-teal w3-small" id="permit-block">승인하기</button>`);
			}
			
			if(result.comment && result.commentUser){
				$("#Detail").html(
				`
				<h4>댓글 글쓴이 : \${result.commentUser.userNickname}</h4>
				<h4>댓글 글쓴이Id : \${result.commentUser.userId}</h4>
				<h4>댓글내용 : \${result.comment.cmtContent}</h4>
				`
				
				);
			} else if(result.board && result.userVO){
				$("#Detail").html(
					`
					<h4>글쓴이 : \${result.userVO.userNickname}</h4>
					<h4>글쓴이Id : \${result.userVO.userId}</h4>
					<h4>내용 : \${result.board.postContent}</h4>
					`
					
					);
			}
			
			else {
				$("#Detail").html(
				`
				<h4>글쓴이 : \${result.userVO.userNickname}</h4>
				<h4>글쓴이Id : \${result.userVO.userId}</h4>
				<h4>내용 : 삭제된 게시물입니다.</h4>
				
				`
				);
				
				
			}
			$("#deny-block").attr('disabled', result.reportStatus == 'Y' ? true : false);
		}
		});
}





/*모달 내부 탭메뉴*/
document.getElementsByClassName("tablink")[0].click();

function openMenu(evt, menuName) {
  var j, x, tablinks;
  x = document.getElementsByClassName("menu");
  for (j = 0; j < x.length; j++) {
    x[j].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (j = 0; j < x.length; j++) {
    tablinks[j].classList.remove("w3-light-grey");
  }
  document.getElementById(menuName).style.display = "block";
  evt.currentTarget.classList.add("w3-light-grey");
}

$(document).on('click', "#permit-block", function(){
	console.log($("input[name=date-selector]").val());
	let param = {
		blockDate : $("input[name=date-selector]").val(),
		reportNo: reportNo	
	}
	$.ajax({
		url: "blockUser.do",
		type: "POST",
		data: JSON.stringify(param),
		dataType: "json",
		contentType: "application/json",
		success: function(result){
			alert('이용정지 처리 완료되었습니다.');
			$("#permit-span").html(
			`<button type="button" class="w3-btn w3-red w3-small" id="cancel-block">정지해제</button>`);
			reportListAjax();
		}
		
	});
})
$(document).on("click","#deny-block", function(){
	console.log('디나이블럭 진입');
	$.ajax({
		url:"denyBlock.do?reportNo="+reportNo,
		success: function(result){
			alert(result + '님에대한 신고가 승인취소 처리 완료되었습니다.');
 			reportListAjax();
 		}
	})
})

$(document).on("click", "#delete-report", function() {
	console.log('딜리트 리포트 진입시 reportNo', reportNo);
	$.ajax({
		url:"deleteReport.do?reportNo="+reportNo,
		success: function(){
			alert('신고글이 삭제처리 되었습니다.');
			reportListAjax();
		}
	})
})

$("#delete-report").on("click", function(){
	
})

$(document).on("click", "#cancel-block", function() {
	console.log('캔슬블럭 진입시 reportNo', reportNo);
	$.ajax({
 		url:"cancelBlock.do?reportNo="+reportNo,
 		dataType: "json",
		success: function(result){
 			alert('이용정지가 해제되었습니다.');
 			$("#permit-span").html(
			`<button type="button" class="w3-btn w3-teal w3-small" id="permit-block">승인하기</button>`);
 			reportListAjax();
 		}
 	})
  
});

  
  </script>
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