package kr.or.kosta.pl.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.kosta.pl.vo.Product;

@Repository
public class AndroidDAO {

	private SqlSessionTemplate session;
	
	@Autowired
	public AndroidDAO(SqlSessionTemplate session) {
		this.session = session;
	}
	
	public List<Product> selectStoreItemsByStoreName(String storeName) {
		return session.selectList("androidMapper.selectStoreItemListByStoreName", storeName);
	}
	
}
