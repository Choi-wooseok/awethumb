<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<button class="modal_btn">Open Modal</button>
    
    <!-- 모달창 -->
    <div class="modal hidden">
        <div class="modal_overlay"></div>
        <div class="modal_content_container">
            <i class="fas fa-caret-left arw-btn"></i>
            <div class="modal_content">
                <div id="boxSize">
                    <img id="image" src="./../images/test_img3.jpg" alt="">
                </div>
                <div>
                    <div class="ModaluserInfo">
                        <div class="commentUser">
                            <img src="./../images/test_user.jpg" alt="">
                        </div>
                        <div class="userName">
                            <a href="#">userName</a>
                        </div>
                    </div>
                    <div class="comment">


                        <div class="commentList">
                            <div class="commentUserImg">
                                <img src="./../images/test_user.jpg" alt="">
                            </div>
                            <div class="commentWrap">
                               가나다라마바사아자차카타파하
                            </div>
                        </div>

                        <div class="commentList">
                                <div class="commentUserImg">
                                    <img src="./../images/test_user.jpg" alt="">
                                </div>
                                <div class="commentWrap">
                                    가나다라마바사아자차카타파하가나다라마바사아자차카타파하
                                </div>
                            </div>


                    </div>
                </div>
            </div>
            <i class="fas fa-caret-right arw-btn"></i>
        </div>
    </div>

    <script src="/js/user.js"></script>