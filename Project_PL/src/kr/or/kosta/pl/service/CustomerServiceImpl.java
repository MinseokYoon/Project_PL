package kr.or.kosta.pl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kosta.pl.dao.CustomerDAO;
import kr.or.kosta.pl.exception.DuplicatedIdException;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	private CustomerDAO dao;
	
	public CustomerServiceImpl() {}
	
	@Autowired
	public CustomerServiceImpl(CustomerDAO dao){
		this.dao = dao;		
	}
	
	
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

	@Override
	public Customer findCustomerById(String customerId) {
		
		Customer customer = dao.selectCustomerById(customerId);
		
		return customer;
	}

	@Override
	public List<Product> findItemList() {
		
		return dao.selectItemList();
		
	}

	@Override
	public Product findItemById(String itemName) {
		
		return dao.selectItemByName(itemName);
	}

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


	@Override
	public List<Product> findItemByNameMany(String itemName) {
	
		return dao.selectItemByNameMany(itemName);
	}



	@Override
	public List<Store> findStoreNameByCount(String itemName) {
		
		return dao.selectStoreNameByCount(itemName);
	}
	
	
	
	
	
	
	

}