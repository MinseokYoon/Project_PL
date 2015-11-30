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
	
	
	//고객 등록 처리 Handler
	@RequestMapping("/add")
	public String add(@ModelAttribute Owner owner, Errors errors,ModelMap model) throws DuplicatedIdException{
		
		new OwnerValidator().validate(owner, errors);
		if(errors.hasErrors()){ 
			return "owner/register_form.tiles";
		}
		
		service.addOwner(owner);
		model.addAttribute("ownerId",owner.getOwnerId());
		return "redirect:/owner/registerSuccess.do";
	}

	
	//점장등록 성공 handler
	@RequestMapping("/registerSuccess")
	public String registerSuccess(@RequestParam String ownerId,ModelMap model) throws OwnerNotFoundException{
		model.addAttribute("owner",service.findOwnerById(ownerId));
		return "owner/register_success.tiles";
	}
	
	
	//점장 Id로 점장 조회 처리 Handler
	@RequestMapping("/fingById")
	public String findById(@RequestParam String ownerId,ModelMap model){
		Owner owner = service.findOwnerById(ownerId);
		model.addAttribute("owner",owner);
		return "owner/owner_info.tiles";
	}
	
	
	//ID 중복 체크
	@RequestMapping("/idDuplicatedCheck")
	@ResponseBody
	public String idDuplicatedCheck(@RequestParam String ownerId) throws OwnerNotFoundException{
		Owner owner = service.findOwnerById(ownerId);
		return String.valueOf(owner!=null); //중복인 경우 "true" 리턴
	}
	
	
	@RequestMapping("/ownerMypageForm")
	public String ownerMypageForm() {
		return "/WEB-INF/mypage/owner/mypage_owner.jsp";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute Owner owner, HttpSession session) throws OwnerNotFoundException{
		service.updateOwner(owner);
		Owner newOwner = service.findOwnerById(owner.getOwnerId());
		session.setAttribute("sessionUser", newOwner);
		return "redirect:/owner/ownerMypageForm.do";
	}
	

	//편의점 재고관리 - 재고현황
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
	
	
	//편의점 재고검색처리 Handler
	@RequestMapping("/product_search")
	public String productSearch(@RequestParam String searchValue,ModelMap model,HttpSession session){
		
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId(); //편의점 주인 Id를 세션에서 가져오는 과정 
		
		String resultValue="";//검색한 값을 담을 변수 
		List<Product> productList = null;
		
		
		resultValue = searchValue;
		productList = service.findProductByName(resultValue,ownerId);
		model.addAttribute("productList",productList); 
		
		
		
		return "/WEB-INF/owner/item_management/product_search_result.jsp";
	}
	
	//편의점 물품 정보
	@RequestMapping("/productInfo")
	public String productStoredAndReleased(@RequestParam String productName,ModelMap model,HttpSession session){
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId();
		//String pName = productName;
		
		Product product = null;
		product = service.findOneProductByName(productName, ownerId); 
		model.addAttribute("product",product);
		
		//System.out.println(product.getItemId());
		//System.out.println(product.getItemName());
		
		return "/WEB-INF/owner/item_management/product_info.jsp";
		
	}
	
	
	//물품 - 입고 처리 Handler
	@RequestMapping("/input")
	public String inputProduct(@RequestParam String itemCount,@RequestParam String inputCount,@RequestParam String productId,@RequestParam String productName,HttpSession session,ModelMap model){
		//System.out.println(itemCount); 물품 개수 따온거
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId();
		String pCount = itemCount;// 원래 물품개수
		String inCount = inputCount;//입고 물품개수
		String pId = productId; //물품 Id
		
		//System.out.println(productId);
		//System.out.println(itemCount + " - " + inputC ount);
		int resultCount = Integer.parseInt(pCount) + Integer.parseInt(inCount);
		//System.out.println(resultCount); //원래 물품 개수 + 입고된 물품 개수 
		int itemId = Integer.parseInt(pId);
		//System.out.println("테스트 : "+resultCount+itemId);
		
		service.updateCountProduct(ownerId,resultCount,itemId);
		
		Product product = null;
		product = service.findOneProductByName(productName, ownerId); 
		model.addAttribute("product",product);
		
		return "/WEB-INF/owner/item_management/product_info.jsp";
		
	}
	
	//물품 - 출고 처리 Handler
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
	
	//주문현황  handler
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
	
	//주문처리 handler - 단순 페이지 이동기능 
	@RequestMapping("/order_processing")
	public String orderProcessing(){
		return "/WEB-INF/owner/order_management/order_processing_form.jsp";
		
	}
	
	//고객이름으로 검색한 주문처리 결과 list handler
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
	
	//휴대폰 번호로 검색한 고객의 주문리스트중에서 선택한 것을 삭제하는 handler
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
	
	//주문완료버튼 클릭해서 처리하는 handler
	@RequestMapping("/orderSuccess")
	public String orderSuccess(@RequestParam String customerId,@RequestParam String storeId){
		
		service.updateAllOrders(customerId,storeId);
		
		return "/WEB-INF/owner/order_management/order_success.jsp";
	}
	
	//본사물품 list 조회하는 handler
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
	
	//본사물품 정보 조회하는 handler
	@RequestMapping("/headOfficeProductInfo")
	public String getHeadOfficeProduct(@RequestParam String productName,ModelMap model){
		
		Product product = service.findHeadOfficeProductByName(productName);
		//System.out.println(product);
		
		
		model.addAttribute("product",product);
		
		return "/WEB-INF/owner/headOfficeItem_management/headOfficeProduct_info.jsp";
	}
	
	//서버물품 테이블에 물품 입고하는 handler
	@RequestMapping("/inputServerItem")
	public String inputHeadOfficeProduct(@RequestParam String itemId,@RequestParam String inputCount,HttpSession session,@RequestParam String year,
										 @RequestParam String month,@RequestParam String day,@RequestParam String itemName){
		Owner owner = (Owner)session.getAttribute("sessionUser");
		String ownerId = owner.getOwnerId();
		
		int y = Integer.parseInt(year);// 여기서 문제, 선택이 안되면 넘어가면 안됨 , 넘겨서 문제임
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(day);
		
		//유통기한 
		Date date = new Date(y,m,d);
		
		
		//본사물품 입고하기전에 체크해야할것, 내 매장에 이미 있는 물품이면 못하게 하기! , itemId로 비교해야 할거 같음 
		Product product = null;
		product = service.findOneProductByName(itemName, ownerId);
		if(product != null){
			return "/WEB-INF/owner/headOfficeItem_management/inputHeadOfficeProduct_fail.jsp";
		}
		
		service.inputHeadOfficeProduct(ownerId,itemId,inputCount,date);
		return "/WEB-INF/owner/headOfficeItem_management/inputHeadOfficeProduct_success.jsp";
	}

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
	
	@RequestMapping("/boardInfo")
	public String boardInfo(@RequestParam String boardIndex, ModelMap model) {
		int index = Integer.parseInt(boardIndex);
		Board board = service.getBoardInfo(index);

		System.out.println("--asdfasdf");
		
		model.addAttribute("board", board);
		return "/WEB-INF/board/board_info_owner.jsp";
	}
	
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "/WEB-INF/board/board_write_owner.jsp";
	}
	
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






