package DM.Project;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MF_scraping {

	public static void createCSVMF(){
		print("running...");
		Document document,document2,document3;
		try {
			document = Jsoup.connect("https://www.moneycontrol.com/mutual-funds/best-funds/equity.html").get();
			document2 = Jsoup.connect("https://www.moneycontrol.com/mutual-funds/best-funds/hybrid.html").get();
			document3 = Jsoup.connect("https://www.moneycontrol.com/mutual-funds/best-funds/debt.html").get();			
			//String title = document.title(); //Get title
			
			Elements table = document.select(".mctable1");
			Elements rows=table.select("tr");
			Elements table2 = document2.select(".mctable1");
			Elements rows2=table2.select("tr");
			Elements table3 = document3.select(".mctable1");
			Elements rows3=table3.select("tr");
			FileOutputStream fout=new FileOutputStream("output_MF.csv");  
			PrintStream csv=new PrintStream(fout); 
			//csv.println(rows.text());
		//	csv.println("Company,eps");
			//print(rows.text());
			for (int i=1; i < rows.size(); i++) {
				Element row=rows.get(i);
				Elements cols=row.select("td");
				String[] fundname = cols.get(0).text().split("-");
				//print(row.text());
				csv.println(fundname[0]+","+"Equity"+","+cols.get(8).text()+","+cols.get(10).text()+","+cols.get(11).text()+","+cols.get(12).text());
			}
			for (int i=1; i < rows2.size(); i++) {
				Element row=rows2.get(i);
				Elements cols=row.select("td");
				//print(row.text());
				String[] fundname = cols.get(0).text().split("-");
				csv.println(fundname[0]+","+"Hybrid"+","+cols.get(8).text()+","+cols.get(10).text()+","+cols.get(11).text()+","+cols.get(12).text());
			}
			for (int i=3; i < rows3.size()-10; i++) {
				Element row=rows3.get(i);
				Elements cols=row.select("td");
				//print(row.text());
				String[] fundname = cols.get(0).text().split("-");
				csv.println(fundname[0]+","+"Debt"+","+cols.get(8).text()+","+cols.get(10).text()+","+cols.get(11).text()+","+cols.get(12).text());
			}
			/*for (int i=0; i < price.size()-2; i++) {
				csv.println(address.get(i).text() + "	" + price.get(i).text());
			}*/
			fout.close();
			databaseConnection db=new databaseConnection();
			db.readCsvUsingMF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		print("done");
	}

	public static void print(String string) {
		System.out.println(string);
	}
}
