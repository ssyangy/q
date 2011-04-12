package q.serialize.mock.domain;

import java.util.List;

public class PojoArray {

	private Pojo single;

	private List<Pojo> many;

	public Pojo getSingle() {
		return single;
	}

	public void setSingle(Pojo single) {
		this.single = single;
	}

	public List<Pojo> getMany() {
		return many;
	}

	public void setMany(List<Pojo> many) {
		this.many = many;
	}

}