<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
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
<!--/head-->
<script type="text/javascript" src="${initParam.rootPath }/js/jquery.js"></script>
<script type="text/javascript">
function cart() {
  	var a = $('#storeId').get(0).options[$('#storeId').get(0).selectedIndex].text;
   	var b = a.substring(a.lastIndexOf("-")+1, a.lastIndexOf("개"));
	
   	var store = $('#storeId').val();
	var count = $('#CountItem').val();

  	var c = window.parseInt(b);
  	var d = window.parseInt(count);
  	
	if(store == "default"){
		alert("매장을 입력해 주세요.")
		return false;
	}
	if(count == ""){
		alert("개수를 입력해 주세요.")
		return false;
	} else if(d > c){
		alert("본 매장의 한정 수량은"+c+"개입니다." );
		return false;
	}
  
	var cart = confirm("장바구니에 담으시겠습니까?");
	if(cart){
		return true;
	}else{
	return false;
	}
}

$(document).ready(function(){
	//카테고리 선택시 아이템 조회
	$("#category").on("change", function(){
		var idx = this.selectedIndex;
		$.ajax({
			"type":"POST",
			"url":"/Project_PL/customer/itemListByCategory.do",
			"data": {"categoryId":$("#category").val()},
			"dataType": "JSON",	//응답 데이터 형식(타입)-
			"beforeSend":function(){
				if($("#category").val()=="default"){
					$("itemId").empty().append("<option value='default'>물품 내역2</option>");
					return false;
				}
			},
			"success":function(item){
				var str = "<option value='default'>물품 이름</option>";
				for(var i = 0; i<item.length; i++){
					str = str + " <option value='"+item[i].itemId+"'>"+ item[i].itemName+"</option>";
					
				}
				$("#itemId").html(str);
			},
			"error": function(){
				alert("오류발생");
			}	
		});
	});
});

</script>

<body>
	<header id="header">
		<!--header-->
		<div class="header-middle">
			<!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-14">
						<div class="logo pull-left">
							<a href="${initParam.rootPath }/basic/item_list.do">
								<img src="${initParam.rootPath}/images/pline.png" alt="" />
							</a> 
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
		<div class="header-bottom">
			<!--header-bottom-->
			<div class="container">
				<div class="row">
					<!--  -->
					<div class="col-sm-9">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle"
								data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
							</button>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/header-bottom-->
	</header>
	<!--/header-->
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
											물품 검색
										</a>
									</h4>
								</div>
								<div id="food" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="${initParam.rootPath }/customer/form_food.do?categoryId=1">식품</a></li>
											<li><a href="${initParam.rootPath }/customer/form_beverage.do?categoryId=2">음료</a></li>
											<li><a href="${initParam.rootPath }/customer/form_snack.do?categoryId=3">과자</a></li>
											<li><a href="${initParam.rootPath }/customer/form_icecream.do?categoryId=4">아이스크림</a></li>
											<li><a href="${initParam.rootPath }/customer/form_daily.do?categoryId=5">생활용품</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordian" href="#beverage"> 
											<span class="badge pull-right">
											<i class="fa fa-plus"></i></span> 편의점 검색
										</a>
									</h4>
								</div>
								<div id="beverage" class="panel-collapse collapse">
									<div class="panel-body">
										<ul>
											<li><a href="${initParam.rootPath }/customer/find_store_name_form.do">매장명 검색</a></li>
											<li><a href="${initParam.rootPath }/customer/find_store_nearby.do">주변 편의점</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"><a href="${initParam.rootPath }/basic/customerBoard.do">게시판</a></h4>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a href="${initParam.rootPath }/basic/customerCenter.do">고객센터</a>
									</h4>
								</div>
							</div>
						</div>
						<!--/category-products-->
					</div>
				</div>
				<div class="col-sm-9 padding-right">
					<div class="product-details">
						<!--product-details-->
						<div class="col-sm-5">
							<div class="view-product">
								<img src="${initParam.rootPath}/images/${requestScope.item.categoryId}/${requestScope.item.itemName}.png" alt="" />
							</div>
							<div id="similar-product" class="carousel slide" data-ride="carousel">
								<!-- Wrapper for slides -->
								<div class="carousel-inner">
									<div>${requestScope.item.categoryName }관련 물품</div>
									<div class="item active">
										<c:forEach items="${requestScope.list }" var="show" begin="0" end="2">
											<a href="${initParam.rootPath}/customer/item.do?itemName=${show.itemName}&categoryId=${show.categoryId}">
												<img src="${initParam.rootPath}/images/${show.categoryId }/${show.itemName }.png" width="80px" height="80px" alt="">
											</a>
										</c:forEach>
									</div>
									<div class="item">
										<c:forEach items="${requestScope.list }" var="show" begin="3" end="5">
											<a href="${initParam.rootPath}/customer/item.do?itemName=${show.itemName}&categoryId=${show.categoryId}">
												<img src="${initParam.rootPath}/images/${show.categoryId }/${show.itemName }.png" width="80px" height="80px" alt="">
											</a>
										</c:forEach>
									</div>
									<div class="item">
										<c:forEach items="${requestScope.list }" var="show" begin="6" end="8">
											<a href="${initParam.rootPath}/customer/item.do?itemName=${show.itemName}&categoryId=${show.categoryId}">
												<img src="${initParam.rootPath}/images/${show.categoryId }/${show.itemName }.png" width="80px" height="80px" alt="">
											</a>
										</c:forEach>
									</div>
								</div>
								<!-- Controls -->
								<a class="left item-control" href="#similar-product" data-slide="prev"> 
									<i class="fa fa-angle-left"></i>
								</a> 
								<a class="right item-control" href="#similar-product" data-slide="next">
									<i class="fa fa-angle-right"></i>
								</a>
							</div>
						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
							<form action="${initParam.rootPath }/customer/cart.do" onsubmit="return cart();">
								<input type="hidden" name=itemId value="${requestScope.item.itemId }">
								<img src="/Project_PL/images/product-details/new.jpg" class="newarrival" alt="" />
								<h2>${requestScope.item.itemName}</h2>
								<p><b>매장 선택</b>
								
								<c:choose>
									<c:when test="${empty requestScope.storeId}" >
										<select name="storeId" id = "storeId">			<!-- 물품 이름으로 매장 다중으로 받아옴 -->
											<option value="default">매장을 선택해 주세요.</option>
											<c:forEach items="${requestScope.store}" var="store">
												<option value="${store.storeId}">${store.storeName}-${store.storeItemCount }개 </option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<br>
										<input type="hidden" name="storeId" value="${requestScope.storeId.storeId }">
										<input type="text" readonly="readonly" value="${requestScope.storeId.storeName}">		
									</c:otherwise>
								</c:choose>

								</p>
								<img src="/Project_PL/images/product-details/rating.png" alt="" />
								
								<span>
									<span>${requestScope.item.itemPrice}원</span>
									<label>Quantity:</label>
									<input type="number" value="1" id="CountItem" name="countItem"/>  <!-- 숫자만 들어오는것 오류 확인해야함 -->
									<button type="submit" class="btn btn-fefault cart">
										<i class="fa fa-shopping-cart"></i>
										Add to cart
									</button>
								</span>
								
								<p><b>분류:</b> ${requestScope.item.categoryName }</p>
							</form>	
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->

					<div class="category-tab shop-details-tab">
						<!--category-tab-->
						<div class="col-sm-12">
							<ul class="nav nav-tabs">
								<!-- 다른 것들 지웠음 -->
								<li class="active"><a href="#reviews" data-toggle="tab">Reviews (${requestScope.reviewCount })</a></li>
							</ul>
						</div>
						<div class="tab-content">
							<div class="tab-pane fade active in" id="reviews">
								<div class="col-sm-12">
									<c:forEach items="${requestScope.reviewList }" var="review">
									<ul>
										<li><a href=""><i class="fa fa-user"></i>${review.reviewWriter}</a></li>
										<li><a href=""><i class="fa fa-clock-o"></i>${review.stringDate1 }</a></li>
										<li><a href=""><i class="fa fa-calendar-o"></i>${review.stringDate2 }</a></li>
									</ul>
									<p>${review.reivewContent } </p>
									</c:forEach>
									<p/>
									<!-- 페이징 처리 시작-->
										<c:choose>
											<c:when test="${requestScope.pagingBeanForReview.previousPageGroup }">
												<a href="${initParam.rootPath }/customer/item.do?pageNo=${requestScope.pagingBeanForReview.startPageOfPageGroup-1}">
													◀ 
												</a>
											</c:when>
											<c:otherwise>
									 			◀
									 		</c:otherwise>
										</c:choose> 
										<!-- Page Group 내의 page들 링크 처리
											- PageGroup의 시작/끝페이지 번호 - 반복문 처리
										 --> 
										<c:forEach begin="${requestScope.pagingBeanForReview.startPageOfPageGroup }" end="${requestScope.pagingBeanForReview.endPageOfPageGroup }" var="page">
											<c:choose>
												<c:when test="${page == requestScope.pagingBeanForReview.currentPage }"> 
													[${page}]
												</c:when>
												<c:otherwise>
													<a href="${initParam.rootPath }/customer/item.do?pageNo=${page }&itemName=${requestScope.item.itemName}&categoryId=${requestScope.item.categoryId }"> ${page } </a>
												</c:otherwise>
											</c:choose>
											&nbsp;&nbsp;
										</c:forEach> 
										<!-- 3. 다음 페이지 그룹 링크
										   	 다음 페이지 그룹이 있으면 링크 처리 없으면 그냥 화살표만 나오도록 처리.
										--> 
										<c:choose>
											<c:when test="${requestScope.pagingBeanForReview.nextPageGroup }">
												<a href="${initParam.rootPath }/customer/item.do?pageNo=${requestScope.pagingBeanForReview.endPageOfPageGroup+1}">
					  								▶
												</a>
											</c:when>
											<c:otherwise>▶</c:otherwise>
										</c:choose>
									<!-- 페이징 처리 끝 -->
									<p>
										<b>Write Your Review</b>
									</p>
									<p/><p/>
									<form action="${initParam.rootPath }/customer/review_add.do">
										<p>
											<select id="category" name="category">
												<option value="${requestScope.item.categoryId }">${requestScope.item.categoryName}</option>
											</select>
											<p/>
											<select id="itemId" name="itemId">
												<option value="${requestScope.item.itemId }">${requestScope.item.itemName }</option>
											</select>
										</p>
										<span> <input readonly="readonly" name="customerId" value="${sessionScope.sessionUser.customerId}" /> 
										</span>
										<textarea name="content"></textarea>
										<button type="submit" class="btn btn-default pull-right">제출</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!--/category-tab-->

				</div>
			</div>
		</div>
	</section>
	<footer id="footer">
		<!--Footer-->
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
					<p class="pull-left">Copyright © 2013 E-SHOPPER Inc. All rights
						reserved.</p>
					<p class="pull-right">
						Designed by <span><a target="_blank"
							href="http://www.themeum.com">Themeum</a></span>
					</p>
				</div>
			</div>
		</div>
	</footer>
	<!--/Footer-->

	<script src="${initParam.rootPath}/js/jquery.js"></script>
	<script src="${initParam.rootPath}/js/price-range.js"></script>
	<script src="${initParam.rootPath}/js/jquery.scrollUp.min.js"></script>
	<script src="${initParam.rootPath}/js/bootstrap.min.js"></script>
	<script src="${initParam.rootPath}/js/jquery.prettyPhoto.js"></script>
	<script src="${initParam.rootPath}/js/main.js"></script>
</body>
</html>