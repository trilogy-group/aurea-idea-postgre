package dcm;


import insertagreementparticipantrspec.RECORD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.aurea.dcm.loader.DCMLoader;
import com.pantero.metamodel.ExchangeModel;
import com.pantero.metamodel.models.DataServiceModel;
import com.pantero.metamodel.operations.DataServiceOperation;
import com.pantero.runtime.PanteroRuntimeException;
import com.pantero.runtime.datasource.OperationDatasourceImpl;
import com.pantero.runtime.metamodel.expressions.library.MiscLibrary;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
import com.progress.dataxtend.si.flatfile.runtime.FlatFileEntity.RootEntity;

import dcm.insertpersonpartyspec.ROOT;
/*
 * Copyright (c) 2004-2013 by Aurea, Inc. All Rights Reserved.
 * All use, reproduction, transfer, publication or disclosure is prohibited
 * except as may be expressly permitted in the applicable license agreement.
 */
/**
 * The primary Java class generated for the DCMFlatFileDataSource model.
 * Description: <br><br>
 *
 * Generated 2013-07-08 05:15:19.208
 *
 */
public class DCMFlatFileDataSourceImpl extends OperationDatasourceImpl {
	protected static final Log logger = LogFactory.getLog(dcm.DCMFlatFileDataSourceImpl.class);
	private static final String CANCELLED = "Cancelled";
	private static final String CLOSED = "Closed";
	
	public DCMFlatFileDataSourceImpl(){
		super();
	} 

	/**
	 * InsertPerson custom method
	 * Description: 
	 * @param Parameter1 
	 * @throws IOException 
	 * @throws FileNotFoundException 
  	 */
	public dcm.Alerts InsertPerson(
			ROOT Parameter1) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("InsertPerson", _args);

		return returnAlert(Parameter1, "InsertPerson");
		//end user defined code for operation
	}

	/**
	 * InsertOrganization custom method
	 * Description: 
	 * @param Parameter1 
  	 */
	public dcm.Alerts InsertOrganization(
			dcm.insertorgpartyspec.ROOT Parameter1) {
		//begin user defined code for operation
		java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
		execute("InsertOrganization", _args);

		return returnAlert(Parameter1, "InsertOrganization");
		//end user defined code for operation
	}

	/**
		 * InsertUser custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUser(
				insertuserspec.ROOT Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUser", _args);
			
			return returnAlert(Parameter1, "InsertUser");
			//end user defined code for operation
		}

	/**
		 * InsertUpdateContactPoint custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUpdateContactPoint(
				contactpointsspec.ROOT Parameter1) {
			logger.debug("----In Insert Update Contact Point-----");
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUpdateContactPoint", _args);
			
			return returnAlert(Parameter1, "InsertUpdateContactPoint");
			//end user defined code for operation
		}

	/**
		 * InsertUpdateTraining custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUpdateTraining(
				cecreditsspec.ROOT Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUpdateTraining", _args);
			
			return returnAlert(Parameter1, "InsertUpdateTraining");
			//end user defined code for operation
		}

	/**
		 * InsertUpdateErrorAndOmission custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUpdateErrorAndOmission(
				enononinheritancepolicyloaderspec.ROOT Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUpdateErrorAndOmission", _args);
			
			return returnAlert(Parameter1, "InsertUpdateErrorAndOmission");
			//end user defined code for operation
		}

	/**
		 * InsertUpdatePaymentAccount custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUpdatePaymentAccount(
				paymentaccountspec.ROOT Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUpdatePaymentAccount", _args);
			
			return returnAlert(Parameter1, "InsertUpdatePaymentAccount");
			//end user defined code for operation
		}

	/**
		 * InsertUpdateAgreementParticipant custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUpdateAgreementParticipant(
				insertagreementparticipantrspec.ROOT Parameter1) {
			logger.debug("Inside AP loader logic");
			//begin user defined code for operation
			Alerts retAlert = new Alerts();
			List<RECORD> list = Parameter1.getRECORD();
			List<RECORD> listToInsert = new ArrayList<RECORD>();
			List<RECORD> listToUpdate = new ArrayList<RECORD>();
			List<ideaupdateagreementparticipantstatusloaderspec.RECORD> listToUpdateCancel = new ArrayList<ideaupdateagreementparticipantstatusloaderspec.RECORD>();
			boolean cancelAp = false;
			for(Iterator<RECORD> it=list.iterator();it.hasNext();){
				RECORD rec = it.next();
				if(rec.getApid()!=null && !"".equals(rec.getApid().trim())){
					logger.debug("Added one row for update");
					
					if(null!=rec.getStatusCode() && null!=rec.getStatusReasonCode()) {
						if(rec.getStatusCode().equalsIgnoreCase(CANCELLED) &&
								rec.getStatusReasonCode().equalsIgnoreCase(CLOSED)){
							cancelAp = true;
							ideaupdateagreementparticipantstatusloaderspec.RECORD uRec= new ideaupdateagreementparticipantstatusloaderspec.RECORD();
							uRec.setApid(rec.getApid());
							uRec.setStartDate(rec.getStartDate());
							uRec.setEndDate(rec.getEndDate());
							uRec.setStatusCode(CANCELLED);
							uRec.setStatusReason(CLOSED);
							listToUpdateCancel.add(uRec);
							listToUpdate.add(rec);
						}
					} else {
						listToUpdate.add(rec);
					}
				}
				else {
					logger.debug("Added one row for insert");
					listToInsert.add(rec);
				}
			}
			if(!listToInsert.isEmpty()){
				logger.debug("Calling insert Loader");
				Parameter1.setRECORD(listToInsert);
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};			
				execute("InsertAgreementParticipant", _args);			
				Alerts ret =  returnAlert(Parameter1, "InsertAgreementParticipant");
				retAlert = addMessagesToAlerts(retAlert, ret);
				logger.debug("Done Calling insert Loader");
			}
			if(!listToUpdate.isEmpty()){
				logger.debug("Calling update Loader");			
				if(cancelAp) {			
					logger.debug("UpdateAgreementParticipant Calling update Loader Cancel Status");	
					ideaupdateagreementparticipantstatusloaderspec.ROOT ParameterUp=new ideaupdateagreementparticipantstatusloaderspec.ROOT(); 
					ParameterUp.setRECORD(listToUpdateCancel);
					java.lang.Object[] _args = new java.lang.Object[] { ParameterUp};			
					execute("UpdateAgreementParticipantCancel", _args);			
					Alerts ret =  returnAlert(ParameterUp, "UpdateAgreementParticipantCancel");
					retAlert = addMessagesToAlerts(retAlert, ret);
				} else {		
					logger.debug(" UpdateAgreementParticipant Calling update Loader Other Status");	
					Parameter1.setRECORD(listToUpdate);
					java.lang.Object[] _args = new java.lang.Object[] { Parameter1};			
					execute("UpdateAgreementParticipant", _args);			
					Alerts ret =  returnAlert(Parameter1, "UpdateAgreementParticipant");
					retAlert = addMessagesToAlerts(retAlert, ret);
				}
				logger.debug("Done Calling update Loader");
			}
			//end user defined code for operation
			return retAlert;
		}

	/**
		 * InsertUpdateLicense custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUpdateLicense(
				insertlicensesspec.ROOT Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUpdateLicense", _args);
			
			return returnAlert(Parameter1, "InsertUpdateLicense");
			//end user defined code for operation
		}

	/**
		 * InsertUpdateLicenseLine custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUpdateLicenseLine(
				insertlicenselinespec.ROOT Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUpdateLicenseLine", _args);
			
			return returnAlert(Parameter1, "InsertUpdateLicenseLine");
			//end user defined code for operation
		}

	/**
		 * InsertUpdateAppointment custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts InsertUpdateAppointment(
				insertappointmentspec.ROOT Parameter1) {
			logger.debug("************************* InsertUpdateAppointment Called*****");
		
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			List<insertappointmentspec.RECORD> record=Parameter1.getRECORD();	
			String partyType="";
			String specFile = "InsertUpdateAppointment";
			if(!record.isEmpty()) {
				insertappointmentspec.RECORD rec = record.get(0);
				if(null!=rec.getPartyType()) {
					partyType = rec.getPartyType();
					logger.debug("**************************PartyType::::"+partyType);
				}
			}
			if("Person".equalsIgnoreCase(partyType)) {
				specFile = "InsertUpdateAppointmentPerson";
			} else if("Organization".equalsIgnoreCase(partyType)) {
				specFile = "InsertUpdateAppointmentOrg";
			} 
			execute(specFile, _args);
			logger.debug("************************* InsertUpdateAppointment Called*****SpecFile:"+specFile);
			return returnAlert(Parameter1, specFile);
			//end user defined code for operation
		}

		/**
		 * InsertUpdatePosition custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return Alerts 
	 	 */
		public dcm.Alerts InsertUpdatePosition(
				com.comphierarchypositionspec.ROOT Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUpdatePosition", _args);
			
			logger.info("!!! InsertUpdatePosition ");
			return returnAlert(Parameter1, "InsertUpdatePosition");
			//end user defined code for operation
		}

	/**
		 * InsertUpdatePositionHolder custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return dcm.Alerts 
	 	 */
		public dcm.Alerts InsertUpdatePositionHolder(
				com.comphierarchypositionholderspec.ROOT Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("InsertUpdatePositionHolder", _args);

			logger.info("!!! InsertUpdatePositionHolder ");
			
			return returnAlert(Parameter1, "InsertUpdatePositionHolder");
			//end user defined code for operation
		}

	/**
		 * OrderLicenseSpecs custom method
		 * Description: 
		 * @param Parameter1 
	  	 */
		public dcm.Alerts OrderLicenseSpecs(
				dcm.ExecutionHelper Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("OrderLicenseSpecs", _args);
			Alerts alerts = new Alerts();
			Alerts moreAlerts = new Alerts();
			boolean isApptOnly = false;
			
			if(Parameter1.getROOTLicense() == null) {
				logger.debug("NO Licenses");
				return alerts;
			}
			// Adding ContactPoint and ContactPointUsage here because appointment requests to gateway
			// need address information from the party
			if (Parameter1.getROOTContactPoint() != null){
				logger.debug("calling insertupdatecontactpoint from OrderLicenseSpecs");
				moreAlerts = InsertUpdateContactPoint(Parameter1.getROOTContactPoint());
				alerts = addMessagesToAlerts(alerts, moreAlerts);
			}
			if (Parameter1.getROOTContactPointUsage() != null){
				logger.debug("calling insertupdatecontactpointusage from OrderLicenseSpecs");
				moreAlerts = InsertUpdateContactPointUsage(Parameter1.getROOTContactPointUsage());
				alerts = addMessagesToAlerts(alerts, moreAlerts);
			}
			//Commenting for Athene as Licenses and LicenseLines are not updated
			/*if(Parameter1.getApptOnlyFlag() != null) {
				isApptOnly = Parameter1.getApptOnlyFlag().booleanValue();
			}
			if (Parameter1.getROOTLicense() != null && !isApptOnly){
				moreAlerts = InsertUpdateLicense(Parameter1.getROOTLicense());
				alerts = addMessagesToAlerts(alerts, moreAlerts);
			}
			if (Parameter1.getROOTLicenseLine() != null && !isApptOnly){
				moreAlerts = InsertUpdateLicenseLine(Parameter1.getROOTLicenseLine());
				alerts = addMessagesToAlerts(alerts, moreAlerts);
			}*/
			if (Parameter1.getROOTAppointment() != null){
				moreAlerts = InsertUpdateAppointment(Parameter1.getROOTAppointment());
				alerts = addMessagesToAlerts(alerts, moreAlerts);
			}
			return alerts;
			//end user defined code for operation
		}
		
		/**
		 * OrderPositionSpecs custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return dcm.Alerts 
	 	 */
		public dcm.Alerts OrderPositionSpecs(
				dcm.ExecutionHelper Parameter1) {
			//begin user defined code for operation
			java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
			execute("OrderPositionSpecs", _args);
			Alerts alerts = new Alerts();
			Alerts moreAlerts = new Alerts();
			if (Parameter1.getROOTPosition() != null){
				moreAlerts = InsertUpdatePosition(Parameter1.getROOTPosition());
				alerts = addMessagesToAlerts(alerts, moreAlerts);
			}
			if (Parameter1.getROOTAgreementParticipant() != null){
				moreAlerts = InsertUpdateAgreementParticipant(Parameter1.getROOTAgreementParticipant());
				alerts = addMessagesToAlerts(alerts, moreAlerts);
			}
			if (Parameter1.getROOTPositionHolder() != null){
				moreAlerts = InsertUpdatePositionHolder(Parameter1.getROOTPositionHolder());
				alerts = addMessagesToAlerts(alerts, moreAlerts);
			}
			return alerts;
			//end user defined code for operation
		}

		private dcm.Alerts addMessagesToAlerts(Alerts alerts, Alerts moreAlerts){
			if (alerts == null)
				alerts = new Alerts();
			if (moreAlerts != null && moreAlerts.getMessageAlert() != null){
				for (MessageAlert messageAlert : moreAlerts.getMessageAlert()) {
					if (messageAlert != null)
						alerts.addMessageAlert(messageAlert);
				}
			}
			return alerts;
		}
		
		public Alerts returnAlert(RootEntity parameter, String operationName){
			Alerts alerts = new Alerts();
			
			boolean validateOnly = isValidateOperation(parameter);
	        
			try {
				List<MessageAlert> results = new DCMLoader().loadDCM(parameter, operationName, validateOnly);
				if (results != null){
					for (MessageAlert messageAlert : results) {
						if(validateOnly) {
							alerts.addMessageAlert(messageAlert);
						} else {
							if("Warning".equals(messageAlert.getSeverity())) {
								// do nothing, skip the warnings
							} else {
								alerts.addMessageAlert(messageAlert);
							}
						}
					}
				}
			} catch (Throwable e) {
				throw new PanteroRuntimeException(e);
			}
			return alerts;
		}

		private boolean isValidateOperation(RootEntity parameter) {
			boolean result = false;
			
			ExchangeModel exModel = parameter._getEntityType() == null ? null : parameter._getEntityType().getExchangeModel();
			DataServiceModel dsm = exModel.getDataServiceModel("IPMDataService");
	        DataServiceOperation validateForInsertOp = dsm.getOperation("ValidateForInsert");
	        DataServiceOperation validateForUpdateOp = dsm.getOperation("ValidateForUpdate");
	        DataServiceOperation validateForUpdatePartyOp = dsm.getOperation("ValidateForUpdateParty");
	        
	        if(MiscLibrary.isCurrentDataServiceOperation(validateForInsertOp) ||
	        		MiscLibrary.isCurrentDataServiceOperation(validateForUpdateOp) ||
	        		MiscLibrary.isCurrentDataServiceOperation(validateForUpdatePartyOp)) {
	        	logger.debug("VALIDATE ONLY operation");
	            result = true;
	        }
	        
			return result;
		}

		/**
			 * InsertUpdateContactPointUsage custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return Alerts 
		 	 */
			public dcm.Alerts InsertUpdateContactPointUsage(
					contactpointusagespec.ROOT Parameter1) {
				logger.debug("-----In Insert Update Contact Point Usage-----");
				//begin user defined code for operation
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				execute("InsertUpdateContactPointUsage", _args);
				
				dcm.Alerts dateoverlapValidationError = checkDateOverlapping(Parameter1, "InsertUpdateContactPointUsage");
				if(dateoverlapValidationError!=null){
					logger.debug("----There are errors------");
					return dateoverlapValidationError;
				}
				
				return returnAlert(Parameter1, "InsertUpdateContactPointUsage");
				//end user defined code for operation
			}

			private dcm.Alerts checkDateOverlapping(contactpointusagespec.ROOT Parameter1, String dxsiOperationName){
				logger.debug("----Entered Check overlapping dates------");
		       dcm.Alerts alert = null;
		       
		       
		       int recordCount = Parameter1.getRECORD().size();
		       logger.debug("----Rec Count = " + recordCount);
		       for(int i=0;i<recordCount;i++){
		       	logger.debug("----OuterLoop ROW = " + i);
		       	contactpointusagespec.RECORD usage = Parameter1.getRECORDAt(i);
		       	String currentUsage = usage.getUsageType();
		       	String currentAP = usage.getUsageAP();
		       	logger.debug("----AJ: i currentUsage = " + currentUsage);
		       	logger.debug("----AJ: i currentAP = " + currentAP);
//		       	boolean isCurrentAPEmptyorNull = !(currentAP!=null && !"".equalsIgnoreCase(currentAP.trim()));
		       	boolean isCurrentAPEmptyorNull = currentAP==null || "".equalsIgnoreCase(currentAP.trim());
		       	logger.debug("----AJ: i isCurrentAPEmptyorNull = " + isCurrentAPEmptyorNull);
		       	for(int j=i+1; j<recordCount; j++){
		       		logger.debug("----InnerLoop ROW = " + i + " -- " + j);
		       		if(i==j) {
		       			logger.debug("i=j skipping");
		       			continue;
		       		}
		       		contactpointusagespec.RECORD usage1 = Parameter1.getRECORDAt(j);
		       		String currentUsage1 = usage1.getUsageType();
		       		String currentAP1 = usage1.getUsageAP();
		       		logger.debug("----AJ: j currentUsage1 = " + currentUsage1);
			       	logger.debug("----AJ: j currentAP1 = " + currentAP1);
		       		boolean iscurrentAP1EmptyorNull = currentAP1==null || "".equalsIgnoreCase(currentAP1.trim());
		       		logger.debug("----AJ: j isCurrentAP1EmptyorNull = " + iscurrentAP1EmptyorNull);
		       		boolean doWithoutAPCheck = isCurrentAPEmptyorNull && iscurrentAP1EmptyorNull; // both are non AP specific usages
		       		boolean doWithAPCheck = !isCurrentAPEmptyorNull && !iscurrentAP1EmptyorNull; // both are AP specific usages
		       		
		       		if(doWithoutAPCheck) {
		       			logger.debug("----AJ: both not AP specific");
		       			if (currentUsage.equalsIgnoreCase(currentUsage1)) {
		       				logger.debug("----Doing without AP check------");
		       				if (checkIfDateOverlap(getDatefromString(usage.getStartDate()),getDatefromString(usage.getEndDate()),getDatefromString(usage1.getStartDate()),getDatefromString(usage1.getEndDate()))){
		       					logger.debug("----Date overlapping without AP------");
		       					if(alert==null)
		       						alert = new Alerts();
		       					dcm.MessageAlert error = new dcm.MessageAlert();
		   		       		error.setRowNumber(i + "," + j);
		   		       		error.setSeverity("Error");
		   		       		error.setMessage("Two or more Party level contact usages of the same type have overlapping dates");
		   		       		error.setOperation(dxsiOperationName);
		   		       		alert.addMessageAlert(error);
		       				}
		       			}
                        } else if(doWithAPCheck) {
                        	logger.debug("----AJ: both are AP specific");
                        	logger.debug("----AJ: " + currentUsage + " -- " + currentUsage1);
                        	logger.debug("----AJ: " + currentAP + " -- " + currentAP1);
                            if ((currentUsage.equalsIgnoreCase(currentUsage1)) && currentAP.equalsIgnoreCase(currentAP1)) {
                            	logger.debug("----Doing with AP check------");
                            	logger.debug("----AP and Usage matched ---- ");
                            	if (checkIfDateOverlap(getDatefromString(usage.getStartDate()),getDatefromString(usage.getEndDate()),getDatefromString(usage1.getStartDate()),getDatefromString(usage1.getEndDate()))){
                            		logger.debug("----Date overlapping with AP------");
                            		if(alert==null)
		       						alert = new Alerts();
                            		dcm.MessageAlert error = new dcm.MessageAlert();
                            		error.setRowNumber(i + "," + j);
            		       		error.setSeverity("Error");
            		       		error.setMessage("Two or more AP Level contact usages of the same type with same AP have overlapping dates");
            		       		error.setOperation(dxsiOperationName);
            		       		alert.addMessageAlert(error);
                            	}
                            }
                        } else {
                        	logger.debug("----AJ: One is AP specific and other is non AP specific");
                        }		       		
		       	}
		       }		       
		       
				return alert;
				
			}

			
			private boolean checkIfDateOverlap(Date s1, Date e1, Date s2, Date e2){
				// Assuming that e1 is after s1 AND e2 after s2
				logger.debug("Dates are " + s1 + ", "+ e1 + ", "+ s2 + ", "+ e2 );
				
				if(s2.before(s1) && e2.after(s1) && e2.before(e1))
					return true;
				
				if(s2.equals(s1))
					return true;
				
				if(s2.after(s1) && s2.before(e1) && e2.after(e1))
					return true;
				
				if(s2.after(s1) && s2.before(e1) && e2.before(e1))
					return true;								
				
												
				return false;
			}
			
			private Date getDatefromString(String datestring){
				try{
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					return formatter.parse(datestring);
				} catch (ParseException e){
					logger.debug(e);
				}
				return null;
				
			}
						
			
		/**
			 * OrderContactSpecs custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return dcm.Alerts 
		 	 */
			public dcm.Alerts OrderContactSpecs(
					dcm.ExecutionHelper Parameter1) {
				//begin user defined code for operation
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				execute("OrderContactSpecs", _args);
				Alerts alerts = new Alerts();
				Alerts moreAlerts = new Alerts();
				logger.debug("in ordercontactspecs");
				if(Parameter1.getROOTLicense() != null) {
					logger.debug("Licenses are not null. Not loading Contacts");
				} else {
					if (Parameter1.getROOTContactPoint() != null){
						logger.debug("calling insertupdatecontactpoint from OrderContactSpecs");
						moreAlerts = InsertUpdateContactPoint(Parameter1.getROOTContactPoint());
						alerts = addMessagesToAlerts(alerts, moreAlerts);
					} else {
						logger.debug("did not call insertupdatecontactpoint");
					}
					if (Parameter1.getROOTContactPointUsage() != null){
						logger.debug("calling insertupdatecontactpointusage from OrderContactSpecs");
						moreAlerts = InsertUpdateContactPointUsage(Parameter1.getROOTContactPointUsage());
						alerts = addMessagesToAlerts(alerts, moreAlerts);
					}
				}
				return alerts;
				//end user defined code for operation
			}

		/**
			 * UpdatePerson custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return Alerts 
		 	 */
			public dcm.Alerts UpdatePerson(
					updatepersonpartyspec.ROOT Parameter1) {
				//begin user defined code for operation
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				execute("UpdatePerson", _args);
				
				return returnAlert(Parameter1, "UpdatePerson");
				//end user defined code for operation
			}

		/**
			 * UpdateOrganization custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return dcm.Alerts 
		 	 */
			public dcm.Alerts UpdateOrganization(
					updateorgpartyspec.ROOT Parameter1) {
				logger.debug("----In  UpdateOrganization-----");
				logger.debug("----In  UpdateOrganization-----"+Parameter1);
				//begin user defined code for operation
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				execute("UpdateOrganization", _args);
				
				return returnAlert(Parameter1, "UpdateOrganization");
				//end user defined code for operation
			}

		/**
			 * InsertUpdatePartyStatus custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return Alerts 
		 	 */
			public dcm.Alerts InsertUpdatePartyStatus(
					updatepartystatusspec.ROOT Parameter1) {
				//begin user defined code for operation
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				execute("InsertUpdatePartyStatus", _args);
				
				return returnAlert(Parameter1, "InsertUpdatePartyStatus");
				//end user defined code for operation
			}

		/**
			 * InsertUpdateComponentParticipation custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return Alerts 
		 	 */
			public dcm.Alerts InsertUpdateComponentParticipation(
					insertupdatecomponentparticipantspec.ROOT Parameter1) {
				//begin user defined code for operation
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				execute("InsertUpdateComponentParticipation", _args);
				
				return returnAlert(Parameter1, "InsertUpdateComponentParticipation");
				//end user defined code for operation
			}

		/**
			 * InsertUpdateFIAgreement custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return Alerts 
		 	 */
			public dcm.Alerts InsertUpdateFIAgreement(
					ideafiagreementsspec.ROOT Parameter1) {
				//begin user defined code for operation
				java.lang.Object[] _args = new java.lang.Object[] { Parameter1};
				execute("InsertUpdateFIAgreement", _args);
				
				return returnAlert(Parameter1, "InsertUpdateFIAgreements");
				//end user defined code for operation
			}
}

