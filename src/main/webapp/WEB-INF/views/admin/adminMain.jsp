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
        <button type="button" id="insertReportForm">신고합니다</button>
        <div class="row">
          <div class="col-lg-11 main-chart w3-margin-left">
            <!-- 메뉴바 -->
            <div class="w3-bar w3-white w3-round-large w3-border w3-margin-bottom">
              <button class="w3-bar-item w3-button tablink w3-blue" onclick="openTab(event,'Summary')">요약</button>
              <button class="w3-bar-item w3-button tablink" onclick="openTab(event,'Daily')">일간통계</button>
              <button class="w3-bar-item w3-button tablink" onclick="openTab(event,'Monthly')">월간통계</button>
            </div>

            <div id="Summary" class="w3-container w3-border dc-tab w3-padding-24">
              <!--CUSTOM CHART START -->
              <div class="border-head">
                <h3>방문자 수 추이</h3>
              </div>
              <div class="custom-bar-chart">
                <ul class="y-axis">
                  <li><span>10.00</span></li>
                  <li><span>8.00</span></li>
                  <li><span>6.00</span></li>
                  <li><span>4.00</span></li>
                  <li><span>2.00</span></li>
                  <li><span>0</span></li>
                </ul>
                <div class="bar ">
                  <div class="title">6월</div>
                  <div class="value tooltips" data-original-title="8.500" data-toggle="tooltip" data-placement="top">85%
                  </div>
                </div>
                <div class="bar ">
                  <div class="title">7월</div>
                  <div class="value tooltips" data-original-title="5.000" data-toggle="tooltip" data-placement="top">50%
                  </div>
                </div>
                <div class="bar ">
                  <div class="title">8월</div>
                  <div class="value tooltips" data-original-title="6.000" data-toggle="tooltip" data-placement="top">60%
                  </div>
                </div>
                <div class="bar ">
                  <div class="title">9월</div>
                  <div class="value tooltips" data-original-title="4.500" data-toggle="tooltip" data-placement="top">45%
                  </div>
                </div>
                <div class="bar">
                  <div class="title">10월</div>
                  <div class="value tooltips" data-original-title="3.200" data-toggle="tooltip" data-placement="top">32%
                  </div>
                </div>
                <div class="bar ">
                  <div class="title">11월</div>
                  <div class="value tooltips" data-original-title="6.200" data-toggle="tooltip" data-placement="top">62%
                  </div>
                </div>
                <div class="bar">
                  <div class="title">12월</div>
                  <div class="value tooltips" data-original-title="7.500" data-toggle="tooltip" data-placement="top">75%
                  </div>
                </div>
              </div>
              <!--custom chart end-->
              <div class="row mt">
                <!-- SERVER STATUS PANELS -->
                <div class="col-md-4 col-sm-4 mb">
                  <div class="grey-panel pn donut-chart">
                    <div class="grey-header">
                      <h5>서버 사용량</h5>
                    </div>
                    <canvas id="serverstatus01" height="120" width="120"></canvas>
                    <script>
                      var doughnutData = [{
                        value: 70,
                        color: "#FF6B6B"
                      },
                      {
                        value: 30,
                        color: "#fdfdfd"
                      }
                      ];
                      var myDoughnut = new Chart(document.getElementById("serverstatus01").getContext("2d")).Doughnut(doughnutData);
                    </script>
                    <div class="row">
                      <div class="col-sm-6 col-xs-6 goleft">
                        <p>사용량<br />증감:</p>
                      </div>
                      <div class="col-sm-6 col-xs-6">
                        <h2>21%</h2>
                      </div>
                    </div>
                  </div>
                  <!-- /grey-panel -->
                </div>
                <!-- /col-md-4-->
                <div class="col-md-4 col-sm-4 mb">
                  <div class="darkblue-panel pn">
                    <div class="darkblue-header">
                      <h5>불량 회원 신고현황</h5>
                    </div>
                    <div>
                      <li>
                        <ul>
                          <h5>쾌우석 신고합니다.</h5>
                        </ul>
                      </li>
                      <ul>
                        <h5>신고 합니다 불량유저.</h5>
                      </ul>
                      </li>
                      <ul>
                        <h5>이 사람 강퇴해야 합니다.</h5>
                      </ul>
                      </li>
                    </div>

                  </div>
                  <!--  /darkblue panel -->
                </div>

                <!-- /col-md-4 -->
                <div class="col-md-4 col-sm-4 mb">
                  <!-- REVENUE PANEL -->
                  <!-- 수익 패널 -->
                  <div class="green-panel pn">
                    <div class="green-header">
                      <h5>수익</h5>
                    </div>
                    <div class="chart mt">
                      <div class="sparkline" data-type="line" data-resize="true" data-height="75" data-width="90%"
                        data-line-width="1" data-line-color="#fff" data-spot-color="#fff" data-fill-color=""
                        data-highlight-line-color="#fff" data-spot-radius="4"
                        data-data="[200,135,667,333,526,996,564,123,890,464,655]"></div>
                    </div>
                    <p class="mt"><b>1614 만원</b><br />월간 수익</p>
                  </div>
                </div>

              </div>
            </div> <!-- 요약탭끝 -->

            <!-- 일간탭 시작 -->
            <div id="Daily" class="w3-container w3-border dc-tab w3-padding-24" style="display:none">
              <div class="w3-half w3-section">
                <div class="w3-card-4 w3-panel w3-twothird w3-auto dc-card w3-content w3-cell w3-cell-middle">
                  <h5 style="text-align:center;">오늘 전체 방문자수</h5>
                  <hr>
                  <p id="total-visit" style="text-align:center; font-size:3em;">${totalVisitToday}명</p>
                </div>
              </div>
              <div class="w3-half w3-section">
                <div class="w3-card-4 w3-panel w3-twothird w3-auto dc-card w3-content w3-cell w3-cell-middle">
                  <h5 style="text-align:center;">오늘 총 글조회수</h5>
                  <hr>
                  <p style="text-align:center; font-size:3em;">134157</p>
                </div>
              </div>
              <div class="w3-half w3-section">
                <div class="w3-card-4 w3-panel w3-twothird w3-auto dc-card w3-content w3-cell w3-cell-middle">
                  <h5 style="text-align:center;">오늘 작성된 글 수</h5>
                  <hr>
                  <p style="text-align:center; font-size:3em;">${totalPostToday}개</p>
                </div>
              </div>
              <div class="w3-half w3-section">
                <div class="w3-card-4 w3-panel w3-twothird w3-auto dc-card w3-content w3-cell w3-cell-middle">
                  <h5 style="text-align:center;">오늘 가입한 회원수</h5>
                  <hr>
                  <p style="text-align:center; font-size:3em;">${totalUserToday}명</p>
                </div>
              </div>
            </div> <!-- 일간탭끝 -->

            <!-- 월간탭 시작 -->
            <div id="Monthly" class="w3-container w3-border dc-tab w3-padding-24" style="display:none">
              <h2>Tokyo</h2>
              <p>Tokyo is the capital of Japan.</p>
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

  <script>

    //insertReport는 대상 신고버튼 id, postNo, commentNo만 있으면 작동한다.
    $(function () {
      insertReport('insertReportForm', 45, 711);
    })
  </script>

</body>

</html>