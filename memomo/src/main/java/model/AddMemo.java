package model;

public class AddMemo {
       public boolean addmemologic(String d) {
    	   String d1 = d.trim();
    	   String d2 = d.strip();
    	   if(d == null || d1 == "" || d2 == "") {
    		   return false;
    	   } else {
    		   return true;
    	   }
       }
}
