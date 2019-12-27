/**
 * 
 */
$.ajax({
	url: pageContextPath + "/alarm/alarmcount.do",
	data:{
		userNo: connectedUserNo
	}
})
.done((e) =>{
		console.log(e)
		$(".alarmCnt").text(e)
	}
)

// 알림 소켓 서버
const alarmSocketUri = "ws://awethumb.kr/alarm.do";

const alarmsocket = new WebSocket(alarmSocketUri);

alarmsocket.onmessage = function(evt) {
	$(".alarmCnt").text(evt.data)
	// 알림창이 열려있고 알림 오면 최근 내용을 알림에 추가 시킨다.
	if($(".alarm-dropdown-wrap").hasClass("alarmhidden") == false) getLatestAlarm();
};


// 알림을 보내는 함수
// 알림 타입  1.구독  2.좋아요  3.댓글  4.공유 수락
function makeAlarm(...arr){
// 	alarmType, receiveUserNo, boardNo, commentNo, projectNo
	let eventNo = null;
	map = {
		sendUserNo : connectedUserNo,
		alarmType: arr[0]
	}
	
	switch(arr[0]){
		case 1: map["receiveUserNo"] = arr[1]; break;
		case 2: map["boardNo"] = arr[1]; break;
		case 3: map["commentNo"] = arr[1]; break;
		case 4: map["projectNo"] = arr[1]; break;
		default : return;
	}
	alarmsocket.send(JSON.stringify(map))
}