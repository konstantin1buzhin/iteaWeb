package homework06;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckerSignIn {
	
	  private String login = "admin@mail.ru";
	  private String password = "adminJJ77";
	  private String existLogin = "";
      private String existPassword = "";
      private String access;
      private boolean show;
      private String name;
      private String currentpassword;
      
      private static final String SALT = "SALT";
      
      
      

	public String getName() {
		
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
            rs = stmt.executeQuery("SELECT name FROM users WHERE login='"+login+"'");
            rs.next();
            name = rs.getString("name");
        } catch (SQLException ex) {
        	name = "";
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
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
            
            rs = stmt.executeQuery("SELECT login, password FROM users WHERE login='"+login+"' AND password='"+hashSHA(password)+"'");
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
       
        

        return (login.equals(existLogin)&&!password.equals("notExist"));
    }

	 public String getAccess() {
		 return isShow() ? "Successfully logged" : "Incorrect username or password";
	    }
	 

	 public static String hashSHA(String strToHash) {
	    	MessageDigest messageDigest = null;
	    	try {
				messageDigest = MessageDigest.getInstance("SHA-256");
				messageDigest.reset();
				messageDigest.update((strToHash+SALT).getBytes());
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
	    	
	    	return String.format("%064x", new BigInteger(messageDigest.digest()));
	    	
	    }

	}

