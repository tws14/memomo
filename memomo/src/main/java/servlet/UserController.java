package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.UserDAO;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	//初期化
	public void init() throws ServletException {
		super.init();
		
		try {
			UserDAO userdao = new UserDAO();
	   } catch(Exception e ) {
		   throw new ServletException(e);
	    }
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//command パラメータ読み込み
		String theCommand = request.getParameter("command");
		//nullの場合
		if(theCommand == null) {
			theCommand = "RE";
		}
		//メソッド分岐
		switch(theCommand) {
			case "RE":
				relogin(request,response);
				break;
		}

}
	private void relogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
