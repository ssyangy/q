/**
 * 
 */
package q.domain;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 27, 2011
 * 
 */
public enum WeiboFromType {
	WEB(0), GROUP(1) {
		public String getFromUrl(long fromId) {
			return "/group/" + fromId;
		}
	},
	APP(2) {
		public String getFromUrl(long fromId) {
			return "/app/" + fromId;
		}
	};

	static {
		WEB.namePrefix = "来自圈子网";
		GROUP.namePrefix = "来自圈子-";
		APP.namePrefix = "来自";
	}

	private int value;
	private String namePrefix;

	private WeiboFromType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * @param int1
	 * @return
	 */
	public static WeiboFromType convertValue(int value) {
		WeiboFromType type = null;
		if (WEB.getValue() == value) {
			type = WEB;
		} else if (GROUP.getValue() == value) {
			type = GROUP;
		} else if (APP.getValue() == value) {
			type = APP;
		}
		return type;
	}

	/**
	 * 
	 */
	public String getFromName(String postfix) {
		return postfix == null ? namePrefix : namePrefix + postfix;
	}

	/**
	 * @param fromId
	 */
	public String getFromUrl(long fromId) {
		return "";
	}

	/**
	 * @return
	 */
	public boolean isFromGroup() {
		return this == GROUP;
	}

}
