package com.nipr;


import org.apache.commons.lang.StringUtils;

import com.pantero.metamodel.config.HTTPSourceLocation;
import com.pantero.runtime.PanteroRuntimeException;
import com.pantero.runtime.datasource.OperationDatasourceImpl;
import com.pantero.runtime.metamodel.expressions.library.MiscLibrary;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
import com.nipr.info.PDB;
/*
 * Copyright (c) 2004-2013 by Aurea, Inc. All Rights Reserved.
 * All use, reproduction, transfer, publication or disclosure is prohibited
 * except as may be expressly permitted in the applicable license agreement.
 */
/**
 * The primary Java class generated for the NIPRXMLDataSource model.
 * Description: <br><br>
 *
 * Generated 2013-09-30 12:09:39.993
 *
 */
public class NIPRXMLDataSourceImpl extends OperationDatasourceImpl {
	protected static final Log logger = LogFactory.getLog(com.nipr.NIPRXMLDataSourceImpl.class);

	private static String NIPR_HITLIST	= "hitlist_xml.cgi";
	private static String NIPR_ENTITY	= "entityinfo_xml.cgi";
	
	public NIPRXMLDataSourceImpl(){
		super();
	} 

	private String getIDString(String customerNumber, String pinNumber, String reportType) throws Exception{
		if (StringUtils.isBlank(customerNumber))
			throw new PanteroRuntimeException("NIPR Customer Number is missing");
		if (StringUtils.isBlank(pinNumber))
			throw new PanteroRuntimeException("NIPR ID is missing");
		String retVal = "";
		retVal = "customer_number=" + customerNumber + "&pin_number=" + pinNumber + "&report_type=" + reportType;
		return retVal;
	}

	/**
		 * GetFirmInformation custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return PDB 
	 	 */
		public PDB GetFirmInformation(
				ProducerInfo Parameter1) {
			//begin user defined code for operation
			String prefixURL = com.utilities.Utils.getDataSourceURL(Parameter1);
			logger.info("!!! GetFirmInformation: URL Prefix: " + prefixURL);
			if (StringUtils.isBlank(prefixURL))
				throw new PanteroRuntimeException("GetFirmInformation: cannot read URL from the HTTP source location in the runtime environment.");

			String URLvalue = prefixURL + NIPR_ENTITY;
			MiscLibrary.overrideSourceLocationProperty(Parameter1, HTTPSourceLocation.URL, URLvalue);
			logger.info("!!! GetFirmInformation: using URL: " + URLvalue);
			Parameter1.setReport_type("0");
			
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			return (PDB)execute("GetFirmInformation", _args);
			//end user defined code for operation
		}

	/**
		 * GetFirmNPN custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return com.nipr.info.HITLIST 
	 	 */
		public com.nipr.info.HITLIST GetFirmNPN(
				com.nipr.Producer Parameter1) {
			//begin user defined code for operation
			if (Parameter1.getName_last() != null)
				return null;
			// build the URL to use in HTTP GET
			String prefixURL = com.utilities.Utils.getDataSourceURL(Parameter1);
			if (StringUtils.isBlank(prefixURL))
				throw new PanteroRuntimeException("GetFirmNPN: cannot read URL from the HTTP source location in the runtime environment.");
			String URLvalue = prefixURL + NIPR_HITLIST;
			Parameter1.setReport_type("0");
			
			// no need to overwrite user and password right now
			MiscLibrary.overrideSourceLocationProperty(Parameter1, HTTPSourceLocation.URL, URLvalue);
			logger.info("!!! GetFirmNPN: using URL: " + URLvalue);
			Parameter1.setReport_type("0");

			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			return (com.nipr.info.HITLIST)execute("GetFirmNPN", _args);
			
			//end user defined code for operation
		}

	/**
		 * GetIndividualInformation custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return com.nipr.info.PDB 
	 	 */
		public com.nipr.info.PDB GetIndividualInformation(
				com.nipr.ProducerInfo Parameter1) {
			//begin user defined code for operation
			if (Parameter1.getType().equals("Person")){
				// build the URL to use in HTTP GET
				String prefixURL = com.utilities.Utils.getDataSourceURL(Parameter1);
				if (StringUtils.isBlank(prefixURL))
					throw new PanteroRuntimeException("GetIndividualInformation: cannot read URL from the HTTP source location in the runtime environment.");
				String URLvalue = prefixURL + NIPR_ENTITY;
				MiscLibrary.overrideSourceLocationProperty(Parameter1, HTTPSourceLocation.URL, URLvalue);
				logger.info("!!! GetIndividualInformation: using URL: " + URLvalue);
				Parameter1.setReport_type("1");
				
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				return (com.nipr.info.PDB)execute("GetIndividualInformation", _args);
			}
			return null;
			//end user defined code for operation
		}

	/**
		 * GetIndividualNPN custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return com.nipr.info.HITLIST 
	 	 */
		public com.nipr.info.HITLIST GetIndividualNPN(
				com.nipr.Producer Parameter1) {
			//begin user defined code for operation
			if (Parameter1.getName_last() != null){
				// build the URL to use in HTTP GET
				String prefixURL = com.utilities.Utils.getDataSourceURL(Parameter1);
				if (StringUtils.isBlank(prefixURL))
					throw new PanteroRuntimeException("GetIndividualNPN: cannot read URL from the HTTP source location in the runtime environment.");
				String URLvalue =  prefixURL + NIPR_HITLIST;
				MiscLibrary.overrideSourceLocationProperty(Parameter1, HTTPSourceLocation.URL, URLvalue);
				logger.info("!!! GetIndividualNPN: using URL: " + URLvalue);
				Parameter1.setReport_type("1");
				
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				return (com.nipr.info.HITLIST)execute("GetIndividualNPN", _args);
			}
			return null;
			
			//end user defined code for operation
		}
}

