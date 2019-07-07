package DM.Project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class buystock {

//createCSV
	public static void createdb(){
		print("running...");
		Document document;
		try {
			//Get Document object after parsing the html from given url.
			document = Jsoup.connect("https://www.moneycontrol.com/stocks/marketinfo/netprofit/bse/index.html").get();

			String title = document.title(); //Get title
			//print("  Title: " + title); //Print title.
			Elements table = document.select(".tbldata14 > tbody");
			Elements rows=table.select("tr");
			FileOutputStream fout=new FileOutputStream("/home/divya/Downloads/DM project/buy_stock.csv");  
			PrintStream csv=new PrintStream(fout); 
		//	csv.println("Company,eps");
			for (int i=1; i < rows.size(); i++) {
				Element row=rows.get(i);
				Elements cols=row.select("td>a>b");
				Elements col1=row.select("td");
				String s=col1.get(4).text();
				s = s.replaceAll(",","");
				Double d=Double.valueOf(s);
				Elements col2=row.select("td");
				String s1=col2.get(1).text();
				s1 = s1.replaceAll(",","");
				Double d1=Double.valueOf(s1);
				
				csv.println(cols.get(0).text()+"\t"+d1+"\t"+col1.get(2).text()+"\t"+col2.get(3).text()+"\t"+d);
				}
		
			document = Jsoup.connect("https://www.moneycontrol.com/stocks/marketinfo/contliab/bse/index.html").get();

			 title = document.title(); //Get title
			//print("  Title: " + title); //Print title.
			table = document.select(".tbldata14 > tbody");
			 rows=table.select("tr");
			 fout=new FileOutputStream("/home/divya/Downloads/DM project/buy_stock2.csv");  
			 csv=new PrintStream(fout); 
			for (int i=1; i < rows.size(); i++) {
				Element row=rows.get(i);
				Elements cols=row.select("td>a>b");
				Elements col1=row.select("td");
				String s=col1.get(5).text();
				s = s.replaceAll(",","");
				Double d=Double.valueOf(s);
				csv.println(cols.get(0).text()+"\t"+d);
				}

			fout.close();
			databaseConnection db=new databaseConnection();
			db.readBuyCsvUsingLoad();
			db.insertdata();
		} catch (IOException e) {
			e.printStackTrace();
		}
		print("done");
	}

	public static void print(String string) {
		System.out.println(string);
	}

}
