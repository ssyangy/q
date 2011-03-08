package q.serialize.tools;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 商品信息DO
 * 
 * @author moling
 * @since 2.0, 2009-12-1
 */
public class Item implements Serializable {

	private static final long serialVersionUID = -7510993367921300892L;
	
	public static final String P = ".";
	
	public static final String BANNER="banner"; //仓库中商品等待上架分类
	public static final String UNSOLD="unsold";//没卖出的
	public static final String PARTLY_SOLD="partly_sold";//部分卖出
	public static final String REGULAR_SHELVED="regular_shelved";//定时上架
	public static final String NEVER_ON_SHELF="never_on_shelf";//从未上架
	public static final String SOLD_OUT="sold_out";//全部卖完
	public static final String OFF_SHELF="off_shelf";//我下架的
	public static final String FOR_SHELVED="for_shelved";//等待所有上架

	private String iid;// 商品id
	public static final String IID = "iid";
    
	private Long numIid;//商品数字ID
	public static final String NUM_IID="num_iid";
	
	private String detailUrl;// 商品url；
	public static final String DETAIL_URL="detail_url";
	
	private String title;// 商品标题
	public static final String TITLE = "title";

	private String nick;// 用户昵称
	public static final String NICK = "nick";

	private String type;// 商品类型
	public static final String TYPE = "type";
	public static final String VALUE_TYPE_AUCTION = "auction"; // 拍卖类型
	public static final String VALUE_AUCTION = "a"; 
	public static final String VALUE_TYPE_FIXED = "fixed"; // 一口价类型
	public static final String VALUE_FIXED = "b"; 
	
	public static final String TBS_UPDATE_TYPE = "tbs_update_type";// taobao.item.tbsupdate标识，借用type属性。

	private Long cid;// 商品类目
	public static final String CID = "cid";

	private String sellerCids;// 卖家类目列表
	public static final String SELLER_CIDS = "seller_cids";

	private String props; // 商品属性
	public static final String PROPS = "props";
	
	private String desc;	//商品描述
	public static final String DESC = "desc";

	private String picUrl;	//商品主图片地址
	public static final String PIC_URL = "pic_url";

	private Integer num;	//商品数量
	public static final String NUM = "num";

	private Integer validThru;	//商品有效期
	public static final String VALID_THRU = "valid_thru";

	private Date listTime; // 上架时间
	public static final String LIST_TIME = "list_time";

	private Date delistTime; // 下架时间
	public static final String DELIST_TIME = "delist_time";

	private String stuffStatus;	//商品新旧程度
	public static final String STUFF_STATUS = "stuff_status";
	public static final String VALUE_STUFF_STATUS_NEW = "new";
	public static final String VALUE_STUFF_STATUS_SECOND = "second";
	public static final String VALUE_STUFF_STATUS_UNUSED = "unused";

	private Location location;
	public static final String LOCATION = "location";
	public static final String LOCATION_CITY = LOCATION + P + Location.CITY;
	public static final String LOCATION_STATE = LOCATION + P + Location.STATE;

	private String price;// 商品价格
	public static final String PRICE = "price";
    

	
	private String postFee; // 平邮价格
	public static final String POST_FEE = "post_fee";

	private String expressFee;
	public static final String EXPRESS_FEE = "express_fee";

	private String emsFee;
	public static final String EMS_FEE = "ems_fee";

	private Boolean hasDiscount;// 支持会员折扣
	public static final String HAS_DISCOUNT = "has_discount";

	private String freightPayer; // 谁承担运费
	public static final String FREIGHT_PAYER = "freight_payer";
	public static final String VALUE_FREIGHT_PAYER_SELLER = "seller";
	public static final String VALUE_FREIGHT_PAYER_BUYER = "buyer";

	private Boolean hasInvoice;	// 是否有发票
	public static final String HAS_INVOICE = "has_invoice";

	private Boolean hasWarranty;	// 是否有保修
	public static final String HAS_WARRANTY = "has_warranty";

	private Boolean hasShowcase;	//是否橱窗推荐
	public static final String HAS_SHOWCASE = "has_showcase";

	private Date modified;	//最近修改时间
	public static final String MODIFIED = "modified";

	private Date created;// 创建时间
	public static final String CREATED = "created";

	// ==========隐私数据=================
	private String increment;// 加价幅度，如果是一口价，不需要指定
	public static final String INCREMENT = "increment";

	private Boolean autoRepost;// 是否到期自动上架
	public static final String AUTO_REPOST = "auto_repost";

	private String approveStatus;	// 商品上传后的状态
	public static final String APPROVE_STATUS = "approve_status"; 
	public static final String VALUE_APPROVE_STATUS_ONSALE = "onsale"; // 出售中状态
	public static final String VALUE_APPROVE_STATUS_INSTOCK = "instock"; // 库存状态
	public static final String VALUE_APPROVE_STATUS_IRREGULAR = "irregular"; //违规状态
	public static final String VALUE_APPROVE_STATUS_ALL = "all"; //所以状态
	public static final String VALUE_APPROVE_STATUS_OTHER = "other"; // 其他商品状态

	private byte[] image;

	// ==========BC合并后新加的字段=================
	
	private Long postageId;
	public static final String POSTAGE_ID = "postage_id";
	
	private Long productId;
	public static final String PRODUCT_ID = "product_id";
	
	private Long auctionPoint;     //商品的返点比例
	public static final String AUCTION_POINT = "auction_point";
	
	private String propertyAlias;      //商品属性值别名
	public static final String PROPERTY_ALIAS = "property_alias";
	
	private List<ItemImg> itemImgs;       //商品子图片列表
	public static final String ITEMIMG = "item_img";
	public static final String ITEMIMG_ID = ITEMIMG + P + ItemImg.ID;
	public static final String ITEMIMG_URL = ITEMIMG + P + ItemImg.URL;
	public static final String ITEMIMG_POSITION = ITEMIMG + P + ItemImg.POSITION;
	
	
	private Map<Integer, String[]> inputProps;        //用户自定义属性
	
	private String inputPids;
	public static final String INPUT_PIDS = "input_pids";
	
	private String inputStr;
	public static final String INPUT_STR = "input_str";
	
	private String lang;
	public static final String LANG = "lang";
	
	/**
	 * 是否定时上架商品, 淘宝助理使用
	 */
	private Boolean isTiming;
	public static final String IS_TIMING = "is_timing";
	
	/**
	 * 是否在淘宝显示(对应与商品option值为ItemOptionsConstants.ITEM_OPTIONS_NOT_SHOW_IN_TAOBAO = 2147483648L)
	 */
	private Boolean isTaobao;
	public static final String IS_TAOBAO = "is_taobao";
	
	/**
	 * 是否在外部网店显示(对应与商品option值为ItemOptionsConstants.ITEM_OPTIONS_NOT_SHOW_IN_EXTRA_SHOP = 4294967296L)
	 */
	private Boolean isEx;
	public static final String IS_EX = "is_ex";
	
	/**
	 * 是否在3D淘宝的商品(对应与商品option值为ItemOptionsConstants.ITEM_OPTIONS_3D = 1L << 39)
	 */
	private Boolean is3D;
	public static final String IS_3D = "is_3D";



	private String outerId;  //商家外部ID
	public static final String OUTER_ID="outer_id";

	private Boolean isVirtual;	//是否是虚拟物品
	public static final String IS_VIRTURAL="is_virtual"; 
	
	private Long score;	//卖家信用数
	public static final String SCORE = "score"; 
	public static final String SELLER_CREDIT = "seller_credit"; 
	
	private Long volume;	//商品30天交易量
	public static final String VOLUME = "volume"; 
	public static final String POPULARITY = "popularity";
	
	//是否替换sku列表的标志。如果为true表示将更新传入的sku替换到原来的sku列表中，否则merge进去
	private Boolean isReplaceSku;
	public static final String IS_REPLACE_SKU = "is_replace_sku";
	
	private Boolean oneStation;					//是否淘1站商品
	public static final String ONE_STATION = "one_station"; 
	public static final String[] ONE_STATION_VALUE = {"1:101", "2:120"}; 
	
	//是否秒杀商品
	private String secondKill;
	public static final String SECOND_KILL = "second_kill"; 
	public static final String SECOND_KILL_WEB = "web_only";
	public static final String SECOND_KILL_WAP = "wap_only";
	public static final String SECOND_KILL_WEB_AND_WAP = "web_and_wap";
	
	//代充商品类型
	private String autoFill;
	public static final String AUTO_FILL = "auto_fill";
	
	public String getIid() {
		return this.iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public Long getNumIid() {
		return this.numIid;
	}
	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}
	public String getDetailUrl() {
		return this.detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNick() {
		return this.nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getCid() {
		return this.cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getSellerCids() {
		return this.sellerCids;
	}
	public void setSellerCids(String sellerCids) {
		this.sellerCids = sellerCids;
	}
	public String getProps() {
		return this.props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public String getDesc() {
		return this.desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPicUrl() {
		return this.picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getNum() {
		return this.num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getValidThru() {
		return this.validThru;
	}
	public void setValidThru(Integer validThru) {
		this.validThru = validThru;
	}
	public Date getListTime() {
		return this.listTime;
	}
	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}
	public Date getDelistTime() {
		return this.delistTime;
	}
	public void setDelistTime(Date delistTime) {
		this.delistTime = delistTime;
	}
	public String getStuffStatus() {
		return this.stuffStatus;
	}
	public void setStuffStatus(String stuffStatus) {
		this.stuffStatus = stuffStatus;
	}
	public Location getLocation() {
		return this.location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getPrice() {
		return this.price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPostFee() {
		return this.postFee;
	}
	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}
	public String getExpressFee() {
		return this.expressFee;
	}
	public void setExpressFee(String expressFee) {
		this.expressFee = expressFee;
	}
	public String getEmsFee() {
		return this.emsFee;
	}
	public void setEmsFee(String emsFee) {
		this.emsFee = emsFee;
	}
	public Boolean getHasDiscount() {
		return this.hasDiscount;
	}
	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}
	public String getFreightPayer() {
		return this.freightPayer;
	}
	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}
	public Boolean getHasInvoice() {
		return this.hasInvoice;
	}
	public void setHasInvoice(Boolean hasInvoice) {
		this.hasInvoice = hasInvoice;
	}
	public Boolean getHasWarranty() {
		return this.hasWarranty;
	}
	public void setHasWarranty(Boolean hasWarranty) {
		this.hasWarranty = hasWarranty;
	}
	public Boolean getHasShowcase() {
		return this.hasShowcase;
	}
	public void setHasShowcase(Boolean hasShowcase) {
		this.hasShowcase = hasShowcase;
	}
	public Date getModified() {
		return this.modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getCreated() {
		return this.created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getIncrement() {
		return this.increment;
	}
	public void setIncrement(String increment) {
		this.increment = increment;
	}
	public Boolean getAutoRepost() {
		return this.autoRepost;
	}
	public void setAutoRepost(Boolean autoRepost) {
		this.autoRepost = autoRepost;
	}
	public String getApproveStatus() {
		return this.approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public byte[] getImage() {
		return this.image;
	}
	public void setImage(byte[] data) {
		this.image = data;
	}
	public Long getPostageId() {
		return this.postageId;
	}
	public void setPostageId(Long postageId) {
		this.postageId = postageId;
	}
	public Long getProductId() {
		return this.productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getAuctionPoint() {
		return this.auctionPoint;
	}
	public void setAuctionPoint(Long auctionPoint) {
		this.auctionPoint = auctionPoint;
	}
	public String getPropertyAlias() {
		return this.propertyAlias;
	}
	public void setPropertyAlias(String propertyAlias) {
		this.propertyAlias = propertyAlias;
	}

	public Map<Integer, String[]> getInputProps() {
		return this.inputProps;
	}
	public void setInputProps(Map<Integer, String[]> inputProps) {
		this.inputProps = inputProps;
	}
	public String getInputPids() {
		return this.inputPids;
	}
	public void setInputPids(String inputPids) {
		this.inputPids = inputPids;
	}
	public String getInputStr() {
		return this.inputStr;
	}
	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}
	public String getLang() {
		return this.lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Boolean getIsTiming() {
		return this.isTiming;
	}
	public void setIsTiming(Boolean isTiming) {
		this.isTiming = isTiming;
	}
	public Boolean getIsTaobao() {
		return this.isTaobao;
	}
	public void setIsTaobao(Boolean isTaobao) {
		this.isTaobao = isTaobao;
	}
	public Boolean getIsEx() {
		return this.isEx;
	}
	public void setIsEx(Boolean isEx) {
		this.isEx = isEx;
	}
	public Boolean getIs3D() {
		return this.is3D;
	}
	public void setIs3D(Boolean is3D) {
		this.is3D = is3D;
	}

	public String getOuterId() {
		return this.outerId;
	}
	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}
	public Boolean getIsVirtual() {
		return this.isVirtual;
	}
	public void setIsVirtual(Boolean isVirtual) {
		this.isVirtual = isVirtual;
	}
	public Long getScore() {
		return this.score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Long getVolume() {
		return this.volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public Boolean getIsReplaceSku() {
		return this.isReplaceSku;
	}
	public void setIsReplaceSku(Boolean isReplaceSku) {
		this.isReplaceSku = isReplaceSku;
	}
	public boolean isFixedType() {
		return VALUE_TYPE_FIXED.equals(type);
	}
	public boolean isAuctionType() {
		return VALUE_TYPE_AUCTION.equals(type);
	}
	public Boolean getOneStation() {
		return this.oneStation;
	}
	public void setOneStation(Boolean oneStation) {
		this.oneStation = oneStation;
	}
	public String getSecondKill() {
		return this.secondKill;
	}
	public void setSecondKill(String secondKill) {
		this.secondKill = secondKill;
	}
	public String getAutoFill() {
		return this.autoFill;
	}
	public void setAutoFill(String autoFill) {
		this.autoFill = autoFill;
	}

}
