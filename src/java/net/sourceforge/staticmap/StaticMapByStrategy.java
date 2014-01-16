package net.sourceforge.staticmap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.sourceforge.staticmap.annotation.GuardedBy;
import net.sourceforge.staticmap.annotation.ThreadSafe;

/**
 * static map manager class
 *
 * make a static map from loaded datasource. ex) database, file, final variable
 *
 * @see MapDataReader<K, V>
 *
 * @author kris.j
 * @param <K, V>
 */
@ThreadSafe
public class StaticMapByStrategy<K, V> implements StaticMap<K, V> {
	protected final Map<K, V> staticMap = new ConcurrentHashMap<K, V>();

	private final ReadWriteLock lock = new ReentrantReadWriteLock(false /* fair */);
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	private final MapDataReader<K, V> reader;

	public StaticMapByStrategy(MapDataReader<K, V> reader) {
		this.reader = reader;
		this.loadData();
	}

	@Override
	@GuardedBy("this")
	public final int cleanAndLoad() {
		this.writeLock.lock();
		try {
			return this.loadData();
		}
		finally {
			this.writeLock.unlock();
		}
	}

	@Override
	@GuardedBy("this")
	public final V get(K key) {
		this.readLock.lock();
		try {
			return this.staticMap.get(key);
		}
		finally {
			this.readLock.unlock();
		}
	}

	/**
	 * read set data from Data source
	 */
	private int loadData() {
		if (this.reader == null) {
			throw new UnsupportedOperationException("There is no DataReader!!");
		}
		else {
			this.staticMap.clear();
			this.staticMap.putAll(this.reader.readMapData());
			return this.staticMap.size();
		}
	}
}
