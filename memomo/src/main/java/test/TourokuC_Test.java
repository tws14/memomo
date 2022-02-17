package test;

import DAO.UserDAO;
import model.user;

public class TourokuC_Test {
	
	
     public static void main(String[] args) {
    	 testTourokumethod test1 = new testTourokumethod();
    	 test1.testFindbyTouroku1();
    	 test1.testFindbyTouroku2();
     }
}

	class testTourokumethod {
		private UserDAO userdao;
		boolean result;
		//アカウントネームサーチテスト
		public void testFindbyTouroku1() {
			user theUser = new user("aaaa", "12345678");
		     userdao = new UserDAO();
		     
		    try {
		    	   result = userdao.accountNameSearch(theUser);
		    	if(result == true) {
		    		System.out.println("ユーザーネームが一致しています：成功しました");
		    	} else {
		    		System.out.println("失敗しました");
		    	}
	       }catch(Exception e) {
		    		e.printStackTrace();
		    	}
		}
		
		public void testFindbyTouroku2() {
			user theUser = new user("aaaaa", "12345678");
		     userdao = new UserDAO();
		     
		    try {
		    	   result = userdao.accountNameSearch(theUser);
		    	if(result == true) {
		    		System.out.println("失敗しました");
		    	} else {
		    		System.out.println("ユーザーネームが一致していません：成功、登録します");
		    		userdao.touroku(theUser);
		    	}
	       }catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    }
	}
		
