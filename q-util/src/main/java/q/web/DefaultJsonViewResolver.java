/**
 * 
 */
package q.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.web.servlet.ModelAndView;

import q.log.Logger;
import q.serialize.config.MappingConfigReader;
import q.serialize.config.MappingFormatException;
import q.serialize.convert.Convert;
import q.serialize.convert.JSONConvert;
import q.serialize.mapping.DefaultMemberMappingFactory;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MemberMappingFactory;
import q.serialize.mapping.OperationCodeException;
import q.util.StringKit;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 7, 2011
 * 
 */
public class DefaultJsonViewResolver implements ViewResolver {
	protected final Logger log = Logger.getLogger();
	
	private Map<String, org.springframework.core.io.Resource> resources = new HashMap<String, org.springframework.core.io.Resource>();
	
	public void setResources(Map<String, org.springframework.core.io.Resource> resources) {
		this.resources = resources;
	}

	private MemberMappingFactory memberMappingFactory = new DefaultMemberMappingFactory();
	
	private MappingConfigReader configReader = new MappingConfigReader();
	
	public void init() throws IOException, MappingFormatException {
		for(Map.Entry<String, org.springframework.core.io.Resource> entry: resources.entrySet()) {
			log.info("start parse resource key:%s", entry.getKey());
			String[] segs = StringKit.split(entry.getKey(), ':');
			MemberMapping<Object> memberMapping = configReader.readMemberMapping(entry.getValue().getInputStream());
			String modelName = segs[1];
			memberMapping.setName(modelName);
			String resourceName = segs[0];
			memberMappingFactory.addMemberMapping(memberMapping, resourceName);
			log.info("add %s:%s", resourceName, memberMapping);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.ViewResolver#view(q.web.ResourceContext, q.web.Resource)
	 */
	@Override
	public ModelAndView view(ResourceContext context, q.web.Resource resource) throws ServletException, IOException, MappingException, OperationCodeException {
		MemberMapping<Object> memberMapping = memberMappingFactory.getMemberMapping(resource.getName());
		if(memberMapping == null) {
			return null;
		} 
		log.debug("get %s:%s", resource.getName(), memberMapping);
		Object model = context.getModel(memberMapping.getName());
		Convert convert = new JSONConvert(context.getWriter());
		memberMapping.write(convert, model, false);
		return null;
	}

}
