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
    <!-- include header -->
    <header></header>

    <section>
        <div class="bgWrap">
            <img src="<c:url value='/images/main_bg.jpg' />" alt="#">
        </div>
        <div class="content">
            <div class="options">
                <div class="layout_left">
                    <span class="pjtTitle">Project Name</span>
                    <button id="updateBtn">
                    	<i class="fas fa-user-cog"></i>
                    </button>
                </div>
                <div class="layout_right">
                    <button id="insertBtn">
                        <i class="fas fa-plus"></i>
                       	등록
                    </button>
                </div>
            </div>
            <div class="grid-stack" data-gs-width="12">
           		<c:forEach var="li" items="${list}">
         			<div class="grid-stack-item" data-gs-x="${li.xCoord}" data-gs-y="${li.yCoord}" data-gs-width="${li.width}" data-gs-height="${li.hight}">
	                    <div class="grid-stack-item-content">
	                    	
	                    	<!-- if문으로 이미지가 있는지 없는지 확인해서 없으면 display:none -->

	                    	<c:choose>
	                    		<c:when test="${empty li.listFile}">
	                    			<div class="detailCont">
				                    	${li.postContent}
			                    	</div>
	                    		</c:when>
	                    		<c:otherwise>
							        <div class="detailImg" style=""></div>
						        	<div class="detailCont">
				                    	${li.postContent}
			                    	</div>
						        </c:otherwise>
	                    	</c:choose>

	                        <button class="optionModalBtn" type="button">
	                            <i class="fas fa-ellipsis-h"></i>
	                        </button>
	                    </div>
                	</div>
                </c:forEach>
                
            </div>     
        </div>
    </section>

    <div class="modalInsertWrap">
        <form method="post" action="write.do">
            <div class="insertFormWrap">
                <div class="insertTitle">
                   	 글 등록하기
                    <button id="closeBtn" type="button">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
    
                <div class="insertCont">
                	<textarea id="summernote" name="postContent"></textarea>
                </div>
                <div class="btnWrap">
                    <button id="okBtn" type="submit">등록</button>
                </div>
            </div>
        </form>
    </div>

    <div class="optionModalWrap optionU">
        <div>
            <div class="report">
               	부적절한 컨텐츠 신고
            </div>
            <div class="followcancel">
               	팔로우 취소
            </div>
            <div class="saved">
               	 게시물 저장
            </div>
            <div class="cancel">
               	 취소
            </div>
        </div>
    </div>

    <div class="optionModalWrap optionF">
        <div>
            <div class="deleteBoard">글 삭제</div>
            <div class="">글 수정</div>
            <div class="cancel">취소</div>
        </div>
    </div>
                
	<!-- grid Js -->
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.7.0/underscore-min.js"></script>
    <script src="<c:url value='/js/grid/gridstack.all.js' />"></script>
    <script src="<c:url value='/js/detailProject.js' />"></script>

</body>
</html>