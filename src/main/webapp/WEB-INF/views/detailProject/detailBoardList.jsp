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
        
	<!-- grid Plugin -->
    <link rel="stylesheet" href="<c:url value='/js/grid/gridstack.css' /> ">

	<!-- summernote Plugin -->
  	<link rel="stylesheet" href="<c:url value='/js/summernote/summernote-lite.css' /> ">
    <script src="<c:url value='/js/summernote/summernote-lite.js' />"></script>
	
	<!-- slide Plugin -->
  	<link rel="stylesheet" href="<c:url value='/js/slide/slick.css' /> ">
  	<link rel="stylesheet" href="<c:url value='/js/slide/slick-theme.css' /> ">    
    <script src="<c:url value='/js/slide/slick.js' />"></script>

</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.user" var="su"/>
	</sec:authorize>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>

    <section>
        <div class="bgWrap">
        	<img src="${url}" />
        </div>
        <div class="content">
            <div class="options">
                <div class="layout_left">
                    <span class="pjtTitle">
                    	<div class="pjtName">${project.projectTitle}</div>
                   	</span>
                   	
              		<c:set value="false" var="sharedUser"></c:set>
              		<c:forEach var="shu" items="${shardUsers}">
              			<c:if test="${shu == su.userNo}">
              				<c:set value="true" var="shardUsers"></c:set>
              			</c:if>
              		</c:forEach>
              		
                   	<c:choose>
                   		<c:when test="${su.userNo == project.userNo || shardUsers == 'true'}">
		                    <button id="updateBtn" data-pjtNo="${projectNo}">
		                    	<i class="fas fa-user-cog"></i>
		                    </button>                   		
                   		</c:when>
                   	</c:choose>
                </div>
                <c:choose>
                  	<c:when test="${su.userNo == project.userNo || shardUsers == 'true'}">
		                <div class="layout_right">
		                    <button id="insertBtn" data-pjtNo="${projectNo}">
		                        <i class="fas fa-plus"></i>
		                       	등록
		                    </button>
		                </div>
                   	</c:when>
                   	<c:otherwise>
                   		<div class="layout_right">
                   			<button id="savedBtn" data-pjtNo="${projectNo}" data-pjtuNo="${project.userNo}">
                   				<i class="fas fa-file-download"></i>
                   				<span>Saved Project</span>
                   			</button>
                   		</div>
                   	</c:otherwise>
                </c:choose>
            </div>
            <div class="grid-stack" data-gs-width="12">
           		<c:forEach var="li" items="${list}">
         			<div class="grid-stack-item" data-gs-x="${li.xCoord}" data-gs-y="${li.yCoord}" data-gs-width="${li.width}" data-gs-height="${li.hight}" data-msg="${li.postNo}">
	                    <div class="grid-stack-item-content">
					        <div class="detailImg">
					        	<img src="${li.url}" alt="#" />
					        </div>
	                    	<button class="optionModalBtn" type="button">
	                            <i class="fas fa-ellipsis-h" data-msg="${li.postNo}"></i>
	                        </button>
	                        
	                        <button class="detailBtn" type="button">
	                        	<i class="fab fa-instagram" data-msg="${li.postNo}"></i>
	                        </button>
	                    </div>
                	</div>
                </c:forEach>
                
            </div>     
        </div>
    </section>
    
    <div class="optionModalWrap optionF">
        <div>
            <div class="report">
               	부적절한 컨텐츠 신고
            </div>
            <div class="cancel">
               	 취소
            </div>
        </div>
    </div> 

    <div class="modalInsertWrap">
       <form method="post" action="write.do">
            <div class="insertFormWrap">
                <div class="insertTitle">
                   	 글 등록하기
                    <button class="closeBtn" type="button">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
                <div class="insertCont">
              		<div>
	              		<input id="insertImg" name="isnertImg" type="file" multiple="multiple" />
	                    <label class="bgChange" for="insertImg">                    
	                    	<i class="far fa-image"></i>
	                    </label>
              			<div class="imageViewWrap"></div>
              		</div>
                	<textarea id="summernote" name="postContent"></textarea>
                </div>
                <div class="inpjtNo"></div>
                <div class="btnWrap">
                    <button id="okBtn" type="button">등록</button>
                </div>
            </div>
        </form>
    </div>
    
	<div class="optionModalWrap modalMini">
        <div></div>
    </div>
    
    <div class="optionModalWrap savedProject">
    	<div></div>
    </div>
    
    <div class="modal hidden">
        <div class="modal_content_container">
<!--             <i class="fas fa-caret-left arw-btn"></i> -->
            <div class="modal_content"></div>
            <i class="fas fa-caret-right arw-btn"></i>
        </div>
    </div>
    <script>
    	window.onload = function(){
    		${makeAlarm}
    		${flashBoardClick}
    	};    	
    	let loginNo = ${su.userNo}
    </script>
                
	<!-- grid Js -->
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.7.0/underscore-min.js"></script>
    <script src="<c:url value='/js/grid/gridstack.all.js' />"></script>
    <script src="<c:url value='/js/detailProject.js' />"></script>
    <script src="<c:url value='/js/like.js' />"></script>

</body>
</html>