package net.sourceforge.staticmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestStaticMapByStrategy {
	public static final StaticMap<String, String> staticMap = initStaticMap();
	
	/**
	 * @see SampleReaderImpl data source depend on SampleReader => encapsulation
	 * @return
	 */
	private static StaticMap<String, String> initStaticMap() {
		// @see SampleReader data source dependency in SampleReader class
		StaticMap<String, String> map = new StaticMapByStrategy<String, String>(new SampleReaderImpl());
		return map;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		staticMap.cleanAndLoad();
	}

	@Test
	public void testStaticMap() {
		assertNotNull(staticMap);
		assertNotNull(staticMap.get("testKey"));
		
		assertNull(staticMap.get("krwusd"));
		
		assertEquals("1020.11", staticMap.get("usdkrw"));
	}
}
