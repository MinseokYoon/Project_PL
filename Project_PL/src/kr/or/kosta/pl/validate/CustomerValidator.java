package kr.or.kosta.pl.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.kosta.pl.vo.Customer;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.isAssignableFrom(Customer.class);
	}

	@Override
	public void validate(Object target, Errors error) {

		if (!supports(target.getClass())) {
			error.reject("notsupport", "검증할수 없는 객체입니다.");
			return;
		}

		Customer customer = (Customer) target;

		if (customer.getCustomerId() == null || customer.getCustomerId().trim().length() < 6) {
			error.rejectValue("id", "required", "6자 이상 입력해 주세요.");
		} else if (customer.getCustomerPassword() == null || customer.getCustomerPassword().trim().isEmpty()) {
			error.rejectValue("password", "required", "비밀번호를 입력해 주세요.");
		} else if (customer.getCustomerName() == null) {
			error.rejectValue("name", "required", "예쁜 이름을 적어주세요.");
		} else if (customer.getCustomerAddress() == null) {
			error.rejectValue("address", "required", "주소를 적어주세요.");
		} else if(customer.getCustomerBirth()<9999999 && customer.getCustomerBirth()>=100000000){		//date가 아닌 숫자 상태. 8자리를 맞추기 위함
			error.rejectValue("birth", "notCorrectBirth", "생일 형식을 정확히 입렵해 주세요.");
		} else if(customer.getCustomerGender()==0){
			error.rejectValue("gender", "nosex", "성별을 입력하세요.");
		} /*else if(!(customer.getCustomerPhone().length() ==11)){
			error.rejectValue("phone", "noPhone", "핸드폰 번호를 형식에 맞게 입력하세요");
		}*/else if(customer.getCustomerEmail() == null ){
			error.rejectValue("email", "noEmail", "이메일을 입력하세요");
		} 
		

	}

}
