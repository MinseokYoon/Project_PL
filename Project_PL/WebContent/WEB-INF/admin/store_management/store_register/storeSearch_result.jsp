<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% if(session.getAttribute("sessionUser") != null) { %>

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
<script type="text/javascript">
function productSearchCheck(){ 
	var form1 = document.productSearch;
	var searchValue = form1.searchValue;
	var howToSearch = form1.howToSearch;
	
	if(searchValue.value.length == 0){
		alert("내용을 입력하세요.");
		searchValue.focus();
		return false; //전송 못하게 하는 것 
	}
}
</script>
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
								<li><a href="${initParam.rootPath }/admin/adminMypageForm.do"><i class="fa fa-user"></i> 마이페이지</a></li>
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
									<img src="${initParam.rootPath}/images/home/girl1.png" class="girl img-responsive" alt="" />
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
									<img src="${initParam.rootPath}/images/home/girl2.png" class="girl img-responsive" alt="" />
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
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#food">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											편의점 관리
										</a>
									</h4>
								</div>
								<div id="food" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="${initParam.rootPath}/admin/storeList.do">편의점 목록</a></li>
											<li><a href="${initParam.rootPath}/admin/storeAdd.do">편의점등록</a></li>
											<li><a href="${initParam.rootPath}/owner/add.do">점주등록</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#product">
											<span class="badge pull-right"><i class="fa fa-plus"></i></span>
											물품 관리
										</a>
									</h4>
								</div>
								<div id="product" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="${initParam.rootPath }/admin/itemList.do">물품 목록</a></li>
											<li><a href="${initParam.rootPath }/admin/itemForm.do">물품 등록</a></li>
											<li><a href="${initParam.rootPath }/admin/categoryList.do">카테고리 목록</a>
											
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="${initParam.rootPath }/basic/adminBoard.do">게시판</a></h4>
								</div>
							</div>
						</div>			
					</div>
				</div>
				<div class="col-sm-9 padding-right">
				<!-- 여기 부분이 이 페이지 부분임! -->
				<h3>매장 검색 결과</h3>
			<c:choose>
					<c:when test="${empty requestScope.store}">
							조회된 편의점이 없습니다.<p>
							<a href="${initParam.rootPath}/admin/storeList.do">뒤로 가기</a>
					</c:when>
					<c:otherwise>
						<table style="width: 800px" border="1">
							<tr>
								<th style="text-align: center; background-color: #FAE0D4;">편의점 ID</th>
								<th style="text-align: center; background-color: #FAE0D4;">편의점명</th>
								<th style="text-align: center; background-color: #FAE0D4;">주소</th>
								<th style="text-align: center; background-color: #FAE0D4;">전화번호</th>
								<th style="text-align: center; background-color: #FAE0D4;">사업번호</th>
								<th style="text-align: center; background-color: #FAE0D4;">점주</th>
								<th style="text-align: center; background-color: #FAE0D4;">점주 전화번호</th>
							</tr>
							<c:forEach items="${requestScope.store }" var="store">
								<tr>
									<td>${store.storeId }</td>
									<td>
										<a href="${initParam.rootPath}/admin/storeFindById.do?storeId=${store.storeId }&pageNo=${requestScope.pagingBean.currentPage}">${store.storeName}</a>
									</td>
									<td>${store.storeAddress}</td>
									<td>${store.storePhone }</td>
									<td>${store.storeLicense}</td>
									<td>${store.ownerName }</td>
									<td>${store.ownerPhone }</td>
								<tr>
							</c:forEach>
						</TABLE>
					</c:otherwise>
				</c:choose><p>
				<!-- 편의점 검색하는 곳 -->
				<div class="search_box pull-left">
					<form name="storeSearch" action="${initParam.rootPath }/admin/StoreByName.do" method="post" onsubmit="return storeSearchCheck();">
						<input type="text" name="searchValue" placeholder="Search" /> 
					 <!-- 	<input type="submit" value="검색"> -->
					</form>
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
							<h2><span>P</span>-Line</h2>
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
					<p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Themeum</a></span></p>
				</div>
			</div>
		</div>
		
	</footer><!--/Footer-->
	

  
    <script src="/Project_PL/js/jquery.js"></script>
	<script src="/Project_PL/js/bootstrap.min.js"></script>
	<script src="/Project_PL/js/jquery.scrollUp.min.js"></script>
	<script src="/Project_PL/js/price-range.js"></script>
    <script src="/Project_PL/js/jquery.prettyPhoto.js"></script>
    <script src="/Project_PL/js/main.js"></script>
</body>
</html>
<% }
else { %>
	<script>
		location.assign("/Project_PL/index.do");
	</script>
<% } %>