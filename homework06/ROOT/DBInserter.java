package homework06;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DBInserter {
    private final static String INSERT = "INSERT INTO users(login, name, password, age, gender, comments, address, amigo) VALUES (?,?,?,?,?,?,?,?)";

    
    private String login;
    private String name;
    private String password;
    private int age;
    private String gender;
    private String address;
    private String comments;
    private String amigo;
    private String registration;
    private boolean checkLogin;
    private String message;
    
    private static final String SALT = "SALT";
    
    public DBInserter() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAmigo() {
		return amigo;
	}

	public void setAmigo(String amigo) {
		this.amigo = amigo;
	}


	private static String existLogin = "";
    
    public boolean insert() {
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
            //запрос в БД
            stmt.setString(1, login);
            stmt.setString(2, name);
            stmt.setString(3, hashSHA(password));
            stmt.setInt(4, age);
            stmt.setString(5, gender);
            stmt.setString(6, comments);
            stmt.setString(7, address);
            stmt.setString(8, amigo);
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
            
            if(conn != null) {
            	try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
        return true;
    }
    
    public String getRegistration() {
    	
    	return insert()?"success":"fault";
    }
    
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
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT login FROM users WHERE login='"+login+"'");
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
       
        

        return (login.equals(existLogin));
    }

    
    public static String hashSHA(String strToHash) {
    	MessageDigest messageDigest = null;
    	try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update((strToHash+SALT).getBytes());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
    	
    	return String.format("%064x", new BigInteger(messageDigest.digest()));
    	
    }
    

    
}
