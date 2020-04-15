package com.bigdata.bigdata.kafak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiqiu
 * @since 2019-11-26
 */
@RequestMapping(value = "/kafka")
@RestController
public class KafkaController {

	@Autowired
	private KafkaSender kafkaSender;

	@GetMapping(value = "/test")
	public Object test() {

		kafkaSender.sendChannelMess("testTopic", "caonima");

		//		SysConfig sysConfig = new SysConfig();
		//		sysConfig.setParamKey("11111");
		//
		//		kafkaSender.sendChannelMap("test2", sysConfig);
		return null;

	}
}
