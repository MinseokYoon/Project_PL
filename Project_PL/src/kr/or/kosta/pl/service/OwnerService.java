package kr.or.kosta.pl.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.exception.OwnerNotFoundException;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;


public interface OwnerService{
	
	/*------------------------------편의점 주인 기본 서비스------------------------------*/
	void addOwner(Owner owner) throws DuplicatedIdException;
	
	void removeOwner(String ownerId) throws OwnerNotFoundException;
	
	void updateOwner(Owner newOwner) throws OwnerNotFoundException;
	
	
	
	/*-----------------------------편의점 주인 조회 서비스--------------------------------*/
	Owner findOwnerById(String ownerId) ;
	
	List<Owner> getAllOwners();

	List<Owner> findOwnerByName(String ownerName) ;
	
	int getAllCountOwners();
	
	Map getAllOwnersPaging(int pageNo);

	
	
	/*-----------------------------재품 관련 서비스--------------------------------*/
	Map getAllProductsPaging(int pageNo,String ownerId);

	List<Product> findProductByName(String productName,String ownerId); //재고물품 검색기능

	Product findOneProductByName(String pName, String ownerId);
	
	int updateCountProduct(String ownerId, int resultCount, int itemId);
	
	

	/*-----------------------------주문관련 서비스--------------------------------*/
	Map getAllOrderListPaging(int pageNo, String ownerId);

	Map getOrderListByPhonePaging(int pageNo, String ownerId, String cusPhone);

	Map updateOrderBySelect(int pageNo, String ownerId, String customerPhone, String orderNumber);

	int updateAllOrders(String customerId, int storeId, int[] itemId, int[] orderCount, int customerPoint);

	
	
	/*-----------------------------본사물품 관련 서비스--------------------------------*/
	Map getAllHeadOfficeProductsListPaging(int pageNo, String ownerId);

	Product findHeadOfficeProductByName(String productName);

	int inputHeadOfficeProduct(String ownerId, String itemId, String inputCount, Date date);
	
	List<Product> findHeadOfficeProductBySearchName(String searchValue); //본사물품 검색기능(이름으
	
	
	/*-----------------------------게시판 처리 서비스--------------------------------*/
	Map getAllBoard(int pageNo);
	
	Board getBoardInfo(int index);

	List<Board> getNotice();

	void insertBoard(HashMap map);

	
	
}
