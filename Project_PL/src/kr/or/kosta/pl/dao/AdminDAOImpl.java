package kr.or.kosta.pl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.util.PagingBean;
import kr.or.kosta.pl.vo.Admin;
import kr.or.kosta.pl.vo.Board;
import kr.or.kosta.pl.vo.Category;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

//Admin테이블과 연동하는 DAO클래스

@Repository
public class AdminDAOImpl implements AdminDAO {

	private SqlSessionTemplate session; // spring mybatis연동을 위한


	//생성자
	@Autowired
	public AdminDAOImpl(SqlSessionTemplate session) {
		this.session = session;
	}

	//no-arg 생성자
	public AdminDAOImpl() {
	}

	//////////////////////////// 관리자 /////////////////////////

	//관리자 등록
	@Override
	public int insertAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return session.insert("adminMapper.insertAdmin", admin);
	}

	//관리자 삭제
	@Override
	public int deleteAdminById(String adminId) {
		// TODO Auto-generated method stub
		return session.delete("adminMapper.deleteAdminById", adminId);
	}

	//관리자 수정
	@Override
	public int updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return session.update("adminMapper.updateAdmin", admin);
	}

	//ID로 관리자 찾기
	@Override
	public Admin selectAdminById(String adminId) {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectAdminById", adminId);
	}

	//관리자 조회
	@Override
	public List<Admin> selectAdmins() {
		// TODO Auto-generated method stub
		return session.selectList("adminMapper.selectAdmins");
	}

	//관리자 paging 처리
	@Override
	public List<Admin> selectAdminsPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("contentsPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo", pageNo);
		List<Admin> list = session.selectList("adminMapper.selectAdminsPaging", map);
		return list;
	}

	//Name으로 관리자 찾기
	@Override
	public List<Admin> selectAdminsByName(String adminName) {
		// TODO Auto-generated method stub
		return session.selectList("adminMapper.selectAdminsByName", adminName);
	}

	//관리자 수 
	@Override
	public int selectCountAdmins() {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectCountAdmins");
	}

	//////////////////////////// 물품관리///////////////////////////////////////////////////

	// 물품 추가
	@Override
	public int insertProduct(String itemName, int itemPrice, int categoryId) {
		HashMap map = new HashMap();
		//map.put("itemId", itemId);
		map.put("itemName", itemName);
		map.put("itemPrice", itemPrice);
		map.put("categoryId", categoryId);
		System.out.println(categoryId);
		return session.insert("adminMapper.insertProduct", map);
	}

	// 물품 삭제
	@Override
	public int deleteProductByItemId(int itemId) {
		// TODO Auto-generated method stub
		return session.delete("adminMapper.deleteProductByItemId", itemId);
	}
	
	// 물품 수정
	@Override
	public int updateProduct(int itemId,String itemName, int itemPrice, int categoryId) {
		HashMap map = new HashMap();
		map.put("itemId", itemId);
		map.put("itemName", itemName);
		map.put("itemPrice", itemPrice);
		map.put("categoryId", categoryId);
		System.out.println(categoryId);
		return session.update("adminMapper.updateProduct", map);
	}
	
	//Name으로 찾기
	@Override
	public Product selectProductByName(String itemName) {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectProductByName", itemName);
	}
	
	//ID로 물품 찾기
	@Override
	public Product selectProductByItemId(int itemId) {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectProductByItemId", itemId);
	}

	// 물품 조회
	@Override
	public List<Product> selectProducts() {
		// TODO Auto-generated method stub
		return session.selectList("adminMapper.selectProducts");
	}
	
	// 물품 paging처리
	@Override
	public List<Product> selectProductsPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("contentsPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo", pageNo);
		List<Product> list = session.selectList("adminMapper.selectProductsPaging", map);
		return list;
	}
	

	//Name으로 물품 찾기 (List) 
	@Override
	public List<Product> selectProductsByItemName(String itemName) {
		
		return session.selectList("adminMapper.selectProductsByItemName", itemName);
	}

	// 물품 수
	@Override
	public int selectCountProducts() {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectCountProducts");
	}

	//////////////////////////// 카테고리관리///////////////////////////////////////////////////

	//카테고리 등록
	@Override
	public int insertCategory(Category category) {
		// TODO Auto-generated method stub
		return session.insert("adminMapper.insertCategory", category);
	}

	//카테고리 삭제
	@Override
	public int deleteCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return session.delete("adminMapper.deleteCategoryById", categoryId);
	}
	
	//카테고리 리스트 
	@Override
	public List<Category> selectCategoryList() {
		List<Category> list = new ArrayList();
		list = session.selectList("adminMapper.selectCategoryList");
		return list;
	}

	//ID로 카테고리 찾기
	@Override
	public Category selectCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectCategoryById", categoryId);
	}

	//카테고리 조회
	@Override
	public List<Category> selectCategorys() {
		// TODO Auto-generated method stub
		return session.selectList("adminMapper.selectCategorys");
	}
	
	//카테고리 paging처리
	@Override
	public List<Category> selectCategorysPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("contentsPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo", pageNo);
		List<Category> list = session.selectList("adminMapper.selectCategorysPaging", map);
		return list;
	}

	//카테고리 수 
	@Override
	public int selectCountCategorys() {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectCountCategorys");
	}

	//////////////////////////// 편의점 관리///////////////////////////////////////////////////

	//편의점 등록
	@Override
	public int insertStore(Store store) {
		// TODO Auto-generated method stub
		return session.insert("adminMapper.insertStore", store);
	}

	//ID로 편의점 찾기
	@Override
	public Store selectStoreById(int storeId) {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectStoreById", storeId);
	}

	//편의점 삭제
	@Override
	public int deleteStoreById(int storeId) {
		// TODO Auto-generated method stub
		return session.delete("adminMapper.deleteStoreById", storeId);
	}

	//편의점 수정
	@Override
	public int updateStore(Store store) {
		// TODO Auto-generated method stub
		return session.update("adminMapper.updateStore", store);
	}

	//편의점 조회
	@Override
	public List<Store> selectStores() {
		// TODO Auto-generated method stub
		return session.selectList("adminMapper.selectStores");
	}

	//Name으로 편의점 찾기
	@Override
	public List<Store> selectStoresByName(String storeName) {
		// TODO Auto-generated method stub
		return session.selectList("adminMapper.selectStoresByName", storeName);
	}

	//편의점 수
	@Override
	public int selectCountStores() {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectCountStores");
	}

	//편의점 paging처리
	@Override
	public List<Store> selectStoresPaging(int pageNo) {
		HashMap map = new HashMap();
		map.put("contentPerPage", PagingBean.CONTENTS_PER_PAGE);
		map.put("pageNo", pageNo);
		// map.put("storeId", storeId); //편의점 id값 넣기

		List<Store> list = session.selectList("adminMapper.selectStoresPaging", map);
		return list;
	}

	//ID로 점주 찾기
	@Override
	public Store selectByOwnerId(String ownerId) {
		// TODO Auto-generated method stub
		return session.selectOne("adminMapper.selectByOwnerId", ownerId);
	}
	
	//////////////////////////// 게시판 관련 ///////////////////////////////////////////////////
	
	@Override
	public List<Board> selectNotice() {
		return session.selectList("adminMapper.selectNotice");
	}
	
	@Override
	public List<Board> selectBoardsPaging(int pageNo) {
		return session.selectList("adminMapper.selectBoardsPaging", pageNo);
	}

	@Override
	public int selectCountBoards() {
		return session.selectOne("adminMapper.selectBoardCount");
	}

	@Override
	public Board selectBoardByIndex(int index) {
		session.update("adminMapper.updateBoardReadCount", index);
		return session.selectOne("adminMapper.selectBoardInfo", index);
	}
	
	@Override
	public int insertBoard(HashMap map) {
		return session.insert("adminMapper.insertBoard", map);
	}

	

	


	


}
