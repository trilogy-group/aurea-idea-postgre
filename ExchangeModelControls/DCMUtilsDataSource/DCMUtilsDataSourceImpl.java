package DCMUtilsDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.vfs.FileObject;

import com.aurea.dcm.DCMClassLoaderHelper;
import com.pantero.metamodel.ExchangeModel;
import com.pantero.metamodel.models.DataServiceModel;
import com.pantero.metamodel.operations.DataServiceOperation;
import com.pantero.runtime.datasource.OperationDatasourceImpl;
import com.pantero.runtime.metamodel.expressions.library.MiscLibrary;
import com.pantero.util.io.FileUtils;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
import com.utilities.GetPartyContactPointEmailList;

import dcm.utils.BIGBackgroundCheckRequest;
import dcm.utils.BIGBackgroundCheckResponse;
import dcm.utils.ChildPositionTypeRequest;
import dcm.utils.ChildPositionTypeResponse;
import dcm.utils.DownloadUpdateFromPDBRequest;
import dcm.utils.DownloadUpdateFromPDBResponse;
import dcm.utils.EmailResponse;
import dcm.utils.Error;
import dcm.utils.GenericRequest;
import dcm.utils.GenericResponse;
import dcm.utils.HierarchyPositionRequest;
import dcm.utils.HierarchyPositionResponse;
import dcm.utils.LOAData;
import dcm.utils.NPNRequest;
import dcm.utils.NPNResponse;
import dcm.utils.PartyInput;
import dcm.utils.PositionData;
import dcm.utils.PositionDetailedRequest;
import dcm.utils.PositionDetailedResponse;
import dcm.utils.PositionHolderData;
import dcm.utils.setupDCMDataRequest;
import dcm.utils.setupDCMDataResponse;
import dcm.utils.UplinePartyResponse;
import dcm.utils.UplinePartyRequest;
import dcm.utils.PartyChannelEligibilityResponse;
import dcm.utils.PartyChannelEligibilityInputs;
import dcm.utils.ActiveProductProfileResponse;
import dcm.utils.ActiveProductProfileRequest;
import dcm.utils.LOAResponse;
import dcm.utils.LOARequest;
import dcm.utils.PartyStatusResponse;
import dcm.utils.PartyStatusRequest;
import dcm.utils.RootAPPartyResponse;
import dcm.utils.RootAPPartyRequest;
import dcm.utils.OrgPrincipalResponse;
import dcm.utils.OrgPrincipalRelation;
import dcm.utils.OrgPrincipalRequest;
/*
 * Copyright (c) 2004-2014 by Aurea, Inc. All Rights Reserved.
 * All use, reproduction, transfer, publication or disclosure is prohibited
 * except as may be expressly permitted in the applicable license agreement.
 */
/**
 * The primary Java class generated for the DCMUtilsDataSource model.
 * Description: <br><br>
 *
 * Generated 2014-08-18 23:05:18.602
 *
 */
public class DCMUtilsDataSourceImpl extends OperationDatasourceImpl {
	protected static final Log logger = LogFactory.getLog(DCMUtilsDataSourceImpl.class);

	public DCMUtilsDataSourceImpl(){
		super();
	} 


	private EmailResponse getEmailsFromDCM(PartyInput parameter1, String operationName) {
		EmailResponse response = new EmailResponse();

		String partyID = parameter1.getPartyID();
		String partyGID = parameter1.getPartyGID();
		if((partyID == null || "".equals(partyID)) && (partyGID == null || "".equals(partyGID))) {
			logger.debug("Both PartyID and PartyGID are null or blank");
			return response;
		}

		logger.debug("Getting implementation class for " + operationName + " using DCMClassLoaderHelper");
		String implClassName = getImplClassName(parameter1, operationName);
		if(implClassName == null) {
			logger.debug("Implementation Class not specified in config.properties file");
			return response;
		}
		logger.debug("Implementation class: " + implClassName);

		Object retVal = null;
		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();

		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

		try {
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			Class clz = dcmClassLoader.loadClass(implClassName);
			Method method;
			Object service;
			if("GetCorrespondenceEmails".equals(operationName)){
				method = clz.getMethod("getEmailAddresses", new Class[] {String.class, String.class, String.class, Date.class});
				service = clz.newInstance();
				retVal = method.invoke(service, new Object[] {partyID, parameter1.getCoverLetterEmailAddress(),parameter1.getPositionGID(),parameter1.getContractEffectiveDate()});
			} else {
				response.setEmails(GetPartyContactPointEmailList.getContactPointEmails(partyID));
				return response;		
			}

		} catch (Exception e) {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION : " + sw.toString());			
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		response.setEmails((Vector) retVal);
		return response;		
	}	

	private String getImplClassName(PartyInput parameter1, String operationName) {

		ExchangeModel exModel = parameter1._getEntityType() == null ? null : parameter1._getEntityType().getExchangeModel();
		FileObject projectDirectory = exModel.getProject().getRootDirectory();

		try {
			FileObject configFile = projectDirectory.getChild("config.properties");
			Properties configProp = new Properties();
			configProp.load(FileUtils.getInputStream(configFile));
			return configProp.getProperty(operationName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getImplClassName(GenericRequest parameter1, String operationName) {

		ExchangeModel exModel = parameter1._getEntityType() == null ? null : parameter1._getEntityType().getExchangeModel();
		FileObject projectDirectory = exModel.getProject().getRootDirectory();

		try {
			FileObject configFile = projectDirectory.getChild("config.properties");
			Properties configProp = new Properties();
			configProp.load(FileUtils.getInputStream(configFile));
			return configProp.getProperty(operationName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * RetrieveEmails custom method
	 * Description: 
	 * @param Parameter1 
	 * @return EmailResponse 
	 */
	public EmailResponse RetrieveEmails(
			PartyInput Parameter1) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("RetrieveEmails", _args);

		logger.info("!!! RetrieveEmails ");

		String callingOperationName = getCallingOperationName(Parameter1);
		return getEmailsFromDCM(Parameter1, callingOperationName);

		//end user defined code for operation
	}


	private String getCallingOperationName(PartyInput parameter) {
		ExchangeModel exModel = parameter._getEntityType() == null ? null : parameter._getEntityType().getExchangeModel();
		DataServiceModel dsm = exModel.getDataServiceModel("IPMDataService");
		DataServiceOperation getAgencyEmailsOp = dsm.getOperation("GetAgencyEmailsForAgent");
		DataServiceOperation getAgentEmailsOp = dsm.getOperation("GetAgentEmailsForAgency");
		DataServiceOperation getPartyEmailsOp = dsm.getOperation("GetPartyEmails");
		DataServiceOperation getCorrespondenceEmailOp = dsm.getOperation("GetCorrespondenceEmails");

		if(MiscLibrary.isCurrentDataServiceOperation(getAgencyEmailsOp)) {
			return "GetAgencyEmailsForAgent";
		} else if(MiscLibrary.isCurrentDataServiceOperation(getAgentEmailsOp)) {
			return "GetAgentEmailsForAgency";
		} else if(MiscLibrary.isCurrentDataServiceOperation(getPartyEmailsOp)) {
			return "GetPartyEmails";
		} else if(MiscLibrary.isCurrentDataServiceOperation(getCorrespondenceEmailOp)) {
			return "GetCorrespondenceEmails";
		}

		return "";
	}

	/**
	 * GetNPNFromNIPR custom method
	 * Description: 
	 * @param Parameter1 
	 * @return NPNResponse 
	 */
	public NPNResponse GetNPNFromNIPR(NPNRequest Parameter1) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("GetNPNFromNIPR", _args);

		return getNPNFromNIPR_UsingDCM(Parameter1);
		//end user defined code for operation
	}


	private NPNResponse getNPNFromNIPR_UsingDCM(NPNRequest parameter1) {
		String retVal = null;
		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();

		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		Vector errors = new Vector();

		try {
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			Class clz = dcmClassLoader.loadClass("com.trilogy.fs.dms.core.validator.utility.PDBUtilities");
			Method method = clz.getMethod("getPartyFromPDB", new Class[] {String.class, String.class, String.class, Vector.class});
			//Object service = clz.newInstance();
			retVal = (String) method.invoke(null, new Object[] {parameter1.getPartyType(), parameter1.getTaxID(), parameter1.getName(), errors});
		} catch (Exception e) {
			errors.add(e.getMessage());
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION : " + sw.toString());
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		return createNPNResponse(retVal, errors);
	}


	private NPNResponse createNPNResponse(String retVal, Vector errors) {
		NPNResponse npnResponse = new NPNResponse();
		if(retVal != null) {
			npnResponse.setNPN(retVal);
		}	
		for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
			String errMsg = (String) iterator.next();
			Error errObj = new Error();
			errObj.setDescription(errMsg);
			npnResponse.addErrors(errObj);
		}
		return npnResponse;
	}


	/**
	 * DownloadUpdateFromPDB custom method
	 * Description: 
	 * @param Parameter1 
	 * @return DownloadUpdateFromPDBResponse 
	 */
	public DownloadUpdateFromPDBResponse DownloadUpdateFromPDB(
			DownloadUpdateFromPDBRequest Parameter1) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("DownloadUpdateFromPDB", _args);
		return downloadUpdateFromPDB_UsingDCM(Parameter1);
		//end user defined code for operation
	}


	private DownloadUpdateFromPDBResponse downloadUpdateFromPDB_UsingDCM(DownloadUpdateFromPDBRequest parameter1) {
		Boolean retVal = null;
		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();

		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		Vector errors = new Vector();

		try {
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			Class clz = dcmClassLoader.loadClass("com.trilogy.awc.core.UpdatePartyUtility");
			Method method = clz.getMethod("updateParty", new Class[] {String.class, Vector.class});			
			//Object service = clz.newInstance();
			retVal = (Boolean) method.invoke(null, new Object[] {parameter1.getPartyID(), errors});
		} catch (Exception e) {
			errors.add(e.getMessage());
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION : " + sw.toString());
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		return createDownloadUpdateFromPDBResponse(retVal, errors);
	}


	private DownloadUpdateFromPDBResponse createDownloadUpdateFromPDBResponse(Boolean retVal, Vector errors) {
		DownloadUpdateFromPDBResponse response = new DownloadUpdateFromPDBResponse();
		response.setSuccess(retVal.booleanValue());
		for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
			String errMsg = "";
			Object errObjFromDCM = iterator.next();
			try {
				errMsg = (String) errObjFromDCM;
			} catch (ClassCastException ccex) {
				DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
				ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
				try {
					Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
					Class clz = dcmClassLoader.loadClass("com.trilogy.fs.dms.pdb.PDBValidatorError");
					if(clz.isInstance(errObjFromDCM)) {
						logger.debug("Error instance of PDBValError");
						Method method = clz.getMethod("toString", new Class[] {});
						//Object service = clz.newInstance();
						String pdbErrorMsg = (String) method.invoke(errObjFromDCM, new Object[] {});
						logger.debug("PDBErrorMsg = " + pdbErrorMsg);
						errMsg = pdbErrorMsg;
					} else {
						logger.debug("Error NOT instance of PDBValError");
					}

				} catch (Exception e) {
					errors.add(e.getMessage());
					Thread.currentThread().setContextClassLoader(oldClassLoader);
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					logger.debug("NEW EXCEPTION : " + sw.toString());
				} finally {
					Thread.currentThread().setContextClassLoader(oldClassLoader);
				}

			}
			Error errObj = new Error();
			errObj.setDescription(errMsg);
			response.addAlerts(errObj);
		}
		return response;
	}

	/**
	 * CheckPartyStatusinDCM custom method
	 * Description: 
	 * @param Parameter1 
	 * @return GenericResponse 
	 */
	public GenericResponse CheckPartyStatusinDCM(
			GenericRequest Parameter1) {
		//begin user defined code for operation
		logger.debug("Enterting CheckPartyStatusinDCM");
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("CheckPartyStatusinDCM", _args);;

		String classNametoCall = getCallingClassNameForPartyStatus(Parameter1);
		String functiontoCall = getCallingFunctionNameForPartyStatus(Parameter1);
		return getPartyStatus_UsingDCM(Parameter1,classNametoCall,functiontoCall);				
		//end user defined code for operation				
	}

	private GenericResponse getPartyStatus_DefaultValues(GenericRequest parameter1, String functionNametoCall) {
		GenericResponse res = new GenericResponse();
		res.setResultParam2("N/A");
		res.setResultParam1("N/A");
		res.setResultParam3(true);
		res.setResultParam4(true);
		return res;
	}

	private GenericResponse getPartyStatus_UsingDCM(GenericRequest parameter1, String classNametoCall, String functionNametoCall) {
		HashMap retVal = null;
		String classtoCall = getImplClassName(parameter1, classNametoCall);
		if(classtoCall==null)
			return getPartyStatus_DefaultValues(parameter1,functionNametoCall);
		String functiontoCall = functionNametoCall;
		logger.debug("ClasName:" + classtoCall);
		logger.debug("FunctionName:" + functiontoCall);
		logger.debug("PartyID:" + parameter1.getPartyID());
		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();

		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		Vector errors = new Vector();

		try {
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			Class clz = dcmClassLoader.loadClass(classtoCall);
			Method method;
			Object service;
			if("checkEOAndBCWaived".equals(functionNametoCall)){
				method = clz.getMethod(functiontoCall, new Class[] {String.class, Date.class});
				service = clz.newInstance();
				retVal = (HashMap)method.invoke(service, new Object[] {parameter1.getPositionGID(),parameter1.getContractEffectivedate()});
			} else {
				method = clz.getMethod(functiontoCall, new Class[] {String.class});
				service = clz.newInstance();
				retVal = (HashMap)method.invoke(service, new Object[] {parameter1.getPartyID()});
			}										
		} catch (Exception e) {
			errors.add(e.getMessage());
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION IS - : " + sw.toString());
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		if(retVal!=null){
			GenericResponse res = new GenericResponse();
			res.setResultParam2(retVal.containsKey("Result")?(String)retVal.get("Result"):"");
			res.setResultParam1(retVal.containsKey("Reason")?(String)retVal.get("Reason"):"");
			res.setResultParam3(retVal.containsKey("EOWaived")?(boolean)retVal.get("EOWaived"):false);
			res.setResultParam4(retVal.containsKey("BCExempt")?(boolean)retVal.get("BCExempt"):false);
			res.setResultParam5(retVal.containsKey("JITExempt")?(boolean)retVal.get("JITExempt"):false);
			return res;
		}

		return null;
	}

	private String getCallingClassNameForPartyStatus(GenericRequest parameter) {
		ExchangeModel exModel = parameter._getEntityType() == null ? null : parameter._getEntityType().getExchangeModel();
		DataServiceModel dsm = exModel.getDataServiceModel("IPMDataService");
		DataServiceOperation GetCancelledin90daysOp = dsm.getOperation("CheckCancelledin90days");
		DataServiceOperation GetcheckPartyRecontractabilityOp = dsm.getOperation("CheckPartyRecontractability");
		DataServiceOperation checkEOAndBCWaivedOp = dsm.getOperation("checkEOAndBCWaived");

		if(MiscLibrary.isCurrentDataServiceOperation(GetCancelledin90daysOp)) {
			return "CheckCancelledin90days";
		} else if(MiscLibrary.isCurrentDataServiceOperation(GetcheckPartyRecontractabilityOp)) {
			return "CheckPartyRecontractability";
		} else if(MiscLibrary.isCurrentDataServiceOperation(checkEOAndBCWaivedOp)){
			return "checkEOAndBCWaived";
		}

		return "";
	}

	private String getCallingFunctionNameForPartyStatus(GenericRequest parameter) {
		ExchangeModel exModel = parameter._getEntityType() == null ? null : parameter._getEntityType().getExchangeModel();
		DataServiceModel dsm = exModel.getDataServiceModel("IPMDataService");
		DataServiceOperation GetCancelledin90daysOp = dsm.getOperation("CheckCancelledin90days");
		DataServiceOperation GetcheckPartyRecontractabilityOp = dsm.getOperation("CheckPartyRecontractability");
		DataServiceOperation checkEOAndBCWaivedOp = dsm.getOperation("checkEOAndBCWaived");

		if(MiscLibrary.isCurrentDataServiceOperation(GetCancelledin90daysOp)) {
			return "isCancelledin90Days";
		} else if(MiscLibrary.isCurrentDataServiceOperation(GetcheckPartyRecontractabilityOp)) {
			return "checkPartyRecontractability";
		} else if(MiscLibrary.isCurrentDataServiceOperation(checkEOAndBCWaivedOp)){
			return "checkEOAndBCWaived";
		}

		return "";
	}


	/**
	 * GetChildPositionTypes custom method
	 * Description: 
	 * @param Parameter1 
	 * @return ChildPositionTypeResponse 
	 */
	public ChildPositionTypeResponse GetChildPositionTypes(
			ChildPositionTypeRequest Parameter1) {
		//begin user defined code for operation
		Object retVal = null;
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("GetChildPositionTypes", _args);

		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		Vector errors = new Vector();

		try{
			ExchangeModel exModel = Parameter1._getEntityType() == null ? null : Parameter1._getEntityType().getExchangeModel();
			FileObject projectDirectory = exModel.getProject().getRootDirectory();
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			FileObject configFile = projectDirectory.getChild("config.properties");
			Properties configProp = new Properties();
			configProp.load(FileUtils.getInputStream(configFile));
			Class clz = dcmClassLoader.loadClass(configProp.getProperty("GetChildPositionTypes"));
			Method method = clz.getMethod("getChildPositions", new Class[] {String.class, String.class});
			Object service = clz.newInstance();
			retVal = method.invoke(service, new Object[] {Parameter1.getPositionType(), Parameter1.getHierarchyType()});

		}  catch (Exception e) {
			errors.add(e.getMessage());
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION IS - : " + sw.toString());
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		ChildPositionTypeResponse response = new ChildPositionTypeResponse();
		response.setPositionType((Vector) retVal);
		return response;

		//end user defined code for operation
	}


	/**
	 * BIGUtilityFunction custom method
	 * Description: 
	 * @param Parameter1 
	 * @return BIGBackgroundCheckResponse 
	 */
	public BIGBackgroundCheckResponse BIGUtilityFunction(
			BIGBackgroundCheckRequest Parameter1) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("BIGUtilityFunction", _args);

		String classNametoCall = getCallingClassNameForBIG(Parameter1);
		String functiontoCall = getCallingFunctionNameForBIG(Parameter1);
		return BIGUtility(Parameter1,classNametoCall,functiontoCall);	

		//end user defined code for operation
	}


	private BIGBackgroundCheckResponse BIGUtility(BIGBackgroundCheckRequest parameter1, String classNametoCall, String functionNametoCall) {
		HashMap retVal = null;
		String classtoCall = getImplClassName(parameter1, classNametoCall);
		String functiontoCall = functionNametoCall;
		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		Vector errors = new Vector();
		try {
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			Class clz = dcmClassLoader.loadClass(classtoCall);
			Method method;
			Object service;
			if("GetBackgroundCheckResponse".equals(classNametoCall)){
				method = clz.getMethod(functiontoCall, new Class[] {String.class});
				service = clz.newInstance();
				retVal = (HashMap)method.invoke(service, new Object[] {parameter1.getCaseID()});
			} else if("SubmitBackgroundCheckRequest".equals(classNametoCall)) {
				method = clz.getMethod(functiontoCall, new Class[] {String.class,String.class});
				service = clz.newInstance();
				retVal = (HashMap)method.invoke(service, new Object[] {parameter1.getCaseID(),parameter1.getPartyID()});
			}										
		} catch (Exception e) {
			errors.add(e.getMessage());
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION IS - : " + sw.toString());
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		if(retVal!=null){
			BIGBackgroundCheckResponse res = new BIGBackgroundCheckResponse();
			res.setRequestID(retVal.containsKey("RequestID")?(String)retVal.get("RequestID"):null);
			res.setStatusCode(retVal.containsKey("StatusCode")?(String)retVal.get("StatusCode"):null);
			res.setErrors(retVal.containsKey("Errors")?(String)retVal.get("Errors"):null);
			res.setClientCaseId(retVal.containsKey("ClientCaseId")?(String)retVal.get("ClientCaseId"):null);
			res.setPassReviewStatusCode(retVal.containsKey("PassReviewStatusCode")?(String)retVal.get("PassReviewStatusCode"):null);
			res.setCreditCheckResultCode(retVal.containsKey("CreditCheckResultCode")?(String)retVal.get("CreditCheckResultCode"):null);
			res.setCriminalBackgroundCheckResultCode(retVal.containsKey("CriminalBackgroundCheckResultCode")?(String)retVal.get("CriminalBackgroundCheckResultCode"):null);
			res.setOFACBackgroundCheckResultCode(retVal.containsKey("OFACBackgroundCheckResultCode")?(String)retVal.get("OFACBackgroundCheckResultCode"):null);
			res.setRedirectLink(retVal.containsKey("RedirectLink")?(String)retVal.get("RedirectLink"):null);
			return res;
		}

		return null;
	}

	private String getImplClassName(BIGBackgroundCheckRequest parameter1, String operationName) {

		ExchangeModel exModel = parameter1._getEntityType() == null ? null : parameter1._getEntityType().getExchangeModel();
		FileObject projectDirectory = exModel.getProject().getRootDirectory();

		try {
			FileObject configFile = projectDirectory.getChild("config.properties");
			Properties configProp = new Properties();
			configProp.load(FileUtils.getInputStream(configFile));
			return configProp.getProperty(operationName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getCallingClassNameForBIG(BIGBackgroundCheckRequest parameter) {
		ExchangeModel exModel = parameter._getEntityType() == null ? null : parameter._getEntityType().getExchangeModel();
		DataServiceModel dsm = exModel.getDataServiceModel("IPMDataService");
		DataServiceOperation SubmitBackgroundCheckRequestOp = dsm.getOperation("SubmitBackgroundCheckRequest");
		DataServiceOperation GetBackgroundCheckResponseOp = dsm.getOperation("GetBackgroundCheckResponse");


		if(MiscLibrary.isCurrentDataServiceOperation(SubmitBackgroundCheckRequestOp)) {
			return "SubmitBackgroundCheckRequest";
		} else if(MiscLibrary.isCurrentDataServiceOperation(GetBackgroundCheckResponseOp)) {
			return "GetBackgroundCheckResponse";
		}

		return "";
	}

	private String getCallingFunctionNameForBIG(BIGBackgroundCheckRequest parameter) {
		ExchangeModel exModel = parameter._getEntityType() == null ? null : parameter._getEntityType().getExchangeModel();
		DataServiceModel dsm = exModel.getDataServiceModel("IPMDataService");
		DataServiceOperation SubmitBackgroundCheckRequestOp = dsm.getOperation("SubmitBackgroundCheckRequest");
		DataServiceOperation GetBackgroundCheckResponseOp = dsm.getOperation("GetBackgroundCheckResponse");


		if(MiscLibrary.isCurrentDataServiceOperation(SubmitBackgroundCheckRequestOp)) {
			return "sendRequestToBIG";
		} else if(MiscLibrary.isCurrentDataServiceOperation(GetBackgroundCheckResponseOp)) {
			return "getResponseFromBIG";
		}

		return "";
	}


	/**
	 * setupDCMDataFunction custom method
	 * Description: 
	 * @param Parameter1 
	 * @return setupDCMDataResponse 
	 */
	public setupDCMDataResponse setupDCMDataFunction(
			setupDCMDataRequest Parameter1) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("setupDCMDataFunction", _args);

		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		Vector<String> errors = new Vector<String>();
		InputStream input=null;
		try{
			ExchangeModel exModel = Parameter1._getEntityType() == null ? null : Parameter1._getEntityType().getExchangeModel();
			FileObject projectDirectory = exModel.getProject().getRootDirectory();
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			FileObject configFile = projectDirectory.getChild("config.properties");			
			logger.debug("SERVICING ONLY IS - : " + Parameter1.getServicingOnly());
			logger.debug("GetProfPrefPreset IS - : " + Parameter1.getProductPrefPreset());
			logger.debug("GetProduct Group - : " + Parameter1.getProductGroup());		
			Properties configProp = new Properties();
			input = FileUtils.getInputStream(configFile);
			configProp.load(input);			
			Class clz = dcmClassLoader.loadClass(configProp.getProperty("setupDCMDataFunction"));
			Method method = clz.getMethod("setupDCMData", new Class[] {String.class, String.class, String.class, boolean.class, 
					boolean.class, boolean.class, Date.class, String.class, String.class, String.class, String.class, String.class, String.class, Date.class, Date.class, Date.class, boolean.class
					, Date.class, Date.class, Date.class, boolean.class,String.class,String.class});
			Object service = clz.newInstance();	
			
			errors = (Vector)method.invoke(service, new Object[] {Parameter1.getActivityType(),Parameter1.getAgreementName(),Parameter1.getPartyID(),
					Parameter1.getIsBackgroundcheckRequired(),Parameter1.getLegalQuestionD(),Parameter1.getLegalQuestionH(),
					Parameter1.getContractEffectiveDate(),Parameter1.getAPID(),Parameter1.getParentPositionGID(),Parameter1.getPositionType(),
					Parameter1.getPositionGID(), Parameter1.getCaseID(),Parameter1.getProductProfileName(),Parameter1.getAMLTrainingDate(),
					Parameter1.getIAIndexTrainingDate(),Parameter1.getModelLawTrainingDate(),Parameter1.getHasRegulatoryAction(),
					Parameter1.getFourHRIntTrainingDate(),Parameter1.getOneHRIntTrainingDate(),Parameter1.getNYREGTrainingDate(),
					Parameter1.getServicingOnly(),Parameter1.getProductPrefPreset(),Parameter1.getProductGroup()
					});
		}  catch (Exception e) {
			errors.add(e.getMessage());
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION IS - : " + sw.toString());
		} finally {
			if(null!=input) {
				try {
					input.close();
				} catch (IOException e) {					
				}
			}
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		setupDCMDataResponse response = new setupDCMDataResponse();
		response.setErrors(errors);
		return response;

		//end user defined code for operation
	}


	/**
	 * GetHierarchyPositionForWelcomeLetter custom method
	 * Description: 
	 * @param Parameter1 
	 * @return HierarchyPositionResponse 
	 */
	public HierarchyPositionResponse GetHierarchyPositionForWelcomeLetter(
			HierarchyPositionRequest Parameter1) {
		//begin user defined code for operation
		logger.debug("---InGetHierarchyPositionForWelcomeLetter --- ");
		Object retVal = null;
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("GetHierarchyPositionForWelcomeLetter", _args);
		
		String error = null;
		String positionGID = Parameter1.getPositionGID();
		String positionType = Parameter1.getPositionType();
		HierarchyPositionResponse response = new HierarchyPositionResponse();

		if((positionGID==null || "".equals(positionGID)) && (positionType==null || "".equals(positionType))) {
			error = "Either PositionGID or PositionType is required";							
			response.setError(error);
			return response;
		}

		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

		try{
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
			Method method = clz.getMethod("getHierarchyPositionForWelcomeLetter", new Class[] {String.class, String.class});
			Object service = clz.newInstance();
			retVal = method.invoke(service, new Object[] {Parameter1.getPositionGID(), Parameter1.getPositionType()});

		}  catch (Exception e) {
			error = e.getMessage();
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION IS - : " + sw.toString());
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		if(error!=null) {
			response.setError(error);
		} else {
			response.setPositionName((String) retVal);
		}

		return response;
		//end user defined code for operation
	}

	/**
	 * GetPositionsDetailed custom method
	 * Description: 
	 * @param Parameter1 
	 * @return PositionDetailedResponse 
	 */
	public PositionDetailedResponse GetPositionsDetailed(
			PositionDetailedRequest req) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] {req};
		execute("GetPositionsDetailed", _args);

		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		String error = null;
		Object retVal = null;

		try{
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
			Method method = clz.getMethod("searchPositionsDetailed", new Class[] {String.class, String.class, 
					String.class, String.class, String.class, String.class, String.class, String.class});
			Object service = clz.newInstance();			
			retVal = method.invoke(service, new Object[] {req.getPartyType(), req.getAPID(), req.getNPN(), 
					req.getFirstName(), req.getLastName(), req.getName(), req.getPositionName(),req.getPositionType()});

		}  catch (Exception e) {
			error = e.getMessage();
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION IS - : " + sw.toString());
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		PositionDetailedResponse response = new PositionDetailedResponse();

		if(error != null || retVal == null) {
			logger.debug("Error or null results");
			return response;
		}

		generatePositionDetailResponse((Vector<Map>) retVal, response);						
		return response;

		//end user defined code for operation
	}

	private void generatePositionDetailResponse(Vector<Map> posDataList, PositionDetailedResponse response) {
		for (Iterator iterator = posDataList.iterator(); iterator.hasNext();) {
			Map posDataMap = (Map) iterator.next();
			PositionData posData = new PositionData();
			posData.setPositionGID((String) posDataMap.get("PosGID"));
			posData.setPositionName((String) posDataMap.get("PosName"));
			posData.setPositionType((String) posDataMap.get("PosType"));
			Vector<Map> posHolderDataList = (Vector<Map>) posDataMap.get("PosHolderData");
			for (Iterator iterator2 = posHolderDataList.iterator(); iterator2.hasNext();) {
				Map posHolderDataMap = (Map) iterator2.next();
				PositionHolderData posHolData = new PositionHolderData();		
				posHolData.setPartyName((String) posHolderDataMap.get("PartyName"));
				posHolData.setAPID((String) posHolderDataMap.get("APID"));				
				posHolData.setAPStatus((String) posHolderDataMap.get("APStatus"));
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
				Date posHolStartDate = (Date) posHolderDataMap.get("StartDate");
				posHolData.setPosHolderStartDate(df.format(posHolStartDate));
				Date posHolEndDate = (Date) posHolderDataMap.get("EndDate");
				posHolData.setPosHolderEndDate(df.format(posHolEndDate));
				logger.debug("Positon Holder Data SERVICING ONLY::::"+ posHolderDataMap.get("ServicingOnly"));
				posHolData.setServicingOnly((String) posHolderDataMap.get("ServicingOnly"));
				posData.addPositionHolderData(posHolData);
			}
			response.addPositionData(posData);
		}
	}

	/**
		 * GetUplineParty custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return UplinePartyResponse 
	 	 */
		public UplinePartyResponse GetUplineParty(
				UplinePartyRequest Parameter1) {
			//begin user defined code for operation
			HashMap retVal = null;
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("GetUplineParty", _args);

			String error = null;
			String APID = Parameter1.getAPID();
			UplinePartyResponse response = new UplinePartyResponse();

			if(APID==null || "".equals(APID)) {
				error = "APID is required";							
				response.setError(error);
				return response;
			}

			DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
			ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

			try{
				Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
				Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
				Method method = clz.getMethod("getUplineParty", new Class[] {String.class, Date.class});
				Object service = clz.newInstance();
				retVal = (HashMap)method.invoke(service, new Object[] {Parameter1.getAPID(), Parameter1.getContractEffectiveDate()});

			}  catch (Exception e) {
				error = e.getMessage();
				Thread.currentThread().setContextClassLoader(oldClassLoader);
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.debug("EXCEPTION IS - : " + sw.toString());
			} finally {
				Thread.currentThread().setContextClassLoader(oldClassLoader);
			}

			if(error!=null) {
				response.setError(error);
			} else {
				if(retVal!=null){
					response.setPartyName(retVal.containsKey("PartyName")?(String)retVal.get("PartyName"):null);
					response.setAVBusinessType(retVal.containsKey("AVBusinessType")?(String)retVal.get("AVBusinessType"):null);
					response.setAVAccToWeb(retVal.containsKey("AVAccToWeb")?(String)retVal.get("AVAccToWeb"):null);
					response.setAVProductGroup(retVal.containsKey("AVProductGroup")?(String)retVal.get("AVProductGroup"):null);					
				}
			}

			return response;
			//end user defined code for operation
		}


	/**
		 * GetPartyChannelEligibility custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return PartyChannelEligibilityResponse 
	 	 */
		public PartyChannelEligibilityResponse GetPartyChannelEligibility(
				PartyChannelEligibilityInputs Parameter1) {
			//begin user defined code for operation
			
			logger.debug("Enterting GetPartyChannelEligibility");
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("GetPartyChannelEligibility", _args);
			return getPartyChannelEligibilityFronDCM(Parameter1);
			
			//end user defined code for operation
		}


		private PartyChannelEligibilityResponse getPartyChannelEligibilityFronDCM(
				PartyChannelEligibilityInputs parameter1) {
				String error = null;
				Object retVal = null;
			PartyChannelEligibilityResponse response = new PartyChannelEligibilityResponse();
			String partyID = parameter1.getPartyID();
			String partyGID = parameter1.getPartyGID();
			if((partyID == null || "".equals(partyID)) && (partyGID == null || "".equals(partyGID))) {
				logger.debug("Both PartyID and PartyGID are null or blank");
				return response;
			}

			DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
			ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

			try{
				Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
				Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
				Method method = clz.getMethod("getPartyChannelEligibilityFromDCM", new Class[] {String.class, String.class, Date.class});
				Object service = clz.newInstance();
				retVal = method.invoke(service, new Object[] {partyID, parameter1.getPositionGID(),parameter1.getContractEffectiveDate()});

			}  catch (Exception e) {
				error = e.getMessage();
				Thread.currentThread().setContextClassLoader(oldClassLoader);
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.debug("EXCEPTION IS - : " + sw.toString());
			} finally {
				Thread.currentThread().setContextClassLoader(oldClassLoader);
			}

			if(error!=null) {
			} else {
				response.setEligibilityResult((String) retVal);
			}

			return response;
			}


		/**
			 * GetActiveProductProfile custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return ActiveProductProfileResponse 
		 	 */
			public ActiveProductProfileResponse GetActiveProductProfile(
					ActiveProductProfileRequest Parameter1) {
				//begin user defined code for operation
				
				logger.debug("Enterting GetActiveProductProfile");
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				execute("GetActiveProductProfile", _args);
				return getActiveProductProfileFromDCM(Parameter1);
				
				//end user defined code for operation
			}


		private ActiveProductProfileResponse getActiveProductProfileFromDCM(
				ActiveProductProfileRequest parameter1) {
			logger.debug("Enterting getActiveProductProfileFromDCM");
			String error = null;
			Object retVal = null;
			ActiveProductProfileResponse response = new ActiveProductProfileResponse();
		Date contractEffectiveDate=parameter1.getContractEffectiveDate();
		if(contractEffectiveDate == null || "".equals(contractEffectiveDate)) {
			logger.debug("ContractEffectiveDate are null or blank");
			return response;
		}

		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

		try{
			Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
			Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
			Method method = clz.getMethod("getProductProfiles", new Class[] {Date.class});
			Object service = clz.newInstance();
			retVal = method.invoke(service, new Object[] {parameter1.getContractEffectiveDate()});
			//retVal.toString()

		}  catch (Exception e) {
			error = e.getMessage();
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION IS - : " + sw.toString());
		} finally {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
		}

		if(error!=null) {
			logger.debug("Error Received"+error);
			
			
		} else {
			logger.debug("No Error Received"+error);
			response.setActiveProductProfile((List<String>) retVal);
			
			
		}
		
		return response;
		
			
		}


		/**
			 * GetLoaData custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return LOAResponse 
		 	 */
			public LOAResponse GetLoaData(
					LOARequest Parameter1) {			
				//begin user defined code for operation
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
				ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
				String error = null;
				Object retVal = null;

				try{
					Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
					Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
					Method method = clz.getMethod("getLoaData", new Class[] {String.class,Date.class});
					Object service = clz.newInstance();
					retVal = method.invoke(service, new Object[] {Parameter1.getPartyId(),Parameter1.getContractEffectiveDate()});

				}  catch (Exception e) {
					error = e.getMessage();
					Thread.currentThread().setContextClassLoader(oldClassLoader);
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					logger.debug("EXCEPTION IS - : " + sw.toString());
				} finally {
					Thread.currentThread().setContextClassLoader(oldClassLoader);
				}

				LOAResponse response = new LOAResponse();

				if(error != null || retVal == null) {
					logger.debug("Error or null results");
					return response;
				}

				generateLoaResponse((Vector<Map>) retVal, response);						
				return response;
				
				
				//end user defined code for operation
			}
			
			private void generateLoaResponse(Vector<Map> loaDataList,LOAResponse response) {
				List<LOAData> loaList=new ArrayList<LOAData>();
				for (Iterator iterator = loaDataList.iterator(); iterator.hasNext();) {
					Map posDataMap = (Map) iterator.next();
					LOAData loaData = new LOAData();					
					loaData.setLicNumber((String) posDataMap.get("licNumber"));				
					loaData.setStartDate((Date) posDataMap.get("startDate"));
					loaData.setEndDate((Date) posDataMap.get("endDate"));					
					loaData.setStatusCode((String) posDataMap.get("statusCode"));
					loaData.setStatusReason((String) posDataMap.get("statusReason")); 
					loaData.setStatusType((String) posDataMap.get("statusType"));
					loaData.setLoaCode((String) posDataMap.get("code"));
					loaData.setName((String) posDataMap.get("name"));
					loaList.add(loaData);
				}					
				response.setLoaData(loaList);				
			}


			/**
				 * GetLastStatusReason custom method
				 * Description: 
				 * @param Parameter1 
			 	 * @return PartyStatusResponse 
			 	 */
				public PartyStatusResponse GetLastStatusReason(
						PartyStatusRequest Parameter1) {	
					//String callingOperationName = getCallingOperationName(Parameter1);				
					/*if(MiscLibrary.isCurrentDataServiceOperation("GetLastStatusReason")) {
						return null;
					}*/
					
					//begin user defined code for operation
					java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
					DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
					ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
					String error = null;
					Object retVal = null;

					try{
						Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
						Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
						Method method = clz.getMethod("getLastStatusReason", new Class[] {String.class,Date.class});
						Object service = clz.newInstance();
						retVal = method.invoke(service, new Object[] {Parameter1.getPartyId(),Parameter1.getContractEffectiveDate()});

					}  catch (Exception e) {
						error = e.getMessage();
						Thread.currentThread().setContextClassLoader(oldClassLoader);
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						e.printStackTrace(pw);
						logger.debug("EXCEPTION IS - : " + sw.toString());
					} finally {
						Thread.currentThread().setContextClassLoader(oldClassLoader);
					}

					PartyStatusResponse response = new PartyStatusResponse();

					if(error != null || retVal == null) {
						logger.debug("Error or null results");
						return response;
					}
					HashMap<String,String> data=(HashMap<String,String>)retVal;
					response.setResult(data.get("Result"));
					response.setStatusReason(data.get("StatusReason"));
					return response;
					
					//end user defined code for operation
				}


			/**
				 * CheckRootApParty custom method
				 * Description: 
				 * @param Parameter1 
			 	 * @return RootAPPartyResponse 
			 	 */
				public RootAPPartyResponse CheckRootApParty(
						RootAPPartyRequest Parameter1) {
					//begin user defined code for operation
					java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
					
					DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
					ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
					String error = null;
					Object retVal = null;
					try{
						logger.debug("CheckRootApParty Called.....!!!");
						logger.debug("TaxID:"+Parameter1.getTaxId()+" APID:"+Parameter1.getApId());
						Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
						Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
						//Method method = clz.getMethod("checkRootAPTaxId", new Class[] {String.class,String.class,Date.class});
						Method method = clz.getMethod("checkRootAPTaxId",new Class[]{String.class,String.class,Date.class,String.class});
						Object service = clz.newInstance();
						retVal = method.invoke(service, new Object[] {Parameter1.getApId(),Parameter1.getTaxId(),Parameter1.getContractEffectiveDate()});
						
					}  catch (Exception e) {
						error = e.getMessage();
						Thread.currentThread().setContextClassLoader(oldClassLoader);
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						e.printStackTrace(pw);
						logger.debug("EXCEPTION IS - : " + sw.toString());
					} finally {
						Thread.currentThread().setContextClassLoader(oldClassLoader);
					}
					HashMap<String,String> data=(HashMap<String,String>)retVal;
					RootAPPartyResponse response=new RootAPPartyResponse();
					response.setFound(data.get("Found"));
					logger.debug("CheckRootApParty Called...RetVal:"+response.getFound());
					return response;					
					//end user defined code for operation
				}


				
				private void generateOrgPrincipalResponse(Vector<Map> loaDataList,OrgPrincipalResponse response) {
					List<OrgPrincipalRelation> orgPriList=new ArrayList<OrgPrincipalRelation>();
					for (Iterator iterator = loaDataList.iterator(); iterator.hasNext();) {
						Map posDataMap = (Map) iterator.next();
						OrgPrincipalRelation orgPriData = new OrgPrincipalRelation();	
						orgPriData.setStartDate((String) posDataMap.get("startDate"));
						orgPriData.setEndDate((String) posDataMap.get("endDate"));					
						orgPriData.setRelationshipType((String)posDataMap.get("relationshipType"));
						orgPriData.setPartyId((String)posDataMap.get("partyId"));
						orgPriData.setName((String)posDataMap.get("name"));
						orgPriList.add(orgPriData);
					}					
					response.setData(orgPriList);		
				}


				/**
					 * GetOrgPrincipalRelation custom method
					 * Description: 
					 * @param Parameter1 
				 	 * @return OrgPrincipalResponse 
				 	 */
					public OrgPrincipalResponse GetOrgPrincipalRelation(
							OrgPrincipalRequest Parameter1) {
						//begin user defined code for operation		
						System.out.println("GetOrgPrincipalRelation Called...::"+Parameter1);
						DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
						ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
						String error = null;
						Object retVal = null;

						try{
							Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
							Class clz = dcmClassLoader.loadClass("com.aviva.fs.DCMTZXIntegration.IMPDataRetriever");
							Method method = clz.getMethod("getOrgPrincipalRelation", new Class[] {String.class});
							Object service = clz.newInstance();
							retVal = method.invoke(service, new Object[] {Parameter1.getOrgTaxID()});
						}  catch (Exception e) {
							error = e.getMessage();
							Thread.currentThread().setContextClassLoader(oldClassLoader);
							StringWriter sw = new StringWriter();
							PrintWriter pw = new PrintWriter(sw);
							e.printStackTrace(pw);
							logger.debug("EXCEPTION IS - : " + sw.toString());
						} finally {
							Thread.currentThread().setContextClassLoader(oldClassLoader);
						}

						OrgPrincipalResponse response = new OrgPrincipalResponse();

						if(error != null || retVal == null) {
							logger.debug("Error or null results");
							return response;
						}

						generateOrgPrincipalResponse((Vector<Map>) retVal, response);
						return response;
						
						//end user defined code for operation
					}
}

