$.ajax({
	url: pageContextPath + "/chat/chat_list.do",
	data:{
		userNo: connectedUserNo
	}
})
.done((e) =>{
		console.log(e);
//		$(".alarmCnt").text(e)
	}
)

function makeChatList(list) {
	
}




let sock = new WebSocket('ws://localhost:8000' + path + '/chat.do');



function connect() {
    sock.onopen = function() {
    };

	sock.onmessage = function(evt) {
	   let data = evt.data;
	   let obj = JSON.parse(data)
	   
	   // 처음 보냈을 때 - userNickname, 방이 존재 할 때 - obj.sendUser
	   let sendUser = obj.sendUser || obj.userNo;
	 
	   
	  
	  // 처음 보내서 유저 닉네임 없다면(받은 사용자 입장)
	   if (obj.userNickname) {
		    $(".list-friends").append(createFriend(sendUser, obj.userNickname, obj.chatroomNo, 0));
			$(".ui > .chat").append(createRoom(sendUser, obj.userNickname, obj.chatroomNo, 0));
	   } 
	   
	   // 보낸사람이 방을 개설했을 경우(방 번호 추가)
	   else if (obj.roomNo) {
		  $(".selectmsg").each((idx, val) => {
			 if (obj.takeUser == $(val).data("userno")) {
				 $(val).attr("data-room", obj.roomNo);
			 }
		  });
		  
		  $(".msglist").each((idx, val) => {
			  if (obj.takeUser == $(val).data("userno")) {
				  $(val).attr("data-room", obj.roomNo);
				  $(val).attr("id", "msg" + obj.roomNo);
			  }
		  });
		  
	   }
	 	   
	   $(".selectmsg").each((idx, val) => {
			  if (sendUser == $(val).data("userno")) {
				  let roomNo = $(val).data("room");
				  // 읽지 않은 수 증가
				  let msgId = "#msg" + roomNo;
				  // 보낸/받는 채팅방이 열려있지 않은 경우
				  if ($(msgId).css("display") == "none") {
					  let cnt = parseInt($(val).children(".info").children(".status").children("span").text());
					  $(val).children(".info").children(".status").removeClass("on");
					  $(val).children(".info").children(".status").addClass("off");
					  $(val).children(".info").children(".status").children("span").text(cnt + 1);
					  unReadTotCnt += 1;
				  // 보낸/받는 채팅방이 열려 있을 경우 처리
				  } else {
					  msgRead(roomNo);
				  }
				  
			  }  
		}); 
	 	  
	   	// 받은 메세지 처리
		  $(".msglist").each((idx, val) => {
		  if (sendUser == $(val).data("userno")) {
			  let sendData = sendMsg("i", obj.messageContent, obj.sendDate, obj.sendTime);
			  $(val).children(".messages").append(sendData);
			  $(val).children(".messages").scrollTop($(val).children(".messages").prop('scrollHeight'));
			  flag = true;
		  }  
	   });
	   
	};
	sock.onclose = function() {
	};
}
$(document).ready(function () {
  	connect();
});


$(".messages").scroll(function (e) {
	let scTop = $(this).scrollTop();

	if (scTop < 1) {
		// 채팅 내용이 더이상 없다면
		if ($(this).data("isprev") == "false") return;
		let pageIdx = $(this).data("pageidx");
		if (pageIdx) {
			$(this).data("pageidx", parseInt(pageIdx + 1));
			loadMore($(this).data("pageidx"), 10 , $(this).parent().data("room"), this);
			
		}
		
	}
	
});



 // 페이징 처리
 function loadMore(pageIndex, pageCount, chatroomNo, prependMessage) {
	 $.ajax({
		url: "select_message_more.do",
		type: "POST",
		data: JSON.stringify({
				"chatroomNo" : chatroomNo,
				"page" :{
					"pageIndex" : pageIndex,
					"pageCount" : pageCount
				}
			}),
		dataType: "JSON",
		contentType: 'application/json; charset=UTF-8',
		success: result => {
			let prependList = "";
			if (result.length == 0) {
				$(prependMessage).data("isprev", "false");
				return;
			}
			$(result).each((idx, val) => {
				let sendType
				prependList += sendMsg(val.sendUser == loginUserNo ? "friend-with-a-SVAGina" : "i", val.messageContent, val.sendDate, val.sendTime);
				
				
			});
			let screenPinning = $(prependMessage).children("li:first");
			$(prependMessage).prepend(prependList);
			$(prependMessage).scrollTop(screenPinning.offset().top);
		}
	 })
 }


 // 보낸 메세지 화면에 append 하기
 function sendMsg(sendType, msgContent, sendDate, sendTime) {
	 return `
	 	<li class="${sendType}">
			<div class="head">
				<span class="time">${sendDate}</span>
				<span class="name">${sendTime}</span>
			</div>
			<div class="message">${msgContent}</div>
	 	</li>
	 	`;
 }

 // 메세지 보내기
 function send({msg, takeUserNo, sendDate, sendTime}) {
  if(msg != ""){
	  message = {};
	  message.messageContent = msg;
	  message.sendUser = loginUserNo;
	  message.takeUser = takeUserNo;
	  message.sendDate = sendDate;
	  message.sendTime = sendTime;
	  
  }


  sock.send(JSON.stringify(message));
 }


 // 시간 구하기
 function getTimeStamp() {
   let d = new Date();
   let hour = d.getHours();
   let ampm = "오전 ";
   if (hour > 12) {
	   hour = hour - 12;
	   ampm = "오후 ";
   }
   let s = { sendDate : 
					     leadingZeros(d.getFullYear(), 4) + '년 ' +
					     leadingZeros(d.getMonth() + 1, 2) + '월 ' +
					     leadingZeros(d.getDate(), 2) + '일'
		   ,sendTime :
			   			 ampm + leadingZeros(hour, 2) + ':' +
					     leadingZeros(d.getMinutes(), 2)
   }
   return s;
 }

 function leadingZeros(n, digits) {
   let zero = '';
   n = n.toString();

   if (n.length < digits) {
     for (i = 0; i < digits - n.length; i++)
       zero += '0';
   }
   return zero + n;
 }

	  
	  
  
  
// 검색 기능
$("#srchNickname").keyup(() => {
	let searchWord = $("#srchNickname").val().replace(/ /g, '');
	if (searchWord != '') {
		
		// 이미 채팅방에 있는 유저라면 제외하고 검색
		let existUser = [];
		$(".msglist").each(function(idx, val) {
			existUser.push($(val).data("userno"));
			
		});
		// 자기 자신도 제외
		existUser.push(loginUserNo);
		
		$.ajax({
				url: "searchNickname.do",
				method: 'POST',
				data: JSON.stringify({"searchWord": searchWord, "existUser": existUser}),
				dataType: 'JSON',
				contentType: 'application/json; charset=UTF-8',
				success: result => {
							if (result.length > 0) {
									let str = "";
									for (let i = 0; i < result.length; i++) {
										str += ('<div class="result-search height" data-userNo="' + result[i].userNo + '">' + result[i].userNickname + '</div>');
									}
									$("#searchResult").html(str);
									$("#searchResult").css("display", "block")
								}
								
							else {
								$("#searchResult").html("");
								$("#searchResult").css("display", "none")
	
							}
					}
			  });
	} else {
			$("#searchResult").html("");
			$("#searchResult").css("display", "none")
	}
				
});
  


$(document).on("click", ".selectmsg", function (e) {
	let userNo = $(this).data("userno");
	
	// 토탈 안읽은 값 빼기
	unReadTotCnt -= parseInt($(this).children(".info").children(".status").children("span").text());
	// 안읽은 수 값 바꾸기
	$(this).children(".info").children(".status").removeClass("off");
	$(this).children(".info").children(".status").addClass("on");
	$(this).children(".info").children(".status").children("span").text("0");
	// 방 전체 숨기기
	$(".msglist").css("display", "none");
	
	$(".msglist").each(function (idx, val) {
		
		// 클릭한 방 이라면 보여주기
		if (userNo == $(val).data("userno")) {
			$(val).css("display", "block");
			sendUserNo = $(val).data("userno");
			// 안읽은 수 읽은 날짜 현재로 변경하기
			if ($(val).data("unreadcnt") != 0) {
				msgRead($(val).data("room"));
			}
			$(val).children(".messages").scrollTop($(val).children(".messages").prop('scrollHeight'));
			return;
		}
	});
});

	
		
		
		
		
$(document).on("click", ".send", function (e) {
	let sendContent = $(this).prev().val();
	if (sendContent.trim() == '' || sendContent.trim().length > 100) {
		Swal.fire({
			  icon: 'error',
			  title: '글 내용 오류',
			  text: '빈값이나 100자가 넘는 냬용은 보내실 수 없습니다.'
		})
		return;
	} 
	let {sendDate, sendTime} = getTimeStamp();
	let sendData = sendMsg("friend-with-a-SVAGina", sendContent, sendDate, sendTime);
	$(this).parent().prev().append(sendData);
	$(this).prev().val("");
	send({
		   msg: sendContent,
		   takeUserNo: $(this).parent().parent().data("userno"),
		   sendDate: sendDate,
		   sendTime: sendTime
	});
	// 맨 밑으로 스크롤 내리기
	$(this).parent().prev().scrollTop($(this).parent().prev().prop('scrollHeight'));
});
	
	
	
// 읽은 메세지 처리	
function msgRead(chatroomNo) {
	// 읽지 않은 메세지 읽을 시에 waitMe 기능 끄기
	$("#waitme-status").attr("class", "waitme-container-none");
	$.ajax({
		url: "read_msg.do",
		method: 'POST',
		data: {takeUser : loginUserNo, chatroomNo: chatroomNo},
		dataType: 'JSON',
		success: result => {
				// 읽었다면 waitMe 기능 켜기
				$("#waitme-status").attr("class", "waitme-container");
			}
	});
}

// 유저 닉네임으로 검색하여 채팅하기 - 이미 채팅중인 사용자는 제외
$(document).on("click", ".result-search", function() {
	let userNick = $(this).text();
	let userNo = $(this).data("userno");
	$(".list-friends").append(createFriend(userNo, userNick));
	$(".ui > .chat").append(createRoom(userNo, userNick))
	
	$("#searchResult").css("display","none");
	$("#srchNickname").val("");
})


// 메시지 이용자 추가 - 사용자 닉네임 검색했 을 경우
function createFriend(userNo, userNickname, chatroomNo, unReadCnt) {
	chatroomNo = chatroomNo || 0;
	unReadCnt = unReadCnt || 0;
	return `<li class="selectmsg" data-room="${chatroomNo}" data-userno="${userNo}">
			<img width="50" height="50" src="http://cs625730.vk.me/v625730358/1126a/qEjM1AnybRA.jpg">
			<div class="info">
				<div class="user">${userNickname}</div>
				<div class="status on">안읽은수:<span>${unReadCnt}</span></div>
			</div>
		</li>`;
}


// 사용자 검색 후에 방 만들기
function createRoom(userNo, userNickname, chatroomNo, unReadCnt) {
	chatroomNo = chatroomNo || 0;
	unReadCnt = unReadCnt || 0;
	return `
			<div class="msglist" id="msg${chatroomNo}" data-room="${chatroomNo}" data-userno="${userNo}" data-unreadcnt="${unReadCnt}" style="display: none">
							<div class="top">
								<div class="avatar">
										<img width="50" height="50" src="http://cs625730.vk.me/v625730358/1126a/qEjM1AnybRA.jpg">
								</div>
								<div class="info">
									<div class="name">${userNickname}</div>
								</div>
							</div>
							<ul class="messages" data-pageidx="0">
									
							</ul>
							<div class="write-form">
								<textarea placeholder="보낼 메세지 입력" name="e" id="texxt"  rows="2"></textarea>
								<span class="send">Send</span>
							</div>
				</div>		
			`; 	
} 

	
  
