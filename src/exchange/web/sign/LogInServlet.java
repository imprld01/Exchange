package exchange.web.sign;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exchange.model.account.Secret;
import exchange.model.sign.SignManager;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher view = null;

		String id = (String)request.getParameter("id");
		String pwd = (String)request.getParameter("pwd");
		Secret secret = new Secret(id, pwd);
		
		SignManager sm = new SignManager();
		boolean checkResult = sm.checkPassword(secret);
		
		if(checkResult) view = request.getRequestDispatcher("/Home.do");
		else view = request.getRequestDispatcher("index.html");
		
		view.forward(request, response);
	}
}