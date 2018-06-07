package com.metaflow.payment.model.Common;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

public @Data class JobType extends ResourceSupport{
	
	private String jobType;
}
