package kr.or.kosta.pl.controller;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kosta.pl.service.AndroidService;
import kr.or.kosta.pl.service.CustomerService;
import kr.or.kosta.pl.vo.Cart;
import kr.or.kosta.pl.vo.Customer;
import kr.or.kosta.pl.vo.Product;
import kr.or.kosta.pl.vo.Store;

@Controller
@RequestMapping("/android")
public class AndroidController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AndroidService androidService;
	   
	@RequestMapping("/login.do")
	@ResponseBody
	public Customer loginAndroid(HttpServletResponse res,@RequestParam String id, String password) {
		res.setCharacterEncoding("UTF-8");
		System.out.println("ID : " + id);
		System.out.println("PW : " + password);
		Customer customer = customerService.findCustomerById(id);
		JSONObject jsonMain = new JSONObject();
		JSONObject jObject = new JSONObject();
		if (customer != null) {
			if (customer.getCustomerPassword().equals(password)) {
				return customer;
			}
		} else {
			customer = null;
		}
		return customer;
	}
	
	@RequestMapping("/modify.do")
	@ResponseBody
	public String modify(@RequestBody(required=false) String json) {   
		System.out.println("======");
	   try {
		   ObjectMapper m = new ObjectMapper();
		   System.out.println("------");
		   System.out.println("json : " + URLDecoder.decode(json, "UTF-8"));
		   Customer customer = (Customer)m.readValue(URLDecoder.decode(json, "UTF-8"), Customer.class);
		   System.out.println("customerID : " + customer.getCustomerName());
		   customerService.modifyCustomer(customer);
		   
//		   List<Customer> list = m.readValue("[]", new TypeReference<List<Customer>>();
	   } catch (Exception e) {
		   e.printStackTrace();
		   return "2";
	   }
	   return "1";
	}
	
	@RequestMapping("find_store_name.do")
	@ResponseBody
	public List<Store> findStore(@RequestParam String storeName) { // 모델어트리뷰트
		System.out.println(storeName);
		System.out.println("==========");
		List<Store> list = customerService.findStoreName(storeName);
//		System.out.println(storeName);
//		System.out.println(storeName.equals(""));

		if (list.size() < 1) {
			return null;

		} else {
			System.out.println("-----");
			return list;
		}
	}
	
	@RequestMapping("/storeItem.do")
	@ResponseBody
	public List<Product> getStoreItem(@RequestParam String storeName) {
		System.out.println("storeName : " + storeName);
		List<Product> itemList = androidService.getStoreItemList(storeName);	
		return itemList;
	}
	
	@RequestMapping("/cart.do")
	@ResponseBody
	public String cart(@RequestParam String storeName, String itemId, String countItem, String customerId) {
		List<Store>list = customerService.findStoreName(storeName);
		int storeIdd = list.get(0).getStoreId();
		int itemIdd = Integer.parseInt(itemId);			//내용들 숫자 변환
		int countItemm = Integer.parseInt(countItem);
		
		customerService.addCart(customerId, storeIdd, itemIdd, countItemm);
		System.out.println("장바구니 추가 완료");
		return "1";
	}
	
	@RequestMapping("/cartPage.do")
	@ResponseBody
	public List<Cart> cartPage(@RequestParam String customerId) {
		System.out.println("cartPage : start (" + customerId + ")");
		
		List<Cart> list = customerService.findCartByCusotmerId(customerId);
		
		System.out.println("end");
		return list;
	}
	
	@RequestMapping("/order.do")
	@ResponseBody
	public String order(@RequestBody(required=false) String jsonArray) {
		System.out.println("order start");
		ObjectMapper oMapper = new ObjectMapper();
		System.out.println(jsonArray);
		try {
			List<Cart> list = (List<Cart>)oMapper.readValue(jsonArray, new TypeReference<List<Cart>>(){});
			System.out.println("mapper ok");
			System.out.println("listSize: " + list.size());
			System.out.println(list);
			
			
			for(Cart cart : list) {
//				System.out.println(cart.getItemName());
				customerService.addOrderAndroid(cart.getCustomerId(), cart.getStoreId(), cart.getItemId(), cart.getCartCount(), 1);
				customerService.deleteCart(cart.getCustomerId(), cart.getStoreId(), cart.getItemId());
			}

		} catch(Exception e) {
			System.out.println("error start");
			e.printStackTrace();
		}
		
		return "1";
	}
}
