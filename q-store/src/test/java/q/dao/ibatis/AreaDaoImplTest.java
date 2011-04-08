package q.dao.ibatis;
import java.io.File;
import junit.framework.Assert;
import org.junit.Test;
import q.util.StringKit;
public class AreaDaoImplTest {
	AreaDaoImpl ipDao = new AreaDaoImpl();
	{
		ipDao.setIpFile(new File(System.getProperty("user.dir") + "/src/test/resources/data/qqwry.dat"));
		ipDao.initIp();
	}

	@Test
	public void testGetCountryArea() {
			String []temp=	ipDao.getCountryArea("59.78.9.123");
			Assert.assertEquals("上海交通大学闵行校区D9楼", temp[0]+temp[1]);
	}

}
