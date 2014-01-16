package net.sourceforge.staticmap;

/**
 * <p>
 * Title : Static Map interface
 * </p>
 * <p>
 * Description : Static final MAP interface
 * static map implementation class 
 * 
 * read codelist from file, database and make a static map object
 * 
 * should implement a loadData() method
 * 
 * @author kris.j
 * @version 1.0
 * @param <K>
 * @param <V>
 * @date : 2012. 10. 30.
 */
public interface StaticMap<K, V> {
	/**
	 * cleanup static map's value and reload this map's value
	 * @return loaded count
	 */
	public int cleanAndLoad();

	/**
	 * retrieve a value matched by key  
	 * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
	 */
	public V get(K key);
}
