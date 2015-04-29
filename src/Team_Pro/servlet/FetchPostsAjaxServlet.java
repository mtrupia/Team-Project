package Team_Pro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FetchPostsAjaxServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Request to fetch posts via AJAX");
		
		
		
		// TODO: read a JSON object from the body of the request
		
		resp.setContentType("application/json");
		resp.getWriter().println("{ \"result\": \"Yay\" }");
	}
}
