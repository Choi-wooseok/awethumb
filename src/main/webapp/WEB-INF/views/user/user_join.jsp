<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 모달창 -->
    <div class="modal hidden">
        <div class="modal_content_container">
            <div class="modal_content" style="display: inline-block; overflow-y: auto; margin: 0 auto; height: 600px; margin-top: 100px; width: 600px;">
            <div style="text-align: center;">
            <i class="fas fa-times fa-3x" id="modalClose"></i>
            </div>
                <div id="boxSize">
                    <form>
                    	아이디 : <input type="text" name="userId" id="userId"> <button type="button" id="userIdChk">아이디 중복 확인</button>
                    	비밀번호 : <input type="password" name="userPass">
                    	닉네임 : <input type="text" name="userNickname" id="userNickname"> <button type="button" id="userNicknameChk">닉네임 중복 확인</button>
                    	이름 : <input type="text" name="userName" id="userName">
                    	카테고리 : 
                    	<c:forEach var="category" items="${categoryList}">
                    		<input type="checkbox" name="categoryChkList" value="${category.categoryNo}">${category.categoryTitle}
                    	</c:forEach>
                    </form>
                </div>
               
            </div>
        </div>
    </div>

