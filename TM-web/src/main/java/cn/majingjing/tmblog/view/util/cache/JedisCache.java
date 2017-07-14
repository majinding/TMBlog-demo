package cn.majingjing.tmblog.view.util.cache;

import cn.majingjing.core.cache.ITmCache;
import cn.majingjing.core.security.SecurityTools;

import redis.clients.jedis.Jedis;

/**
 * @author majinding888@foxmail.com
 */
public class JedisCache implements ITmCache {

	public static final String KEY_PRE = "TM-JedisCache#";
	private static final String HOST = "59.110.233.xxx";
	private static final int PORT = 6379;
	private Jedis jedis;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCacheClient() {
		return (T) jedis;
	}

	public JedisCache() {
		jedis = new Jedis(HOST, PORT);
	}

	@Override
	public String set(String key, String value) {
		value = SecurityTools.AESEncode(SecurityTools.DEFAULT_KEY, value);
		return jedis.set(getKey(key), value);
	}

	@Override
	public String set(String key, String value, int seconds) {
		value = SecurityTools.AESEncode(SecurityTools.DEFAULT_KEY, value);
		return jedis.setex(getKey(key), seconds, value);
	}

	@Override
	public String get(String key) {
		String val = jedis.get(getKey(key));
		if (null != val) {
			return SecurityTools.AESDncode(SecurityTools.DEFAULT_KEY, val);
		}
		return null;
	}

	@Override
	public String hget(String key, String field) {
		String val = jedis.hget(getKey(key), field);
		if (null != val) {
			return SecurityTools.AESDncode(SecurityTools.DEFAULT_KEY, val);
		}
		return null;
	}

	@Override
	public Long hset(String key, String field, String value) {
		value = SecurityTools.AESEncode(SecurityTools.DEFAULT_KEY, value);
		Long hset = jedis.hset(getKey(key), field, value);
		// jedis.expire(key, 20);
		return hset;
	}

	@Override
	public Long del(String patternKey) {
		String[] keys = jedis.keys(getKey(patternKey)).toArray(new String[0]);
		return keys.length == 0 ? 0L : jedis.del(keys);
	}

	@Override
	public Long clear() {
		String[] keys = jedis.keys(getKey("*")).toArray(new String[0]);
		return keys.length == 0 ? 0L : jedis.del(keys);
	}

	/*************** 私有方法 ***********************/
	private String getKey(String key) {
		return KEY_PRE + key;
	}

}
