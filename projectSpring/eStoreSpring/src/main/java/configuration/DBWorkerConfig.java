package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.FactoryDAO;
import web.MySQLProductDAO;
import web.MySQLUserDAO;

@Configuration
public class DBWorkerConfig {
	
	@Bean
	public MySQLUserDAO mySQLUserDAO() {
		return (MySQLUserDAO) FactoryDAO.getFactoryDAO(1).getUserDAO();
	}
	
	@Bean
	public MySQLProductDAO mySQLProductDAO() {
		return (MySQLProductDAO) FactoryDAO.getFactoryDAO(1).getProductDAO();
	}

}
