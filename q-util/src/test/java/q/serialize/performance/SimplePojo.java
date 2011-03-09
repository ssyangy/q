package q.serialize.performance;

import java.util.Date;

public class SimplePojo {

	private boolean booleanMember;

	public boolean getBooleanMember() {
		return booleanMember;
	}

	public void setBooleanMember(boolean booleanMember) {
		this.booleanMember = booleanMember;
	}

	private int intMember;

	public int getIntMember() {
		return intMember;
	}

	public void setIntMember(int intMember) {
		this.intMember = intMember;
	}

	private String stringMember;

	public String getStringMember() {
		return stringMember;
	}

	public void setStringMember(String stringMember) {
		this.stringMember = stringMember;
	}
	
	private char charMember;

	public char getCharMember() {
		return charMember;
	}

	public void setCharMember(char charMember) {
		this.charMember = charMember;
	}
	
	private long longMember;

	public long getLongMember() {
		return longMember;
	}

	public void setLongMember(long longMember) {
		this.longMember = longMember;
	}
	
	private float floatMember;

	public float getFloatMember() {
		return floatMember;
	}

	public void setFloatMember(float floatMember) {
		this.floatMember = floatMember;
	}
	
	private double doubleMember;

	public double getDoubleMember() {
		return doubleMember;
	}

	public void setDoubleMember(double doubleMember) {
		this.doubleMember = doubleMember;
	}
	
	private byte byteMember;

	public byte getByteMember() {
		return byteMember;
	}

	public void setByteMember(byte byteMember) {
		this.byteMember = byteMember;
	}
	
	private short shortMember;

	public short getShortMember() {
		return shortMember;
	}

	public void setShortMember(short shortMember) {
		this.shortMember = shortMember;
	}
	
	private Date dateMember;

	public Date getDateMember() {
		return dateMember;
	}

	public void setDateMember(Date dateMember) {
		this.dateMember = dateMember;
	}

	@Override
	public String toString() {
		return "SimplePojo [booleanMember=" + booleanMember + ", byteMember="
				+ byteMember + ", charMember=" + charMember + ", dateMember="
				+ dateMember + ", doubleMember=" + doubleMember
				+ ", floatMember=" + floatMember + ", intMember=" + intMember
				+ ", longMember=" + longMember + ", shortMember=" + shortMember
				+ ", stringMember=" + stringMember + "]";
	}

	
}