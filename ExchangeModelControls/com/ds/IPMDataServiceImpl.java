package com.ds;

import com.pantero.runtime.dataservice.DataService;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
import com.ipm.Error;
import com.partyinformation.Insert;
import com.partyinformation.UpdateParty;
import com.ipm.OrganizationResponse;
import com.ipm.PartyRequest;
import com.ipm.PartyResponse;
import com.ipm.PaymentAccountResponse;
import com.ipm.PaymentAccountRequest;
import com.ipm.PersonResponse;
import com.ipm.PositionResponse;
import com.ipm.PositionRequest;
import com.ipm.TransactionResponse;
import com.ipm.TransactionRequest;
import com.ipm.UserResponse;
import com.partyinformation.Update;
import com.ipm.EmailResponse;
import com.ipm.EmailRequest;
import com.ipm.AgreementParticipantResponse;
import com.ipm.AgreementParticipantRequest;
import com.ipm.AppointmentResponse;
import com.ipm.AppointmentRequest;
import com.ipm.NPNResponse;
import com.ipm.NPNRequest;
import com.ipm.DownloadUpdateFromPDBResponse;
import com.ipm.DownloadUpdateFromPDBRequest;
import com.ipm.ContactPointResponse;
import com.ipm.ContactPointRequest;
import com.ipm.LicenseResponse;
import com.ipm.LicenseRequest;
import com.ipm.ContactPointUsageResponse;
import com.ipm.ContactPointUsageRequest;
import com.ipm.RegulatoryActionResponse;
import com.ipm.RegulatoryActionRequest;
import com.ipm.CorrespondenceEmailRequest;
import com.ipm.NIPRDetailsForAppointmentResponse;
import com.ipm.NIPRDetailsForAppointmentRequest;
import com.ipm.Cancelledin90daysResponse;
import com.ipm.Cancelledin90daysRequest;
import com.ipm.checkPartyRecontractabilityResponse;
import com.ipm.checkPartyRecontractabilityRequest;
import com.ipm.EOAndBCWaivedResponse;
import com.ipm.EOAndBCWaivedRequest;
import com.ipm.OtherCompanyAppointmentsResponse;
import com.ipm.OtherCompanyAppointmentsRequest;
import com.ipm.ChildPositionTypeResponse;
import com.ipm.ChildPositionTypeRequest;
import com.ipm.ErrorAndOmissionResponse;
import com.ipm.ErrorAndOmissionRequest;
import com.ipm.APIDForCaseResponse;
import com.ipm.APIDForCaseRequest;
import com.ipm.PaymentAccountsResponse;
import com.ipm.PaymentAccountsRequest;
import com.ipm.SubmitBackgroundCheckResponse;
import com.ipm.SubmitBackgroundCheckRequest;
import com.ipm.BackgroundCheckResponse;
import com.ipm.BackgroundCheckResponseRequest;
import com.ipm.setupDCMDataResponse;
import com.ipm.setupDCMDataRequest;
import com.ipm.HierarchyPositionResponse;
import com.ipm.HierarchyPositionRequest;
import com.ipm.PositionDetailedResponse;
import com.ipm.PositionDetailedRequest;
import com.ipm.FIAgreementsResponse;
import com.ipm.FIAgreementsRequest;
import com.ipm.UplinePartyResponse;
import com.ipm.UplinePartyRequest;
import com.ipm.ChannelEligibilityResponse;
import com.ipm.PartyChannelEligibilityRequest;
import com.ipm.ActiveProductProfileResponse;
import com.ipm.ActiveProductProfileRequest;
import com.ipm.LOAResponse;
import com.ipm.LOARequest;
import com.ipm.PartyStatusReason;
import com.ipm.PartyStatusRequest;

/*
 * Copyright (c) 2004-2014 by Aurea, Inc. All Rights Reserved.
 * All use, reproduction, transfer, publication or disclosure is prohibited
 * except as may be expressly permitted in the applicable license agreement.
 */
/**
 * The primary Java class generated for the IPMDataService model.
 * Description: <br><br>
 *
 * Generated 2014-08-03 00:51:09.86
 *
 */
public class IPMDataServiceImpl extends DataService {
	protected static final Log logger = LogFactory.getLog(IPMDataServiceImpl.class);

	public IPMDataServiceImpl() {
		super("IPMDataService");
	}

	public IPMDataServiceImpl(String config) {
		super("IPMDataService", config);
	}


	/**
	 * Insert custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return Error 
 	 */
	public Error Insert(
			Insert Parameter1) {
		incrementMetric("Insert");
		//begin user defined code for operation
		try {
			Error _result = (Error)map(Parameter1, Error.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * UpdateParty custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return Error 
 	 */
	public Error UpdateParty(
			UpdateParty Parameter1) {
		incrementMetric("UpdateParty");
		//begin user defined code for operation
		try {
			Error _result = (Error)map(Parameter1, Error.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * GetOrganizations custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return OrganizationResponse 
 	 */
	public OrganizationResponse GetOrganizations(
			PartyRequest Parameter1) {
		incrementMetric("GetOrganizations");
		//begin user defined code for operation
		try {
			OrganizationResponse _result = (OrganizationResponse)map(Parameter1, OrganizationResponse.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * GetPartyInformation custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return PartyResponse 
 	 */
	public PartyResponse GetPartyInformation(
			PartyRequest Parameter1) {
		incrementMetric("GetPartyInformation");
		//begin user defined code for operation
		try {
			PartyResponse _result = (PartyResponse)map(Parameter1, PartyResponse.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * GetPaymentAccountUpdates custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return PaymentAccountResponse 
 	 */
	public PaymentAccountResponse GetPaymentAccountUpdates(
			PaymentAccountRequest Parameter1) {
		incrementMetric("GetPaymentAccountUpdates");
		//begin user defined code for operation
		try {
			PaymentAccountResponse _result = (PaymentAccountResponse)map(Parameter1, PaymentAccountResponse.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * GetPersons custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return PersonResponse 
 	 */
	public PersonResponse GetPersons(
			PartyRequest Parameter1) {
		incrementMetric("GetPersons");
		//begin user defined code for operation
		try {
			PersonResponse _result = (PersonResponse)map(Parameter1, PersonResponse.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * GetPositions custom method
	 * Description: 
	 * @param request 
 	 * @return PositionResponse 
 	 */
	public PositionResponse GetPositions(
			PositionRequest request) {
		incrementMetric("GetPositions");
		//begin user defined code for operation
		try {
			PositionResponse _result = (PositionResponse)map(request, PositionResponse.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * GetTransactions custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return TransactionResponse 
 	 */
	public TransactionResponse GetTransactions(
			TransactionRequest Parameter1) {
		incrementMetric("GetTransactions");
		//begin user defined code for operation
		try {
			TransactionResponse _result = (TransactionResponse)map(Parameter1, TransactionResponse.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * GetUserInformation custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return UserResponse 
 	 */
	public UserResponse GetUserInformation(
			PartyRequest Parameter1) {
		incrementMetric("GetUserInformation");
		//begin user defined code for operation
		try {
			UserResponse _result = (UserResponse)map(Parameter1, UserResponse.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
	 * Update custom method
	 * Description: 
	 * @param Parameter1 
 	 * @return Error 
 	 */
	public Error Update(
			Update Parameter1) {
		incrementMetric("Update");
		//begin user defined code for operation
		try {
			Error _result = (Error)map(Parameter1, Error.class);
			return _result;
		} finally {
			completeTransaction();
		}
		//end user defined code for operation
	}

	/**
		 * ValidateForInsert custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return Error 
	 	 */
		public Error ValidateForInsert(
				Insert Parameter1) {
			incrementMetric("ValidateForInsert");
			//begin user defined code for operation
			try {
				Error _result = (Error)map(Parameter1, Error.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * ValidateForUpdate custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return Error 
	 	 */
		public Error ValidateForUpdate(
				Update Parameter1) {
			incrementMetric("ValidateForUpdate");
			//begin user defined code for operation
			try {
				Error _result = (Error)map(Parameter1, Error.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * ValidateForUpdateParty custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return Error 
	 	 */
		public Error ValidateForUpdateParty(
				UpdateParty Parameter1) {
			incrementMetric("ValidateForUpdateParty");
			//begin user defined code for operation
			try {
				Error _result = (Error)map(Parameter1, Error.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetAgencyEmailsForAgent custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return EmailResponse 
	 	 */
		public EmailResponse GetAgencyEmailsForAgent(
				EmailRequest Parameter1) {
			incrementMetric("GetAgencyEmailsForAgent");
			//begin user defined code for operation
			try {
				EmailResponse _result = (EmailResponse)map(Parameter1, EmailResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetAgreementParticipants custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return AgreementParticipantResponse 
	 	 */
		public AgreementParticipantResponse GetAgreementParticipants(
				AgreementParticipantRequest Parameter1) {
			incrementMetric("GetAgreementParticipants");
			//begin user defined code for operation
			try {
				AgreementParticipantResponse _result = (AgreementParticipantResponse)map(Parameter1, AgreementParticipantResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetAgentEmailsForAgency custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return EmailResponse 
	 	 */
		public EmailResponse GetAgentEmailsForAgency(
				EmailRequest Parameter1) {
			incrementMetric("GetAgentEmailsForAgency");
			//begin user defined code for operation
			try {
				EmailResponse _result = (EmailResponse)map(Parameter1, EmailResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetAppointments custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return AppointmentResponse 
	 	 */
		public AppointmentResponse GetAppointments(
				AppointmentRequest Parameter1) {
			incrementMetric("GetAppointments");
			//begin user defined code for operation
			try {
				AppointmentResponse _result = (AppointmentResponse)map(Parameter1, AppointmentResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetNPNFromNIPR custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return NPNResponse 
	 	 */
		public NPNResponse GetNPNFromNIPR(
				NPNRequest Parameter1) {
			incrementMetric("GetNPNFromNIPR");
			//begin user defined code for operation
			try {
				NPNResponse _result = (NPNResponse)map(Parameter1, NPNResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * DownloadUpdateFromPDB custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return DownloadUpdateFromPDBResponse 
	 	 */
		public DownloadUpdateFromPDBResponse DownloadUpdateFromPDB(
				DownloadUpdateFromPDBRequest Parameter1) {
			incrementMetric("DownloadUpdateFromPDB");
			//begin user defined code for operation
			try {
				DownloadUpdateFromPDBResponse _result = (DownloadUpdateFromPDBResponse)map(Parameter1, DownloadUpdateFromPDBResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetPartyEmails custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return EmailResponse 
	 	 */
		public EmailResponse GetPartyEmails(
				EmailRequest Parameter1) {
			incrementMetric("GetPartyEmails");
			//begin user defined code for operation
			try {
				EmailResponse _result = (EmailResponse)map(Parameter1, EmailResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetContactPoints custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return ContactPointResponse 
	 	 */
		public ContactPointResponse GetContactPoints(
				ContactPointRequest Parameter1) {
			incrementMetric("GetContactPoints");
			//begin user defined code for operation
			try {
				ContactPointResponse _result = (ContactPointResponse)map(Parameter1, ContactPointResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetLicenses custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return LicenseResponse 
	 	 */
		public LicenseResponse GetLicenses(
				LicenseRequest Parameter1) {
			incrementMetric("GetLicenses");
			//begin user defined code for operation
			try {
				LicenseResponse _result = (LicenseResponse)map(Parameter1, LicenseResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetContactPointUsages custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return ContactPointUsageResponse 
	 	 */
		public ContactPointUsageResponse GetContactPointUsages(
				ContactPointUsageRequest Parameter1) {
			incrementMetric("GetContactPointUsages");
			//begin user defined code for operation
			try {
				ContactPointUsageResponse _result = (ContactPointUsageResponse)map(Parameter1, ContactPointUsageResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetRegulatoryAction custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return RegulatoryActionResponse 
	 	 */
		public RegulatoryActionResponse GetRegulatoryAction(
				RegulatoryActionRequest Parameter1) {
			incrementMetric("GetRegulatoryAction");
			//begin user defined code for operation
			try {
				RegulatoryActionResponse _result = (RegulatoryActionResponse)map(Parameter1, RegulatoryActionResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetCorrespondenceEmails custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return EmailResponse 
	 	 */
		public EmailResponse GetCorrespondenceEmails(
				CorrespondenceEmailRequest Parameter1) {
			incrementMetric("GetCorrespondenceEmails");
			//begin user defined code for operation
			try {
				EmailResponse _result = (EmailResponse)map(Parameter1, EmailResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetNIPRDetailsForAppointment custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return NIPRDetailsForAppointmentResponse 
	 	 */
		public NIPRDetailsForAppointmentResponse GetNIPRDetailsForAppointment(
				NIPRDetailsForAppointmentRequest Parameter1) {
			incrementMetric("GetNIPRDetailsForAppointment");
			//begin user defined code for operation
			try {
				NIPRDetailsForAppointmentResponse _result = (NIPRDetailsForAppointmentResponse)map(Parameter1, NIPRDetailsForAppointmentResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * CheckPartyRecontractability custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return checkPartyRecontractabilityResponse 
	 	 */
		public checkPartyRecontractabilityResponse CheckPartyRecontractability(
				checkPartyRecontractabilityRequest Parameter1) {
			incrementMetric("CheckPartyRecontractability");
			//begin user defined code for operation
			try {
				checkPartyRecontractabilityResponse _result = (checkPartyRecontractabilityResponse)map(Parameter1, checkPartyRecontractabilityResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * CheckCancelledin90days custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return Cancelledin90daysResponse 
	 	 */
		public Cancelledin90daysResponse CheckCancelledin90days(
				Cancelledin90daysRequest Parameter1) {
			incrementMetric("CheckCancelledin90days");
			//begin user defined code for operation
			try {
				Cancelledin90daysResponse _result = (Cancelledin90daysResponse)map(Parameter1, Cancelledin90daysResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * checkEOAndBCWaived custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return EOAndBCWaivedResponse 
	 	 */
		public EOAndBCWaivedResponse checkEOAndBCWaived(
				EOAndBCWaivedRequest Parameter1) {
			incrementMetric("checkEOAndBCWaived");
			//begin user defined code for operation
			try {
				EOAndBCWaivedResponse _result = (EOAndBCWaivedResponse)map(Parameter1, EOAndBCWaivedResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

		/**
		 * GetOtherCompanyAppointments custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return OtherCompanyAppointmentsResponse 
	 	 */
		public OtherCompanyAppointmentsResponse GetOtherCompanyAppointments(
				OtherCompanyAppointmentsRequest Parameter1) {
			incrementMetric("GetOtherCompanyAppointments");
			//begin user defined code for operation
			try {
				OtherCompanyAppointmentsResponse _result = (OtherCompanyAppointmentsResponse)map(Parameter1, OtherCompanyAppointmentsResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}
	/**
		 * GetChildPositionTypes custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return ChildPositionTypeResponse 
	 	 */
		public ChildPositionTypeResponse GetChildPositionTypes(
				ChildPositionTypeRequest Parameter1) {
			incrementMetric("GetChildPositionTypes");
			//begin user defined code for operation
			try {
				ChildPositionTypeResponse _result = (ChildPositionTypeResponse)map(Parameter1, ChildPositionTypeResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

		/**
			 * GetAPIDForCase custom method
			 * Description: 
			 * @param Parameter1 
		 	 * @return APIDForCaseResponse 
		 	 */
			public APIDForCaseResponse GetAPIDForCase(
					APIDForCaseRequest Parameter1) {
				incrementMetric("GetAPIDForCase");
				//begin user defined code for operation
				try {
					APIDForCaseResponse _result = (APIDForCaseResponse)map(Parameter1, APIDForCaseResponse.class);
					return _result;
				} finally {
					completeTransaction();
				}
				//end user defined code for operation
			}
	/**
		 * GetErrorAndOmission custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return ErrorAndOmissionResponse 
	 	 */
		public ErrorAndOmissionResponse GetErrorAndOmission(
				ErrorAndOmissionRequest Parameter1) {
			incrementMetric("GetErrorAndOmission");
			//begin user defined code for operation
			try {
				ErrorAndOmissionResponse _result = (ErrorAndOmissionResponse)map(Parameter1, ErrorAndOmissionResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetPaymentAccounts custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return PaymentAccountsResponse 
	 	 */
		public PaymentAccountsResponse GetPaymentAccounts(
				PaymentAccountsRequest Parameter1) {
			incrementMetric("GetPaymentAccounts");
			//begin user defined code for operation
			try {
				PaymentAccountsResponse _result = (PaymentAccountsResponse)map(Parameter1, PaymentAccountsResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * SubmitBackgroundCheckRequest custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return SubmitBackgroundCheckResponse 
	 	 */
		public SubmitBackgroundCheckResponse SubmitBackgroundCheckRequest(
				SubmitBackgroundCheckRequest Parameter1) {
			incrementMetric("SubmitBackgroundCheckRequest");
			//begin user defined code for operation
			try {
				SubmitBackgroundCheckResponse _result = (SubmitBackgroundCheckResponse)map(Parameter1, SubmitBackgroundCheckResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetBackgroundCheckResponse custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return BackgroundCheckResponse 
	 	 */
		public BackgroundCheckResponse GetBackgroundCheckResponse(
				BackgroundCheckResponseRequest Parameter1) {
			incrementMetric("GetBackgroundCheckResponse");
			//begin user defined code for operation
			try {
				BackgroundCheckResponse _result = (BackgroundCheckResponse)map(Parameter1, BackgroundCheckResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * setupDCMData custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return setupDCMDataResponse 
	 	 */
		public setupDCMDataResponse setupDCMData(
				setupDCMDataRequest Parameter1) {
			incrementMetric("setupDCMData");
			//begin user defined code for operation
			try {
				setupDCMDataResponse _result = (setupDCMDataResponse)map(Parameter1, setupDCMDataResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
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
			incrementMetric("GetHierarchyPositionForWelcomeLetter");
			//begin user defined code for operation
			try {
				HierarchyPositionResponse _result = (HierarchyPositionResponse)map(Parameter1, HierarchyPositionResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetPositionsDetailed custom method
		 * Description: 
		 * @param request 
	 	 * @return PositionResponse 
	 	 */
		public PositionResponse GetPositionsDetailed(
				PositionRequest request) {
			incrementMetric("GetPositionsDetailed");
			//begin user defined code for operation
			try {
				PositionResponse _result = (PositionResponse)map(request, PositionResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetPositionsDetailed custom method
		 * Description: 
		 * @param request 
	 	 * @return PositionDetailedResponse 
	 	 */
		public PositionDetailedResponse GetPositionsDetailed(
				PositionDetailedRequest request) {
			incrementMetric("GetPositionsDetailed");
			//begin user defined code for operation
			try {
				PositionDetailedResponse _result = (PositionDetailedResponse)map(request, PositionDetailedResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetFIAgreements custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return FIAgreementsResponse 
	 	 */
		public FIAgreementsResponse GetFIAgreements(
				FIAgreementsRequest Parameter1) {
			incrementMetric("GetFIAgreements");
			//begin user defined code for operation
			try {
				FIAgreementsResponse _result = (FIAgreementsResponse)map(Parameter1, FIAgreementsResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetUplineParty custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return UplinePartyResponse 
	 	 */
		public UplinePartyResponse GetUplineParty(
				UplinePartyRequest Parameter1) {
			incrementMetric("GetUplineParty");
			//begin user defined code for operation
			try {
				UplinePartyResponse _result = (UplinePartyResponse)map(Parameter1, UplinePartyResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * CheckPartyChannelEligibility custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return ChannelEligibilityResponse 
	 	 */
		public ChannelEligibilityResponse CheckPartyChannelEligibility(
				PartyChannelEligibilityRequest Parameter1) {
			incrementMetric("CheckPartyChannelEligibility");
			//begin user defined code for operation
			try {
				ChannelEligibilityResponse _result = (ChannelEligibilityResponse)map(Parameter1, ChannelEligibilityResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetActiveProductProfiles custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return ActiveProductProfileResponse 
	 	 */
		public ActiveProductProfileResponse GetActiveProductProfiles(
				ActiveProductProfileRequest Parameter1) {
			incrementMetric("GetActiveProductProfiles");
			//begin user defined code for operation
			try {
				ActiveProductProfileResponse _result = (ActiveProductProfileResponse)map(Parameter1, ActiveProductProfileResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetLoaData custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return LOAResponse 
	 	 */
		public LOAResponse GetLoaData(
				LOARequest Parameter1) {
			incrementMetric("GetLoaData");
			//begin user defined code for operation
			try {
				LOAResponse _result = (LOAResponse)map(Parameter1, LOAResponse.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

	/**
		 * GetLastStatusReason custom method
		 * Description: 
		 * @param Parameter1 
	 	 * @return PartyStatusReason 
	 	 */
		public PartyStatusReason GetLastStatusReason(
				PartyStatusRequest Parameter1) {
			incrementMetric("GetLastStatusReason");
			//begin user defined code for operation
			try {
				PartyStatusReason _result = (PartyStatusReason)map(Parameter1, PartyStatusReason.class);
				return _result;
			} finally {
				completeTransaction();
			}
			//end user defined code for operation
		}

}