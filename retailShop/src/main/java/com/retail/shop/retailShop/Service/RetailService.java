package com.retail.shop.retailShop.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.retail.shop.retailShop.model.AuthResponse;
import com.retail.shop.retailShop.model.Cart;
import com.retail.shop.retailShop.model.Retail;
import com.retail.shop.retailShop.model.UserData;
import com.retail.shop.retailShop.model.Vendor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vamsi Krishna
 *
 */
@Service
public class RetailService {
	
	private static final Logger log = LoggerFactory.getLogger(RetailService.class);
	
	private String customerID = new String();
	@Autowired private EurekaClient eurekaClient;
	@Autowired private RestTemplate restTemplate;
	@Value("${product.name}") String product;
	@Value("${proceedtobuy.name}") String proceedToBuy;
	@Value("${vendor.name}") String vendor;
	@Value("${auth.name}") String auth;
	
	private String http = "http://";

	private static List<Retail> listOfProducts;
	private static List<Vendor> wishes = new LinkedList<>();
	private static List<Cart> carts = new LinkedList<>();
	String uName2;

	/** Checks user validity, generates a temporary token for 15 minutes
	 * @param userId
	 * @param name
	 * @param password
	 * @return valid user or not
	 */
	public String validateUser(String userId , String name, String password) {
		try {
			uName2 = name;
			customerID = userId;
			Application application1 = eurekaClient.getApplication(auth);
			InstanceInfo instanceInfo1 = application1.getInstances().get(0);
			String url1 = http + instanceInfo1.getIPAddr() + ":" + instanceInfo1.getPort() + "/" + "login";
			String head = " {\"userId\":\""+userId+"\",\"userName\":\""+name+"\",\"password\":\""+password+"\"}";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(head, headers);
			ResponseEntity<UserData> response = restTemplate.exchange(url1, HttpMethod.POST, entity, UserData.class);
			UserData user=response.getBody();
			
			String url2 = http + instanceInfo1.getIPAddr() + ":" + instanceInfo1.getPort() + "/" + "validate";
			String token = user.getAuthToken();
			String authorizationHeader = "Bearer " + token;
			HttpHeaders requestHeaders = new HttpHeaders();
	        
	        requestHeaders.add("Authorization", authorizationHeader);
	        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
	        ResponseEntity<AuthResponse> responseEntity = restTemplate.exchange(url2,HttpMethod.GET,requestEntity,AuthResponse.class);
	        if(responseEntity.getStatusCode() == HttpStatus.OK) log.info("Success");
	        return token;
		}catch(Exception e) {
			return null;
		}
	}
	

	/** Collects List Of Products from Product microservice
	 * @return List Of Products
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public List<Retail> retrieveRetails() throws Exception {
		Application application = eurekaClient.getApplication(product);
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String url = http + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/product";
		String jsonValues = restTemplate.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		listOfProducts = mapper.readValue(jsonValues, new TypeReference<List<Retail>>() {});
		return listOfProducts;
	}

	/** Collects list of WishList Items from Proceed to buy microservice
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List<Vendor> addWish(String id) throws Exception {
		try {
			Application application1 = eurekaClient.getApplication(proceedToBuy);
			InstanceInfo instanceInfo1 = application1.getInstances().get(0);
			String url1 = http + instanceInfo1.getIPAddr() + ":" + instanceInfo1.getPort() + "/"
					+ "addProductToWishlist";
			String head = "{\"customerId\": \""+customerID+"\",\"productId\": \"" + id + "\"}";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(head, headers);
			ResponseEntity<Vendor> response = restTemplate.exchange(url1, HttpMethod.POST, entity, Vendor.class);
			Vendor v=response.getBody();
			boolean x=true;
			for(Vendor i:wishes)
				if(i.getVendorId().equals(v.getVendorId()))
					x=false;
			if(x)
				wishes.add(response.getBody());
			//wishes.add(response.getBody());
			return wishes;

		} catch (Exception exc) {
			throw new Exception();
		}
	}

	/**
	 * @return Vendor details
	 */
	public List<Vendor> retrieveWishes() {
		return wishes;
	}

	/** Collects list of Cart Items from Proceed to buy microservice
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List<Cart> addCart(String id) throws Exception {
		int min = 100000, max = 999999;
		String dateOfDelivery;
		int a = (int)(Math.random()*(max-min+1)+min); 
		final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(a%2==0)  calendar.add(Calendar.DAY_OF_YEAR, 4);
		else calendar.add(Calendar.DAY_OF_YEAR, 5);
		dateOfDelivery = format.format(calendar.getTime());
		
		try {
			Application application1 = eurekaClient.getApplication(proceedToBuy);
			InstanceInfo instanceInfo1 = application1.getInstances().get(0);
			String url1 = http + instanceInfo1.getIPAddr() + ":" + instanceInfo1.getPort() + "/"
					+ "addProductToCart";
			String head = "{\"customerId\": \""+customerID+"\",\"productId\": \"" + id
					+ "\",\"zipcode\": \""+a+"\",\"deliveryDate\": \""+dateOfDelivery+"\"}";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(head, headers);
			ResponseEntity<Cart> response = restTemplate.exchange(url1, HttpMethod.POST, entity, Cart.class);
			Cart s = response.getBody();
			boolean x = true;
			for(Cart i:carts) if(i.getProductId().equals(s.getProductId())) x = false;
			if(x) carts.add(response.getBody());
			if(s.getvObject() == null) throw new Exception();
			return carts;

		} catch (Exception exc) {
			throw new Exception();
		}
	}

	/**
	 * @return Cart details
	 */
	public List<Cart> retrieveCarts() {
		return carts;
	}

	/** Searches Products list by name/id
	 * @param search
	 * @return
	 */
	public List<Retail> search(String search) throws Exception {
		Application application = eurekaClient.getApplication(product);
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String url = http + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/searchProductByName/"+search;
		String jsonValues = restTemplate.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		listOfProducts = mapper.readValue(jsonValues, new TypeReference<List<Retail>>() {});
		
		String url1 = http + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/searchProductById/"+search;
		String jsonValues1 = restTemplate.getForObject(url1, String.class);
		ObjectMapper mapper1 = new ObjectMapper();
		List<Retail> searchById = mapper1.readValue(jsonValues1, new TypeReference<List<Retail>>() {});
		log.info("listOfProducts");
		log.info(listOfProducts.toString());
		log.info(searchById.toString());
		
		return Stream.concat(listOfProducts.stream(), searchById.stream()).collect(Collectors.toList());
	}
	
	/** Sets the rating of a product
	 * @param productId
	 * @param rating
	 * @throws Exception
	 */
	public void UpdateRating(String productId, String rating) throws Exception{
		Application application = eurekaClient.getApplication(product);
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String url = http + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/addProductRating?id="+productId+"&rating="+rating;
		String head = "";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(head, headers);
		restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	}

}
