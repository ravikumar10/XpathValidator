import java.io.IOException;
import java.io.StringReader;
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

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.io.StringReader;
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

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class SoapDecoder {
	String XpathValues  =null;
	StringBuffer objectData = null;
	static String  message =null;
	String Xpath=null;
	public SoapDecoder(String message) {
		objectData =new StringBuffer();
		SoapDecoder.message=message;
	}
	static String  decodeSoap(String Xpath) throws IOException{
		   
		   
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
						doc = db.parse(new InputSource( new StringReader(message)));
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
//	        System.out.println("Xpath Values Retrived:" +Arrays.toString(XpathValues.toArray()));
		   
		   return XpathValues;
		   
			 
		}

	 static String getXpathValues(Document doc, XPathExpression expr) {
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
			XpathValues=decodeSoap(Xpath);
			if(XpathValues.isEmpty()){
					return false;
			}
						
			return true;
		}
		public String getValue(){
			
		    return XpathValues;
			
		}
		String getDump(){
			
			return null;
		}
}
