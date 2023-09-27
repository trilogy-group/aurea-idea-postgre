package NIPRGatewayDataSource;


import com.pantero.runtime.datasource.OperationDatasourceImpl;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
import com.nipr.NIPR;
/*
 * Copyright (c) 2004-2013 by Aurea, Inc. All Rights Reserved.
 * All use, reproduction, transfer, publication or disclosure is prohibited
 * except as may be expressly permitted in the applicable license agreement.
 */
/**
 * The primary Java class generated for the NIPRGatewayDataSource model.
 * Description: <br><br>
 *
 * Generated 2013-10-09 10:47:19.15
 *
 */
public class NIPRGatewayDataSourceImpl extends OperationDatasourceImpl {
	protected static final Log logger = LogFactory.getLog(NIPRGatewayDataSourceImpl.class);

	public NIPRGatewayDataSourceImpl(){
		super();
	}

	/**
		 * UpdateGateway custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return NIPR 
	 	 */
		public NIPR UpdateGateway(
				NIPR Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			return (NIPR)execute("UpdateGateway", _args);
			
			//end user defined code for operation
		} 

}

