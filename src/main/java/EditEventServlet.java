

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditEventServlet
 */
@WebServlet("/EditEventServlet")
public class EditEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int event_id = Integer.parseInt(request.getParameter("event_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String name = request.getParameter("eventName");
		String organizer = request.getParameter("organization");
		String description = request.getParameter("description");
		String date = request.getParameter("eventDate");
		String time = request.getParameter("startTime");
		String time_end = request.getParameter("endTime");
		
		JDBCConnector.Event event = new JDBCConnector.Event(event_id, user_id, name, organizer, description, date, time, time_end);
		JDBCConnector.setEvent(event);
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

}
