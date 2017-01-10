package exchange.web.sign;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exchange.model.account.Secret;
import exchange.model.sign.SignManager;

@WebServlet("/Login.do")
public class LogInServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession();

		System.out.println("getSession" + request.getSession());// ->

		if (session.isNew()) {

			System.out.println("session is new");// ->

			String id = (String) request.getParameter("id");
			String pwd = (String) request.getParameter("pwd");
			Secret secret = new Secret(id, pwd);

			SignManager sm = new SignManager();
			boolean checkResult = false;
			checkResult = sm.check(secret);

			if (checkResult) {
				// session
				session.setAttribute("uid", secret.getId());
				System.out.println("session" + session.getAttribute("uid"));// ->
				session.setMaxInactiveInterval(1800);
			} else {
				System.out.println("密碼錯誤");
				session.invalidate();
				response.sendRedirect("Index.jsp#login");
				return;
			}
		}

		response.sendRedirect("http://localhost:8080/Exchange/Home.do");
	}
}