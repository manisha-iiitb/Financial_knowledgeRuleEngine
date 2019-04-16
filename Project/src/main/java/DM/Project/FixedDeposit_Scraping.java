package DM.Project;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class FixedDeposit_Scraping {

	public static void createCSV2(){
		
		Document document;
		try {
			
			document = Jsoup.connect("https://www.policybazaar.com/fixed-deposit/").get();

			
			Element table = document.select("table").get(0);
			Elements rows=table.select("tr");
			FileOutputStream fout=new FileOutputStream("output_fixed.csv");  
			PrintStream csv=new PrintStream(fout); 
		
			for (int i=1; i < rows.size(); i++) {
				Element row=rows.get(i);
				Elements cols=row.select("td");
				
				csv.println(cols.get(0).text()+","+cols.get(2).text());
				
				}
		
			
			
			fout.close();
			databaseConnection db=new databaseConnection();
			db.readCSVOfFixedDeposit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
