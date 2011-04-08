/**
 * 
 */
package q.commons.pinyin;

import junit.framework.Assert;

import org.junit.Test;

public class PinyinTest {

	@Test
	public void testGetPinYin() {
		Assert.assertTrue(Pinyin.getPinyin("单田芳").contains("dantianfang"));
		Assert.assertTrue(Pinyin.getPinyin("单田芳").contains("shantianfang"));
		Assert.assertTrue(Pinyin.getPinyin("单田芳cc").contains("shantianfangcc"));
		// FIXME //Assert.assertTrue(Pinyin.getPinyin("单田芳12").contains("shantianfang12"));
	}
	
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			Pinyin.getPinyin("单田芳");
		}
		System.out.println(System.currentTimeMillis() - start);
	}

}
