package com.metaflow.payment.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.metaflow.payment.base.BaseMgr;
import com.metaflow.payment.base.BillMgr;
import com.metaflow.payment.config.ConstantsValue;
import com.metaflow.payment.config.PropUtil;
import com.metaflow.payment.model.BillPayment.BillPayment;
import com.metaflow.payment.model.BillPayment.BillPaymentInfo;
import com.metaflow.payment.model.Common.JobType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description = "Billing Payment Service", produces = "application/json")
@RestController
@RequestMapping(value = "/billPayment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class Payment {

    @ApiOperation(value = "selectListBillPayment", notes = "List of Payment byt due date")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "dueDate", value = "Payment Date", required = true, dataType = "string", paramType = "path", defaultValue = ""),
    })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resources<Resource<BillPayment>>> getBillPayment(@RequestParam(value = "dueDate", required = true,defaultValue = "2018-05-15")  String dueDate) throws IOException {
    	
    	BaseMgr mgr = new BaseMgr();
    	
		PropUtil util = new PropUtil();
    	List<Resource<BillPayment>> list = new ArrayList<>();

    	String listStr = mgr.getVendorBill(dueDate);
		JSONObject jsonObject = util.getJsonObjFromStr(listStr);

		JSONArray billList = (JSONArray) jsonObject.get("bills");
            
        System.out.println(billList);;
        Iterator<JSONObject> iterator1 = billList.iterator();
        while (iterator1.hasNext()) {
          	BillPayment info = new BillPayment();
       		JSONObject billObj = iterator1.next();
            	
       		info.setBillId((String)billObj.get("bill_id"));
       		info.setTransType(new ConstantsValue().TRANSACTIONTYPE[2]);

           	list.add(new Resource<>(info, linkTo(methodOn(Payment.class).getBillPayment(dueDate)).slash(info.getBillId()).withSelfRel()));          
        }  
     	
        Link link = linkTo(methodOn(Payment.class).getBillPayment(dueDate)).withSelfRel();

        Resources<Resource<BillPayment>> resources = new Resources<>(list, link);
        
        return ResponseEntity.ok(resources);
    }


    
    
    @ApiOperation(value = "detailBillPayment", notes = "Information of each Bill Payment")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "billId", value = "Bill ID", required = true, dataType = "string", paramType = "path", defaultValue = ""),
    })
	@RequestMapping(value = "/{billId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public HttpEntity<BillPaymentInfo> getEachBillPayment(@RequestParam(value = "billId", required = true)  String billId) throws JsonProcessingException {
    public HttpEntity<BillPaymentInfo> getEachBillPayment (@PathVariable String billId) throws JsonProcessingException {
   	
    	BillPaymentInfo info = new BillPaymentInfo();
	    BillMgr mgr = new BillMgr();
	    info = mgr.getBillDetailInfo(billId);
	   	
//    	info.add(linkTo(methodOn(PaymentController.class).getEachBillPayment(billId)).slash(info.getBillId()).withSelfRel());
    	info.add(linkTo(methodOn(Payment.class).getEachBillPayment(billId)).withSelfRel());
    	
        return new ResponseEntity<>(info, HttpStatus.OK);
    	
    }
 
    
    
}
