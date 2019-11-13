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
            <form>
                <input type="text" id="id" placeholder="username" />
                <input type="password" id="pass" placeholder="password" />
                <button type="button">Login</button>
            </form>
            <p class="loginText">회원가입을 원하시면 <a href="#">여기</a>를 눌러주세요.</p>
        </div>
    </section>
</body>
</html>