/**
 * 
 */
package q.dao.ibatis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import q.dao.AreaDao;
import q.domain.Area;
import q.util.StringKit;

/**
 * 
 * content of file:
 * 
 * <ul>
 * <li>120000 天津市</li>
 * <li>120100 市辖区 *</li>
 * <li>120101 和平区</li>
 * <li>120102 河东区</li>
 * <li>120103 河西区</li>
 * <li>120104 南开区</li>
 * <li>120105 河北区</li>
 * </ul>
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 15, 2011
 * 
 */
public class AreaDaoImpl extends AbstractDaoImpl implements AreaDao {

	private Area rootArea;

	private Map<Long, Area> areas;

	public void init() throws IOException {
		this.rootArea = new Area();
		this.areas = new HashMap<Long, Area>();
		BufferedReader br = new BufferedReader(new InputStreamReader(AreaDaoImpl.class.getClassLoader().getResourceAsStream("data/area.txt"), "utf-8"));
		String strLine;
		Area province = null;
		Area city = null;
		while ((strLine = br.readLine()) != null) {
			if (StringKit.isNotEmpty(strLine)) {
				String[] segs = StringKit.split(strLine, ' ');
				if (segs.length > 3) { // 654300 阿勒泰地区 阿勒泰
					throw new IllegalArgumentException(strLine);
				}
				Area area = new Area();
				Long areaId = Long.valueOf(segs[0]);
				String name = segs[1]; // 阿勒泰地区
				if (segs.length == 3) {
					if (segs[2].equals("*")) { // ignore 120100 市辖区 *
						if (areaId % 100 == 0) {
							city = null; // ignore city
						}
						continue;
					}
					name = segs[2]; // 阿勒泰
				}
				area.setName(name);
				area.setId(areaId);
				this.areas.put(areaId, area); // put area into map
				if (areaId % 10000 == 0) { // province: 120000 天津市
					this.rootArea.addChild(area);
					province = area;
				} else if (areaId % 100 == 0) { // city: 320100 南京市
					province.addChild(area);
					city = area;
				} else {
					if (city == null) { // 市级区
						province.addChild(area);
					} else {
						city.addChild(area);
					}
				}
				area.setFullName(segs[1]);
			}
		}

	}

	@Override
	public Area getRootArea() {
		return rootArea;
	}

	@Override
	public Area getAreaById(long id) {
		return this.areas.get(id);
	}

}
