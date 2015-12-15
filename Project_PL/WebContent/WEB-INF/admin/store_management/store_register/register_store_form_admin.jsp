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
	// $("#regForm3").on("submit", registerFormCheck3);
	$("#storeId").on("keyup", function(){
		$.ajax({
			url:"${initParam.rootPath}/admin/idDuplicatedCheck4.do", //요청 url
			type:"POST",
			data: {storeId:$("#storeId").val()},//요청파라미터   id=aaaaa
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
					$("#idErrorMessage").text("이미 사용중인 ID입니다.");
					idDuplicated = true;
				}else{
					$("#idErrorMessage").text("사용가능한 ID입니다.");
					idDuplicated = false;
				}
			}
		});
	});
});
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   if (session.getAttribute("sessionUser") != null) {
%>

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
<script type="text/javascript">
/*
 * window.open("새창에 보여질 문서의 URL", "창의 이름", "새창에 대한 설정들") - 팝업 창을 띄우는 메소드
 * window.close() - 창을 닫는 메소드
 */
 function popup(){
	window.open("${initParam.rootPath }/owner/ownerList.do", "popupWin", "width=850, height=500, resizable=no");
	
}
</script>

<script type="text/javascript" src="/Project_PL/script/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#chkOwnerId").on("click", function() {
				$.ajax({
					"url" : "/Project_PL/admin/findByOwnerIdJson.do",//요청할 서버 URL 
					"type" : "POST",
					"data" : {"ownerId" : $("#ownerId").val()},//요청파라미터 - query string형태, javascript 객체
					"dataType" : "text",//응답 데이터 형식(타입)-text(기본),json, jsonp, xml
					"beforeSend" : function() {
						if (!$("#ownerId").val()) {
							alert("점주 ID를 입력하세요 ");
							$("#ownerId").focus();
							return false;
						}
					},
					"success" : function(text) {
						if(text == "A"){
							alert("등록되지 않은 Owner입니다");
							return false;
						} else if(text == "B"){
							alert("이미등록된 Owner입니다.");
						} else if(text == " "){
							var t = document.ownerForm.id_check.value = "N";
							return true;
						}
							
						
							
					},		
					"error" : function() {
						
						var t1 = document.ownerForm.id_check.value = "N";
						return true;
					},
				});
				 var hidden = document.ownerForm.id_check.value;
				 if (hidden == "N") {	
						return true;
				 } else {
						 return false;
				 }	 
			});
		});
	</script>
<script type="text/javascript">
  function popupOpen(){
     window.open("${initParam.rootPath}/admin/storeMap.do","popupwin","width=500,height=350,resizeable=no");
     
  }
  </script>
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
<h3>편의점등록</h3>
<spring:hasBindErrors name="store"/>
<form action="${initParam.rootPath}/admin/storeAdd.do" method="post" id="regForm" name="ownerForm">
<!-- 요청 처리할 Controller에 대한 구분값 -->
<table border="1" style="width:510px">
   <tr>
      <th style="text-align: center; background-color: #FAE0D4;">편의점 ID</th>
      <td><input type="number" id="storeId" name="storeId" size="28" placeholder="편의점 ID를 입력해 주세요."> 
      <span class="errorMessage" id="idErrorMessage"><form:errors path="store.storeId"/></span></td>
   </tr>
   <tr>
      <th style="text-align: center; background-color: #FAE0D4;">편의점명</th>
      <td><input type="text" id="storeName" name="storeName" size="25" placeholder="편의점명을 입력해주세요.">
       <span class="errorMessage"><form:errors path="store.storeName"/></span></td>
   </tr>
   <tr>
      <th style="text-align: center; background-color: #FAE0D4;">점주 ID</th>
      <td><input type="text" id="ownerId" name="ownerId" size="28" placeholder="점주 ID하나당 하나의 편의점 등록">
      <input type="button" value="조회" onclick="popup();">
      <span class="errorMessage" id="idErrorMessage"><form:errors path="store.ownerId"/></span></td>
   </tr>
   <tr>
      <th style="text-align: center; background-color: #FAE0D4;">편의점 주소</th>
      <td><input type="text" id="storeAddress" name="storeAddress" size="25" placeholdr="편의점 주소(예: 경기도 성남시 )"> 
      <span class="errorMessage"><form:errors path="store.storeAddress"/></span></td>
   </tr>
   <tr>
      <th style="text-align: center; background-color: #FAE0D4;">전화번호</th>
      <td><input type="text" id="storePhone" name="storePhone" size="25" placeholder="전화번호 (예 : 0311231234)"> 
      <span class="errorMessage"><form:errors path="store.storePhone"/></span></td>
   </tr>
   <tr>
      <th style="text-align: center; background-color: #FAE0D4;">사업번호</th>
      <td><input type="text" id="storeLicense" name="storeLicense" size="25" placeholder="사업번호 (10자리 이하)"> 
      <span class="errorMessage"><form:errors path="store.storeLicense"/></span></td>
   </tr>
   <tr>
      <td colspan="2">
      	<input type="hidden" name="id_check" id="id_check" value="Y"/>
         <input type="submit" value="등록" id="chkOwnerId"> <input type="reset" value="초기화">
         <input type = "button" value ="지도 넣기" onclick="popupOpen();">
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