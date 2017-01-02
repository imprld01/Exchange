package exchange.web.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Account.do")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final int CREATION = 0;
	private static final int MODIFICATION = 1;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mark = Integer.parseInt((String)request.getParameter("mark"));
		
		switch(mark){
		case CREATION: break;
		case MODIFICATION: break;
		default: ;
		}
	}
}