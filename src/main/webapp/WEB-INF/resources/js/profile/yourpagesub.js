const $sBtn = $(".sBtn");
// 구독여부 확인하는 스크립트
$.ajax({
	url: "checksub.do",
	data: {
		subUserNo,
		oppUserNo : userNo
	}
}).done((e) => {
	if(e == 1) $sBtn.toggleClass("myHidden");
})

// 구독 취소
$(".unsub_btn").click(() => {
	$.ajax({
		url: `deletesub.do?subUserNo=${subUserNo}&oppUserNo=${userNo}`,
		type: "DELETE"
	}).done(() => {
		$sBtn.toggleClass("myHidden");
		$(".follower_cnt").text(--follwerCnt);
	})
})

// 구독
$(".sub_btn").click(() => {
	$.ajax({
		url: "insertsub.do",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			subUserNo,
			oppUserNo: userNo
		})
	}).done(() => {
		$sBtn.toggleClass("myHidden");
		$(".follower_cnt").text(++follwerCnt);
		// 알림 전송
		makeAlarm(1, userNo);
	})
})