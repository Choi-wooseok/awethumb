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
})

let chkUserId = false;
let chkUserNickname = false;

$("#modalOpen").click(() => {
	$(".modal").css("display","block");
});
$("#modalClose").click(() => {
	$(".modal").css("display","none");
});

$("#userIdChk").click(() => {
	chkUser('userId', $("#userId").val(), "이미 등록된 유저 아이디 입니다.", chkUserId);
});

$("#userNicknameChk").click(() => {
	chkUser('userNickname', $("#userNickname").val(), "이미 등록된 유저 닉네임 입니다.", chkUserNickname);
});

function chkUser(chkType, chkValue, validateMsg, flag) {
	$.ajax({
		url: 'chk_user.do',
		method: 'post',
		data: JSON.stringify({chkType, chkValue}),
		contentType: 'application/json; charset=UTF-8',
		success: result => {
			console.log(result);
			if (result == 1) {
				alert(validateMsg);
				flag = false;
				return;
			}
			alert("사용 가능합니다.");
			flag = true;
			console.log(chkUserId);
		}
	});
}


$('.tabs .tab').click(function(){
    if ($(this).hasClass('signin')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signin-cont').show();
    } 
    if ($(this).hasClass('signup')) {
        $('.tabs .tab').removeClass('active');
        $(this).addClass('active');
        $('.cont').hide();
        $('.signup-cont').show();
    }
});
$('.container .bg').mousemove(function(e){
    var amountMovedX = (e.pageX * -1 / 30);
    var amountMovedY = (e.pageY * -1 / 9);
    $(this).css('background-position', amountMovedX + 'px ' + amountMovedY + 'px');
});




// comment
