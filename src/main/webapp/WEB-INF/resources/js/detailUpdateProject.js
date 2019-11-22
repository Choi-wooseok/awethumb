$(function () {
    $('.grid-stack').gridstack({
        animate: true,
        width: 12,
        vertical_margin: 10,
        removeTimeout: 2000,
    });
});

$(".grid-stack-item").resize((e) => {
    let width = $(e.target).width();
    let height = $(e.target).height();

    // 3 x 3 이하일 경우
    if (width <= 320 && height <= 235) {
        $(e.target).addClass('min_layout');
    } else {
        $(e.target).removeClass('min_layout');
    }
});

// 모달창이 띄어졌을 시 스크롤 방지
$(".modalInsertWrap").on('scroll touchmove mousewheel', function(event) {
    event.preventDefault();
    event.stopPropagation();
    return false;
});

// 신고하기 등등 버튼 모달창 띄우기 / 제거하기
$(".optionModalBtn").click((e) => {
	let thisNo = $(e.target).data("msg");
	$(".optionModalWrap").addClass("block");
	$(".updateBoard").attr("data-val", thisNo);	
	$(".deleteBoard").html(
		`<a href="delete.do?postNo=${thisNo}">글 삭제</a>`
	);
})
$(".updatecancel").click(() => {
    $(".optionU").removeClass("block");
})

// 수정클릭 시 글쓰기 창 띄우기 / 제거하기
$(".updateBoard").click((e) => {
	$(".modalInsertWrap").addClass("block");
	let thisNo = $(e.target).data("val");
	$(".updateForm").append(
		`<input type="hidden" name="postNo" value="${thisNo}" />`
	);
})
$("#closeBtn").click(() => {
    $(".modalInsertWrap").removeClass("block");
})

$("#posUpdateBtn").click(() => {
	$(".grid-stack-item").each((i, e) => {
		let posNo = $(e).data("postno");
		let posX = $(e).data("gs-x");
		let posY = $(e).data("gs-y");
		let posW = $(e).data("gs-width");
		let posH = $(e).data("gs-height");
		$.post({
			url: "updateList.do",
			data: {
				postNo: posNo,
				x_coord: posX,
				y_coord: posY,
				width : posW,
				hight : posH
			},
			success: () => {
				window.location.href="/awethumb/detailProject/detailBoardList.do"
			}
		});
	})
	
	
	/*let posX = gridItem.data("gs-x");
	console.log(posX);*/
})
