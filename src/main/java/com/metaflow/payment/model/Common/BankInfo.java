package com.metaflow.payment.model.Common;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

public @Data class BankInfo extends ResourceSupport{
	
	private String financialNumber;
	private String branchNumber;
	private String accountNumber;
//	private String accountID;
//	private String accountMemo;
	private double amount;
	private String transDate;
	

}
