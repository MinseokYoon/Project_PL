package kr.or.kosta.pl.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Order;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

public interface CustomerDAO {
	
	int insertCustomer (Customer customer);
	
	Customer selectCustomerById(String customerId);
	
	List<Product> selectItemList();
	
	Product selectItemByName(String itemName);
	
	List<Product> selectItemListByCategory(int categoryId);
	
	List<Product> selectItemByNameMany(String itemName);
	
	List<Store> selectStoreNameByCount(String itemName);
	
	int insertCart(String customerId, int storeId, int itemId, int countItem);
	
	List<Board> selectBoardsPaging(int pageNo);
	
	int selectCountBoards();
	
	Board selectBoardByIndex(int index);
	
	List<Store> selectStoreName(String storeName);
	
	Store selectStoreById(int storeId);
	
	List<Cart> selectCartByCustomerId(String customerId);
	
	int deleteCart(String customerId, int storeId, int itemId);

	List<Board> selectNotice();

	int insertOrder(String customerId, int storeId, int itemId, int orderCount, int orderStatus);

	List<Order> selectOrderByCustomerId(String customerId);

	List<Order> selectOrderByCustomerIdLast(String customerId);

	int updateCustomer(Customer customer);

	int insertBoard(HashMap map);

}
