package exchange.web.evaluation;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.evaluation.EvaluationManager;
import exchange.model.exchange.ExchangeManager;
import exchange.model.skill.Score;
import exchange.model.skill.SkillManager;

@WebServlet("/Evaluation.do")
public class EvaluationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session != null){
			
			int my = Integer.parseInt((String)request.getParameter("my"));
			int other = Integer.parseInt((String)request.getParameter("other"));
			int attitude = Integer.parseInt((String)request.getParameter("atd"));
			int profession = Integer.parseInt((String)request.getParameter("pfn"));
			int teaching = Integer.parseInt((String)request.getParameter("tch"));
			int frequency = Integer.parseInt((String)request.getParameter("fqc"));
			int satisfication = Integer.parseInt((String)request.getParameter("sfn"));
			String comment = (String)request.getParameter("comment");
			
			Score score = new Score(attitude, profession, teaching, frequency, satisfication);
			
			try {
				EvaluationManager.saveScore(other, score);
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}
			try {
				EvaluationManager.saveComment(other, comment);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			SkillManager.judgeBlock(other, score);
			SkillManager.updateSkillLevel(other);
			
			ExchangeManager.finishExchange(my, other);
			
			response.sendRedirect("Exchange.do");
		}
		else response.sendRedirect("index.html");
	}
}