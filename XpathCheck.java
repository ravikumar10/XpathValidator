import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class XpathCheck{
	
	public static void main(String[] args)  {
		 	String filename = null;
		    String type = null;
		    String parameter = null;
		    
		    for(int i=0;i<args.length;i++)
		    {
		        String arg = args[i];
		        if(arg.equalsIgnoreCase("-xml"))
		        {
		            if((args.length-i)>0)
		            	filename = args[++i];
		            type="XML";
		        }
		        else if(arg.equalsIgnoreCase("-soap"))
	            {
		            if((args.length-i)>0)
		            	filename = args[++i];
		            type="SOAP";
	            }
		        else if(arg.equalsIgnoreCase("-json"))
	            {
		            if((args.length-i)>0)
		            	filename = args[++i];
		            type="JSON";
	            }
		        else if(arg.equalsIgnoreCase("-p"))
	            {
		            if((args.length-i)>0)
		            	parameter = args[++i];
	            }
		    }
		    if(filename !=null && parameter != null)
		    {
		    	if(type.equalsIgnoreCase("XML")){
					
		    		XmlDecoder xmlDecoder =new XmlDecoder(getFileContent(filename));
					try {
						if(xmlDecoder.validateXpath(parameter)){
							System.out.println(xmlDecoder.getValue());
						}
						else System.out.println("No value found matching to the given Xpath");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(type.equalsIgnoreCase("SOAP")){
					
					SoapDecoder soapDecoder =new SoapDecoder(getFileContent(filename));
					try {
						if(soapDecoder.validateXpath(parameter)){
							System.out.println(soapDecoder.getValue());
						}
						else System.out.println("No value found matching to the given Xpath");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(type.equalsIgnoreCase("JSON")){
					JsonDecoder jsonDecoder =new JsonDecoder(getFileContent(filename));
					try {
						if(jsonDecoder.validateXpath(parameter)){
							System.out.println(jsonDecoder.getValue());
						}
						else System.out.println("No value found matching to the given Xpath");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
				}
		        
		    }
		    
		   
	}
	static String getFileContent(final String dataFilename){
		 StringBuffer messg = new StringBuffer();
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(dataFilename));
        } catch (FileNotFoundException e1) {
          
            e1.printStackTrace();
        }
        try 
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                
                    messg.append(sCurrentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } 
      
    	return messg.toString();
    }

}
