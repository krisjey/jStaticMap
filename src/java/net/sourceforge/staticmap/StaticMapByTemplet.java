package net.sourceforge.staticmap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.sourceforge.staticmap.annotation.GuardedBy;
import net.sourceforge.staticmap.annotation.ThreadSafe;

/**
 * static map implementation class 
 * 
 * read codelist from file, database and make a static map object using loadData method
 * 
 * should implement a loadData() method.
 * 
 * @author kris.j
 * 
 * @param <K, V>
 */
@ThreadSafe
public abstract class StaticMapByTemplet<K, V> implements StaticMap<K, V> {
	protected final Map<K, V> staticMap = new ConcurrentHashMap<K, V>();

	private final ReadWriteLock lock = new ReentrantReadWriteLock(false /* fair */);
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	@Override
	@GuardedBy("this")
	public final int cleanAndLoad() {
		this.writeLock.lock();
		try {
			this.staticMap.clear();
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
	 * read from datasource and set to Map object
	 */
	protected abstract int loadData();
}
