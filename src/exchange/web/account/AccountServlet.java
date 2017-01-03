package exchange.web.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.account.AccountManager;
import exchange.model.account.Profile;
import exchange.model.account.Secret;

@WebServlet("/Account.do")
public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int SECRET_MODIFICATION = 0;
	private static final int PROFILE_MODIFICATION = 1;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
		
			AccountManager am = new AccountManager();
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			
			switch(mark){
			case SECRET_MODIFICATION:
				String id = (String)session.getAttribute("uid");
				String pwd = (String)request.getParameter("pwd");
				
				Secret secret = new Secret(id, pwd);
				
				am.setSecret(secret);
				
				response.sendRedirect("/Home.do");
				break;
			case PROFILE_MODIFICATION:
				String nick = (String)request.getParameter("nick");
				String email = (String)request.getParameter("email");
				String region = (String)request.getParameter("region");
				
				Profile profile = new Profile(nick, email, region);
				
				am.setProfile(profile);
				
				response.sendRedirect("/Home.do");
				break;
			}
		}
		else response.sendRedirect("index.html");
	}
}