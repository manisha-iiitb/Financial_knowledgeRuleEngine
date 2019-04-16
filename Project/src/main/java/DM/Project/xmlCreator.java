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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import DM.Project.*;
 
public class xmlCreator {
 static String bankname;
 static Double roi;
 static Integer amount;
 static Integer years;
 
static String cn;
 static Double cps;
 static Double eps;
 static 	Double grams;
 static String carat;
 static Double curr_rate;
	public void getGoldData(Double gms,String ct ,Double rate )
	{
		grams=gms;
		carat=ct;
		curr_rate=rate;
	}
	public void getData(String companyname, Double cps,Double eps )
	{
		cn=companyname;
		this.cps=cps;
		this.eps=eps;
	}
	
	public void getAmount(String bankname,Double roi,Integer amount,Integer years)
	{
		this.bankname=bankname;
		this.roi=roi;
		this.amount=amount;
		this.years=years;
		
	}
    public static void set() {
 
    	
    	
        try {
        	
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("stock");
            document.appendChild(root);
 
            // employee element
            Element company = document.createElement("company_name");
             company.appendChild(document.createTextNode(cn));
            root.appendChild(company);
 
            // set an attribute to staff element
       /*     Attr attr = document.createAttribute("id");
            attr.setValue("10");
            employee.setAttributeNode(attr);*/
 
            //you can also use staff.setAttribute("id", "1") for this
 
            // firstname element
            Element EPS = document.createElement("EPS");
            EPS.appendChild(document.createTextNode(eps.toString()));
            root.appendChild(EPS);
 
            // lastname element
            Element CPS = document.createElement("Current_Stock_Price");
            CPS.appendChild(document.createTextNode(cps.toString()));
            root.appendChild(CPS);
 
                      // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("/home/manisha/Downloads/DM project/STOCK.xml"));
 
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
    
 public static void createXML() {
 
    	
    	
        try {
        	
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("FixedDeposit");
            document.appendChild(root);
 
           
            Element bank = document.createElement("bankname");
             bank.appendChild(document.createTextNode(bankname));
            root.appendChild(bank);
 
           
           
            Element principal = document.createElement("principal");
            principal.appendChild(document.createTextNode(amount.toString()));
            root.appendChild(principal);
 
           
            Element year = document.createElement("years");
            year.appendChild(document.createTextNode(years.toString()));
            root.appendChild(year);
            
            Element rate = document.createElement("rate");
            rate.appendChild(document.createTextNode(roi.toString()));
            root.appendChild(rate);
 
                      // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("/home/manisha/Downloads/DM project/FixedDeposit.xml"));
 
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
 
 //Jhalak
 public static void goldXML() {
     try {
     	
         DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

         DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

         Document document = documentBuilder.newDocument();

         // root element
         Element root = document.createElement("Gold_investment");
         document.appendChild(root);

         // employee element
         Element carats = document.createElement("carat");
          carats.appendChild(document.createTextNode(carat.toString()));
         root.appendChild(carats);

         // set an attribute to staff element
    /*     Attr attr = document.createAttribute("id");
         attr.setValue("10");
         employee.setAttributeNode(attr);*/

         //you can also use staff.setAttribute("id", "1") for this

         
         Element current_rate = document.createElement("current_rate");
         current_rate.appendChild(document.createTextNode(curr_rate.toString()));
         root.appendChild(current_rate);

        
         Element gram = document.createElement("grams");
         gram.appendChild(document.createTextNode(grams.toString()));
         root.appendChild(gram);

                   // create the xml file
         //transform the DOM Object to an XML File
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource domSource = new DOMSource(document);
         StreamResult streamResult = new StreamResult(new File("/home/manisha/Downloads/DM project/gold.xml"));

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