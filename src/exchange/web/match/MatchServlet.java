package exchange.web.match;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.batch.Main;

import exchange.model.account.AccountManager;
import exchange.model.match.BasicAlgorithm;
import exchange.model.skill.FavoriteSkill;
import exchange.model.skill.KindTypeManager;
import exchange.model.skill.Skill;

@WebServlet("/Match.do")
public class MatchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher view = null;
		HttpSession session = request.getSession(false);

		if (session != null) {

			String uid = (String) session.getAttribute("uid");

			int cid = Integer.parseInt((String) request.getParameter("cardId"));
			//System.out.println("[cid]->" + cid);
			AccountManager am = new AccountManager();
			BasicAlgorithm ba = (BasicAlgorithm) session.getAttribute("algorithm");
			String region = "";
			if (ba == null) {
				ba = new BasicAlgorithm(uid, cid);
				ba.creatMateSet();
				session.setAttribute("algorithm", ba);
			}
			//System.out.println("[ba]->" + ba.match());
			Skill skill = ba.match();
			//System.out.println("[skill]->" + skill);
			if(skill == null) {
				ba = new BasicAlgorithm(uid, cid);
				ba.creatMateSet();
				session.setAttribute("algorithm", ba);
				response.sendRedirect("http://localhost:8080/Exchange/Home.do");
			}
			if(skill != null)
			{
				
				region = am.getRegion(skill.getUserId());
				System.out.println("[skill("+region+")]->" + skill);// <-----
				request.setAttribute("para", "cardId");
				request.setAttribute("cid", cid);
				request.setAttribute("skill", skill);
				request.setAttribute("region", region);
				request.setAttribute("kindName", KindTypeManager.getKindName(skill.getType().getKindCode()));
				view = request.getRequestDispatcher("/MatchPage.jsp");
				view.forward(request, response);
			}
		} else
			response.sendRedirect("http://localhost:8080/Exchange/Home.do");
	}
	
}