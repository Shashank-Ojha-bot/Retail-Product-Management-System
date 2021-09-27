package com.cognizant.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.model.Cart;
import com.cognizant.model.Vendor;
import com.cognizant.model.VendorWishlist;
import com.cognizant.repository.CartRepository;
import com.cognizant.repository.VendorRepository;
import com.cognizant.repository.VendorWishlistRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

/**
 * @author Vamsi Krishna
 *
 */
@Service public class ProductToBuyService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductToBuyService.class);
	
	@Autowired private RestTemplate restTemplate;
	@Autowired private EurekaClient eurekaClient;
	Cart cart;
	@Autowired CartRepository cartRepository;
	VendorWishlist vendorWishList;
	@Autowired VendorWishlistRepository vendorWishlistRepository;
	@Autowired Vendor vendor;
	@Autowired VendorRepository vendorRepository;
	@Value("${vendor.name}") String vendorServiceName;
	
	/** 
	 * Adds Customer product to cart
	 * @param customerId
	 * @param productId
	 * @param zipcode
	 * @param deliveryDate
	 * @return Cart
	 * @throws ParseException
	 */
	public Cart addProductToCart(String customerId, String productId, String zipcode, String deliveryDate) throws ParseException {
		log.info("addProductToCart method ProductToBuyService class");
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(deliveryDate);  
		cart = new Cart();
		cart.setCartId(productId+"A");
		cart.setProductId(productId);
		cart.setZipcode(zipcode);
		cart.setDeliveryDate(date1);
		Vendor x = addProductToWishlist(customerId,productId);
		cart.setvObject(x);
		cartRepository.save(cart);
		return cart;
	}

	/** Adds product to WishList
	 * @param customerId
	 * @param productId
	 * @return Vendor
	 */
	public Vendor addProductToWishlist(String customerId, String productId) {
		log.info("addProductToWishlist method ProductToBuyService class");
		try{
			
			Application application = eurekaClient.getApplication(vendorServiceName);
			InstanceInfo instanceInfo = application.getInstances().get(0);
			String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "getVendorDetails/" + productId;
			String jsonValues = restTemplate.getForObject(url, String.class);
			ObjectMapper mapper = new ObjectMapper();
			
			List<Vendor> listOfVendors = mapper.readValue(jsonValues, new TypeReference<List<Vendor>>(){});
			
			for(Vendor i : listOfVendors) {
				vendorRepository.save(i);
			} 
			
			Vendor maxRatedVendor = listOfVendors
					.stream()
					.max(Comparator
					.comparing(Vendor::getRating))
					.get();
			
			vendorWishList = new VendorWishlist();
			vendorWishList.setVendorWishlistId(productId+"B");
			vendorWishList.setProductId(productId);
			vendorWishList.setQuantity(1);
			vendorWishList.setDateAddedToWishlist(new Date());
			vendorWishList.setVendorId(maxRatedVendor.getVendorId());
			vendorWishlistRepository.save(vendorWishList);

			return maxRatedVendor;
			
		}catch(Exception e) {
			return null;
		}
	}
	
}

