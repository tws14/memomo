package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.MemoDAO;
import DAO.UserDAO;


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
	}





	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	

	private void listMemos(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

}
