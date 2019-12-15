<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>     
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/include/cssScript.jsp"%> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/chat.css">
 <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <!-- font icon -->
    
</head>
<div class="chat">
			
	<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
	<div id="waitme-status" class="waitme-container">
	  	<div class="ui">
				<div class="left-menu">		
					<div class="search" style="margin: 0 5px;">
						<input placeholder="search..." type="search" name="" id="srchNickname">
						<div id="searchResult" class="search-val"></div>
					</div>
					<c:set var="unReadTotCnt" value="0" />
					<menu class="list-friends">
						<c:forEach var="ssss" items="${chatList}">
						
								<li class="selectmsg" data-room="${ssss.chatroomNo}" data-userno="${ssss.user.userNo}">
									<img width="50" height="50" src="http://cs625730.vk.me/v625730358/1126a/qEjM1AnybRA.jpg">
									<div class="info">
										<div class="user">${ssss.user.userNickname}</div>
										<c:choose>
											<c:when test="${ssss.unReadCnt eq 0}">
												<div class="status on">
											</c:when>
											<c:otherwise>
												<div class="status off">
											</c:otherwise>
										</c:choose>
										안읽은수:<span>${ssss.unReadCnt}</span></div>
										<c:set var="unReadTotCnt" value="${unReadTotCnt +  ssss.unReadCnt}" />
									</div>
								</li>
						</c:forEach>
					</menu>
				</div>
				<div class="chat">
					<c:forEach var="ssss" items="${chatList}">
							<div class="msglist" id="msg${ssss.chatroomNo}" data-room="${ssss.chatroomNo}" data-userno="${ssss.user.userNo}" data-unreadcnt="${ssss.unReadCnt}">
								<div class="top">
									<div class="avatar">
											<img width="50" height="50" src="http://cs625730.vk.me/v625730358/1126a/qEjM1AnybRA.jpg">
									</div>
									<div class="info">
										<div class="name">${ssss.user.userNickname}</div>
									</div>
								</div>
								
								<ul class="messages" data-pageidx="1" data-isprev="true">
									<c:forEach var="msg" items="${ssss.messageList}">
										<c:choose> 
											<c:when test="${su.userNo eq msg.sendUser}"><li class="friend-with-a-SVAGina"></c:when>
											<c:otherwise><li class="i"></c:otherwise>
										</c:choose>
										<div class="head">
											<span class="time">${msg.sendDate}</span>
											<span class="name">${msg.sendTime}</span>
										</div>
										<div class="message">${msg.messageContent}</div>
										</li>
									</c:forEach>
								</ul>
								
								<div class="write-form">
									<textarea placeholder="보낼 메세지 입력" name="e" id="texxt"  rows="2"></textarea>
									<span class="send">Send</span>
								</div>
							</div>
						
					</c:forEach>
				</div>
			</div>
	</div>
	<script>
		let loginUserNo = '${su.userNo}';
		let path = '${pageContext.request.contextPath}';
		let sendUserNo = 0;
		let unReadTotCnt = ${unReadTotCnt};
	</script>



	<script type="text/javascript" src="${pageContext.request.contextPath}/js/chat.js"></script>
	
</div>



</html>