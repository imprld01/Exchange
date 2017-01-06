package exchange.web.skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;
import exchange.model.skill.Type;

@WebServlet("/Skill.do")
public class SkillServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int CREATION = 0;
	private static final int MODIFICATION = 1;
	private static final int SHOW_SKILL = 2;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			
			Type type;
			int vn, in;
			Skill skill;
			String cid, ie, id;
			SkillManager sm = new SkillManager();
			ArrayList<String> img = new ArrayList<String>();
			ArrayList<String> vdo = new ArrayList<String>();
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			
			switch(mark){
			case CREATION:
				ie = (String)request.getParameter("introExper");
				//type = new Type((String)request.getParameter("type"));
				vn = Integer.parseInt((String)request.getParameter("vnum"));
				in = Integer.parseInt((String)request.getParameter("inum"));
				for(int i = 1; i <= in; ++i) img.add((String)request.getParameter("image" + i));
				for(int i = 1; i <= vn; ++i) vdo.add((String)request.getParameter("video" + i));
				
				//skill = new Skill(ie, type, img, vdo);
				
				//sm.createSkill(skill);
				
				response.sendRedirect("/Home.do");
				break;
			case MODIFICATION:
				cid = (String)request.getParameter("cid");
				ie = (String)request.getParameter("introExper");
				vn = Integer.parseInt((String)request.getParameter("vnum"));
				in = Integer.parseInt((String)request.getParameter("inum"));
				for(int i = 1; i <= in; ++i) img.add((String)request.getParameter("image" + i));
				for(int i = 1; i <= vn; ++i) vdo.add((String)request.getParameter("video" + i));
				
				//skill = new Skill(cid, ie, img, vdo);
				
				//sm.modifySkill(skill);
				
				response.sendRedirect("/Home.do");
				break;
			case SHOW_SKILL:
				
				RequestDispatcher view = null;
				Hashtable<String, Skill> table = (Hashtable<String, Skill>)session.getAttribute("skills");
				
				id = (String)request.getParameter("id");
				
				request.setAttribute("skill", table.get(id));
				
				view = request.getRequestDispatcher("/SkillPage.jsp");
				view.forward(request, response);
				break;
			}
		}
		else response.sendRedirect("index.html");
	}
}