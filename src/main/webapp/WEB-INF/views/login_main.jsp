<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

    <!-- metaTag.html Include 이후 -->
    <!-- basic Css 삭제 -->
    <!-- jquery 삭제 -->
    <!-- web Font 삭제 -->
    <!-- font icon 삭제 -->

    <!-- Basic Css -->
    <link rel="stylesheet" href="./../css/reset.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:1281px)" href="/css/pc.css">
    <link rel="stylesheet" type="text/css" media="screen and (min-width:768px) and (max-width:1280px)" href="/css/tablet.css">
    <link rel="stylesheet" type="text/css" media="screen and (max-width:767px)" href="/css/mobile.css">
    <link rel="stylesheet" href="./../css/common.css">
    <!-- Jquery -->
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <!-- web font -->
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <!-- font icon -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" 
        integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        
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