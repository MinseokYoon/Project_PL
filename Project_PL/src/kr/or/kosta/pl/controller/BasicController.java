package kr.or.kosta.pl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.kosta.pl.service.AdminService;
import kr.or.kosta.pl.service.CustomerService;
import kr.or.kosta.pl.service.OwnerService;
import kr.or.kosta.pl.vo.Admin;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Email;
import kr.or.kosta.pl.vo.EmailSender;
import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;

@Controller
@RequestMapping("/basic")
public class BasicController {
   
   @Autowired
   private CustomerService customerService;
   
   @Autowired
   private OwnerService ownerService;
   
   @Autowired
   private AdminService adminService;
   /*--------------------------------로그 아웃---------------------------------*/
   @RequestMapping("/index")
   public String index(ModelMap model){

      List<Product> pd = customerService.findItemList();
      
      model.addAttribute("product", pd);
      
      return "/WEB-INF/index.jsp";
   }
   
   /*--------------------------------위쪽 페이지 이동---------------------------------*/
   //로그인 페이지 이동
   @RequestMapping("/login_form")
   public String loginForm() {
      return "/WEB-INF/login/login.jsp";
   }
   //회원가입 페이지 이동
   @RequestMapping("/register_form")
   public String registerForm() {
      return "/WEB-INF/register/register_form_customer.jsp";
   }
   
   /*--------------------------------로그인 controller---------------------------------*/
   //로그인 처리 
   @RequestMapping("/login_ctr")
   public String login_check(@RequestParam String id, @RequestParam String password, ModelMap model, HttpSession session){
      
      Customer customer = customerService.findCustomerById(id);
      Owner owner = ownerService.findOwnerById(id);
      Admin admin = adminService.findAdminById(id);
      
      if(customer != null) {
         if(customer.getCustomerPassword().equals(password)) {
            session.setAttribute("sessionUser", customer);
            return "/basic/item_list.do";
         }
      } else if(owner != null) {
         if(owner.getOwnerPassword().equals(password)) {
            session.setAttribute("sessionUser", owner);
            return "/WEB-INF/owner/main_owner.jsp";
         }
      } else if(admin != null) {
         if(admin.getAdminPassword().equals(password)) {
            session.setAttribute("sessionUser", admin);
            return "/WEB-INF/admin/main_admin.jsp";
         }
      }
      
      //아이디,패스워드 일치하지 않았을 경우!
        model.addAttribute("errorMessage","Id와 Password가 일치하지 않습니다.");
      return "/WEB-INF/login/login.jsp";
   }
   
   //아이디 찾는 form 가는 컨트롤러
   @RequestMapping("/findId_form")
   public String findIdForm(){
	   return "/WEB-INF/login/findId_form.jsp";
   }
   //비밀번호 찾는 form 가는 컨트롤러
   @RequestMapping("/findPassword_form")
   public String findPasswordForm(){
	   
	   return "/WEB-INF/login/findPassword_form_ById.jsp";
   }
   //아이디 찾기
   @RequestMapping("/findId")
   public String findId(@RequestParam String name,@RequestParam String phoneNumber,ModelMap model){
	   
 	   Customer customer = customerService.findCustomerByNameAndPhoneNumber(name,phoneNumber);
	   
	   if(customer == null){
		   return "/WEB-INF/login/findId_fail.jsp";
	   }
	   
	   model.addAttribute("customer",customer);
	   
	   return "/WEB-INF/login/findId_success.jsp";
   }
   //비밀번호 찾는 컨트롤러 - 1 - 아이디로 우선 조회후 비밀번호 찾기
   @RequestMapping("/findPassword")
   public String findPassword(@RequestParam String customerId,ModelMap model){
	   
	   Customer customer = customerService.findCustomerById(customerId);
	   
	   if(customer == null){
		   model.addAttribute("errorMessage","없는 Id입니다.");
		   return "/WEB-INF/login/findPassword_form_ById.jsp";
	   }
	   
	   return "/WEB-INF/login/findPassword_form_ByValues.jsp"; 
   }
   
   //비밀번호 찾는 컨트롤러 - 2 - 입력한 값들이 일치할 경우 메일로 비밀번호 전송 
   @RequestMapping("/findPassword_2")
   public String findPassword2(@RequestParam String customerId,@RequestParam String customerName, @RequestParam String customerPhoneNumber,ModelMap model){
	   
	   Customer customer = customerService.findCustomerById(customerId);
	   
	   if(customer == null){//없는 아이디를 입력할 경우 
		   model.addAttribute("errorMessage","입력하신 정보가 일치하지 않습니다.");
		   return "/WEB-INF/login/findPassword_form_ById.jsp";
	   }else{//아이디는 일치하는 경우
		   if(customer.getCustomerName().equals(customerName) && customer.getCustomerPhone().equals(customerPhoneNumber)){//이름과 폰번호가 일치하는 경우
			   email.setContent("비밀번호는"+customer.getCustomerPassword()+"입니다.");
			   email.setReceiver(customer.getCustomerEmail());
			   email.setSubject(customerId+"님 비밀번호 찾기 메일입니다.");
			   try {
				emailSender.SendEmail(email);
			} catch (Exception e) {
				System.out.println("메일 오류");
				e.printStackTrace();
			}
			   return "/WEB-INF/login/findPassword_success.jsp";
		   }else{//이름과 폰번호가 일치하지 않는 경우
			   model.addAttribute("errorMessage","일치하지 않는 값입니다.");
			   return "/WEB-INF/login/findPassword_form_ByValues.jsp";
		   }
	   }
	   
   }
   
   /*--------------------------------메인 페이지 이동---------------------------------*/
   //고객 메인 페이지 이동
   @RequestMapping("/item_list")
   public String itemList(ModelMap model, HttpSession session){
      
      List<Product> pd = customerService.findItemList();
      
         model.addAttribute("product", pd);
         
      
      if(session.getAttribute("sessionUser") != null && session.getAttribute("sessionUser").getClass() == Customer.class){
         return "/WEB-INF/customer/main_customer.jsp";
      } else {
         return "/WEB-INF/index.jsp";
      }
   }
   //점주 메인 페이지 이동
   @RequestMapping("/ownerHome")
   public String ownerHome() {
      return "/WEB-INF/owner/main_owner.jsp";
   }
   //관리자 메인 페이지 이동
   @RequestMapping("/adminHome")
   public String adminHome() {
      return "/WEB-INF/admin/main_admin.jsp";
   }
   
   /*--------------------------------고객 센터---------------------------------*/
   @Autowired
   private EmailSender emailSender;
   @Autowired
   private Email email;

   @RequestMapping("/sendEmail.do")
   public String sendEmailAction (@RequestParam String customerId, @RequestParam String customerEmail, 
                           @RequestParam String subject, @RequestParam String content, ModelMap model){
       String id= (String)customerId;
       String e_mail= customerEmail;
       String e_subject = subject;
       String e_content = content;
       
       if(e_mail!=null) {
           email.setContent(e_mail+"에서 온 메일입니다."+ e_content);
           email.setReceiver(e_mail);
           email.setSubject(id+"님의 의견- "+e_subject);
           try {
            emailSender.SendEmail(email);
         } catch (Exception e) {
            System.out.println("메일 오류");
         }
           return "redirect:/basic/item_list.do";
       }else {
           return "redirect:/customer/customerCenter.do";
       }
   }
   
   /*--------------------------------계시판 페이지 이동---------------------------------*/
	//고객페이지 게시판 이동
	@RequestMapping("/customerBoard")
	public String customerBoard() {
		return "/customer/boardList.do";
	}
	//점주페이지 게시판 이동
	@RequestMapping("/ownerBoard")
	public String ownerBoard() {
		return "/owner/boardList.do";
	}
	//관리자페이지 게시판 이동
	@RequestMapping("/adminBoard")
	public String adminBoard() {
		return "/admin/boardList.do";
	}
   
   /*--------------------------------고객센터 페이지 이동---------------------------------*/
   @RequestMapping("/customerCenter")
   public String customerCenter(ModelMap model){
	 
	   
	   model.addAttribute("category", customerService.findCategoryList());
      return "/WEB-INF/customerCenter/customerCenter.jsp";
   }
   
   /*--------------------------------로그인이 필요합니다.---------------------------------*/
   @RequestMapping("/back_home")
   private String backHome() {
      return "/WEB-INF/back_home.jsp";
   }
}