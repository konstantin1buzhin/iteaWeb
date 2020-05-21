package web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Product;

@Controller
public class ControllerCart {

	private static final String CART = "CART";
	private static final String CART_VALUE = "CART_VALUE";

	@Autowired
	private MySQLProductDAO productDB;

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String getGetCartPage(HttpSession session, ModelMap model) {
		Map<Product, Integer> listProduct;
		model.addAttribute("imgHeight", "150px");
		if (session.getAttribute(CART) != null) {
			listProduct = (Map) session.getAttribute(CART);
		} else {
			listProduct = new HashMap<Product, Integer>();
		}
		if (session.getAttribute(CART_VALUE) == null) {
			session.setAttribute(CART_VALUE, 0);
		}

		model.addAttribute("listProducts", listProduct);
		return "cart";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST, params = { "productDeleteId" })
	public String getPostCartPage(HttpSession session, ModelMap model,
			@RequestParam("productDeleteId") String productDeleteId) {
		if (productDeleteId != null) {
			Map<Product, Integer> listProduct;
			listProduct = (Map) session.getAttribute(CART);
			Product product = productDB.getProduct(Integer.valueOf(productDeleteId));
			listProduct.remove(product);
			int qnt = listProduct.size();
			session.setAttribute(CART_VALUE, qnt);

			model.addAttribute("imgHeight", "150px");
			if (session.getAttribute(CART) != null) {
				listProduct = (Map) session.getAttribute(CART);
			} else {
				listProduct = new HashMap<Product, Integer>();
			}
			if (session.getAttribute(CART_VALUE) == null) {
				session.setAttribute(CART_VALUE, 0);
			}

			model.addAttribute("listProducts", listProduct);
			return "cart";

		}
		return "cart";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	@ResponseBody
	public String getPostCartPage(HttpSession session, ModelMap model) {

		return "nothing";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST, params = { "productId", "productCount" })
	@ResponseBody
	public String getPostCartPage(HttpSession session, ModelMap model, @RequestParam("productId") String productId,
			@RequestParam("productCount") String productCount) {
		Map<Product, Integer> productsMap = new HashMap<Product, Integer>();

		if (session.getAttribute(CART) != null) {
			productsMap = (Map) session.getAttribute(CART);
		}
		if (productId != null && productCount != null) {
			Product product = productDB.getProduct(Integer.valueOf(productId));
			Integer countProducts = Integer.valueOf(productCount);
			Integer valueCount = 0;
			for (Entry<Product, Integer> entry : productsMap.entrySet()) {
				Product currentProduct = entry.getKey();
				if (currentProduct.equals(product)) {
					valueCount = entry.getValue();
				}
			}
			Integer putValueCount = 0;
			if (valueCount > countProducts) {

				putValueCount = valueCount - countProducts;
				productsMap.put(product, productsMap.get(product) - putValueCount);
			} else {
				putValueCount = countProducts - valueCount;
				productsMap.put(product, productsMap.get(product) + putValueCount);
			}

			session.setAttribute(CART, productsMap);
			int qnt = productsMap.size();
			session.setAttribute(CART_VALUE, qnt);
			int sumOrder = 0;
			for (Entry<Product, Integer> entry : productsMap.entrySet()) {

				sumOrder += entry.getKey().getPrice() * entry.getValue();
			}
			String lineOrder = "Your Order: " + sumOrder;

			return lineOrder;

		}
		return "Your Order: 0";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST, params = { "productId", "productQnt" })
	@ResponseBody
	public String getPostCartPage(ModelMap model, @RequestParam("productQnt") String productQnt,
			@RequestParam("productId") String productId, HttpSession session) {
		Map<Product, Integer> productsMap = new HashMap<Product, Integer>();

		if (session.getAttribute(CART) != null) {
			productsMap = (Map) session.getAttribute(CART);
		}
		if (productId != null && productQnt != null) {
			Product product = productDB.getProduct(Integer.valueOf(productId));
			Integer countProducts = Integer.valueOf(productQnt);
			if (productsMap.containsKey(product)) {
				productsMap.put(product, productsMap.get(product) + countProducts);
			} else {
				productsMap.put(product, countProducts);
			}
			session.setAttribute(CART, productsMap);
			int qnt = productsMap.size();
			session.setAttribute(CART_VALUE, qnt);
			return "" + qnt;
		}
		return "Your Order: 0";
	}
}
