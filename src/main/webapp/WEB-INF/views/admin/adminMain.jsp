<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <%@ include file="/WEB-INF/views/include/adminInclude.jsp" %>
</head>

<style>
  .dc-card {
    margin-left: 15%;
    height: 210px;
  }


  .dc-today {
    font-family: Arial;
    padding: 25px;
    background-color: #f5f5f5;
    color: #808080;
    text-align: center;
  }

  /*-=-=-=-=-=-=-=-=-=-=-=- */
  /* Column Grids */
  /*-=-=-=-=-=-=-=-=-=-=-=- */

  .dc-col_half {
    width: 49%;
  }

  .dc-col_third {
    width: 32%;
  }

  .dc-col_fourth {
    width: 21%;
  }

  .dc-col_fifth {
    width: 18.4%;
  }

  .dc-col_sixth {
    width: 15%;
  }

  .dc-col_three_fourth {
    width: 74.5%;
  }

  .dc-col_twothird {
    width: 66%;
  }

  .dc-col_half,
  .dc-col_third,
  .dc-col_twothird,
  .dc-col_fourth,
  .dc-col_three_fourth,
  .dc-col_fifth {
    position: relative;
    display: inline;
    display: inline-block;
    float: left;
    margin-right: 2%;
    margin-bottom: 20px;
  }

  .dc-end {
    margin-right: 0 !important;
  }

  /* Column Grids End */

  .dc-wrapper {
    width: 980px;
    margin: 30px auto;
    position: relative;
  }

  .dc-counter {
    background-color: #ffffff;
    padding: 20px 0;
    border-radius: 5px;
  }

  .dc-count-title {
    font-size: 40px;
    font-weight: normal;
    margin-top: 10px;
    margin-bottom: 0;
    text-align: center;
  }

  .dc-count-text {
    font-size: 13px;
    font-weight: normal;
    margin-top: 10px;
    margin-bottom: 0;
    text-align: center;
  }

  .fa-2x {
    margin: 0 auto;
    float: none;
    display: table;
    color: #4C4C4C;
    font-size: 5em;
  }
</style>

<body>
  <section id="container">
    <!-- **********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->

    <%@ include file="/WEB-INF/views/include/adminHeaderAndSideBar.jsp" %>

    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT 메인 컨텐츠
        *********************************************************************************************************************************************************** -->
    <!-- 메인 컨텐츠 시작 -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <div class="row">
          <div class="col-lg-11 main-chart w3-margin-left">
            <!-- 메뉴바 -->
            <div class="w3-bar w3-white w3-round-large w3-border w3-margin-bottom">
              <button class="w3-bar-item w3-button tablink w3-blue" onclick="openTab(event,'Summary')">요약</button>
              <button class="w3-bar-item w3-button tablink" onclick="openTab(event,'Daily')">오늘</button>
              <button class="w3-bar-item w3-button tablink" onclick="openTab(event,'Monthly')">최근30일</button>
            </div>

            <div id="Summary" class="w3-container w3-border dc-tab w3-padding-24">
              <!--CUSTOM CHART START -->
              <h4>오늘 하루 동안 사이트의 통계</h4>
              <br>
              <hr>

              <div class="dc-counter dc-col_fourth">
                <i class="fa fa-child fa-2x"></i>
                <h2 class="dc-timer dc-count-title dc-count-number" data-to="${totalVisitToday}" data-speed="2500"></h2>
                <p class="dc-count-text ">오늘 전체 방문자수</p>
              </div>

              <div class="dc-counter dc-col_fourth">
                <i class="fa fa-eye fa-2x"></i>
                <h2 class="dc-timer dc-count-title dc-count-number" data-to="157" data-speed="2500"></h2>
                <p class="dc-count-text ">오늘 총 글조회수</p>
              </div>
              <div class="dc-counter dc-col_fourth">
                <i class="fa fa-edit fa-2x"></i>
                <h2 class="dc-timer dc-count-title dc-count-number" data-to="${totalPostToday}" data-speed="2500"></h2>
                <p class="dc-count-text ">오늘 작성된 글 수</p>
              </div>
              <div class="dc-counter dc-col_fourth">
                <i class="fa fa-address-card fa-2x"></i>
                <h2 class="dc-timer dc-count-title dc-count-number" data-to="${totalUserToday}" data-speed="2500"></h2>
                <p class="dc-count-text ">오늘 가입한 회원수</p>
              </div>
              
            </div> <!-- 요약탭끝 -->

            <!-- 일간탭 시작 -->
            <div id="Daily" class="w3-container w3-border dc-tab w3-padding-24 dc-wrapper dc-today"
              style="display:none">
              <h4>오늘 하루 동안 사이트의 통계</h4>
              <br>
              <hr>

              <div class="dc-counter dc-col_fourth">
                <i class="fa fa-child fa-2x"></i>
                <h2 class="dc-timer dc-count-title dc-count-number" data-to="${totalVisitToday}" data-speed="2500"></h2>
                <p class="dc-count-text ">오늘 전체 방문자수</p>
              </div>

              <div class="dc-counter dc-col_fourth">
                <i class="fa fa-eye fa-2x"></i>
                <h2 class="dc-timer dc-count-title dc-count-number" data-to="157" data-speed="2500"></h2>
                <p class="dc-count-text ">오늘 총 글조회수</p>
              </div>
              <div class="dc-counter dc-col_fourth">
                <i class="fa fa-edit fa-2x"></i>
                <h2 class="dc-timer dc-count-title dc-count-number" data-to="${totalPostToday}" data-speed="2500"></h2>
                <p class="dc-count-text ">오늘 작성된 글 수</p>
              </div>
              <div class="dc-counter dc-col_fourth">
                <i class="fa fa-address-card fa-2x"></i>
                <h2 class="dc-timer dc-count-title dc-count-number" data-to="${totalUserToday}" data-speed="2500"></h2>
                <p class="dc-count-text ">오늘 가입한 회원수</p>
              </div>

            </div>

            <!-- 옛날 수동 코드-->

            <!-- <div id="Daily" class="w3-container w3-border dc-tab w3-padding-24" style="display:none">
              <div class="w3-half w3-section">
                <div class="w3-card-4 w3-panel w3-twothird w3-auto dc-card w3-content w3-cell w3-cell-middle">
                  <h5 style="text-align:center;">오늘 전체 방문자수</h5>
                  <hr>
                  <p class="dc-counter" id="total-visit" style="text-align:center; font-size:3em;">${totalVisitToday}명</p>
                </div>
              </div>
              <div class="w3-half w3-section">
                <div class="w3-card-4 w3-panel w3-twothird w3-auto dc-card w3-content w3-cell w3-cell-middle">
                  <h5 style="text-align:center;">오늘 총 글조회수</h5>
                  <hr>
                  <p class="dc-counter" style="text-align:center; font-size:3em;">134157</p>
                </div>
              </div>
              <div class="w3-half w3-section">
                <div class="w3-card-4 w3-panel w3-twothird w3-auto dc-card w3-content w3-cell w3-cell-middle">
                  <h5 style="text-align:center;">오늘 작성된 글 수</h5>
                  <hr>
                  <p class="dc-counter" style="text-align:center; font-size:3em;">${totalPostToday}개</p>
                </div>
              </div>
              <div class="w3-half w3-section">
                <div class="w3-card-4 w3-panel w3-twothird w3-auto dc-card w3-content w3-cell w3-cell-middle">
                  <h5 style="text-align:center;">오늘 가입한 회원수</h5>
                  <hr>
                  <p class="dc-counter" style="text-align:center; font-size:3em;">${totalUserToday}명</p>
                </div>
              </div>
            </div>  -->
            <!-- 일간탭끝 -->

            <!-- 월간탭 시작 -->
            <div id="Monthly" class="w3-container w3-border dc-tab w3-padding-24" style="display:none">
              <!-- 이곳에선 최근 한달간 4가지 주요항목들의의 데이터를 볼 수 있다. -->
              <!-- 주식차트처럼 가로축 현재달의 총일수. 세로축은 각 일자마다의 4자리항목을 막대로 표시해서 보여준다. -->
              <canvas id="myChart" width="100%" height="370px"></canvas>
            </div> <!-- 월간탭끝 -->
          </div>

        </div>
        <!-- **********************************************************************************************************************************************************
              RIGHT SIDEBAR CONTENT
              *********************************************************************************************************************************************************** -->
        <!-- 오른쪽 사이드바 영역 아직 뭐 넣을지 미정 여백도 나쁘지 않음. 나중에 메인컨텐츠 좌측 마진 좀만 주면 그냥 무난 -->
        <div class="col-lg-1 ds">
          <br><br><br>
        </div>
        <!-- /col-lg-1 -->
        </div>
        <!-- /row -->


      </section>
    </section>
    <!--main content end-->

    <!-- 메인컨텐츠끝 푸터 시작 -->
    <%@ include file="/WEB-INF/views/include/adminFooter.jsp" %>
  </section>
  <!-- js placed at the end of the document so the pages load faster -->


  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="lib/jquery.scrollTo.min.js"></script>
  <script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
  <script src="lib/jquery.sparkline.js"></script>
  <!--common script for all pages-->
  <script src="lib/common-scripts.js"></script>
  <script type="text/javascript" src="lib/gritter/js/jquery.gritter.js"></script>
  <script type="text/javascript" src="lib/gritter-conf.js"></script>
  <!--script for this page-->
  <script src="lib/sparkline-chart.js"></script>
  <script src="lib/zabuto_calendar.js"></script>
  <script src="lib/adminMain.js"></script>
  <script src="<c:url value='/js/insertReport.js' />"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.js"></script>
  <!--   <script src="https://raw.githubusercontent.com/nnnick/Chart.js/master/Chart.min.js" ></script> -->
  <script>
    let monthlyStats = {
      logDtList: ${ monthlyStats.logDtList },
      visitCntList: ${ monthlyStats.visitCntList },
      joinCntList: ${ monthlyStats.joinCntList },
      viewCntList: ${ monthlyStats.viewCntList },
      postCntList: ${ monthlyStats.postCntList }
};

    console.log(monthlyStats);
// 	console.log(${monthlyStats.logDtList},
// 			${monthlyStats.visitCntList},
// 			${monthlyStats.joinCntList},
// 			${monthlyStats.viewCntList},
// 			${monthlyStats.postCntList});
// let logDtList = monthlyStats.logDtList;
// let visitCntList = monthlyStats.visitCntList;
// let joinCntList = monthlyStats.joinCntList;
// let viewCntList = monthlyStats.viewCntList;
// let postCntList = monthlyStats.postCntList;
// console.log(logDtList, visitCntList, joinCntList, viewCntList, postCntList);
  </script>

  <script src="<c:url value='/js/montlyChart.js' />"></script>
  <script>

    //insertReport는 대상 신고버튼 id, postNo, commentNo만 있으면 작동한다.
    
  </script>

</body>

</html>