package DM.Project;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class FixedDepositAnalysis_Scraping {

	public static void readGeneralFDRates(){
		
		Document document;
		try {
			
			document = Jsoup.connect("https://www.paisabazaar.com/tax/tds-on-fd/").get();

			System.out.println("hi");
			Element table = document.select("table").get(0);
			Elements rows=table.select("tr");
			FileOutputStream fout=new FileOutputStream("general_fixedrate.csv");  
			PrintStream csv=new PrintStream(fout); 
		
			for (int i=2; i < rows.size(); i++) {
				Element row=rows.get(i);
				Elements cols=row.select("td");
				//System.out.println(cols.get(0).text()+","+cols.get(1).text()+","+cols.get(2).text());
				
				csv.println(cols.get(0).text()+","+cols.get(1).text()+","+cols.get(2).text());
				
				}
		
			
			
			fout.close();
			databaseConnection db=new databaseConnection();
			db.readCSVOfFixedDeposit1();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
public static void readTaxSavingFDRates(){
		
		Document document;
		try {
			
			document = Jsoup.connect("https://www.paisabazaar.com/fixed-deposit/interest-rates-of-tax-saving-fd/").get();

			System.out.println("hi");
			Element table = document.select("table").get(0);
			Elements rows=table.select("tr");
			FileOutputStream fout=new FileOutputStream("taxsaving_fixedrate.csv");  
			PrintStream csv=new PrintStream(fout); 
		
			for (int i=2; i < rows.size(); i++) {
				Element row=rows.get(i);
				Elements cols=row.select("td");
				//System.out.println(cols.get(0).text()+","+cols.get(1).text()+","+cols.get(2).text());
				
				csv.println(cols.get(0).text()+","+cols.get(1).text()+","+cols.get(2).text());
				
				}
		
			
			
			fout.close();
			databaseConnection db=new databaseConnection();
			db.readCSVOfFixedDeposit2();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
