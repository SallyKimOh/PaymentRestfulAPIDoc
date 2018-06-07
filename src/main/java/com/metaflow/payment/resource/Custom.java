package com.metaflow.payment.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

//@RestController
//@RequestMapping("/abc")
public class Custom {
    @RequestMapping(value = "/custom1", method = RequestMethod.GET, headers ="ADSFAD"  )
    public String custom() {
        return "custom";
    }
    
    	@RequestMapping(value="/ab", method = RequestMethod.GET)
    	public String index() {
    		return "helloworld!";
    	}
    	  
        @RequestMapping(value = "/test2", method = RequestMethod.GET)
        public String test1() {
            return "custom";
        }
   
}
