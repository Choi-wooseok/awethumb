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
    <!-- include header -->
    <header>
    	<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
    </header>

    <section id="mypage">
        <div class="content">
            <div class="userWrap">
                <div>
                    <div class="userImg">
                        <img src="./../images/test_user.jpg" alt="">
                        <div class="imgHover"></div>
                    </div>
                    <div class="userInfo">
                        <div style="text-align: right; margin: 0px;">
                            <i class="fas fa-user-cog u_modal_btn"></i>
                        </div>
                        <h1>${u.userNickname}</h1>
                        <p>${u.userName}</p>
                        <div>
                            <span>
                            	Project
                                <span class="cnt">23</span>
                            </span>
                            <div class="line"></div>
                            <span>
                                Following
                                <span class="cnt f_cnt" data-type="Following">${u.followingCnt}</span>
                            </span>
                            <div class="line"></div>
                            <span>
                                Followers
                                <span class="cnt f_cnt" data-type="Followers">${u.followerCnt}</span>
                            </span>
                        </div>
                        <div>
                            <button class="sub_btn" type="button">
                                   	 구독
                                    <i class="fas fa-plus"></i>
                            </button>
                            <button class="unsub_btn" type="button">
                                                                                 구독중
                                    <i class="fas fa-check"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <div class="myprojectWrap">
        <div class="myprojectSlide">
                
            <div class="myprojectContWrap">
                <!-- Ajax -->
                <!-- 슬라이드 버튼 누를 시 해당 글자로 변경 -->
                <div class="mpjinfo">
                    <span>Progress Project</span>
                    <!-- <span>Shared Project</span> -->
                    <!-- <span>Saved Project</span> -->
                    <div class="add_proj " >
                        <div>추가</div>
                        <i class="fas fa-plus proj_mod_btn" data-proj-type="1"></i>
                    </div>
                </div>
                <p>진행중인 프로젝트</p>
                <div class="mpjcont">
                    <div class="mpjlist">
                        <div>
                            <div class="bg_gray">
                                <div>
                                    <p>Project Name</p>
                                    <span>
                                        <i class="fas fa-project-diagram"></i>
                                        20
                                    </span>
                                </div>
                            </div>
                            <img src="./../images/test_img1.jpg" alt="">
                        </div>
                    </div>
                </div>
            </div>

            <div class="myprojectContWrap">
                <div class="mpjinfo">
                    <span>Shared Project</span>
                    <div class="add_proj ">
                        <div>추가</div>
                        <i class="fas fa-plus proj_mod_btn" data-proj-type="2"></i>
                    </div>
                </div>
                <p>공유중인 프로젝트</p>
                
            </div>

            <div class="myprojectContWrap">
                <div class="mpjinfo">
                    <span>Saved Project</span>
                    <div class="add_proj" >
                        <div>추가</div>
                        <i class="fas fa-plus  proj_mod_btn" data-proj-type="3"></i>
                    </div>
                </div>
                <p>저장한 프로젝트</p>
                
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
                    <div>
                        <ul>
                            <li>
                                <div class="f_mod_img_con">
                                    <img src="./../images/test_user.jpg" class="f_mod_img"/>
                                </div>
                                <div class="f_mod_user_con">
                                    <a class="f_user_nick">following</a>
                                    <div class="f_user_name">name</div>
                                </div>
                                <div class="f_mod_btn_con">
                                    <button class="sub_btn" type="button">
                                            	구독
                                            <i class="fas fa-plus"></i>
                                    </button>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="follower_container">
                    <div class="f_mod_names">
                        <h2 class="follower_following_btn">Following</h2>
                        <h2 style="border-bottom-color: #000000;">Follower</h2>
                    </div>
                    <div>
                        <ul>
                            <li>
                                <div class="f_mod_img_con">
                                    <img src="./../images/test_user.jpg" class="f_mod_img"/>
                                </div>
                                <div class="f_mod_user_con">
                                    <a class="f_user_nick">follower</a>
                                    <div class="f_user_name">name</div>
                                </div>
                                <div class="f_mod_btn_con">
                                    <button class="sub_btn" type="button">
                                           	 구독
                                            <i class="fas fa-plus"></i>
                                    </button>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <!-- 슬라이드 -->
    <script>
        $('.myprojectSlide').slick({
            slidesToShow: 1,
            slidesToScroll: 1,
        });
    </script>
    
      
    <!-- 팔로잉 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/followmod.js"></script>

</body>
</html>