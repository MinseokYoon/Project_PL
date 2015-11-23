<%@ page contentType="text/html;charset=UTF-8" %>
물품을 등록했습니다.<br>
등록한 정보는 다음과 같습니다.<br>
<table border="1" style="width:300px">
	<tr>
		<th>물품 ID</th>
		<td>${requestScope.product.itemId }</td>
	</tr>
	<tr>
		<th>품명</th>
		<td>${requestScope.product.itemName }</td>
	</tr>
	<tr>
		<th>가격</th>
		<td>${requestScope.product.itemPrice }</td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td>${requestScope.product.categoryId }</td>
	</tr>
</table>
