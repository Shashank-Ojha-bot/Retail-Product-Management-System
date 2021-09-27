package com.cognizant;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.json.JSONException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.model.VendorWishlist;

/**
 * @author Vamsi Krishna
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProceedToBuyApplicationTests {
	
	private static final Logger log = LoggerFactory.getLogger(ProceedToBuyApplicationTests.class);
	
	@Autowired private TestRestTemplate restTemplate;
	@Autowired private VendorWishlist v;
	
	/**tests addProductToWishlist - Positive
	 * @throws JSONException 
	 */
	@Test void addProductToWishlistTestPositive1() throws JSONException {
		log.info("into addProductToWishlistTestPositive method in ProceedToBuyApplicationTests class");
		String head = "{\"customerId\": \"ABCD\",\"productId\": \"P103\"}";
		String result1 = "{\"vendorId\": \"V101\",\"vendorName\": \"Suresh\",\"deliveryCharge\": 35.0,\"rating\": 4.2}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(head, headers);
		ResponseEntity<String> response = restTemplate.exchange("/addProductToWishlist", HttpMethod.POST, entity, String.class);
		JSONAssert.assertEquals(result1, response.getBody(), true);
	}
	
	/**tests /addProductToWishlist - Negative
	 * @throws JSONException
	 */
	@Test void addProductToWishlistTestNegative1() throws JSONException {
		log.info("into addProductToWishlistTestNegative method in ProceedToBuyApplicationTests class");
		String head = "{\"customerId\": \"ABCD\",\"productId\": \"P104\"}";
		String result2 = "{\"vendorId\": \"V104\",\"vendorName\": \"Priya\",\"deliveryCharge\": 45.0,\"rating\": 2.2}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(head, headers);
		ResponseEntity<String> response = restTemplate.exchange("/addProductToWishlist", HttpMethod.POST, entity, String.class);
		JSONAssert.assertNotEquals(result2, response.getBody(), true);
	}
	
	/**tests addProductToWishlist - Positive
	 * @throws JSONException 
	 */
	@Test void addProductToWishlistTestPositive2() throws JSONException {
		log.info("into addProductToWishlistTestPositive method in ProceedToBuyApplicationTests class");
		String head = "{\"customerId\": \"ABCD\",\"productId\": \"P105\"}";
		String result1 = "{\"vendorId\": \"V101\",\"vendorName\": \"Suresh\",\"deliveryCharge\": 35.0,\"rating\": 4.2}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(head, headers);
		ResponseEntity<String> response = restTemplate.exchange("/addProductToWishlist", HttpMethod.POST, entity, String.class);
		JSONAssert.assertEquals(result1, response.getBody(), true);
	}
	
	/**tests /addProductToWishlist - Negative
	 * @throws JSONException
	 */
	@Test void addProductToWishlistTestNegative2() throws JSONException {
		log.info("into addProductToWishlistTestNegative method in ProceedToBuyApplicationTests class");
		String head = "{\"customerId\": \"ABCD\",\"productId\": \"PA25\"}";
		String result2 = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(head, headers);
		ResponseEntity<String> response = restTemplate.exchange("/addProductToWishlist", HttpMethod.POST, entity, String.class);
		JSONAssert.assertEquals(result2, response.getBody(), true);
	}
	
	/**tests /addProductToCart - Positive
	 * @throws JSONException
	 */
	@Test void addProductToCartTestPositive1() throws JSONException {
		log.info("into addProductToCartTestPositive1 method in ProceedToBuyApplicationTests class");
		String head = "{\"customerId\": \"ABCD\",\"productId\": \"P111\",\"zipcode\": \"123456\",\"deliveryDate\": \"6/6/2000\"}";
		String result1 = "{\"cartId\": \"P111A\",\"productId\": \"P111\",\"zipcode\": \"123456\",\"deliveryDate\": \"2000-06-05T18:30:00.000+00:00\",\"vObject\": {\"vendorId\": \"V102\",\"vendorName\": \"Karan\",\"deliveryCharge\": 25.0,\"rating\": 3.6}}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(head, headers);
		ResponseEntity<String> response = restTemplate.exchange("/addProductToCart", HttpMethod.POST, entity, String.class);
		JSONAssert.assertEquals(result1, response.getBody(), true);
	}
	
	/**
	 *  Tests main method of Proceed to buy microservice
	 */
	@Test void vendorWishListTest() {
		log.info("Into vendorWishListTest method");
		String[] st = new String[1];
		st[0] = "";
		ProceedToBuyApplication.main(st);
		
		v.setVendorWishlistId("123");
		v.setVendorId("ABC");
		v.setProductId("XYZ");
		Date d = new Date();
		v.setDateAddedToWishlist(d);
		v.setQuantity(5);
		String s = "VendorWishlist [vendorWishlistId=123, vendorId=ABC, productId=XYZ, quantity=5, dateAddedToWishlist="+d+"]";
		assertEquals(s,v.toString());
	}
	
}