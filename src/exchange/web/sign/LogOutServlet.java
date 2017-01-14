package exchange.web.sign;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.database.DataBaseAdmin;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/Logout.do")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DataBaseAdmin.closeConnection();
		
		
		HttpSession session = request.getSession(false);
		session = null;
		
		response.sendRedirect("Index.jsp");
	}

	

}
