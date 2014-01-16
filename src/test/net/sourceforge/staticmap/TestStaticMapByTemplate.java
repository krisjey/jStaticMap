package net.sourceforge.staticmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestStaticMapByTemplate {
	public static final StaticMap<String, String> staticMap = new StaticMapByTempletExample();

	@Test
	public void test() {
		assertNotNull(staticMap);
		assertNotNull(staticMap.get("testKey"));
		
		assertNull(staticMap.get("krwusd"));
		
		assertEquals("1020.11", staticMap.get("usdkrw"));
	}

}
