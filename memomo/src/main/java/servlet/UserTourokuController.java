package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Touroku;
import model.user;

import java.io.IOException;

import DAO.UserDAO;

@WebServlet("/UserTourokuController")
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
				String repass = request.getParameter("repass");
			   //入れる用インスタンス
				user theUser = new user(username, password);
				boolean result0;
				boolean result1;
				boolean result2;
				
				Touroku tourokulogic = new Touroku();
				result0 = tourokulogic.execute(theUser);
				result2 = (repass.equals(password));
	
	try {
		if(result0 == true) {
		  
		      
				result1 = userdao.accountNameSearch(theUser);
				
				if(result1 == true) {
					retouroku(request, response);
				} else if(result2 == false){
					repassword(request, response);
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
				
				request.setAttribute("status", "error");
				

				RequestDispatcher d =
						request.getRequestDispatcher("/touroku.jsp");
				d.forward(request, response);
	}

	private void retouroku(HttpServletRequest request, HttpServletResponse response)
	    throws Exception	{
		
	         	request.setAttribute("status", "tourokuzumi");
		

				RequestDispatcher d =
						request.getRequestDispatcher("/touroku.jsp");
				d.forward(request, response);
	}
	
	private void repassword(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		 		
				request.setAttribute("status", "different");
				
				RequestDispatcher d =
						request.getRequestDispatcher("/touroku.jsp");
				d.forward(request, response);
		
	}


}
