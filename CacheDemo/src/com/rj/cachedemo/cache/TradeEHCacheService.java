package com.rj.cachedemo.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.Status;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import com.rj.cachedemo.model.Trade;

public class TradeEHCacheService implements CacheService<Long, Trade> {
	private final CacheManager cacheManager;
	private final Cache tradeCache;

	public TradeEHCacheService() {
		Configuration cacheManagerConfig = new Configuration()
				.diskStore(new DiskStoreConfiguration()
						.path("D:\\shikha\\java\\workspace\\TestData\\EhCache\\TradeCache"));

		cacheManager = new CacheManager(cacheManagerConfig);
		CacheConfiguration cacheOnfiguration = new CacheConfiguration(
				"tradeCache", 10000)
				.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
				.overflowToDisk(true).eternal(true).diskPersistent(true)
				.diskExpiryThreadIntervalSeconds(0);
		cacheOnfiguration.setMaxElementsOnDisk(0); //unlimited
		

		tradeCache = new Cache(cacheOnfiguration);
		cacheManager.addCache(tradeCache);

	}

	@Override
	public Trade get(Long uid) {
		Element element = tradeCache.get(uid);
		if (element != null) {
			return (Trade) element.getObjectValue();
		}
		return null;
	}

	@Override
	public void put(Trade trade) {
		Long uid = trade.getUid();
		Element element = new Element(uid, trade);
		tradeCache.put(element);
	}

	@Override
	public boolean remove(Long uid) {
		return tradeCache.remove(uid);
	}

	@Override
	public void shutDown() {
		this.flush();

		System.out.println("Shutting down cache manager");
		if (cacheManager != null) {
			cacheManager.shutdown();
		}

	}

	@Override
	public void flush() {
		tradeCache.flush();
		if (cacheManager != null
				&& cacheManager.getStatus() == Status.STATUS_ALIVE) {
			cacheManager.shutdown();
		}

	}

}
