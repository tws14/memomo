package model;

public class Touroku {
		  public boolean execute(user theUser) {
			  
			  String name = theUser.getUsername();
			  String pass = theUser.getPassword();
			  int numN = name.length();
			  int numP = pass.length();
			  
			  //nullのとき
			  if(name.isEmpty() || pass.isEmpty()) {
				  return false;
			 
			//空白を含むとき
		  } else if(name.contains(" ") || pass.contains(" ")) {
			       return false;
			       
			//４文字から１６文字の間
		  } else if((3 >= numN || numN >= 17) || (3 >= numP || numP >= 17)) {
			       return false;
		  } else {return true;}
		}
}
