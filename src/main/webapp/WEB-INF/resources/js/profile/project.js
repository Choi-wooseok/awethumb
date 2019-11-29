// 슬라이드
$('.myprojectSlide').slick({
    slidesToShow: 1,
    slidesToScroll: 1
});

let currentProjectType = 1;

let progressProjCurrPageNo = 0;
let sharedProjCurrPageNo = 0;
let savedProjCurrPageNo = 0;
let progressProjCnt = 0;
let sharedProjCnt = 0;
let savedProjCnt = 0;
const projCountPerPage = 9;
function getProjectsAjax(projectType, currentPageNo){
	$.ajax({
		url: "getprojects.do",
		dataType: "json",
		data: {
			userNo, 
			projectType, 
			currentPageNo
		}
	})
	.done((list) => {
		
		let $cont = null;
		// 불러온 프로젝트 목록에 따라 불러온 페이지수 추가
		switch(projectType){
		case 1: 
			$cont = $(".progress-project-cont");
			progressProjCurrPageNo += projCountPerPage;
			progressProjCnt += list.length
			break;
		case 2: 
			$cont = $(".shared-project-cont");
			sharedProjCurrPageNo += projCountPerPage;
			sharedProjCnt += list.length
			break;
		case 3: 
			$cont = $(".saved-project-cont");
			savedProjCurrPageNo += projCountPerPage;
			savedProjCnt += list.length
			break;
		}
		
		for (let proj of list){
			$cont.append(
				`<div class="mpjlist">
	                <div>
	                    <div class="bg_gray">
	                        <div>
	                            <p>${proj.projectTitle}</p>
	                            <span>
	                                <i class="fas fa-project-diagram"></i>
	                                20
	                            </span>
	                        </div>
	                    </div>
	                    <img id="proj-${proj.projectNo}-thumb" alt="">
	                </div>
	            </div>`)
            // 썸네일 가져오기
            getProjectThumb(proj.projectNo);
		}
		
	})
}

// 최초 1회 실행
getProjectsAjax(1, progressProjCurrPageNo);
getProjectsAjax(2, sharedProjCurrPageNo);
getProjectsAjax(3, savedProjCurrPageNo);

// 프로젝트 썸네일을 가져오는 함수
function getProjectThumb(projectNo) {
	$.ajax({
		url: "getprojectthumb.do",
		dataType: "text",
		data: {
			projectNo
		}
	})
	.done((data) => {
		$(`#proj-${projectNo}-thumb`).attr("src", data)
	})
}

// 어떤 프로젝트가 보여지고 있느냐에 따라 불러오는 프로젝트 타입이 달라짐
$(window).scroll(function() {
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
    	let projCurrPageNo = 0;
    	let projCnt = 0;
    	switch(currentProjectType){
    	case 1: 
    		projCurrPageNo = progressProjCurrPageNo;
    		projCnt = progressProjCnt;
    		break;
    	case 2: 
    		projCurrPageNo = sharedProjCurrPageNo;
    		projCnt = sharedProjCnt;
    		break;
    	case 3: 
    		projCurrPageNo = savedProjCurrPageNo;
    		projCnt = savedProjCnt;
    		break;
    	}
    	
    	if (projCnt % projCountPerPage != 0 || projCnt == 0) return;

    	// 고정되어 있는 높이를 변동해준다.
    	$(".slick-list").height("100%");
    	
    	getProjectsAjax(currentProjectType, projCurrPageNo);
    }
});

// 슬라이드가 움직일 때마다 높이를 바꿔준다.
$('.myprojectSlide').on('beforeChange', function(event, slick, currentSlide, nextSlide){
	$(".slick-list").height($(`#slide-${nextSlide}`).height())
	// 지금 보여지는 프로젝트 타입을 지정해준다
	currentProjectType = nextSlide + 1;
});
