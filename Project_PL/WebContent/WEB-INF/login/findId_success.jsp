<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% if(session.getAttribute("sessionUser")!= null) {
      session.invalidate();   
}   
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>편라인 : Project PL</title>
		<link href="${initParam.rootPath }/css/bootstrap.min.css" rel="stylesheet">
		<link href="${initParam.rootPath }/css/font-awesome.min.css" rel="stylesheet">
		<link href="${initParam.rootPath }/css/prettyPhoto.css" rel="stylesheet">
		<link href="${initParam.rootPath }/css/price-range.css" rel="stylesheet">
		<link href="${initParam.rootPath }/css/animate.css" rel="stylesheet">
		<link href="${initParam.rootPath }/css/main.css" rel="stylesheet">
		<link href="${initParam.rootPath }/css/responsive.css" rel="stylesheet">
		<!--[if lt IE 9]>
		    <script src="js/html5shiv.js"></script>
		    <script src="js/respond.min.js"></script>
		    <![endif]-->
		<link rel="shortcut icon" href="${initParam.rootPath }/images/ico/favicon.ico">
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="${initParam.rootPath }/images/ico/apple-touch-icon-144-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="${initParam.rootPath }/images/ico/apple-touch-icon-114-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="${initParam.rootPath }/images/ico/apple-touch-icon-72-precomposed.png">
		<link rel="apple-touch-icon-precomposed" href="${initParam.rootPath }/images/ico/apple-touch-icon-57-precomposed.png">
	</head>
	<body>
		<header id="header"><!--header-->
			<div class="header-middle">
				<!--header-middle-->
				<div class="container">
					<div class="row">
						<div class="col-sm-4">
							<div class="logo pull-left">
								<a href="${initParam.rootPath }/basic/item_list.do"><img src="${initParam.rootPath }/images/pline.png" alt="" /></a>
							</div>
						</div>
						<div class="col-sm-8">
							<div class="shop-menu pull-right">
								<ul class="nav navbar-nav">
									<li><a href="${initParam.rootPath }/basic/login_form.do"><i class="fa fa-lock"></i> 로그인</a></li>
									<li><a href="${initParam.rootPath }/basic/register_form.do"><i class="fa fa-user"></i> 회원가입</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div><!--/header-middle--> 
		</header><!--/header-->
		<section id="slider"><!--slider-->
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div id="slider-carousel" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
								<li data-target="#slider-carousel" data-slide-to="1"></li>
								<li data-target="#slider-carousel" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner">
								<div class="item active">
									<div class="col-sm-6">
										<h1>
											<span>P</span>-LINE
										</h1>
										<h2>코스타 101기 2조 프로젝트</h2>
										<p>윤민석 최정길 강지선 권효균 안동신 김경모</p>
										<button type="button" class="btn btn-default get">편라인</button>
									</div>
									<div class="col-sm-6">
										<img src="${initParam.rootPath }/images/home/girl1.png" class="girl img-responsive" alt="" /> 
									</div>
								</div>
								<div class="item">
									<div class="col-sm-6">
										<h1>
											<span>P</span>-LINE
										</h1>
										<h2>프로젝트 : 편라인</h2>
										<p>윤민석 최정길 강지선 권효균 안동신 김경모</p>
										<button type="button" class="btn btn-default get">편라인</button>
									</div>
									<div class="col-sm-6">
										<img src="${initParam.rootPath }/images/home/girl2.png" class="girl img-responsive" alt="" /> 
									</div>
								</div>
								<div class="item">
									<div class="col-sm-6">
										<h1>
											<span>P</span>-LINE
										</h1>
										<h2>한국소프트웨어기술진흥협회</h2>
										<p>윤민석 최정길 강지선 권효균 안동신 김경모</p>
										<button type="button" class="btn btn-default get">편라인</button>
									</div>
									<div class="col-sm-6">
										<img src="${initParam.rootPath }/images/home/girl3.png" class="girl img-responsive" alt="" /> 
									</div>
								</div>
							</div>
							<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev"> 
								<i class="fa fa-angle-left"></i></a> <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next"> 
								<i class="fa fa-angle-right"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
		</section><!--/slider--> <!-- 여기 아래 -->
		<section id="form"><!--form-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4 col-sm-offset-1">
						<div class="login-form"><!--login form-->						
							<h3 style="color: #fe980f">아이디는 ${requestScope.customer.customerId }입니다.</h3>
						</div><!--/login form-->
						<a href="${initParam.rootPath }/basic/findPassword_form.do">비밀번호 찾기</a>
					</div>
				</div>
			</div>
		</section><!--/form-->
		</section>
		<footer id="footer"><!--Footer-->  <!-- 여기위에 -->
		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-sm-2">
						<div class="companyinfo">
							<h2>
								<span>P</span>-Line
							</h2>
							<p>윤민석 최정길 강지선 권효균 안동신 김경모</p>
						</div>
					</div>
					<div class="col-sm-7">
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="${initParam.rootPath }/images/home/IMG_0035.JPG" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="${initParam.rootPath }/images/home/IMG_0248.PNG" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src=${initParam.rootPath }/images/home/IMG_0258.PNG alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="video-gallery text-center">
								<a href="#">
									<div class="iframe-img">
										<img src="${initParam.rootPath }/images/home/IMG_0362.JPG" alt="" />
									</div>
									<div class="overlay-icon">
										<i class="fa fa-play-circle-o"></i>
									</div>
								</a>
								<p>Circle of Hands</p>
								<h2>24 DEC 2014</h2>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="address">
							<img src="${initParam.rootPath }/images/home/map.png" alt="" />
							<p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights reserved.</p>
					<p class="pull-right">
						Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span>
					</p>
				</div>
			</div>
		</div>
		</footer>
		<!--/Footer-->

		<script src="${initParam.rootPath }/js/jquery.js"></script>
		<script src="${initParam.rootPath }/js/bootstrap.min.js"></script>
		<script src="${initParam.rootPath }/js/jquery.scrollUp.min.js"></script>
		<script src="${initParam.rootPath }/js/price-range.js"></script>
		<script src="${initParam.rootPath }/js/jquery.prettyPhoto.js"></script>
		<script src="${initParam.rootPath }/js/main.js"></script>
	</body>
</html>