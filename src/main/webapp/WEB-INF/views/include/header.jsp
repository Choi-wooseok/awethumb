<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alarm/alarmheader.css" />
<header>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
    <div>
        <div class="layout_left">
            <a href="${pageContext.request.contextPath}/mainfeed/mainfeed.do" class="title">
            	<span>AweThumb</span>
            </a>     
        </div>

        <div class="layout_right">
            <sec:authorize access="isAuthenticated()">
            	<span class="user layout_left">
                	<img src="${pageContext.request.contextPath}/images/test_user.jpg" alt="">
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
	    			<a class="dropdown-showmore" href="${pageContext.request.contextPath}/alarm/veiwall">View all notifications</a>
	    		</div>
	    	</div>
	    	<div></div>
	    </div>
    	<!-- 알림 js -->
	    <script>
	    	const connectedUserNo= ${su.userNo};
	    	const pageContextPath = "${pageContext.request.contextPath}";
        </script>
        <script src="${pageContext.request.contextPath}/js/alarm/alarm.js"></script>
        <!-- 알림 dropdown js -->
        <script src="${pageContext.request.contextPath}/js/alarm/dropdown.js"></script>
    </sec:authorize>
</header>