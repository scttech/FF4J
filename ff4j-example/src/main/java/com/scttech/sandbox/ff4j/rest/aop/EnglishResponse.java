package com.scttech.sandbox.ff4j.rest.aop;

import org.springframework.stereotype.Component;

@Component("en")
public class EnglishResponse implements AopResponse {

	@Override
	public String sayHello() {
		return "Hello";
	}

}
