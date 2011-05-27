package q.dao.ibatis;
import java.sql.SQLException;
import java.util.Random;

import q.dao.AuthcodeDao;

public class AuthcodeDaoImpl extends AbstractDaoImpl implements AuthcodeDao {
	@Override
	public String getValueById(long authcodeId) throws SQLException {
		return (String) this.sqlMapClient.queryForObject("selectAuthcodeById",authcodeId);

	}

	@Override
	public void updateValueById(final long authcodeId)
			throws SQLException {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(rand.nextInt(10));
		}
		final String value = sb.toString();
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
