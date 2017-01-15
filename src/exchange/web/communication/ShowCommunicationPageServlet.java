package exchange.web.communication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.communication.CommunicationManager;
import exchange.model.skill.Skill;
import exchange.model.skill.SkillManager;

@WebServlet("/Communication.do")
public class ShowCommunicationPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);

		if (session != null) {

			int id = Integer.parseInt((String) request.getParameter("id"));
			int othersID = Integer.parseInt((String) request.getParameter("othersID"));
			Skill skilltoshow = null;

			try {
				skilltoshow = SkillManager.findSkill(othersID);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			String myNickName = CommunicationManager.getNickName(id);
			String othersNickName = CommunicationManager.getNickName(othersID);
			request.setAttribute("skill", skilltoshow);
			request.setAttribute("myNickName", myNickName);
			request.setAttribute("othersNickName", othersNickName);
			request.setAttribute("id", id);

			RequestDispatcher view = null;

			// CommunicationManager cm = new CommunicationManager();
			// ArrayList<String> messages = cm.getAllMessages();

			// request.setAttribute("messages", messages);

			view = request.getRequestDispatcher("CommunicationPage.jsp");
			view.forward(request, response);

			// String message = (String)request.getParameter("msg");

			// CommunicationManager cm = new CommunicationManager();
			// cm.saveMessage(message);

		} else
			response.sendRedirect("Index.jsp");
	}
}