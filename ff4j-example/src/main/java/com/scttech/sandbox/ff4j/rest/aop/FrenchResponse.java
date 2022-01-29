package com.scttech.sandbox.ff4j.rest.aop;

import org.springframework.stereotype.Component;

@Component("fr")
public class FrenchResponse implements AopResponse {

	@Override
	public String sayHello() {
		return "Bonjour";
	}

}
