<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <span class="user layout_left">
                <img src="${pageContext.request.contextPath}/images/test_user.jpg" alt="">
            </span>
            <a href="${pageContext.request.contextPath}/profile/${su.userNickname}">공찬희</a>

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
                <button>
                	<a href="${pageContext.request.contextPath}/detailProject/detailBoardList.do">
                    	<i class="far fa-bell"></i>
                    </a>	
                </button>
            </div>
        </div>
    </div>
</header>