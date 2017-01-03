package exchange.web.sign;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.account.Secret;
import exchange.model.sign.SignManager;

@WebServlet("/Login.do")
public class LogInServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.isNew()){
			String id = (String)request.getParameter("id");
			String pwd = (String)request.getParameter("pwd");
			Secret secret = new Secret(id, pwd);
			
			SignManager sm = new SignManager();
			boolean checkResult = sm.checkPassword(secret);
			
			if(checkResult){
				//session
				session.setAttribute("uid", secret.getId());
				session.setMaxInactiveInterval(1800);
			}
			else{
				session.invalidate();
				response.sendRedirect("index.html");
				return;
			}
		}
		
		response.sendRedirect("/Home.do");
	}
}