package kr.or.kosta.pl.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.util.PagingBean;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Order;
import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;

/*
 * Owner 테이블과 연동하는 DAO 클래스
 */

@Repository("OwnerDAOImpl")
public class OwnerDAOImpl implements OwnerDAO {
	
	//spring - mybatis 연결시키기 위해 필요 
	private SqlSessionTemplate session;
	
	//no-arg 생성자
	public OwnerDAOImpl() {}

	//생성자
	@Autowired
	public OwnerDAOImpl(SqlSessionTemplate session) {
		this.session = session;
	}

	
	
	/*-----------------------기본 Dao------------------------*/
	//편의점주인 추가
	@Override
	public int insertOwner(Owner owner) {//
		return session.insert("ownerMapper.insertOwner",owner);
	}

	//편의점주인 삭제
	@Override	
	public int deleteOwnerById(String ownerId) {//
		return session.delete("ownerMapper.deleteOwnerById",ownerId);
	}

	//편의점주인 수정
	@Override
	public int updateOwner(Owner owner) {//
		return session.update("ownerMapper.updateOwner",owner);
	}

	
	
	/*-----------------------편의점 주인 조회-----------------------*/
	//Id로 편의점주인 조회
	@Override
	public Owner selectOwnerById(String ownerId) {//
		return session.selectOne("ownerMapper.selectOwnerById",ownerId);
	}

	//모든 편의점주인 리스트 조회
	@Override
	public List<Owner> selectOwners() {//
		return session.selectList("ownerMapper.selectOwners");
	}

	//이름으로 편의점주인 조회
	@Override
	public List<Owner> selectOwnersByName(String ownerName) {
		return session.selectList("ownerMapper.selectOwnersByName",ownerName);
	}

	//편의점주인 수 조회
	@Override
	public int selectCountOwners() {
		return session.selectOne("ownerMapper.selectCountOwners");
	}
	
	//편의점 리스트 페이징처리
	@Override
	public List<Owner> selectOwnersPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("contentsPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo", pageNo);
		List<Owner> list = session.selectList("ownerMapper.selectOwnersPaging", map);
		return list;
	}

	
	
	/*-----------------------제품 관련 dao-----------------------*/
	//제품 조회 리스트 
	@Override
	public List<Product> selectProductsPaging(int pageNo,String ownerId) {
		HashMap map = new HashMap();
		map.put("contentPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo",pageNo);
		map.put("ownerId", ownerId); //편의점 id값 넣기 
		
		List<Product> list = session.selectList("ownerMapper.selectProductsPaging",map);
		return list;
	}

	//제품 수 조회
	@Override
	public int selectCountProducts(String ownerId) {
		return session.selectOne("ownerMapper.selectCountProducts",ownerId);
	}

	//이름으로 제품 조회
	@Override
	public List<Product> selectProductByName(String productName,String ownerId) {
		HashMap map = new HashMap();
		map.put("productName", productName);
		map.put("ownerId",ownerId);
		
		return session.selectList("ownerMapper.selectProductByName",map);
	}

	//하나의 제품 조회
	@Override
	public Product selectOneProduct(String pName, String ownerId) {
		HashMap map = new HashMap();
		map.put("productName",pName);
		map.put("ownerId",ownerId);
		return session.selectOne("ownerMapper.selectOneProduct",map);
	}

	//제품 수정
	public int updateInputProduct(String ownerId, int resultCount,int itemId) {
		HashMap map = new HashMap();
		map.put("ownerId",ownerId);
		map.put("resultCount",resultCount);
		map.put("itemId", itemId);
		return session.update("ownerMapper.updateInputProduct",map);
	}

	
	
	/*-----------------------주문 관련 dao-----------------------*/
	//주문 수 조회
	@Override
	public int selectCountOrders(String ownerId) {
		return session.selectOne("ownerMapper.selectCountOrders",ownerId);
	}

	//주문 리스트 페이징 
	@Override
	public List<Order> selectOrdersPaging(int pageNo, String ownerId) {
		HashMap map = new HashMap();
		map.put("contentPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo",pageNo);
		map.put("ownerId", ownerId); //편의점 id값 넣기 
		
		List<Order> list = session.selectList("ownerMapper.selectOrdersPaging",map);
		return list;
	}

	//핸드폰번호로 주문 조회 페이징
	@Override
	public List<Order> selectOrdersByPhonePaging(int pageNo, String ownerId,String cusPhone) {
		HashMap map = new HashMap();
		map.put("contentPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo",pageNo);
		map.put("ownerId", ownerId); //편의점 id값 넣기 
		map.put("customerPhone", cusPhone);
		
		List<Order> list = session.selectList("ownerMapper.selectOrdersByPhonePaging",map);
		return list;
	}

	//핸드폰번호로 주문 조회
	@Override
	public int selectCountOrdersByPhone(String ownerId,String cusPhone) {
		HashMap map = new HashMap();
		map.put("ownerId", ownerId);
		map.put("customerPhone", cusPhone);
		return session.selectOne("ownerMapper.selectCountOrdersByPhone",map);
	}

	//선택한 주문 상태 바꾸기
	@Override
	public int updateOrderStatus(String orderNumber) {
		return session.update("ownerMapper.updateOrderStatus",orderNumber);
	}

	//모든 주문 상태 바꾸기 
	@Override
	public int updateAllOrdersStatus(String customerId, int storeId) {
		HashMap map = new HashMap();
		map.put("customerId", customerId);
		map.put("storeId",storeId);

		return session.update("ownerMapper.updateAllOrdersStatus",map);
	}
	
	//주문 개수 변환
	@Override
	public int updateOrderCount(int storeId, int itemId, int nowCount){
		HashMap map = new HashMap();
		map.put("storeId", storeId);
		map.put("itemId", itemId);
		map.put("nowCount", nowCount);

		System.out.println("포인트 업그레이드");
		return session.update("ownerMapper.updateOrderCount" ,map);
	}
	
	//본사 물품 개수 조회
	@Override
	public int selectItemcountNow(int storeId, int itemId){
		HashMap parameter = new HashMap();
		parameter.put("storeId", storeId);
		parameter.put("itemId", itemId);
		return session.selectOne("ownerMapper.selectItemcountNow", parameter);
	}
	
	
	/*-----------------------본사물품 관련 dao-----------------------*/
	//본사물품 개수 조회하는거 
	@Override
	public int selectHeadOfficeProductCount(String ownerId) {
		return session.selectOne("ownerMapper.selectHeadOfficeProductCount",ownerId);
	}


	//본사 물품 조회하는 거
	@Override
	public Product selectHeadOfficeProductByName(String productName) {
		return session.selectOne("ownerMapper.selectHeadOfficeProductByName",productName);
	}

	//본사 물품 인서트 하는거 
	@Override
	public int insertServerItem(String ownerId, String itemId, String inputCount,Date date) {
		HashMap map = new HashMap();
		map.put("ownerId", ownerId);
		map.put("itemId",itemId);
		map.put("inputCount", inputCount); 
		map.put("date",date);
		
		return session.insert("ownerMapper.insertServerItem",map);
	}
	
	//본사물품 페이징처리 
	@Override
	public List<Product> selectHeadOfficeProductsPaging(int pageNo, String ownerId) {
		HashMap map = new HashMap();
		map.put("contentPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo",pageNo);
		map.put("ownerId", ownerId); //편의점 id값 넣기 
		
		List<Product> list = session.selectList("ownerMapper.selectHeadOfficeProductsPaging",map);
		return list;
	}
	
	//본사물품 검색하는 기능 - 이름을 쳐서 검색! 
	@Override
	public List<Product> selectHeadOfficeProductBySearchName(String searchValue) {
		return session.selectList("ownerMapper.selectHeadOfficeProductBySearchName",searchValue);
	}
	
	
	
	/*-----------------------고객 point 관련 dao-----------------------*/
	//고객 포인트 조회
	@Override
	public int selectCustomerPoint(String customerId){
		return session.selectOne("ownerMapper.selectCustomerPoint", customerId);
	}
	
	@Override
	public int updateCustomerPoint(String customerId, int customerTotalPoint) {
		HashMap map = new HashMap();
		map.put("customerId", customerId);
		map.put("customerTotalPoint", customerTotalPoint);
		return session.update("ownerMapper.updateCustomerPoint", map);
	}
	

	/*-----------------------게시판 관련 dao-----------------------*/
	@Override
	public List<Board> selectBoardsPaging(int pageNo) {
		return session.selectList("ownerMapper.selectBoardsPaging", pageNo);
	}
	
	@Override
	public int selectCountBoards() {
		return session.selectOne("ownerMapper.selectBoardCount");
	}
	
	@Override
	public Board selectBoardByIndex(int index) {
		session.update("ownerMapper.updateBoardReadCount", index);
		return session.selectOne("ownerMapper.selectBoardInfo", index);
	}
	
	@Override
	public List<Board> selectNotice() {
		return session.selectList("ownerMapper.selectNotice");
	}
	
	@Override
	public int insertBoard(HashMap map) {
		return session.insert("ownerMapper.insertBoard", map);
	}


}
