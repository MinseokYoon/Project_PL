package kr.or.kosta.pl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.util.PagingBean;
import common.util.PagingBeanForReview;
import kr.or.kosta.pl.dao.CustomerDAO;
import kr.or.kosta.pl.exception.CustomerNotFoundException;
import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Category;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Order;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Review;
import kr.or.kosta.pl.vo.Store;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	private CustomerDAO dao;
	
	public CustomerServiceImpl() {}
	
	@Autowired
	public CustomerServiceImpl(CustomerDAO dao){
		this.dao = dao;		
	}
	
	/*-------------------------------회원 조회--------------------------------------------*/
	//회원 가입
	@Override
	public void addCustomer(Customer customer) throws DuplicatedIdException{

		//입력된 객체의 아이디를 통해 db를 검색
		Customer cust = dao.selectCustomerById(customer.getCustomerId());
		
		if(cust != null){	//중복검사 같은 아이디가 있을 경우 에러 출력
			throw new DuplicatedIdException(customer.getCustomerId()+"는 이미 존재하는 아이디입니다.");
		}
		//비어있을 경우 insert
		dao.insertCustomer(customer);
	}
	//회원 아이디로 조회
	@Override
	public Customer findCustomerById(String customerId) {
		
		Customer customer = dao.selectCustomerById(customerId);
		
		return customer;
	}
	//회원 정보 수정
	@Override
	public void modifyCustomer(Customer customer) {
		dao.updateCustomer(customer);
	}
	//이름,핸드폰번호로 아이디 찾기
	@Override
	public Customer findCustomerByNameAndPhoneNumber(String name, String phoneNumber) {
		
		Customer customer = dao.selectCustomerByNameAndPhoneNumber(name,phoneNumber);
		return customer;
	}

	/*-------------------------------물품 조회--------------------------------------------*/
	//메인 페이지 12개 랜덤 조회(젠체 물품 중)
	@Override
	public List<Product> findItemList() {
		return dao.selectItemList();
	}

	//물품 이름(아이디 대체)으로 물건 1개 조회
	@Override
	public Product findItemById(String itemName) {
		return dao.selectItemByName(itemName);
	}
	
	//물품 아이디로 물건 1개 조회
	@Override
	public Product findItemById2(int itemId){
		return dao.selectItemById(itemId);
	}
	
	//물품 상세 정보 페이지 내 관련물품 사진 작은거 9개
	@Override
	public List<Product> findItemListByCategorySmallRecommand(int categoryId) {
		
		List<Product> list = new ArrayList();
		List<Product> list2 = dao.selectItemListByCategory(categoryId);
		//9개만 사용
		int[] result = new int[9];

		for (int i = 0; i < 9; i++) {
			result[i] = (int) ((Math.random() * (dao.selectItemListByCategory(categoryId).size()-1)) );
			for (int k = 0; k < i; k++) {
				if (result[i] == result[k]) {
					result[i] = (int) ((Math.random() * (dao.selectItemListByCategory(categoryId).size()-1)));
					k = 0;
				}
			}
		}
		for(int i =0; i<9; i++){
			list.add(list2.get(result[i]));	
		}
		return list;
	}
	//카테고리별 페이지 12개 랜덤 조회(카테고리 단위로)
	@Override
	public List<Product> findItemListByCategoryMain(int categoryId) {
		
		List<Product> list = new ArrayList();
		List<Product> list2 = dao.selectItemListByCategory(categoryId);
		//9개만 사용
		int[] result = new int[12];

		for (int i = 0; i < 12; i++) {
			result[i] = (int) ((Math.random() * (dao.selectItemListByCategory(categoryId).size()-1)) );
			for (int k = 0; k < i; k++) {
				if (result[i] == result[k]) {
					result[i] = (int) ((Math.random() * (dao.selectItemListByCategory(categoryId).size()-1)));
					k = 0;
				}
			}
		}
		for(int i =0; i<12; i++){
			list.add(list2.get(result[i]));	
		}
		return list;
	}
	//물품 이름으로 전체 조회
	@Override
	public List<Product> findItemByNameMany(String itemName) {
	
		return dao.selectItemByNameMany(itemName);
	}
	
	//카테고리 리스트 전체 조회
	@Override
	public List<Category> findCategoryList(){
		return dao.selectCategoryList();
	}
	
	//아이템 리스트 카테고리로 전체 조회
	@Override
	public List<Product> findItemListByCategory(int categoryId){
		return dao.selectItemListByCategory(categoryId);
	}
	//리뷰 등록
	@Override
	public void addReview(String customerId, int itemId, String content) {
		dao.insertReview(customerId, itemId, content);
	}
	//리뷰 조회
	@Override
	public List<Review> findReview(int itemId){
		return dao.selectReview(itemId);
	}

	//리뷰 조회 페이징 
	@Override
	public Map getAllReviewsPaging(int page, int itemId,String itemName) {
		HashMap map = new HashMap();
		map.put("reviewList", dao.selectReview(itemId,page));
		
		PagingBeanForReview pagingBeanForReview = new PagingBeanForReview(dao.selectCountReviews(itemName), page);
		map.put("pagingBeanForReview", pagingBeanForReview);
		map.put("reviewCount", dao.selectCountReviews(itemName));
		
		return map;
	}
	
	
	/*-------------------------------매장 검색--------------------------------------------*/
	//매장 아이디로 찾기 1개
	@Override
	public Store findStoreById(int storeId) {
		
		return dao.selectStoreById(storeId);
	}
	//매장 이름으로 찾기 list
	@Override
	public List<Store> findStoreName(String storeName) {
		return dao.selectStoreName(storeName);
	}
	//어떤 물건이 0개이상 존재하는 매장만 출력 (물건으로 검색시 매장 선택 select창)
	@Override
	public List<Store> findStoreNameByCount(String itemName) {
		
		return dao.selectStoreNameByCount(itemName);
	}
	
	/*-------------------------------장바구니 처리--------------------------------------------*/
	//장바구니에 같은 품목 있는지 조회
	@Override
	public Cart findCartByCusotmerIdItemId(String customerId, int storeId, int itemId) {
		
		return dao.selectCartByCustomerIdItemId(customerId, storeId, itemId);
	}
	@Override
	public int updateCart(int cartNumber, int totalCount) {
		
		return dao.updateCart(cartNumber, totalCount);
	}
	
	@Override
	public int updateCart2(int storeId, int itemId, int countItem) {
		int totalCount = 0;
		totalCount = dao.selectServerItemById(storeId, itemId) - countItem;
	
		return dao.updateServerItemCount(storeId, itemId, totalCount);
	}

	//장바구니 추가
	@Override
	@Transactional
	public void addCart(String customerId, int storeId, int itemId, int countItem) {
		
		//서버에서 물건 개수 조회 후 개수 줄이기
		int totalCount = 0;
		totalCount = dao.selectServerItemById(storeId, itemId) - countItem;
	
		//주문 개수를 빼서 업데이트 
		dao.updateServerItemCount(storeId, itemId, totalCount);
		
		dao.insertCart(customerId, storeId, itemId, countItem);
	}
	
	//고객 아이디로 장바구니 검색
	@Override
	public List<Cart> findCartByCusotmerId(String customerId) {
		return dao.selectCartByCustomerId(customerId);
	}
	//장바구니 내용 삭제
	@Override
	public void deleteCart(String customerId, int storeId, int itemId) {
		dao.deleteCart(customerId, storeId, itemId);
	}
	
	/*-------------------------------주문 처리--------------------------------------------*/
	//주문 삽입
	@Override
	@Transactional
	public void addOrder(String[] customerId, int[] storeId, int[] itemId, int[] orderCount, int[] orderStatus) {
		
		for(int i = 0; i < customerId.length; i++){
			dao.insertOrder(customerId[i], storeId[i], itemId[i], orderCount[i], orderStatus[i]);
		}
		for(int k = 0; k < customerId.length; k++){
			dao.deleteCart(customerId[k], storeId[k], itemId[k]);
		}
	}
	//주문 삽입 - 안드로이드
	public void addOrderAndroid(String customerId, int storeId, int itemId, int orderCount, int orderStatus) {
			dao.insertOrder(customerId, storeId, itemId, orderCount, orderStatus);
	}
	//주문 조회
	@Override
	public List<Order> findOrderByCusotmerId(String customerId) {
		
		return dao.selectOrderByCustomerId(customerId);
	}
	//이전 주문 조회
	@Override
	public List<Order> findOrderByCusotmerIdLast(String customerId) {
		return dao.selectOrderByCustomerIdLast(customerId);
	}
	
	//이전 주문 조회 - 페이징 
	@Override
	public Map getLastOrderPaging(String customerId,int pageNo) {
	
		HashMap map = new HashMap();
		map.put("lastOrderList", dao.selectOrderByCustomerIdLastPaging(customerId,pageNo)); //페이징처리 매퍼로 바꿔야 함 
		PagingBeanForReview pagingBeanForReview = new PagingBeanForReview(dao.selectCountLastOrder(customerId), pageNo);
		map.put("pagingBeanForReview", pagingBeanForReview);
		
		return map;
	}
	
	/*-------------------------------게시판 처리--------------------------------------------*/
	//게시판 전체
	@Override
	public Map getAllBoard(int pageNo) {
		HashMap map = new HashMap();
		List<Board> list = dao.selectBoardsPaging(pageNo);
		PagingBean pagingBean = new PagingBean(dao.selectCountBoards(), pageNo);
		
		map.put("list", list);
		map.put("pagingBean", pagingBean);

		return map;
	}
	//게시판 정보
	@Override
	public Board getBoardInfo(int index) {
		Board board = dao.selectBoardByIndex(index);
		return board;
	}

	@Override
	public List<Board> getNotice() {
		List<Board> list = dao.selectNotice();
		return list;
	}
	
	@Override
	public void insertBoard(HashMap map) {
		dao.insertBoard(map);
	}

}
