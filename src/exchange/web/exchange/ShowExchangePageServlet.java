package exchange.web.exchange;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.exchange.Exchange;
import exchange.model.exchange.ExchangeManager;

@WebServlet("/Exchange.do")
public class ShowExchangePageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		if(session != null){
			RequestDispatcher view = null;
			String uid = (String)session.getAttribute("uid");
			
			ArrayList<Exchange> exchange = ExchangeManager.getExchangings(uid);
			ArrayList<Exchange> receive = ExchangeManager.getReceiveInvitations(uid);
			ArrayList<Exchange> send = ExchangeManager.getSendInvitations(uid);
			
			request.setAttribute("Exchanging", exchange);
			request.setAttribute("ReceiveInvitation", receive);
			request.setAttribute("SendInvitation", send);
			
			view = request.getRequestDispatcher("/ExchangePage.jsp");
			view.forward(request, response);
		}
		else response.sendRedirect("Index.jsp");
	}
}