package com.metaflow.payment.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metaflow.payment.config.ConstantsValue;
import com.metaflow.payment.model.Common.JobType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description = "Transaction Service", produces = "application/json")
@RestController
@RequestMapping(value = "/transaction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class Transaction {

    @ApiOperation(value = "selectListTransaction", notes = "List of Transaction")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dueDate", value = "Payment Date", required = true, dataType = "string", paramType = "path", defaultValue = ""),
    })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<JobType> getIndex(@RequestParam(value = "dueDate", required = false,defaultValue = "2018-05-15") String dueDate) throws JsonProcessingException {
        JobType jobType = new JobType();
        jobType.setJobType(new ConstantsValue().JOBTYPE[0]);
//        test.add(linkTo(methodOn(Greeting2Controller.class).test()).slash(test.getJobType()).withRel("NEXT"));
        
        for(int i=0; i < new ConstantsValue().TRANSACTIONTITLE.length; i++) {
        	jobType.add(linkTo(methodOn(Transaction.class).getIndex(dueDate)).withRel(new ConstantsValue().TRANSACTIONTITLE[i]).withHref(new ConstantsValue().TRANSACTIONlINK[i]+"?dueDate="+dueDate));
        }

        return new ResponseEntity<>(jobType, HttpStatus.OK);
    }
   
    
}
