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

	    <section id="index">
	        <div class="indexBg"></div>
	        <div class="login">
	            <p class="title">AweThumb</p>
		        <br />
	            <br />
	            <br />
	            <br />
	            <br />
	            <br />
	           <p class="loginText">페이지가 존재하지 않습니다.</p>
	             <br />
	             <br />
	             <br />
	             <br />
	            <br />
	             <p class="loginText">요청하신 주소가 없거나 삭제되었을 수 있습니다.</p>
	            <br />
	             <br />
	             <br />
	             <br />
	            <br />
	            <p class="loginText"><a href="${pageContext.request.contextPath}/mainfeed/mainfeed.do">메인페이지로 이동하기</a></p>
	        </div>
	    </section>
 	<%@ include file="/WEB-INF/views/user/user_join.jsp"%>   
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/user.js"></script>
 	
</body>
</html>