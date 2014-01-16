package net.sourceforge.staticmap;

import java.util.HashMap;
import java.util.Map;

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
