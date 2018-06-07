package com.metaflow.payment.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

//@Api(basePath = "swagger-demo/person", value = "Person", description = "Operations with person", produces = "application/json")
//@RestController
//@RequestMapping(value = "swagger-demo/person", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {
	@RequestMapping("/test")
	public String index() {
		return "helloworld!";
	}
	
//	@RequestMapping(method = RequestMethod.POST, value = "/create")
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ApiOperation(value = "Get Person", notes = "Fetch List of Person")
//    @ApiResponses(value = [
//            @ApiResponse(code = 400, message = "Fields are with validation errors"),
//            @ApiResponse(code = 201, message = "List"),
//            @ApiResponse(code = 500, message = "Error occurred while fetching Person")
//    ])
//	
//    ResponseEntity create(@RequestBody PersonCO co) {
//        return new ResponseEntity(new Person(firstName: co.firstName, lastName: co.lastName, age: co.age), HttpStatus.OK)
//    }	
}