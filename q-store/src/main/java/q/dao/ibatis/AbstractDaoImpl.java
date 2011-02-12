package q.dao.ibatis;

import com.ibatis.sqlmap.client.SqlMapClient;

abstract class AbstractDaoImpl {
	protected SqlMapClient sqlMapClient;

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
