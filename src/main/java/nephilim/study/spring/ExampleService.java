package nephilim.study.spring;


/**
 * {@link Service} with hard-coded input data.
 */
public class ExampleService implements Service {
	
	/**
	 * Reads next record from input
	 */
	public String getMessage() {
		return "Hello world!";	
	}

}
