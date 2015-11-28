package kr.or.kosta.pl.controller;

import java.util.HashMap;
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

import kr.or.kosta.pl.exception.CustomerNotFoundException;
import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.service.CustomerService;
import kr.or.kosta.pl.validate.CustomerValidator;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Order;
import kr.or.kosta.pl.vo.Store;

@Controller
@RequestMapping("/customer") 
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	/*-------------------------------위쪽 페이지 이동---------------------------------------*/
	//마이페이지
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, ModelMap model){
		
		Customer customer = (Customer)session.getAttribute("sessionUser");
		List<Order> order = service.findOrderByCusotmerId(customer.getCustomerId());
		
		model.addAttribute("order", order);
		
		return "/WEB-INF/mypage/customer/mypage_customer.jsp";
	}
	
	//장바구니
	@RequestMapping("/cartpage")
	public String cartPage(HttpSession session, ModelMap model){
		
		//if (session.getAttribute("sessionUser").getClass() == Customer.class)
		//세션에서 어떤 유저인지를 검사 하려면!!!
		Customer customer = (Customer)session.getAttribute("sessionUser");
		
		List<Cart> cart = service.findCartByCusotmerId(customer.getCustomerId());
		
		model.addAttribute("cart", cart);
		
		return "/WEB-INF/customer/cart/cart_form.jsp";
	}
	
	/*-----------------------------------회원 정보------------------------------------------------*/
	//회원 가입
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
	//회원 정보 수정
	@RequestMapping("/modify.do")
	public String modify(@ModelAttribute Customer customer, Errors errors, ModelMap model, HttpSession session) throws CustomerNotFoundException {   
	   service.modifyCustomer(customer);
	   session.setAttribute("sessionUser",customer);
	   return "redirect:/customer/mypage.do";
	}
	
	/*----------------------물품 검색 페이지 이동 (좌측 메뉴)-----------------------------------*/
	//카테고리-식품
	@RequestMapping("/form_food.do")
	public String formFood(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_food.jsp";
	}
	//카테고리-음료
	@RequestMapping("/form_beverage.do")
	public String formBeverage(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_beverage.jsp";
	}
	//카테고리-과자
	@RequestMapping("/form_snack.do")
	public String formSnack(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_snack.jsp";
	}
	//카테고리-아이스크림
	@RequestMapping("/form_icecream.do")
	public String formIcecream(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_icecream.jsp";
	}
	//카테고리-생활용품
	@RequestMapping("/form_daily.do")
	public String formDaily(@RequestParam int categoryId, ModelMap model){
		model.addAttribute("list", service.findItemListByCategoryMain(categoryId));
		return "/WEB-INF/customer/item_list/item_list_each/item_list_daily.jsp";
	}
	
	/*----------------------매장검색 페이지 이동 (좌측 메뉴)-----------------------------------*/
	
	@RequestMapping("find_store_name_form.do")
	public String findStore(){
		return "/WEB-INF/customer/find_store/find_store_name.jsp";
	}
	
	@RequestMapping("/find_store_nearby")
	public String findStoreNearby(){
		return "/WEB-INF/customer/find_store/find_store_nearby.jsp";
	}
	
	/*------------------------------------마이 페이지 메뉴------------------------------------------*/
	//포인트 조회
	 @RequestMapping("/form_point.do")
	   public String formPoint(){
	      return "/WEB-INF/mypage/customer/mypage_point.jsp";
	   }
	//회원 정보 수정
	 @RequestMapping("/form_myPage.do")
	   public String formMoify(){
	      return "/WEB-INF/mypage/customer/mypage_modify.jsp";
	   }
	 //이전 주문 목록
	 @RequestMapping("/last_order")
		public String lastOrder(HttpSession session, ModelMap model){
			Customer customer = (Customer)session.getAttribute("sessionUser");
			List<Order> order = service.findOrderByCusotmerIdLast(customer.getCustomerId());
			
			model.addAttribute("order", order);
			return "/WEB-INF/mypage/customer/mypage_last_order.jsp";
		}
	 
	/*------------------------------------물품 다중 조회------------------------------------------*/
	@RequestMapping("/search_item")
	public String searchItem(@RequestParam String itemName, ModelMap model){
		
		model.addAttribute("item", service.findItemByNameMany(itemName));
		model.addAttribute("itemName", itemName);
		return "/WEB-INF/customer/item_list/search_item.jsp";
	}
	
	/*---------------------------------매장검색 시 사용되는 controller---------------------------------------*/
	//매장 이름 조회
	@RequestMapping("find_store_name.do")
	public String findStore(@RequestParam(value="findStoreName") String storeName, ModelMap model){
		List<Store> list = service.findStoreName(storeName);
		model.addAttribute("findstore",list);
		return "/WEB-INF/customer/find_store/find_store_name_success.jsp";
	}
	//카테고리 페이지로 이동 (매장 이름 으로 조회 후)
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
	
	/*---------------------------------물품 상세 정보 페이지---------------------------------------*/
	
	@RequestMapping("/item.do")
	public String itemPage(@RequestParam(value="itemName") String itemName, @RequestParam int categoryId, @RequestParam(defaultValue="0") int storeId, ModelMap model){
		
		//물품검색으로 왔을 경우, 매장이름 검색으로 왔을 경우 공통 
		model.addAttribute("item", service.findItemById(itemName));
		
		model.addAttribute("list", service.findItemListByCategorySmallRecommand(categoryId));  
		
		model.addAttribute("store", service.findStoreNameByCount(itemName));
		
		if(storeId != 0){	//매장이름 검색으로 왔을 경우
			model.addAttribute("storeId", service.findStoreById(storeId));
		}
		return "/WEB-INF/customer/item_list/item.jsp";
	}


	/*------------------------------------장바구니------------------------------------------*/
	//장바구니 추가
	@RequestMapping("/cart")
	public String cart(@RequestParam String storeId, @RequestParam String itemId, @RequestParam String countItem,
						HttpSession session){	//장바구니 페이지 이동 flag필요
		Customer customer = (Customer)session.getAttribute("sessionUser");
		
		String customerId = customer.getCustomerId();
		int storeIdd = Integer.parseInt(storeId);
		int itemIdd = Integer.parseInt(itemId);			//내용들 숫자 변환
		int countItemm = Integer.parseInt(countItem);
		
		service.addCart(customerId, storeIdd, itemIdd, countItemm);
		
		return "redirect:/customer/cartpage.do";		//장바구니 페이지
	}
	//장바구니 삭제
	@RequestMapping("/deleteCart")
	public String deleteCart(@RequestParam String customerId, @RequestParam int storeId, @RequestParam int itemId){
		
		service.deleteCart(customerId, storeId, itemId);
		
		return "redirect:/customer/cartpage.do";
	}
	/*------------------------------------주문------------------------------------------*/
	@RequestMapping("/order")
	public String order(@RequestParam String[] customerId, @RequestParam int[] storeId, @RequestParam int[] itemId, 
						@RequestParam int[] orderCount, @RequestParam int[] orderStatus){
		for(int i = 0; i <customerId.length; i++){
			service.addOrder(customerId[i], storeId[i], itemId[i], orderCount[i], orderStatus[i]);
		}
		
		for(int i = 0; i <customerId.length; i++){
			service.deleteCart(customerId[i], storeId[i], itemId[i]);
		}
		return "redirect:/basic/item_list.do";
	}

	/*------------------------------------게시판 조회------------------------------------------*/
	@RequestMapping("/boardList")
	public String boardList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model) {
		int page = Integer.parseInt(pageNo);
		System.out.println("pageNo = " + page);
		List<Board> notice = service.getNotice();
		Map map = service.getAllBoard(page);
		
		model.addAttribute("notice", notice);
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
	
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "/WEB-INF/board/board_write_customer.jsp";
	}
	
	@RequestMapping("/insertBoard")
	public String insertBoard(@RequestParam String boardTitle, String boardContent, String boardWriter) {
//			System.out.println("제목 : " + boardTitle);
//			System.out.println("작성자 : " + boardWriter);
//			System.out.println("내용 : " + boardContent.copyValueOf(boardContent.toCharArray(), 0, boardContent.length()-13));
		HashMap map = new HashMap();
		map.put("boardTitle", boardTitle);
		map.put("boardWriter", boardWriter);
		map.put("boardContent", boardContent.copyValueOf(boardContent.toCharArray(), 0, boardContent.length()-13));
		service.insertBoard(map);
		return "redirect:/customer/boardList.do";
	}
}
