package test;

import java.util.List;

import DAO.MemoDAO;
import model.Memo;
import model.user;

public class MemoListTest {
      public static void main(String[] args) {
    	  memolisttest Mlt = new memolisttest();
    	  Mlt.listtest();
      }
} 
      class memolisttest{
    	  private MemoDAO memodao;
    	  
    	  
    	public void listtest() {  
    	  user theUser = new user(1,"aaaa", "12345678");
    	  memodao = new MemoDAO();
     try { 	  
    	  
    	  List<Memo> result = memodao.getMemos(theUser);
    	  
    	  if(result != null) {
    		  for(Memo mm : result) {
    			  System.out.println(mm.getUserid());
    			  System.out.println(mm.getDaimei());
    			  System.out.println(mm.getHonbun());
    		  }
    	  } else if(result == null) {
    		  System.out.println("失敗しました : メモリストがnullです");
    	  }
      } catch (Exception e) {
    	  e.printStackTrace();
      }
     }
   }

