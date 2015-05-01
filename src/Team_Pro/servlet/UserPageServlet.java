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

public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pos = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("user") == null) {
			resp.sendRedirect(req.getContextPath() + "/LogIn");
			return;
		}
		
		Controller controller;
		try {
			controller = new Controller();
			User me = (User) req.getSession().getAttribute("user");
			List<Comment> mover = new ArrayList<Comment>();
			if (pos == 0) {
				mover = controller.userPosts(me.getId());
			} else {
				mover = controller.likePosts(me.getId());
			}
			List<Comment> Posts = new ArrayList<Comment>();
			for (int i = mover.size()-1; i >= 0; i--) {
				Comment post = mover.get(i);
				Posts.add(post);
			}
			req.setAttribute("Posts", Posts);
		} catch (Exception e) {
			throw new ServletException("Error Retrieving Posts", e);
		}
		req.getRequestDispatcher("/_view/UserPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Controller controller;
		try {
			controller = new Controller();
			
			if (req.getParameter("mainPage") != null){
				resp.sendRedirect(req.getContextPath() + "/MainPage");
				return;
			} else if (req.getParameter("post") != null) {
				String text = req.getParameter("text");
				if (text.isEmpty()) {
					resp.sendRedirect(req.getContextPath() + "/UserPage");
					return;
				}
				String tag = req.getParameter("tag");
				User user = (User) req.getSession().getAttribute("user");
				if (text.toLowerCase().contains("tube")) {
					text = text.substring(32);
					text = "https://www.youtube.com/embed/" + text;
				}
				controller.post(text, user.getId());
			} else if (req.getParameter("log") != null) {
				// logout
				User user = (User) req.getSession().getAttribute("user");
				controller.logout(user.getId());
				req.getSession().removeAttribute("user");
				resp.sendRedirect(req.getContextPath() + "/LogIn");
				return;
			} else if (req.getParameter("posts") != null) {
				pos = 0;
			} else if (req.getParameter("likes") != null) {
				pos = 1;
			} else {
				for (int i = 0; i < controller.allPosts().size(); i++) {
					int testId  = controller.allPosts().get(i).getId();
					if (req.getParameter("delete" + Integer.toString(testId)) != null) {
						req.removeAttribute("delete" + Integer.toString(testId));
						controller.deletePost(testId);
					}
				}
			}
			resp.sendRedirect(req.getContextPath() + "/UserPage");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
