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
	<div class="waitme-container">
		<sec:authorize access="isAuthenticated()">
			<script>
				location.href="${pageContext.request.contextPath}/mainfeed/mainfeed.do"
			</script>
		</sec:authorize>
		    <section id="index">
	        <div class="indexBg"></div>
	        <div class="login">
	            <p class="title">AweThumb</p>
	            <form method='post' action="<c:url value="/user/login.do" />" >
	                <input type="text" name="userId" id="id" placeholder="username" />
	                <input type="password" name="userPass" id="pass" placeholder="password" />
	                <button>Login</button>
<!-- 	                <button type="button" id="googleOauthLogin">Google Login</button> -->
	                <button type="button" id="naverOauthLogin" style="margin-top: 15px;">Naver Login</button>
	                <button type="button" id="kakaoOauthLogin" style="margin-top: 15px;">Kakao Login</button>
	<!-- 	            <button type="button">Google Login</button> -->
	            </form>
	            <p class="loginText">회원가입을 원하시면 <a href="javascript:;" id="modalOpen">여기</a>를 눌러주세요.</p>
	        </div>
	    </section>
		<%@ include file="/WEB-INF/views/user/user_join.jsp"%>  
		<script type="text/javascript">
			
			let user = JSON.parse('${user}');
			let googleURL = "${google_url}";
			let naverURL = "${naver_url}";
			let kakaoURL = "${kakao_url}";
			let profile = "${profile}";
			
			if ('${errCode}' == 1) {
				Swal.fire({
					  icon: 'error',
					  title: '로그인 실패',
					  text: '<spring:message code="userlogin.fail" />'
				})
			}
			
			else if ('${errCode}' == 2) {
				Swal.fire({
					  icon: 'error',
					  title: '로그인 실패',
					  text: '<spring:message code="userlogin.email.auth" />'
				})
			}
			
			else if ('${errCode}' == 3) {
				Swal.fire({
					  icon: 'error',
					  title: '로그인 실패',
					  text: '<spring:message code="userlogin.fail" />'
				})
			}
			
			else if ('${errCode}' == 4) {
				Swal.fire({
					  icon: 'error',
					  title: '로그인 실패',
					  text: '<spring:message code="userlogin.oauth.fail" />'
				})
			}
			
			else if ('${errCode}' == 5) {
				Swal.fire({
					  icon: 'error',
					  title: '로그인 실패',
					  text: '<spring:message code="userlogin.oauthKakao.fail" />'
				})
			}
			
			else if ('${errCode}' == 6) {
				Swal.fire({
					  icon: 'error',
					  title: '로그인 실패',
					  text: '<spring:message code="userlogin.oauth.fail.changeduserid" />'
				})
			}
			else if ('${errCode}' == 7) {
				Swal.fire({
					  icon: 'error',
					  title: '로그인 실패',
					  html: '<spring:message code="userlogin.block" /> <br>사유 : ${blockMsg.blockReason} <br>정지 날짜 : ${blockStart} <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;~ ${blockEnd}'
				})
			}
			
			
		</script> 
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>
	</div>
</body>
</html>