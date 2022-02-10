package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import model.user;

public class UserDAO {
		
	private DataSource datasource;
	
	
	private UserDAO(DataSource theDataSource) {
		datasource = theDataSource;
	}
	//login処理
	public user login(user theUser) throws Exception {
		
		user theAccount = null;
		
		//jdbcドライバ接続		
		Connection Conn = null;
		PreparedStatement Stmt = null;
		ResultSet rs = null;
	
	try{	
		Conn = datasource.getConnection();
		
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
			throw new Exception("ユーザーが見つかりません");
		}
		return theAccount;
			
		} finally {
			close(Conn, Stmt, rs);
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
		

