<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <%@ include file="/WEB-INF/views/include/cssScript.jsp"%>  
        
    <!-- grid Css -->
    <link rel="stylesheet" href="<c:url value='/js/grid/gridstack.css' /> ">

    <!-- summernote Plugin -->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>


</head>
<body>
    <!-- include header -->
    <header></header>

    <section>
        <div class="bgWrap">
            <!-- <img src="<c:url value='/images/main_bg.jpg' />" alt="#"> -->
            <img src="./../images/main_bg.jpg" alt="#">
        </div>
        <div class="content">
            <div class="options">
                <div class="layout_left">
                    <span class="pjtTitle">Project Name</span>
                    <button id="updateBtn">
                    	<i class="fas fa-user-cog"></i>
                    </button>
                </div>
                <div class="layout_right">
                    <button id="insertBtn">
                        <i class="fas fa-plus"></i>
                       	등록
                    </button>
                </div>
            </div>
            <div class="grid-stack" data-gs-width="12">
                <div class="grid-stack-item" data-gs-x="0" data-gs-locked data-gs-y="0" data-gs-width="2" data-gs-height="2">
                    <div class="grid-stack-item-content">
                        <button class="optionModalBtn" type="button">
                            <i class="fas fa-ellipsis-h"></i>
                        </button>
                    	content
                    </div>
                </div>

                <div class="grid-stack-item" data-gs-x="4" data-gs-y="0" data-gs-width="4" data-gs-height="4">
                    <div class="grid-stack-item-content">
                        <button class="optionModalBtn" type="button">
                            <i class="fas fa-ellipsis-h"></i>
                        </button>
                        content
                    </div>
                </div>
                <!-- 
                <div class="grid-stack-item" data-gs-x="8" data-gs-y="0" data-gs-width="2" data-gs-height="2">
                    <div class="grid-stack-item-content">content2</div>
                </div>
                <div class="grid-stack-item" data-gs-x="10" data-gs-y="0" data-gs-width="2" data-gs-height="2">
                    <div class="grid-stack-item-content">content3</div>
                </div>
                <div class="grid-stack-item" data-gs-x="0" data-gs-y="4" data-gs-width="2" data-gs-height="2">
                    <div class="grid-stack-item-content">content4 </div>
                </div>
                <div class="grid-stack-item" data-gs-x="2" data-gs-y="4" data-gs-width="2" data-gs-height="4">
                    <div class="grid-stack-item-content">content5</div>
                </div>
                <div class="grid-stack-item" data-gs-x="8" data-gs-y="4" data-gs-width="4" data-gs-height="2">
                    <div class="grid-stack-item-content">content6</div>
                </div>
                <div class="grid-stack-item" data-gs-x="0" data-gs-y="6" data-gs-width="2" data-gs-height="2">
                    <div class="grid-stack-item-content">content7</div>
                </div>
                <div class="grid-stack-item" data-gs-x="4" data-gs-y="6" data-gs-width="4" data-gs-height="2">
                    <div class="grid-stack-item-content">content8</div>
                </div>
                <div class="grid-stack-item" data-gs-x="8" data-gs-y="6" data-gs-width="2" data-gs-height="2">
                    <div class="grid-stack-item-content">content9</div>
                </div>
                <div class="grid-stack-item" data-gs-x="10" data-gs-y="6" data-gs-width="2" data-gs-height="2">
                    <div class="grid-stack-item-content">content10</div>
                </div> -->
            </div>     
        </div>
    </section>

    <div class="modalInsertWrap">
        <form action="">
            <div class="insertFormWrap">
                <div class="insertTitle">
                    글 등록하기
                    <button id="closeBtn" type="button">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
    
                <div class="insertCont">
                    
                    <div>
                        <div class="cTitle">
                            <label for="title">제목</label>
                            <input id="title" type="text">
                        </div>
                    </div>


                    <div id="summernote"></div>
                    
    
                </div>
                
                <div class="btnWrap">
                    <button id="okBtn" type="submit">등록</button>
                </div>
            </div>
        </form>
    </div>

    <div class="optionModalWrap optionU">
        <div>
            <div class="report">
                부적절한 컨텐츠 신고
            </div>
            <div class="followcancel">
                팔로우 취소
            </div>
            <div class="saved">
                게시물 저장
            </div>
            <div class="cancel">
                취소
            </div>
        </div>
    </div>

    <div class="optionModalWrap optionF">
        <div>
            <div>글 삭제</div>
            <div>글 수정</div>
            <div class="cancel">취소</div>
        </div>
    </div>
                
    <!-- grid Js -->
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.7.0/underscore-min.js"></script>
    <script src="./../js/grid/gridstack.all.js"></script>
    <script>
        $(document).ready(function() {
            $("header").load("./header.html");

            $('#summernote').summernote({
                height: 300,
                minHeight: null,
                maxHeight: null,
                focus: true
            });
        });
        
        $(function () {
            $('.grid-stack').gridstack({
                animate: true,
                width: 12,
                // vertical gap size
                // side gap size - gridstack.css 36 / left, right
                vertical_margin: 10,
                // grid move delay
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

        let modalWrap = document.querySelector(".modalInsertWrap");
        let mBtn = document.querySelector("#insertBtn");
        let close = document.querySelector("#closeBtn");

        mBtn.addEventListener("click", () => {
            modalWrap.classList.toggle("block");
        });

        close.addEventListener("click", () => {
            modalWrap.classList.toggle("block");
        });

        // 모달창이 띄어졌을 시 스크롤 방지
        $(".modalInsertWrap").on('scroll touchmove mousewheel', function(event) {
            event.preventDefault();
            event.stopPropagation();
            return false;
        });

        $(".optionModalBtn").click(() => {
            $(".optionF").addClass("block");
        })
        $(".cancel").click(() => {
            $(".optionF").removeClass("block");
        })      
    </script>

</body>
</html>

</body>
</html>