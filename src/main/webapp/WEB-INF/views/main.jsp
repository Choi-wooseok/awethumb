<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>hi</h2>
<a href=" <c:url value="user.do"/> ">유저</a>
<a href=" <c:url value="/admin/adminMain.do" /> ">관리자메인</a>
</body>
</html>