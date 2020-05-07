package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.PlayerDaoImpl;
import utils.impl.EmailUtilsImpl;


@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/EmailServlet"})
public class EmailServlet extends HttpServlet {
	private String email;  //®ç®±
	private String vCode;  // 
	private String vCodeReceive;  //
	private String method;  // 
	private PrintWriter out;  // 
	private EmailUtilsImpl emailUtil = EmailUtilsImpl.instance;
	public EmailServlet() {
		// TODO Auto-generated constructor stub
		System.out.println("Îßºþ");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	/*
	 *
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		//
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		out = resp.getWriter();
		// 
		email = req.getParameter("email");
		vCodeReceive = req.getParameter("vcode");
		method = req.getParameter("method");

		switch (method) {
		case "getVCode":
			mGetVCode();
			break;
		case "verify":
			mVerify();
			break;
		default:
			break;
		}
		
		out.flush();
		out.close();
	}

	/*
	 * 
	 */
	private void mGetVCode() {
		
		// TODO Auto-generated method stub
		if(!isEmail(email)) {  //
			out.print(-1);
			return;
		}
		
		try {
			emailUtil.sendEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vCode = emailUtil.getVCode();
		System.out.println("vCode:" + vCode);
		out.print(1);
	}
	
	/*
	 * @return true/false
	 */
	private boolean isEmail(String email) {
		PlayerDaoImpl userDao = new PlayerDaoImpl();
		if(email.length() == 0 || email == null) {
			return false;
		}
		if(!userDao.emailVerify(email)) {
			return false;
		}
		//
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
		return pattern.matcher(email).matches();
	}
	
	/*
	 * 
	 */
	private void mVerify() {
		// TODO Auto-generated method stub
		if(vCode.equals(vCodeReceive)) {
			out.print(1);
		}
		else {
			out.print(-1);
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

}
