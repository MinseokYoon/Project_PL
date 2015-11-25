package kr.or.kosta.pl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	
	private SqlSessionTemplate session;
	
	@Autowired
	public CustomerDAOImpl(SqlSessionTemplate session){
		this.session = session;
	}
	public CustomerDAOImpl(){}
	
	@Override
	public int insertCustomer(Customer customer) {

		return session.insert("customerMapper.insertCustomer", customer);
	}

	@Override
	public Customer selectCustomerById(String customerId) {
		return session.selectOne("customerMapper.selectCustomerById", customerId);
	}
	@Override
	public List<Product> selectItemList() {
		
		List<Product> list = new ArrayList();
		
		
		int[] result = new int[12];

		for (int i = 0; i < 12; i++) {
			result[i] = (int) ((Math.random() * 120) + 1);
			for (int k = 0; k < i; k++) {
				if (result[i] == result[k]) {
					result[i] = (int) ((Math.random() * 120) + 1);
					k = 0;
				}
			}
		}
		for(int i =0; i<12; i++){
			Product pd = session.selectOne("customerMapper.selectItemList", result[i]);
			
			list.add(pd);		
		}
		
		return list;
	}
	
	
	@Override
	public Product selectItemByName(String itemName) {

		Product pd =session.selectOne("customerMapper.selectItemByName", itemName);

		return session.selectOne("customerMapper.selectItemByName", itemName);
	}
	
	
	@Override
	public List<Product> selectItemListByCategory(int categoryId) {
		
		List<Product> list = new ArrayList();
		//mapper에서 카테고리 개수만큼 찾아와야함
		list = session.selectList("customerMapper.selectItemListByCategory", categoryId);
		
//		for(int i =0; i<12; i++){
//			Product pd = session.selectOne("customerMapper.selectItemListByCategory", categoryId);
//			
//			list.add(pd);		
//		}
		
		return list;
	}
	
	
	@Override
	public List<Product> selectItemByNameMany(String itemName) {
		List<Product> list = new ArrayList();
		list =session.selectList("customerMapper.selectItemByNameMany", itemName);
		return list;
	}
	
	
	@Override
	public List<Store> selectStoreNameByCount(String itemName) {
		List<Store> list = new ArrayList();
		list = session.selectList("customerMapper.selectStoreByCount", itemName);
		return list;
	}
	
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
	
	@Override
	public List<Board> selectBoardsPaging(int pageNo) {
		return session.selectList("customerMapper.selectBoardsPaging", pageNo);
	}
	
	@Override
	public int selectCountBoards() {
		return session.selectOne("customerMapper.selectBoardCount");
	}

	@Override
	public Board selectBoardByIndex(int index) {
		return session.selectOne("customerMapper.selectBoardInfo", index);
	}

	/*매장찾기*/
	@Override
	public List<Store> selectStoreName(String storeName) {
		return session.selectList("customerMapper.findStoreName",storeName);

	}

	@Override
	public Store selectStoreById(int storeId) {
		
		return session.selectOne("customerMapper.selectStoreById", storeId);
	}
	
	@Override
	public List<Cart> selectCartByCustomerId(String customerId) {
	
		return session.selectList("customerMapper.selectCartByCustomerId", customerId);
	}
	
	



}
