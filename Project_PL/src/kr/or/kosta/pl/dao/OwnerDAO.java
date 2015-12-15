package kr.or.kosta.pl.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Order;
import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;


public interface OwnerDAO {
	
	/*-----------------------기본 dao-----------------------*/
	int insertOwner(Owner owner);

	int deleteOwnerById(String ownerId);

	int updateOwner(Owner owner);

	
	/*-----------------------편의점 주인 조회-----------------------*/
	Owner selectOwnerById(String ownerId);

	List<Owner> selectOwners();

	List<Owner> selectOwnersByName(String ownerName);

	int selectCountOwners();
	
	List<Owner> selectOwnersPaging(int pageNo);
	
	
	/*-----------------------제품 관련 dao-----------------------*/
	List<Product> selectProductsPaging(int pageNo,String ownerId);
	
	int selectCountProducts(String ownerId);

	List<Product> selectProductByName(String productName,String ownerId); 

	Product selectOneProduct(String pName, String ownerId);

	int updateInputProduct(String ownerId, int resultCount,int itemId);


	/*-----------------------주문 관련 dao-----------------------*/
	int selectCountOrders(String ownerId);

	List<Order> selectOrdersPaging(int pageNo, String ownerId);

	List<Order> selectOrdersByPhonePaging(int pageNo, String ownerId, String cusPhone);

	int selectCountOrdersByPhone(String ownerId, String cusPhone);

	int updateOrderStatus(String orderNumber);
	
	int updateAllOrdersStatus(String customerId, int storeId); 
	
	int updateOrderCount(int storeId, int itemId, int nowCount);
	
	int selectItemcountNow(int storeId, int itemId);	//현재 물품 개수 조회
	
	/*-----------------------본사물품 관련 dao-----------------------*/
	int selectHeadOfficeProductCount(String ownerId);

	Product selectHeadOfficeProductByName(String productName);

	int insertServerItem(String ownerId, String itemId, String inputCount, Date date);

	List<Product> selectHeadOfficeProductsPaging(int pageNo, String ownerId);

	List<Product> selectHeadOfficeProductBySearchName(String searchValue); //본사물품 검색하는거
	
	/*-----------------------고객 point 관련 dao-----------------------*/
	
	int selectCustomerPoint(String customerId);
	
	int updateCustomerPoint(String customerId, int customerTotalPoint);
	
	/*-----------------------게시판 관련 dao-----------------------*/
	List<Board> selectBoardsPaging(int pageNo);
	
	int selectCountBoards();
	
	Board selectBoardByIndex(int index);
	
	List<Board> selectNotice();
	
	int insertBoard(HashMap map);



}
