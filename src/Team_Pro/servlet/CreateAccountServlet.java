package Team_Pro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Team_Pro.controller.Controller;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("user") != null) {
			resp.sendRedirect(req.getContextPath() + "/MainPage");
			return;
		}
		req.getRequestDispatcher("/_view/CreateAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Controller controller;
		try {
			controller = new Controller();
			if (req.getParameter("create") != null) {
				String fname = req.getParameter("fname");
				String lname = req.getParameter("lname");
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String mes;
				mes = controller.createAccount(fname, lname, username, password);
				req.setAttribute("name", username);
				req.setAttribute("pass", password);
				req.setAttribute("errorMessage", mes);
				if (mes.contains("created!")) {
					resp.sendRedirect(req.getContextPath() + "/LogIn");
					return;
				} else {
					req.getRequestDispatcher("/_view/CreateAccount.jsp").forward(req, resp);
					return;
				}
			} else {
				req.removeAttribute("name");
				req.removeAttribute("pass");
				req.removeAttribute("errorMessage");
				resp.sendRedirect(req.getContextPath() + "/LogIn");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
