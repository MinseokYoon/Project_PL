<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${initParam.rootPath }/script/formcheck.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#modifyForm").on("submit", modifyFormCheck);
});
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("sessionUser") != null) { %>

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
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-14">
						<div class="logo pull-left">
							<a href="${initParam.rootPath }/basic/adminHome.do"><img src="${initParam.rootPath}/images/pline.png" alt="" /></a>
							&nbsp&nbsp&nbsp<b>${sessionScope.sessionUser.adminName }</b>님 환영합니다.
						</div>
					</div>
					<div class="col-sm-13">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="${initParam.rootPath }/admin/adminMypageForm.do"><i class="fa fa-lock"></i> 마이페이지</a></li>
								<li><a href="${initParam.rootPath }/basic/index.do"><i class="fa fa-lock"></i> 로그아웃</a></li>
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
									<h1><span>P</span>-LINE</h1>
									<h2>코스타 101기 2조 프로젝트</h2>
									<p>윤민석 최정길 강지선 권효균 안동신 김경모</p>
									<button type="button" class="btn btn-default get">편라인</button>
								</div>
								<div class="col-sm-6">
									<img src="${initParam.rootPath}/images/home/girl1.jpg" class="girl img-responsive" alt="" />
									<img src="${initParam.rootPath}/images/home/pricing.png"  class="pricing" alt="" />
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1><span>P</span>-LINE</h1>
									<h2>프로젝트 : 편라인</h2>
									<p>윤민석 최정길 강지선 권효균 안동신 김경모</p>
									<button type="button" class="btn btn-default get">편라인</button>
								</div>
								<div class="col-sm-6">
									<img src="${initParam.rootPath}/images/home/girl2.jpg" class="girl img-responsive" alt="" />
									<img src="${initParam.rootPath}/images/home/pricing.png"  class="pricing" alt="" />
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1><span>P</span>-LINE</h1>
									<h2>한국소프트웨어기술진흥협회</h2>
									<p>윤민석 최정길 강지선 권효균 안동신 김경모</p>
									<button type="button" class="btn btn-default get">편라인</button>
								</div>
								<div class="col-sm-6">
									<img src="${initParam.rootPath}/images/home/girl3.jpg" class="girl img-responsive" alt="" />
									<img src="${initParam.rootPath}/images/home/pricing.png" class="pricing" alt="" />
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
	</section><!--/slider-->
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="left-sidebar">
						<h2>카테고리</h2>
						<div class="panel-group category-products" id="accordian"><!--category-productsr-->
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">편의점 관리</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="${initParam.rootPath }/admin/itemList.do">물품 관리</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="#">고객센터관리</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="${initParam.rootPath }/admin/boardList.do">게시판</a></h4>
								</div>
							</div>
						</div><!--/category-products-->					
					</div>
				</div>
				<div class="product list paging">
					<h2>물품 정보 수정</h2>
					<spring:hasBindErrors name="product"/>
					<form action="${initParam.rootPath}/admin/itemModify.do" method="post" id="itemModifyForm">
						<input type="hidden" name="pageNo" value="${param.pageNo }">
						<table border="1" style="width:500px">
							<tr>
								<th>물품 ID</th>
								<td>
									${requestScope.product.itemId }
									<input type="hidden" name="itemId" id="itemId" value="${requestScope.product.itemId }">
								</td>
							</tr>
							<tr>
								<th>품명</th>
								<td>
									<input type="text" id="itemName" name="itemName" size="25" value="${requestScope.product.itemName }" >
									<span class="errorMessage"><form:errors path="product.itemName" delimiter=" | "/></span>
								</td>
							</tr>
							<tr>
								<th>가격</th>
								<td>
									<input type="text" id="itemPrice" name="itemPrice" size="25" value="${requestScope.product.itemPrice }">
									<span class="errorMessage"><form:errors path="product.itemPrice" delimiter=" | "/></span>	
								</td>
							</tr>
							<tr>
								<th>카테고리</th>
								<td>
									<input type="text" id="categoryId" name="categoryId" size="25" value="${requestScope.product.categoryId}">
									<span class="errorMessage"><form:errors path="product.categoryId" delimiter=" | "/></span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="submit" value="수정"> <input type="reset" value="초기화">
								</td>
							</tr>
						</table>
					</form>	
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
							<h2><span>P</span>-Line</h2>
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
					<p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
				</div>
			</div>
		</div>
	</footer><!--/Footer-->
  
    <script src="${initParam.rootPath}/js/jquery.js"></script>
	<script src="${initParam.rootPath}/js/bootstrap.min.js"></script>
	<script src="${initParam.rootPath}/js/jquery.scrollUp.min.js"></script>
	<script src="${initParam.rootPath}/js/price-range.js"></script>
    <script src="${initParam.rootPath}/js/jquery.prettyPhoto.js"></script>
    <script src="${initParam.rootPath}/js/main.js"></script>
</body>
</html>
<% }
else { %>
	<script>
		location.assign("/Project_PL/index.do");
	</script>
<% } %>