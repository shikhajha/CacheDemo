package com.rj.cachedemo.cache;


public interface CacheService<K,V> {
	
	public V get(K k);
	public void put(V v);
	
	
	public boolean remove(K k);
	public void shutDown();
	public void flush();
}
