package test;

import javax.sql.DataSource;

import DAO.UserDAO;
import model.user;

public class LoginC_Test {
	
	
	public static void main(String[] args) {
		testLoginmethod test1 = new testLoginmethod();
		test1.testFindByLogin1();
		test1.testFingByLogin2();
	}
}

   class testLoginmethod{
	   private UserDAO userdao; 
	   
		
	   
	public  void testFindByLogin1() {
		user theUser = new user("aaaa", "12345678");
		     userdao = new UserDAO();

		try {
			user result = userdao.login(theUser);
			
			if(result != null &&
					result.getId() == 1 &&
					result.getUsername().equals("aaaa") &&
					result.getPassword().equals("12345678") ) {
				System.out.println("成功しました");
			}else {
				System.out.println("失敗しました");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public  void testFingByLogin2() {
		user theUser = new user("aaaaa", "123567");
		    userdao = new UserDAO();

			try {
			user	result = userdao.login(theUser);
			
			if(result == null) {
				System.out.println("成功しました");
			}else {
				System.out.println("失敗しました");
				}
	      } catch (Exception e) {
				e.printStackTrace();
			}
			
	}
}
