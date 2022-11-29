package com.aurea.dcm.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.vfs.FileObject;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.aurea.dcm.DCMClassLoaderHelper;
import com.pantero.metamodel.ExchangeModel;
import com.pantero.runtime.PanteroRuntimeException;
import com.pantero.util.io.FileUtils;
import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;
import com.progress.dataxtend.si.flatfile.runtime.FlatFileEntity.RootEntity;

public class DCMLoader {
	protected static final Log logger = LogFactory.getLog(com.aurea.dcm.loader.DCMLoader.class);

	private static final String CONFIG_FILE_NAME   = "config.properties";
	private static final String OUTPUT_FILE_PREFIX = "DXSItoDCNFile";
	private static final String OUTPUT_FILE_SUFFIX = "csv";

	public Properties configProp ; 
	public FileObject configFile ; 
	private String specName;
	private String specLocation;
	public List<dcm.MessageAlert> logMsgAlertList = new ArrayList<dcm.MessageAlert>();

	public DCMLoader() {
		super();
	}


	public boolean executeLoaderTask(String[] loaderArgs, String dxsiOperationName, Map antParams) {

		Object retVal = null;

		if("InsertUpdateAppointment".equalsIgnoreCase(dxsiOperationName)) {
			logger.debug("AAA: APPT_Loader_Invoked");
			return executeLoaderAntTask(antParams, dxsiOperationName);
		
		} else {
			logger.debug("AAA: NON APPT_Loader_Invoked");

			DCMClassLoaderHelper dcmClassLoader = DCMClassLoaderHelper.getDCMClassLoader();
			printCP(dcmClassLoader.s_dcmClassLoader);

			logger.debug("Calling DMSLoader using DCMClassLoaderHelper");

			ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(DCMClassLoaderHelper.s_dcmClassLoader);
				Class clz = dcmClassLoader.loadClass("com.trilogy.fs.dms.tools.DMSLoader");
				Method method = clz.getMethod("run", new Class[] {String[].class});
				//Object service = clz.newInstance();
				retVal = method.invoke(null, new Object[] {loaderArgs});
				return true;
			} catch (Exception e) {
				//System.out.println("Exception in executeLoaderTask : " + e.getMessage());
				//e.printStackTrace(System.out);
				Thread.currentThread().setContextClassLoader(oldClassLoader);
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				logger.debug("EXCEPTION : " + sw.toString());			
				addLogMsgAlert("0", "Error", "Exception: " + e.getMessage(), dxsiOperationName);
				return false;
			} finally {
				Thread.currentThread().setContextClassLoader(oldClassLoader);
			}
		}
		
	}

	private boolean executeLoaderAntTask(Map params, String dxsiOperationName) {		
		Project project = new Project();
		try {
			logger.debug("executeLoaderAntTask starting " + dxsiOperationName);
			String buildXmlFileFullPath = getProperty("buildFile_Location").trim();
			logger.debug("executeLoaderAntTask build " + buildXmlFileFullPath);
			long startTime, totalTime;
			startTime=System.currentTimeMillis();
		
			// Prepare Ant project
			
			File buildFile = new File(buildXmlFileFullPath);

			// Capture event for Ant script build start / stop / failure
		
			project.fireBuildStarted();
			project.init();
			ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
			project.addReference("ant.projectHelper", projectHelper);
			projectHelper.parse(project, buildFile);
			// If no target specified then default target will be executed.
			String targetToExecute = "LoadFile";
			Iterator<String> paramsIter = params.keySet().iterator();
			logger.debug("executeLoaderAntTask iterating params " + params.size());
			while (paramsIter.hasNext()) {
				String key = paramsIter.next();
				String value = (String) params.get(key);
				logger.debug("executeLoaderAntTask iterating param " + key + " = " + value);
				project.setUserProperty(key, value);
			}
			// Execute the DCM Target			
			logger.debug("executeLoaderAntTask calling ant target ");
			project.executeTarget(targetToExecute);
			logger.debug("executeLoaderAntTask ant call complete");
			project.fireBuildFinished(null);
			logger.debug("Ant Script execution completed");
			totalTime=System.currentTimeMillis() - startTime;
			logger.info("Executing ANT task :" + totalTime);
			return true;
		} catch (BuildException buildException) {
			project.fireBuildFinished(buildException);
			logger.info("!!! Unable To Process executeLoaderAntTask Ant Build !!!");
			throw new RuntimeException("!!! Unable To Process Ant Build !!!",
					buildException);
		} catch (Exception e) {
			//System.out.println("Exception in executeLoaderAntTask : " + e.getMessage());
			//e.printStackTrace(System.out);			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			logger.debug("EXCEPTION : " + sw.toString());			
			addLogMsgAlert("0", "Error", "Exception: " + e.getMessage(), dxsiOperationName);
			return false;
		}
	}


	public static void printCP(URLClassLoader classLoader) {

        //Get the URLs
        URL[] urls = classLoader.getURLs();

		for(int i=0; i< urls.length; i++)
		{
			logger.debug("jar: " + urls[i].getFile());
			//System.out.println(urls[i].getFile());
		}       

    }
	
	/**
	 * invokeDCMLoader method Description: This method will save the DXSI Output
	 * to a temporary file and calls 'invokeDCMLoader' method
	 * 
	 * @param configFile - File object which refers to the input file 'config.properties'
	 * @param dxsiOuputFile - The temporary output file which stores DXSI output
	 * @param dxsiOperationName - The current DataSource Operation name
	 * @return 
	 * @throws ParseException 
	 */
	public List<dcm.MessageAlert> invokeDCMLoader(File dxsiOuputFile, String dxsiOperationName, boolean validateOnly) 
			throws FileNotFoundException, IOException, ParseException {

		logger.debug("The DXSI DataSource Operation invoked is : " + dxsiOperationName);
		logger.debug("Java Version : " + System.getProperty("java.version"));

		String[] loaderArgs = new String[9];
		HashMap<String, String> antParams = new HashMap<String, String>();
				
		loaderArgs[0] = getProperty("mccProps").trim(); // 1st Parameter is mccProps

		specName = getSpecName(dxsiOperationName);

		if (specName.equalsIgnoreCase("Not Found")) {
			logger.debug(" NO DCM Spec file found to validate against the DXSI Output Data \n");
			return null; 
		}

		specLocation = getProperty("specsLocation").trim();
		loaderArgs[1] = specLocation + "/" + specName; // 2nd Parameter is SpecFileLocation
		antParams.put("SPEC_FILE", specLocation + "/" + specName);
		loaderArgs[2] = getProperty("log4jProps").trim(); // 3rd Parameter is log4jProps

		//All other parameters should be prefixed by -D
		loaderArgs[3] = "-DFILENAME=" + dxsiOuputFile.getAbsolutePath();
		antParams.put("DATA_FILE", dxsiOuputFile.getAbsolutePath());
		loaderArgs[4] = "-DBATCH_SIZE=" + getProperty("BATCH_SIZE").trim();
		antParams.put("BATCH_SIZE", getProperty("BATCH_SIZE").trim());
		loaderArgs[5] = "-DOUTPUT_DIR=" + getProperty("OUTPUT_DIR").trim();
		antParams.put("OUTPUT_DIR", getProperty("OUTPUT_DIR").trim());
		
		if(validateOnly) {
			logger.debug("DCMLoader.java : " + validateOnly);
			loaderArgs[6] = "-DSTRATEGY=validator_validation_strategy";
			antParams.put("STRATEGY", "validator_validation_strategy");
			loaderArgs[7] = "-DSANITY_CHECK=false";
			antParams.put("SANITY_CHECK", "false");
		} else {
			loaderArgs[6] = "-DSTRATEGY=dms_validator_strategy";
			antParams.put("STRATEGY", "dms_validator_strategy");
			loaderArgs[7] = "-DSANITY_CHECK=true";
			antParams.put("SANITY_CHECK", "true");
		}

		Date currentDate = new Date();
		long time = currentDate.getTime();
		String errorFileName = "Errors_" + time + ".csv";
		logger.debug("Error file name: " + errorFileName);
		loaderArgs[8] = "-DERROR_FILENAME=" + errorFileName;
		antParams.put("ERROR_FILENAME", errorFileName);
		
		// Create the output directory for DCM logs if the directory doesn't exist
		String outputDir = getProperty("OUTPUT_DIR").trim();
		File f = new File(outputDir);
		if (!f.exists())
			logger.debug("DCM Logs folder creation @ " + f.getAbsolutePath(), f.mkdirs() ? " Succesful!" : " Failed" +"\n");
		else
			logger.debug("DCM Logs folder already exists @ : " + f.getAbsolutePath() + "\n");

		// Get the DCM SpecName based on the DXSI DataSource Operation Name and invoke the loader

		logger.debug("Invoking Loader with parameters = \n");
		for (int i = 0; i < loaderArgs.length; i++) {
			logger.debug(loaderArgs[i]);
		}

		boolean success = executeLoaderTask(loaderArgs, dxsiOperationName, antParams);

		if(success) {
			parseLog(outputDir, errorFileName, dxsiOperationName) ;
		} else {
			logger.debug("RunTime exception when running loader. Refer to loader.log");
		}
		
		return getLogMsgAlertList();	
		
	}


	private String getProperty(String property) {		
		try{
			return configProp.getProperty(property);
		}catch (Throwable e) {
			throw new PanteroRuntimeException(e);
		}		
	}

	/**
	 * getSpecName method Description: This method will help in finding out the
	 * DCM Spec file to be used depending on the datasource operation invoked.
	 * 
	 * @param dxsiOperationName
	 *            - The current DataSource Operation name
	 * @param configFile
	 *            - The configuration file where DXSI Datasource Operation name
	 *            and DCM Spec file key-value pairs are listed
	 */
	private String getSpecName(String dxsiOperationName) throws FileNotFoundException, IOException {
		String dcmSpecName = "Not Found";
		Enumeration e = configProp.propertyNames();
		boolean flag = false;
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			if ((key.startsWith("dxsi.")) && (key.substring(5, key.length()).equalsIgnoreCase(dxsiOperationName.trim()))) {
				dcmSpecName = (String) getProperty(key);
				flag = true;
				break;
			}
		}
		logger.debug(flag ? "The DCM Spec file to be used is : " + dcmSpecName
				: "DCM Spec file UNIdentified - DXSI DataSource Operation NOT FOUND in the list \n");
		return dcmSpecName;
	}

	public void loadConfigFile(FileObject configFile) throws FileNotFoundException, IOException	{
		configProp = new Properties();
		configProp.load(FileUtils.getInputStream(configFile));
	}

	/**
	 * loadDCM method Description: This method will save the DXSI Output to a
	 * temporary file and calls 'invokeDCMLoader' method
	 * 
	 * @param Parameter1 - DXSI Output
	 * @param dxsiOperationName - The current DataSource Operation name
	 * @throws ParseException 
	 */
	public List<dcm.MessageAlert> loadDCM(RootEntity Parameter1, String dxsiOperationName, boolean validateOnly) 
			throws FileNotFoundException, IOException, ParseException {
		if (Parameter1 == null)
			return null;

		ExchangeModel exModel = Parameter1._getEntityType() == null ? null : Parameter1._getEntityType().getExchangeModel();

		FileObject projectDirectory = exModel.getProject().getRootDirectory();
		try {
			configFile = projectDirectory.getChild(CONFIG_FILE_NAME);
			loadConfigFile(configFile);
			File dxsiOutputFile = FileUtils.createTempFile(OUTPUT_FILE_PREFIX, OUTPUT_FILE_SUFFIX, true);
			OutputStream stream = new FileOutputStream(dxsiOutputFile);
			try {
				logger.debug("DXSI Ouput File : " + dxsiOutputFile);
				Parameter1._getSource(true).save(stream, "utf-8");
				logger.debug("Reading Configuration File : " + configFile.toString());
				return invokeDCMLoader(dxsiOutputFile, dxsiOperationName, validateOnly);
			} finally {
				stream.close();
			}
		} catch (Exception ex) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			logger.debug("A EXCEPTION : " + sw.toString());
			logger.debug("Exception: " + ex.getMessage());
			addLogMsgAlert("0", "Error", "Exception: " + ex.getMessage(), dxsiOperationName);			
		}
		return getLogMsgAlertList();
	}


	private void addLogMsgAlert(String rowNumber, String severity, String message, String dxsiOperationName) {
		dcm.MessageAlert alert = new dcm.MessageAlert();
		alert.setRowNumber(rowNumber);
		alert.setSeverity(severity);
		alert.setMessage(message);
		alert.setOperation(dxsiOperationName);
		getLogMsgAlertList().add(alert);
	}


	public void parseLog(String errorFilePath, String errorFileName, String dxsiOperationName) {
		logger.debug("Reading error file " + errorFilePath + "/" + errorFileName);
		logMsgAlertList = new ArrayList<dcm.MessageAlert>();

		File file = new File(errorFilePath + "/" + errorFileName);
		FileInputStream fis = null;
		BufferedReader reader = null;

		try {

			fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis));

			String line = reader.readLine();
			while(line != null) {
				if(!"".equals(line)) {
					String row = null;
					String severity = null;
					String message = null;
					
					int rowIndex = line.indexOf(":");
					if(rowIndex != -1) { 
						row = line.substring(0, rowIndex);
					}
					int severityIndex = line.indexOf(":", rowIndex+1);
					if(severityIndex != -1) { 
						severity = line.substring(rowIndex+1, severityIndex);
					}
					message = line.substring(severityIndex+1);
					String updatedMessage = message.replaceAll("\\:", "-");
					
					addLogMsgAlert(row, severity, updatedMessage, dxsiOperationName);
				}
				line = reader.readLine();
			}           

		} catch (FileNotFoundException ex) {
			logger.debug("FileNotFoundException" + ex.getMessage());
			addLogMsgAlert("0", "Error", "FileNotFoundException: " + ex.getMessage(), dxsiOperationName);
		} catch (IOException ex) {
			logger.debug("IOException" + ex.getMessage());
			addLogMsgAlert("0", "Error", "IOException: " + ex.getMessage(), dxsiOperationName);
		} finally {
			try {
				reader.close();
				fis.close();
			} catch (IOException ex) {
				logger.debug("IOException" + ex.getMessage());
				addLogMsgAlert("0", "Error", "IOException: " + ex.getMessage(), dxsiOperationName);
			}
		}

		if(file.delete()){
			logger.debug(file.getName() + " is deleted.");
		}else{
			logger.debug("Delete operation is failed.");
		}
	}

	public List<dcm.MessageAlert> getLogMsgAlertList() {
		return logMsgAlertList;
	}
}