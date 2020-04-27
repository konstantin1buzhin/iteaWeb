package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DBWorker;
import model.Product;

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBWorker worker = new DBWorker();
		List<Product> listProduct = worker.getProducts();
		if (request.getParameter("category") != null) {
			listProduct = worker.getProductsCategory(request.getParameter("category"));
		} else if (request.getParameter("id") != null) {
			listProduct = worker.getProduct(Integer.valueOf(request.getParameter("id")));
		}
		request.setAttribute("imgHeight", "200px");
		request.setAttribute("listProducts", listProduct);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/products.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("idProduct") != null) {
			int idProduct = Integer.valueOf(request.getParameter("idProduct"));
			request.setAttribute("imgHeight", "300px");
			DBWorker worker = new DBWorker();
			List<Product> listProduct = worker.getProduct(idProduct);
			request.setAttribute("listProducts", listProduct);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/products.jsp");
			rd.forward(request, response);
		} else {
			doGet(request, response);
		}
		
	}

}
