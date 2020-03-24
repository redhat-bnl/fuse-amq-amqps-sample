package com.redhat.demo.rahmed;


import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



@Component
@ConfigurationProperties(prefix = "topic")
public class ConsumerTopic extends RouteBuilder {
	private String name;
	private String subcsribtionName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubcsribtionName() {
		return subcsribtionName;
	}

	public void setSubcsribtionName(String subcsribtionName) {
		this.subcsribtionName = subcsribtionName;
	}
	public Map<String, Object> processDummyJMSMessage() {

		Map<String, Object> map = new HashMap<String, Object>();

		//Object body = (Object) exchange.getIn().getBody();

		// map.put("ERROR_ID",111);
		// map.put("ERROR_CODE",headers.get("ERROR-CODE"));
		// map.put("ERROR_MESSAGE",headers.get("ERROR-MESSAGE"));
		// map.put("MESSAGE", body.toString());
		// map.put("STATUS", "ERROR");
		map.put("ID", "1");
		map.put("MESSAGE_ATTRIBUTE_1", "asdsad");
		map.put("MESSAGE_ATTRIBUTE_2", "asdasddsasad");
		return map;
	}
	
	@Override
	public void configure() {
		from("timer:demo?period=3000").routeId("route-amqp-timer").tracing()
			//I need to create a JMS
	 		//.bean(ConsumerTopic.class, "processDummyJMSMessage()")
		     .setBody (simple ("Hello World !!"))
	 		//.setExchangePattern(ExchangePattern.InOnly)
	 		.log("target Topic is amqp:topic:"+getName())
	 		.to("amqp:topic:"+getName())		
	 		.transform(constant("OK Message Sent"))
         .end();

		
		

	}
}
