package tt.test.testId.testApp.exceptions;

import java.util.Set;

/**
 * Created on 10/11/2018
 * @author Dimitris Stavroulakis
 */
public class TestException extends RuntimeException {

	private ExceptionCode exceptionCode;

	private Set<String> items;

	/**
	 * Marks whether the exception has been logged so that it is not logged again in interceptor (ExceptionHandler)
	 * Transient in order not to be sent to UI
	 */
	protected transient boolean logged = false;

	public TestException() {
		// default constructor
	}

	public TestException(String message, ExceptionCode code, boolean logged) {
		super(message);
		this.exceptionCode = code;
		this.logged = logged;
	}

	public TestException(String message, ExceptionCode code, boolean logged, Set<String> items) {
		super(message);
		this.exceptionCode = code;
		this.logged = logged;
		this.items = items;
	}

	public TestException(String message, ExceptionCode exceptionCode) {
		super(message);
		this.exceptionCode=exceptionCode;
	}

	public TestException(String message, Throwable cause) {
		super(message, cause);
	}


	public void setExceptionCode(ExceptionCode code) {
		this.exceptionCode = code;
	}

	public ExceptionCode getExceptionCode() {
		return this.exceptionCode;
	}

	public Set<String> getItems() {
		return items;
	}

	public void setItems(Set<String> items) {
		this.items = items;
	}

	/**
	 * Marks whether the exception has been logged so that it is not logged again in interceptor (ExceptionHandler)
	 * Transient in order not to be sent to UI
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * Marks whether the exception has been logged so that it is not logged again in interceptor (ExceptionHandler)
	 * Transient in order not to be sent to UI
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
}
