package kr.or.kosta.pl.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.util.PagingBean;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;

/*
 * Owner 테이블과 연동하는 DAO 클래스
 */

@Repository("OwnerDAOImpl")
public class OwnerDAOImpl implements OwnerDAO {
	
	private SqlSessionTemplate session;
	//spring - mybatis 연결시키기 위해 필요 
	
	
	//no-arg 생성자
	public OwnerDAOImpl() {
	}

	//생성자
	@Autowired
	public OwnerDAOImpl(SqlSessionTemplate session) {
		this.session = session;
	}


	@Override
	public int insertOwner(Owner owner) {//
		return session.insert("ownerMapper.insertOwner",owner);
	}


	@Override	
	public int deleteOwnerById(String ownerId) {//
		return session.delete("ownerMapper.deleteOwnerById",ownerId);
	}

	@Override
	public int updateOwner(Owner owner) {//
		return session.update("ownerMapper.updateOwner",owner);
	}

	@Override
	public Owner selectOwnerById(String ownerId) {//
		return session.selectOne("ownerMapper.selectOwnerById",ownerId);
	}

	@Override
	public List<Owner> selectOwners() {//
		return session.selectList("ownerMapper.selectOwners");
	}

	@Override
	public List<Owner> selectOwnersByName(String ownerName) {//
		return session.selectList("ownerMapper.selectOwnersByName",ownerName);
	}

	@Override
	public int selectCountOwners() {//
		return session.selectOne("ownerMapper.selectCountOwners");
	}
	
	@Override
	public List<Product> selectProductsPaging(int pageNo,String ownerId) {
		HashMap map = new HashMap();
		map.put("contentPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo",pageNo);
		map.put("ownerId", ownerId); //편의점 id값 넣기 
		
		List<Product> list = session.selectList("ownerMapper.selectProductsPaging",map);
		return list;
	}

	@Override
	public int selectCountProducts(String ownerId) {
		return session.selectOne("ownerMapper.selectCountProducts",ownerId);
	}

	@Override
	public List<Product> selectProductByName(String productName,String ownerId) {
		HashMap map = new HashMap();
		map.put("productName", productName);
		map.put("ownerId",ownerId);
		
		return session.selectList("ownerMapper.selectProductByName",map);
	}

	@Override
	public Product selectOneProduct(String pName, String ownerId) {
		HashMap map = new HashMap();
		map.put("productName",pName);
		map.put("ownerId",ownerId);
		return session.selectOne("ownerMapper.selectOneProduct",map);
	}

	@Override
	public List<Board> selectBoardsPaging(int pageNo) {
		return session.selectList("ownerMapper.selectBoardsPaging", pageNo);
	}
	
	@Override
	public int selectCountBoards() {
		return session.selectOne("ownerMapper.selectBoardCount");
	}
	
	
	public int updateInputProduct(String ownerId, int resultCount,int itemId) {
		HashMap map = new HashMap();
		map.put("ownerId",ownerId);
		map.put("resultCount",resultCount);
		map.put("itemId", itemId);
		return session.update("ownerMapper.updateInputProduct",map);
	}

	@Override
	public Board selectBoardByIndex(int index) {
		return session.selectOne("ownerMapper.selectBoardInfo", index);
	}
	
}
