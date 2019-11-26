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
        
	<!-- grid Css -->
    <link rel="stylesheet" href="<c:url value='/js/grid/gridstack.css' /> ">

	<!-- summernote Plugin -->
  	<link rel="stylesheet" href="<c:url value='/js/summernote/summernote-lite.css' /> ">
    <script src="<c:url value='/js/summernote/summernote-lite.js' />"></script>


</head>
<body>
    <%@ include file="/WEB-INF/views/include/header.jsp"%>

    <section>
    	<div class="bgWrap">
            <img src="<c:url value='/images/main_bg.jpg' />" alt="#">
        </div>
        <div class="content">
            <div class="options">
                <div class="layout_left">
                    <span class="pjtTitle">
						<div class="pjtName"></div>
					</span>
                    <button id="bgChange">
                    	<i class="far fa-image"></i>
                    </button>
                </div>
                <div class="layout_right">
                    <button id="posUpdateBtn" type="button">
                       	수정완료
                    </button>
                </div>
            </div>
            <div class="grid-stack">
           		<c:forEach var="li" items="${list}">
         			<div class="grid-stack-item" data-gs-x="${li.xCoord}" data-gs-y="${li.yCoord}" data-gs-width="${li.width}" data-gs-height="${li.hight}" data-postno="${li.postNo}" >
	                    <div class="grid-stack-item-content">
	                    	
	                    	<!-- if문으로 이미지가 있는지 없는지 확인해서 없으면 display:none -->
	                    	<c:choose>
	                    		<c:when test="${empty li.listFile}">
	                    			<div class="detailCont">
				                    	${li.postContent}
			                    	</div>
	                    		</c:when>
	                    		<c:otherwise>
							        <div class="detailImg"></div>
						        	<div class="detailCont">
				                    	${li.postContent}
			                    	</div>
						        </c:otherwise>
	                    	</c:choose>

	                        <button class="optionModalBtn" type="button">
	                            <i class="fas fa-ellipsis-h" data-msg="${li.postNo}"></i>
	                        </button>
	                    </div>
                	</div>
                </c:forEach>
                
            </div>     
        </div>

        <div class="optionModalWrap optionU">
	        <div>
	            <div class="deleteBoard"></div>
	            <div class="updateBoard">글 수정</div>
	            <div class="updatecancel">취소</div>
	        </div>
	    </div>
        
        <div class="modalInsertWrap">
	        <form class="updateForm" method="post" action="update.do">
	            <div class="insertFormWrap">
	                <div class="insertTitle">
	                   	 글 수정하기
	                    <button id="closeBtn" type="button">
	                        <i class="fas fa-times"></i>
	                    </button>
	                </div>
	    
	                <div class="insertCont">
	                	<textarea id="summernote" name="postContent"></textarea>
	                </div>
	                <div class="btnWrap">
	                    <button class="okBtn" type="submit">등록</button>
	                </div> 
	            </div>
	        </form>
	    </div>
    </section>

    
    <%-- <a href="update.do?postNo=${li.postNo}"> --%>
                
	<!-- grid Js -->
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.7.0/underscore-min.js"></script>
    <script src="<c:url value='/js/grid/gridstack.all.js' />"></script>
    <script src="<c:url value='/js/detailUpdateProject.js' />"></script>

</body>
</html>