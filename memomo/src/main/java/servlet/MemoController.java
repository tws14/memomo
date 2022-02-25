package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AddMemo;
import model.Memo;
import model.user;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
				case "MEMO":
					writeMemo(request,response);
					break;
				case "ADD":
					addMemo(request,response);
					break;
				case "LOAD":
					loadMemo(request,response);
					break;
				case "UPDATE":
					updateMemo(request,response);
					break;
				case "DELETE":
					deleteMemo(request,response);
					break;
				case "SEARCH":
					searchMemo(request,response);
			}
		}  catch (Exception e) {
				e.printStackTrace();
	     }
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {
			
			//パラメータ取得
			String theCommand2 = (String) request.getAttribute("MainCommand");
			
			if(theCommand2 == null) {
				theCommand2 = "LIST2";
			}
			
			switch(theCommand2) {
						case "LIST2":
							   listMemos(request, response);
							   break;
						case "ALLDELETE":
							    alldeleteMemos(request, response);
							   break;
			           }
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
	}
	



	private void listMemos(HttpServletRequest request, HttpServletResponse response)
			throws Exception  {
		
				HttpSession session = request.getSession();
		
				user tempUser = (user) session.getAttribute("loginUser");
		
				//リスト
				List<Memo> tempMemos = memodao.getMemos(tempUser);
				
				session.setAttribute("MemoList", tempMemos);
				
				RequestDispatcher d =
						request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
				d.forward(request, response);
		
	}
	

	private void alldeleteMemos(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
					HttpSession session = request.getSession();
		
					user tempUser = (user) session.getAttribute("loginUser");
		
					int userid = tempUser.getId();
					
					memodao.alldelete(userid);
					
					session.invalidate();
					
					RequestDispatcher d =
							request.getRequestDispatcher("/WEB-INF/jsp/deleteend.jsp");
					d.forward(request, response);
		
		
	}


	private void writeMemo(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
				RequestDispatcher d =
						request.getRequestDispatcher("/WEB-INF/jsp/memoform.jsp");
				d.forward(request, response);
		
	}
	
	private void addMemo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		       //リクエストパラメータ取得
			   request.setCharacterEncoding("UTF-8");
		      String daimei = request.getParameter("daimei");
		      String honbun = request.getParameter("honbun");
		      
		      AddMemo am = new AddMemo();
		      boolean result = am.addmemologic(daimei);
		      
		  if(result == true) {    
		      //セッションからid取得
		      HttpSession session = request.getSession();
		      user loginUser = (user)session.getAttribute("loginUser");
		      
		      int id = loginUser.getId();
		      
		      Memo theMemo = new Memo(id,daimei,honbun);
		      
		      memodao.add(theMemo);
		      
		      listMemos(request, response);
		      
		  } else {
			  
			  request.setAttribute("Dstatus", "not daimei");
			  
			  RequestDispatcher d =
						request.getRequestDispatcher("/WEB-INF/jsp/memoform.jsp");
				d.forward(request, response);
		  }
		      
	}



	private void loadMemo(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		//memoidパラメータ取得
    	request.setCharacterEncoding("UTF-8");
		int uI = Integer.parseInt(request.getParameter("userId"));
		String Dm = request.getParameter("Daimei");
		String Hb = request.getParameter("Honbun");
		int mI = Integer.parseInt(request.getParameter("memoId"));
		

		//リクエストセット
		request.setAttribute("userid", uI);
		request.setAttribute("daimei", Dm);
		request.setAttribute("honbun", Hb);
		request.setAttribute("memoid", mI);
		
		RequestDispatcher d =
				request.getRequestDispatcher("/WEB-INF/jsp/update-memoform.jsp");
		d.forward(request, response);
		
	
	}



	private void updateMemo(HttpServletRequest request, HttpServletResponse response)
		   throws Exception{
		//リクエストパラメータ取得
		request.setCharacterEncoding("UTF-8");
		String daimei = request.getParameter("daimei");
		String honbun = request.getParameter("honbun");
		int memoid = Integer.parseInt(request.getParameter("memoid"));
		
		//daimeiをチェック
	      AddMemo am = new AddMemo();
	      boolean result = am.addmemologic(daimei);
	     
	      if(result == true){
	    	  
	    	  Memo theMemo = new Memo(daimei, honbun, memoid);
	    	  
	    	  memodao.update(theMemo);
	    	  
	    	  listMemos(request,response);
	    	  
	      }else {
			  
			  request.setAttribute("Dstatus", "not daimei");
				request.setAttribute("daimei", daimei);
				request.setAttribute("honbun", honbun);
				request.setAttribute("memoid", memoid);
			  
			  
			  RequestDispatcher d =
						request.getRequestDispatcher("/WEB-INF/jsp/update-memoform.jsp");
				d.forward(request, response);
		  }
	}


	private void deleteMemo(HttpServletRequest request, HttpServletResponse response) 
	    throws Exception{
		
		//memoidパラメータ取得
    	request.setCharacterEncoding("UTF-8");
		int mI = Integer.parseInt(request.getParameter("memoId"));
		
		memodao.delete(mI);
		
		listMemos(request,response);
	}
	


	private void searchMemo(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		//パラメータ取得
		String word = request.getParameter("word");
		
		AddMemo ad = new AddMemo();
		boolean wordtest = ad.addmemologic(word);
		
	if(wordtest == true) {
		//セッション取得
		HttpSession session = request.getSession();
		
		List<Memo> tempMemos = (List<Memo>) session.getAttribute("MemoList");
		
		//リストの中からwordを含むものを再リスト化
		List<Memo> SearchedMemos = tempMemos.stream()
					.filter(m -> m.getDaimei().contains(word) || m.getHonbun().contains(word))
					.collect(Collectors.toList());

		//リクエストセット
		request.setAttribute("SearchedMemos", SearchedMemos);
		
		RequestDispatcher d =
				request.getRequestDispatcher("/WEB-INF/jsp/searched-memolist.jsp");
		d.forward(request, response); 
		return;
		
	} else {
		
		request.setAttribute("SearchedMemos", null);
		
		RequestDispatcher d =
				request.getRequestDispatcher("/WEB-INF/jsp/searched-memolist.jsp");
		d.forward(request, response); 
		
	}
	
		
	}


}
