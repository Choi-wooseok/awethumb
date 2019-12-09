 /* 정지,취소,승인등을 처리하기 위해 전역변수 설정 */
  let userNo;
  let page = 1;
  let perPageNum = 10;
  let list;
  let pageMaker;
  let Criteria = { "page" : page, "perPageNum" : perPageNum };
  
 	function userListAjax() {
		$.ajax({
			url: "userListAjaxPaging.do",
        	method : 'GET',
            data : Criteria,
			success: function(result){
				list = result.uList;
        		pageMaker = result.pageMaker;
        		console.log('list :', list, 'pageMaker :', pageMaker);
            	makeUserList(list, pageMaker);
			}
		});
	}
  /* 로딩시 실행되는 스크립트 */
  $(() => {
	// 로딩시 목록 호출
	userListAjax();
	
	// 로딩시 정지셀렉터에 현재 날짜 자동 부여
// $("input[name=date-selector]").val(new Date().toISOString().substring(0,
// 10));
  	
  })
  
  function toPad(val) {
		return val < 10 ? "0" + val : val;
  }
  
  function makeUserList(list, pageMaker) {
		let $tbr = $("<tbody></tbody>");
		$.each(list, (i, u) => {
			if(u.block != null){
			var date = new Date(u.block.blockEndDt);
			var time = date.getFullYear() + "-" 
			         + (date.getMonth() + 1) + "-" 
			         + date.getDate() + " "
			         + toPad(date.getHours()) + ":"
			         + toPad(date.getMinutes()) + ":"
			         + toPad(date.getSeconds());
			} else {
				var time = "";
			}
			$tbr.append( 
			    `<tr id="row${u.userNo}" class="row">
			   		<td class="col-md-1">${u.userNo} </td>
					<td class="col-md-2">${u.userId} </td>
					<td class="col-md-3">${u.blockEnabled} </td>
					<td class="col-md-3">${time} </td>
					<td class="row col-md-3"><button type="button" class="w3-btn btn-detail-dc w3-round-medium" id="detail${u.userNo}" style="background-color:#6dd5bc; color: white;
	        	    font-weight: 500;">정지해제/연장/강제탈퇴
	        	    </td>
	          </button>
			    </tr>`);
		});
		// 페이지네이션 만드는 작업.
		let $pag = $("<div></div>").addClass("w3-bar").addClass("pagination-dc");
		console.log('리스트', list);
		console.log('페이지메이커', pageMaker);
		if(pageMaker.prev){
			$pag.append(`<a href="javascript:paging(${pageMaker.startPage}-1);" class="w3-button">&laquo;</a>`);
		}
		for(let i = pageMaker.startPage; i <= pageMaker.endPage; i++){
			$pag.append(`<a class="w3-button" href="javascript:paging(${i});" data-page="${i}">${i}</a>`);
		}
		if(pageMaker.next && pageMaker.endPage > 0){
			$pag.append(`<a class="w3-button" href="javascript:paging(${pageMaker.endPage}+1);">&raquo;</a>`);
		}
		<!-- 페이지네이션, 페이징, html부분 -->
		$("#tbo > tbody").remove();
		$("#tbo").append($tbr);
		$(".pagination-dc").remove();
		$("#tbo").after($pag);
		console.log('현재페이지', pageMaker.cri.page);
		
		let page = $('#main-content > section > div.w3-bar.pagination-dc > a')
		
		for (let p of page) {
			if($(p).data('page') == pageMaker.cri.page) {
				$(p).addClass("w3-green");
			}
		}	
	}
/* 페이징 관련 함수 */

function paging(idx){
    page = idx;    
	Criteria["page"] = page;
    Criteria["perPageNum"] = perPageNum; /* 나중에 셀렉박스.val로 바꾸셈 */
    userListAjax();
    }

/* 상세보기 관련 */

$(document).on("click", ".btn-detail-dc", function() {
// console.log("ㄲㅈ");
  reply_click($(this).attr("id"));
  
});
function reply_click(id){
	userNo = id.slice(6);
    view_detail(userNo);
}

function view_detail(userNo){
  $("#detail-user").css("display", "block");	
  console.log('뷰디테일에서', userNo);
  userManageAjax(userNo);
}

function userManageAjax(userNo){
	  console.log('에이젝스 시작전', userNo);
		$.ajax({
			url: "userManageAjax.do",
			type: "GET",
			data: {userNo: userNo},
			dataType: "json",
			success: function(result){
				if(result.block != null){
					let someTimestamp = Number(`${result.block.blockEndDt}`);
					let endDt = new Date(someTimestamp);
					let Dt = endDt.toLocaleDateString();
					
					console.log('리절트.블럭.블락데이트 : ',`${result.block.blockEndDt}`);
					console.log(typeof(`${result.block.blockEndDt}`));
					console.log('someTimestamp : ', someTimestamp);
					console.log(typeof(someTimestamp));
					
					console.log('endDt : ',endDt);
					console.log(typeof(endDt));
					
					console.log('Dt : ', Dt);
					console.log(typeof(Dt));
					$("#Detail").html(
							`
							<h4>회원 아이디 : ${result.user.userId}</h4>
							<h4>회원 닉네임 : ${result.user.userNickname}</h4>
							<h4>정지상태 : 정지중(${Dt}까지)</h4>
							`
							);
					$("#unlockAndBlock").html(
						`<button type="button" class="w3-btn w3-red w3-small" id="cancel-block">정지해제</button>`);
					
				}
				else {
					$("#Detail").html(
							`
							<h4>회원 아이디 : ${result.user.userId}</h4>
							<h4>회원 닉네임 : ${result.user.userNickname}</h4>
							<h4>정지상태 : </h4>
							`
					);
					$("#unlockAndBlock").html(
					`<button type="button" class="w3-btn w3-red w3-small" id="force-block">정지하기</button>`);
				}
				
			}
			});
	}

/* 회원 정지처리 관련 */
// 정지를 업데이트시키기위해서는 블락넘버와 블락기간이 필요한데, 에이젝스로 블락넘버를 보내야 한다.
// 단순히 userNo만 가지고는 blockNo를 받아올수가 없다.
// 아니면 애초에 정지처리가 들어갈때 user가 정지되어 있다고 전제하고 보냈으니
// 유저넘버만 보내서 그 유저넘버와 관련된 블락테이블의 기간만 바꿔줘도 큰 지장은 없을 것이다.
$(document).on('click', "#update-block", function(){
	console.log($("input[name=date-selector]").val());
	let param = {
		blockDate : $("input[name=date-selector]").val(),
		userNo: userNo	
	}
	$.ajax({
		url: "updateBlock.do",
		type: "POST",
		data: JSON.stringify(param),
		dataType: "json",
		contentType: "application/json",
		success: function(result){
			alert('정지기간 수정 처리 완료되었습니다.');
			userListAjax();
		}
		
	});
})


