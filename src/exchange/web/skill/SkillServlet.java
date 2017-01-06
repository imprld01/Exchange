package exchange.web.skill;

import java.io.IOException;
import java.sql.SQLException;
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
	
	private static final int CREATE_SKILL = 0;
	private static final int MODIFY_SKILL = 1;
	private static final int SHOW_SKILL = 2;
	private static final int CREATE_FAVORITE = 3;
	private static final int DELETE_FAVORITE = 4;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			
			Type type = null;
			int vn, in;
			Skill skill;
			String cid, ie, id, uid, tp;
			ArrayList<String> img = new ArrayList<String>();
			ArrayList<String> vdo = new ArrayList<String>();
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			
			switch(mark){
			case CREATE_SKILL:
				try {
					type = new Type((String)request.getParameter("type"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				uid = (String)session.getAttribute("uid");
				ie = (String)request.getParameter("introExper");
				vn = Integer.parseInt((String)request.getParameter("vnum"));
				in = Integer.parseInt((String)request.getParameter("inum"));
				for(int i = 1; i <= in; ++i) img.add((String)request.getParameter("image" + i));
				for(int i = 1; i <= vn; ++i) vdo.add((String)request.getParameter("video" + i));
				
				skill = new Skill(uid, ie, type, img, vdo);
				
				SkillManager.createSkill(skill);
				
				response.sendRedirect("/Home.do");
				break;
			case MODIFY_SKILL:
				try {
					type = new Type((String)request.getParameter("type"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cid = (String)request.getParameter("cid");
				ie = (String)request.getParameter("introExper");
				vn = Integer.parseInt((String)request.getParameter("vnum"));
				in = Integer.parseInt((String)request.getParameter("inum"));
				for(int i = 1; i <= in; ++i) img.add((String)request.getParameter("image" + i));
				for(int i = 1; i <= vn; ++i) vdo.add((String)request.getParameter("video" + i));
				
				skill = new Skill(cid, ie, type, img, vdo);
				
				SkillManager.modifySkill(skill);
				
				response.sendRedirect("/Home.do");
				break;
			case SHOW_SKILL:
				RequestDispatcher view = null;
				//Hashtable<String, Skill> table = (Hashtable<String, Skill>)session.getAttribute("skills");
				
				id = (String)request.getParameter("id");
				Skill skilltoshow = new Skill(Integer.parseInt(id));
				
				request.setAttribute("skill", skilltoshow);
				
				view = request.getRequestDispatcher("/SkillPage.jsp");
				view.forward(request, response);
				break;
			case CREATE_FAVORITE:
				tp = (String)request.getParameter("type");
				uid = (String)session.getAttribute("uid");
				
				SkillManager.createFavoriteSkill(uid, tp);
				
				response.sendRedirect("/Home.do");
				break;
			case DELETE_FAVORITE:
				tp = (String)request.getParameter("type");
				uid = (String)session.getAttribute("uid");
				
				SkillManager.deleteFavoriteSkill(uid, tp);
				
				response.sendRedirect("/Home.do");
				break;
			}
		}
		else response.sendRedirect("index.html");
	}
}