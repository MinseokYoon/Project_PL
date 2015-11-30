<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (session.getAttribute("sessionUser") != null) {
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>편라인 : Project PL</title>
	<link href="/Project_PL/css/bootstrap.min.css" rel="stylesheet">
	<link href="/Project_PL/css/font-awesome.min.css" rel="stylesheet">
	<link href="/Project_PL/css/prettyPhoto.css" rel="stylesheet">
	<link href="/Project_PL/css/price-range.css" rel="stylesheet">
	<link href="/Project_PL/css/animate.css" rel="stylesheet">
	<link href="/Project_PL/css/main.css" rel="stylesheet">
	<link href="/Project_PL/css/responsive.css" rel="stylesheet">
	<!--[if lt IE 9]>
	    <script src="js/html5shiv.js"></script>
	    <script src="js/respond.min.js"></script>
	    <![endif]-->
	<link rel="shortcut icon" href="/Project_PL/images/ico/favicon.ico">
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="/Project_PL/images/ico/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="/Project_PL/images/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="/Project_PL/images/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" href="/Project_PL/images/ico/apple-touch-icon-57-precomposed.png">
</head>
<script type="text/javascript" src="/${initParam.rootPath }/jquery.js"></script>
<script type="text/javascript">
	function productSearchCheck() {
		var form1 = document.productSearch;
		var searchValue = form1.searchValue;
		var howToSearch = form1.howToSearch;

		if (searchValue.value.length == 0) {
			alert("내용을 입력하세요.");
			searchValue.focus();
			return false; //전송 못하게 하는 것 
		}
	}

	function resultOuputMessage() {

		var outCount = document.getElementsByName("outputCount")[0].value;

		if (outCount == "") {
			alert("출고개수를 입력하세요");
			return false;
		}

		alert("출고되었습니다.")
	}
	
	function resultInputMessage() {
		var inputCount = $('#inputCount').val();
		var year = $('#year').val();
		var month = $('#month').val();
		var day = $('#day').val();
	
		
		if (inputCount == "") {
			alert("입고개수를 입력하세요");
			return false;
		} 
		
		if(year == "default"){
			alert("년도를 입력하세요");
			return false;
		}
		
		if(month == "default"){
			alert("월을 입력하세요");
			return false;
		}
		
		if(day == "default"){
			alert("일을 입력하세요");
			return false;
		} 
		
		return confirm("정말 입고하시겠습니까?");
	}
</script>


<body>
	<header id="header"><!--header-->
		<div class="header-middle">
			<!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-14">
						<div class="logo pull-left">
							<a href="/Project_PL/.jsp"><img src="/Project_PL/images/pline.png" alt="" /></a>
							 &nbsp&nbsp&nbsp<b>${sessionScope.sessionUser.ownerName }</b>님 환영합니다.
						</div>
					</div>
					<div class="col-sm-13">
						<div class="shop-menu pull-right">
							<ul class="nav navbar-nav">
								<li><a href="${initParam.rootPath }/owner/ownerMypageForm.do"><i class="fa fa-lock"></i> 마이페이지</a></li>
								<li><a href="${initParam.rootPath }/index.do"><i class="fa fa-user"></i> 로그아웃</a></li>
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
									<img src="/Project_PL/images/home/girl1.jpg" class="girl img-responsive" alt="" /> 
									<img src="/Project_PL/images/home/pricing.png" class="pricing" alt="" />
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
									<img src="/Project_PL/images/home/girl2.jpg" class="girl img-responsive" alt="" /> 
									<img src="/Project_PL/images/home/pricing.png" class="pricing" alt="" />
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
									<img src="/Project_PL/images/home/girl3.jpg" class="girl img-responsive" alt="" /> 
									<img src="/Project_PL/images/home/pricing.png" class="pricing" alt="" />
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
											주문관리
										</a>
									</h4>
								</div>
								<div id="food" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="${initParam.rootPath }/owner/order_list.do?pageNo=${param.pageNo}">주문현황</a></li>
										<li><a href="${initParam.rootPath }/owner/order_processing.do?pageNo=${param.pageNo}">주문처리</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a href="${initParam.rootPath }/owner/product_list.do?pageNo=${param.pageNo}">재고관리</a>
									</h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="${initParam.rootPath }/owner/headOfficeProducts_list.do">본사물품</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="${initParam.rootPath }/owner/headOfficeProducts_list.do?pageNo=${param.pageNo}">본사물품</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a href="${initParam.rootPath }/basic/ownerBoard.do">게시판</a>
									</h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a href="#">고객센터</a>
									</h4>
								</div>
							</div>
						</div>
						<!--/category-products-->
					</div>
				</div>
				<!-- 여기 부분이 이 페이지 부분임! -->
				<div class="col-sm-9 padding-right">
					<div class="product-details">
						<!--product-details-->
						<div class="col-sm-5">
							<div class="view-product">
								<img src="${initParam.rootPath}/images/${requestScope.product.categoryId}/${requestScope.product.itemName}.png" alt="" />
							</div>
						</div>
						<div class="col-sm-7">
							<div class="product-information">
								<!--/product-information-->
								<h2>${requestScope.product.itemName }</h2>
								<span> <span>${requestScope.product.itemPrice }원</span> 
									
								</span>
								<p>분류 : ${requestScope.product.categoryName }</p>
								<p>유통기한 </p>
								<form action="${initParam.rootPath }/owner/inputServerItem.do" onsubmit="return resultInputMessage();">
									<select name="year" style="width: 70px" id="year">
										<option value="default"></option>
										<c:forEach begin="2015" end="2025" var="y">
											<option>${y }</option>
										</c:forEach>
									</select>년
									<select name="month" style="width: 60px" id="month">
										<option value="default"></option>
										<c:forEach begin="1" end="12" var="y">
											<option>${y }</option>
										</c:forEach>
									</select>월
									<select name="day" style="width: 60px" id="day">
										<option value="default"></option>
										<c:forEach begin="1" end="31" var="y">
											<option>${y }</option>
										</c:forEach>
									</select>일
									<input type="hidden" name="itemId" value="${requestScope.product.itemId }">
									<input type="hidden" name="itemName" value="${requestScope.product.itemName }"><p>
									<input type="number" name="inputCount" placeholder="입고개수" id="inputCount" > 
									<input type="submit" value="입고"> 
								</form>
							</div>
							<!--/product-information-->
						</div>
					</div>
				</div>
				<!-- 여기가 끝 -->
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
									<img src="/Project_PL/images/home/IMG_0035.JPG" alt="" />
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
									<img src="/Project_PL/images/home/IMG_0248.PNG" alt="" />
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
									<img src=/Project_PL/images/home/IMG_0258.PNG alt="" />
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
									<img src="/Project_PL/images/home/IMG_0362.JPG" alt="" />
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
						<img src="/Project_PL/images/home/map.png" alt="" />
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

	<script src="/Project_PL/js/jquery.js"></script>
	<script src="/Project_PL/js/bootstrap.min.js"></script>
	<script src="/Project_PL/js/jquery.scrollUp.min.js"></script>
	<script src="/Project_PL/js/price-range.js"></script>
	<script src="/Project_PL/js/jquery.prettyPhoto.js"></script>
	<script src="/Project_PL/js/main.js"></script>
</body>
</html>
<%
	} else {
%>
<script>
	location.assign("/Project_PL/index.do");
</script>
<% } %>