package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dao.ProductDAO;
import model.Product;

@Component
public class MySQLProductDAO implements ProductDAO{

	private final static String SELECTALL = "SELECT * FROM products";
	private final static String IDFORM = "SELECT * FROM products WHERE id=?";
	private final static String GET_CATEGORY = "SELECT * FROM products WHERE category=?";
	
	@Override
	public Product getProduct(int id) {
		Product product = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteaweb?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.prepareStatement(IDFORM);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return product;
	}

	@Override
	public List<Product> getProducts() {
		List<Product> productList = new ArrayList<Product>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteaweb?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		Statement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECTALL);
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				productList.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return productList;
	}

	@Override
	public List<Product> getProductsCategory(String category) {
		List<Product> productList = new ArrayList<Product>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteaweb?" + "user=root&password=");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			stmt = conn.prepareStatement(GET_CATEGORY);
			stmt.setString(1, category);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setCategory(rs.getString("category"));
				productList.add(product);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return productList;
	}

}
