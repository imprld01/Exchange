package exchange.web.skill;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.skill.SkillManager;

@WebServlet("/Skill.do")
public class SkillServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int CREATION = 0;
	private static final int MODIFICATION = 1;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
		
			String ie, type, img, vdo;
			SkillManager sm = new SkillManager();
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			
			switch(mark){
			case CREATION:				
				ie = (String)session.getAttribute("introExper");
				type = (String)request.getParameter("type");
				img = (String)session.getAttribute("image");
				vdo = (String)request.getParameter("video");
				
				response.sendRedirect("/Home.do");
				break;
			case MODIFICATION:
				ie = (String)session.getAttribute("introExper");
				img = (String)session.getAttribute("image");
				vdo = (String)request.getParameter("video");
				
				response.sendRedirect("/Home.do");
				break;
			}
		}
		else response.sendRedirect("index.html");
	}
}