package kr.or.kosta.pl.service;

import java.util.List;
import java.util.Map;

import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

public interface CustomerService {
	
	/**
	 * 고객 등록 메소드
	 * 						-id중복검사가  필요하다.
	 * @param customer
	 */
	void addCustomer(Customer customer) throws DuplicatedIdException;
	
	Customer findCustomerById(String customerId);
	
	List<Product> findItemList();
	
	Product findItemById(String itemName);
	
	List<Product> findItemListByCategorySmallRecommand(int categoryId);
	
	List<Product> findItemListByCategoryMain(int categoryId);

	List<Product> findItemByNameMany(String itemName);
	
	List<Store> findStoreNameByCount(String itemName);
	
	void addCart(String customerId, int storeId, int itemId, int countItem);
	
	Map getAllBoard(int pageNo);
	
	Board getBoardInfo(int index);
	
	List<Store> findStoreName(String storeName);
	
	Store findStoreById(int storeId);
	
	List<Cart> findCartByCusotmerId(String customerId);
}
