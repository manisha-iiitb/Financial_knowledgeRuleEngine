package DM.Project;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Stock_scraping {

	public static void createCSV(){
		print("running...");
		Document document;
		try {
			//Get Document object after parsing the html from given url.
			document = Jsoup.connect("https://www.moneycontrol.com/stocks/marketinfo/eps/bse/index.html").get();

			String title = document.title(); //Get title
			//print("  Title: " + title); //Print title.
			Elements table = document.select(".tbldata14 > tbody");
			Elements rows=table.select("tr");
			FileOutputStream fout=new FileOutputStream("/home/divya/Downloads/DM project/output_stock.csv");  
			PrintStream csv=new PrintStream(fout); 
		//	csv.println("Company,eps");
			for (int i=2; i < rows.size(); i++) {
				Element row=rows.get(i);
				Elements cols=row.select("td>a>b");
				Elements col1=row.select("td");
				String s=col1.get(2).text();
				s = s.replaceAll(",","");
				Double d=Double.valueOf(s);
				csv.println(cols.get(0).text()+"\t"+col1.get(5).text()+"\t"+d);
				}
		
			
			
			/*for (int i=0; i < price.size()-2; i++) {
				csv.println(address.get(i).text() + "	" + price.get(i).text());
			}*/
			fout.close();
			databaseConnection db=new databaseConnection();
			db.readCsvUsingLoad();
		} catch (IOException e) {
			e.printStackTrace();
		}
		print("done");
	}

	public static void print(String string) {
		System.out.println(string);
	}
}
