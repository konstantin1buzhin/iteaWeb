package dao;

import java.util.List;

import model.Product;

public interface ProductDAO {

	public Product getProduct(int id);
	
	public List<Product> getProducts();
	
	public List<Product> getProductsCategory(String category);
}
