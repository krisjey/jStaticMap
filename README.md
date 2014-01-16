jStaticMap
==========

This code moved from SF's staticmap

jStaticMap is singleton Map of Java implementation.

Very small code, There is two kind of implementation(Template method pattern and Strategy pattern).

The Feature of jStaticMap are:

- When Map<T> data changing state, client cannot be read a data from Map<T>.
- Should be thread safe.
- Lib user can change a data reader implementation.

If you decide datasource, then you have to implement data reader(Strategy pattern).

That reader class should be implement MapDataReader<K, V> interface.

	public class SampleReaderImpl implements MapDataReader<String, String> {
		@Override
		public Map<String, String> readMapData() {
			Map<String, String> map = new HashMap<String, String>();
	
			// TODO add a key, value to map from FILE
			map.put("testKey", "testValue");
			map.put("usdkrw", "1020.11");
	
			return map;
		}
	}
	
Next step, Create a new StaticMapByStrategy instance using SampleReaderImpl class.

	private static StaticMap<String, String> initStaticMap() {
		// @see SampleReader data source dependency in SampleReader class
		StaticMap<String, String> map = new StaticMapByStrategy<String, String>(new SampleReaderImpl());
		return map;
	}

Finally, You can use this values.

	assertNotNull(staticMap);
	assertNotNull(staticMap.get("testKey"));
	
	assertNull(staticMap.get("krwusd"));
	
	assertEquals("1020.11", staticMap.get("usdkrw"));
	

