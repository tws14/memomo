package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Memo;
import model.user;

import java.io.IOException;
import java.util.List;

import DAO.MemoDAO;
import DAO.UserDAO;

@WebServlet("/MemoController")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	    private MemoDAO memodao;
     
		//初期化
		public void init() throws ServletException {
			super.init();
			
			try {
				MemoDAO memoDAO = new MemoDAO();
				memodao = memoDAO;
		   } catch(Exception e ) {
			   throw new ServletException(e);
		    }
		}
	    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	try {	
		//memocommandパラメータ
		String theCommand = request.getParameter("MemoCommand");
		
		//null default の場合
		if(theCommand == null) {
			theCommand = "LIST";
			
		}
		//switch
		switch(theCommand) {
				case "LIST":
					listMemos(request, response);
					break;
			}
		}  catch (Exception e) {
				e.printStackTrace();
	     }
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {
			listMemos(request, response);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
	}
	

	private void listMemos(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
				HttpSession session = request.getSession();
		
				user tempUser = (user) session.getAttribute("loginUser");
		
				//リスト
				List<Memo> tempMemos = memodao.getMemos(tempUser);
				
				session.setAttribute("MemoList", tempMemos);
				
				RequestDispatcher d =
						request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
				d.forward(request, response);
		
	}

}
