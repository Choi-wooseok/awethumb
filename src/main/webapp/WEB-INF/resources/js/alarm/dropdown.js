$(".alarm-dropdown-btn").click(() => {
	// 알림 목록을 가져온다
	getDropdownAlarmListAjax();
	
	// 안읽은 알림을 전부 읽음처리한다.
	readAlarmAjax();
	
	toggleAlarmDropdown();
})

function toggleAlarmDropdown(){
	$(".alarm-dropdown-wrap").toggleClass("alarmhidden")
	$(".alarm-list").text("");
}

function getDropdownAlarmListAjax(){
	$.ajax({
		url: pageContextPath + "/alarm/getalarmlist.do",
		data:{
			receiveUserNo: connectedUserNo,
			countPerPage: 3,
			currentPageNo: 0
		},
		dataType: "json"
	})
	.done((list) =>{
		for (alarm of list){
			appendDropdownAlarm(alarm);
		}
	})
}

function appendDropdownAlarm(alarm) {
	const appendLine = alarmTypeAppender(alarm.alarmType);
	$(".alarm-list").append(`
		<li class="alarm-${alarm.alarmNo}">
			<article>
				<div>
					<a class="alarm-profile-link" href="${pageContextPath}/profile/${alarm.userNickname}">
						<img class="alarm-dropdown-thumb adt-${alarm.sendUserNo}">
					</a>
				</div>
				<div class="alarm-content ac-${alarm.alarmNo}">
					<span class="ac-usernick">${alarm.userNickname}</span>님이 ${appendLine}
				</div>
			</article>
			<button class="alarm-dropdown-delbtn alarm-delbtn" data-alarmno="${alarm.alarmNo}">X</button>
			<span class="alarm-dropdown-time">${alarm.timeAgo}</span>
		</li>
	`)
	getDropdownAlarmThumbAjax(alarm.sendUserNo);
}
function prependDropdownAlarm(alarm) {
	const appendLine = alarmTypeAppender(alarm.alarmType);
	$(".alarm-list").prepend(`
			<li class="alarm-${alarm.alarmNo}">
			<article>
			<div>
			<a class="alarm-profile-link" href="${pageContextPath}/profile/${alarm.userNickname}">
			<img class="alarm-dropdown-thumb adt-${alarm.sendUserNo}">
			</a>
			</div>
			<div class="alarm-content ac-${alarm.alarmNo}">
			<span class="ac-usernick">${alarm.userNickname}</span>님이 ${appendLine}
			</div>
			</article>
			<button class="alarm-dropdown-delbtn alarm-delbtn" data-alarmno="${alarm.alarmNo}">X</button>
			<span class="alarm-dropdown-time">${alarm.timeAgo}</span>
			</li>
	`)
	getDropdownAlarmThumbAjax(alarm.sendUserNo);
}

// 알림의 프로필 사진을 가져온다.
function getDropdownAlarmThumbAjax(userNo) {
	$.ajax({
		url: pageContextPath + `/api/user/${userNo}/thumb`
	})
	.done((src) =>{
		$(`.adt-${userNo}`).attr("src", src)
	})
}

// 알림 내용을 알림타입에 따라 정해준다.
function alarmTypeAppender(type){
	let appendLine = "";
	switch(type){
	case 1: appendLine = "구독을 시작했습니다."; break;
	case 2: appendLine = "게시글을 좋아합니다."; break;
	case 3: appendLine = "게시글에 댓글을 남겼습니다."; break;
	case 4: appendLine = "프로젝트를 공유했습니다."; break;
	}
	return appendLine;
}

// 알림을 읽음처리 하는 ajax
function readAlarmAjax(){
	$.ajax({
		url: pageContextPath + "/alarm/readalarm",
		type: "PUT",
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify({userNo : connectedUserNo}),
		success: $(".alarmCnt").text("0")
	})
}

// 알림 삭제하는 이벤트
$(document).on("click", ".alarm-delbtn", (e)=>{
	const alarmNo = $(e.target).data("alarmno");
	console.log("alarmno = ", alarmNo)
	$.ajax({
		url: pageContextPath + "/alarm/deletealarm/" + alarmNo,
		method: "DELETE",
		success: $(`.alarm-${alarmNo}`).remove()
	})
})

// 가장 최근 알림을 가져오는  ajax
function getLatestAlarm(){
	$.ajax({
		url: pageContextPath + "/alarm/getlatestalarm.do",
		dataType: "json",
	})
	.done(alarm => {
		prependDropdownAlarm(alarm)
	})
}