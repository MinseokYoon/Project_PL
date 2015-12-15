package kr.or.kosta.pl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.kosta.pl.exception.AdminNotFoundException;
import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.exception.StoreNotFoundException;
import kr.or.kosta.pl.vo.Admin;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Category;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

public interface AdminService {

	//////////////////////////// 관리자 //////////////////////////// 

	// 관리자 등록
	void addAdmin(Admin admin) throws DuplicatedIdException;

	// 관리자 조회
	List<Admin> getAllAdmins();

	// 관리자 paging 처리
	Map getAllAdminsPaging(int pageNo);

	// ID로 관리자 찾기
	Admin findAdminById(String adminId);

	// Name으로 관리자 찾기
	List<Admin> findAdminByName(String adminName);

	// 관리자 삭제
	void removeAdmin(String adminId) throws AdminNotFoundException;

	// 관리자 수정
	void updateAdmin(Admin newAdmin) throws AdminNotFoundException;

	//////////////////////////// 물품관리///////////////////////////////////////////////////

	// 물품 추가
	void addProduct(String itemName, int itemPrice, int categoryId) throws DuplicatedIdException;

	// 물품 삭제
	void removeProduct(int itemId) throws AdminNotFoundException;

	// 물품 수정
	void updateProduct(int itemId, String itemName, int itemPrice, int categoryId) throws AdminNotFoundException;

	// 품명으로 찾기
	Product findProductByName(String itemName);

	// ITEM ID로 물품 찾기
	Product findProductByItemId(int itemId);

	// 물품 목록 조회
	List<Product> getAllProducts();

	// 물품 목록 페이지
	Map getAllProductsPaging(int pageNo);

	// 이름으로 물품 찾기
	List<Product> findProductByItemName(String itemName);

	// 물품 수
	int getAllCountProducts();

	//////////////////////////// 케테고리관리///////////////////////////////////////////////////

	//카테고리 등록
	void addCategory(Category category) throws DuplicatedIdException;

	//ID로 카테고리 찾기
	Category findCategoryById(int categoryId);

	//카테고리 조회
	List<Category> findcategoryList();

	//카테고리 삭제
	void removeCategory(int categoryId) throws AdminNotFoundException;

	//카테고리 조회
	List<Category> getAllCategorys();

	//카테고리 paging처리
	Map getAllCategorysPaging(int pageNo);

	//////////////////////////// 편의점관리///////////////////////////////////////////////////

	//편의점 추가
	void addStore(Store store) throws DuplicatedIdException;

	//ID로 편의점 찾기
	Store findStoreById(int storeId);

	//편의점 삭제
	void removeStore(int storeId) throws StoreNotFoundException;

	//편의점 수정
	void updateStore(Store newSto) throws StoreNotFoundException;

	//편의점 조회
	List<Store> getAllStores();

	//Name으로 편의점 찾기
	List<Store> findStoreByName(String storeName);

	//편의점 수
	int getAllCountStores();

	//편의점 paning처리
	Map getAllStoresPaging(int pageNo);

	//ID로 점주 찾기
	Store selectByOwnerId(String ownerId);

	//////////////////////////// 게시판 ////////////////////////////

	Map getAllBoard(int pageNo);

	List<Board> getNotice();

	Board getBoardInfo(int index);

	void insertBoard(HashMap map);

}
