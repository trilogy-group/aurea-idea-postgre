package com.aurea.dcm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.pantero.util.logging.Log;
import com.pantero.util.logging.LogFactory;

/**
 * DCM Class Loader Helper
 */

public class DCMClassLoaderHelper {

	protected static final Log logger = LogFactory.getLog(com.aurea.dcm.DCMClassLoaderHelper.class);

	private static final String BUNDLE_NAME = "DCMClassPath";

	private static final String CLASSPATH_KEY = "MCCFORMULA_CLASSPATH";

	private static TreeMap s_loadedDCMClasses = new TreeMap();

	public static DCMIPMClassLoader s_dcmClassLoader;

	static {

		ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME);
		String mccFormulaClasspath = rb.getString(CLASSPATH_KEY);
		
		logger.debug("Mcc customized class path: " + mccFormulaClasspath);
		if (null == mccFormulaClasspath) {
			throw new RuntimeException("Unable to find property " + CLASSPATH_KEY + " in resource bundle " + BUNDLE_NAME);
		}

		logger.debug("Initializing DCM classpath using path " + mccFormulaClasspath);

		StringTokenizer token = new StringTokenizer(mccFormulaClasspath, java.io.File.pathSeparator);
		ArrayList urlList = new ArrayList();

		while (token.hasMoreTokens()) {

			try {

				String pathElement = token.nextToken();
				logger.debug("pathElement: " + pathElement);
				if(pathElement.endsWith("*")) {

					String dirPath = pathElement.substring(0, pathElement.length()-1);

					logger.debug("without * dirPath: " + dirPath);
					
//					Path pp = FileSystems.getDefault().getPath(dirPath);
//					try {
//						DirectoryStream<Path> dss = Files.newDirectoryStream(pp, "*.jar");
//					 for (Path ppf : dss) {
//						 // Iterate over the paths in the directory and print filenames
//						 System.out.println(pp.getFileName());
//						 logger.debug("JARFILE: " + pp.getFileName());
//					 }
//					} catch (IOException e) {
//						logger.debug("IOEXception : " + e.getMessage());
//						StringWriter sw = new StringWriter();
//						PrintWriter pw = new PrintWriter(sw);
//						e.printStackTrace(pw);
//						logger.debug(sw.toString());
//					}
					
					
					File f = new File(dirPath);
					
					if (f.exists() && f.isDirectory()) {
						ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
						logger.debug("no. of jars : " + names.size());
						for (Iterator iterator = names.iterator(); iterator.hasNext();) {

							String jarFileName = (String) iterator.next();
							String fileName = dirPath + jarFileName;
							File jarFile = new File(fileName);
							
							if(jarFile.isFile()) {
								logger.debug("adding to URL List 1 : " + "file:" + getURLSeparator() + fileName);
								urlList.add(new URL("file:" + getURLSeparator() + fileName));
							}
						}
					}

				} else {
					File f = new File(pathElement);
					if (f.exists() && f.isDirectory()) {
						// very important to have the / a the end of each directory.
						// without them, they won't get picked up by the classloader
						pathElement += File.separator;
					}
					logger.debug("added to urllist :  " + "file:" + getURLSeparator() + pathElement);
					urlList.add(new URL("file:" + getURLSeparator() + pathElement));	
				}

			} catch (MalformedURLException ex) {
				logger.debug("Error while setting up DCM classloader" + ex.getMessage());
				//ex.printStackTrace();
			} catch (Exception ex) {
				logger.debug("Exception while setting up DCM classloader" + ex.getMessage());
				//ex.printStackTrace();
			}

		}

		URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);

		s_dcmClassLoader = new DCMIPMClassLoader(urls, DCMClassLoaderHelper.class.getClassLoader());
		// updated as part of "SSL implementation not available" issue 
		//s_dcmClassLoader = new DCMIPMClassLoader(urls, null);

	}



	private static class DCMIPMClassLoader extends URLClassLoader {

		public DCMIPMClassLoader(URL[] urls, ClassLoader parent) {
			super (urls, parent);
		}

		protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {

			Class c = (Class) s_loadedDCMClasses.get(name);

			if (null == c) {
				try {
					c = loadClassDirect(name, resolve);
				} catch (Exception e) {
					// do nothing, let the parent classLoader resolve it
				} finally {
					if (null == c) {
						c = super.loadClass(name, resolve);
					}
				}
			}

			return c;
		}


		protected Class loadClassDirect(String name, boolean resolve) throws ClassNotFoundException {

			//		URL[] urls = getURLs();
			//	    for (int i = 0; i < urls.length; i++) {
			//	      System.out.println(urls[i].getFile());
			//	    }

			Class c = findClass(name);
			s_loadedDCMClasses.put(name, c);
			if (resolve) {
				resolveClass(c);
			}
			return c;
		}

	};


	/**
	 * Get DCM class loader. This class loader is used for load classes in DCM
	 * @return the DCM class loader
	 */

	public static DCMClassLoaderHelper getDCMClassLoader() {
		return (new DCMClassLoaderHelper());
	}


	private DCMClassLoaderHelper() {}


	public Class loadClass(String className) throws ClassNotFoundException {

		// First, check if the class has already been loaded
		// we're not using findLoadedClasses so that we can ensure
		// all DCM classes get loaded by this class loader

		logger.debug("DCMClassLoaderHelper : Trying to load class - "+className);

		Class c = (Class) s_loadedDCMClasses.get(className);
		if (null == c) {
			logger.debug("DCMClassLoaderHelper : class not already loaded - "+className);
			c = s_dcmClassLoader.loadClassDirect(className, false);
		}
		return (c);

	}


	private static String getURLSeparator() {

		String separator = File.separator + File.separator;
		String osName = System.getProperty("os.name");
		if(osName.startsWith("Windows"))
			separator = "\\";
		return separator;

	}

}

