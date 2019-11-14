<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<%@ include file="/WEB-INF/views/include/cssScript.jsp"%>

<!-- metaTag.html Include ì´í -->
<!-- basic Css ì­ì  -->
<!-- jquery ì­ì  -->
<!-- web Font ì­ì  -->
<!-- font icon ì­ì  -->

<!-- Basic Css -->
<link rel="stylesheet" href="./../css/reset.css">
<link rel="stylesheet" type="text/css"
	media="screen and (min-width:1281px)" href="../css/pc.css">
<!--     <link rel="stylesheet" type="text/css" media="screen and (min-width:768px) and (max-width:1280px)" href="../css/tablet.css"> -->
<!-- <link rel="stylesheet" type="text/css" media="screen and (max-width:767px)" href="../css/mobile.css"> -->
<link rel="stylesheet" href="./../css/common.css">
<link rel="stylesheet" href="./../css/mainfeed.css">
<!-- Jquery -->
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/MainFeed.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<!-- web font -->
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<!-- font icon -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<!-- grid -->
<script src="./../js/brick/brick.min.js"></script>
<link rel="stylesheet" href="./../js/brick/brick.css">

</head>
<body>
	<header></header>

	<section id="feeds">
	<a href="#" id="mainfeed-scroll" style="display: none;"><span></span></a>
		<div class="feedsWrap msrItems">
			<!-- 반복 -->
			<div class="feedsList msrItem">
				<div>
					<div class="feedsInfo">
						<div class="feedUserImg">
							<img src="./../images/test_user.jpg" alt="">
						</div>
						<div>
							<a href="#">${list.userNickname}</a>
							<button 
							type="button">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>

					<!-- 이미지가 있다면 해당 div가 유지, 없으면 안보이게 -->
					<div class="feedsImgWrap">
						<a href="#"> <img src="./../images/main_bg.jpg" alt="">
						</a>
					</div>

					<div class="feedsPlay">
						<div class="feedsContWrap">눈꽃육회 먹으러 갑시다 !!!</div>
						<div class="hashTag">
							<a href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a>
						</div>
						<div class="playInfo">
							댓글 <span>100</span>개
							<button>
								<i class="far fa-heart"></i> <span>10</span>
							</button>
						</div>
					</div>

				</div>
			</div>
			<!-- 여기까지 반복 -->

			<div class="feedsList msrItem">
				<div>
					<div class="feedsInfo">
						<div class="feedUserImg">
							<img src="./../images/test_user.jpg" alt="">
						</div>
						<div>
							<a href="#">userName</a>
							<button type="button">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>

					<!-- 이미지가 있다면 해당 div가 유지, 없으면 안보이게 -->
					<div class="feedsImgWrap">
						<a href="#"> <img src="./../images/test_img1.jpg" alt="">
						</a>
					</div>

					<div class="feedsPlay">
						<div class="feedsContWrap">눈꽃육회 먹으러 갑시다 !!!</div>
						<div class="hashTag">
							<a href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a>
						</div>
						<div class="playInfo">
							댓글 <span>100</span>개
							<button>
								<i class="far fa-heart"></i> <span>10</span>
							</button>
						</div>
					</div>

				</div>
			</div>

			<div class="feedsList msrItem">
				<div>
					<div class="feedsInfo">
						<div class="feedUserImg">
							<img src="./../images/test_img3.jpg" alt="">
						</div>
						<div>
							<a href="#">userName</a>
							<button type="button">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>

					<!-- 이미지가 있다면 해당 div가 유지, 없으면 안보이게 -->
					<div class="feedsImgWrap">
						<a href="#"> <img src="./../images/test_img3.jpg" alt="">
						</a>
					</div>

					<div class="feedsPlay">
						<div class="feedsContWrap">눈꽃육회 먹으러 갑시다 !!!</div>
						<div class="hashTag">
							<a href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a>
						</div>
						<div class="playInfo">
							댓글 <span>100</span>개
							<button>
								<i class="far fa-heart"></i> <span>10</span>
							</button>
						</div>
					</div>

				</div>
			</div>

			<div class="feedsList msrItem">
				<div>
					<div class="feedsInfo">
						<div class="feedUserImg">
							<img src="./../images/test_img3.jpg" alt="">
						</div>
						<div>
							<a href="#">userName</a>
							<button type="button">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>

					<!-- 이미지가 있다면 해당 div가 유지, 없으면 안보이게 -->
					<div class="feedsImgWrap">
						<a href="#"> <img src="./../images/test_img3.jpg" alt="">
						</a>
					</div>

					<div class="feedsPlay">
						<div class="feedsContWrap">눈꽃육회 먹으러 갑시다 !!!</div>
						<div class="hashTag">
							<a href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a>
						</div>
						<div class="playInfo">
							댓글 <span>100</span>개
							<button>
								<i class="far fa-heart"></i> <span>10</span>
							</button>
						</div>
					</div>

				</div>
			</div>

			<div class="feedsList msrItem">
				<div>
					<div class="feedsInfo">
						<div class="feedUserImg">
							<img src="./../images/test_user.jpg" alt="">
						</div>
						<div>
							<a href="#">userName</a>
							<button type="button">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>

					<!-- 이미지가 있다면 해당 div가 유지, 없으면 안보이게 -->
					<div class="feedsImgWrap">
						<a href="#"> <img src="./../images/test_img4.jpg" alt="">
						</a>
					</div>

					<div class="feedsPlay">
						<div class="feedsContWrap">눈꽃육회 먹으러 갑시다 !!!</div>
						<div class="hashTag">
							<a href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a>
						</div>
						<div class="playInfo">
							댓글 <span>100</span>개
							<button>
								<i class="far fa-heart"></i> <span>10</span>
							</button>
						</div>
					</div>

				</div>
			</div>

			<div class="feedsList msrItem">
				<div>
					<div class="feedsInfo">
						<div class="feedUserImg">
							<img src="./../images/test_user.jpg" alt="">
						</div>
						<div>
							<a href="#">userName</a>
							<button type="button">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>

					<!-- 이미지가 있다면 해당 div가 유지, 없으면 안보이게 -->
					<div class="feedsImgWrap">
						<a href="#"> <img src="./../images/test_img3.jpg" alt="">
						</a>
					</div>

					<div class="feedsPlay">
						<div class="feedsContWrap">눈꽃육회 먹으러 갑시다 !!!</div>
						<div class="hashTag">
							<a href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a>
						</div>
						<div class="playInfo">
							댓글 <span>100</span>개
							<button>
								<i class="far fa-heart"></i> <span>10</span>
							</button>
						</div>
					</div>

				</div>
			</div>


			<div class="feedsList msrItem">
				<div>
					<div class="feedsInfo">
						<div class="feedUserImg">
							<img src="./../images/test_user.jpg" alt="">
						</div>
						<div>
							<a href="#">userName</a>
							<button type="button">
								<i class="fas fa-ellipsis-h"></i>
							</button>
						</div>
					</div>

					<!-- 이미지가 있다면 해당 div가 유지, 없으면 안보이게 -->
					<div class="feedsImgWrap">
						<a href="#"> <img src="./../images/test_img3.jpg" alt="">
						</a>
					</div>

					<div class="feedsPlay">
						<div class="feedsContWrap">눈꽃육회 먹으러 갑시다 !!!</div>
						<div class="hashTag">
							<a href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a> <a
								href="#">#가필드</a> <a href="#">#가필드</a> <a href="#">#가필드</a>
						</div>
						<div class="playInfo">
							댓글 <span>100</span>개
							<button>
								<i class="far fa-heart"></i> <span>10</span>
							</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>

	<script>
		$(document).ready(function() {
			$("header").load("./header.html");
		});
		//init
		$('.msrItems').msrItems({
			'colums' : 3, //columns number
			'margin' : 20
		//right and bottom margin
		});

		//update columns size on window resize
		$(window).on('resize', function(e) {
			clearTimeout(time);
			time = setTimeout(function() {
				$('.msrItems').msrItems('refresh');
			}, 200);
		})
	</script>

</body>
</html>