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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mypage/mypage.css"/>
</head>
<body>
	<sec:authentication property="principal.user" var="u"/>
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
                                <span class="cnt f_cnt" data-type="Following">114</span>
                            </span>
                            <div class="line"></div>
                            <span>
                                Followers
                                <span class="cnt f_cnt" data-type="Followers">314</span>
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

    <!-- 정보 수정 모달창 -->
    <div class="u_mod modal hidden">
        <div class="u_mod_ol modal_overlay"></div>
        <div class="modal_content_container">
            <div class="modal_content">
                <form class="user_setting mod_form" method="post" action="update.do">
                	<input name="userNo" hidden="hidden" value="${u.userNo}">
                	<input name="categoryList" hidden="hidden" value="" />
                    <div>
                        <aside>
                            <button type="button" class="img_btn">
                                <img src="./../images/test_user.jpg">
                            </button>
                        </aside>
                        <div>
                            <h1>${u.userNickname}</h1>
                            <p class="img_info">* 프로필 사진을 변경하기 위해 사진을 클릭하세요.</p>
                            <input type="file" style="display: none;">
                        </div>
                    </div>
                    <div>
                        <aside>닉네임</aside>
                        <div>
                            <input class="si" type="text" name="userNickname" value="${u.userNickname}">
                        </div>
                    </div>
                    <div>
                        <aside>이름</aside>
                        <div>
                            <input class="si" type="text" name="userName" value="${u.userName}">
                        </div>
                    </div>
                    <div>
                        <aside>카테고리</aside>
                        <div class="cc">
                        	<c:forEach var="c" items="${categories}">
	                           <button type="button" class="cb" data-no="${c.categoryNo}">
	                               	${c.categoryTitle}<i class="cb_i fas fa-plus"></i>
	                           </button>
                        	</c:forEach>
                        </div>
                    </div>
                    <div>
                        <aside>
                            <button class="change_pass" type="button">
                                	비밀번호 변경
                                <i class="fas fa-chevron-down"></i>
                            </button>
                        </aside>
                    </div>
                    <!-- 비밀번호 변경 -->
                    <div class="pw" data-pw="off"></div>
                    <button class="profile_sub">제출</button>
                </form>
            </div>
        </div>
    </div>

    <!-- 프로젝트 추가 모달창 -->
    <div class="proj_mod modal hidden">
        <div class="proj_mod_ol modal_overlay"></div>
        <div class="modal_content_container">
            <div class="proj_mod_con modal_content">
                <h2 class="proj_name mod_name"></h2>
                <form class="add_proj_form mod_form">
                    <input name="projectType" hidden="hidden"/>
                    <div>
                        <aside>프로젝트 썸네일</aside>
                        <div>
                            <input type="file" name="projectFile">
                        </div>
                    </div>
                    <div>
                        <aside>프로젝트 타이틀</aside>
                        <div>
                            <input type="text" name="projectTitle">
                        </div>
                    </div>
                    <div>
                        <aside>비밀 프로젝트</aside>
                        <div>
                            <input type="checkbox" name="projectPublicEnabled" value="Y">
                        </div>
                    </div>
                </form>
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
        $(document).ready(function() {
            $("header").load("./header.html");
        });

        $('.myprojectSlide').slick({
            slidesToShow: 1,
            slidesToScroll: 1,
        });
    </script>
    
    <script>
//     	$(".profile_sub").click((e)=>{
// 		    e.preventDefault();
//     		Swal.fire({
// 				  icon: 'error',
// 				  title: '닉네임 중복',
// 				  text: '이미 사용중인 닉네임입니다.',
// 		  	})    		
//     	})
    </script>
    <script>
    	const cList = ${u.categoryList};
    </script>
      
    <!-- 유저 수정 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage/usermod.js"></script>

    <!-- 프로젝트 추가 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage/projmod.js"></script>

    <!-- 팔로잉 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage/followmod.js"></script>

</body>
</html>