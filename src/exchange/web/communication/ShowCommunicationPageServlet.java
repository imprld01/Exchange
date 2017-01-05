package exchange.web.communication;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.communication.CommunicationManager;

@WebServlet("/Communication.do")
public class ShowCommunicationPageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int SHOW_MESSAGES = 0;
	private static final int SAVE_MESSAGES = 1;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			
			switch(mark){
			case SHOW_MESSAGES:
				
				RequestDispatcher view = null;
				
				String uid = (String)session.getAttribute("uid");
				
				CommunicationManager cm = new CommunicationManager();
				ArrayList<String> messages = cm.getAllMessages();
				
				request.setAttribute("messages", messages);
				
				view = request.getRequestDispatcher("/CommunicationPage.jsp");
				view.forward(request, response);
				
				break;
			case SAVE_MESSAGES:
				
				break;
			}
		}
		else response.sendRedirect("index.html");
	}
}