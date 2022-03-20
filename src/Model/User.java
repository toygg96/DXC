package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.DBConnection;

// User Class
public class User {

	// Variables for this class
	private String username = null;
	private String userRole = null;
	private String realName = null;
	private String password = "";

	// Default Constructor
	public User(String username, final String password) {
		this.username = username;
		this.password = password;
	}

	// Constructor for session user variable
	public User(String username, final String userRole, String realName) {
		this.username = username;
		this.userRole = userRole;
		this.realName = realName;
	}

	// Query the database to fetch the user profile which matches the user credentials and return the user instance
	public User isAuthenticated() {
		Connection conn = null;
		
		try {
			
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM USERS WHERE username=? AND passwordHash=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, username);
			pstmt.setString(2, hashPass(password));
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
			    setUserRole(rs.getString("userRole"));
				setRealName(rs.getString("realName"));
				setPassword("");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		
		return this;
	}

	// Takes in the password and hashed it with SHA-256 algo and returns the hashed
	// password
	public String hashPass(String password) {
		
		String passwordHash = null;
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] bytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			passwordHash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}