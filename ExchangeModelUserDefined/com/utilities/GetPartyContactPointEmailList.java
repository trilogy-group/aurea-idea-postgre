package com.utilities;

import java.util.Vector;

import com.aurea.dcm.DCMClassLoaderHelper;

/**
 * 
 * Class gets all party contact point email, the OQL is executed using DCM class by reflection.  
 * 
 * @author marcos.celio
 *
 */
public class GetPartyContactPointEmailList {

	private static final String SINGLETON_SC_MANAGER_CLASS = "com.trilogy.fs.dms.core.SingletonSCManager";
	private static final String GET_MANAGER_METHOD = "getManager";
	private static final String SC_MANAGER_CLASS = "com.trilogy.sc.ISCManager";
	private static final String NEW_QUERY_METHOD = "newQuery";
	private static final String EXECUTE_METHOD = "execute";
	private static final String MOVE_NEXT_METHOD = "moveNext";
	private static final String VALUE_FROM_INDEX_METHOD = "valueFromIndex";
	private static final String oql = "SELECT contactPoint.Address.Email FROM FSContactPoint contactPoint, FSParty party WHERE contactPoint.Party = party and party.Unid = ";
	
	/**
	 * It Retrieves email addresses from party contact point list. 
	 * 
	 * @return Vector - list of email.
	 */
	public static Vector getContactPointEmails(String partyID){
		Vector<String> emails = new Vector<String>();
		try{
			// Loads classpath
			DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
			// Instantiate SingletonSCManager by reflection 
			Class singletonSCManager = dcmClassLoader.loadClass(SINGLETON_SC_MANAGER_CLASS);
			// Creates method "getManager" for execution
			java.lang.reflect.Method getManager = singletonSCManager.getMethod(GET_MANAGER_METHOD);
			// Executes method "getManager"
			Object getManagerObj = getManager.invoke(null);
			// Instantiate ISCManager by reflection
			Class iSCManager = dcmClassLoader.loadClass(SC_MANAGER_CLASS);
			// Cast SingletonSCManager to ISCManager
			getManagerObj = iSCManager.cast(getManagerObj);
			// Creates method "newQuery" for execution
			java.lang.reflect.Method newQuery = getManagerObj.getClass().getMethod(NEW_QUERY_METHOD);
			// Executes method "newQuery"
			Object newQueryObj = newQuery.invoke(getManagerObj);
			// Creates method "execute" for execution
			java.lang.reflect.Method execute = newQueryObj.getClass().getMethod(EXECUTE_METHOD, new Class[] {String.class});
			// Executes method "execute"
			Object resultSet = execute.invoke(newQueryObj, new Object[] {oql + "'" + partyID + "'"});
			// Creates method "moveNext" for execution
			java.lang.reflect.Method moveNext = resultSet.getClass().getMethod(MOVE_NEXT_METHOD);
			// Executes method "execute"
			boolean flag = Boolean.valueOf(moveNext.invoke(resultSet).toString());
			while(flag){
				// Creates method "valueFromIndex" for execution
				java.lang.reflect.Method valueFromIndex = resultSet.getClass().getMethod(VALUE_FROM_INDEX_METHOD, Integer.TYPE);
				// Gets email object from result set
				Object emailObj = valueFromIndex.invoke(resultSet, 0);
				if(emailObj != null){
					// Add email to the list
					emails.add(emailObj.toString());
				}				
				flag = Boolean.valueOf(moveNext.invoke(resultSet).toString());
			}
		}catch(Exception e){
			return null;
		}
		return emails;
	}
}
