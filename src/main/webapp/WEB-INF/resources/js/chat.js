let sock = new WebSocket('ws://localhost:8000' + path + '/chat.do');

//	let scrollBottom = $("body").height() - $(window).height() - $(window).scrollTop(); //스크롤바텀값


function connect() {
    sock.onopen = function() {
        console.log('open');
    };
    
    sock.onmessage = function(evt) {
   	   let data = evt.data;
 	   let obj = JSON.parse(data)
 	   
 	   // 처음 보냈을 때 - userNickname, 방이 존재 할 때 - obj.sendUser
 	   let sendUser = obj.sendUser || obj.userNo;
// 	   console.log(obj);
 	 
 	   
 	   $(".selectmsg").each((idx, val) => {
 		  if (sendUser == $(val).data("userno")) {
 			  // 처음 개설된 방이 아니라면 읽지 않은수 증가
 			  if ($(val).data("room") != 0) {
 				  let cnt = parseInt($(val).children(".info").children(".status.on").children("span").text());
 				  $(val).children(".info").children(".status.on").children("span").text(cnt + 1);
 				  unReadTotCnt += 1;
 			  }
 		  }  
 	   });
 	  // 처음 보내서 유저 닉네임 없다면(받은 사용자 입장)
 	   if (obj.userNickname) {
 		    $(".list-friends").append(createFriend(sendUser, obj.userNickname, obj.chatroomNo, 1));
			$(".ui > .chat").append(createRoom(sendUser, obj.userNickname, obj.chatroomNo, 1));
 	   } 
 	   
 	   // 보낸사람이 방을 개설했을 경우(방 번호 추가)
 	   else if (obj.roomNo) {
 		  console.log("roomNo : " + obj.roomNo);
 		  console.log("takUser : " + obj.takeUser);
 		  $(".selectmsg").each((idx, val) => {
 			 if (obj.takeUser == $(val).data("userno")) {
 				 $(val).attr("data-room", obj.roomNo);
// 				 console.log("changedRoom : " + $(val).data("room"));
 			 }
 		  });
 		  
 		  $(".msglist").each((idx, val) => {
 			  if (obj.takeUser == $(val).data("userno")) {
 				  $(val).attr("data-room", obj.roomNo);
// 				  console.log("changedRoom : " + $(val).data("room"));
 			  }
 		  });
 		  
 	   }
 	   
		 
 	  
   
	  $(".msglist").each((idx, val) => {
		  console.log("sendUser : " + sendUser + " : userNo :" + $(val).data("userno"));
 		  if (sendUser == $(val).data("userno")) {
			  let sendData = sendMsg("i", obj.messageContent);
			  console.log($(val).children(".messages"));
			  $(val).children(".messages").append(sendData);
			  flag = true;
 		  }  
 	   });
 	   
    };
    sock.onclose = function() {
        console.log('close');
    };
}
$(document).ready(function () {

  	connect();

});


 // 보낸 메세지 화면에 append 하기
 function sendMsg(sendType, msgContent) {
	 return `
	 	<li class="${sendType}">
			<div class="head">
				<span class="time">10:13 AM, Today</span>
				<span class="name">1111</span>
			</div>
			<div class="message">${msgContent}</div>
	 	</li>
	 	`;
 }

 // 메세지 보내기
 function send({msg, takeUserNo}) {
  console.log(msg);
  if(msg != ""){
	  message = {};
	  message.messageContent = msg;
	  message.sendUser = loginUserNo;
	  message.takeUser = takeUserNo;
	  
  }


  sock.send(JSON.stringify(message));
//  $("#message").val("");
 }


 // 시간 구하기 - 구현 해야 함
 function getTimeStamp() {
   var d = new Date();
   var s =
     leadingZeros(d.getFullYear(), 4) + '-' +
     leadingZeros(d.getMonth() + 1, 2) + '-' +
     leadingZeros(d.getDate(), 2) + ' ' +

     leadingZeros(d.getHours(), 2) + ':' +
     leadingZeros(d.getMinutes(), 2) + ':' +
     leadingZeros(d.getSeconds(), 2);

   return s;
 }

 function leadingZeros(n, digits) {
   var zero = '';
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
		unReadTotCnt -= parseInt($(this).children(".info").children(".status.on").children("span").text());
		// 안읽은 수 값 바꾸기
		$(this).children(".info").children(".status.on").children("span").text("0");
		$(".msglist").css("display", "none");
		
		$(".msglist").each(function (idx, val) {
			
			if (userNo == $(val).data("userno")) {
				$(val).css("display", "block");
				sendUserNo = $(val).data("userno");
				// 안읽은 수 읽은 날짜 현재로 변경하기
				if ($(val).data("unreadcnt") != 0) {
					msgRead($(val).data("room"));
				}
				console.log($(document).height());
				console.log($(val));
				console.log($(val).children(".messages").height() + 10);
				$(val).children(".messages").scrollTop($(val).children(".messages").prop('scrollHeight'));
				return;
			}
		});
	});

	
		
		
		
		
	$(document).on("click", ".send", function (e) {
		console.log($(this).parent().prev());
		let sendContent = $(this).prev().val();
		let sendData = sendMsg("friend-with-a-SVAGina", sendContent);
		$(this).parent().prev().append(sendData);
		$(this).prev().val("");
		send({
			   msg: sendContent,
			   takeUserNo: $(this).parent().parent().data("userno")
		});
	});
	
	
	
	function msgRead(chatroomNo) {
		$.ajax({
			url: "read_msg.do",
			method: 'POST',
			data: {takeUser : loginUserNo, chatroomNo: chatroomNo},
			dataType: 'JSON',
			success: result => {console.log("ㅎㅇ")}
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
	
	
	// 메시지 이용자 추가
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
	
	
	// 처음 검색 후에 방 만들기
	function createRoom(userNo, userNickname, chatroomNo, unReadCnt) {
		chatroomNo = chatroomNo || 0;
		unReadCnt = unReadCnt || 0;
		return `
				<div class="msglist" data-room="${chatroomNo}" data-userno="${userNo}" data-unreadcnt="${unReadCnt}" style="display: none">
								<div class="top">
									<div class="avatar">
											<img width="50" height="50" src="http://cs625730.vk.me/v625730358/1126a/qEjM1AnybRA.jpg">
									</div>
									<div class="info">
										<div class="name">${userNickname}</div>
									</div>
								</div>
								<ul class="messages">
										
								</ul>
								<div class="write-form">
									<textarea placeholder="Type your message" name="e" id="texxt"  rows="2"></textarea>
									<span class="send">Send</span>
								</div>
					</div>		
				`; 	
	} 
	
	
  
