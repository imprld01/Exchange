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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session != null){
		
			AccountManager am = new AccountManager();
			String id = (String)session.getAttribute("uid");
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			System.out.println("[mark]:"+mark);
			switch(mark){
			case SECRET_MODIFICATION:
				System.out.println("SECRET_MODIFICATION");
				String oldPwd = (String)request.getParameter("old_pwd");
				String pwd = (String)request.getParameter("pwd");
				String rePwd = (String)request.getParameter("re_pwd");
				Secret secret = new Secret(id, oldPwd);
				System.out.println("[oldPwd]:"+oldPwd);
				System.out.println("[pwd]:"+pwd);
				System.out.println("[rePwd]:"+rePwd);
				switch(am.changeSecret(secret,pwd,rePwd))
				{
					case 0:
						System.out.println("NOTMATCH");
						response.sendRedirect("ChangePwd.jsp#popup_notmatch");
						break;
					case 1:
						System.out.println("popupNOTSAME");
						response.sendRedirect("ChangePwd.jsp#popup_notsame");
						break;
					case 2:
						System.out.println("SUCCESSCHANGE");
						response.sendRedirect("Home.do#popup_success_change");
						break;
				}
				break;
			case PROFILE_MODIFICATION:
				String nick = (String)request.getParameter("nick");
				String email = (String)request.getParameter("email");
				String region = (String)request.getParameter("region");
				
				Profile profile = new Profile(nick, email, region);
				
				am.setProfile(id, profile);
				
				response.sendRedirect("Home.do#popup_succesEdit");
				break;
			}
		}
		else response.sendRedirect("Index.jsp");
	}
}