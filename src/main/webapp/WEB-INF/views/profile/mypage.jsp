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
    
    <!-- croppie -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile/croppie.css" />
    <script src="${pageContext.request.contextPath}/js/profile/croppie.js"></script>
    
    <!-- tokeninput -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile/tagify.css"/>
	
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
                        <div id="upload-input-con">
                        	<div><i class="fas fa-camera"></i>  Update image</div>
	                        <input type="file" id="upload-input" accept="image/*" value="Choose a file" onchange="readURL(this); togglePImodal();">
                        </div>
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
                                <span class="cnt">${projectCnt}</span>
                            </span>
                            <div class="line"></div>
                            <span>
                                Following
                                <span class="cnt f_cnt following_count_btn" data-type="Following">${u.followingCnt}</span>
                            </span>
                            <div class="line"></div>
                            <span>
                                Followers
                                <span class="cnt f_cnt follower_count_btn" data-type="Followers">${u.followerCnt}</span>
                            </span>
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
                    <div class="add_proj">
                        <div>추가</div>
                        <i class="fas fa-plus proj_mod_btn" data-proj-type="1"></i>
                    </div>
                </div>
                <p>진행중인 프로젝트</p>
                <div class="mpjcont progress-project-cont">
                    
                </div>
            </div>

            <div class="myprojectContWrap" id="slide-1">
                <div class="mpjinfo">
                    <span>Shared Project</span>
                    <div class="add_proj ">
                        <div>추가</div>
                        <i class="fas fa-plus proj_mod_btn" data-proj-type="2"></i>
                    </div>
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
                            <button type="button" class="profile_img_btn img_btn">
                                <img id="usermod_img" src="">
                            </button>
                        </aside>
                        <div>
                            <h1>${u.userNickname}</h1>
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
	                           <button type="button" class="user_cb cb" data-no="${c.categoryNo}">
	                               	${c.categoryTitle}<i class="user_cb_i cb_i fas fa-plus"></i>
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
                    <button class="profile_sub mod_sub_btn_css">제출</button>
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
                <form class="add_proj_form mod_form" method="post" action="insertproj.do" enctype="multipart/form-data">
                    <input name="userNickname" hidden="hidden" value="${u.userNickname}"/>
                    <input name="userNo" hidden="hidden" value="${u.userNo}"/>
                    <input name="projectType" hidden="hidden"/>
                    <input name="categoryNo" hidden="hidden"/>
                    <input name="projectPublicEnabled" hidden="hidden" value="Y"/>
                    <input name="sharedUserList" hidden="hidden"/>
                    <div>
                        <aside>프로젝트 썸네일</aside>
                        <div>
                            <input type="file" name="projectFile">
                        </div>
                    </div>
                    <div>
                        <aside>프로젝트 제목</aside>
                        <div>
                            <input type="text" name="projectTitle">
                        </div>
                    </div>
                    <div class="shared-user-cont myHidden">
                    	<aside>공유할 유저</aside>
	                    <div class="tokenInput_wrapper">
							<input type="text" class="tokenInput">
	                    </div>
                    </div>
                    <div>
                        <aside>비밀 프로젝트</aside>
                        <div>
                            <input type="checkbox" id="private_check">
                        </div>
                    </div>
                    <div>
                    	<aside>카테고리</aside>
	                    <div class="cc">
		                   	<c:forEach var="c" items="${categories}">
		                       <button type="button" class="proj_cb cb" data-no="${c.categoryNo}">
		                           	${c.categoryTitle}<i class="proj_cb_i cb_i fas fa-plus"></i>
		                       </button>
		                   	</c:forEach>
	                    </div>
                    </div>
                    
                    <button class="mod_sub_btn_css proj_mod_sub_btn">제출</button>
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
    
    <!-- 프로필 이미지 변경 모달 -->
    <div class="pi_mod modal hidden">
        <div class="pi_mod_ol modal_overlay"></div>
        <div class="modal_content_container">
            <div class="pi_mod_con modal_content">
               	<div class="upload-wrap">
		            <div id="upload"></div>
		        </div>
		        <div class="pi_mod_btn_con">
			        <button type="button" onclick="togglePImodal();">cancel</button>
			        <button type="button" id="upload-result">update</button>
		        </div>
            </div>
        </div>
    </div>

    
    <script>
    	let followingCnt = ${u.followingCnt};
    	const cList = "${u.categoryList}";
    	const userNo = ${u.userNo};
    	const subUserNo = ${su.userNo};
    	const contextPath = '<%=request.getContextPath()%>'
    </script>
    
    <!-- 공유유저 추가 스크립트 -->
    <script src="${pageContext.request.contextPath}/js/profile/tagify.min.js"></script>
    
    <!-- 프로젝트 불러오는 스크립트 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/project.js"></script>
    
    <!-- 프로필 이미지 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/pimod.js"></script>
      
    <!-- 유저 수정 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/usermod.js"></script>

	<!-- 프로젝트 추가 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/projmod.js"></script>

	<!-- 팔로잉 모달 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/followmod.js"></script>
	
	<!-- init 함수 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/profile/init.js"></script>
    
    
</body>
</html>