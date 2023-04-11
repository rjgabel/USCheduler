// Use when user submits registration information.
// Attempts to add the user to the database
// If successful, it should also log the user in.

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		String display_name = request.getParameter("DisplayName");
		String email = request.getParameter("Email");
		
		int addResult = JDBCConnector.addUser(username, password, display_name, email);
		String json;
		if (addResult == -1) {
			// Username is already taken. Send an error code of -1
			json = "{\"user_id\":-1}";
		}
		else if (addResult == -2) {
			// Email is already taken. Send an error code of -2.
			json = "{\"user_id\":-2}";
		}
		else {
			// Registration was successful
			JDBCConnector.User user = JDBCConnector.getUser(addResult);
			json = "{";
			json += "\"user_id\":" + user.getUserID() + ", ";
			json += "\"username\":\"" + user.getUsername() + "\", ";
			json += "\"password\":\"" + user.getPassword() + "\", ";
			json += "\"display_name\":\"" + user.getDisplayName() + "\", ";
			json += "\"email\":\"" + user.getEmail() + "\"";
			json += "}";
		}
		
		out.print(json);
		out.flush();
		out.close();
		
	}

}
