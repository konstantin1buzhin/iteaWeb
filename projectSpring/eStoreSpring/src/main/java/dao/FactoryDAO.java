package dao;

import mySQL.MySQLFactoryDAO;

public abstract class FactoryDAO {

	public abstract UserDAO getUserDAO();

	public abstract ProductDAO getProductDAO();
	
	public static FactoryDAO getFactoryDAO(int factory) {
		switch (factory) {
		case 1:
			return new MySQLFactoryDAO();

		default:
			break;
		}
		return null;
	}
}
