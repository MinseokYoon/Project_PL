package kr.or.kosta.pl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.util.PagingBeanForReview;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Category;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Order;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Review;
import kr.or.kosta.pl.vo.Store;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	
	private SqlSessionTemplate session;
	
	@Autowired
	public CustomerDAOImpl(SqlSessionTemplate session){
		this.session = session;
	}
	public CustomerDAOImpl(){}
	/*-------------------------------회원 조회--------------------------------------------*/
	//회원 가입
	@Override
	public int insertCustomer(Customer customer) {

		return session.insert("customerMapper.insertCustomer", customer);
	}
	//회원 아이디로 조회
	@Override
	public Customer selectCustomerById(String customerId) {
		return session.selectOne("customerMapper.selectCustomerById", customerId);
	}
	//회원 정보 수정
	@Override
	public int updateCustomer(Customer customer) {
		return session.update("customerMapper.updateCustomer", customer);
	}
	//이름,폰번호로 아이디 찾기
	@Override
	public Customer selectCustomerByNameAndPhoneNumber(String name, String phoneNumber) {
		HashMap map = new HashMap();
		map.put("name", name);
		map.put("phoneNumber", phoneNumber);
		return session.selectOne("customerMapper.selectCustomerByNameAndPhoneNumber",map);	
	}
	
	/*-------------------------------물품 조회--------------------------------------------*/
	//메인 페이지 12개 랜덤 조회(젠체 물품 중)
	@Override
	public List<Product> selectItemList() {
		
		List<Product> list = new ArrayList();
		int lastNumber = session.selectOne("customerMapper.selectItemCount");
		int count = 50;
		int[] result = new int[count];

		for (int i = 0; i < count; i++) {
			result[i] = (int) ((Math.random() * lastNumber) + 1);
			for (int k = 0; k < i; k++) {
				if (result[i] == result[k]) {
					result[i] = (int) ((Math.random() * lastNumber) + 1);
					k = 0;
				}
			}
		}
		for(int i =0; i< count; i++){
			Product pd = session.selectOne("customerMapper.selectItemList", result[i]);		
			if(pd != null){
				list.add(pd);
			}
			if(list.size() == 12){
				break;
			}
		}	
		return list;
		
		
	}
	//물품 이름으로 물건 1개 조회
	@Override
	public Product selectItemByName(String itemName) {
		return session.selectOne("customerMapper.selectItemByName", itemName);
	}
	//물품 아이디로 물건 조회
	@Override
	public Product selectItemById(int itemId){
		return session.selectOne("customerMapper.selectItemById", itemId);
	}
	//카테고리별 페이지 12개 랜덤 조회(카테고리 단위로)
	@Override
	public List<Product> selectItemListByCategory(int categoryId) {
		
		List<Product> list = new ArrayList();
		//mapper에서 카테고리 개수만큼 찾아와야함
		list = session.selectList("customerMapper.selectItemListByCategory", categoryId);
		
		return list;
	}
	//물품 이름으로 전체 조회
	@Override
	public List<Product> selectItemByNameMany(String itemName) {
		List<Product> list = new ArrayList();
		list =session.selectList("customerMapper.selectItemByNameMany", itemName);
		return list;
	}
	
	//카테고리 리스트 전제 조회
	@Override
	public List<Category> selectCategoryList(){
		return session.selectList("customerMapper.selectCategoryList");
	}
	//리뷰 등록
	@Override
	public int insertReview(String customerId, int itemId, String content) {
		HashMap parameter = new HashMap();
		parameter.put("customerId", customerId);
		parameter.put("itemId", itemId);
		parameter.put("content", content);	
		return session.insert("customerMapper.insertReview", parameter);
	}
	
	//리뷰 조회
	@Override
	public List<Review> selectReview(int itemId){
		return session.selectList("customerMapper.selectReview", itemId);
	}
	
	//리뷰 개수 갖고오기
	@Override
	public int selectCountReviews(String itemName) {
		return session.selectOne("customerMapper.selectCountReviews",itemName);
	}
	
	//리뷰 조회 페이징
	@Override
	public List<Review> selectReview(int itemId,int page){
		HashMap map = new HashMap();
		map.put("contentPerPage",PagingBeanForReview.CONTENTS_PER_PAGE);
		map.put("itemId",itemId);
		map.put("pageNo", page);
		
		List<Review> list = session.selectList("customerMapper.selectReviewPaging", map);
		return list;
	}
		/*-------------------------------매장 검색--------------------------------------------*/
	//매장 아이디로 찾기 1개
		@Override
	public Store selectStoreById(int storeId) {
		
		return session.selectOne("customerMapper.selectStoreById", storeId);
	}
	//매장 이름으로 찾기 list
	@Override
	public List<Store> selectStoreName(String storeName) {
		return session.selectList("customerMapper.findStoreName",storeName);
	}
	//어떤 물건이 0개이상 존재하는 매장만 출력 (물건으로 검색시 매장 선택 select창)
	@Override
	public List<Store> selectStoreNameByCount(String itemName) {
		List<Store> list = new ArrayList();
		list = session.selectList("customerMapper.selectStoreByCount", itemName);
		return list;
	}
	
	/*-------------------------------장바구니 처리--------------------------------------------*/
	//장바구니 있는지 조회
	@Override
	public Cart selectCartByCustomerIdItemId(String customerId, int storeId, int itemId) {
		HashMap map = new HashMap();
		map.put("customerId", customerId);
		map.put("storeId", storeId);
		map.put("itemId", itemId);
		return session.selectOne("customerMapper.selectCartByCustomerIdItemId", map);
	}
	//장바구니 있을 시 개수만 변경
	@Override
	public int updateCart(int cartNumber, int totalCount) {
		HashMap map = new HashMap();
		map.put("cartNumber", cartNumber);
		map.put("totalCount", totalCount);
		return session.update("customerMapper.updateCart", map);
	}
	//서버 물품 조회
	@Override
	public int selectServerItemById(int storeId, int itemId) {
		HashMap map = new HashMap();
		map.put("storeId", storeId);
		map.put("itemId", itemId);
		
		return session.selectOne("customerMapper.selectServerItemCount", map);
	}
	@Override
	public int updateServerItemCount(int storeId, int itemId, int totalCount) {
		HashMap map = new HashMap();
		map.put("storeId", storeId);
		map.put("itemId", itemId);
		map.put("totalCount", totalCount);
		System.out.println(storeId + " " + itemId +  " " + totalCount);
		return session.update("customerMapper.updateServerItem", map);
	}
	//장바구니 추가
	@Override
	public int insertCart(String customerId, int storeId, int itemId, int countItem) {
		// TODO 중복 검사 해 주어야 함
		HashMap parameter = new HashMap();
		parameter.put("customerId", customerId);
		parameter.put("storeId", storeId);
		parameter.put("itemId", itemId);
		parameter.put("countItem", countItem);
		
		return session.insert("customerMapper.insertCart", parameter);
	}
	
	//고객 아이디로 장바구니 검색 
	@Override
	public List<Cart> selectCartByCustomerId(String customerId) {
	
		return session.selectList("customerMapper.selectCartByCustomerId", customerId);
	}
	//장바구니 내용 삭제
	@Override
	public int deleteCart(String customerId, int storeId, int itemId) {
		
		HashMap parameter = new HashMap();
		
		parameter.put("customerId", customerId);
		parameter.put("storeId", storeId);
		parameter.put("itemId", itemId);

		return session.delete("customerMapper.deleteCart", parameter);
	}
	/*-------------------------------주문 처리--------------------------------------------*/
	//주문 삽입
	@Override
	public int insertOrder(String customerId, int storeId, int itemId, int orderCount, int orderStatus) {
		
		HashMap parameter =new HashMap();
		parameter.put("cusotmerId", customerId);
		parameter.put("storeId", storeId);
		parameter.put("itemId", itemId);
		parameter.put("orderCount", orderCount);
		parameter.put("orderStatus", orderStatus);
		
		System.out.println(customerId);
				
		return session.insert("customerMapper.insertOrder", parameter);
	}
	//주문 조회
	@Override
	public List<Order> selectOrderByCustomerId(String customerId) {
		
		return session.selectList("customerMapper.selectOrderByCustomerIdnow", customerId);
	
	}
	//이전 주문 조회 
	@Override
	public List<Order> selectOrderByCustomerIdLast(String customerId) {
		return session.selectList("customerMapper.selectOrderByCustomerIdLast", customerId);
	}
	
	//이전 주문 조회 페이징
	@Override
	public List<Order> selectOrderByCustomerIdLastPaging(String customerId,int pageNo) {
		HashMap map = new HashMap();
		map.put("contentPerPage",PagingBeanForReview.CONTENTS_PER_PAGE);
		map.put("customerId", customerId);
		map.put("pageNo", pageNo);
		
		List<Order> list = session.selectList("customerMapper.selectOrderByCustomerIdLastPaging", map);
		return list;
	}
	
	//이전 주문조회 개수 
	@Override
	public int selectCountLastOrder(String customerId) {
		return session.selectOne("customerMapper.selectCountLastOrder",customerId);
	}
	
	/*-------------------------------게시판 처리--------------------------------------------*/
	//게시판 페이지
	@Override
	public List<Board> selectBoardsPaging(int pageNo) {
		return session.selectList("customerMapper.selectBoardsPaging", pageNo);
	}
	//게시판 개수
	@Override
	public int selectCountBoards() {
		return session.selectOne("customerMapper.selectBoardCount");
	}
	//게시판 선택
	@Override
	public Board selectBoardByIndex(int index) {
		session.update("customerMapper.updateBoardReadCount", index);
		return session.selectOne("customerMapper.selectBoardInfo", index);
	}

	@Override
	public List<Board> selectNotice() {
		return session.selectList("customerMapper.selectNotice");
	}
	
	@Override
	public int insertBoard(HashMap map) {
		return session.insert("customerMapper.insertBoard", map);
	}



}
