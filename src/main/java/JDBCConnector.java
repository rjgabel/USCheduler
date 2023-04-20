import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCConnector {
	private static final String URL = "jdbc:mysql://localhost:3306/finalprojectdatabase?user=root&password=root";

	/*
	 * Returns the user with the specified username. If the user with the specified
	 * username does not exist, returns null.
	 */
	public static User getUser(String username) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM USERTABLE WHERE USERNAME='" + username + "'");
			if (rs.next()) {
				user = new User(rs.getInt("USERID"), username, rs.getString("PASSWORD"), rs.getString("DISPLAYNAME"),
						rs.getString("EMAIL"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return user;
	}

	/*
	 * Returns the user with the specified UserID. If the user with the specified
	 * UserID does not exist, returns null.
	 */
	public static User getUser(int user_id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM USERTABLE WHERE USERID=" + user_id);
			if (rs.next()) {
				user = new User(user_id, rs.getString("USERNAME"), rs.getString("PASSWORD"),
						rs.getString("DISPLAYNAME"), rs.getString("EMAIL"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return user;
	}

	/*
	 * Sets the User data of the user with the given id to match that passed in as a
	 * parameter.
	 */
	public static void setUser(User user) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			st.execute("UPDATE USERTABLE SET USERNAME='" + user.getUsername() + "',PASSWORD='" + user.getPassword()
					+ "',DISPLAYNAME='" + user.getDisplayName() + "',EMAIL='" + user.getEmail() + "' WHERE USERID="
					+ user.getUserID());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	/*
	 * Attempts to register a new user. If successful, returns the UserID of the new
	 * user: otherwise, returns a negative error code.
	 * 
	 * -1: Username already taken
	 * 
	 * -2: Email already taken
	 */
	public static synchronized int addUser(String username, String password, String display_name, String email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int user_id = 0;
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT USERID FROM USERTABLE WHERE USERNAME='" + username + "'");
			if (rs.next()) {
				user_id = -1;
			} else {
				rs.close();
				rs = st.executeQuery("SELECT USERID FROM USERTABLE WHERE EMAIL='" + email + "'");
				if (rs.next()) {
					user_id = -2;
				} else {
					rs.close();
					st.execute("INSERT INTO USERTABLE(USERNAME,PASSWORD,DISPLAYNAME,EMAIL)VALUES('" + username + "','"
							+ password + "','" + display_name + "','" + email + "')");
					rs = st.executeQuery("SELECT USERID FROM USERTABLE WHERE USERNAME='" + username + "'");
					rs.next();
					user_id = rs.getInt("USERID");
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return user_id;
	}

	/*
	 * A class used to represent returned user data (will probably replace with
	 * actual user class once it is written)
	 */
	public static class Event {
		private int event_id;
		private int user_id;
		private String name;
		private String organizer;
		private String description;
		private String date;
		private String time;
		private String time_end;
		private String img_url;

		public Event(int event_id, int user_id, String name, String organizer, String description, String date
				, String time, String time_end, String img_url) {
			this.event_id = event_id;
			this.user_id = user_id;
			this.name = name;
			this.organizer = organizer;
			this.description = description;
			this.date = date;
			this.time = time;
			this.time_end = time_end;
			this.img_url = img_url;
		}

		public int getEventID() {
			return event_id;
		}

		public int getUserID() {
			return user_id;
		}

		public String getEventName() {
			return name;
		}

		public String getOrganizer() {
			return organizer;
		}

		public String getEventDescription() {
			return description;
		}
		
		public String getEventDate() {
			return date;
		}

		public String getEventTime() {
			return time;
		}

		public String getEventTimeEnd() {
			return time_end;
		}
		
		public String getImg_url() {
	        return img_url;
	    }
	}

	/*
	 * Returns the event with the specified EventID. If the event with the specified
	 * EventID does not exist, returns null.
	 */
	public static Event getEvent(int event_id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Event event = null;
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM EVENTTABLE WHERE EVENTID=" + event_id);
			if (rs.next()) {
				event = new Event(event_id, rs.getInt("USERID"), rs.getString("EVENTNAME"), rs.getString("ORGANIZER"),
						rs.getString("EVENTDESCRIPTION"), rs.getString("EVENTDATE"), rs.getString("EVENTTIME"),
						rs.getString("EVENTTIMEEND"), rs.getString("IMGURL"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return event;
	}
	
	public static ArrayList<Event> getEventsByDate(String event_date) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Event> eventList = new ArrayList<Event>();
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM EVENTTABLE WHERE EVENTDATE=" + event_date);
			while (rs.next()) {
				eventList.add(new Event(rs.getInt("EVENTID"), rs.getInt("USERID"), rs.getString("EVENTNAME"), rs.getString("ORGANIZER"),
						rs.getString("EVENTDESCRIPTION"), rs.getString("EVENTDATE"), rs.getString("EVENTTIME"),
						rs.getString("EVENTTIMEEND"), rs.getString("IMGURL")));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return eventList;
	}

	/*
	 * Sets the Event data of the event with the given id to match that passed in as
	 * a parameter.
	 */
	public static void setEvent(Event event) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			st.execute("UPDATE EVENTTABLE SET USERID=" + event.getUserID() + ",EVENTNAME='" + event.getEventName()
					+ "',ORGANIZER='" + event.getOrganizer() + "',EVENTDESCRIPTION='" + event.getEventDescription()
					+ "',EVENTDATE='" + event.getEventDate()
					+ "',EVENTTIME='" + event.getEventTime() + "',EVENTTIMEEND='" + event.getEventTimeEnd()
					+ "',IMGURL='" + event.getImg_url() + "' WHERE EVENTID=" + event.getEventID());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	/*
	 * Adds a new event and returns the EventID of the new event.
	 */
	public static int addEvent(int user_id, String name, String organizer, String description, String date, 
			String time, String time_end, String img_url) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int event_id = 0;
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			st.execute(
					"INSERT INTO EVENTTABLE(USERID,EVENTNAME,ORGANIZER,EVENTDESCRIPTION,EVENTTIME,EVENTTIMEEND,IMGURL)VALUES("
							+ user_id + ",'" + name + "','" + organizer + "','" + description + "','" + date + "','" + time + "','"
							+ time_end + ",'" + img_url + "')");
			// https://dev.mysql.com/doc/refman/8.0/en/information-functions.html#function_last-insert-id
			rs = st.executeQuery("SELECT LAST_INSERT_ID()");
			rs.next();
			event_id = rs.getInt(1);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return event_id;
	}
}
