package kr.or.kosta.pl.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kosta.pl.service.AdminService;
import kr.or.kosta.pl.service.CustomerService;
import kr.or.kosta.pl.service.OwnerService;
import kr.or.kosta.pl.vo.Admin;
import kr.or.kosta.pl.vo.Customer;
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
	
	@RequestMapping("/index")
	public String index(ModelMap model){

		List<Product> pd = customerService.findItemList();
		
		model.addAttribute("product", pd);
		
		return "/WEB-INF/index.jsp";
	}

	@RequestMapping("/login_form")
	public String loginForm() {
		return "/WEB-INF/login/login.jsp";
	}
	
	@RequestMapping("/register_form")
	public String registerForm() {
		return "/WEB-INF/register/register_form_customer.jsp";
	}
	
	@RequestMapping("/login_ctr")
	public String login_check(@RequestParam String id, @RequestParam String password, HttpSession session){
		
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
		return "/WEB-INF/login/login.jsp";
	}
	
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
	
	@RequestMapping("/ownerHome")
	public String ownerHome() {
		return "/WEB-INF/owner/main_owner.jsp";
	}
	
	@RequestMapping("/adminHome")
	public String adminHome() {
		return "/WEB-INF/admin/main_admin.jsp";
	}
	
	@RequestMapping("/ownerBoard")
	public String ownerBoard() {
		return "/owner/boardList.do";
	}
	
	@RequestMapping("/customerBoard")
	public String customerBoard() {
		return "/customer/boardList.do";
	}
	
	@RequestMapping("/adminBoard")
	public String adminBoard() {
		return "/admin/boardList.do";
	}
	
	/*--------------------------------로그인이 필요합니다.---------------------------------*/
	@RequestMapping("/back_home")
	private String backHome() {
		return "/WEB-INF/back_home.jsp";
	}
}
