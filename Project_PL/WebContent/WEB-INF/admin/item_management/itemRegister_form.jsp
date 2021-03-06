<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript" src="${initParam.rootPath }/script/jquery.js"></script>
<script type="text/javascript" src="${initParam.rootPath }/script/formcheck.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $("#regForm2").on("submit", function(){
		 if(idDuplicated){
				alert("사용할 수 없는 품명입니다.");
				return false;
			}
			return regModFormCheck2();

			function regModFormCheck2(){
				
				if(!$("#itemName").val()){
					$("#itemName").focus();
					alert("품명은 필수 입력사항입니다.");
					return false;
				}
				if(!$("#itemPrice").val()){
					$("#itemPrice").focus();
					alert("가격은 필수 입력사항입니다.");
					return false;
				}
				if($("#categoryId").val()=="none"){
					alert("카테고리를 선택해 주세요");
					return false;
				}
				var chk = document.getElementById('upImage'); 
				if(!chk.value){
					alert("파일을 등록해 주세요");
					return false;
				} 
				return true;
			}
	 });
	$("#itemName").on("keyup", function(){
		$.ajax({
			url:"${initParam.rootPath}/admin/idDuplicatedCheck2.do", //요청 url
			type:"POST",
			data: {itemName:$("#itemName").val()},//요청파라미터   id=aaaaa
			dataType:"text",//응답 데이터 타입 - text(기본), json, jsonp, xml
			beforeSend:function(){
				//전송 전에 호출할 함수 등록
				if($("#id").val()==""){
					alert("조회할 ID를 입력하세요");
					return false;//false 리턴시 서버단으로 요청을 하지 않는다.
				}
			},
			success:function(txt){
				//$("#layer").text(txt);
				if(txt=='true'){//중복
					$("#idErrorMessage").text("이미 사용중인 품명입니다.");
					idDuplicated = true;
				}else{
					$("#idErrorMessage").text("사용가능한 품명입니다.");
					idDuplicated = false;
				}
			}
		});
	});
});
</script>

<%
	if (session.getAttribute("sessionUser") != null) {
%>
<!DOCTYPE>
<html>
<style type="text/css">
/*메세지 스타일*/
.errorMessage{
	font-size: 12px;
	color: red
}
.normalMessage{
	font-size:12px;
	color:blue;
}

</style>
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
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="/Project_PL/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="/Project_PL/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="/Project_PL/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="/Project_PL/images/ico/apple-touch-icon-57-precomposed.png">
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
			<div id="layer"></div>
			<h3>물품등록</h3>
			<spring:hasBindErrors name="product" />
			<form action="${initParam.rootPath}/admin/itemAdd.do" method="post" enctype="multipart/form-data" id="regForm2" name="regForm2">
				<!-- 요청 처리할 Controller에 대한 구분값 -->
				<table border="1" style="width: 500px">
					<tr>
						<th style="text-align: center; background-color: #FAE0D4;">물품 ID</th>
						<td><input type="hidden"  id="itemId" size="25"></td>
					</tr>
					<tr>
						<th style="text-align: center; background-color: #FAE0D4;">품명</th>
						<td><input type="text" id="itemName" name="itemName" size="25"> <span class="errorMessage" id="idErrorMessage"></td>
					</tr>
					<tr>
						<th style="text-align: center; background-color: #FAE0D4;">가격</th>
						<td><input type="number" id="itemPrice" name="itemPrice" size="25"> </td>
					</tr>
					<tr>
						<th style="text-align: center; background-color: #FAE0D4;">카테고리</th>
						<td>
							<select name="categoryId" id="categoryId">			
								<option value="none">카테고리 선택</option>
								<c:forEach items="${requestScope.category}" var="category">
									<option value="${category.categoryId}">${category.categoryName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th style="text-align: center; background-color: #FAE0D4;">물품 사진</th>
						<td><input type="file" name="upImage" id="upImage"> </td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="물품등록"> <input
							type="reset" value="초기화"></td>
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