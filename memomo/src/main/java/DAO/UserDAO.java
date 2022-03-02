package DAO;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import model.user;

public class UserDAO {
		
	private static Connection getConnection() throws URISyntaxException, SQLException{
		
		URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
		
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		
		String propaties = "?characterEncoding=UTF-8&severTimezone=JST";
		
		String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath() +propaties;
		
		return DriverManager.getConnection(dbUrl, username, password);

	}
	
	//login method
	public user login(user theUser) throws Exception {
		
		user theAccount = null;
		
		//jdbcドライバ接続		
		Connection Conn = null;
		PreparedStatement Stmt = null;
		ResultSet rs = null;
	
	try{	
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Conn = getConnection();
		
		//sql文の準備
		String sql = "SELECT id, username, password FROM user WHERE username = ? AND password = ?";
		
		Stmt = Conn.prepareStatement(sql);
		
		Stmt.setString(1, theUser.getUsername());
		Stmt.setString(2, theUser.getPassword());
		
		//実行
		rs = Stmt.executeQuery();
		
		//ユーザーが一致した場合
		//そのユーザーを表すuserを生成
		if(rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			
	      theAccount = new user(id, username, password);
		} else {
			return null;
		}
		return theAccount;
			
		} finally {
			close(Conn, Stmt, rs);
			}
		}
	
	
	
	//accountnamesearch method
	 public boolean accountNameSearch(user theUser) throws Exception {
		 
			
			//jdbcドライバ接続		
			Connection Conn = null;
			PreparedStatement Stmt = null;
			ResultSet rs = null;
		
		try{	
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Conn = getConnection();
			
			//sql文の準備
			String sql = "SELECT username FROM user WHERE username = ?";
			
			Stmt = Conn.prepareStatement(sql);
			
			Stmt.setString(1, theUser.getUsername());
			
			//実行
			rs = Stmt.executeQuery();
			
			//ユーザーが一致した場合
			//trueを戻す
			if(rs.next()) {
				return true;
			} 
				return false;
				
			} finally {
				close(Conn, Stmt, rs);
				}
			}
      
	
	//touroku method
	 public void touroku(user theUser)  throws Exception {
		 
		 
		 //jdbcドライバ接続		
		 Connection Conn = null;
		 PreparedStatement Stmt = null;
		 
	try{				
		 Class.forName("com.mysql.cj.jdbc.Driver");
				
		 Conn = getConnection();
				
		 //sql文の準備
		 String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
		 		
	 	 Stmt = Conn.prepareStatement(sql);
				
		 Stmt.setString(1, theUser.getUsername());
		 Stmt.setString(2, theUser.getPassword());
				
	 	 //実行
        Stmt.execute();
		  	 
	   } finally {
		   close(Conn, Stmt, null);
	   }
   }
	
	 //delete method
    public void delete(user theUser)  throws Exception {
		 
		 
		    //jdbcドライバ接続		
		    Connection Conn = null;
		    PreparedStatement Stmt = null;
		 
	try{				
		    Class.forName("com.mysql.cj.jdbc.Driver");
		  		
		    Conn = getConnection();
				
		    //sql文の準備
	    	 String sql = "DELETE FROM user WHERE username = ?";
		 		
	     	 Stmt = Conn.prepareStatement(sql);
				
	    	 Stmt.setString(1, theUser.getUsername());
				
	 	    //実行
           Stmt.execute();
		  	 
	   } finally {
		   close(Conn, Stmt, null);
	   }
   }
	
	
	//クローズ処理
	private void close(Connection Conn, PreparedStatement Stmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(Stmt != null) {
				Stmt.close();
			}
			if(Conn != null) {
				Conn.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
		

