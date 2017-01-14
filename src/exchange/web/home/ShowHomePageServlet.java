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
import exchange.model.exchange.Exchange;
import exchange.model.exchange.ExchangeManager;
import exchange.model.skill.KindTypeManager;
import exchange.model.skill.SkillManager;
import exchange.model.skill.Type;

@WebServlet("/Home.do")
public class ShowHomePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("uid") != null) {
			RequestDispatcher view = null;
			String uid = (String) session.getAttribute("uid");

			AccountManager am = new AccountManager();
			Profile profile = null;

			profile = am.getAccount(uid).getProfile();

			boolean isSkillsFull = false;
			try {
				isSkillsFull = AccountManager.isSkillFull(uid);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			ArrayList<Type> favorites = SkillManager.getAllFavoriteSkills(uid);

			ArrayList<Exchange> skills = ExchangeManager.getAllMySkills(uid);
			request.setAttribute("isSkillsFull", isSkillsFull);
			request.setAttribute("profile", profile);
			request.setAttribute("skills", skills);
			request.setAttribute("favorites", favorites);
			request.setAttribute("kinds", KindTypeManager.getKindList());
			request.setAttribute("types", KindTypeManager.getTypeList());

			//System.out.println("[Kind List]" + KindTypeManager.getKindList());
			request.setAttribute("age", 2017 - Integer.parseInt(profile.getBirthday().split("-")[0]));

			view = request.getRequestDispatcher("HomePage.jsp");
			view.forward(request, response);
		} else
			response.sendRedirect("Index.jsp");
	}
}