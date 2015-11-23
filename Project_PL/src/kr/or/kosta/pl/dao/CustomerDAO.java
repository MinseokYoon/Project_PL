package kr.or.kosta.pl.dao;

import java.util.List;

import kr.or.kosta.pl.vo.Customer;
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
}
