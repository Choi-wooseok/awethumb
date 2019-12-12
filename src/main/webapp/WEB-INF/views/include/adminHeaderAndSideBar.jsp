<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--header start-->
    <header class="header wht-bg">
      <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="<c:url value='/admin/adminMain.do'/>" class="logo"><b>Awe<span>Thumb</span></b></a>
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
          <li><a class="logout" href="${pageContext.request.contextPath}/user/logout.do">로그아웃</a></li>
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
          <h5 class="centered">관리자</h5>
          <li class="mt">
            <a class="" href="<c:url value='/admin/adminMain.do'/>">
              <i class="fa fa-dashboard"></i>
              <span class="menu-font-dc">대시 보드</span>
              </a>
          </li>
          <li class="">
            <a class="" href="<c:url value='/admin/manageUser.do'/>">
              <i class="fa fa fa-user-circle-o"></i>
              <span class="menu-font-dc">회원 관리</span>
              </a>
          </li>
          <li class="">
            <a class="" href="<c:url value='/admin/reportList.do'/>">
              <i class="fa fa fa-ban"></i>
              <span class="menu-font-dc">신고 현황</span>
              </a>
          </li>
          <!-- 신고,회원관련 메뉴 -->
          <%-- <li class="sub-menu">
            <a href="<c:url value='/admin/adminMain.do'/>">
              <i class="fa fa-desktop"></i>
              <span>회원 관리</span>
              </a>
            <ul class="sub">
              <li><a href="<c:url value='/admin/reportList.do'/>">불량회원 신고현황</a></li>
              <li><a href="<c:url value='/admin/manageUser.do'/>">회원 관리</a></li>
            </ul>
          </li> --%>         
 
         
        </ul>
        <!-- 사이드바 끝 -->
        <!-- sidebar menu end-->
      </div>
    </aside>
    <!--sidebar end-->