public class Event {
    private int user_id;
    private String name;
    private String organizer;
    private String description;
    private String date;
    private String time;
    private String time_end;

    public Event(int user_id, String name, String organizer, String description, String date, String time, String time_end) {
        this.user_id = user_id;
        this.name = name;
        this.organizer = organizer;
        this.description = description;
        this.date = date;
        this.time = time;
        this.time_end = time_end;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
	public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
	public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }    

}