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
// 	$("input[name=date-selector]").val(new Date().toISOString().substring(0, 10));
  	
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
		//페이지네이션 만드는 작업.
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
/*페이징 관련 함수*/

function paging(idx){
    page = idx;    
	Criteria["page"] = page;
    Criteria["perPageNum"] = perPageNum; /* 나중에 셀렉박스.val로 바꾸셈 */
    userListAjax();
    }
