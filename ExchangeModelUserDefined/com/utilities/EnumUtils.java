package com.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.aurea.dcm.DCMClassLoaderHelper;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;

public class EnumUtils {

	protected static final Log logger = LogFactory.getLog(com.utilities.EnumUtils.class);
	
	private static EnumUtils instance;
	protected Map<String, EnumWrapper> enumsCache = null;
	
	public Map<String, EnumWrapper> getEnumsCache() {
		return enumsCache;
	}

	public void setEnumsCache(Map<String, EnumWrapper> enumsCache) {
		this.enumsCache = enumsCache;
	}

	private EnumUtils() {
		enumsCache = new HashMap<String, EnumWrapper>();
	}
	
	public static synchronized EnumUtils getInstance() {
		if(instance == null){
            instance = new EnumUtils();
        }
        return instance;
	}
	
	public String getNameFromValue(String enumName, String value) {
		
		logger.debug("EnumUtils.getNameFromValue " + enumName + ", " + value);
		EnumWrapper wrapperObj;
		if(enumsCache.containsKey(enumName)) {
			wrapperObj = (EnumWrapper) enumsCache.get(enumName);
		} else {
			wrapperObj = addEnumDataToCache(enumName);
			if(wrapperObj == null)
				return "";
		}
		
		Map valKeyMap = wrapperObj.m_valueKeyMap;
		if(valKeyMap.containsKey(value)) {
			return (String) valKeyMap.get(value);
		} else {
			return "";
		}
	}
	
	public String getValueFromName(String enumName, String enumKey) {
		
		logger.debug("EnumUtils.getValueFromName " + enumName + ", " + enumKey);
		EnumWrapper wrapperObj;
		if(enumsCache.containsKey(enumName)) {
			wrapperObj = (EnumWrapper) enumsCache.get(enumName);
		} else {
			wrapperObj = addEnumDataToCache(enumName);
			if(wrapperObj == null)
				return "";
		}
		
		Map keyValMap = wrapperObj.m_keyValueMap;
		if(keyValMap.containsKey(enumKey)) {
			return (String) keyValMap.get(keyValMap);
		} else {
			return "";
		}
	}
	
	
	private EnumWrapper addEnumDataToCache(String enumName) {
		
		DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
		logger.debug("Calling IDEAUtils.getEnumData using DCMClassLoaderHelper");
		ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
		
		Object retVal = null;
		boolean success = false;

		try {
            Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
            Class clz = dcmClassLoader.loadClass("com.aurea.ipm.utils.impl.IDEAUtils");
            Method method = clz.getMethod("getEnumData", new Class[] {String.class});
            retVal = method.invoke(null, new Object[] {enumName});
            success = true;
		} catch (Exception e) {
			Thread.currentThread().setContextClassLoader(oldClassLoader);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION : " + sw.toString());			
			success = false;
		} finally {
            Thread.currentThread().setContextClassLoader(oldClassLoader);
        }
	
		logger.debug("Back from IDEAUtils.getEnumData. Success=" + success);
		
		EnumWrapper enumWrapper = null;
		
		if(success && retVal != null) {			
			logger.debug("Enum Data retrieved for enum " + enumName);
			Vector enumData = (Vector) retVal;
			enumWrapper = new EnumWrapper(enumName, (Map) enumData.get(0), (Map) enumData.get(1));
			enumsCache.put(enumName, enumWrapper);
			
		} else {
			logger.debug("Error in getting Enum Data for enum " + enumName);
			return null;
		}
		
		return enumWrapper;
	}
	
	
	public class EnumWrapper {
		String m_enumName;
		Map m_keyValueMap;
		Map m_valueKeyMap;
		
		public EnumWrapper() {
			m_enumName = "";
			m_keyValueMap = new HashMap<String, String>();
			m_valueKeyMap = new HashMap<String, String>();
		}
		
		public EnumWrapper(String enumName, Map keyValueMap, Map valueKeyMap) {
			m_enumName = enumName;
			m_keyValueMap = keyValueMap;
			m_valueKeyMap = valueKeyMap;
		}
	}
}
