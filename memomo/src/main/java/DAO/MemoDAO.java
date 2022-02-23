package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Memo;
import model.user;

public class MemoDAO {

	String databasename = "memo_mo";
	String propaties = "?characterEncoding=UTF-8&severTimezone=JST";
	String URL = "jdbc:mysql://localhost:3306/" + databasename + propaties;
	
	String USER = "ta*****";
	String PASS = "*********";
	
	public List<Memo> getMemos(user theUser) throws Exception {
	       
			List<Memo> Memos = new ArrayList<>();
	
			//jdbcドライバ接続		
			Connection Conn = null;
			PreparedStatement Stmt = null;
			ResultSet rs = null;
		
	   try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Conn = DriverManager.getConnection(URL , USER , PASS);
			
			//sql文の準備
			String sql = "SELECT userid, daimei, honbun, memoid FROM memo WHERE userid = ?";
			
			Stmt = Conn.prepareStatement(sql);
			
			Stmt.setInt(1, theUser.getId());
			
			//実行
			rs = Stmt.executeQuery();
			
			//ユーザーが一致した場合
			//Memoを取得
			while(rs.next()) {
				int userid = rs.getInt("userid");
				String daimei = rs.getString("daimei");
				String honbun = rs.getString("honbun");
				int memoid = rs.getInt("memoid");
				
				Memo tempmemo = new Memo(userid, daimei, honbun, memoid);
				
				Memos.add(tempmemo);
			} 
					return Memos;
	    }  finally {
			close(Conn, Stmt, rs);
			}
	}
	
	public void add(Memo theMemo) throws Exception{
		 
		 //jdbcドライバ接続		
		 Connection Conn = null;
		 PreparedStatement Stmt = null;
		 
	try{				
		 Class.forName("com.mysql.cj.jdbc.Driver");
				
		 Conn = DriverManager.getConnection(URL , USER , PASS);
				
		 //sql文の準備
		 String sql = "INSERT INTO memo(userid, daimei, honbun) VALUES(?, ?, ?)";
		 		
	 	 Stmt = Conn.prepareStatement(sql);
				
		 Stmt.setInt(1, theMemo.getUserid());
		 Stmt.setString(2, theMemo.getDaimei());
		 Stmt.setString(3, theMemo.getHonbun());		
	 	 //実行
       Stmt.execute();
		  	 
	   } finally {
		   close(Conn, Stmt, null);
	   }
	}
	
	public void update(Memo theMemo) throws Exception {
		
		   //JDBCドライバ接続
		   Connection Conn = null;
	     	PreparedStatement Stmt = null;
	  
	  try {
		  
		  Class.forName("com.mysql.cj.jdbc.Driver");
			
		  Conn = DriverManager.getConnection(URL , USER , PASS);
		  
			 //sql文の準備
			 String sql = "UPDATE memo SET daimei=?, honbun=? WHERE memoid=?;";
			 		
		 	 Stmt = Conn.prepareStatement(sql);
					
			 Stmt.setString(1, theMemo.getDaimei());
			 Stmt.setString(2, theMemo.getHonbun());		
			 Stmt.setInt(3, theMemo.getMemoid());
		 	 //実行
	       Stmt.execute();
			  	 
	  }finally {
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
