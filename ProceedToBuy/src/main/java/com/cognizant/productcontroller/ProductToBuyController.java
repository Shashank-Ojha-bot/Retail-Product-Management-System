package com.cognizant.productcontroller;

import java.text.ParseException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.Cart;
import com.cognizant.model.Vendor;
import com.cognizant.services.ProductToBuyService;

/**
 * @author Vamsi Krishna
 * java doc
 *
 */
@RestController
public class ProductToBuyController {

	private static final Logger log = LoggerFactory.getLogger(ProductToBuyController.class);

	@Autowired
	ProductToBuyService ps;

	/** Controller for "/addProductToCart"
	 * @param body
	 * @return Cart
	 * @throws ParseException
	 */
	@PostMapping("/addProductToCart")
	public Cart addProductToCart(@RequestBody Map<String, Object> body) throws ParseException {
		log.info("addProductToCart method ProductToBuyController class");
		return ps.addProductToCart(body.get("customerId").toString(), body.get("productId").toString(),
				body.get("zipcode").toString(), body.get("deliveryDate").toString());
	}

	/** Controller for "/addProductToWishlist"
	 * @param body
	 * @return wishlist
	 */
	@PostMapping("/addProductToWishlist")
	public Vendor addProductToWishlist(@RequestBody Map<String, Object> body) {
		log.info("addProductToWishlist method ProductToBuyController class");
		return ps.addProductToWishlist(body.get("customerId").toString(), body.get("productId").toString());
	}
}
