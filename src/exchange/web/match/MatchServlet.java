package exchange.web.match;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.match.BasicAlgorithm;
import exchange.model.skill.Skill;

@WebServlet("/Match.do")
public class MatchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession(false);
		
		if(session != null){
			
			String uid = (String)session.getAttribute("uid");
			String cid = (String)request.getParameter("cardId");
			
			BasicAlgorithm ba = new BasicAlgorithm(uid);
			//Skill skill = ba.match();
			
			request.setAttribute("para", "cardId");
			request.setAttribute("cid", cid);
			//request.setAttribute("skill", skill);
			
			view = request.getRequestDispatcher("/MatchPage.jsp");
			view.forward(request, response);
		}
		else response.sendRedirect("index.html");
	}
}