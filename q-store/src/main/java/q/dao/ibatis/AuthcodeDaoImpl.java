package q.dao.ibatis;
import java.sql.SQLException;
import q.dao.AuthcodeDao;
import q.log.Logger;

public class AuthcodeDaoImpl extends AbstractDaoImpl implements AuthcodeDao {
	private final static Logger log = Logger.getLogger();
	@Override
	public String getValueById(long authcodeId) throws SQLException {
		return (String) this.sqlMapClient.queryForObject("selectAuthcodeById",authcodeId);

	}

	@Override
	public void updateValueById(final long authcodeId, final String value)
			throws SQLException {
		Object authcode = new Object(){
			@SuppressWarnings("unused")
			public long getId() {
				return authcodeId;
			}
			@SuppressWarnings("unused")
			public String getValue(){
				return value;
			}
		};
		this.sqlMapClient.update("updateAuthcodeById", authcode);
	}

}
