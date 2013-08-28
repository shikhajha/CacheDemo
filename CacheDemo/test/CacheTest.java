import java.util.Date;

import org.junit.After;
import org.junit.Test;

import com.rj.cachedemo.cache.CacheService;
import com.rj.cachedemo.cache.TradeEHCacheService;
import com.rj.cachedemo.model.Trade;

public class CacheTest {

	private final CacheService<Long, Trade> tradeEhCacheService = new TradeEHCacheService();
	private final long totalElement = 500000;

	@Test
	public void ehCachePutTest() {

		long startTime = System.currentTimeMillis();
		for (long uid = 0; uid < totalElement; uid++) {
			tradeEhCacheService.put(getTrade(uid));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken to add " + totalElement + " Element: "
				+ (endTime - startTime));

	}

	//@Test
	public void ehCacheGetTest() {
		long startTime = System.currentTimeMillis();
		for (long uid = 0; uid < totalElement; uid++) {
			Trade t = tradeEhCacheService.get(Long.valueOf(uid));
			if(t == null || t.getUid() != uid)
			{
				System.out.println("Trade for uid: "+uid+" not read correctly");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken to get " + totalElement + " Element: "
				+ (endTime - startTime));

	}

	@After
	public void shutDown() {
		tradeEhCacheService.shutDown();
	}

	private Trade getTrade(long uid) {
		Trade trade = new Trade(uid);
		StringBuilder builder = new StringBuilder();
		builder.append("1 This is only a test trade uid"+uid+". The idea is to make it as big as possible so that we could check the performance of the caching service. Hope this test passes!!");
		builder.append("2 This is only a test trade. uid"+uid+" The idea is to make it as big as possible so that we could check the performance of the caching service. Hope this test passes!!");
		builder.append("3 This is only a test trade. uid"+uid+"The idea is to make it as big as possible so that we could check the performance of the caching service. Hope this test passes!!");
		builder.append("4 This is only a test trade. uid"+uid+"The idea is to make it as big as possible so that we could check the performance of the caching service. Hope this test passes!!");
		builder.append("5 This is only a test trade. uid"+uid+"The idea is to make it as big as possible so that we could check the performance of the caching service. Hope this test passes!!");
		
		trade.setDescription(builder.toString());
		
		StringBuilder summaryBuilder = new StringBuilder();
		summaryBuilder.append("1 Another summary for uid"+uid+"trying to increase the amount of data file in cache to check performance.");
		summaryBuilder.append("2 Another summary for uid"+uid+"trying to increase the amount of data file in cache to check performance.");
		summaryBuilder.append("3 Another summary for uid"+uid+"trying to increase the amount of data file in cache to check performance.");
		summaryBuilder.append("4 Another summary for uid"+uid+"trying to increase the amount of data file in cache to check performance.");
		summaryBuilder.append("5 Another summary for uid"+uid+"trying to increase the amount of data file in cache to check performance.");
		
		
		trade.setExpiry(new Date());
		trade.setMaturity(new Date());
		trade.setPayNotional(new Double(12345678424.0));
		trade.setRecNotional(new Double(2498732984732.0));
		trade.setProductGroup(new String("Interest Rate Swap"));
		return trade;

	}
}
