package com.scttech.sandbox.ff4j.rest.aop;

import org.ff4j.aop.Flip;

public interface AopResponse {

	/**
	 * This method is controlled by the GreetingFeature, when it is enabled the Component marked with "fr" will execute
	 * in this case FrenchResponse is annotated with @Component("fr")
	 * @return
	 */
	@Flip(name = "GreetingFeature", alterBean = "fr")
	String sayHello();
	
}


