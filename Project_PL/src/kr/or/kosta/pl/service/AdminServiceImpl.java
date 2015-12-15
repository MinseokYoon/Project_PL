package kr.or.kosta.pl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.util.PagingBean;
import kr.or.kosta.pl.dao.AdminDAO;
import kr.or.kosta.pl.exception.AdminNotFoundException;
import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.exception.StoreNotFoundException;
import kr.or.kosta.pl.vo.Admin;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Category;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private AdminDAO dao;

	@Autowired
	public AdminServiceImpl(AdminDAO dao) {
		this.dao = dao;
	}

	public AdminServiceImpl() {
	}

	////////////////////////////관리자 ////////////////////////////
	
	//관리자 등록
	@Override
	public void addAdmin(Admin admin) throws DuplicatedIdException {
		Admin ad = dao.selectAdminById(admin.getAdminId());
		if (ad != null) {
			throw new DuplicatedIdException(admin.getAdminId() + "는 이미 등록된 ID입니다");
		}
		// insert
		dao.insertAdmin(admin);

	}

	//관리자 조회
	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return dao.selectAdmins();
	}

	//관리자 paging처리
	@Override
	public Map getAllAdminsPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("list", dao.selectAdminsPaging(pageNo));
		PagingBean pagingBean = new PagingBean(dao.selectCountAdmins(), pageNo);
		map.put("pagingBean", pagingBean);
		return map;
	}

	//ID로 관리자 찾기
	@Override
	public Admin findAdminById(String adminId) {
		// TODO Auto-generated method stub
		return dao.selectAdminById(adminId);
	}

	//Name으로 관리자 찾기
	@Override
	public List<Admin> findAdminByName(String adminName) {
		// TODO Auto-generated method stub
		return dao.selectAdminsByName(adminName);
	}

	//관리자 삭제
	@Override
	public void removeAdmin(String adminId) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		Admin ad = dao.selectAdminById(adminId);
		if (ad == null) {
			throw new AdminNotFoundException(adminId + "는 없는 ID이므로 삭제할 수 없습니다.");
		}
		dao.deleteAdminById(adminId);
	}

	//관리자 수정
	@Override
	public void updateAdmin(Admin newAd) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		Admin ad = dao.selectAdminById(newAd.getAdminId());
		if (ad == null) {
			throw new AdminNotFoundException(newAd.getAdminId() + "는 없는 ID이므로 수정할 수 없습니다.");
		}
		dao.updateAdmin(newAd);
	}

	//////////////////////////// 물품관리///////////////////////////////////////////////////
	
	//물품 추가
	@Override
	public void addProduct(String itemName, int itemPrice,  int categoryId) throws DuplicatedIdException {
	
		dao.insertProduct( itemName, itemPrice, categoryId);

	}

	//물품 조회
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return dao.selectProducts();
	}

	//물품 paging처리
	@Override
	public Map getAllProductsPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("list", dao.selectProductsPaging(pageNo));
		PagingBean pagingBean = new PagingBean(dao.selectCountProducts(), pageNo);
		map.put("pagingBean", pagingBean);
		return map;
	}

	//Name으로 물품 찾기
	@Override
	public Product findProductByName(String itemName) {
		// TODO Auto-generated method stub
		
		System.out.println(dao.selectProductByName(itemName));
		return dao.selectProductByName(itemName);
	}
	
	//ID로 물품찾기
	@Override
	public Product findProductByItemId(int itemId) {
		// TODO Auto-generated method stub
		return dao.selectProductByItemId(itemId);
	}

	//Name으로 물품찾기(List)
	@Override
	public List<Product> findProductByItemName(String itemName) {
		// TODO Auto-generated method stub
		return dao.selectProductsByItemName(itemName);
	}

	//물품 삭제
	@Override
	public void removeProduct(int itemId) throws AdminNotFoundException {
		Product pro = dao.selectProductByItemId(itemId);
		if (pro == null) {
			throw new AdminNotFoundException(itemId + "는 없는 ID이므로 삭제할 수 없습니다.");
		}
		dao.deleteProductByItemId(itemId);

	}

	//물품 수정
	@Override
	public void updateProduct(int itemId,String itemName, int itemPrice, int categoryId) throws AdminNotFoundException{
		// TODO Auto-generated method stub
			
				dao.updateProduct(itemId,itemName, itemPrice, categoryId);
		
	}
	

	// 물품 수
	@Override
	public int getAllCountProducts() {
		// TODO Auto-generated method stub
		return dao.selectCountProducts();
	}


	//////////////////////////// 케테고리관리///////////////////////////////////////////////////

	//카테고리 등록
	@Override
	public void addCategory(Category category) throws DuplicatedIdException {
		Category cate = dao.selectCategoryById(category.getCategoryId());
		if (cate != null) {
			throw new DuplicatedIdException(category.getCategoryId() + "는 이미 등록된 ID입니다");
		}
		// insert
		dao.insertCategory(category);

	}

	//카테고리 삭제
	@Override
	public void removeCategory(int categoryId) throws AdminNotFoundException {
		Category cate = dao.selectCategoryById(categoryId);
		if (cate == null) {
			throw new AdminNotFoundException(categoryId + "는 없는 ID이므로 삭제할 수 없습니다.");
		}
		dao.deleteCategoryById(categoryId);

	}

	//ID로 카테고리 찾기
	@Override
	public Category findCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return dao.selectCategoryById(categoryId);
	}

	//카테고리 조회(List)
	@Override
	public List<Category> findcategoryList() {
		// TODO Auto-generated method stub
		return dao.selectCategoryList();
	}
	
	//카테고리 조회
	@Override
	public List<Category> getAllCategorys() {
		// TODO Auto-generated method stub
		return dao.selectCategorys();
	}

	//카테고리 paging처리
	@Override
	public Map getAllCategorysPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("list", dao.selectCategorysPaging(pageNo));
		PagingBean pagingBean = new PagingBean(dao.selectCountCategorys(), pageNo);
		map.put("pagingBean", pagingBean);
		return map;
	}

	//////////////////////////// 편의점관리///////////////////////////////////////////////////

	//편의점 추가
	@Override
	public void addStore(Store store) throws DuplicatedIdException {
		Store st = dao.selectStoreById(store.getStoreId());
		if (st != null) {
			throw new DuplicatedIdException(store.getStoreId() + "는 이미 등록된 ID입니다");
		}
		dao.insertStore(store);
	}

	//ID로 편의점 찾기
	@Override
	public Store findStoreById(int storeId) {
		// TODO Auto-generated method stub
		return dao.selectStoreById(storeId);
	}

	//편의점 삭제
	@Override
	public void removeStore(int storeId) throws StoreNotFoundException {
		// TODO Auto-generated method stub
		Store sto = dao.selectStoreById(storeId);
		if (sto == null) {
			throw new StoreNotFoundException(storeId + "는 없는 ID이므로 삭제할 수 없습니다.");
		}
		dao.deleteStoreById(storeId);
	}

	//편의점 수정
	@Override
	public void updateStore(Store newSto) throws StoreNotFoundException {
		// TODO Auto-generated method stub
		Store sto = dao.selectStoreById(newSto.getStoreId());
		if (sto == null) {
			throw new StoreNotFoundException(newSto.getStoreId() + "는 없는 ID이므로 수정할 수 없습니다.");
		}

		dao.updateStore(newSto);
	}

	//편의점 조회
	@Override
	public List<Store> getAllStores() {
		// TODO Auto-generated method stub
		return dao.selectStores();
	}

	//Name으로 편의점 찾기
	@Override
	public List<Store> findStoreByName(String storeName) {
		List<Store> list = dao.selectStoresByName(storeName);

		return dao.selectStoresByName(storeName);
	}

	//편의점 수
	@Override
	public int getAllCountStores() {
		// TODO Auto-generated method stub
		return dao.selectCountStores();
	}

	//편의점 paging처리
	@Override
	public Map getAllStoresPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("list", dao.selectStoresPaging(pageNo));
		PagingBean pagingBean = new PagingBean(dao.selectCountStores(), pageNo);
		map.put("pagingBean", pagingBean);
		return map;
	}

	//ID로 점주찾기
	@Override
	public Store selectByOwnerId(String ownerId) {
		// TODO Auto-generated method stub
		return dao.selectByOwnerId(ownerId);
	}

	
	//////////////////////////// 게시판 관련 ///////////////////////////////////////////////////

	@Override
	public List<Board> getNotice() {
		List<Board> list = dao.selectNotice();
		return list;
	}

	@Override
	public Map getAllBoard(int pageNo) {
		HashMap map = new HashMap();
		List<Board> list = dao.selectBoardsPaging(pageNo);
		PagingBean pagingBean = new PagingBean(dao.selectCountBoards(), pageNo);

		map.put("list", list);
		map.put("pagingBean", pagingBean);

		return map;
	}

	@Override
	public Board getBoardInfo(int index) {
		Board board = dao.selectBoardByIndex(index);
		return board;
	}

	@Override
	public void insertBoard(HashMap map) {
		dao.insertBoard(map);
	}


}
