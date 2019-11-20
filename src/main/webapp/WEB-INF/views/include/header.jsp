<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
    <div>
        <div class="layout_left">
            <a href="#" class="title"><span>AweThumb</span></a>  
            <span class="search">
                <form action="">
                    <input id="search" type="text" />
                    <label for="search"><i class="fas fa-search"></i></label>
                </form>
            </span>   
        </div>

        <div class="layout_right">
            <span class="user layout_left">
                <img src="${pageContext.request.contextPath}/images/test_user.jpg" alt="">
            </span>
            <a href="${pageContext.request.contextPath}/profile/${su.userNickname}">공찬희</a>

            <div class="optBtn">
                <button>
                    <i class="far fa-user"></i>
                </button>
                <button>
                    <i class="far fa-compass"></i>
                </button>
                <button>
                    <i class="far fa-bell"></i>
                </button>
            </div>
        </div>
    </div>
</header>