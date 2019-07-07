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
 
public class xmlCreatorfd {
 static String bankname;
 static Double roi;
 static Integer amount;
 static Integer years;
 static Double max_genreg;
 static Double max_gensen;
 static Double max_taxreg;
 static Double max_taxsen;
 static String bank1;
 static String bank2;
 static String bank3;
 static String bank4;
 static String category;
 static Integer income;
 
 public void FDDetails(String category, int years,int amount,int income,String bank1,double max_genreg,String bank2,double max_gensen,
		 String bank3,double max_taxreg,String bank4,double max_taxsen)
	{
		this.category=category;
		this.years=years;
		this.amount=amount;
		this.income=income;
		this.max_genreg=max_genreg;
		this.max_gensen=max_gensen;
		this.max_taxreg=max_taxreg;
		this.max_taxsen=max_taxsen;
		this.bank1=bank1;
		this.bank2=bank2;
		this.bank3=bank3;
		this.bank4=bank4;
	}
 
 

	public void getAmount(String bankname,Double roi,Integer amount,Integer years)
	{
		this.bankname=bankname;
		this.roi=roi;
		this.amount=amount;
		this.years=years;
		
	}
	
	public static void createFDXML() {

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
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(
					new File("/home/divya/Downloads/DM project/FixedDeposit.xml"));


			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
   
    //Divya
 public static void createFDAXML() {
 
    	
    	
        try {
        	
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("FixedDeposit");
            document.appendChild(root);
 
           
            Element Income = document.createElement("income");
             Income.appendChild(document.createTextNode(income.toString()));
            root.appendChild(Income);
 
           
           
            Element Category = document.createElement("category");
            Category.appendChild(document.createTextNode(category.toString()));
            root.appendChild(Category);
 
           
            Element Year = document.createElement("years");
            Year.appendChild(document.createTextNode(years.toString()));
            root.appendChild(Year);
            
            Element Amount = document.createElement("amount");
            Amount.appendChild(document.createTextNode(amount.toString()));
            root.appendChild(Amount);
            
            
            Element regular = document.createElement("Regular-Citizen");
           
            root.appendChild(regular);
            
            
            Element regularfdbank = document.createElement("Bank-NormalFD");
            regularfdbank.appendChild(document.createTextNode(bank1.toString()));
            regular.appendChild(regularfdbank);
            
            Element regularfdrate = document.createElement("NormalFDRate");
            regularfdrate.appendChild(document.createTextNode(max_genreg.toString()));
            regular.appendChild(regularfdrate);
            
            Element taxregfdbank = document.createElement("Bank-TaxSavingFD");
            taxregfdbank.appendChild(document.createTextNode(bank3.toString()));
            regular.appendChild(taxregfdbank);
            
            Element taxregfdrate = document.createElement("TaxSavingFDRate");
            taxregfdrate.appendChild(document.createTextNode(max_taxreg.toString()));
            regular.appendChild(taxregfdrate);
            
            
            Element senior = document.createElement("Senior-Citizen");
           
            root.appendChild(senior);
            
            
            Element seniorregfdbank = document.createElement("Bank-NormalFD");
            seniorregfdbank.appendChild(document.createTextNode(bank2.toString()));
            senior.appendChild(seniorregfdbank);
            
            Element seniorregfdrate = document.createElement("NormalFDRate");
            seniorregfdrate.appendChild(document.createTextNode(max_gensen.toString()));
            senior.appendChild(seniorregfdrate);
            
            Element seniortaxfdbank = document.createElement("Bank-TaxSavingFD");
            seniortaxfdbank.appendChild(document.createTextNode(bank4.toString()));
            senior.appendChild(seniortaxfdbank);
            
            Element seniortaxfdrate = document.createElement("TaxSavingFDRate");
            seniortaxfdrate.appendChild(document.createTextNode(max_taxsen.toString()));
            senior.appendChild(seniortaxfdrate);
 
                      // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("/home/divya/Downloads/DM project/fixedDepositAnalysis.xml"));
 
            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating FD XML File");
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
 
 
}