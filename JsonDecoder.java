
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class JsonDecoder {
	String XpathValues  =null;
	StringBuffer objectData = null;
	static String  message =null;
	String Xpath=null;
	public JsonDecoder(String message) {
		objectData =new StringBuffer();
		JsonDecoder.message=message;
	}
	public	static String  decodeJson(String Xpath) throws IOException{
		String xml=null;
		StringBuffer xmleading = new StringBuffer("<?xml version=\'1.0\' encoding=\'UTF-8\'?>");
		try {
			
			JSONObject json = new JSONObject(message);
			 xml = xmleading.append(org.json.XML.toString(json)).toString(); 
			} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println("json to xml:"+ xml+"\nxpath : "+Xpath);
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			try{
			 	db = dbf.newDocumentBuilder();
			}
			catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			Document doc = null;
			try {
					 try {
						doc = db.parse(new StringBufferInputStream(xml));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 doc.getDocumentElement().normalize();
			}
			catch (SAXException e){
				e.printStackTrace();
			}
			 XPathFactory xpathfactory = XPathFactory.newInstance();
		     XPath xpath = xpathfactory.newXPath();
		     XPathExpression expr = null;
			try {
				expr = xpath.compile(Xpath);
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
			String XpathValues = getXpathValues(doc, expr);
	      //  System.out.println("Xpath Values Retrived:" + Arrays.toString(XpathValues.toArray()));
		   
		   return XpathValues;
		
	}
	
	public static String getXpathValues(Document doc, XPathExpression expr) {
	     String list =null;
        try {
            //create XPathExpression object
            
            //evaluate expression result on XML document
            list = (String) expr.evaluate(doc, XPathConstants.STRING);
            
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }
	 
	 public Boolean  validateXpath(final String Xpath) throws IOException{
			this.Xpath=Xpath;
				
			XpathValues=decodeJson(Xpath);
			if(XpathValues.isEmpty()){
				return false;
			}
						
			return true;
		}
		public String getValue(){
			
		    return XpathValues;
			
		}
		public	String getDump(){
			
			return null;
		}
}
