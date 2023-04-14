

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

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
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	ZoneId pst = ZoneId.of("America/Los_Angeles");
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
		PrintWriter out = response.getWriter();
		
		int event_id = Integer.parseInt(request.getParameter("event_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String name = request.getParameter("eventName");
		String organizer = request.getParameter("organization");
		String description = request.getParameter("description");
		String date = request.getParameter("eventDate");
		String time = request.getParameter("startTime");
		String time_end = request.getParameter("endTime");
		int errorCode = -1;
		
		try {
			if (sdf.parse(date).compareTo(new Date()) < 0) {
				// If the given date is in the past, it is invalid
				errorCode = 1;
			}
			else if (sdf.parse(date).compareTo(new Date()) == 0 && LocalTime.parse(time).compareTo(LocalTime.now(pst)) < 0) {
				// If the given date is today, but the start time is before the current time
				errorCode = 2;
			}
			else if (LocalTime.parse(time).compareTo(LocalTime.parse(time_end)) >= 0) {
				// If the start time is after or the same as the end time
				errorCode = 3;
			}
			else {
				// If the given date is in the future
				JDBCConnector.Event event = new JDBCConnector.Event(event_id, user_id, name, organizer, description, date, time, time_end);
				JDBCConnector.setEvent(event);
				errorCode = 0;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			out.print("{\"error_code\":" + errorCode + "}");
			out.flush();
			out.close();
		}
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
