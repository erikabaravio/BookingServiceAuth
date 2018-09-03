package pw.io.booker.exception;

public class BookerServiceException extends RuntimeException{
	
private final String userFriendlyErrorMessage;
	
	public BookerServiceException(Exception e, String userFriendlyErrorMessage) {
		super(userFriendlyErrorMessage);
		this.userFriendlyErrorMessage = userFriendlyErrorMessage;
	}
		
	public BookerServiceException(String message) {
		this.userFriendlyErrorMessage = message;
	}

	public String getUserFriendlyErrorMessage() {
		return userFriendlyErrorMessage;
	}	
	
	@Override
		public String getMessage() {
			return userFriendlyErrorMessage;
		}
}
