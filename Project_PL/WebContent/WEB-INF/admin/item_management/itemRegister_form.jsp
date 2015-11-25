<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${initParam.rootPath }/script/formcheck.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#regForm").on("submit", registerFormCheck);
	$("#itemId").on("keyup", function(){
		$.ajax({
			url:"${initParam.rootPath}/admin/idDuplicatedCheck.do", //요청 url
			type:"POST",
			data: {itemId:$("#itemId").val()},//요청파라미터   id=aaaaa
			dataType:"text",//응답 데이터 타입 - text(기본), json, jsonp, xml
			beforeSend:function(){
				//전송 전에 호출할 함수 등록
				if($("#id").val()==""){
					alert("조회할 ID를 입력하세요");
					return false;//false 리턴시 서버단으로 요청을 하지 않는다.
				}
			},
			success:function(txt){
				$("#layer").text(txt);
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

<div id="layer"></div>
<h2>물품등록</h2>
<spring:hasBindErrors name="product"/>
<form action="${initParam.rootPath}/admin/itemAdd.do" method="post" id="regForm">
<!-- 요청 처리할 Controller에 대한 구분값 -->
<table border="1" style="width:500px">
	<tr>
		<th>물품 ID</th>
		<td><input type="number" id="itemId" name="itemId" size="25"> <span class="errorMessage" id="idErrorMessage"><form:errors path="product.itemId"/></span></td>
	</tr>
	<tr>
		<th>품명</th>
		<td><input type="text" id="itemName" name="itemName" size="25"> <span class="errorMessage"><form:errors path="product.itemName"/></span></td>
	</tr>
	<tr>
		<th>가격</th>
		<td><input type="number" id="itemPrice" name="itemPrice" size="25"> <span class="errorMessage"><form:errors path="product.itemPrice"/></span></td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td><input type="number" id="categoryId" name="categoryId" size="25"> <span class="errorMessage"><form:errors path="product.categoryId"/></span></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="물품등록"> <input type="reset" value="초기화">
		</td>
	</tr>
</table>
</form>
