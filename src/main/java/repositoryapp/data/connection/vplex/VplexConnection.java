package repositoryapp.data.connection.vplex;

/**
 * Just a structure to hold basic credentials used to communicate with a Vplex.
 * 
 *
 */
public class VplexConnection {
	private  String host;
	private  String user;
	private  String password;
	

    public VplexConnection(String host, String username, String password) {
		this.host = host;
		this.user = username;
		this.password = password;
	}

    
	public String getHostname() {
		return host;
	}

	public String getUsername() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
	public void setHost(String host)
    {
        this.host = host;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}