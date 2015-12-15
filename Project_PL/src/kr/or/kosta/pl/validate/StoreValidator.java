package kr.or.kosta.pl.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kr.or.kosta.pl.vo.*;

public class StoreValidator implements Validator{

   @Override
   public boolean supports(Class<?> clazz) {
      // TODO Auto-generated method stub
      return clazz.isAssignableFrom(Store.class);
   }
   @Override
   public void validate(Object target, Errors errors) {
      
      Store store = (Store) target;
      
      if (store.getStoreId() == 0 ){
         errors.rejectValue("storeId", "required", new Object[]{"ID는"}, "편의점 ID를 입력해주세.");
      } else if (store.getStoreName().equals("")){
         errors.rejectValue("storeName", "required", new Object[]{"편의점이름은"}, "주소를 적어주세요.");
      } else if (store.getStoreAddress().equals("")){
         errors.rejectValue("storeAddress", "required", new Object[]{"주소는"}, "주소를 적어주세요.");
      } else if (store.getStorePhone().equals("")){
         errors.rejectValue("storePhone","required",new Object[]{"핸드폰번호는"},"DefultPhone");
      } else if (store.getStoreLicense().equals("")){
         errors.rejectValue("storeLicense", "required",new Object[]{"사업번호는"},"DefultLicense");
      }
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ownerId", "requried", new Object[]{"관리자 이름"}, "필수입력사항입니다.");
   }

}