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
<!-- favicon -->
<link rel="shortcut icon" href="../images/favicon/favicon.ico">
<!-- Basic Css -->
<link rel="stylesheet" href="./../css/reset.css">
<link rel="stylesheet" type="text/css"
	media="screen and (min-width:1281px)" href="./../css/pc.css">
<!-- <link rel="stylesheet" type="text/css" media="screen and (min-width:768px) and (max-width:1280px)" href="/css/tablet.css"> -->
<!-- <link rel="stylesheet" type="text/css" media="screen and (max-width:767px)" href="/css/mobile.css"> -->
<link rel="stylesheet" href="./../css/common.css">
<link rel="stylesheet" href="./../css/mainfeed.css">

<script type="text/javascript" src="../js/jquery.js"></script>
<script src="https://kit.fontawesome.com/95c297fe6b.js"
	crossorigin="anonymous"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->

</head>
<body>
<%-- 	<%@ include file="/WEB-INF/views/include/header.jsp"%> --%>
<!-- 	<button class="modal_btn">Open Modal</button> -->

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
                            <a href="#">${detailList.userNickname}</a>
                        	<!-- Trigger/Open The Modal -->
                            <button id="myBoard"><i class="fas fa-bars"></i></button>
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
	<!-- board-->
    <!-- The Modal -->
    <div id="modalBoard" class="board">

        <!-- Modal content -->
        <div class="board-modal">
            <h4> <button id="report" type="button" onclick="doReport()">신 고<i class="fas fa-angry"></i></button></h4>
            <h4> <a href="bb.html"><button id="share">퍼가기</button></a></h4>
            <h4 class="boardClose">취 소 </h4>
        </div>

    </div>
    <script>

    </script>
    <script>let postNo = ${detailList.postNo};</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/MainFeed.js"></script>
</body>
</html>