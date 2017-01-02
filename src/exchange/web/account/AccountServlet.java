package exchange.web.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.account.AccountManager;

@WebServlet("/Account.do")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//only for modification
		HttpSession session = request.getSession();
		
		if(session.isNew()){
			String id = (String)request.getParameter("id");
			String pwd = (String)request.getParameter("pwd");
			String user = (String)request.getParameter("user");
			String nick = (String)request.getParameter("nick");
			String email = (String)request.getParameter("email");
			String birth = (String)request.getParameter("birth");
			String region = (String)request.getParameter("region");
			
			//Profile profile = new Profile(user, nick, email, birth, region);
			
			AccountManager sm = new AccountManager();
			/*boolean checkResult = sm.checkPassword(secret);
			
			if(checkResult){
				//session
				session.setAttribute("uid", secret.getId());
				session.setMaxInactiveInterval(1800);
			}
			else{
				session.invalidate();
				response.sendRedirect("index.html");
			}
			*/
		}
		
		response.sendRedirect("/Home.do");
	}
}