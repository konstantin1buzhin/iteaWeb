package homework05;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckerSignIn {
	
	  private String login;
	  private String password;
	  private String existLogin = "";
      private String existPassword = "";
      private String access;
      private boolean show;
      
      private static final String SALT = "SALT";
      

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CheckerSignIn() {
	}

	public CheckerSignIn(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getExistLogin() {
		return existLogin;
	}

	public void setExistLogin(String existLogin) {
		this.existLogin = existLogin;
	}

	public String getExistPassword() {
		return existPassword;
	}

	public void setExistPassword(String existPassword) {
		this.existPassword = existPassword;
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
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT login, password FROM users WHERE login='"+login+"' AND password='"+password+"'");
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
       
        

        return (login.equals(existLogin)&&password.equals(existPassword));
    }

	 public String getAccess() {
		 return isShow() ? "Successfully logged" : "Incorrect username or password";
	    }
	 

	 public static String hashMD5(String strToHash) {
	    	try {
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update((SALT+strToHash).getBytes());
				System.out.println(String.format("%032x", new BigInteger(messageDigest.digest())));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	    	
	    	return "";
	    	
	    }
    

	 public static void main(String[] args) {
		 try {
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.update(("admin").getBytes());
				System.out.println(String.format("%064x", new BigInteger(1, messageDigest.digest())));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	    	
	    	
	    }
	}

