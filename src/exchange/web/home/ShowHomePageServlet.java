package exchange.web.home;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.account.AccountManager;
import exchange.model.account.Profile;
import exchange.model.skill.SkillManager;

@WebServlet("/Home.do")
public class ShowHomePageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			RequestDispatcher view = null;
			
			String uid = (String)session.getAttribute("uid");
			
			
			AccountManager am = new AccountManager();
			Profile profile = am.getAccount(uid).getProfile();
			
			SkillManager sm = new SkillManager();
			//ArrayList<Skill> skills = sm.getAllSkills(uid);
			
			request.setAttribute("profile", profile);
			//request.setAttribute("skills", skills);
			
			
			view = request.getRequestDispatcher("/HomePage.jsp");
			view.forward(request, response);
		}
		else response.sendRedirect("index.html");
	}
}