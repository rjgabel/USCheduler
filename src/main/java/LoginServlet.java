// Use when user submits login information
// Logs the user in

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Gson gson = new Gson();
		if (username.isEmpty() || password.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String error = "User info missing";
			out.write(gson.toJson(error));
			out.flush();
			out.close();
			return;
		}
		
		JDBCConnector.User user = JDBCConnector.getUser(username);
		if (user.equals(null)) {
			// If the user was not found
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String error = "User not found";
			out.write(gson.toJson(error));
		}
		else {
			if (!user.getPassword().equals(password)) {
				// If the inputed password does not match that user's password
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String error = "Incorrect password";
				out.write(gson.toJson(error));
			}
			else {
				// The username and password is correct
				response.setStatus(HttpServletResponse.SC_OK);
				out.write(gson.toJson(user));
			}
		}
		
		out.flush();
		out.close();
		
	}

}
