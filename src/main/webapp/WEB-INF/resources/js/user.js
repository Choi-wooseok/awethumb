$(document).ready(function() {
	let maxSize = 300;
	let boxSize = $("#boxSize");
	let image = $("#image");
	let imgHeight = image.height;
	let imgWidth = image.width;
	if (imgWidth > maxSize && imgHeight > maxSize) {
		if (imgWidth > imgHeight) {
			boxSize.style.width = maxSize + "px";
			boxSize.style.height = "auto";
			image.style.width = "100%";
		} else {
			boxSize.style.width = "auto";
			boxSize.style.height = maxSize + "px";
			image.style.height = "100%";
		}
	} else if (imgWidth > maxSize && imgHeight < maxSize) {
		boxSize.style.width = maxSize + "px";
		image.style.width = "100%";
	} else if (imgWidth < maxSize && imgHeight > maxSize) {
		boxSize.style.height = maxSize + "px";
		image.style.height = "100%";
	}
	$("#join2").css("display","none");
	$("#join3").css("display","none");
})

let chkUserId = false;
let chkUserNickname = false;
let passFlag = false;


//let idPtn = /(^[a-zA-Z0-9]{4,12})+$/;
let idPtn = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
let namePtn = /(^[가-힣]{2,6})+$/;
let nicknamePtn = /(^[a-zA-Z0-9]{5,30})+$/;
let passPtn = /(^(?=.*[a-zA-Z])(?=.*[!@#$%^~*+=-])(?=.*[0-9]).{10,20})+$/;




$("#modalOpen").click(() => {
	$(".modal").css("display","block");
});
$("#modalClose").click(() => {
	$(".modal").css("display","none");
});

$("#userIdChk").click(() => {
	chkUser('userId', $("#userId").val(), "이미 등록된 유저 아이디 입니다.", "id");
});

$("#userNicknameChk").click(() => {
	chkUser('userNickname', $("#userNickname").val(), "이미 등록된 유저 닉네임 입니다.", "nickname");
});

function chkUser(chkType, chkValue, validateMsg, chkIdNick) {
	if (chkIdNick == "id") {
		if(chkValue.trim() == '' || !(idPtn.test(chkValue))) {
			Swal.fire({
				icon: 'error',
				title: '유효하지 않은 아이디',
				text: '이메일 형식의 아이디를 입력하세요.'
			})
			return false;
		}
	}
	else if (chkIdNick == "nickname") {
		if(chkValue.trim() == '' || !(nicknamePtn.test(chkValue))) {
			Swal.fire({
				icon: 'error',
				title: '유효하지 않은 닉네임',
				text: '닉네임 5~30자 이외의 값, 영문자, 숫자외의 값은 입력하실 수 없습니다.'
			})
			return false;
		}
	}
	$.ajax({
		url: 'chk_user.do',
		method: 'post',
		data: JSON.stringify({chkType, chkValue}),
		contentType: 'application/json; charset=UTF-8',
		success: result => {
			console.log(result);
			if (result == 1) {
				Swal.fire({
		    		icon: 'error',
		    		title: '중복되는 이름/닉네임',
		    		text: validateMsg
		    	})
				
				if (chkIdNick == "id") chkUserId = false;
				else if (chkIdNick == "nickname") chkUserNickname = false;
				return;
			}
			Swal.fire({
	    		icon: 'success',
	    		title: '사용 가능',
	    		text: "사용 가능합니다."
	    	})
			if (chkIdNick == "id") chkUserId = true;
			else if (chkIdNick == "nickname") chkUserNickname = true;
			console.log(chkUserId);
		}
	});
}


$('.container .bg').mousemove(function(e){
    var amountMovedX = (e.pageX * -1 / 30);
    var amountMovedY = (e.pageY * -1 / 9);
    $(this).css('background-position', amountMovedX + 'px ' + amountMovedY + 'px');
});


$("#joinDefaultChk").click(() => {
	visibleSelector("#join2", "#join1", "#join3");
	visibleActiveSignup("#activeSignup2", "#activeSignup1", "#activeSignup3");
});
/*
$("#joinEmailChk").click(() => {
	visibleSelector("#join3", "#join1", "#join2");
	visibleActiveSignup("#activeSignup3", "#activeSignup2", "#activeSignup1");
});

*/
	// 전 화면 클릭시 돌아가기 구현 - 주석 해제 후 사용

$("#activeSignup1").click(() => {
	visibleSelector("#join1", "#join2", "#join3");
	visibleActiveSignup("#activeSignup1", "#activeSignup2", "#activeSignup3");
});
$("#activeSignup2").click(() => {
	visibleSelector("#join2", "#join1", "#join3");
	visibleActiveSignup("#activeSignup2", "#activeSignup1", "#activeSignup3");
});
/*
$("#activeSignup3").click(() => {
	visibleSelector("#join3", "#join1", "#join2");
	visibleActiveSignup("#activeSignup3", "#activeSignup2", "#activeSignup1");
});
*/

$("#userPass").keyup( passChk );
$("#userPassRetry").keyup( passChk );


$("#userId").keyup( () => chkUserId = false );
$("#userNickname").keyup( () => chkUserNickname = false );

function passChk() {
	  let password = $("#userPass").val();
	  let passwordRetry = $("#userPassRetry").val();
	  if(password.length != 0 || passwordRetry.length != 0) {
		  if(password != passwordRetry) {
			  $("#passChkVal").css("color", "red");
			  $("#passChkVal").html("&nbsp;&nbsp;* 비밀번호 불일치");
			  passFlag = false;  
		  } else {
			  $("#passChkVal").css("color", "black");
			  $("#passChkVal").html("&nbsp;&nbsp;* 비밀번호 일치");
			  passFlag = true;
		  }  
	  }
}


$("#crForm").submit(() => {
	let obj = document.fr;
    if(!(idPtn.test(obj.userId.value))) {
    	Swal.fire({
    		  icon: 'error',
    		  title: '필수 사항 미입력',
    		  text: '이메일 형식의 아이디를 입력하세요.',
    	})
        return false;
    }
    
    if(!(passPtn.test(obj.userPass.value))) {
    	Swal.fire({
    		  icon: 'error',
    		  title: '필수 사항 미입력',
    		  text: '영문자, 숫자, 특수문자를 포함한 10~20자내의 비밀번호를 입력하세요.',
      	})
        return false;
    }
    
    if(!(passPtn.test(obj.userPassRetry.value))) {
    	Swal.fire({
  		  icon: 'error',
  		  title: '필수 사항 미입력',
  		  text: '영문자, 숫자, 특수문자를 포함한 10~20자내의 비밀번호를 입력하세요.',
    	})
        return false;
    }
    
    
    
    if(!(nicknamePtn.test(obj.userNickname.value))) {
    	Swal.fire({
    		icon: 'error',
    		title: '필수 사항 미입력',
    		text: '닉네임 5~30자 이외의 값, 영문자, 숫자외의 값은 입력하실 수 없습니다.',
    	})
    	return false;
    }
    
    if(!(namePtn.test(obj.userName.value))) {
    	Swal.fire({
  		  icon: 'error',
  		  title: '필수 사항 미입력',
  		  text: '이름 2~6자 이외의 값, 한글이외의 값은 입력하실 수 없습니다.',
    	})
        return false;
    }
    
    
    if(!chkUserId) {
    	Swal.fire({
    		  icon: 'error',
    		  title: '중복 체크 확인',
    		  text: '아이디 중복 체크 후 진행해 주세요.'
      	})
        return false;
    }
    
    if(!chkUserNickname) {
    	Swal.fire({
    		icon: 'error',
    		title: '중복 체크 확인',
    		text: '닉네임 중복 체크 후 진행해 주세요.'
    	})
    	return false;
    }
    
    if(!passFlag) {
    	Swal.fire({
    		icon: 'error',
    		title: '비밀번호 확인',
    		text: '비밀번호가 일치하는지 확인 후 진행해 주세요.'
    	})
    	return false;
    }

    let categoryList = $("input:checkbox[name=categoryList]:checked");
    if (categoryList.length < 3) {
    	Swal.fire({
    		icon: 'error',
    		title: '카테고리 확인',
    		text: '최소 관심 카테고리 3개 이상의 값을 체크 후 진행해 주세요.'
    	})
    	return false;
    }
    let categoryResult = "";
    categoryResult += $(categoryList).eq(0).val();
    
    for (let i = 1; i < categoryList.length; i++) {
    	categoryResult += (":" + $(categoryList).eq(i).val());
    }
    

    let objList = {userId : obj.userId.value, userPass : obj.userPass.value, 
    			   userNickname : obj.userNickname.value, userName : obj.userName.value,
    			   categoryList : categoryResult };
	$.ajax({
		url: "user_regist.do",
		data: objList,
		success: result => {
			// 작업해야 할 것 : 앞에 화면으로 돌아가는 거 막기, Controller에서 db insert(auth도 같이 insert - transaction)
			visibleSelector("#join3", "#join1", "#join2");
			visibleActiveSignup("#activeSignup3", "#activeSignup2", "#activeSignup1");
		}
	});
	
	
    return false;
	
	
});






function visibleActiveSignup(visibleView, ...selVal) {
	$(visibleView).addClass("active");
	for (sel of selVal) {
		$(sel).removeClass("active");
	}
}


function visibleSelector(visibleView, ...selVal) {
	$(visibleView).css("display", "block");
	for (sel of selVal) {
		$(sel).css("display", "none");
	}
}


$("#closeModal").click(() => {
	$(".modal").css("display","none");
});

//Swal.fire({
//	  icon: 'error',
//	  title: '필수 사항 미입력',
//	  text: '이메일 형식의 아이디를 입력하세요.',
////	  footer: '<a href>Why do I have this issue?</a>'
//})
// comment
