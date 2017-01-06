package exchange.web.home;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.account.AccountManager;
import exchange.model.account.Profile;
import exchange.model.exchange.ExchangeManager;
import exchange.model.skill.SkillManager;
import exchange.model.skill.Type;

@WebServlet("/Home.do")
public class ShowHomePageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			RequestDispatcher view = null;
			
			String uid = (String)session.getAttribute("uid");
			
			AccountManager am = new AccountManager();
			Profile profile = null;
			try {
				profile = am.getAccount(uid).getProfile();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<Type> favorites = null;
			try {
				favorites = SkillManager.getAllFavoriteSkills(uid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ArrayList<MySkill> skills = ExchangeManager.getMySkillStatus(uid);
			
			request.setAttribute("profile", profile);
			request.setAttribute("skills", skills);
			request.setAttribute("favorites", favorites);
			
			view = request.getRequestDispatcher("/HomePage.jsp");
			view.forward(request, response);
		}
		else response.sendRedirect("index.html");
	}
}