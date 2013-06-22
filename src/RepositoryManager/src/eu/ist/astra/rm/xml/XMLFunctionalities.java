package eu.ist.astra.rm.xml;

import java.io.CharArrayReader;
import java.io.Reader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * Auxiliar XML functionalities for the repository
 * 
 * @author David Rozas
 *
 */
public class XMLFunctionalities {
	
	/**
	 * 
	 * Returns the XML file which describes the rule. The difference with getXMLData
	 * is that this method provides already the necessary changes in the ownership
	 * internally (which is maintained to provide compatibility)
	 * 
	 * 
	 * 
	 * @param oldXmlData		Application identifier
	 * @param newUserID	New user identifier.
	 * 
	 * @return	XML file describing the rule, null if there was any parsing problem
	 * 
	 * @author David Rozas 
	 * 
	 */
	public static String getXmlRule(String oldXmlData, String newUserID){
		//String oldXmlData = this.getXmlData(appID, ruleID);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Reader reader=new CharArrayReader(oldXmlData.toCharArray());
			Document document = db.parse(new org.xml.sax.InputSource(reader));
			
	   
		   //Change the ownership in the nodes NAME which are children of RESULT (not of CONDITION)
		   NodeList list = document.getElementsByTagName("RESULT");
		   for (int i=0; i<list.getLength(); i++)
		   {
			   Node n = list.item(i);
			   NodeList children = n.getChildNodes();
			   
			   for (int j=0; j<children.getLength(); j++){
				   Node c = children.item(j);

				   if (c.getNodeName().equalsIgnoreCase("NAME"))
				   {
					   String[] parts = c.getTextContent().split(":");
					   //The new value will be newUserId + applicationName
					   String newValue = newUserID + ":" + parts[parts.length-1];
					   c.setTextContent(newValue);
				   }
			   }
			   
		   }
		   
		   //Change the rule identifier in the node "RULE_NAME"
		   list = document.getElementsByTagName("RULE_NAME");
		   for (int i=0; i<list.getLength(); i++)
		   {
			   Node n = list.item(i);

			   String[] parts = n.getTextContent().split(":");
			   //The new value will be newUserId + ruleName
			   String newValue = newUserID + ":" + parts[parts.length-1];
			   n.setTextContent(newValue);
			   
		   }
		   
		   return documentToString(document);

		} catch (Exception e) {
		     e.printStackTrace();
		     return null;
		}	
		
		

	}
	
	


	/**
	 * 
	 * It analyzes the rule in Xml format returning a description in 
	 * a readable way
	 * 
	 * @param XmlRule	Rule in XML
	 * @return	Rule description
	 * 
	 * @author David Rozas
	 */
	public static String parseRuleDescription(String xmlRule)
	{

		String ruleDescription = "";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try 
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Reader reader=new CharArrayReader(xmlRule.toCharArray());
			Document document = db.parse(new org.xml.sax.InputSource(reader));


			//Take all the nodes "condition"
			NodeList list = document.getElementsByTagName("CONDITION_PART");
			if (list.getLength()>0)
			{
				//It has a complex condition

				for (int i=0; i<list.getLength(); i++)
				{
					Node condition_part = list.item(i);


					//Paint first condition children (this is always a leaf)
					NodeList children = condition_part.getChildNodes();
					int j=0; 
					while(children.item(j).getNodeType()!=Node.ELEMENT_NODE)
						j++;

					//paintLeafCondition(children.item(j));
					ruleDescription += getLeafCondition(children.item(j));

					//Paint AND/OR operator
					NodeList siblings = condition_part.getParentNode().getChildNodes();
					j=0; 
					while(siblings.item(j).getNodeType()!=Node.ELEMENT_NODE)
						j++;
					//System.out.println(siblings.item(j).getTextContent());
					ruleDescription+= siblings.item(j).getTextContent() +"\n";

					//Paint the second children, but only in the case is a simple one (only the last one is a leaf)

					//If it is the last "condition_part" node, it means this is the second children which is leaf
					if (i==(list.getLength()-1))
					{
						NodeList lastConditions = condition_part.getChildNodes();
						//Paint the second children leaf
						Node lastCondition = lastConditions.item(lastConditions.getLength()-1);
						//paintLeafCondition(lastCondition);
						ruleDescription+= getLeafCondition(lastCondition);
					}
				}

			}else{
				//It has a simple condition
				//In this case, we just need to take the node condition
				NodeList conditions = document.getElementsByTagName("CONDITION");

				//There should be only one, but just in case
				for (int i=0; i<conditions.getLength(); i++)
				{
					Node leafCondition = conditions.item(i);
					ruleDescription = getLeafCondition(leafCondition);
					//this.retrieveRuleDescription.setText(this.paintLeafCondition(leafCondition));

				}
			}

			return ruleDescription;


		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	

		
	}
	
	/**
	 * 
	 * Returns a more readable description of the condition
	 * 
	 * @param n	Node with the condition
	 * 
	 * @return	String with the descritpion
	 * 
	 * @author David Rozas
	 */
	private static String getLeafCondition(Node n)
	{
		//Paint the info of a Leaf condition
		String res = "";
		
		NodeList children = n.getChildNodes();
		for (int j=0; j<children.getLength(); j++)
		{
			Node c = children.item(j);
			if (c.getNodeType()==Node.ELEMENT_NODE)
			{
				//Return the answer in a more readable way
				
				if(c.getNodeName().equalsIgnoreCase("TYPE"))
				{
					//Conversion of type to "natural language"
					if (c.getTextContent().equalsIgnoreCase("Awareness")){
						res+= "my state ";
					}else if(c.getTextContent().equalsIgnoreCase("Service")){
						res+= "the service ";
					}else if(c.getTextContent().equalsIgnoreCase("InvokeMessage")){
						res+= "the application ";
					}
				
				}
				else if(c.getNodeName().equalsIgnoreCase("NAME"))
				{
				
					//If type is "Service" (device), we divide the name in service an device
					if (c.getPreviousSibling().getTextContent().equalsIgnoreCase("Service"))
					{
						String[] parts = c.getTextContent().split("@");
						res+= parts[1] + " in " + parts[0] + " ";
						
					}else{
						//application, device or state name (literal)
						res+= c.getTextContent() + " ";
					}
					
						
				}
				else if(c.getNodeName().equalsIgnoreCase("COMPARISON_OPERATOR"))
				{
					//Conversion of operators
					if (c.getTextContent().equalsIgnoreCase("EQ")){
						res+= "is ";
					}else if(c.getTextContent().equalsIgnoreCase("NEQ")){
						res+= "is not ";
					}
						
				}
				else if(c.getNodeName().equalsIgnoreCase("VALUE"))
				{

					
					//If it is an "InvokeMessage", we say "active/inactive instead"
					if (c.getParentNode().getFirstChild().getTextContent().equalsIgnoreCase("InvokeMessage"))
					{
						if (c.getTextContent().equalsIgnoreCase("true"))
							res+= "active";
						else if(c.getTextContent().equalsIgnoreCase("false"))
							res+= "inactive";
					}else{
						//value (literal: true/false)
						res+= c.getTextContent();
					}
						

				}
			}
		}
		res+="\n";
		return res;

	}
	
	/**
	 * 
	 * Transforms a DOM document into a String
	 * 
	 * 
	 * @param document	DOM document to transform
	 *
	 * @return String with the representation of the tree
	 * 
	 * @throws Exception
	 * 
	 * @author David Rozas
	 * 
	 */
	private static String documentToString(Document document) throws Exception
	{
        //set up a transformer
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");

        //create string from xml tree
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(document);
        trans.transform(source, result);
        return sw.toString();
	}
	


}
