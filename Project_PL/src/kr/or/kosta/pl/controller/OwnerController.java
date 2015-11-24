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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.exception.OwnerNotFoundException;
import kr.or.kosta.pl.service.OwnerService;
import kr.or.kosta.pl.validate.OwnerValidator;
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
	
	
	//물품관리 처리 Handler
/*	@RequestMapping("/product_list")
	public String list(@RequestParam(defaultValue="1") String pageNo,ModelMap model){
		int page=1;
		try{
			page = Integer.parseInt(pageNo); //null일 경우 예외처리를 통해 page를 1로 처리
		}catch(NumberFormatException e){}
		
		Map attributes = service.getAllProductsPaging(page);
		model.addAllAttributes(attributes);
		return "owner/product_list.tiles";
	}*/
	
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
		return "/WEB-INF/mypage/owner/mypage_owner.jsp";
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
}