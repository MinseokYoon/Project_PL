package kr.or.kosta.pl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.util.PagingBean;
import kr.or.kosta.pl.dao.OwnerDAO;
import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.exception.OwnerNotFoundException;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;

@Service("OwnerServiceImpl")
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	private OwnerDAO dao;
	
	public OwnerServiceImpl(){}
	
	@Autowired
	public OwnerServiceImpl(OwnerDAO dao) {
		this.dao = dao;
	}
	
	
	
	/*------------------------------편의점 주인 기본 서비스------------------------------*/
	//편의점주인 추가 서비스 
	public void addOwner(Owner owner) throws DuplicatedIdException {

		Owner own = dao.selectOwnerById(owner.getOwnerId());
		if(own != null){ //이미 있는 점장id이면 예외발생
			throw new DuplicatedIdException(own.getOwnerId()+"는 이미 등록된 ID입니다.");
		}
		dao.insertOwner(owner);
	}
	
	//편의점주인 Id로 점장 삭제하는 서비스
	@Override
	public void removeOwner(String ownerId) throws OwnerNotFoundException {
		Owner own = dao.selectOwnerById(ownerId);
		
		if(own == null){
			throw new OwnerNotFoundException(ownerId+"는 없는 ID입니다.");
		}
		dao.deleteOwnerById(ownerId);
	}
	
	//점장 수정하는 메소드 
	@Override
	public void updateOwner(Owner newOwner) throws OwnerNotFoundException {
		Owner own = dao.selectOwnerById(newOwner.getOwnerId());
		if(own== null){
			throw new OwnerNotFoundException(newOwner.getOwnerId()+"는 없는 ID이므로 수정할 수 없습니다.");
		}
		dao.updateOwner(newOwner);
	}
	
	
	/*-----------------------------편의점 주인 조회 서비스--------------------------------*/
	//편의점주인 id로 점장 찾는 메소드 
	@Override
	public Owner findOwnerById(String ownerId) {
		Owner own = dao.selectOwnerById(ownerId);
		
		return dao.selectOwnerById(ownerId);
	}
	
	//모든 편의점주인 찾는 메소드
	@Override
	public List<Owner> getAllOwners() {
		return dao.selectOwners();
	}
	
	//편의점주인 이름으로 점장찾는 메소드
	@Override
	public List<Owner> findOwnerByName(String ownerName) {
		
		List<Owner> list = dao.selectOwnersByName(ownerName);
		
		return dao.selectOwnersByName(ownerName);
	}
	
	//점장id로 모든 점장의 수를 리턴하는 메소드
	@Override
	public int getAllCountOwners() {
		return dao.selectCountOwners();
	}
	
	//점장 리스트 페이징 처리
	@Override
	public Map getAllOwnersPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("list", dao.selectOwnersPaging(pageNo));
		PagingBean pagingBean = new PagingBean(dao.selectCountOwners(), pageNo);
		map.put("pagingBean", pagingBean);
		return map;
	}

	
	/*-----------------------------재품 관련 서비스--------------------------------*/
	//모든 제품 페이징처리 관련 서비스
	@Override
	public Map getAllProductsPaging(int pageNo,String ownerId) {
		HashMap map = new HashMap();
		map.put("list",dao.selectProductsPaging(pageNo,ownerId));
		PagingBean pagingBean = new PagingBean(dao.selectCountProducts(ownerId), pageNo);
		map.put("pagingBean", pagingBean);
		map.put("ownerId", ownerId);
		return map;
	}

	//물품이름으로 조회하는 서비스 - 여러개 나올 수 있음 
	@Override
	public List<Product> findProductByName(String productName,String ownerId) {
		return dao.selectProductByName(productName,ownerId);
	}
	
	//물품이름으로 하나의 제품만 조회하는 서비스
	public Product findOneProductByName(String pName, String ownerId) {
		return dao.selectOneProduct(pName,ownerId);
	}
	
	//제품 수정하는 서비스
	@Override
	public int updateCountProduct(String ownerId, int resultCount ,int itemId) {
		return dao.updateInputProduct(ownerId,resultCount,itemId);
	}
	
	
	/*-----------------------------주문관련 서비스--------------------------------*/
	//주문현황 list 조회하는 서비스
	@Override
	public Map getAllOrderListPaging(int pageNo, String ownerId) {
		HashMap map = new HashMap();
		map.put("list",dao.selectOrdersPaging(pageNo,ownerId));
		PagingBean pagingBean = new PagingBean(dao.selectCountOrders(ownerId), pageNo);
		System.out.println("총 주문 개수 : " + dao.selectCountOrders(ownerId));
		map.put("pagingBean", pagingBean);
		map.put("ownerId", ownerId);
		return map;
	}
	
	//고객이름으로 검색한 주문현황 list 조회 서비스 
	@Override
	public Map getOrderListByPhonePaging(int pageNo, String ownerId,String cusPhone) {
		HashMap map = new HashMap();
		map.put("list", dao.selectOrdersByPhonePaging(pageNo,ownerId,cusPhone));
		
		PagingBean pagingBean = new PagingBean(dao.selectCountOrdersByPhone(ownerId,cusPhone), pageNo);
		map.put("pagingBean", pagingBean);
		return map;
	}

	//선택한 주문 주문상태 업데이트하는 서비스
	@Override
	public Map updateOrderBySelect(int pageNo,String ownerId,String customerPhone,String orderNumber) {
		//주문 취소한거 업데이트하는 과정 
		dao.updateOrderStatus(orderNumber); // orderNumber를 기준으로 잡아서 주문 취소하게끔 만들어야 함 
		return getOrderListByPhonePaging(pageNo, ownerId, customerPhone);
	}

	//주문완료 버튼 눌러서 모든 주문 주문완료상태로 바꾸는 메소드 
	@Override
	@Transactional
	public int updateAllOrders(String customerId, int storeId, int[] itemId, int[] orderCount, int customerPoint) {
		
		int customerTotalPoint = customerPoint+dao.selectCustomerPoint(customerId);
		int[] nowCount = new int[itemId.length];
		
		for(int i = 0; i < itemId.length; i++){
			nowCount[i]= dao.selectItemcountNow(storeId, itemId[i]) - orderCount[i];
		}
		//updateOrderCount
		for(int a = 0; a< itemId.length; a++){
			dao.updateOrderCount(storeId, itemId[a], nowCount[a]);
		}
		
		dao.updateCustomerPoint(customerId, customerTotalPoint);
		
		return dao.updateAllOrdersStatus(customerId,storeId);
	}

	
	/*-----------------------------본사물품 관련 서비스--------------------------------*/
	//본사 전체 물품 조회하는 메소드
	@Override
	public Map getAllHeadOfficeProductsListPaging(int pageNo, String ownerId) {
		HashMap map = new HashMap();
		map.put("list",dao.selectHeadOfficeProductsPaging(pageNo,ownerId));
		PagingBean pagingBean = new PagingBean(dao.selectHeadOfficeProductCount(ownerId), pageNo);
		
		map.put("pagingBean", pagingBean);
		map.put("ownerId", ownerId);
		return map;
	}

	//본사 물품 조회하는 메소드 - 클릭으로 링크타서 검색하는방법!! 
	@Override
	public Product findHeadOfficeProductByName(String productName) {
		return dao.selectHeadOfficeProductByName(productName);
	}

	//본사 물품 인서트 하는 메소드 
	@Override
	public int inputHeadOfficeProduct(String ownerId, String itemId, String inputCount,Date date) {
		return dao.insertServerItem(ownerId,itemId,inputCount,date);
	}
	
	//본사 물품 검색하는 메소드 - 검색창에 이름을 쳐서 검색하는방법!!
	@Override
	public List<Product> findHeadOfficeProductBySearchName(String searchValue) {
		return dao.selectHeadOfficeProductBySearchName(searchValue);
	}
	

	
	
	/*-----------------------------게시판 처리 서비스--------------------------------*/
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
