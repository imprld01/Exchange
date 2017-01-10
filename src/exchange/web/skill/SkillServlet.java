package exchange.web.skill;

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

import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;

@WebServlet("/Skill.do")
public class SkillServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int CREATE_SKILL = 0;
	private static final int MODIFY_SKILL = 1;
	private static final int SHOW_SKILL = 2;
	private static final int CREATE_FAVORITE = 3;
	private static final int DELETE_FAVORITE = 4;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session != null){
			int vn, in;
			Skill skill = null;
			String cid, ie, uid, tp;
			ArrayList<String> img = new ArrayList<String>();
			ArrayList<String> vdo = new ArrayList<String>();
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			
			switch(mark){
			case CREATE_SKILL:
				tp = (String)request.getParameter("type");
				uid = (String)session.getAttribute("uid");
				ie = (String)request.getParameter("introExper");
				vn = Integer.parseInt((String)request.getParameter("vnum"));
				in = Integer.parseInt((String)request.getParameter("inum"));
				for(int i = 1; i <= in; ++i) img.add((String)request.getParameter("image" + i));
				for(int i = 1; i <= vn; ++i) vdo.add((String)request.getParameter("video" + i));
				
				skill = new Skill(uid, ie, tp, img, vdo);
				SkillManager.createSkill(skill);
				
				break;
			case MODIFY_SKILL:
				tp = (String)request.getParameter("type");
				cid = (String)request.getParameter("cid");
				ie = (String)request.getParameter("introExper");
				vn = Integer.parseInt((String)request.getParameter("vnum"));
				in = Integer.parseInt((String)request.getParameter("inum"));
				for(int i = 1; i <= in; ++i) img.add((String)request.getParameter("image" + i));
				for(int i = 1; i <= vn; ++i) vdo.add((String)request.getParameter("video" + i));
				
				skill = new Skill(cid, ie, tp, img, vdo);
				
				SkillManager.modifySkill(skill);
				break;
			case CREATE_FAVORITE:
				tp = (String)request.getParameter("type");
				uid = (String)session.getAttribute("uid");
				
				SkillManager.createFavoriteSkill(tp, uid);
				break;
			case DELETE_FAVORITE:
				tp = (String)request.getParameter("type");
				uid = (String)session.getAttribute("uid");
				
				SkillManager.deleteFavoriteSkill(tp, uid);
				break;
			case SHOW_SKILL:
				RequestDispatcher view = null;

				String id = (String)request.getParameter("id");
				Skill skilltoshow = null;
				
				try {
					skilltoshow = SkillManager.findSkill(Integer.parseInt(id));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				request.setAttribute("skill", skilltoshow);
				
				view = request.getRequestDispatcher("/SkillPage.jsp");
				view.forward(request, response);
				return;
			}
			
			response.sendRedirect("/Home.do");
		}
		else response.sendRedirect("index.html");
	}
}