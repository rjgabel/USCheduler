// Use when user submits login information
// Logs the user in

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String json;
		
		JDBCConnector.User user = JDBCConnector.getUser(username);
		if (user.equals(null)) {
			// If the user was not found
			json = "{\"user_id\":-1}";
		}
		else {
			if (!user.getPassword().equals(password)) {
				// If the inputed password does not match that user's password
				json = "{\"user_id\":-2}";
			}
			else {
				// The username and password is correct
				json = "{";
				json += "\"user_id\":" + user.getUserID() + ", ";
				json += "\"username\":\"" + user.getUsername() + "\", ";
				json += "\"password\":\"" + user.getPassword() + "\", ";
				json += "\"display_name\":\"" + user.getDisplayName() + "\", ";
				json += "\"email\":\"" + user.getEmail() + "\"";
				json += "}";
			}
		}
		
		out.print(json);
		out.flush();
		out.close();
		
	}

}
