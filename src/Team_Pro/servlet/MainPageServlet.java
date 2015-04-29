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

public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pos = 0;
	private String searching = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("user") == null){
			resp.sendRedirect(req.getContextPath() + "/LogIn");
			return;
		}
		Controller controller;
		try {
			controller = new Controller();
			List<Comment> mover = new ArrayList<Comment>();
			if (pos == 0) {
				mover = controller.allPosts();
			} else {
				mover = controller.searchPosts(searching);
			}
			List<Comment> Posts = new ArrayList<Comment>();
			//int highestId = 0;
			for (int i = mover.size()-1; i >= 0; i--) {
				Comment post = mover.get(i);
				Posts.add(post);
				/*if (post.getId() > highestId) {
					highestId = post.getId();
				}*/
			}
			req.setAttribute("Posts", Posts);
			//req.setAttribute("HighestId", highestId);
		} catch (Exception e) {
			throw new ServletException("Error Retrieving Posts", e);
		}
		req.getRequestDispatcher("/_view/MainPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Controller controller;
		try {
			controller = new Controller();
			if (req.getParameter("userPage") != null){
				resp.sendRedirect(req.getContextPath() + "/UserPage");
				return;
			} else if (req.getParameter("allPosts") != null) {
				pos = 0;
				searching = "";
				resp.sendRedirect(req.getContextPath() + "/MainPage");
				return;
			} else if (req.getParameter("search") != null) {
				pos = 1;
				searching = req.getParameter("searching");
				resp.sendRedirect(req.getContextPath() + "/MainPage");
				return;
			} else if (req.getParameter("log") != null) {
				// logout
				User user = (User) req.getSession().getAttribute("user");
				if (user != null) {
				controller.logout(user.getId());
				}
				req.getSession().removeAttribute("user");
				resp.sendRedirect(req.getContextPath() + "/LogIn");
				return;
			} else if (req.getParameter("post") != null) {
				String text = req.getParameter("text");
				if (text.isEmpty()) { 
					resp.sendRedirect(req.getContextPath() + "/MainPage");
					return; 
				}
				// no more tags
				String tag = req.getParameter("tag");
				User user = (User) req.getSession().getAttribute("user");
				if (text.toLowerCase().contains("tube")) {
					text = text.substring(32);
					text = "https://www.youtube.com/embed/" + text;
				}
				controller.post(text, tag, user.getId());
				resp.sendRedirect(req.getContextPath() + "/MainPage");
				return;
			} else if(req.getParameter("ModLink") != null){
				//Moderator
				resp.sendRedirect(req.getContextPath() + "/ModeratorPage");
				return;
			} else {
				for (int i = 0; i < controller.allPosts().size(); i++) {
					int testId  = controller.allPosts().get(i).getId();
					if (req.getParameter("like" + Integer.toString(testId)) != null) {
						req.removeAttribute("like" + Integer.toString(testId));
						User user = (User) req.getSession().getAttribute("user");
						controller.like(user.getId(), testId);
						resp.sendRedirect(req.getContextPath() + "/MainPage");
						return;
					}
					if (req.getParameter("flag" + Integer.toString(testId)) != null) {
						req.removeAttribute("flag" + Integer.toString(testId));
						controller.flag(testId);
						resp.sendRedirect(req.getContextPath() + "/MainPage");
						return;
					}
					if (req.getParameter("delete" + Integer.toString(testId)) != null) {
						req.removeAttribute("delete" + Integer.toString(testId));
						controller.deletePost(testId);
						resp.sendRedirect(req.getContextPath() + "/MainPage");
						return;
					}
				}
				resp.sendRedirect(req.getContextPath() + "/MainPage");
			}
		} catch (Exception e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
