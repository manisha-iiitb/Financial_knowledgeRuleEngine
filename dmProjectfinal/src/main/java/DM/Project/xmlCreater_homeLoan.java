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
	 static Double minimum_Interest;
	 static Integer timePeriod;
	 static Integer principal;
	 static String []bankname;
	 
	 public void getData(String BankName, Double rateofInterest,Integer timePeriod,Integer principal,String []bankname,Double minimum_Interest)
		{
			xmlCreater_homeLoan.BankName=BankName;
			xmlCreater_homeLoan.rateofInterest=rateofInterest;
			xmlCreater_homeLoan.timePeriod=timePeriod;
			xmlCreater_homeLoan.principal=principal;
			xmlCreater_homeLoan.bankname = bankname;
			xmlCreater_homeLoan.minimum_Interest = minimum_Interest;
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
	         
	               
	                    Element rof = document.createElement("rateofInterest");
	                    rof.appendChild(document.createTextNode(rateofInterest.toString()));
	                    root.appendChild(rof);
	         
	                  
	                    Element tp = document.createElement("timePeriod");
	                    tp.appendChild(document.createTextNode(timePeriod.toString()));
	                    root.appendChild(tp);
	                    
	                    Element p = document.createElement("principal");
	                    p.appendChild(document.createTextNode(principal.toString()));
	                    root.appendChild(p);
	         
	                    // BankName element
	                    for(int i=0;i<bankname.length;i++)
	                    {
	                    	 Element Banks = document.createElement("Banks");
		                     Banks.appendChild(document.createTextNode(bankname[i]));
		                    root.appendChild(Banks);
	                    }
	                    
	                    // Minimum_Iterest element
	                    Element min = document.createElement("MinimumInterest");
	                    min.appendChild(document.createTextNode(minimum_Interest.toString()));
	                    root.appendChild(min);
	                    
	                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	                    Transformer transformer = transformerFactory.newTransformer();
	                    DOMSource domSource = new DOMSource(document);
	                    StreamResult streamResult = new StreamResult(new File("/home/divya/Downloads/DM project/HomeLoan.xml"));
	         
	              
	                    transformer.transform(domSource, streamResult);
	         
	                    System.out.println("Done creating XML File");
	         
	                } catch (ParserConfigurationException pce) {
	                    pce.printStackTrace();
	                } catch (TransformerException tfe) {
	                    tfe.printStackTrace();
	                }
	            }
	        }
	           
