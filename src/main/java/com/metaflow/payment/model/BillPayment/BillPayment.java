package com.metaflow.payment.model.BillPayment;

import org.springframework.hateoas.ResourceSupport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//@ToString(callSuper = true, includeFieldNames = true)
@ApiModel(value = "BillPayment",description= "Billing key informaiton" )
public @Data class BillPayment extends ResourceSupport{

	@ApiModelProperty(value = "bill id", example = "1256987000000213644")
	private String billId;
	@ApiModelProperty(value = "transaction type", example = "BillPayment")
	private String transType;
	
}
