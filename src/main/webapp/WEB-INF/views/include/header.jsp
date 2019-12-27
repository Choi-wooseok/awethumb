<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alarm/alarmheader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chat.css">
<header>
	<script>const pageContextPath = "${pageContext.request.contextPath}";</script>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
    <div>
        <div class="layout_left">
            <a href="${pageContext.request.contextPath}/mainfeed/mainfeed.do" class="title">
            	<span>AweThumb</span>
            </a>
			<!-- 검색  -->
            <form id="srchForm" method="post" action="${pageContext.request.contextPath}/mainfeed/mainfeed.do">
	            <div class="search" id="srchBar">
		            <input id="search" name="hashtag" type="text" value=""/>
		            <div id="searchBtn">
		            	<i class="fas fa-search"></i>
		            </div>
		         	<div id="searchResults"></div>
		         </div>
            </form>
        </div>

        <div class="layout_right">
            <sec:authorize access="isAuthenticated()">
            	<span class="user layout_left">
                	<img src="" alt="">
            	</span>
				<a href="${pageContext.request.contextPath}/profile/${su.userNickname}">${su.userNickname}</a>
			</sec:authorize>
            <sec:authorize access="isAnonymous()">
	            <a href="${pageContext.request.contextPath}/user/login_main.do">로그인하세요</a>
			</sec:authorize>

            <div class="optBtn">
            	<button>
            		<a href="${pageContext.request.contextPath}/mainfeed/mainfeed.do">
            			<i class="fas fa-search-location"></i>
            		</a>
            	</button>
                <button>
                	<a href="${pageContext.request.contextPath}/user/logout.do">
	                    <i class="far fa-user"></i>
                    </a>
                </button>
                <button>
                	<a href="${pageContext.request.contextPath}/feed/feed.do">
                    	<i class="far fa-compass"></i>
                    </a>	
                </button>
                <button class="alarm-dropdown-btn">
                	<div>
	                   	<i class="far fa-bell"></i>
	                   	<div class="alarmCnt"></div>
                	</div>
                </button>
                <sec:authorize access="isAuthenticated()">
	                <button id="chatServer">
	                	<div>
		                   	<i class="far fa-paper-plane"></i>
		                   	<div id="chatCnt"></div>
	                	</div>
	                </button>
                </sec:authorize>
            </div>
        </div>
    </div>
    
    <sec:authorize access="isAuthenticated()">
	    <!-- 알림 dropdown -->
	    <div class="alarm-dropdown-wrap alarmhidden">
	    	<div class="alarm-list-wrap">
	    		<ul class="alarm-list">
	    		</ul>
	    		<div>
	    			<a class="dropdown-showmore" href="${pageContext.request.contextPath}/alarm/viewall">View all notifications</a>
	    		</div>
	    	</div>
	    	<div></div>
	    </div>
	    
	    <!-- 채팅 화면 -->
	    <div class="chatting chat-hidden">
	    	<div id="waitme-status" class="waitme-container">
			  	<div class="ui">
					<div class="left-menu">		
						<div class="search" style="margin: 0 5px;">
							<input placeholder="search..." type="search" name="" id="srchNickname">
							<div id="searchResult" class="search-val"></div>
						</div>
						<menu class="list-friends">
						</menu>
					</div>
				<div class="chat">
					<div class="exitchat">
							<i class="fas fa-times"></i>
					</div>
				</div>		
				</div>
			</div>
	    </div>
    	<!-- 알림 js -->
	    <script>
	    	const connectedUserNo= ${su.userNo};
	    	let profileImgURL = "${pageContext.request.contextPath}/api/user/${su.userNo}/thumb";
	    	$.ajax({
	    		url: profileImgURL,
				method: 'GET',
				success: function (result) {
					$(".user.layout_left > img").attr("src", result);
				}
	    	});
	    	
        </script>
        <script src="${pageContext.request.contextPath}/js/alarm/alarm.js"></script>
        <!-- 알림 dropdown js -->
        <script src="${pageContext.request.contextPath}/js/alarm/dropdown.js"></script>
        <!-- 채팅 js -->
        <script src="${pageContext.request.contextPath}/js/chat.js"></script>
    </sec:authorize>
        <!-- 검색 js -->
        <script src="${pageContext.request.contextPath}/js/search.js"></script>
        <!-- 해시태그 플러그인 js -->
        <script src="${pageContext.request.contextPath}/js/hashtag/jquery.hashtags.js"></script>
        <script src="${pageContext.request.contextPath}/js/hashtag/autosize.min.js"></script>
        <!-- 해시태그 js -->
        <script src="${pageContext.request.contextPath}/js/hashtag/hashtagjs.js"></script>
</header>