import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

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

public class XmlDecoder {
    String XpathValues  =null;
	StringBuffer objectData = null;
	static String  message =null;
	String Xpath=null;
	String httpCode=null;
	public XmlDecoder(String message) {
		objectData =new StringBuffer();
		XmlDecoder.message=message;
	}
	static String  decodeXml(String Xpath) throws IOException{
		   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		   dbf.setNamespaceAware(true);
			DocumentBuilder db = null;
			try{
			 	db = dbf.newDocumentBuilder();
			}
			catch (ParserConfigurationException e) {
					e.printStackTrace();
			}
			Document doc = null;
			try {
					 try {
						doc = db.parse(new InputSource( new StringReader(message)));
					} catch (IOException e) {
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
			return XpathValues;
	}
	
	static String getXpathValues(Document doc, XPathExpression expr) {
	     String list = null;
        try {
            list = (String) expr.evaluate(doc, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }
	public Boolean  validateXpath(final String Xpath) throws IOException{
			this.Xpath=Xpath;
			XpathValues=decodeXml(Xpath);
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
