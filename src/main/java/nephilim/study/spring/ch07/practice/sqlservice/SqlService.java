package nephilim.study.spring.ch07.practice.sqlservice;


public interface SqlService {

	public String getSql(String key) throws SqlRetrievalFailureException; 
	
}