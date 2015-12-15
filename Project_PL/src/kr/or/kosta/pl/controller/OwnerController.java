package kr.or.kosta.pl.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.exception.OwnerNotFoundException;
import kr.or.kosta.pl.service.OwnerService;
import kr.or.kosta.pl.validate.OwnerValidator;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;

@Controller
@RequestMapping("/owner")
public class OwnerController {
	
	@Autowired
	private OwnerService service;
	
	
	/*----------------------------------------편의점 주인 관련 컨트롤러---------------------------------------*/
	//관리자가 편의점주인 등록하는 컨트롤러
	@RequestMapping("/add")
	public String add(@ModelAttribute Owner owner, Errors errors,ModelMap model) throws DuplicatedIdException{
		
		new OwnerValidator().validate(owner, errors);
		if(errors.hasErrors()){ 
			return "/WEB-INF/admin/store_management/owner_register/register_owner_form_admin.jsp";
		}
		
		service.addOwner(owner);
		model.addAttribute("ownerId",owner.getOwnerId());
		return "redirect:/owner/registerSuccess.do";
	}

	
	//편의점주인 등록 성공처리하는 컨트롤러
	@RequestMapping("/registerSuccess")
	public String registerSuccess(@RequestParam String ownerId,ModelMap model) throws OwnerNotFoundException{
		model.addAttribute("owner",service.findOwnerById(ownerId));
		return "/WEB-INF/admin/store_management/owner_register/register_owner_success_admin.jsp";
	}
	
	
	//편의점주인 Id로 편의점주인들 리스트 뽑는 컨트롤러
	@RequestMapping("/findById")
	public String findById(@RequestParam String ownerId,ModelMap model){
		Owner owner = service.findOwnerById(ownerId);
		model.addAttribute("owner",owner);
		return "/WEB-INF/admin/store_management/owner_register/owner_info.jsp";
	}
	
	
	//편의점주인 Id중복 처리하는 컨트롤러
	@RequestMapping("/idDuplicatedCheck")
	@ResponseBody
	public String idDuplicatedCheck(@RequestParam String ownerId) throws OwnerNotFoundException{
		Owner owner = service.findOwnerById(ownerId);
		return String.valueOf(owner!=null); //중복인 경우 "true" 리턴
	}
	
	
	//편의점주인 마이페이지로 이동 처리하는 컨트롤러
	@RequestMapping("/ownerMypageForm")
	public String ownerMypageForm() {
		return "/WEB-INF/mypage/owner/mypage_owner.jsp";
	}
	
	
	//편의점주인 수정하는 컨트롤러
	@RequestMapping("/modify")
	public String modify(@ModelAttribute Owner owner, HttpSession session) throws OwnerNotFoundException{
		service.updateOwner(owner);
		Owner newOwner = service.findOwnerById(owner.getOwnerId());
		session.setAttribute("sessionUser", newOwner);
		return "redirect:/owner/ownerMypageForm.do";
	}
	//Owner List 조회
	@RequestMapping("/ownerList")
	public String ownerList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model) {
		int page = 1;
		try {
			page = Integer.parseInt(pageNo); // null일 경우 예외처리를 통해 page를 1로
			// 처리한다..
		} catch (NumberFormatException e) {
		}
		Map attributes = service.getAllOwnersPaging(page);
		model.addAllAttributes(attributes);
		return "/WEB-INF/admin/store_management/store_register/ownerList.jsp";
	}
	
	
	/*----------------------------------------편의점 재고관리 관련된 컨트롤러---------------------------------------*/
	//편의점 재고현황 리스트 뽑는 컨트롤러
	@RequestMapping("/product_list")
	public String list(@RequestParam(defaultValue="1") String pageNo, ModelMap model, HttpSession session){
		int page = 1;
		try{
			page = Integer.parseInt(pageNo);
		}catch(NumberFormatException e){}
		
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId(); //편의점 주인 Id를 세션에서 가져오는 과정 
		
		Map attributes = service.getAllProductsPaging(page,ownerId);
		model.addAllAttributes(attributes);
		
		return "/WEB-INF/owner/item_management/product_list.jsp";
	}
	
	
	//편의점 재고검색 처리하는 컨틀로러
	@RequestMapping("/product_search")
	public String productSearch(@RequestParam String searchValue,ModelMap model,HttpSession session){
		
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId(); //편의점 주인 Id를 세션에서 가져오는 과정 
		
		List<Product> productList = null;
		
		productList = service.findProductByName(searchValue,ownerId);
		model.addAttribute("productList",productList); 
		
		return "/WEB-INF/owner/item_management/product_search_result.jsp";
	}
	
	//본사물품 재고검색 처리하는 컨트롤러
	@RequestMapping("/HeadOfficeProduct_search")
	public String HeadOfficeProductSearch(@RequestParam String searchValue,ModelMap model,HttpSession session){
		//Owner owner = (Owner)session.getAttribute("sessionUser");
		//String ownerId = owner.getOwnerId(); //편의점 주인 Id를 세션에서 가져오는 과정 
		
		List<Product> HeadOfficeProductList = null;
		HeadOfficeProductList = service.findHeadOfficeProductBySearchName(searchValue);
		model.addAttribute("HeadOfficeProductList",HeadOfficeProductList); 
		
		return "/WEB-INF/owner/headOfficeItem_management/HeadOfficeProduct_search_result.jsp";
	}
	
	
	//편의점 물품 정보처리하는 컨트롤러
	@RequestMapping("/productInfo")
	public String productStoredAndReleased(@RequestParam String productName,ModelMap model,HttpSession session){
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId();
		
		Product product = null;
		product = service.findOneProductByName(productName, ownerId); 
		model.addAttribute("product",product);
		
		return "/WEB-INF/owner/item_management/product_info.jsp";
	}
	
	
	//본사물품 리스트 조회하는 컨트롤러
	@RequestMapping("/headOfficeProducts_list")
	public String getHeadOfficeProductsList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model,HttpSession session){
		int page=1;
		try{
			page = Integer.parseInt(pageNo);
		}catch(NumberFormatException e){}
		
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId(); //편의점 주인 Id를 세션에서 가져오는 과정 
		
		Map attributes = service.getAllHeadOfficeProductsListPaging(page,ownerId);
		model.addAllAttributes(attributes);
		
		return "/WEB-INF/owner/headOfficeItem_management/headOfficeProducts_list.jsp";
	}
	
	
	//본사물품 물품정보 조회하는 컨트롤러
	@RequestMapping("/headOfficeProductInfo")
	public String getHeadOfficeProduct(@RequestParam String productName,ModelMap model){
		
		Product product = service.findHeadOfficeProductByName(productName);
		//System.out.println(product);
		
		
		model.addAttribute("product",product);
		
		return "/WEB-INF/owner/headOfficeItem_management/headOfficeProduct_info.jsp";
	}
	
	
	//서버물품 테이블에 물품 입고하는 컨트롤러
	@RequestMapping("/inputServerItem")
	public String inputHeadOfficeProduct(@RequestParam String itemId,@RequestParam String inputCount,HttpSession session,@RequestParam String year,
										 @RequestParam String month,@RequestParam String day,@RequestParam String itemName){
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId();
		
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(day);
		
		//유통기한 
		Date date = new Date(y,m,d);
		
		Product product = null;
		product = service.findOneProductByName(itemName, ownerId);
		if(product != null){
			return "/WEB-INF/owner/headOfficeItem_management/inputHeadOfficeProduct_fail.jsp";
		}
		
		service.inputHeadOfficeProduct(ownerId,itemId,inputCount,date);
		return "/WEB-INF/owner/headOfficeItem_management/inputHeadOfficeProduct_success.jsp";
	}
	
	
	/*----------------------------------------편의점 주문처리 관련된 컨트롤러---------------------------------------*/
	//물품 입고처리하는 컨트롤러
	@RequestMapping("/input")
	public String inputProduct(@RequestParam String itemCount,@RequestParam String inputCount,@RequestParam String productId,@RequestParam String productName,HttpSession session,ModelMap model){
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId();
		String pCount = itemCount;// 원래 물품개수
		String inCount = inputCount;//입고 물품개수
		String pId = productId; //물품 Id
		
		int resultCount = Integer.parseInt(pCount) + Integer.parseInt(inCount);
		int itemId = Integer.parseInt(pId);
		
		service.updateCountProduct(ownerId,resultCount,itemId);
		
		Product product = null;
		product = service.findOneProductByName(productName, ownerId); 
		model.addAttribute("product",product);
		
		return "/WEB-INF/owner/item_management/product_info.jsp";
		
	}
	
	
	//물품 출고처리하는 컨트롤러
	@RequestMapping("/output")
	public String outputProduct(@RequestParam String itemCount,@RequestParam String outputCount,@RequestParam String productId,@RequestParam String productName,HttpSession session,ModelMap model){
		//System.out.println(itemCount); 물품 개수 따온거
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId();
		String pCount = itemCount;// 원래 물품개수
		String outCount = outputCount;//입고 물품개수
		String pId = productId; //물품 Id
		
		int resultCount = Integer.parseInt(pCount) - Integer.parseInt(outCount);
		int itemId = Integer.parseInt(pId);
		
		service.updateCountProduct(ownerId,resultCount,itemId);
		
		Product product = null;
		product = service.findOneProductByName(productName, ownerId); 
		model.addAttribute("product",product);
		
		return "/WEB-INF/owner/item_management/product_info.jsp";
		
	}
	
	
	//주문현황 리스트 처리하는 컨트롤러
	@RequestMapping("/order_list")
	public String orderList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model,HttpSession session){
		int page=1;
		try{
			page = Integer.parseInt(pageNo);
		}catch(NumberFormatException e){}
		
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId(); //편의점 주인 Id를 세션에서 가져오는 과정 
		
		Map attributes = service.getAllOrderListPaging(page,ownerId);
		model.addAllAttributes(attributes);
		
		return "/WEB-INF/owner/order_management/order_list.jsp";
	}
	
	
	//주문처리하는 페이지로 이동하는 컨트롤러
	@RequestMapping("/order_processing")
	public String orderProcessing(){
		return "/WEB-INF/owner/order_management/order_processing_form.jsp";
		
	}
	
	
	//고객핸드폰번호로 검색해서 주문현황 리스트 뽑아오는 컨트롤러
	@RequestMapping("/order_list_by_cutomerPhone")
	public String orderProcessingResult(@RequestParam String customerPhone,@RequestParam(defaultValue = "1") String pageNo,ModelMap model,HttpSession session){
		int page=1;
		try{
			page = Integer.parseInt(pageNo);
		}catch(NumberFormatException e){}
		
		
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId(); //편의점 주인 Id를 세션에서 가져오는 과정 
		
		Map attributes = service.getOrderListByPhonePaging(page,ownerId,customerPhone);
		model.addAllAttributes(attributes);
		
		return "/WEB-INF/owner/order_management/order_list_by_customerPhone.jsp";
	}
	
	
	//고객 휴대폰번호로 조회한 주문현황 중에서 선택한 것을 주문취소(삭제)하는 컨트롤러
	@RequestMapping("/updateOrderStatus")
	public String deleteOrderBySelect(@RequestParam String orderNumber,@RequestParam String customerPhone,@RequestParam(defaultValue = "1") String pageNo,ModelMap model,HttpSession session){
		int page=1;
		try{
			page = Integer.parseInt(pageNo);
		}catch(NumberFormatException e){}
		
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId(); //편의점 주인 Id를 세션에서 가져오는 과정 
		
		Map attributes = service.updateOrderBySelect(page,ownerId,customerPhone,orderNumber);
		model.addAllAttributes(attributes);
		
		return "/WEB-INF/owner/order_management/order_list_by_customerPhone.jsp";
	}
	
	
	//고객이 주문한 모든 주문리스트들을 완료버튼 클릭해서 처리하는 컨트롤러
	@RequestMapping("/orderSuccess")
	public String orderSuccess(@RequestParam String[] customerId,@RequestParam int[] storeId, @RequestParam int[] itemId,
							   @RequestParam int[]orderCount, @RequestParam int[] customerPoint){
		
		service.updateAllOrders(customerId[0],storeId[0],itemId, orderCount, customerPoint[0]);
		
		return "/WEB-INF/owner/order_management/order_success.jsp";
	}
	
	
	
	/*----------------------------------------편의점주인 게시판 관련 컨트롤러---------------------------------------*/
	//게시판 리스트 조회하는 컨트롤러
	@RequestMapping("/boardList")
	public String boardList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model) {
		int page = Integer.parseInt(pageNo);
		System.out.println("pageNo = " + page);
		List<Board> notice = service.getNotice();
		Map map = service.getAllBoard(page);

		model.addAttribute("notice", notice);
		model.addAttribute("list", map.get("list"));
		model.addAttribute("pagingBean", map.get("pagingBean"));
		return "/WEB-INF/board/board_owner.jsp";
	}
	
	
	//게시판 글 하나 보는 컨트롤러
	@RequestMapping("/boardInfo")
	public String boardInfo(@RequestParam String boardIndex, ModelMap model) {
		int index = Integer.parseInt(boardIndex);
		Board board = service.getBoardInfo(index);

		System.out.println("--asdfasdf");
		
		model.addAttribute("board", board);
		return "/WEB-INF/board/board_info_owner.jsp";
	}
	
	
	//게시판 글 작성 컨트롤러
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "/WEB-INF/board/board_write_owner.jsp";
	}
	
	
	//게시판 글 입력 컨트롤러
	@RequestMapping("/insertBoard")
	public String insertBoard(@RequestParam String boardTitle, String boardContent, String boardWriter) {
//			System.out.println("제목 : " + boardTitle);
//			System.out.println("작성자 : " + boardWriter);
//			System.out.println("내용 : " + boardContent.copyValueOf(boardContent.toCharArray(), 0, boardContent.length()-13));
		HashMap map = new HashMap();
		map.put("boardTitle", boardTitle);
		map.put("boardWriter", boardWriter);
//		map.put("boardContent", boardContent.copyValueOf(boardContent.toCharArray(), 0, boardContent.length()-13));
		map.put("boardContent", boardContent);
		service.insertBoard(map);
		return "redirect:/owner/boardList.do";
	}
}






