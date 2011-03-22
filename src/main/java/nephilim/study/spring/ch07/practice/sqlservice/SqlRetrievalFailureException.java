package nephilim.study.spring.ch07.practice.sqlservice;

public class SqlRetrievalFailureException extends RuntimeException {
	
	private static final long serialVersionUID = 1401971919672308384L;

	public SqlRetrievalFailureException(String message) {
		super(message);
	}
	
	public SqlRetrievalFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}
