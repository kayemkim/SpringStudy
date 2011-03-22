package nephilim.study.spring.ch07.practice.sqlservice;

import java.util.Map;

public class SimpleSqlService implements SqlService {

	private Map<String, String> sqlMap;
	
	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}


	public String getSql(String key) throws SqlRetrievalFailureException {
		String sql = sqlMap.get(key);
		if(sql == null) {
			String message = String.format("Ű[%s]�� ���� SQL�� ã�� �� �����ϴ�.", key);
			throw new SqlRetrievalFailureException(message);
		}
		return sql;
	}

}
