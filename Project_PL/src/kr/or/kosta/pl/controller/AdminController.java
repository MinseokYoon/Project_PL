package kr.or.kosta.pl.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import kr.or.kosta.pl.exception.AdminNotFoundException;
import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.service.AdminService;
import kr.or.kosta.pl.service.OwnerService;
import kr.or.kosta.pl.validate.AdminValidator;
import kr.or.kosta.pl.validate.CategoryValidator;
import kr.or.kosta.pl.validate.ProductValidator;
import kr.or.kosta.pl.validate.StoreValidator;
import kr.or.kosta.pl.vo.Admin;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Category;
import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;

	@Autowired
	private OwnerService ownerService;

	
	//////////////////////////// 관리자 ///////////////////////////
	
	// ID로 관리자 조회
	@RequestMapping("/findById")
	public String findById(@RequestParam String adminId, ModelMap model) {
		Admin admin = service.findAdminById(adminId);
		model.addAttribute("admin", admin);
		return "test1/admin_info.tiles";
	}

	// 관리자 List 조회처리
	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue = "1") String pageNo, ModelMap model) {
		int page = 1;
		try {
			page = Integer.parseInt(pageNo); // null일 경우 예외처리를 통해 page를 1로
												// 처리한다..
		} catch (NumberFormatException e) {
		}
		Map attributes = service.getAllAdminsPaging(page);
		model.addAllAttributes(attributes);
		return "test1/list.tiles";
	}

	// 관리자 등록
	@RequestMapping("/add")
	public String add(@ModelAttribute Admin admin, Errors errors, ModelMap model) throws DuplicatedIdException {
		new AdminValidator().validate(admin, errors);
		if (errors.hasErrors()) {
			return "test1/register_form.tiles";
		}

		service.addAdmin(admin);
		model.addAttribute("adminId", admin.getAdminId());
		return "redirect:/admin/registerSuccess.do";
	}

	// 등록 후 성공페이지로 이동 처리.
	@RequestMapping("/registerSuccess")
	public String registerSuccess(@RequestParam String adminId, ModelMap model) {

		model.addAttribute("admin", service.findAdminById(adminId));
		return "test1/register_success.tiles";
	}

	// 수정폼 조회
	@RequestMapping("/modifyForm")
	public String modifyForm(@RequestParam(defaultValue = "") String adminId, ModelMap model) throws Exception {
		// 요청파라미터 검증
		if (adminId.trim().length() == 0) {
			throw new Exception("수정할 관리자의 아이디가 없습니다.");
		}

		model.addAttribute("admin", service.findAdminById(adminId));

		return "test1/modify_form.tiles";
	}

	// 수정 처리
	@RequestMapping("/modify")
	public String modify(@ModelAttribute Admin admin, HttpSession session) throws AdminNotFoundException {
		service.updateAdmin(admin);
		Admin newAdmin = service.findAdminById(admin.getAdminId());
		session.setAttribute("sessionUser", newAdmin);
		return "redirect:/admin/adminMypageForm.do";
	}

	// 관리자 삭제 처리 
	@RequestMapping("/remove.do")
	public String remove(@RequestParam(defaultValue = "") String adminId,
			@RequestParam(defaultValue = "1") String pageNo, ModelMap model) throws Exception {

		if (adminId.trim().length() == 0) {
			throw new Exception("삭제할 관리자의 아이디가 없습니다.");
		}
		
		service.removeAdmin(adminId);
		model.addAttribute("pageNo", pageNo);
		return "redirect:/admin/list.do";
	}

	// 관리자 ID 중복 체크
	@RequestMapping("/idDuplicatedCheck")
	@ResponseBody
	public String idDuplicatedCheck(@RequestParam String adminId) {
		Admin ad = service.findAdminById(adminId);
		return String.valueOf(ad != null); // 중복인 경우 "true" 리턴
	}

	//////////////////////////// 물품관리 ///////////////////////////////////////////////////

	// ID로 물품 조회
	@RequestMapping("/findByItemId")
	public String findByItemId(@RequestParam int itemId, @RequestParam String categoryName, ModelMap model) {
		Product product = service.findProductByItemId(itemId);
		model.addAttribute("product", product);
		model.addAttribute("category", categoryName);
		return "/WEB-INF/admin/item_management/product_info.jsp";
	}

	// 품명으로 물품조회
	@RequestMapping("/productsByItemName")
	public String ProductsByItemName(@RequestParam String searchValue, ModelMap model) {

		String resultValue = "";
		List<Product> product = null;

		resultValue = searchValue;
		product = service.findProductByItemName(resultValue);
		model.addAttribute("product", product);
		return "/WEB-INF/admin/item_management/itemSearch_result.jsp";

	}

	// 물품 List 조회
	@RequestMapping("/itemList")
	public String itemList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model, HttpSession session) {
		int page = 1;
		try {
			page = Integer.parseInt(pageNo); // null일 경우 예외처리를 통해 page를 1로
												// 처리한다..
		} catch (NumberFormatException e) {
		}

		Admin admin = (Admin) session.getAttribute("sessionUser");
		String adminId = admin.getAdminId();
		Map attributes = service.getAllProductsPaging(page);
		model.addAllAttributes(attributes);
		return "/WEB-INF/admin/item_management/itemList.jsp";
	}

	// 물품 등록폼 조회
	@RequestMapping("/itemForm")
	public String itemForm(ModelMap model) throws Exception {
		model.addAttribute("category", service.findcategoryList());
		return "/WEB-INF/admin/item_management/itemRegister_form.jsp";
	}

	// 물품 등록
	@RequestMapping("/itemAdd")
	public String itemAdd(@RequestParam String itemName, @RequestParam int itemPrice,@RequestParam int categoryId, MultipartFile upImage,
			ModelMap model) throws DuplicatedIdException, IOException {
		service.addProduct(itemName, itemPrice, categoryId);

		if (upImage != null && !upImage.isEmpty()) {
			String fileName = itemName + ".png";
			long filesize = upImage.getSize();
			System.out.println(fileName + " - " + filesize);
			//String dir = "C:\\Java\\eclipse\\workspace_framework\\Project_PL\\WebContent\\images\\" + categoryId + "\\";
			/*String dir = "C:\\Users\\kosta\\Desktop\\programs\\java\\eclipse-jee-mars-R-win32-x86_64\\"
					+ "eclipse\\workspace_framework\\Project_PL\\WebContent\\images\\"
					+ categoryId + "\\";*/
			String dir = "C:\\Java\\apache-tomcat-8.0.26\\webapps\\Project_PL\\images\\"+ categoryId + "\\";
			
			File upImag = new File(dir, fileName);
			System.out.println("222222222");
			upImage.transferTo(upImag);
			System.out.println("33333333");
		}/*else{
			return "/WEB-INF/admin/item_management/itemRegister_form.jsp";
		}*/
		
		model.addAttribute("itemName", itemName);

		return "redirect:/admin/itemRegisterSuccess.do";
	}

	// 물품 등록 후 성공페이지로 이동 처리.
	@RequestMapping("/itemRegisterSuccess")
	public String itemRegisterSuccess(@RequestParam String itemName, ModelMap model) {
		model.addAttribute("product", service.findProductByName(itemName));
	
		return "/WEB-INF/admin/item_management/itemRegister_success.jsp";
	}

	
	// 물품 수정폼 조회
	@RequestMapping("/itemModifyForm")
	public String itemModifyForm(@RequestParam(defaultValue = "") int itemId,@RequestParam String categoryName, ModelMap model) throws Exception {
		// 요청파라미터 검증
		String item = Integer.toString(itemId);
		if (item.trim().length() == 0) {
			throw new Exception("수정할 물품의 아이디가 없습니다.");
		}

		model.addAttribute("category", service.findcategoryList());
		model.addAttribute("product", service.findProductByItemId(itemId));
		model.addAttribute("categoryName", categoryName);
		return "/WEB-INF/admin/item_management/itemModify_form.jsp";
	}

	// 수정 처리
	@RequestMapping("/itemModify")
	   public String itemModify(@RequestParam int itemId,@RequestParam String itemName,@RequestParam int itemPrice,@RequestParam int categoryId, MultipartFile upImage, 
	         @RequestParam(defaultValue = "1") String pageNo, ModelMap model) throws Exception, IOException  {
		//System.out.println("44444444444");
	      service.updateProduct(itemId, itemName, itemPrice, categoryId);
	     // System.out.println("5555555555555555");
	      if (upImage != null && !upImage.isEmpty()) {
	         String fileName = itemName + ".png";
	         long filesize = upImage.getSize();
	         System.out.println(fileName + " - " + filesize);
	         //String dir = "C:\\Java\\eclipse\\workspace_framework\\Project_PL\\WebContent\\images\\" + categoryId + "\\";
	         /*String dir = "C:\\Users\\kosta\\Desktop\\programs\\java\\eclipse-jee-mars-R-win32-x86_64\\"
						+ "eclipse\\workspace_framework\\Project_PL\\WebContent\\images\\"
						+ categoryId + "\\";*/
	         String dir = "C:\\Java\\apache-tomcat-8.0.26\\webapps\\Project_PL\\images\\"+ categoryId + "\\";
	         
	         File upImag = new File(dir, fileName);
	 
	         upImage.transferTo(upImag);
	         
	      }
	      model.addAttribute("itemId", itemId);
	      model.addAttribute("itemName", itemName);
	      model.addAttribute("pageNo", pageNo);
	      model.addAttribute("upImage", upImage);
	      
	      return "redirect:/admin/itemRegisterSuccess2.do";
	   }
	
	// 물품 수정 후 성공페이지로 이동 처리.
    @RequestMapping("/itemRegisterSuccess2")
    public String itemRegisterSuccess2(@RequestParam(defaultValue = "") int itemId, @RequestParam String itemName, ModelMap model) {
       model.addAttribute("product", service.findProductByName(itemName));
       return "/WEB-INF/admin/item_management/itemRegister_success.jsp";
    }
	

	// 삭제 처리 HandlerattributeValue
	@RequestMapping("/itemRemove.do")
	public String itemRemove(@RequestParam(defaultValue = "") int itemId,
			@RequestParam(defaultValue = "1") String pageNo, ModelMap model) throws Exception {
		String item = Integer.toString(itemId);
		if (item.trim().length() == 0) {
			throw new Exception("삭제할 물품의 아이디가 없습니다.");
		}
		
		service.removeProduct(itemId);
		model.addAttribute("pageNo", pageNo);
		return "redirect:/admin/itemList.do";
	}

	//물품 Name 중복 체크
	@RequestMapping("/idDuplicatedCheck2")
	@ResponseBody
	public String idDuplicatedCheck2(@RequestParam String itemName) {
		Product pro = service.findProductByName(itemName);
		return String.valueOf(pro != null); // 중복인 경우 "true" 리턴
	}

	//////////////////////////// 카테고리////////////////////////////
	// 카테고리 등록
	@RequestMapping("/categoryAdd")
	public String categoryAdd(@ModelAttribute Category category, @RequestParam int categoryId, Errors errors, ModelMap model)
			throws DuplicatedIdException {

		new CategoryValidator().validate(category, errors);
		if (errors.hasErrors()) {
			return "/WEB-INF/admin/item_management/categoryList.jsp";
		}
		File dir = new File("C:\\Java\\apache-tomcat-8.0.26\\webapps\\Project_PL\\images\\"+ categoryId + "\\");
	
		boolean flag = dir.exists();
		System.out.println(flag);
		
		
		 if (!dir.exists()) { //폴더 없으면 폴더 생성
	            dir.mkdirs();
	        }
		
		service.addCategory(category);
		model.addAttribute("categoryId", category.getCategoryId());
		return "redirect:/admin/categoryRegisterSuccess.do";

	}

	// 카테고리 등록 후 성공페이지로 이동 처리.
	@RequestMapping("/categoryRegisterSuccess")
	public String categoryRegisterSuccess(@RequestParam int categoryId, ModelMap model) {

		model.addAttribute("category", service.findCategoryById(categoryId));
		return "/WEB-INF/admin/item_management/categoryRegister_success.jsp";
	}

	// ID로 카테고리 조회
	@RequestMapping("/findByCategoryId")
	public String findByCategoryId(@RequestParam int categoryId, ModelMap model) {
		Category category = service.findCategoryById(categoryId);
		model.addAttribute("category", category);
		return "/WEB-INF/admin/item_management/category_info.jsp";
	}

	// 삭제 처리 
	@RequestMapping("/categoryRemove.do")
	public String categoryRemove(@RequestParam(defaultValue = "") int categoryId,
			@RequestParam(defaultValue = "1") String pageNo, ModelMap model) throws Exception {
		String category = Integer.toString(categoryId);
		if (category.trim().length() == 0) {
			throw new Exception("삭제할 카테고리의 아이디가 없습니다.");
		}
		// 비지니스 로직 - 삭제처리()
		service.removeCategory(categoryId);
		model.addAttribute("pageNo", pageNo);
		return "redirect:/admin/categoryList.do";
	}

	// 카테고리 List 조회
	@RequestMapping("/categoryList")
	public String categoryList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model) {
		int page = 1;
		try {
			page = Integer.parseInt(pageNo); // null일 경우 예외처리를 통해 page를 1로
			// 처리한다..
		} catch (NumberFormatException e) {
		}
		Map attributes = service.getAllCategorysPaging(page);
		model.addAllAttributes(attributes);
		return "/WEB-INF/admin/item_management/categoryList.jsp";
	}

	//카테고리 ID 중복 체크
	@RequestMapping("/idDuplicatedCheck3")
	@ResponseBody
	public String idDuplicatedCheck3(@RequestParam int categoryId) {
		Category cate = service.findCategoryById(categoryId);
		return String.valueOf(cate != null); // 중복인 경우 "true" 리턴
	}

	//////////////////////////// 편의점 ////////////////////////////

	// ID로 편의점 조회
	@RequestMapping("/storeFindById")
	public String storeFindById(@RequestParam int storeId, ModelMap model) {
		Store store = service.findStoreById(storeId);
		model.addAttribute("store", store);
		return "/WEB-INF/admin/store_management/store_register/store_info.jsp";
	}

	// 편의점 명으로 편의점 정보 조회
	@RequestMapping("/StoreByName")
	public String StoreByName(@RequestParam String searchValue, ModelMap model) {

		String resultValue = "";
		List<Store> store = null;

		resultValue = searchValue;
		store = service.findStoreByName(resultValue);
		model.addAttribute("store", store);
		return "/WEB-INF/admin/store_management/store_register/storeSearch_result.jsp";

	}

	// 편의점 List 조회
	@RequestMapping("/storeList")
	public String storeList(@RequestParam(defaultValue = "1") String pageNo, ModelMap model, HttpSession session) {
		int page = 1;
		try {
			page = Integer.parseInt(pageNo); // null일 경우 예외처리를 통해 page를 1로
												// 처리한다..
		} catch (NumberFormatException e) {
		}
	
		Admin admin = (Admin) session.getAttribute("sessionUser");
		String adminId = admin.getAdminId();

		Map attributes = service.getAllStoresPaging(page);
		model.addAllAttributes(attributes);
		return "/WEB-INF/admin/store_management/store_register/storeList.jsp";
	}

	// 편의점 등록
	@RequestMapping("/storeAdd")
	public String storeAdd(@ModelAttribute Store store, Errors errors, ModelMap model) throws DuplicatedIdException {
		new StoreValidator().validate(store, errors);
		if (errors.hasErrors()) {
			return "/WEB-INF/admin/store_management/store_register/register_store_form_admin.jsp";
		}

		service.addStore(store);
		model.addAttribute("storeId", store.getStoreId());
		return "redirect:/admin/storeRegisterSuccess.do";
	}

	// 편의점 등록 후 성공페이지로 이동 처리.
	@RequestMapping("/storeRegisterSuccess")
	public String storeRegisterSuccess(@RequestParam int storeId, ModelMap model) {

		model.addAttribute("store", service.findStoreById(storeId));
		return "/WEB-INF/admin/store_management/store_register/register_store_success_admin.jsp";
	}

	// 편의점 수정폼 조회
	@RequestMapping("/storeModifyForm")
	public String storeModifyForm(@RequestParam(defaultValue = "") int storeId, ModelMap model) throws Exception {
		// 요청파라미터 검증
		String store = Integer.toString(storeId);
		if (store.trim().length() == 0) {
			throw new Exception("수정할 편의점의 아이디가 없습니다.");
		}
		model.addAttribute("store", service.findStoreById(storeId));
		return "/WEB-INF/admin/store_management/store_register/storeModify_form.jsp";
	}

	// 수정 처리
	@RequestMapping("/storeModify")
	public String storeModify(@ModelAttribute Store store, Errors errors,
			@RequestParam(defaultValue = "1") String pageNo, ModelMap model) throws Exception {
		
		service.updateStore(store);
		model.addAttribute("storeId", store.getStoreId());
		model.addAttribute("pageNo", pageNo);

		return "redirect:/admin/storeFindById.do";
	}

	// 삭제 처리 
	@RequestMapping("/storeRemove.do")
	public String storeRemove(@RequestParam(defaultValue = "") int storeId,
			@RequestParam(defaultValue = "1") String pageNo, ModelMap model) throws Exception {
		String store = Integer.toString(storeId);
		if (store.trim().length() == 0) {
			throw new Exception("삭제할 편의점의 아이디가 없습니다.");
		}
		// 비지니스 로직 - 삭제처리
		service.removeStore(storeId);
		model.addAttribute("pageNo", pageNo);
		return "redirect:/admin/storeList.do";
	}
	
	//편의점 ID 중복 체크
	@RequestMapping("/idDuplicatedCheck4")
	@ResponseBody
	public String idDuplicatedCheck4(@RequestParam int storeId) {
		Store sto = service.findStoreById(storeId);
		return String.valueOf(sto != null); // 중복인 경우 "true" 리턴
	}
	
	
	@RequestMapping("/findByOwnerIdJson.do")
	@ResponseBody
	public String findByOwnerIdJson(@RequestParam String ownerId) {
		System.out.println("JSP에서 받은 값 :" + ownerId);
		System.out.println("컨트롤러에서 JSP에값을 받아 조회한 값 : " + service.selectByOwnerId(ownerId));
		Store store = service.selectByOwnerId(ownerId);
		Owner owner = ownerService.findOwnerById(ownerId);
		System.out.println(owner);
		System.out.println(store);
		if (owner == null) {
			System.out.println("오너가없을경우");
			return "A";
		}
		if (store.getOwnerId() != null) {
			System.out.println("이미등록되 오너일경우");
			return "B";
		} else {
			return " ";
		}

	}
	
	@RequestMapping("/storeMap.do")
	public String storeAdd() {
		return "/WEB-INF/admin/store_management/store_register/storeMap.jsp";
	}
	
	//////////////////////////// 게시판 ///////////////////////////////////////////////////

	@RequestMapping("/adminMypageForm")
	public String ownerMypageForm() {
		return "/WEB-INF/mypage/admin/mypage_admin.jsp";
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
		return "/WEB-INF/board/board_admin.jsp";
	}

	@RequestMapping("/boardInfo")
	public String boardInfo(@RequestParam String boardIndex, ModelMap model) {
		int index = Integer.parseInt(boardIndex);
		Board board = service.getBoardInfo(index);

		System.out.println("--asdfasdf");

		model.addAttribute("board", board);
		return "/WEB-INF/board/board_info_admin.jsp";
	}

	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "/WEB-INF/board/board_write_admin.jsp";
	}

	@RequestMapping("/insertBoard")
	public String insertBoard(@RequestParam String boardTitle, String boardCategoryName, String boardContent,
			String boardWriter) {
		// if(boardContent.charAt(0) =='<') {
		// System.out.println("content : " + boardContent);
		// boardContent = boardContent.copyValueOf(boardContent.toCharArray(),
		// 3, boardContent.length()-3);
		// System.out.println("잘라낸 후 : " + boardContent);
		// }
		if (boardCategoryName.equals("일반")) {
			System.out.println("제목 : " + boardTitle);
			System.out.println("작성자 : " + boardWriter);
			System.out.println("내용 : " + boardContent);
			// System.out.println("내용 : " +
			// boardContent.copyValueOf(boardContent.toCharArray(), 0,
			// boardContent.length()-13));
			HashMap map = new HashMap();
			map.put("boardTitle", boardTitle);
			map.put("boardWriter", boardWriter);
			// map.put("boardContent",
			// boardContent.copyValueOf(boardContent.toCharArray(), 0,
			// boardContent.length()-13));
			map.put("boardContent", boardContent);
			map.put("boardCategoryName", boardCategoryName);
			map.put("boardCategory", 1);
			service.insertBoard(map);
			return "redirect:/admin/boardList.do";

		} else {
			HashMap map = new HashMap();
			map.put("boardTitle", boardTitle);
			map.put("boardWriter", boardWriter);
			// map.put("boardContent",
			// boardContent.copyValueOf(boardContent.toCharArray(), 0,
			// boardContent.length()-13));
			map.put("boardContent", boardContent);
			map.put("boardCategoryName", boardCategoryName);
			map.put("boardCategory", 2);
			service.insertBoard(map);
			return "redirect:/admin/boardList.do";
		}
	}

}
