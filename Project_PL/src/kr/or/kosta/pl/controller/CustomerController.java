package kr.or.kosta.pl.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.service.CustomerService;
import kr.or.kosta.pl.validate.CustomerValidator;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Store;

@Controller
@RequestMapping("/customer") // view& section을 어디서 찾아야 할까
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping("/index")
	public String index_customer(){
		return "/WEB-INF/customer/main_customer.jsp";
	}

	@RequestMapping("/cartpage")
	public String cartPage(HttpSession session, ModelMap model){
		
		//if (session.getAttribute("sessionUser").getClass() == Customer.class)
		//세션에서 어떤 유저인지를 검사 하려면!!!
		Customer customer = (Customer)session.getAttribute("sessionUser");
		
		List<Cart> cart = service.findCartByCusotmerId(customer.getCustomerId());
		
		model.addAttribute("cart", cart);
		System.out.println(cart);
		
		return "/WEB-INF/customer/cart/cart_form.jsp";
	}
	
	
	
	/*-----------------------------------회원 가입------------------------------------------------*/
	@RequestMapping("/add.do")
	public String add(@ModelAttribute Customer customer, Errors errors, ModelMap model) throws DuplicatedIdException {

		CustomerValidator validate = new CustomerValidator();
		validate.validate(customer, errors);

		/*
		 * if(errors.hasErrors()){ return
		 * "/WEB-INF/register/register_form_customer.jsp"; }
		 */

		service.addCustomer(customer);
		model.addAttribute("customerId", customer.getCustomerId());
	
		return "redirect:/customer/registerSuccess.do";
	}

	@RequestMapping("registerSuccess")
	public String registerSuccess(@RequestParam String customerId, ModelMap model) {

		model.addAttribute("customer", service.findCustomerById(customerId));
		return "/WEB-INF/register/register_success_customer.jsp";

	}
	
	/*----------------------물품 페이지 이동 (좌측 메뉴)-----------------------------------*/
	
	@RequestMapping("/form_food.do")
	public String formFood(@RequestParam int categoryId, ModelMap model){
		
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		
		return "/WEB-INF/customer/item_list/item_list_each/item_list_food.jsp";
	}
	@RequestMapping("/form_beverage.do")
	public String formBeverage(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_beverage.jsp";
	}
	@RequestMapping("/form_snack.do")
	public String formSnack(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_snack.jsp";
	}
	@RequestMapping("/form_icecream.do")
	public String formIcecream(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_icecream.jsp";
	}
	@RequestMapping("/form_daily.do")
	public String formDaily(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_daily.jsp";
	}
	
	/*----------------------위치 페이지 이동 (좌측 메뉴)-----------------------------------*/
	
	@RequestMapping("find_store_name_form.do")
	public String findStore(){
		return "/WEB-INF/customer/find_store/find_store_name.jsp";
	}
	
	@RequestMapping("/find_store_nearby")
	public String findStoreNearby(){
		return "/WEB-INF/customer/find_store/find_store_nearby.jsp";
	}
	
	/*---------------------------------매장이름으로 조회---------------------------------------*/
	@RequestMapping("find_store_name.do")
	public String findStore(@RequestParam(value="findStoreName") String storeName, ModelMap model){
		List<Store> list = service.findStoreName(storeName);
		model.addAttribute("findstore",list);
		return "/WEB-INF/customer/find_store/find_store_name_success.jsp";
	}
	
	/*---------------------------------카테고리 페이지 (매장 이름 으로 조회)---------------------------------------*/
	@RequestMapping("find_store_categoryPage")
	public String findStoreCategoryPage(@RequestParam int categoryId, int storeId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		model.addAttribute("storeId",storeId);
		
		if(categoryId ==1){
			return "/WEB-INF/customer/item_list/item_list_each/item_list_food.jsp";
		} else if(categoryId ==2){
			return "/WEB-INF/customer/item_list/item_list_each/item_list_beverage.jsp";
		} else if(categoryId ==3){
			return "/WEB-INF/customer/item_list/item_list_each/item_list_snack.jsp";
		} else if(categoryId ==4){
			return "/WEB-INF/customer/item_list/item_list_each/item_list_icecream.jsp";
		} else if(categoryId ==5){
			return "/WEB-INF/customer/item_list/item_list_each/item_list_daily.jsp";
		} else{
			return "redirect:/customer/find_store_name.do";	//else 때문에 그냥 함
		}
	}
	
	
	/*---------------------------------물품 상세 정보 페이지 (매장도 조회)---------------------------------------*/
	
	@RequestMapping("/item.do")
	public String itemPage(@RequestParam(value="itemName") String itemName, @RequestParam int categoryId, @RequestParam(defaultValue="0") int storeId, ModelMap model){			//들어가야 할 정보는??
		
		if(storeId == 0){
			
			model.addAttribute("item", service.findItemById(itemName));
			
			model.addAttribute("list", service.findItemListByCategorySmallRecommand(categoryId));  
			
			model.addAttribute("store", service.findStoreNameByCount(itemName));
			
			return "/WEB-INF/customer/item_list/item.jsp";
			
		}else{
			
			model.addAttribute("item", service.findItemById(itemName));
			
			model.addAttribute("list", service.findItemListByCategorySmallRecommand(categoryId));  
			
			model.addAttribute("store", service.findStoreNameByCount(itemName));
			
			model.addAttribute("storeId", service.findStoreById(storeId));
			
			return "/WEB-INF/customer/item_list/item.jsp";
		}
		
	
	}


	/*------------------------------------장바구니 추가------------------------------------------*/
	@RequestMapping("/cart")
	public String cart(@RequestParam String storeId, @RequestParam String itemId, @RequestParam String countItem,
						HttpSession session){	//장바구니 페이지 이동 flag필요
		Customer customer = (Customer)session.getAttribute("sessionUser");
		
		String customerId = customer.getCustomerId();
		int storeIdd = Integer.parseInt(storeId);
		int itemIdd = Integer.parseInt(itemId);			//내용들 숫자 변환
		int countItemm = Integer.parseInt(countItem);
		
		service.addCart(customerId, storeIdd, itemIdd, countItemm);
		
		return "/WEB-INF/customer/cart/cart_form.jsp";		//장바구니 페이지
	}
	
	
	/*------------------------------------물품 다중 조회------------------------------------------*/

	@RequestMapping("/search_item")
	public String searchItem(@RequestParam String itemName, ModelMap model){
		
		model.addAttribute("item", service.findItemByNameMany(itemName));
		model.addAttribute("itemName", itemName);
		return "/WEB-INF/customer/item_list/search_item.jsp";
	}

	@RequestMapping("/boardList")
	public String boardList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model) {
		int page = Integer.parseInt(pageNo);
		System.out.println("pageNo = " + page);
		Map map = service.getAllBoard(page);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("pagingBean", map.get("pagingBean"));
		return "/WEB-INF/board/board_customer.jsp";
	}
	
	
	@RequestMapping("/boardInfo")
	public String boardInfo(@RequestParam String boardIndex, ModelMap model) {
		int index = Integer.parseInt(boardIndex);
		Board board = service.getBoardInfo(index);

		System.out.println("--asdfasdf");
		
		model.addAttribute("board", board);
		return "/WEB-INF/board/board_info_customer.jsp";
	}

	
	
}
