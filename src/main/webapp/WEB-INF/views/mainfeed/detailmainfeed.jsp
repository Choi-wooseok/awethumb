<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<%@ include file="/WEB-INF/views/include/cssScript.jsp"%>
<title>Document</title>

<link rel="stylesheet" href="./../css/reset.css">
<link rel="stylesheet" type="text/css"
	media="screen and (min-width:1281px)" href="./../css/pc.css">
<link rel="stylesheet" type="text/css"
	media="screen and (min-width:768px) and (max-width:1280px)"
	href="/css/tablet.css">
<link rel="stylesheet" type="text/css"
	media="screen and (max-width:767px)" href="/css/mobile.css">
<link rel="stylesheet" href="./../css/common.css">

<script src="https://kit.fontawesome.com/95c297fe6b.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
	<button class="modal_btn">Open Modal</button>

	<!-- 모달창 -->
    <div class="modal hidden">
        <div class="modal_overlay"></div>
        <div class="modal_content_container">
            <i class="fas fa-caret-left arw-btn"></i>
            <div class="modal_content">
                <div id="boxSize">
                    <img id="image" src="./../images/test_img3.jpg" alt="">
                </div>
                <div id="rightBox">
                    <div class="ModaluserInfo">
                        <div class="commentUser">
                            <img src="./../images/test_user.jpg" alt="">
                        </div>
                        <div class="userName">
                            <a href="#">userName</a>
                        </div>
                    </div>
                    <div class="comment">


                        <div class="commentList">
                            <div class="commentUserImg">
                                <img src="./../images/test_user.jpg" alt="">
                            </div>
                            <div class="commentWrap">
                               가나다라마바사아자차카타파하
                            </div>
                        </div>

                        <div class="commentList">
                                <div class="commentUserImg">
                                    <img src="./../images/test_user.jpg" alt="">
                                </div>
                                <div class="commentWrap">
                                    가나다라마바사아자차카타파하가나다라마바사아자차카타파하
                                </div>
                            </div>
                    </div>

                    <div class="insertComment">
                        <input type="text" />
                        <button>등록</button>
                    </div>
                </div>
            </div>
            <i class="fas fa-caret-right arw-btn"></i>
        </div>
    </div>

    <script>
        let maxSize = 600;
        let image = document.getElementById("image");
        let boxSize = document.getElementById("boxSize");
        let imgHeight = image.height;
        let imgWidth = image.width;
        let rightBox = document.getElementById("rightBox");
        $(document).ready(function() {
            if (imgWidth > maxSize && imgHeight > maxSize) {
                if (imgWidth > imgHeight) {
                    boxSize.style.width = maxSize+"px";
                    boxSize.style.height = "auto";
                    image.style.width = "100%";
                } else {
                    boxSize.style.width = "auto";
                    boxSize.style.height = maxSize+"px";
                    image.style.height = "100%";                }
            } else if (imgWidth > maxSize && imgHeight < maxSize) {
                boxSize.style.width = maxSize+"px";
                image.style.width =  "100%";
            } else if (imgWidth < maxSize && imgHeight > maxSize) {
                box.Size.style.height = maxSize+"px";
                image.style.height = "100%";
            }
            rightBox.style.height = boxSize.style.height;
        })

        const modal = document.querySelector(".modal");
        const mBtn = document.querySelector(".modal_btn");
        // 모달창 클래스 토글 기능
        function hideModal() {
            modal.classList.toggle("hidden");
        }
        // 취소 버튼 클릭시 모달창 닫힘
        mBtn.addEventListener("click", hideModal)
        // 모달창 밖에 클릭시 모달창 닫힘
        document.querySelector(".modal_overlay").addEventListener("click", hideModal)

        // 모달창이 띄워졌을 시 스크롤 방지
        $(".modal").on('scroll touchmove mousewheel', function(event) {
            event.preventDefault();
            event.stopPropagation();
            return false;
        });


    </script>
</body>
</html>