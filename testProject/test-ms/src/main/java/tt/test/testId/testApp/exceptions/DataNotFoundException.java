package tt.test.testId.testApp.exceptions;

/**
 * Created on 10/11/2018
 * @author Dimitris Stavroulakis
 */
public class DataNotFoundException extends TestException {

    public DataNotFoundException() {
    	super("Data could not be found.",ExceptionCode.DATA_NOT_FOUND);
	};

    public DataNotFoundException(boolean logged) {
    	this();
    	this.logged = logged;
	};

	public DataNotFoundException(String message, boolean logged) {
		super(message,ExceptionCode.DATA_NOT_FOUND,logged);
	}

}
