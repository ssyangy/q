package q.serialize.config;

import q.serialize.mapping.Mapping;

public interface MappingFactory {

	public Mapping<?> getMappingFromStore(String mappingType)
			throws InstantiationException, IllegalAccessException;

}
