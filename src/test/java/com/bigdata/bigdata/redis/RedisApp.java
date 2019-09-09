package com.bigdata.bigdata.redis;

import com.bigdata.bigdata.utils.RedisUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Author:   zhiqiu
 * Date:     2019-09-09
 */
public class RedisApp {

	private String host = "127.0.0.1";
	private int port = 6379;
	private Jedis jedis;

	@Before
	public void setUp() {
		jedis = new Jedis(host, port);
	}

	@Test
	public void test01() {
		jedis.set("info", "this is pk");
		Assert.assertEquals("this is pk", jedis.get("info"));
	}

	@Test
	public void test02() {
		Jedis jedis = RedisUtils.getJedis();
		Assert.assertEquals("this is pk", jedis.get("info"));
	}

	@After
	public void tearDown() {
		jedis.close();
	}

}
