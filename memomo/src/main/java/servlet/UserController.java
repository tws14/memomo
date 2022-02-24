package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Login;
import model.user;

import java.io.IOException;

import DAO.UserDAO;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
		private UserDAO userdao;
	   private user theAccount;
	//初期化
	public void init() throws ServletException {
		super.init();
		
		try {
			UserDAO Userdao = new UserDAO();
			userdao = Userdao;
	   } catch(Exception e ) {
		   throw new ServletException(e);
	    }
	}
	//ログアウト機能
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションスコープ破棄
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("/memomo/login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータ取得
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
	   //入れる用インスタンス
		user theUser = new user(username, password);
		boolean result;
		
	try {
		theAccount = userdao.login(theUser);
		//Loginインスタンス生成
		Login loginlogic = new Login();
		 result = loginlogic.execute(theAccount);
	
	if(result == true) {
			gomainpage(request, response);
		} else {
		   relogin(request, response);
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	private void gomainpage(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		//セッションインスタンス取得
		HttpSession session = request.getSession();
		
		session.setAttribute("loginUser", theAccount);
		
		RequestDispatcher d =
				request.getRequestDispatcher("/MemoController");
		d.forward(request, response);
		
	}
	private void relogin(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
	    request.setAttribute("status", "no user");
		
		RequestDispatcher d =
				request.getRequestDispatcher("/login.jsp");
		d.forward(request, response);
	}
}
