package exchange.web.exchange;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.exchange.ExchangeManager;

@WebServlet("/Invitation.do")
public class InvitationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int REJECT_INVITATION = 0;
	private static final int ACCEPT_INVITATION = 1;
	private static final int SEND_INVITATION = 2;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if(session != null){
			
			ExchangeManager em = new ExchangeManager();
			String sender = (String)request.getParameter("sdr");
			String receiver = (String)request.getParameter("rcv");
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			
			switch(mark){
			case REJECT_INVITATION:
				//em.rejectInvitation(send, receiver);
				break;
			case ACCEPT_INVITATION:
				//em.acceptInvitation(send, receiver);
				break;
			case SEND_INVITATION:
				//en.sendInvitation(sender, receiver);
				break;
			}
			
			response.sendRedirect("/Exchange.do");
		}
		else response.sendRedirect("index.html");
	}
}