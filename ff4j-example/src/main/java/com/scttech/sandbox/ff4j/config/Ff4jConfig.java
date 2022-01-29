package com.scttech.sandbox.ff4j.config;

import java.io.InputStream;

import org.ff4j.FF4j;
import org.ff4j.conf.FF4jConfiguration;
import org.ff4j.conf.XmlParser;
import org.ff4j.springjdbc.store.EventRepositorySpringJdbc;
import org.ff4j.springjdbc.store.FeatureStoreSpringJdbc;
import org.ff4j.springjdbc.store.PropertyStoreSpringJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
 
@Configuration
public class Ff4jConfig {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
    
    @Bean
    public FF4j getFF4j() {
        FF4j ff4j = new FF4j();

        // Load some default features
        InputStream dataset = FF4j.class.getClassLoader().getResourceAsStream("ff4j-cli-features.xml");
        FF4jConfiguration initConfig = new XmlParser().parseConfigurationFile(dataset);
        
        /* 
         * Implementation of each store. Here this is boiler plate as if nothing 
         * is specified the inmemory is used. Those are really the one that will
         * change depending on your technology.
         */
        
        /*
        ff4j.setFeatureStore(new InMemoryFeatureStore(initConfig));        
        ff4j.setPropertiesStore(new InMemoryPropertyStore(initConfig));
        ff4j.setEventRepository(new InMemoryEventRepository());
        */
                
        ff4j.setFeatureStore(new FeatureStoreSpringJdbc(jdbcTemplate.getDataSource()));        
        ff4j.setPropertiesStore(new PropertyStoreSpringJdbc(jdbcTemplate.getDataSource()));
        ff4j.setEventRepository(new EventRepositorySpringJdbc(jdbcTemplate.getDataSource()));       
                
        // Enabling audit and monitoring, default value is false
        ff4j.audit(true);
 
        // When evaluting not existing features, ff4j will create then but disabled 
        ff4j.autoCreate(true);
  
        // To define a cacher layer to relax the DB, multiple implementations
        //ff4j.cache([a cache Manager]);
        
        return ff4j;
    }
}
