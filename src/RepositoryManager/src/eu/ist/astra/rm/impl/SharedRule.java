package eu.ist.astra.rm.impl;

import eu.ist.astra.rm.xml.XMLFunctionalities;

/**
 * 
 * Class which represents a rule seen from the back-end side. For the moment we have
 * considered to store:
 * 
 * - id		Rule identifier in the standard format: user@astra:application_id_#rule
 * - description Rule description based on the information of the XML file (in a readable way)
 * - xml_data	String with the description rule file
 * 
 * @author David Rozas & Alfredo Perez
 *
 */
public class SharedRule 
{
	private String id;
	private String xml_data;
	private String description;
	
	/**
	 * 
	 * Shared Rule constructor
	 * 
	 * 
	 * @param id	Rule identifier in the standard format
	 * @param xml_data	Description of the rule in XML
	 */
	public SharedRule(String id, String xml_data)
	{
		this.id=id;
		this.xml_data=xml_data;
		this.description = XMLFunctionalities.parseRuleDescription(this.xml_data);
	}
	
	/**
	 * 
	 * Returns the rule id
	 * 
	 * @return	Rule identifier in the standard format
	 */
	public String getId()
	{
		return this.id;
	}
	
	/**
	 * 
	 * Returns the description of the rule in a readable format
	 * 
	 * @return Description of the rule
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * 
	 * Returns the description of the rule in xml
	 * 
	 * @return rule in xml
	 */
	public String getXmlData()
	{
		return this.xml_data;
	}
	
}
