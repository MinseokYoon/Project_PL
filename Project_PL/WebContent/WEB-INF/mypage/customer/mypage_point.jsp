<%@page import="kr.or.kosta.pl.vo.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>편라인 : Project PL</title>
	<link href="${initParam.rootPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${initParam.rootPath}/css/font-awesome.min.css" rel="stylesheet">
	<link href="${initParam.rootPath}/css/prettyPhoto.css" rel="stylesheet">
	<link href="${initParam.rootPath}/css/price-range.css" rel="stylesheet">
	<link href="${initParam.rootPath}/css/animate.css" rel="stylesheet">
	<link href="${initParam.rootPath}/css/main.css" rel="stylesheet">
	<link href="${initParam.rootPath}/css/responsive.css" rel="stylesheet">
	<!--[if lt IE 9]>
	    <script src="js/html5shiv.js"></script>
	    <script src="js/respond.min.js"></script>
	    <![endif]-->
	<link rel="shortcut icon" href="${initParam.rootPath}/images/ico/favicon.ico">
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="${initParam.rootPath}/images/ico/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="${initParam.rootPath}/images/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="${initParam.rootPath}/images/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" href="${initParam.rootPath}/images/ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
	<header id="header"><!--header-->
		<div class="header-middle">
			<!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-14">
						<div class="logo pull-left">
							<a href="${initParam.rootPath }/basic/item_list.do"><img src="${initParam.rootPath}/images/pline.png" alt="" /></a> 
							&nbsp&nbsp&nbsp<b>${sessionScope.sessionUser.customerName }</b>님 환영합니다.
						</div>
					</div>
					<div class="col-sm-13">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="${initParam.rootPath }/customer/mypage.do"><i class="fa fa-user"></i>마이페이지</a></li>
								<li><a href="${initParam.rootPath }/customer/cartpage.do"><i class="fa fa-shopping-cart"></i>장바구니</a></li>
								<li><a href="${initParam.rootPath }/basic/index.do"><i class="fa fa-lock"></i> 로그아웃</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/header-middle--> 
	</header>
	<!--/header-->
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
									<img src="${initParam.rootPath}/images/home/girl1.png" class="girl img-responsive" alt="" /> 
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
									<img src="${initParam.rootPath}/images/home/girl2.png" class="girl img-responsive" alt="" /> 
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
									<img src="${initParam.rootPath}/images/home/girl3.png" class="girl img-responsive" alt="" /> 
								</div>
							</div>
						</div>
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev"> 
							<i class="fa fa-angle-left"></i>
						</a> 
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next"> 
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/slider-->
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>카테고리</h2>
						<div class="panel-group category-products" id="accordian">
							<!--category-productsr-->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#food">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											개인 정보
										</a>
									</h4>
								</div>
								<div id="food" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="${initParam.rootPath }/customer/form_myPage.do">정보 수정</a></li>
											<li><a href="${initParam.rootPath }/customer/form_point.do">포인트 조회</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#beverage"> 
											<span class="badge pull-right">
											<i class="fa fa-plus"></i></span> 주문 내역 조회
										</a>
									</h4>
								</div>
								<div id="beverage" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="${initParam.rootPath }/customer/mypage.do">현재 주문 내역</a></li>
											<li><a href="${initParam.rootPath }/customer/last_order.do">이전 주문 내역</a></li>
										</ul>
									</div>
								</div>
							</div>
							
						</div>
						<!--/category-products-->
					</div>
				</div>
				
				<!--  -->
				<div class="col-sm-9 padding-right">

				  <div class="container">
					  <div class="row">
					     <div class="col-sm-4 col-sm-offset-4">
					        <div class="signup-form">
					           <!--login form-->
					               <div style="Text-align:center; padding:20px; border:1px;">
					                        </div>
					              <h2><b>${sessionScope.sessionUser.customerId}</b>님의 포인트</h2>
					              <div>해당 포인트는 매장에서 결제시 사용해 주세요.</div>
					              <div>(500점 이상부터 사용 가능합니다.)</div>
					              <spring:hasBindErrors name="customer"/>
					              <form action="/Project_PL/basic/item_list.do">
					                 <input readonly="readonly" name="customerPoint" value="${sessionScope.sessionUser.customerPoint}point " />
								<div>이용해 주셔서 감사합니다.</div>
					                      <button type="submit" class="btn btn-default" Text-align:center>메인으로 돌아가기</button>     
					                        <div style="Text-align:center; padding:50px; border:1px;">
					                        </div>             
					              </form>
					           </div>
					           <!--/login form-->
					        </div>
					    </div>
					</div>

				</div>
				
							
			</div>
		</div>
	</section>
	<footer id="footer"><!--Footer-->
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
										<img src="${initParam.rootPath}/images/home/IMG_0035.JPG" alt="" />
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
										<img src="${initParam.rootPath}/images/home/IMG_0248.PNG" alt="" />
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
										<img src=${initParam.rootPath}/images/home/IMG_0258.PNG alt="" />
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
										<img src="${initParam.rootPath}/images/home/IMG_0362.JPG" alt="" />
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
							<img src="${initParam.rootPath}/images/home/map.png" alt="" />
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

	<script src="${initParam.rootPath}/js/jquery.js"></script>
	<script src="${initParam.rootPath}/js/bootstrap.min.js"></script>
	<script src="${initParam.rootPath}/js/jquery.scrollUp.min.js"></script>
	<script src="${initParam.rootPath}/js/price-range.js"></script>
	<script src="${initParam.rootPath}/js/jquery.prettyPhoto.js"></script>
	<script src="${initParam.rootPath}/js/main.js"></script>
</body>
</html>