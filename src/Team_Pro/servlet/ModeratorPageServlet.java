package Team_Pro.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Team_Pro.controller.Controller;
import Team_Pro.model.Comment;
import Team_Pro.model.User;

public class ModeratorPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User you = ((User) req.getSession().getAttribute("user"));
		if (req.getSession().getAttribute("user") == null){
			resp.sendRedirect(req.getContextPath() + "/LogIn");
			return;
		}
		else if(you.getModded() == 0){
			resp.sendRedirect(req.getContextPath() + "/MainPage");
			return;
		}
		Controller controller;
		try {
			controller = new Controller();
			
			List<Comment> mover = new ArrayList<Comment>();
			List<User> users = new ArrayList<User>();
			mover = controller.modPosts(you.getId());
			List<Comment> removedPosts = new ArrayList<Comment>();
			for (int i = mover.size()-1; i >= 0; i--) {
				Comment post = mover.get(i);
				removedPosts.add(post);
			}
			users = controller.getUsers();
			req.setAttribute("Posts", removedPosts);
			req.setAttribute("Users", users);
			
		} catch (Exception e) {
			throw new ServletException("Error Retrieving Posts", e);
		}
		req.getRequestDispatcher("/_view/ModeratorPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Controller controller;
		User you = ((User) req.getSession().getAttribute("user"));
		try {
			controller = new Controller();
			if (req.getParameter("userPage") != null){
				resp.sendRedirect(req.getContextPath() + "/UserPage");
				return;
			}
			else if(req.getParameter("MainPage") != null){
				resp.sendRedirect(req.getContextPath() + "/MainPage");
				return;
			}
			else if(req.getParameter("log") != null) {
				// logout
				User user = (User) req.getSession().getAttribute("user");
				controller.logout(user.getId());
				req.getSession().removeAttribute("user");
				resp.sendRedirect(req.getContextPath() + "/LogIn");
				return;
			}
			else {
				for (int i = 0; i < controller.getUsers().size(); i++) {
					int testId  = controller.getUsers().get(i).getId();
					if (req.getParameter("mod" + Integer.toString(testId)) != null) {
						req.removeAttribute("mod" + Integer.toString(testId));
						controller.modded(testId);
					}
				}
				for (int i = 0; i < controller.modPosts(you.getId()).size(); i++) {
					int testId2  = controller.modPosts(you.getId()).get(i).getId();
					if(req.getParameter("return" + Integer.toString(testId2)) != null){
						controller.addtoList(testId2);
					}
					else if(req.getParameter("remove" + Integer.toString(testId2)) != null){
						controller.increment(testId2);
					}
				}
				resp.sendRedirect(req.getContextPath() + "/ModeratorPage");
			}
		} catch (Exception e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}