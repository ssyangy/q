package q.serialize.mapping;


/**
 * 添加ApiArrayItemSupport,以用来在memberMapping中直接给mapping赋值,@See(MemberMapping.
 * setMappingArrayItemName)
 * 
 * <br>
 * apiArrayItem使用在集合出现的情况,如array,list,set。 用法为: mappingName="strings"
 * mappingType="java.lang.String" apiArrayItemName="str",则输出json/xml格式如下：
 * strings:{str:[1,2]} <strings><str>1</str><str>2</str></strings>
 * 
 * <br>
 * 如果没有配置apiArrayItemName,则会出现这样的错误： strings:{[1,2]} <strings>12</strings>
 * 
 * @author jiangyongyuan.tw
 * @author alin
 * 
 */
public interface HasComponentMapping<T, M> extends Mapping<T> {
	/**
	 * 获取
	 * 
	 * @return the component name
	 */
	public String getComponentName();

	/**
	 * 设置Set子项名称.
	 * 
	 * @param componentName
	 *            the new component name
	 */
	public void setComponentName(String componentName);

	public void setComponentMapping(Mapping<M> componentMapping);

	/**
	 * Gets the component mapping.
	 * 
	 * @return the componentMapping
	 */
	public Mapping<M> getComponentMapping();

}
