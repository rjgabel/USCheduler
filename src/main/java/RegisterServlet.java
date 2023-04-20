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

import com.google.gson.Gson;

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
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String display_name = request.getParameter("displayName");
		String email = request.getParameter("email");
		
		Gson gson = new Gson();
		if (username.isEmpty() || password.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String error = "User info missing";
			out.write(gson.toJson(error));
			out.flush();
			out.close();
			return;
		}
		
		int addResult = JDBCConnector.addUser(username, password, display_name, email);
		String json;
		if (addResult == -1) {
			// Username is already taken. Send an error code of -1
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String error = "Username is taken";
			out.write(gson.toJson(error));
		}
		else if (addResult == -2) {
			// Email is already taken. Send an error code of -2.
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String error = "Email is already registered";
			out.write(gson.toJson(error));
		}
		else {
			// Registration was successful
			response.setStatus(HttpServletResponse.SC_OK);
			User user = JDBCConnector.getUser(addResult);
			out.write(gson.toJson(user));
		}
		
		out.flush();
		out.close();
		
	}

}
