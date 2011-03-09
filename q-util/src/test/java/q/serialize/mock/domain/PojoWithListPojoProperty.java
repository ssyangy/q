/**
 * 
 */
package q.serialize.mock.domain;

import java.util.List;

/**
 * @author xalinx at gmail dot com
 * @date Mar 18, 2010
 */
public class PojoWithListPojoProperty {
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private List<Pojo> pojos;

	public List<Pojo> getPojos() {
		return pojos;
	}

	public void setPojos(List<Pojo> pojos) {
		this.pojos = pojos;
	}

}
