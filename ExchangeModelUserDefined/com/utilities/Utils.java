package com.utilities;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.vfs.FileObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.pantero.metamodel.config.HTTPSourceLocation;
import com.pantero.metamodel.config.SourceLocation;
import com.pantero.metamodel.expressions.Library.CollectionOverride;
import com.pantero.metamodel.expressions.Library.CollectionOverrideKind;
import com.pantero.metamodel.expressions.Library.Function;
import com.pantero.metamodel.expressions.Library.OverrideType;
import com.pantero.metamodel.expressions.Library.ReturnTypeOverride;
import com.pantero.runtime.datasource.DatasourceObjectManager;
import com.pantero.runtime.entity.Entity;
import com.pantero.runtime.entity.EntityUtils;
import com.pantero.runtime.objectmanager.ModelSession;
import com.pantero.runtime.objectmanager.ObjectManager;
import com.pantero.runtime.util.RuntimeUtils;
import com.pantero.util.collections.CollectionUtils;
import com.pantero.util.io.FileUtils;
import com.pantero.util.xml.XmlUtils;

@com.pantero.metamodel.expressions.Library.Class (
		name="Utils",
		displayName="Utils Functions"	
)
public class Utils {
	private static Map<String,String> POSITION_TYPE = null;
	private static Map<String,String> PARTY_STATUS_TYPE = null;
	
	@Function(
			name="intersection",
			description="Returns elements common to the two collections",
			parameters= {"collection","otherCollection"}
			)
	@ReturnTypeOverride(overrideType=OverrideType.COLLECTION, parameterIndices={0,1})
	@CollectionOverride(kind=CollectionOverrideKind.MERGE,narrow=true)
	public static Collection intersection(Collection a, Collection b) {
		if (a == null && b == null)
			return null;
		else if (a == null)
			return b;
		else if (b == null)
			return a;
		return CollectionUtils.intersection(a,b);
	}
	
	@Function(
			name="BooleanToStringYN",
			description="Returns Y for True and N for False",
			parameters= {"boolean"}
			)
	public static String BooleanToStringYN(Boolean b) {
		if (b == null)
			return null;
		else if (b.booleanValue())
			return "Y";
		else
			return "N";
	}
	
	@Function(
			name="EncodeCredentials",
			description="Returns Base64 encoded string",
			parameters= {"String"}
			)
	public static String EncodeCredentials(String credentials) {
		return new String(Base64.encodeBase64(credentials.getBytes()));
	}
	
	@Function(
			name="positionTypeFromNumber",
			description="Returns the text representation of the position number",
			parameters= {"typeNumber"}
			)
	public static String positionTypeFromNumber(String number) {
		if (POSITION_TYPE == null) {
			Map<String,String> map = new HashMap<String,String>();
			try {
				FileObject enumFile = RuntimeUtils.getTestFile("enums.xml");
				Node root = XmlUtils.parseXml(FileUtils.getInputStream(enumFile));
				List<Element> enums = XmlUtils.getNodesByElementName(root, "enum");
				Node relEnum = null;
				for (Element element : enums) {
					if ("RELATIONSHIP".equals(element.getAttribute("id"))) {
							relEnum = element;
							break;
					}
				}
				if (relEnum != null) {
					List<Element> entries = XmlUtils.getNodesByElementName(relEnum, "entry");
					for (Element element : entries) {
						map.put(element.getAttribute("value"), element.getAttribute("name"));
					}
				}
			} catch (Exception e) {
				//log something?
			}
			POSITION_TYPE = map;
		}
		return POSITION_TYPE.get(number);
	}
	
	@Function(
			name="partyStatusTypeFromNumber",
			description="Returns the text representation of the party status number",
			parameters= {"typeNumber"}
			)
	public static String partyStatusTypeFromNumber(String number) {
		if (PARTY_STATUS_TYPE == null) {
			Map<String,String> map = new HashMap<String,String>();
			try {
				FileObject enumFile = RuntimeUtils.getTestFile("enums.xml");
				Node root = XmlUtils.parseXml(FileUtils.getInputStream(enumFile));
				List<Element> enums = XmlUtils.getNodesByElementName(root, "enum");
				Node relEnum = null;
				for (Element element : enums) {
					if ("Party.RecontractableStatus".equalsIgnoreCase(element.getAttribute("id"))) {
							relEnum = element;
							break;
					}
				}
				if (relEnum != null) {
					List<Element> entries = XmlUtils.getNodesByElementName(relEnum, "entry");
					for (Element element : entries) {
						map.put(element.getAttribute("value"), element.getAttribute("name"));
					}
				}
			} catch (Exception e) {
				//log something?
			}
			PARTY_STATUS_TYPE = map;
		}
		return PARTY_STATUS_TYPE.get(number);
	}

	public static String getDataSourceURL(Entity parameter){
		ModelSession session = EntityUtils.getProposedSession(parameter);
		ObjectManager om = session.getObjectManager();
		if (om instanceof DatasourceObjectManager){
			SourceLocation location = ((DatasourceObjectManager)om).getCurrentDataSourceLocation();
			if (location instanceof HTTPSourceLocation){
				return ((HTTPSourceLocation)location).getUrl().toString();
			}
		}
		return null;
	}

	
	@Function(
			name="getValueFromName",
			description="Returns the enum value from enum key",
			parameters= {"String enumName, String key"}
			)
	public static String getValueFromName(String enumName, String key) {
		EnumUtils eUtils = EnumUtils.getInstance();
		return eUtils.getValueFromName(enumName, key);
	}

	@Function(
			name="getNameFromValue",
			description="Returns the enum key from enum value",
			parameters= {"String enumName, String value"}
			)
	public static String getNameFromValue(String enumName, String value) {
		EnumUtils eUtils = EnumUtils.getInstance();
		return eUtils.getNameFromValue(enumName, value);
	}
	
}
