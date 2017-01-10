package exchange.web.communication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;

@WebServlet("/Communication.do")
public class ShowCommunicationPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int SHOW_MESSAGES = 0;
	private static final int SAVE_MESSAGES = 1;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			
			int id = Integer.parseInt((String)request.getParameter("id"));
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			Skill skilltoshow = null;
			
			try {
				skilltoshow = SkillManager.findSkill(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("skill", skilltoshow);
			
			switch(mark){
			case SHOW_MESSAGES:
				
				RequestDispatcher view = null;
				
				//CommunicationManager cm = new CommunicationManager();
				//ArrayList<String> messages = cm.getAllMessages();
				
				//request.setAttribute("messages", messages);
				
				view = request.getRequestDispatcher("/CommunicationPage.jsp");
				view.forward(request, response);
				
				break;
			case SAVE_MESSAGES:
				
				String message = (String)request.getParameter("msg");
				
				//CommunicationManager cm = new CommunicationManager();
				//cm.saveMessage(message);
				
				break;
			}
		}
		else response.sendRedirect("index.html");
	}
}