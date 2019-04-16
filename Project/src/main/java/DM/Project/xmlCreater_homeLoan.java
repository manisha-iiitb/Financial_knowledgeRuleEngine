package DM.Project;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class xmlCreater_homeLoan {
	
	static String BankName;
	 static Double rateofInterest;
	 static Integer timePeriod;
	 static Integer principal;
	 
	 public void getData(String BankName, Double rateofInterest,Integer timePeriod,Integer principal)
		{
			this.BankName=BankName;
			this.rateofInterest=rateofInterest;
			this.timePeriod=timePeriod;
			this.principal=principal;
		}

	 public static void setHomeLoan() {
		 
	    	
	    	
	        try {
	        	
	                    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	         
	                    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	         
	                    Document document = documentBuilder.newDocument();
	         
	                    // root element
	                    Element root = document.createElement("HomeLoan");
	                    document.appendChild(root);
	         
	                    // employee element
	                    Element Bank = document.createElement("BankName");
	                     Bank.appendChild(document.createTextNode(BankName));
	                    root.appendChild(Bank);
	         
	                    // set an attribute to staff element
	               /*     Attr attr = document.createAttribute("id");
	                    attr.setValue("10");getData
	                    employee.setAttributeNode(attr);*/
	         
	                    //you can also use staff.setAttribute("id", "1") for this
	         
	                    // firstname element
	                    Element rof = document.createElement("rateofInterest");
	                    rof.appendChild(document.createTextNode(rateofInterest.toString()));
	                    root.appendChild(rof);
	         
	                    // lastname element
	                    Element tp = document.createElement("timePeriod");
	                    tp.appendChild(document.createTextNode(timePeriod.toString()));
	                    root.appendChild(tp);
	                    
	                    Element p = document.createElement("principal");
	                    p.appendChild(document.createTextNode(principal.toString()));
	                    root.appendChild(p);
	         
	                              // create the xml file
	                    //transform the DOM Object to an XML File
	                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	                    Transformer transformer = transformerFactory.newTransformer();
	                    DOMSource domSource = new DOMSource(document);
	                    StreamResult streamResult = new StreamResult(new File("/home/manisha/Downloads/DM project/HomeLoan.xml"));
	         
	                    // If you use
	                    // StreamResult result = new StreamResult(System.out);
	                    // the output will be pushed to the standard output ...
	                    // You can use that for debugging 
	         
	                    transformer.transform(domSource, streamResult);
	         
	                    System.out.println("Done creating XML File");
	         
	                } catch (ParserConfigurationException pce) {
	                    pce.printStackTrace();
	                } catch (TransformerException tfe) {
	                    tfe.printStackTrace();
	                }
	            }
	        }
	           
