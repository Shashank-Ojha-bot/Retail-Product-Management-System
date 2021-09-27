package com.retail.shop.retailShop.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.retail.shop.retailShop.Service.RetailService;

@SessionAttributes({"name","token"})
@Controller 
public class RetailController {
	
	@Autowired RetailService service;
	private String err = "error";
	 String name = null;
	 String token = null;

	@GetMapping("/login")
	public String showlogin(ModelMap model) {
		try {
			return "login";
		}catch(Exception e) {
			e.printStackTrace();
			return err;
		}
	}
	
	@GetMapping("/logout")
	public String showlogOut(ModelMap model,SessionStatus status) {
		try {
			status.setComplete();
			name=null;
			return "login";
		}catch(Exception e) {
			e.printStackTrace();
			return err;
		}
	}
	
	
	@PostMapping("/login")
	public String showlogin(  @RequestParam String Id , @RequestParam String name, @RequestParam String password, ModelMap model, HttpSession session) {
		try {
			this.name = name;
			this.token = service.validateUser(Id,name,password);
			session.setAttribute("token", token);
			if (token == null) {
				model.put("errMessage", "invalid Credentials");
				return "login";
			}
			model.put("Id",Id);
			model.put("name", name);
			model.put("password", password);
			model.put("Name", name);
			return "redirect:product";

		}catch(Exception e) {
			e.printStackTrace();
			return "login";
		}
	}

	@GetMapping("/product")
	public String product(ModelMap model) {
		try {
			model.put("Name", name);
			model.put("listOfProducts", service.retrieveRetails());
			return "product";
			
		} catch (Exception e) {
			return err;
		} 
	}

	@GetMapping("/add-towish")
	public String addToWishlist(@RequestParam String id, ModelMap model) {
		try {
			model.put("Name", name);
			service.addWish(id);
			return "redirect:product";
		}catch(Exception e) {
			return err;
		}
	}
	

	@GetMapping("/wishlist")
	public String wishlist(ModelMap model) {
		try {
			
			model.put("Name", name);
			model.put("wishes", service.retrieveWishes());
			return "wishlist";
		}catch(Exception e) {
			return err;
		}
	}

	@GetMapping("/add-tocart")
	public String addToCart(@RequestParam String id,ModelMap model) {
		try {
			model.put("Name", name);
			service.addCart(id);
			return "redirect:cart";
		}catch(Exception e) {
			return err;
		}
	}
	
	
	
	@RequestMapping("/add-rating")
	public String addRating(@RequestParam String rating , @RequestParam String id , ModelMap model) {
		try {
			model.put("Name", name);
			model.put("rating", rating);
			model.put("id", id);
			service.UpdateRating(id, rating);
			return "redirect:product";
		}catch(Exception e) {
			return err;
		}
	}
	
	
	

	@GetMapping("/cart")
	public String cartList(ModelMap model) {
		try {
			model.put("Name", name);
			model.put("carts", service.retrieveCarts());
			return "cart";
		}catch(Exception e) {
			return err;
		}
	}

	@PostMapping("/product")
	public String product(@RequestParam String search, ModelMap model){
		try {
			model.put("Name", name);
			model.put("listOfProducts", service.search(search));
			return "product";
			
		}catch(Exception e) {
			return err;
		}
	}
	
	@GetMapping("/error")
	public String errorPage( ModelMap model) {
		return err;
	}
}
