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
        
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<script>
			location.href="${pageContext.request.contextPath}/mainfeed/mainfeed.do"
		</script>
	</sec:authorize>
	    <section id="index">
        <div class="indexBg"></div>
        <div class="login">
            <p class="title">AweThumb</p>
            <form method='post' action="<c:url value="/login" />" >
                <input type="text" name="userId" id="id" placeholder="username" />
                <input type="password" name="userPass" id="pass" placeholder="password" />
                <button>Login</button>
            </form>
            <p class="loginText">회원가입을 원하시면 <a href="javascript:;" id="modalOpen">여기</a>를 눌러주세요.</p>
        </div>
    </section>
	<span><spring:message code="userlogin.fail" /></span>
	<%@ include file="/WEB-INF/views/user/user_join.jsp"%>  
	<script type="text/javascript">
	let errCode = '${errCode}';
	console.log("errcode : ",'${errCode}');
		if ('${errCode}' == 1) {
			Swal.fire({
				  icon: 'error',
				  title: '로그인 실패',
				  text: '<spring:message code="userlogin.fail" />'
			})
		}
	</script> 
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>

</body>
</html>