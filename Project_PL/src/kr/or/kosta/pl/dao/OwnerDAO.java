package kr.or.kosta.pl.dao;

import java.util.ArrayList;
import java.util.List;

import kr.or.kosta.pl.vo.Owner;
import kr.or.kosta.pl.vo.Product;


public interface OwnerDAO {
	
	int insertOwner(Owner owner);//ok

	int deleteOwnerById(String ownerId);//ok

	int updateOwner(Owner owner);//ok

	Owner selectOwnerById(String ownerId);//ok

	List<Owner> selectOwners();//ok

	List<Owner> selectOwnersByName(String ownerName);//ok

	int selectCountOwners();//ok
	
	List<Product> selectProductsPaging(int pageNo,String ownerId);
	
	int selectCountProducts(String ownerId);//ok

	List<Product> selectProductByName(String productName,String ownerId); // 물품이름으로 검색하는 메소드

	Product selectOneProduct(String pName, String ownerId);

	int updateInputProduct(String ownerId, int resultCount,int itemId); 
}
