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

public class xml_HomeLoanEligibility {
	
	 static String Employment;
	 static String []bankname;
	 static Double rateofInterest;
	 static Integer Age;
	 static Integer MonthlyIncome;
	 static Integer ITR1;


		public void getData(Integer age, Double rateofInterest, Integer monthlyIncome, String employment, Integer ITR1,
			 String []bankname) {
			Age= age;
	        xml_HomeLoanEligibility.rateofInterest = rateofInterest;
	       MonthlyIncome = monthlyIncome;
	        Employment = employment;
	        xml_HomeLoanEligibility.ITR1 = ITR1;
	        xml_HomeLoanEligibility.bankname = bankname;
	
		}

	 public static void setHomeLoanEligibility() {
		 
	    	
	    	
	        try {
	        	
	                    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	         
	                    DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
	         
	                    Document document = documentBuilder.newDocument();
	         
	                    // root element
	                    Element root = document.createElement("HomeLoan");
	                    document.appendChild(root);
	         
	                    
	                    // BankName element
	                    for(int i=0;i<bankname.length;i++)
	                    {
	                    	 Element Bank = document.createElement("BankName");
		                     Bank.appendChild(document.createTextNode(bankname[i]));
		                    root.appendChild(Bank);
	                    }
	                   
	         
	                    // rateOfInterest element
	                    Element rof = document.createElement("rateofInterest");
	                    rof.appendChild(document.createTextNode(rateofInterest.toString()));
	                    root.appendChild(rof);
	                    
	                    //MonthlySalary
	                    Element monthlysalary = document.createElement("MonthlyIncome");
	                    monthlysalary.appendChild(document.createTextNode(MonthlyIncome.toString()));
	                    root.appendChild(monthlysalary);
	         
	                    //Age element
	                    Element age = document.createElement("Age");
	                    age.appendChild(document.createTextNode(Age.toString()));
	                    root.appendChild(age);
	                    
	                    //category element
	                    Element cat = document.createElement("Category");
	                    cat.appendChild(document.createTextNode(Employment.toString()));
	                    root.appendChild(cat);
	                    
	                  //ITR1 element
	                    Element itr1 = document.createElement("TimePeriod");
	                    itr1.appendChild(document.createTextNode(ITR1.toString()));
	                    root.appendChild(itr1);
	                 
	         
	                    
	                    
	                    // create the xml file
	                    //transform the DOM Object to an XML File
	                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	                    Transformer transformer = transformerFactory.newTransformer();
	                    DOMSource domSource = new DOMSource(document);
	                    StreamResult streamResult = new StreamResult(new File("/home/divya/Downloads/DM project/HomeLoanEligibility.xml"));
	         
	                    transformer.transform(domSource, streamResult);
	         
	                    System.out.println("Done creating XML File");
	         
	                } catch (ParserConfigurationException pce) {
	                    pce.printStackTrace();
	                } catch (TransformerException tfe) {
	                    tfe.printStackTrace();
	                }
	            }



}
