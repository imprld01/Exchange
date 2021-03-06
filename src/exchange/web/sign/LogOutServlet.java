package exchange.web.sign;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.database.DataBaseAdmin;

@WebServlet("/Logout.do")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataBaseAdmin.closeConnection();
		
		HttpSession session = request.getSession(false);
		session.removeAttribute("uid");
		session.invalidate();
		
		response.sendRedirect("Index.do");
	}
}