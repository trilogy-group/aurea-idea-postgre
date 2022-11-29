package com.big;


import com.pantero.runtime.dataservice.DataService;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
import com.bigschema.BIGAcknowledgement;
import com.bigschema.BIGRequest;
import com.bigschema.BIGResponseAcknowledgement;
import com.bigschema.BIGResponse;

/*
 * Copyright (c) 2004-2013 by Aurea, Inc. All Rights Reserved.
 * All use, reproduction, transfer, publication or disclosure is prohibited
 * except as may be expressly permitted in the applicable license agreement.
 */
/**
 * The primary Java class generated for the BIGDataService model.
 * Description: <br><br>
 *
 * Generated 2013-09-11 10:28:23.328
 *
 */
public class BIGDataServiceImpl extends DataService {
	protected static final Log logger = LogFactory.getLog(BIGDataServiceImpl.class);

	public BIGDataServiceImpl() {
		super("BIGDataService");
	}

	public BIGDataServiceImpl(String config) {
		super("BIGDataService", config);
	}

	/**
		 * PostServiceRequest custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return com.bigschema.BIGAcknowledgement 
	 	 */
		public com.bigschema.BIGAcknowledgement PostServiceRequest(
				com.bigschema.BIGRequest Parameter1) {
			incrementMetric("PostServiceRequest");
			//begin user defined code for operation
			try {
				com.bigschema.BIGAcknowledgement _result = (com.bigschema.BIGAcknowledgement)map(Parameter1, com.bigschema.BIGAcknowledgement.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * Convert custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return com.bigschema.BIGResponse 
	 	 */
		public com.bigschema.BIGResponse Convert(
				com.big.BIG Parameter1) {
			incrementMetric("Convert");
			//begin user defined code for operation
			try {
				com.bigschema.BIGResponse _result = (com.bigschema.BIGResponse)map(Parameter1, com.bigschema.BIGResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}



}