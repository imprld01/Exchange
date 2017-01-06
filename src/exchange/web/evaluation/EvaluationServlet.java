package exchange.web.evaluation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.evaluation.EvaluationManager;
import exchange.model.exchange.ExchangeManager;

@WebServlet("/Evaluation.do")
public class EvaluationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			
			String my = (String)request.getParameter("my");
			String other = (String)request.getParameter("other");
			int attitude = Integer.parseInt((String)request.getParameter("atd"));
			int profession = Integer.parseInt((String)request.getParameter("pfn"));
			int teaching = Integer.parseInt((String)request.getParameter("tch"));
			int frequency = Integer.parseInt((String)request.getParameter("fqc"));
			int satisfication = Integer.parseInt((String)request.getParameter("sfn"));
			String comment = (String)request.getParameter("comment");
			
			//EvaluationManager em = new EvaluationManager(other);
			//em.saveScore(attitude, profession, teaching, frequency, satisfication);
			//em.saveComment(comment);
			
			
			ExchangeManager.finishExchange(my, other);
			
			response.sendRedirect("/Exchange.do");
		}
		else response.sendRedirect("index.html");
	}
}