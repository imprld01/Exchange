package exchange.web.exchange;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.exchange.ExchangeManager;
import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;

@WebServlet("/Exchange.do")
public class ShowExchangePageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			RequestDispatcher view = null;
			
			String uid = (String)session.getAttribute("uid");
			
			ExchangeManager em = new ExchangeManager();
			//ArrayList<Invitation> result = em.getAllInvitations();
			
			//request.setAttribute("invitations", result);
			
			view = request.getRequestDispatcher("/ExchangePage.jsp");
			view.forward(request, response);
		}
		else response.sendRedirect("index.html");
	}
}