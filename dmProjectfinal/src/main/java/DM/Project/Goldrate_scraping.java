package DM.Project;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Goldrate_scraping {

	public static void createCSVGold(){
		print("running...");
		Document document;
		try {
			//Get Document object after parsing the html from given url.
			document = Jsoup.connect("https://www.policybazaar.com/gold-rate").get();
			String title = document.title(); //Get title
			//print("  Title: " + title); //Print title.
			Element table = document.select(".trande_table").get(0);
			Elements rows=table.select("tr");
			FileOutputStream fout=new FileOutputStream("output_gold.csv");  
			PrintStream csv=new PrintStream(fout); 
		//	csv.println("Company,eps");
			//print(rows.text());
			for (int i=1; i < rows.size(); i++) {
				Element row=rows.get(i);
				Elements cols=row.select("td");
				print(cols.get(1).text());
				csv.println(cols.get(0).text()+","+cols.get(1).text());
			}
			
			/*for (int i=0; i < price.size()-2; i++) {
				csv.println(address.get(i).text() + "	" + price.get(i).text());
			}*/
			fout.close();
			databaseConnection db=new databaseConnection();
			db.readCsvUsingGold();
		} catch (IOException e) {
			e.printStackTrace();
		}
		print("done");
	}

	public static void print(String string) {
		System.out.println(string);
	}
}
