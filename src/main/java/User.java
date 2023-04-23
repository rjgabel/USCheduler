//package USCheduler; //do we need one of these bad boys

public class User {
	// username, password, email, balance
    private int userID;
    private String username;
    private String password;
    private String displayName;
    private String email;

    public User() {
    	this.userID = -1;
    }
    
    public User(int userID, String username, String password, String displayName, String email) {
		this.userID = userID;
        this.username = username;
		this.password = password;
        this.displayName = displayName;
        this.email = email;
	}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}