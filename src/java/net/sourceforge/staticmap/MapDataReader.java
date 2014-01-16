package net.sourceforge.staticmap;

import java.util.Map;

public interface MapDataReader<K, V> {
	/**
	 * read data from datasource and set map data 
	 *  
	 * @return map
	 */
	public Map<K, V> readMapData();
}
