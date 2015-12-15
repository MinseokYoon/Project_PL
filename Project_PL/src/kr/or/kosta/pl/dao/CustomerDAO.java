package kr.or.kosta.pl.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Category;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Order;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Review;
import kr.or.kosta.pl.vo.Store;

public interface CustomerDAO {
	
	/*-------------------------------회원 조회--------------------------------------------*/
	int insertCustomer (Customer customer);	//회원 가입
	
	Customer selectCustomerById(String customerId);	//회원 아이디로 조회
	
	int updateCustomer(Customer customer);	//회원 정보 수정
	
	Customer selectCustomerByNameAndPhoneNumber(String name, String phoneNumber);	//이름,폰번호로 아이디 찾기
	
	/*-------------------------------물품 조회--------------------------------------------*/
	List<Product> selectItemList();	//메인 페이지 12개 랜덤 조회(젠체 물품 중)
	
	Product selectItemByName(String itemName);	//물품 이름으로 물건 1개 조회
	
	Product selectItemById(int itemId);	//물품 Id로 물품 조회
			
	List<Product> selectItemListByCategory(int categoryId);	//카테고리별 페이지 12개 랜덤 조회(카테고리 단위로)
		
	List<Product> selectItemByNameMany(String itemName);	//물품 이름으로 전체 조회
	
	List<Category> selectCategoryList();	//카테고리 전체 조회
	
	int insertReview(String customerId, int itemId, String content);	//리뷰 등록
	
	List<Review> selectReview(int itemId);
	
	List<Review> selectReview(int itemId,int page);	//리뷰 조회 페이징
	/*-------------------------------매장 검색--------------------------------------------*/
	Store selectStoreById(int storeId);	//매장 아이디로 찾기 1개
	
	List<Store> selectStoreName(String storeName);	//매장 이름으로 찾기 list
	
	List<Store> selectStoreNameByCount(String itemName);	//어떤 물건이 0개이상 존재하는 매장만 출력 (물건으로 검색시 매장 선택 select창)
	
	/*-------------------------------장바구니 처리--------------------------------------------*/
	Cart selectCartByCustomerIdItemId(String customerId, int storeId, int itemId);	//장바구니 있는지 조회
	
	int updateCart(int cartNumber, int totalCount);		//장바구니 있을시 개수만 변경
	
	int selectServerItemById(int storeId, int itemId);	//서버 물품 조회
	
	int updateServerItemCount(int storeId, int itemId, int totalCount);	//서버 물품 개수 변환
	
	int insertCart(String customerId, int storeId, int itemId, int countItem);	//장바구니 추가
	
	List<Cart> selectCartByCustomerId(String customerId);	//고객 아이디로 장바구니 검색 
	
	int deleteCart(String customerId, int storeId, int itemId);
	
	/*-------------------------------주문 처리--------------------------------------------*/
	int insertOrder(String customerId, int storeId, int itemId, int orderCount, int orderStatus);	//주문 삽입
	
	List<Order> selectOrderByCustomerId(String customerId);//주문 조회
	
	List<Order> selectOrderByCustomerIdLast(String customerId);	//이전 주문 조회
	
	int selectCountLastOrder(String customerId); // 이전 주문조회 개수 갖고오기 

	List<Order> selectOrderByCustomerIdLastPaging(String customerId, int pageNo); //이전 주문조회 페이징
	
	/*-------------------------------게시판 처리--------------------------------------------*/
	List<Board> selectBoardsPaging(int pageNo);	//게시판 페이지
	
	int selectCountBoards();	//게시판 개수
	
	Board selectBoardByIndex(int index);	//게시판 선택

	List<Board> selectNotice();

	int insertBoard(HashMap map);

	int selectCountReviews(String itemName);



	

}
