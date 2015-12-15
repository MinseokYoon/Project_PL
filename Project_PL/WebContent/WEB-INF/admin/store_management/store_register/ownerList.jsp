<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
function showOwnerToOpener(ownerId){
	alert(ownerId);
	//선택한 우편번호와 주소를 팝업을 연 창(opener)에 보이도록 처리
	
	//팝업에서 자신을 띄운 창(window)에 접근 -> opener객체 이용
	//document객체.html.body.form 객체로 접근할 필요없이 form객체는 document를 통해 바로 접근이 가능
	//구문 : window.document.formName(form의 name속성값)
	opener.document.ownerForm.ownerId.value = ownerId;
	/* 
	var flag = window.confirm("창을 닫겠습니까");
	if(flag){
		window.close();
	} */
	window.close();
}
</script>
					<h3>점주 목록</h3>
					<c:choose>
						<c:when test="${fn:length(requestScope.list)==0 }">
							등록된 점주가 없습니다.
						</c:when>
						<c:otherwise>
							<table style="width: 800px" border="1">
								<tr color="red">
									<th style="text-align: center; background-color: #FAE0D4;">점주ID</th>
									<th style="text-align: center; background-color: #FAE0D4;">점주명</th>
									<th style="text-align: center; background-color: #FAE0D4;">전화번호</th>
								</tr>
								<c:forEach items="${requestScope.list }" var="owner">
									<tr>
										<td>${owner.ownerId }</td>
										<td><a href="javascript:showOwnerToOpener('${owner.ownerId}');">${owner.ownerName}</a></td>
										<td>${owner.ownerPhone }</td>
									<tr>
								</c:forEach>
								<tr>
									<td colspan="7" align="center">
										<!-- Paging 처리 --> <!-- 
										1. 이전 페이지 그룹으로 이동 처리
										  이전페이지 그룹이 있으면 링크처리 없으면 <- 모양만 나오도록 처리.
										 --> <c:choose>
											<c:when test="${requestScope.pagingBean.previousPageGroup }">
												<a
													href="${initParam.rootPath }/owner/ownerList.do?pageNo=${requestScope.pagingBean.startPageOfPageGroup-1}">
													◀ </a>
											</c:when>
											<c:otherwise>
										 		◀
										 	</c:otherwise>
										</c:choose> <!-- Page Group 내의 page들 링크 처리
											- PageGroup의 시작/끝페이지 번호 - 반복문 처리
										 --> <c:forEach
											begin="${requestScope.pagingBean.startPageOfPageGroup }"
											end="${requestScope.pagingBean.endPageOfPageGroup }"
											var="page">
											<c:choose>
												<c:when
													test="${page == requestScope.pagingBean.currentPage }">
													[${page}]
												</c:when>
												<c:otherwise>
													<a
														href="${initParam.rootPath }/owner/ownerList.do?pageNo=${page }">
														${page } </a>
												</c:otherwise>
											</c:choose>
											&nbsp;&nbsp;
										</c:forEach> <!-- 3. 다음 페이지 그룹 링크
										    다음 페이지 그룹이 있으면 링크 처리 없으면 그냥 화살표만 나오도록 처리.
										 --> <c:choose>
											<c:when test="${requestScope.pagingBean.nextPageGroup }">
												<a
													href="${initParam.rootPath }/owner/ownerList.do?pageNo=${requestScope.pagingBean.endPageOfPageGroup+1}">
													▶ </a>
											</c:when>
											<c:otherwise>▶</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</table>
						</c:otherwise>
					</c:choose>
					<p>
					<!-- 편의점 검색하는 곳 -->
					<%-- <div class="search_box pull-left">
						<form name="storeSearch"
							action="${initParam.rootPath }/admin/StoreByName.do"
							method="post" onsubmit="return storeSearchCheck();">
							<input type="text" name="searchValue" placeholder="Search" />
							<!-- 	<input type="submit" value="검색"> -->
						</form>
					</div> --%>
					<input type="button" value="닫기" onclick="showOwnerToOpener();">
