/**
 * 
 */
package q.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import q.util.CollectionKit;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 14, 2011
 * 
 */
public class Area extends AbstractDomain implements Serializable {
	private static final long serialVersionUID = -7895347057802203037L;

	private String name;

	private String fullName;

	private Area parent;

	private List<Area> childs;

	public List<Area> getChilds() {
		return childs;
	}

	public Area getParent() {
		return parent;
	}

	public void addChild(Area child) {
		if (childs == null) {
			childs = new ArrayList<Area>();
		}
		this.childs.add(child);
		child.parent = this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJson() {
		StringBuilder sb = new StringBuilder();
		buildJson(sb);
		return sb.toString();
	}

	public String getChildsJson() {
		StringBuilder sb = new StringBuilder();
		buildChildsJson(sb);
		return sb.toString();
	}

	/**
	 * @param sb
	 */
	private void buildChildsJson(StringBuilder sb) {
		sb.append("[");
		for (Iterator<Area> itt = childs.iterator(); itt.hasNext();) {
			Area area = itt.next();
			area.buildJson(sb);
			if (itt.hasNext()) {
				sb.append(',');
			}
		}
		sb.append(']');
	}

	private void buildJson(StringBuilder sb) {
		sb.append('{');
		sb.append("\"id\":");
		sb.append(id);
		sb.append(',');
		sb.append("\"name\":");
		sb.append('\"');
		sb.append(name);
		sb.append('\"');
		if (childs != null) {
			sb.append(',');
			sb.append("\"childs\":");
			buildChildsJson(sb);
		}
		sb.append('}');
	}

	/**
	 * @return
	 */
	public boolean hasChilds() {
		return CollectionKit.isNotEmpty(childs);
	}

	/**
	 * @param city
	 * @return
	 */
	public boolean isChild(Area father) {
		if (father == null) {
			return false;
		}
		if (this.parent == null) {
			return false;
		}
		return this.parent.getId() == father.getId();
	}
}
