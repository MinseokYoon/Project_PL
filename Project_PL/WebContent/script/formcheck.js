var idDuplicated = false;


function regModFormCheck(){
	if(!$("#ownerId").val()){
		$("#ownerId").focus();
		alert("ID는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#ownerPassword").val()){
		$("#OwnerPassword").focus();
		alert("패스워드는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#ownerName").val()){
		$("#ownerName").focus();
		alert("이름은 필수 입력사항입니다.");
		return false;
	}
	if(!$("#ownerPhone").val()){
		$("#ownerPhone").focus();
		alert("전화번호는 필수 입력사항입니다.");
		return false;
	}
	
	return true;
}


function registerFormCheck(){
	if(idDuplicated){
		alert("사용할 수 없는 ID입니다.");
		return false;
	}
	return regModFormCheck();
}

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
	return true;
}

function registerFormCheck2(){
	if(idDuplicated){
		alert("사용할 수 없는 품명입니다.");
		return false;
	}
	return regModFormCheck2();
}

function regModFormCheck3(){
	
	if(!$("#storeId").val()){
		$("#storeId").focus();
		alert("편의점 ID는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#storeName").val()){
		$("#storeName").focus();
		alert("편의점 명은 필수 입력사항입니다.");
		return false;
	}
	
	if(!$("#storeAddress").val()){
		$("#storeAddress").focus();
		alert("주소는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#ownerId").val()){
		$("#ownerId").focus();
		alert("점주ID는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#storePhone").val()){
		$("#storePhone").focus();
		alert("전화번호는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#storeLicense").val()){
		$("#storeLicense").focus();
		alert("사업번호는 필수 입력사항입니다.");
		return false;
	}
	return true;
}

function registerFormCheck3(){
	if(idDuplicated){
		alert("사용할 수 없는 ID입니다.");
		return false;
	}
	return regModFormCheck3();
}

function regModFormCheck4(){
	
	if(!$("#ownerId").val()){
		$("#ownerId").focus();
		alert("점주ID는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#storePhone").val()){
		$("#storePhone").focus();
		alert("전화번호는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#storeLicense").val()){
		$("#storeLicense").focus();
		alert("사업번호는 필수 입력사항입니다.");
		return false;
	}
	return true;
}

function registerFormCheck4(){
	if(idDuplicated){
		alert("사용할 수 없는 품명입니다.");
		return false;
	}
	return regModFormCheck2();
}
