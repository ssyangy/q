package q.dao.ibatis;

import q.log.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

abstract class AbstractDaoImpl {
	protected final Logger log = Logger.getLogger();
	
	protected SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
