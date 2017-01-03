package exchange.web.sign;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exchange.model.account.Profile;
import exchange.model.account.Secret;
import exchange.model.sign.SignManager;

@WebServlet("/Signup.do")
public class SignUpServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = (String)request.getParameter("id");
		String pwd = (String)request.getParameter("pwd");
		String user = (String)request.getParameter("user");
		String nick = (String)request.getParameter("nick");
		boolean gender = Boolean.parseBoolean((String)request.getParameter("gender"));
		String email = (String)request.getParameter("email");
		String birth = (String)request.getParameter("birth");
		String region = (String)request.getParameter("region");
		
		/*
		String birth = request.getParameter("birth");
		SimpleDateFormat parseDate = new java.text.SimpleDateFormat("dd/MM/yyyy");
		
		Date date = null;
		try {
			date = (Date)parseDate.parse(birth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//String DisplayDate= formatDate.format(date);
		*/
		
		Secret secret = new Secret(id, pwd);
		Profile profile = new Profile(user, nick, gender, email, birth, region);
		SignManager sm = new SignManager();
		
		boolean checkResult = sm.isAccountValid(secret.getId());
		
		if(!checkResult) sm.create(secret, profile);
		
		response.sendRedirect("index.html");
	}
}