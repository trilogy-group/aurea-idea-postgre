package com.big;

import com.pantero.runtime.datasource.OperationDatasourceImpl;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
/*
 * Copyright (c) 2004-2014 by Aurea, Inc. All Rights Reserved.
 * All use, reproduction, transfer, publication or disclosure is prohibited
 * except as may be expressly permitted in the applicable license agreement.
 */
/**
 * The primary Java class generated for the BIGXMLDataSource model.
 * Description: <br><br>
 *
 * Generated 2014-08-03 00:51:09.723
 *
 */
public class BIGXMLDataSourceImpl extends OperationDatasourceImpl {
	protected static final Log logger = LogFactory.getLog(com.big.BIGXMLDataSourceImpl.class);

	public BIGXMLDataSourceImpl(){
		super();
	} 

	/**
	 * PostServiceRequest custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return BIG 
 	 */
	public BIG PostServiceRequest(
			BIG Parameter1) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		return (BIG)execute("PostServiceRequest", _args);
		
		//end user defined code for operation
	}

}

