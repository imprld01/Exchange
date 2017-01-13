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
	private static final int DELETE_INVITATION = 3;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session != null){
			String sender = (String)request.getParameter("sdr");
			String receiver = (String)request.getParameter("rcv");
			int mark = Integer.parseInt((String)request.getParameter("mark"));
			System.out.println("0");
			switch(mark){
			case REJECT_INVITATION:
				ExchangeManager.rejectInvitation(sender, receiver);
				break;
			case ACCEPT_INVITATION:
				ExchangeManager.acceptInvitation(sender, receiver);
				break;
			case SEND_INVITATION:
				System.out.println("1");
				ExchangeManager.sendInvitation(sender, receiver);
				break;
			case DELETE_INVITATION:
				System.out.println("1");
				ExchangeManager.rejectInvitation(receiver, sender);
				break;
			}
			System.out.println("2");
			response.sendRedirect("http://localhost:8080/Exchange/Exchange.do");
		}
		else response.sendRedirect("Index.jsp");
	}
}