<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userjoin.css">
    <!-- 모달창 -->
    <div class="modal hidden">
        <div class="modal_content_container">
            <div class="modal_content" style="display: inline-block; margin: 0 auto; height: 550px; margin-top: 100px; width: 750px;">
                <div id="boxSize">
					<section class="container">
					<div style="text-align: center; float: right;">
						<i class="fas fa-times fa-lg" id="modalClose"></i>
					</div>
					 
					
					<form action="#" method="post" enctype="multipart/form-data">
						<article class="half">
							        <h1>Awethumb</h1>
							        <div class="tabs">
								            <span class="tab signin active"><a href="#signin">회원가입</a></span>
								            <span class="tab signup"><a href="#signup">카테고리 선택</a></span>
								            <span class="tab signup"><a href="#signup">가입완료</a></span>
							        </div>
							        <div class="content">
								            <div class="signin-cont cont">
										                    <div class="aaa">
										                    	<input type="email" name="userId" id="userId" class="inpt" required="required" placeholder="이메일을 입력하세요">
										                    	<button type="button" class="confirm" id="userIdChk">중복 확인</button>
										                    	<label for="email">Your email</label>
										                    </div>
										                    <div class="aaa">
											                    <input type="password" name="userPass" id="userPass" class="inpt" required="required" placeholder="비밀번호를 입력하세요">
					                						    <label for="password">Your password</label>
										                    </div>
				                						    <div class="aaa">
										                    	<input type="nickname" name="userNickname" id="userNickname" class="inpt" required="required" placeholder="닉네임을 입력하세요">
										                    	<button type="button" class="confirm" id="userNicknameChk">중복 확인</button>
				                						    	<label for="nickname">Your nickname</label>
				                						    </div>
				                						    <div class="aaa">
											                    <input type="name" name="userName" id="userName" class="inpt" required="required" placeholder="이름을 입력하세요">
					                						    <label for="name">Your name</label>
				                						    </div>
										                    <div class="submit-wrap">
											                        <input type="submit" value="가입 진행하기" class="submit">
											                        <a href="#" class="more">비밀번호를 잊어버리셨습니까?</a>
										                    </div>
				        					        
				    				        </div>
				    				        
				    				        <div class="signin-cont cont">
								                    <div class="aaa">
								                    	<c:forEach var="category" items="${categoryList}">
								                    		<input type="checkbox" name="categoryList" class="inpt" required="required" value="${category.categoryNo}">${category.categoryTitle}
								                    	</c:forEach>
								                    	<button type="button" class="confirm" id="userIdChk">중복 확인</button>
								                    	<label for="email">Your email</label>
								                    </div>
								                    <div class="submit-wrap">
									                        <input type="submit" value="가입 진행하기" class="submit">
									                        <a href="#" class="more">비밀번호를 잊어버리셨습니까?</a>
								                    </div>
				    				        </div>
				    				        
				    				        
				    				        
<!-- 				    				        <div class="signup-cont cont"> -->
<!-- 								                <form action="#" method="post" enctype="multipart/form-data"> -->
<!-- 										                    <input type="email" name="email" id="name" class="inpt" required="required" placeholder="Your name"> -->
<!-- 										                    <label for="name">Your name</label> -->
<!-- 				                    						<input type="email" name="email" id="email" class="inpt" required="required" placeholder="Your email"> -->
<!-- 										                    <label for="email">Your email</label> -->
<!-- 										                    <input type="password" name="password" id="password" class="inpt" required="required" placeholder="Your password"> -->
<!-- 				                						    <label for="password">Your password</label> -->
<!-- 										                    <div class="submit-wrap"> -->
<!-- 											                        <input type="submit" value="Sign up" class="submit"> -->
<!-- 											                        <a href="#" class="more">Terms and conditions</a> -->
<!-- 										                    </div> -->
<!-- 				        					       </form> -->
<!-- 				           					 </div> -->
							        </div>
						    	</article>
						    </form>
						    <div class="half bg"></div>
						</section>
				      
                  
                </div>
            </div>
        </div>
    </div>

