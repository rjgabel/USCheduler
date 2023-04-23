import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCConnector {
	private static final String URL = "jdbc:mysql://localhost/finalprojectdatabase?user=root&password=root";

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
		User user = new User(-1, " ", " ", " ", " ");
		try {
			conn = DriverManager.getConnection(URL);
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM usertable WHERE username='" + username + "'");
			if (rs.next()) {
				user = new User(rs.getInt("UserID"), username, rs.getString("password"), rs.getString("displayName"),
						rs.getString("email"));
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
			rs = st.executeQuery("SELECT * FROM usertable WHERE UserID = " + user_id);
			if (rs.next()) {
				user = new User(user_id, rs.getString("username"), rs.getString("password"),
						rs.getString("displayName"), rs.getString("email"));
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
			st.execute("UPDATE usertable SET username='" + user.getUsername() + "', password='" + user.getPassword()
					+ "', displayName='" + user.getDisplayName() + "', email='" + user.getEmail() + "' WHERE UserID="
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
			rs = st.executeQuery("SELECT UserID FROM usertable WHERE username='" + username + "'");
			if (rs.next()) {
				user_id = -1;
			} else {
				rs.close();
				rs = st.executeQuery("SELECT UserID FROM usertable WHERE email='" + email + "'");
				if (rs.next()) {
					user_id = -2;
				} else {
					rs.close();
					st.execute("INSERT INTO usertable(username,password,displayName,email)VALUES('" + username + "','"
							+ password + "','" + display_name + "','" + email + "')");
					rs = st.executeQuery("SELECT UserID FROM usertable WHERE username='" + username + "'");
					rs.next();
					user_id = rs.getInt("UserID");
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
			rs = st.executeQuery("SELECT * FROM eventtable WHERE EventID=" + event_id);
			if (rs.next()) {
				event = new Event(event_id, rs.getInt("UserID"), rs.getString("EventName"), rs.getString("Organizer"),
						rs.getString("EventDescription"), rs.getString("EventDate"), rs.getString("EventTime"),
						rs.getString("EventTimeEnd"), rs.getString("ImgURL"));
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
			rs = st.executeQuery("SELECT * FROM eventtable WHERE EventDate='" + event_date + "'");
			while (rs.next()) {
				eventList.add(new Event(rs.getInt("EventID"), rs.getInt("UserID"), rs.getString("EventName"), rs.getString("Organizer"),
						rs.getString("EventDescription"), rs.getString("EventDate"), rs.getString("EventTime"),
						rs.getString("EventTimeEnd"), rs.getString("ImgURL")));
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
			st.execute("UPDATE eventtable SET UserID = " + event.getUser_id() + ",EventName='" + event.getName()
					+ "',Organizer='" + event.getOrganizer() + "',EventDescription='" + event.getDescription()
					+ "',EventDate='" + event.getDate()
					+ "',EventTime='" + event.getTime() + "',EventTimeEnd='" + event.getTime_end()
					+ "',ImgURL='" + event.getImg_url() + "' WHERE EventID=" + event.getEvent_id());
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
			String ex = "INSERT INTO eventtable(UserID,EventName,Organizer,EventDescription,EventDate,EventTime,EventTimeEnd,ImgURL) VALUES("
					+ user_id + ",'" + name + "','" + organizer + "','" + description + "','" + date + "','" + time + "','"
					+ time_end + "','" + img_url + "')";
			st.execute(ex);
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
