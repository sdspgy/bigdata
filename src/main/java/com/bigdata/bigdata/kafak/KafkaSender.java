package com.bigdata.bigdata.kafak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhiqiu
 * @since 2019-11-26
 */
@Component
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	/**
	 * 发送消息到kafka
	 */
	public void sendChannelMess(String channel, String message) {
		kafkaTemplate.send(channel, message);
	}

//	public void sendChannelMap(String channel, SysConfig message) {
//		kafkaTemplate.send(channel, JSON.toJSONString(message));
//	}
}
