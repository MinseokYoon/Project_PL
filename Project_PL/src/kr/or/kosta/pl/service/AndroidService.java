package kr.or.kosta.pl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kosta.pl.dao.AndroidDAO;
import kr.or.kosta.pl.vo.Product;

@Service("androidService")
public class AndroidService {
	private AndroidDAO dao;
	
	public AndroidService() {}
	
	@Autowired
	public AndroidService(AndroidDAO dao) {
		this.dao = dao;
	}
	
	public List<Product> getStoreItemList(String storeName) {
		
		List<Product> itemList = dao.selectStoreItemsByStoreName(storeName);
		
		return itemList;
	}
}
