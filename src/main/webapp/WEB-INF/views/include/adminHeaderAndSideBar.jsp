<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--header start-->
    <header class="header wht-bg">
      <div class="sidebar-toggle-box">
      </div>
      <!--logo start-->
      <a href="<c:url value='/admin/adminMain.do'/>" class="logo"><b>Awe<span>Thumb</span></b></a>
      <!--logo end-->
      
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
          <p class="centered"><a href="<c:url value='/admin/adminMain.do'/>"><img src="<c:url value='/admin/img/ui-sam.jpg' />" class="img-circle" width="80"></a></p>
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
         
        </ul>
        <!-- 사이드바 끝 -->
        <!-- sidebar menu end-->
      </div>
    </aside>
    <!--sidebar end-->