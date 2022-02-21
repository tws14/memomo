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

@WebServlet("/UserConfig")
public class UserConfig extends HttpServlet {
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
	
		RequestDispatcher d =
				request.getRequestDispatcher("/WEB-INF/jsp/configcheck.jsp");
		d.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
       try {	
		  //パラメーターリクエスト取得
	     String theCommand = request.getParameter("command");
		  
	     //null
	     if(theCommand == null) {
	    	 theCommand = "BACK";	    	 
	      }
		 
	     //switch
	     switch (theCommand) {
	     	     case "BACK":
	     	    	    backmain(request,response);
	            case "CHECK":
	            	    check(request,response);
	            	    break;
	            case "DELETE":
	            		delete(request,response);
	            default:
						check(request,response);
	     }
       } catch(Exception e) {
    	   e.printStackTrace();
       }
	}
		
	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		HttpSession session = request.getSession();
		
		user theAccount = (user) session.getAttribute("loginUser");
		
		userdao.delete(theAccount);
		
		session.invalidate();
		
		RequestDispatcher d =
				request.getRequestDispatcher("/WEB-INF/jsp/deleteend.jsp");
		d.forward(request, response);
	}
	
	
	private void check(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		//リクエストパラメータ取得
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean result1;
		boolean result2;
		
		//セッション
		HttpSession session = request.getSession();
		
		user loginUser = (user)session.getAttribute("loginUser");
		
		result1 = (loginUser.getUsername().equals(username));
		result2 = (loginUser.getPassword().equals(password));
		
	
	if(result1 || result2) {
			goconfigpage(request, response);
		} else {
		   back(request, response);
		}
	}

	private void goconfigpage(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		RequestDispatcher d =
				request.getRequestDispatcher("/WEB-INF/jsp/userconfig.jsp");
		d.forward(request, response);
		
	}
	private void back(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		HttpSession session = request.getSession();
		
		//set status
		session.setAttribute("status", "not");
		
		//back request page
		RequestDispatcher d =
				request.getRequestDispatcher("/WEB-INF/jsp/configcheck.jsp");
		d.forward(request, response);
		
	}
	
	private void backmain(HttpServletRequest request, HttpServletResponse response) 
	        throws Exception{
		
		RequestDispatcher d =
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		d.forward(request, response);
		
	}

}