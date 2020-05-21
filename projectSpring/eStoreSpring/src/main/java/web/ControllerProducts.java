package web;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import model.Product;

@Controller
public class ControllerProducts {

	@Autowired
	private MySQLProductDAO productDB;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getGetProductsPage(HttpSession session, ModelMap model) {

		List<Product> listProduct = productDB.getProducts();

		model.addAttribute("listProducts", listProduct);

		if (session.getAttribute("CART_VALUE") == null) {
			session.setAttribute("CART_VALUE", 0);
		}
		model.addAttribute("imgHeight", "200px");

		return "products";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, params = { "category" })
	public String getGetProductsPage(@RequestParam("category") String category, HttpSession session, ModelMap model) {

		List<Product> listProduct = productDB.getProducts();

		if (category != null) {
			listProduct = productDB.getProductsCategory(category);
			model.addAttribute("listProducts", listProduct);
		}
		if (session.getAttribute("CART_VALUE") == null) {
			session.setAttribute("CART_VALUE", 0);
		}
		model.addAttribute("imgHeight", "200px");

		return "products";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET, params = { "id" })
	public String getGetProductsPage(HttpSession session, @RequestParam("id") String id, ModelMap model) {

		List<Product> listProduct = productDB.getProducts();

		if (id != null) {
			int idProduct = Integer.valueOf(id);
			Product[] product = { productDB.getProduct(idProduct) };
			model.addAttribute("listProducts", product);
		}
		if (session.getAttribute("CART_VALUE") == null) {
			session.setAttribute("CART_VALUE", 0);
		}
		model.addAttribute("imgHeight", "200px");

		return "products";
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST, params = { "idProduct" })
	public String getPostProductsPage(HttpSession session, ModelMap model, @RequestParam("idProduct") String idProd) {

		if (session.getAttribute("CART_VALUE") == null) {
			session.setAttribute("CART_VALUE", 0);
		}
		if (idProd != null) {
			int idProduct = Integer.valueOf(idProd);
			model.addAttribute("imgHeight", "200px");
			Product listProduct = productDB.getProduct(idProduct);
			model.addAttribute("listProducts", listProduct);

		} else {
			List<Product> listProduct = productDB.getProducts();

			model.addAttribute("listProducts", listProduct);
			model.addAttribute("imgHeight", "200px");

		}
		return "products";
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String getPostProductsPage(HttpSession session, ModelMap model) {

		if (session.getAttribute("CART_VALUE") == null) {
			session.setAttribute("CART_VALUE", 0);
		}

		List<Product> listProduct = productDB.getProducts();

		model.addAttribute("listProducts", listProduct);
		model.addAttribute("imgHeight", "200px");

		return "products";
	}

}
