jStaticMap
==========

This code moved from SF's staticmap

jStaticMap is singleton Map of Java implementation.

The Feature of jStaticMap are:

- When Map<T> data changing state, client cannot be read a data from Map<T>.
- Should be thread safe.
- Lib user can change a data reader implementation.

If you decide datasource, then you have to implement file data reader.

That reader class should be implement MapDataReader<K, V> interface.

    public class SampleReaderImpl implements MapDataReader<String, String> {
    	@Override
    	public Map<String, String> readMapData() {
    		Map<String, String> map = new HashMap<String, String>();
    
    		// TODO add a key, value to map
    		map.put("testKey", "testValue");
    		map.put("usdkrw", "1020.11");
    
    		return map;
    	}
    }
