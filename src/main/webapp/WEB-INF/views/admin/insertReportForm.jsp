<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고하기</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <script src="lib/jquery/jquery.min.js"></script>
</head>
<body class="w3-container w3-auto" style="width:700px;">
	<!-- 이걸 해줌으로써 su.userName이런식으로 현재 로그인한 사용자의 정보를 받아올수 있게 된다. -->
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
	<div>
		<form action="<c:url value='/admin/insertReport.do'/>" method="post" name="submit-form" class="w3-panel w3-card-4" onsubmit='return submitForm();'>
			<h2>신고하기</h2>
			<div id="userId">신고대상 회원 아이디 : </div>
			<div id="userNickName">신고대상 회원 닉네임 : </div>
			<div>
			<select class="w3-select" name="blockCode">
			    <option value="" disabled selected>신고 사유를 선택해주세요.</option>
			    <option value="1">욕설 및 비방</option>
			    <option value="2">음란물</option>
			    <option value="3">스팸</option>
			    <option value="4">사행성</option>
			    <option value="5">기타</option>
			</select>
			</div>
			<input type="text" class="w3-input" placeholder="기타를 선택하신분은 사유를 적어주세요." id="reason" name="reportReason">
			<input type="hidden" name="reportUserNo" value="${su.userNo}">
			<button id="report-button" >신고하기</button>
			<br>
		</form>
	</div>
	
<script>
	/* 기본 디스에이블드, 셀렉트변화시 기타일경우에 풀어주고, 다시 다른걸로 변하면 잠궈준다. */
	$(function(){
		$("#reason").attr("disabled", true);
		
		/*
		겟방식으로 이 페이지에 들어왔을때, commentNo가 있으면 댓글이 신고된 것이고, postNo만 있으면 게시글이 신고된 것이다.
		commentNo가 null이 아닐경우와 null일 경우에 따라, ajax로 바로 게시글이나 댓글의 원본을 추적해서 화면에 보여준다.
		*/
		let postNo = "";
		let commentNo = "";
		postNo = <%= request.getParameter("postNo") %>;
		commentNo = <%= request.getParameter("commentNo") %>;
		console.log('postNo: '+postNo, 'commentNo: '+commentNo);
		
		if(commentNo == null){
			$.ajax({
				url: "selectReportPost.do?postNo="+postNo,
				dataType: "json",
				success: function(result){
					$("#userId").html(`<h3>신고대상 회원아이디 : \${result.user.userId}</h3>
									   <input type="hidden" name="userNo" value="\${result.user.userNo}">
									   <input type="hidden" name="postNo" value="\${postNo}">`)
					$("#userNickName").html(`<h3>신고대상 회원닉네임 : \${result.user.userNickname}</h3>`)
				}
			})
		} else {
			$.ajax({
				url: "selectReportPostAndComment.do?postNo="+postNo+"&commentNo="+commentNo,
				dataType: "json",
				success: function(result){
					$("#userId").html(`<h3>신고대상 회원아이디 : \${result.user.userId}</h3>
									   <input type="hidden" name="userNo" value="\${result.user.userNo}">
							   		   <input type="hidden" name="postNo" value="\${postNo}">
							   		   <input type="hidden" name="commentNo" value="\${commentNo}">`)
					$("#userNickName").html(`<h3>신고대상 회원닉네임 : \${result.user.userNickname}</h3>`)
				}
			})
		}
	})
	$('select[name="blockCode"]').on('change', () => {
	if($('select[name="blockCode"]').val() == 5){
			$("#reason").attr("disabled", false);
	} else {
		$("#reason").attr("disabled", true);
	}
	})
	
	function submitForm(){
		if($("select[name=blockCode").val() == null){
			alert("신고 사유를 입력해주세요.");
			return false;
		}
		let confirm_report = confirm("허위 신고시 제제를 받으실 수 있습니다.");
		if(confirm_report == true){
			alert("신고가 완료되었습니다.");
			return true;
		}
		else if(confirm_report == false){
			return false;
		}
		
	}
	
	
</script>
</body>
</html>