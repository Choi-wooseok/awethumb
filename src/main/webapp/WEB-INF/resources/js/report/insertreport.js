/**
 * 
 */
/*	$(function(){
		$("#reason").attr("disabled", true);*/
if(commentNo == null || commentNo == undefined || commentNo == ""){
	$.ajax({
		url: "selectReportPost.do?postNo="+postNo,
		dataType: "json",
		success: function(result){
			$("#userId").html(`<h3>신고대상 회원아이디 : ${result.user.userId}</h3>
							   <input type="hidden" name="userNo" value="${result.user.userNo}">
							   <input type="hidden" name="postNo" value="${postNo}">`)
			$("#userNickName").html(`<h3>신고대상 회원닉네임 : ${result.user.userNickname}</h3>`)
		}
	})
} else {
	$.ajax({
		url: "selectReportPostAndComment.do?postNo="+postNo+"&commentNo="+commentNo,
		dataType: "json",
		success: function(result){
			$("#userId").html(`<h3>신고대상 회원아이디 : ${result.user.userId}</h3>
							   <input type="hidden" name="userNo" value="${result.user.userNo}">
					   		   <input type="hidden" name="postNo" value="${postNo}">
					   		   <input type="hidden" name="commentNo" value="${commentNo}">`)
			$("#userNickName").html(`<h3>신고대상 회원닉네임 : ${result.user.userNickname}</h3>`)
			}
		})
}
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
	