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
    <%@ include file="/WEB-INF/views/include/header.jsp"%>

    <section>
    	<div class="bgWrap">
            <img src="${url}" >
        </div>
        <div class="content">
            <div class="options">
                <div class="layout_left">
                    <span class="pjtTitle">
						<div class="pjtName">
							${project.projectTitle}
						</div>
					</span>
                    <input type="file" id="bgChange" />
                    <label class="bgChange" for="bgChange">                    
                    	<i class="far fa-image"></i>
                    </label>
                </div>
                <div class="layout_right">
                    <button id="posUpdateBtn" data-pjtNo="${project.projectNo}" type="button">
                       	수정완료
                    </button>
                </div>
            </div>
            <div class="grid-stack">
           		<c:forEach var="li" items="${list}">
         			<div class="grid-stack-item" data-gs-x="${li.xCoord}" data-gs-y="${li.yCoord}" data-gs-width="${li.width}" data-gs-height="${li.hight}" data-postno="${li.postNo}" >
	                    <div class="grid-stack-item-content">
	                    	<div class="detailImg">
					        	<img src="${li.url}" alt="#" />
					        </div>
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
	            <div class="deleteBoard">글 삭제</div>
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
	                	<div>
							<input id="insertImg" name="isnertImg" type="file" multiple="multiple" />	                	
		                	<div class="imageViewWrap"></div>
	                	</div>
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