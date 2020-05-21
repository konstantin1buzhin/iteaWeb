package web;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import dao.FactoryDAO;
import dao.UserDAO;

@Component
public class MySQLUserDAO implements UserDAO {

	private User user;
	private String userName;
	private boolean show;
	private String registration;
	private String access;
	private boolean checkLogin;
	private final static String INSERT = "INSERT INTO users(login, name, password, age, gender, comments, address, amigo) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SALT = "SALT";
	
	

	

	@Override
	public String getUserName() {

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
			rs = stmt.executeQuery("SELECT name FROM users WHERE login='" + user.getLogin() + "'");
			rs.next();
			userName = rs.getString("name");
		} catch (SQLException ex) {
			userName = "";
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

		return userName;
	}

	public boolean isShow() {
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
		String existLogin;
		String existPassword;

		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery("SELECT login, password FROM users WHERE login='" + user.getLogin()
					+ "' AND password='" + toHexString(hashSHA(user.getPassword())) + "'");
			rs.next();
			existLogin = rs.getString("login");
			existPassword = rs.getString("password");

		} catch (SQLException ex) {
			existLogin = "notExist";
			existPassword = "notExist";
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

		return (user.getLogin().equals(existLogin) && !(existPassword.equals("notExist")));
	}

	public static byte[] hashSHA(String strToHash) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return md.digest((strToHash + SALT).getBytes(StandardCharsets.UTF_8));

	}

	public static String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

	@Override
	public String getAccess() {

		return isShow() ? "Successfully logged" : "Incorrect username or password";
	}

	@Override
	public boolean getCheckLogin() {
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
		String existLogin;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT login FROM users WHERE login='" + user.getLogin() + "'");
			rs.next();
			existLogin = rs.getString("login");
		} catch (SQLException ex) {
			existLogin = "notExist";
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

		return (user.getLogin().equals(existLogin));
	}

	@Override
	public String getRegistration() {

		return insertData() ? "success" : "fault";
	}

	@Override
	public boolean insertData() {
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

		try {
			stmt = conn.prepareStatement(INSERT);
			// запрос в БД
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getName());
			stmt.setString(3, toHexString(hashSHA(user.getPassword())));
			stmt.setInt(4, user.getAge());
			stmt.setString(5, user.getGender());
			stmt.setString(6, user.getComments());
			stmt.setString(7, user.getAddress());
			stmt.setString(8, user.getAmigo());
			stmt.execute();

		} catch (SQLException ex) {

			ex.printStackTrace();
			return false;
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}

				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
