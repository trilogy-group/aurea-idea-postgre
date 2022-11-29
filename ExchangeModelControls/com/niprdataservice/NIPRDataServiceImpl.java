package com.niprdataservice;

import com.pantero.runtime.dataservice.DataService;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
import com.nipr.schema.Result;
import com.nipr.schema.Getter;
import com.nipr.schema.Gateway;

/*
 * Copyright (c) 2004-2014 by Aurea, Inc. All Rights Reserved.
 * All use, reproduction, transfer, publication or disclosure is prohibited
 * except as may be expressly permitted in the applicable license agreement.
 */
/**
 * The primary Java class generated for the NIPRDataService model.
 * Description: <br><br>
 *
 * Generated 2014-08-03 00:51:09.934
 *
 */
public class NIPRDataServiceImpl extends DataService {
	protected static final Log logger = LogFactory.getLog(NIPRDataServiceImpl.class);

	public NIPRDataServiceImpl() {
		super("NIPRDataService");
	}

	public NIPRDataServiceImpl(String config) {
		super("NIPRDataService", config);
	}


	/**
	 * GetNPN custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return Result 
 	 */
	public Result GetNPN(
			Getter Parameter1) {
		incrementMetric("GetNPN");
		//begin user defined code for operation
		try {
			Result _result = (Result)map(Parameter1, Result.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * GetInformation custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return Result 
 	 */
	public Result GetInformation(
			Getter Parameter1) {
		incrementMetric("GetInformation");
		//begin user defined code for operation
		try {
			Result _result = (Result)map(Parameter1, Result.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * UpdateAppointment custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return Gateway 
 	 */
	public Gateway UpdateAppointment(
			Gateway Parameter1) {
		incrementMetric("UpdateAppointment");
		//begin user defined code for operation
		try {
			Gateway _result = (Gateway)map(Parameter1, Gateway.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

}