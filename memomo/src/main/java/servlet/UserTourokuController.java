package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Touroku;
import model.user;

import java.io.IOException;

import DAO.UserDAO;


public class UserTourokuController extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		//リクエストパラメータ取得
				request.setCharacterEncoding("UTF-8");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
			   //入れる用インスタンス
				user theUser = new user(username, password);
				boolean result;
				boolean result0;
				
				Touroku tourokulogic = new Touroku();
				result0 = tourokulogic.execute(theUser);
	
	try {
		if(result0 == true) {
		  
		      
				result = userdao.accountNameSearch(theUser);
				
				if(result == true) {
					retouroku(request, response);
				} else {
					userdao.touroku(theUser);
					
					//forward
					RequestDispatcher d =
							request.getRequestDispatcher("/WEB-INF/jsp/tourokuend.jsp");
					d.forward(request, response);
					
				}
		
		} else {
				retouroku0(request, response);
	         	}
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}
	

	private void retouroku0(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
				
				HttpSession session = request.getSession();
				
				session.setAttribute("status", "error");
				
				response.sendRedirect("/memomo/touroku.jsp");
		
	}

	private void retouroku(HttpServletRequest request, HttpServletResponse response)
	    throws Exception	{
		
	         	HttpSession session = request.getSession();
		
		       session.setAttribute("status", "tourokuzumi");
		
		       response.sendRedirect("/memomo/touroku.jsp");
	}


}
