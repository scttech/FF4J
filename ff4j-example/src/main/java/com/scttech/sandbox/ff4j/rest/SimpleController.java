package com.scttech.sandbox.ff4j.rest;

import javax.annotation.PostConstruct;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.property.PropertyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scttech.sandbox.ff4j.rest.aop.AopResponse;


@RestController
public class SimpleController {
	
	private static final String FEATURE_HELLO = "Hello";
	private static final String FEATURE_GREETING = "GreetingFeature";
    private static final String PROPERTY_USERNAME = "username";
	
	@Autowired
	public FF4j ff4j;
	
	// Inject a class that has methods controlled by features
	@Autowired
	@Qualifier("en")
	private AopResponse greeting;
	
	@PostConstruct
	public void setupFeatures() {
		// Define features and properties via code
		if( !ff4j.exist(FEATURE_HELLO)) {
			ff4j.createFeature(new Feature(FEATURE_HELLO, true));
		}
		if( !ff4j.exist(FEATURE_GREETING)) {
			ff4j.createFeature(new Feature(FEATURE_GREETING, false));
		}
        if (!ff4j.getPropertiesStore().existProperty(PROPERTY_USERNAME)) {
            ff4j.createProperty(new PropertyString(PROPERTY_USERNAME, "testuser"));
        }		
	}

	@GetMapping("/api/simple")
	public String simpleGet() {		
		if (ff4j.check(FEATURE_HELLO)) {
			return "Hello";
		} else {
			return "Goodbye";
		}
		
	}
	
	@GetMapping("/api/aop/simple")
	public String simpleAopGet() {	
		// This will invoke the appropriate method depending on the feature
		return greeting.sayHello();
	}

	@PostMapping("/api/simple")
	public String simplePost(@RequestBody String body) {
		return body;
	}

}
