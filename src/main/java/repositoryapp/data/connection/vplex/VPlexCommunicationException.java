package repositoryapp.data.connection.vplex;

public class VPlexCommunicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3001665447137390561L;
	
	public VPlexCommunicationException(){
		
	}
	
	public VPlexCommunicationException(String message) {
		super(message);
	}
	
	public VPlexCommunicationException(Throwable cause) {
		super(cause);
	}
	
	public VPlexCommunicationException(String message, Throwable cause) {
		super(message, cause);
	}
}
