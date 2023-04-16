

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

import com.google.gson.Gson;

/**
 * Servlet implementation class AddEventServlet
 */
@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	ZoneId pst = ZoneId.of("America/Los_Angeles");
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEventServlet() {
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

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int user_id = Integer.parseInt(request.getParameter("User_id"));
		String name = request.getParameter("eventName");
		String organizer = request.getParameter("organization");
		String description = request.getParameter("description");
		String date = request.getParameter("eventDate");
		String time = request.getParameter("startTime");
		String time_end = request.getParameter("endTime");
		Gson gson = new Gson();
		
		try {
			if (sdf.parse(date).compareTo(new Date()) < 0) {
				// If the given date is in the past, it is invalid
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String error = "Invalid date: Date must be in the future.";
				out.write(gson.toJson(error));
			}
			else if (sdf.parse(date).compareTo(new Date()) == 0 && LocalTime.parse(time).compareTo(LocalTime.now(pst)) < 0) {
				// If the given date is today, but the start time is before the current time
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String error = "Invalid time: Start time must be in the future.";
				out.write(gson.toJson(error));
			}
			else if (LocalTime.parse(time).compareTo(LocalTime.parse(time_end)) >= 0) {
				// If the start time is after or the same as the end time
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String error = "Invalid time: Start time must come before the end time.";
				out.write(gson.toJson(error));
			}
			else {
				// If the given date is in the future
				JDBCConnector.addEvent(user_id, name, organizer, description, date, time, time_end);
				response.setStatus(HttpServletResponse.SC_OK);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			out.flush();
			out.close();
		}
	}
}
