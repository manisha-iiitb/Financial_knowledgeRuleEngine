package DM.Project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HomeloanScrape {

	public void ScrapingInterest() {

		databaseConnection db = new databaseConnection();
		print("running...");
		Document document;
		try {

			document = Jsoup.connect("https://www.myloancare.in/home-loan-interest-rates/").get();

			FileOutputStream fout = new FileOutputStream("homeloan.csv");
			PrintStream csv = new PrintStream(fout);

			Element table = document.select("table").get(1);
			Elements rows = table.select("tr");

			for (int i = 1; i < rows.size(); i++) { // first row is the col names so skip it.
				Element row = rows.get(i);
				Elements cols = row.select("td");
				csv.println(cols.get(0).text() + ";" + cols.get(1).text());
			}

			fout.close();
			db.readCsvUsingLoadHomeLoan();

		} catch (IOException e) {
			e.printStackTrace();
		}
		print("done");
	}

	public static void print(String string) {
		System.out.println(string);
	}

}
