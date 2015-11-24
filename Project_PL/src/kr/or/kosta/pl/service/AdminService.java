package kr.or.kosta.pl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.kosta.pl.exception.AdminNotFoundException;
import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.vo.Admin;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Product;

public interface AdminService {

	void addAdmin(Admin admin) throws DuplicatedIdException;
	
	List<Admin> getAllAdmins();
	
	Map getAllAdminsPaging(int pageNo);
	
	Admin findAdminById(String adminId);
	
	List<Admin> findAdminByName(String adminName);
	
	void removeAdmin(String adminId) throws AdminNotFoundException;
	
	void updateAdmin(Admin newAdmin) throws AdminNotFoundException;
	
////////////////////////////물품관리///////////////////////////////////////////////////

	void addProduct(Product product) throws DuplicatedIdException;
	
	List<Product> getAllProducts();
	
	Map getAllProductsPaging(int pageNo);
	
	Product findProductByItemId(int itemId);
	
	Product findProductByItemName(String itemName);
	
	void removeProduct(int itemId) throws AdminNotFoundException;
	
	void updateProduct(Product newPro) throws AdminNotFoundException;
	
	Map getAllBoard(int pageNo);
	
	Board getBoardInfo(int index);
}
