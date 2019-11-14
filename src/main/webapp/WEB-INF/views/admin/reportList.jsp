<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/adminInclude.jsp" %>
<meta charset="UTF-8">
<title>불량회원 신고현황</title>
</head>
<body>
	<section id="container">
    <!--header start-->
    <header class="header black-bg">
      <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="<c:url value='/index.html'/>" class="logo"><b>Awe<span>Thumb</span></b></a>
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        <ul class="nav top-menu">
          <!-- settings start -->
          <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="<c:url value='#'/>">
              <i class="fa fa-tasks"></i>
              <span class="badge bg-theme">4</span>
              </a>
            <ul class="dropdown-menu extended tasks-bar">
              <div class="notify-arrow notify-arrow-green"></div>
              <li>
                <p class="green">You have 4 pending tasks</p>
              </li>
              <li>
                <a href="<c:url value='#'/>">
                  <div class="task-info">
                    <div class="desc">AweThumb 관리자 패널</div>
                    <div class="percent">40%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                      <span class="sr-only">40% Complete (success)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="<c:url value='#'/>">
                  <div class="task-info">
                    <div class="desc">Database Update</div>
                    <div class="percent">60%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                      <span class="sr-only">60% Complete (warning)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="<c:url value='#'/>">
                  <div class="task-info">
                    <div class="desc">Product Development</div>
                    <div class="percent">80%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                      <span class="sr-only">80% Complete</span>
                    </div>
                  </div>
                </a>
              </li>
              <li>
                <a href="<c:url value='#'/>">
                  <div class="task-info">
                    <div class="desc">Payments Sent</div>
                    <div class="percent">70%</div>
                  </div>
                  <div class="progress progress-striped">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%">
                      <span class="sr-only">70% Complete (Important)</span>
                    </div>
                  </div>
                </a>
              </li>
              <li class="external">
                <a href="#">See All Tasks</a>
              </li>
            </ul>
          </li>
          <!-- settings end -->
          <!-- inbox dropdown start-->
          <li id="header_inbox_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
              <i class="fa fa-envelope-o"></i>
              <span class="badge bg-theme">5</span>
              </a>
            <ul class="dropdown-menu extended inbox">
              <div class="notify-arrow notify-arrow-green"></div>
              <li>
                <p class="green">5개의 신규 알림</p>
              </li>
              <li>
                <a href="index.html#">
                  <span class="photo"><img alt="avatar" src="<c:url value='/admin/img/ui-zac.jpg' />"></span>
                  <span class="subject">
                  <span class="from">잭 스나이더</span>
                  <span class="time">방금 전</span>
                  </span>
                  <span class="message">
                  	안녕, 친구. 잘 되가니?
                  </span>
                  </a>
              </li>
              <li>
                <a href="#">
                  <span class="photo"><img alt="avatar" src="<c:url value='/admin/img/ui-divya.jpg' />"></span>
                  <span class="subject">
                  <span class="from">디바 매니안</span>
                  <span class="time">40분 전.</span>
                  </span>
                  <span class="message">
                  	도움!
                  </span>
                  </a>
              </li>
              <li>
                <a href="#">
                  <span class="photo"><img alt="avatar" src="<c:url value='/admin/img/ui-danro.jpg' />"></span>
                  <span class="subject">
                  <span class="from">댄 로저</span>
                  <span class="time">2시간 전.</span>
                  </span>
                  <span class="message">
                  	너의 대쉬보드가 사랑스럽다.
                  </span>
                  </a>
              </li>
              <li>
                <a href="#">
                  <span class="photo"><img alt="avatar" src="<c:url value='/admin/img/ui-sherman.jpg' />"></span>
                  <span class="subject">
                  <span class="from">디제이 샤먼</span>
                  <span class="time">4시간 전.</span>
                  </span>
                  <span class="message">
                  	답장 요망.
                  </span>
                  </a>
              </li>
              <li>
                <a href="#">메시지 모두 보기</a>
              </li>
            </ul>
          </li>
          <!-- 인박스 드랍다운 끝 -->
          <!-- inbox dropdown end -->
          
          <!-- 알림창 드랍다운 시작 -->
          <!-- notification dropdown start-->
          <li id="header_notification_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
              <i class="fa fa-bell-o"></i>
              <span class="badge bg-warning">7</span>
              </a>
            <ul class="dropdown-menu extended notification">
              <div class="notify-arrow notify-arrow-yellow"></div>
              <li>
                <p class="yellow">7개의 읽지 않은 알림이 있습니다.</p>
              </li>
              <li>
                <a href="#">
                  <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                  	서버가 과부하 상태입니다.
                  <span class="small italic">4분 전.</span>
                  </a>
              </li>
              <li>
                <a href="#">
                  <span class="label label-warning"><i class="fa fa-bell"></i></span>
                  2번째 메모리가 응답하지 않습니다.
                  <span class="small italic">30분전.</span>
                  </a>
              </li>
              <li>
                <a href="#">
                  <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                  	디스크 사용량이 85%에 도달했습니다..
                  <span class="small italic">2시간전.</span>
                  </a>
              </li>
              <li>
                <a href="#">
                  <span class="label label-success"><i class="fa fa-plus"></i></span>
                  	새로운 사용자가 회원가입 했습니다.
                  <span class="small italic">3시간전.</span>
                  </a>
              </li>
              <li>
                <a href="#">모든 알림 보기</a>
              </li>
            </ul>
          </li>
          <!-- 알림 드랍다운 끝 -->
          <!-- notification dropdown end -->
        </ul>
        <!-- 알림영역 끝 -->
        <!--  notification end -->
      </div>
      <div class="top-menu">
        <ul class="nav pull-right top-menu">
          <li><a class="logout" href="login.html">로그아웃</a></li>
        </ul>
      </div>
    </header>
    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    <aside>
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          <p class="centered"><a href="profile.html"><img src="<c:url value='/admin/img/ui-sam.jpg' />" class="img-circle" width="80"></a></p>
          <h5 class="centered">유승재</h5>
          <li class="mt">
            <a class="active" href="#">
              <i class="fa fa-dashboard"></i>
              <span>대시보드</span>
              </a>
          </li>
          <!-- 신고,회원관련 메뉴 -->
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>회원 관리</span>
              </a>
            <ul class="sub">
              <li><a href="<c:url value='/admin/reportList.do'/>">불량회원 신고현황</a></li>
              <li><a href="buttons.html">회원 관리</a></li>
            </ul>
          </li>         
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>UI 엘리먼트</span>
              </a>
            <ul class="sub">
              <li><a href="general.html">일반</a></li>
              <li><a href="buttons.html">버튼</a></li>
              <li><a href="panels.html">패널</a></li>
              <li><a href="font_awesome.html">폰트 어썸</a></li>
            </ul>
          </li>         
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-book"></i>
              <span>기타 페이지</span>
              </a>
            <ul class="sub">
              <li><a href="404.jsp">404 에러페이지</a></li>
              <li><a href="500.jsp">500 에러페이지</a></li>
            </ul>
          </li>
          
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-th"></i>
              <span>데이타 테이블</span>
              </a>
            <ul class="sub">
              <li><a href="basic_table.jsp">기본형</a></li>
              <li><a href="advanced_table.jsp">향상된 버전1</a></li>
              <li><a href="advanced_table2.jsp">향상된 버전2</a></li>
              <li><a href="advanced_table3.jsp">향상된 버전3</a></li>
            </ul>
          </li>
          
         
          <li class="sub-menu">
            <a href="javascript:;">
              <i class=" fa fa-bar-chart-o"></i>
              <span>차트</span>
              </a>
            <ul class="sub">
              <li><a href="morris.jsp">모리스차트</a></li>
              <li><a href="chartjs.jsp">차트js</a></li>
              <li><a href="flot_chart.jsp">플롯 차트</a></li>
              <li><a href="xchart.jsp">x차트</a></li>
            </ul>
          </li>
 
         
        </ul>
        <!-- 사이드바 끝 -->
        <!-- sidebar menu end-->
      </div>
    </aside>
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN CONTENT 메인 컨텐츠
        *********************************************************************************************************************************************************** -->
    <!-- 메인 컨텐츠 시작 -->
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <table>
        	<thead>
        		<tr>
        			<th>유저아이디</th>
	        		<th>신고사유</th>
	        		<th>신고날짜</th>
	        		<th>신고 발생글</th>
	        	</tr>
        	</thead>
        	<tbody>
        		<tr>
        			<td></td>
        			<td></td>
        			<td></td>
        			<td></td>
        		</tr>
        	</tbody>
        	
        </table>        
        
      </section>
    </section>
    <!--main content end-->
    
    <!-- 메인컨텐츠끝 푸터 시작 -->
    <!--footer start-->
    <footer class="site-footer">
      <div class="text-center">
        <p>
          &copy; Copyrights <strong>AweThumb</strong>. 2019 All Rights Reserved
        </p>
        <div class="credits">
          <!--
            You are NOT allowed to delete the credit link to TemplateMag with free version.
            You can delete the credit link only if you bought the pro version.
            Buy the pro version with working PHP/AJAX contact form: https://templatemag.com/dashio-bootstrap-admin-template/
            Licensing information: https://templatemag.com/license/
          -->
          Created with Dashio template by <a href="https://templatemag.com/">TemplateMag</a>
        </div>
        <a href="index.html#" class="go-top">
          <i class="fa fa-angle-up"></i>
          </a>
      </div>
    </footer>
    <!--footer end-->
  </section>
  <!-- js placed at the end of the document so the pages load faster -->
  <script src="lib/jquery/jquery.min.js"></script>

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
  <script type="text/javascript">
    $(document).ready(function() {
      var unique_id = $.gritter.add({
        // (string | mandatory) the heading of the notification
        title: 'Welcome to Dashio!',
        // (string | mandatory) the text inside the notification
        text: 'Hover me to enable the Close Button. You can hide the left sidebar clicking on the button next to the logo.',
        // (string | optional) the image to display on the left
        image: 'img/ui-sam.jpg',
        // (bool | optional) if you want it to fade out on its own or just sit there
        sticky: false,
        // (int | optional) the time you want it to be alive for before fading out
        time: 8000,
        // (string | optional) the class name you want to apply to that specific message
        class_name: 'my-sticky-class'
      });

      return false;
    });
  </script>
  <script type="application/javascript">
    $(document).ready(function() {
      $("#date-popover").popover({
        html: true,
        trigger: "manual"
      });
      $("#date-popover").hide();
      $("#date-popover").click(function(e) {
        $(this).hide();
      });

      $("#my-calendar").zabuto_calendar({
        action: function() {
          return myDateFunction(this.id, false);
        },
        action_nav: function() {
          return myNavFunction(this.id);
        },
        ajax: {
          url: "show_data.php?action=1",
          modal: true
        },
        legend: [{
            type: "text",
            label: "Special event",
            badge: "00"
          },
          {
            type: "block",
            label: "Regular event",
          }
        ]
      });
    });

    function myNavFunction(id) {
      $("#date-popover").hide();
      var nav = $("#" + id).data("navigation");
      var to = $("#" + id).data("to");
      console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }
  </script>

</body>
</html>