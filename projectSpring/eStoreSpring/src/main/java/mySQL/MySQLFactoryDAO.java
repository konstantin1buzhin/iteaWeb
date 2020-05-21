package mySQL;

import dao.FactoryDAO;
import dao.ProductDAO;
import dao.UserDAO;
import web.MySQLProductDAO;
import web.MySQLUserDAO;

public class MySQLFactoryDAO extends FactoryDAO{

	@Override
	public UserDAO getUserDAO() {
		return new MySQLUserDAO();
	}

	@Override
	public ProductDAO getProductDAO() {
		return new MySQLProductDAO();
	}

}
