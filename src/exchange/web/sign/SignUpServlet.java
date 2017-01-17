package exchange.web.sign;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exchange.model.account.Account;
import exchange.model.account.Profile;
import exchange.model.account.Secret;
import exchange.model.sign.SignManager;
import exchange.model.database.DataBaseAdmin;

@WebServlet("/Signup.do")
public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = (String) request.getParameter("id");
		String pwd = (String) request.getParameter("pwd");
		String rePwd = (String) request.getParameter("re_pwd");
		String user = (String) request.getParameter("user");
		String nick = (String) request.getParameter("nick");
		
		boolean gender = Boolean.parseBoolean((String) request.getParameter("gender"));
		String email = (String) request.getParameter("email");
		String birth = (String) request.getParameter("birth");
		String region = (String) request.getParameter("region");

		/*
		 * String birth = request.getParameter("birth"); SimpleDateFormat
		 * parseDate = new java.text.SimpleDateFormat("dd/MM/yyyy");
		 * 
		 * Date date = null; try { date = (Date)parseDate.parse(birth); } catch
		 * (ParseException e) { e.printStackTrace(); } //String DisplayDate=
		 * formatDate.format(date);
		 */
//		System.out.println("id:" + id);
//		System.out.println("pwd:" + pwd);
//		System.out.println("user:" + user);
//		System.out.println("nick:" + nick);
//		System.out.println("gender:" + gender);
//		System.out.println("email:" + email);
//		System.out.println("birth:" + birth);
//		System.out.println("region:" + region);
		
		
		
		Secret secret = new Secret(id, pwd);
		Profile profile = new Profile(user, nick, gender, email, birth, region);
		SignManager sm = new SignManager();

		boolean checkResult = false;
		checkResult = sm.isAccountValid(secret.getId());
		System.out.println("checkResult:"+checkResult);
		if(pwd != rePwd)
		{
			response.sendRedirect("Index.jsp#popup_notsame");
		}
		else if (checkResult)
		{
			sm.create(new Account(profile, secret));
			response.sendRedirect("Index.jsp#popup_signsuccess");
		}
		else
		{
			response.sendRedirect("Index.jsp#popup_IdDuplicate");
		}
		
	}
}