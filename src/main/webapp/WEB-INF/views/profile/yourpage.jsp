<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

	<c:import url="/WEB-INF/views/include/cssScript.jsp"></c:import>
	
    <!-- slide plugin -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/slide/slick.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/slide/slick-theme.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/slide/slick.js"></script>
    
    <!-- webfont -->
    <link href="https://fonts.googleapis.com/css?family=Passion+One&display=swap" rel="stylesheet">

	<!-- mypage.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile/basic.css"/>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
    <!-- include header -->
    <header>
    	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
    </header>

    <section id="mypage">
        <div class="content">
            <div class="userWrap">
                <div>
                    <div class="userImg">
                        <img id="main_profile_img" src="" alt="">
                    </div>
                    <div class="userInfo">
                        <div style="text-align: right; margin: 0px;"></div>
                        <h1>${u.userNickname}</h1>
                        <p>${u.userName}</p>
                        <div>
                            <span>
                            	Project
                                <span class="cnt">${projectCnt}</span>
                            </span>
                            <div class="line"></div>
                            <span>
                                Following
                                <span class="cnt f_cnt" data-type="Following">${u.followingCnt}</span>
                            </span>
                            <div class="line"></div>
                            <span>
                                Followers
                                <span class="cnt f_cnt follower_cnt" data-type="Followers">${u.followerCnt}</span>
                            </span>
                        </div>
                        <div class="sub_btn_container">
                        	<button class="unsub_btn sBtn myHidden" type="button">
                       			구독중
                    			<i class="fas fa-check"></i>
                    		</button>
                    		<button class="sub_btn sBtn" type="button">
                                                                        구독
                    			<i class="fas fa-plus"></i>
                    		</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <div class="myprojectWrap">
        <div class="myprojectSlide">
                
            <div class="myprojectContWrap" id="slide-0">
                <div class="mpjinfo">
                    <span>Progress Project</span>
                </div>
                <p>진행중인 프로젝트</p>
                <div class="mpjcont progress-project-cont">
                    
                </div>
            </div>

            <div class="myprojectContWrap" id="slide-1">
                <div class="mpjinfo">
                    <span>Shared Project</span>
                </div>
                <p>공유중인 프로젝트</p>
                <div class="mpjcont shared-project-cont">
                </div>
            </div>

            <div class="myprojectContWrap" id="slide-2">
                <div class="mpjinfo">
                    <span>Saved Project</span>
                </div>
                <p>저장한 프로젝트</p>
                <div class="mpjcont saved-project-cont">
                </div>
            </div>
        </div>
    </div>

    <!-- 팔로잉 모달창 -->
    <div class="f_mod modal hidden">
        <div class="f_mod_ol modal_overlay"></div>
        <div class="modal_content_container">
            <div class="f_mod_con modal_content">
                <div class="following_container">
                    <div class="f_mod_names">
                        <h2 style="border-bottom-color: #000000;">Following</h2>
                        <h2 class="following_follower_btn">Follower</h2>
                    </div>
                    <div class="f_srch_con">
                    	<input class="f_srch following_srch" type="text" placeholder="유저 검색">
                    </div>
                    <div class="f_list_container following_list_con">
                        <ul class="following_ul">
                        </ul>
                        <ul class="srch_following_ul">
                        </ul>
                    </div>
                </div>
                <div class="follower_container">
                    <div class="f_mod_names">
                        <h2 class="follower_following_btn">Following</h2>
                        <h2 style="border-bottom-color: #000000;">Follower</h2>
                    </div>
                    <div class="f_srch_con">
                    	<input class="f_srch follower_srch" type="text" placeholder="유저 검색">
                    </div>
                    <div class="f_list_container follower_list_con">
                        <ul class="follower_ul">
                        </ul>
                        <ul class="srch_follower_ul">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 변수들 -->
    <script>
	    let follwerCnt = ${u.followerCnt};
		const subUserNo = ${su.userNo};
		const userNo = ${u.userNo};
		const contextPath = '<%=request.getContextPath()%>'
    </script>
    
    <!-- 프로필 이미지를 가져오는 스크립트 -->
    <script>
	    function getProfileImgAjax(){
	    	console.log("in")
	    	$.ajax({
	    		url: "getprofileimg.do",
	    		dataType: "text",
	    		data: {userNo},
	    		success: function(data) {$("#main_profile_img").attr("src", data)}
	    	})	
	    }
	    getProfileImgAjax();
	</script>
    
    <script>
		const $sBtn = $(".sBtn");
    	// 구독여부 확인하는 스크립트
    	$.ajax({
    		url: "checksub.do",
    		type: "POST",
			contentType: "application/json",
    		data: JSON.stringify({
    			subUserNo,
    			oppUserNo : userNo
    		})
    	}).done((e) => {
    		if(e == 1) $sBtn.toggleClass("myHidden");
    	})
    	
    	// 구독 취소
    	$(".unsub_btn").click(() => {
    		$.ajax({
        		url: "deletesub.do",
        		type: "POST",
    			contentType: "application/json",
        		data: JSON.stringify({
        			subUserNo,
        			oppUserNo: userNo
        		})
        	}).done(() => {
        		$sBtn.toggleClass("myHidden");
        		$(".follower_cnt").text(--follwerCnt);
        	})
    	})
    	
    	// 구독
    	$(".sub_btn").click(() => {
    		$.ajax({
        		url: "insertsub.do",
        		type: "POST",
    			contentType: "application/json",
        		data: JSON.stringify({
        			subUserNo,
        			oppUserNo: userNo
        		})
        	}).done(() => {
        		$sBtn.toggleClass("myHidden");
        		$(".follower_cnt").text(++follwerCnt);
        	})
    	})
    </script>
    
    <!-- 프로젝트 불러오는 스크립트 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/project.js"></script>
      
    <!-- 팔로잉 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/followmod.js"></script>
    
    <!-- init 함수 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/init.js"></script>
    
</body>
</html>