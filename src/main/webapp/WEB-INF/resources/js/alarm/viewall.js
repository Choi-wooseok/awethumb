const alarmCountPerPage = 10;
var alarmCurrentPageNo = 0;


getAlarmListAjax();

function getAlarmListAjax(){
	$.ajax({
		url: pageContextPath + "/alarm/getalarmlist.do",
		data:{
			receiveUserNo: connectedUserNo, 
			countPerPage: alarmCountPerPage,
			currentPageNo: alarmCurrentPageNo
		},
		dataType: "json"
	})
	.done((list) =>{
		alarmCurrentPageNo += list.length;
		
		for (alarm of list){
			appendAlarm(alarm);
		}
	})
}
function appendAlarm(alarm) {
	const appendLine = alarmTypeAppender(alarm.alarmType);
	$(".alarmList").append(`
		<li class="alarm-item-wrap alarm-${alarm.alarmNo}">
			<article>
				<div class="alarm-item-image">
					<a href="${pageContextPath}/profile/${alarm.userNickname}">
						<img class="alarm-item-image-thumb adt-${alarm.sendUserNo}">
					</a>
				</div>
				<div class="alarm-item-content">
					<div class="alarm-item-content-top">
						<div class="alarm-user">
							<div class="user-wrap">
								<div class="user-title">
									<h3>
										<a href="${pageContextPath}/profile/${alarm.userNickname}">${alarm.userNickname}</a>
									</h3>
								</div>
								<div class="user-meta">
									<div class="meta-status">
										<i class="fas fa-users"></i>
										<span class="follower-count user-${alarm.sendUserNo}-folcnt">1</span>
									</div>
									<div class="meta-btn"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="alarm-item-content-down">
						<h3><a href="#">${appendLine}</a></h3>
						<div><button class="alarm-delbtn" data-alarmno="${alarm.alarmNo}">X</button></div>
					</div>
				</div>
				<span class="alarm-item-time">${alarm.timeAgo}</span>
			</article>
		</li>
	`)
	getAlarmThumbAjax(alarm.sendUserNo);
	checkSubAjax(alarm.receiveUserNo, alarm.sendUserNo);
	getFollowerCount(alarm.sendUserNo)
}

// 썸네일 가져오기
function getAlarmThumbAjax(userNo) {
	$.ajax({
		url: pageContextPath + "/profile/getprofileimg.do",
		data:{userNo},
	})
	.done((src) =>{
		$(`.adt-${userNo}`).attr("src", src)
	})
}

// 구독 여부 확인하기
function checkSubAjax(subUserNo, oppUserNo){
	$.ajax({
		url: pageContextPath + "/profile/checksub.do",
		data: {
			subUserNo,
			oppUserNo
		}
	}).done((e) => {
		if(e === 1) $(".meta-btn").html(`<button class="userNo-${oppUserNo}-btn unsub_btn" data-oppuserno="${oppUserNo}">구독중</button>`)
		else $(".meta-btn").html(`<button class="userNo-${oppUserNo}-btn sub_btn" data-oppuserno="${oppUserNo}">구독</button>`)
	})
}

//구독 취소
$(document).on("click", ".unsub_btn",(e) => {
	const oppUserNo = $(e.target).data("oppuserno");
	$.ajax({
		url: pageContextPath + `/profile/deletesub.do?subUserNo=${connectedUserNo}&oppUserNo=${oppUserNo}`,
		type: "DELETE"
	}).done(() => {
		$(`.userNo-${oppUserNo}-btn`).removeClass("unsub_btn").addClass("sub_btn").text("구독")
	})
})

// 구독
$(document).on("click", ".sub_btn",(e) => {
	const oppUserNo = $(e.target).data("oppuserno");
	$.ajax({
		url: pageContextPath + "/profile/insertsub.do",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify({
			subUserNo: connectedUserNo,
			oppUserNo
		})
	}).done(() => {
		$(`.userNo-${oppUserNo}-btn`).removeClass("sub_btn").addClass("unsub_btn").text("구독중")
		makeAlarm(1, oppUserNo);
	})
})

// 팔로우 숫자 구해오기
function getFollowerCount(userNo){
	$.ajax({
		url: pageContextPath + "/profile/getfollowercount",
		data: {userNo}
	})
	.done((e) => {
		$(`.user-${userNo}-folcnt`).text(e)
	})
}

// 무한 스크롤

$(window).scroll(function() {
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
    	// 불러올 목록들이 더 있을 때만 리스트를 불러옴
    	if(alarmCurrentPageNo % alarmCountPerPage === 0){
    		getAlarmListAjax();
    	}
    }
});
	
