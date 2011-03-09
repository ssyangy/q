package q.serialize.tools;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品图片信息DO
 * 
 * @author moling
 * @since 2.0, 2009-12-1
 */
public class ItemImg implements Serializable {

	private static final long serialVersionUID = -1977149608097061825L;

	private String iid;           //商品图片所属商品的id
	public static final String IID = "iid";
	
	private Long id;           //商品图片的id
	public static final String ID = "id";
	
	private String url;          //商品图片访问地址
	public static final String URL = "url";
	
	private Integer position;          //商品图片显示位置，排序显示为0，1，2，3，4
	public static final String POSITION = "position";
	
	private Boolean isMajor;          //是否主图
	public static final String IS_MAJOR = "is_major";
	
	private Date created;			//图片上传时间
	public static final String CREATED = "created";
	
	private byte[] image;

	public String getIid() {
		return this.iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Boolean getIsMajor() {
		return this.isMajor;
	}

	public void setIsMajor(Boolean isMajor) {
		this.isMajor = isMajor;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
