package q.serialize.album;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class AlbumSet.
 */
public class AlbumSet implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5596972515367639695L;

	/** The count. */
	private int count;

	/** The pages. */
	private List<Album> pages;
	
	/** The sets. */
	private Set<Album> sets;

	/**
	 * Gets the count.
	 * 
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 * 
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Gets the pages.
	 * 
	 * @return the pages
	 */
	public List<Album> getPages() {
		return pages;
	}

	/**
	 * Sets the pages.
	 * 
	 * @param pages the pages to set
	 */
	public void setPages(List<Album> pages) {
		this.pages = pages;
	}

	/**
	 * Gets the sets.
	 * 
	 * @return the sets
	 */
	public Set<Album> getSets() {
		return sets;
	}

	/**
	 * Sets the sets.
	 * 
	 * @param sets the new sets
	 */
	public void setSets(Set<Album> sets) {
		this.sets = sets;
	}

}
