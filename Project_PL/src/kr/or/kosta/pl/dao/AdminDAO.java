package kr.or.kosta.pl.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.kosta.pl.vo.Admin;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Category;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

//관리자 관리 DAO

public interface AdminDAO {

	//////////////////////////// 관리자 ///////////////////////////////////////////////////
	
	// 관리자 등록
	int insertAdmin(Admin admin);

	// 관리자 삭제
	int deleteAdminById(String adminId);

	// 관리자 수정
	int updateAdmin(Admin admin);

	// ID로 관리자 찾기
	Admin selectAdminById(String adminId);

	// 관리자 목록조회
	List<Admin> selectAdmins();

	// 관리자 목록 페이지
	List<Admin> selectAdminsPaging(int pageNo);

	// 이름으로 관리자 찾기
	List<Admin> selectAdminsByName(String adminName);

	// 관리자 수
	int selectCountAdmins();

	//////////////////////////// 물품관리///////////////////////////////////////////////////

	// 물품 추가
	int insertProduct(String itemName, int itemPrice, int categoryId);

	// 물품 삭제
	int deleteProductByItemId(int itemId);

	// 물품 수정
	int updateProduct(int itemId, String itemName, int itemPrice, int categoryId);
	
	//품명으로 찾기
	Product selectProductByName(String itemName);
	
	// ITEM ID로 물품 찾기
	Product selectProductByItemId(int itemId);

	// 물품 목록 조회
	List<Product> selectProducts();

	// 물품 목록 페이지
	List<Product> selectProductsPaging(int pageNo);

	// 이름으로 물품 찾기
	List<Product> selectProductsByItemName(String itemName);

	// 물품 수
	int selectCountProducts();


	//////////////////////////// 카테고리관리///////////////////////////////////////////////////

	// 카테고리 추가
	int insertCategory(Category category);

	// Category ID로 찾기
	Category selectCategoryById(int categoryId);
	
	//category list 뽑아내기
	List<Category> selectCategoryList();

	// 카테고리 삭제
	int deleteCategoryById(int categoryId);

	// 카테고리 목록 조회
	List<Category> selectCategorys();

	// 카테고리 목록 페이지
	List<Category> selectCategorysPaging(int pageNo);

	// 카테고리 수
	int selectCountCategorys();


	//////////////////////////// 편의점 ///////////////////////////////////////////////////

	// 편의점 등록
	int insertStore(Store store);

	//편의점 삭제
	int deleteStoreById(int storeId);

	//편의점 수정
	int updateStore(Store store);

	// ID로 편의점 찾기
	Store selectStoreById(int storeId);

	//편의점 수
	List<Store> selectStores();

	//Name으로 편의점 찾기
	List<Store> selectStoresByName(String storeName);

	//편의점 수
	int selectCountStores();

	//편의점 paging처리
	List<Store> selectStoresPaging(int pageNo);
	
	//ID로 점주 찾기
	Store selectByOwnerId(String ownerId);

	//////////////////////////// 게시판 관련 ///////////////////////////////////////////////////

	List<Board> selectNotice();

	List<Board> selectBoardsPaging(int pageNo);

	int selectCountBoards();

	Board selectBoardByIndex(int index);
	
	int insertBoard(HashMap map);
	
}
