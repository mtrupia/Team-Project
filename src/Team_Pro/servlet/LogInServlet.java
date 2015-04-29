package Team_Pro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Team_Pro.controller.Controller;
import Team_Pro.model.User;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("user") != null) {
			resp.sendRedirect(req.getContextPath() + "/MainPage");
			return;
		}
		req.getRequestDispatcher("/_view/LogIn.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Controller controller;
		try {
			controller = new Controller();
			// Decode form parameters and dispatch to controller
			if (req.getParameter("log") != null) {
				String name = req.getParameter("name");
				String pass = req.getParameter("pass");
				User user = controller.login(name, pass);
				if (user.getId() >= 0) {
					req.getSession().setAttribute("user", user);
					resp.sendRedirect(req.getContextPath() + "/MainPage");
					return;
				} else {
					String mes = "";
					if (user.getId() == -1) {
						mes = "Invalid username";
					} else if (user.getId() == -2) {
						mes = "Invalid password";
						req.setAttribute("name", req.getParameter("name"));
					}
					req.setAttribute("errorMessage", mes);
					req.getRequestDispatcher("/_view/LogIn.jsp").forward(req, resp);
					return;
				}
			} else {
				req.removeAttribute("name");
				req.removeAttribute("pass");
				req.removeAttribute("errorMessage");
				resp.sendRedirect(req.getContextPath() + "/CreateAccount");
			}
		} catch (Exception e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
