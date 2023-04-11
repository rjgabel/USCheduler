// Used when clicking on an event to see all of the event's info

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
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
		
		int eventID = Integer.parseInt(request.getParameter("EventID"));
		JDBCConnector.Event event = JDBCConnector.getEvent(eventID);
		String json;
		
		if (event.equals(null)) {
			/* Should be impossible since this servlet only gets called when you
			click on a valid event, but just in case, send an error code */
			json = "{\"event_id\":-1}";
		}
		else {
			json = "{";
			json += "\"event_id\":" + event.getEventID() + ", ";
			json += "\"user_id\":" + event.getUserID() + ", ";
			json += "\"name\":\"" + event.getEventName() + "\", ";
			json += "\"organizer\":\"" + event.getOrganizer() + "\", ";
			json += "\"description\":\"" + event.getEventDescription() + "\", ";
			json += "\"time\":\"" + event.getEventTime() + "\", ";
			json += "\"timeEnd\":\"" + event.getEventTimeEnd() + "\"";
			json += "}";
		}
		
		out.print(json);
		out.flush();
		out.close();
		
	}
	
}
