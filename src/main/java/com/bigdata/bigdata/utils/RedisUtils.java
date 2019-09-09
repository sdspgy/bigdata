package com.bigdata.bigdata.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Author:   zhiqiu
 * Date:     2019-09-09
 * 懒汉模式下双重校验锁
 */
public class RedisUtils {

	/*一个volatile静态的实例*/
	private volatile static JedisPool jedisPool = null;

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 6379;

	/*私有化构造函数*/
	private RedisUtils() {
	}

	/*给出一个公共的静态方法返回一个单一实例*/
	public static Jedis getJedis() {
		if (jedisPool == null) {
			synchronized (JedisPool.class) {
				if (jedisPool == null) {
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxIdle(10);
					config.setMaxTotal(100);
					config.setMaxWaitMillis(1000);
					config.setTestOnBorrow(true);

					jedisPool = new JedisPool(config, HOST, PORT);
				}
			}
		}
		return jedisPool.getResource();
	}

}
